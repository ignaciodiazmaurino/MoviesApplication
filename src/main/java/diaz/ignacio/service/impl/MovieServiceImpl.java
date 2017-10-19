package diaz.ignacio.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import diaz.ignacio.model.Actor;
import diaz.ignacio.model.Movie;
import diaz.ignacio.service.MovieService;
import exceptions.ActorNotFoundException;
import exceptions.MovieAlreadyExistsError;
import exceptions.MovieNotFoundException;
import ignacio.diaz.dataAccess.impl.DataAccessImpl;

/**
 * Implementation of {@link MovieService} interface
 * 
 * @author ignaciodiazmaurino
 *
 */
public class MovieServiceImpl implements MovieService {

	private final String URL_BASE = "http://localhost:8080/MoviesApplication/";
	private final AtomicLong COUNTER = new AtomicLong();

	private DataAccessImpl dataAccess = DataAccessImpl.getInstance();

	public Movie createMovie(Movie movie) throws MovieAlreadyExistsError {

		Long id = COUNTER.incrementAndGet();
		boolean exists = dataAccess.getMovies().stream()
				.map(Movie::getName)
				.anyMatch(movie.getName()::equals);
		if (exists) {
			throw new MovieAlreadyExistsError();
		}
		movie.setId(id);
		movie.setLink(URL_BASE + id);
		dataAccess.addMovie(movie);

		return movie;
	}

	public Movie addActor(Long movieId, Actor actor) throws MovieNotFoundException {

		Movie movie = getMovieFromList(movieId);
		movie.getCast().add(actor);
		
		return movie;

	}

	public Movie deleteActor(Long movieId, Long actorId) throws MovieNotFoundException, ActorNotFoundException {

		Movie movie = getMovieFromList(movieId);
		int movieListLength = movie.getCast().size();
		movie.getCast().removeIf(a -> actorId.longValue() == a.getId().longValue());

		if (movieListLength == movie.getCast().size()) {
			throw new ActorNotFoundException();
		}
		
		return movie;

	}

	public List<Movie> getMovies() {
		return dataAccess.getMovies();
	}

	private Movie getMovieFromList(Long id) throws MovieNotFoundException {
		Optional<Movie> filteredMovie = dataAccess.getMovies().stream()
				.filter(m -> id.longValue() == m.getId().longValue()).findFirst();

		Movie movie = filteredMovie.orElseThrow(() -> new MovieNotFoundException());

		return movie;
	}

}
