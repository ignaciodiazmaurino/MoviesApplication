package diaz.ignacio.api.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import diaz.ignacio.model.Actor;
import diaz.ignacio.model.ErrorResponse;
import diaz.ignacio.model.Movie;
import diaz.ignacio.service.ActorService;
import diaz.ignacio.service.MovieService;
import exceptions.ActorAlreadyExistsEcception;
import exceptions.ActorNotFoundException;
import exceptions.ApiException;
import exceptions.MovieAlreadyExistsError;
import exceptions.MovieNotFoundException;

/**
 * Class to manage the request
 * 
 * @author ignaciodiazmaurino
 *
 */
@RestController
@RequestMapping("/movies")
public class MovieController {

	private static final Logger LOGGER = LogManager.getLogger(MovieController.class);

	@Autowired
	private MovieService movieService;
	@Autowired
	private ActorService actorService;

	/**
	 * Creates a movie with the data passed in the request body
	 * 
	 * @param movie
	 * @return 201 - The movie created 409 - If the movie was already created
	 * @throws ApiException
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) throws ApiException {

		LOGGER.info("createMovie - Start");

		try {
			movieService.createMovie(movie);
		} catch (MovieAlreadyExistsError e) {

			LOGGER.info("createMovie - Movie already exists");
			throw new ApiException("Movie already exists", HttpStatus.CONFLICT);
		}

		LOGGER.info("createMovie - Movie created");

		return new ResponseEntity<Movie>(movie, HttpStatus.CREATED);

	}

	/**
	 * Returs all the movies existent in memory
	 * 
	 * @return 200 - A movies List
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> getMovies() {

		LOGGER.info("getMovies - Start");

		List<Movie> movies = movieService.getMovies();

		LOGGER.info("getMovies - List of movies returned");

		return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);

	}

	/**
	 * Modifies the movie adding the actor passed in the request body
	 * 
	 * @return The movie with the actor added
	 */
	@RequestMapping(value = "{movieId}", method = RequestMethod.PUT)
	public ResponseEntity<Movie> addAnActor(@PathVariable("movieId") long movieId, @RequestBody Actor actor)
			throws ApiException {

		LOGGER.info("addAnActor - start");
		Movie movie = null;
		try {

			movie = movieService.getMovieFromList(movieId);
			actor = actorService.createAnActor(actor);
			movie = movieService.addActor(movie, actor);

		} catch (MovieNotFoundException e) {
			LOGGER.info("addAnActor - Movie not found");
			throw new ApiException("Movie not found", HttpStatus.NOT_FOUND);
		} catch (ActorAlreadyExistsEcception e) {
			LOGGER.info("addAnActor - Actor already exists");
			throw new ApiException("Actor already exists", HttpStatus.CONFLICT);
		}

		LOGGER.info("addAnActor - Actor added to the movie");
		return new ResponseEntity<Movie>(movie, HttpStatus.CREATED);

	}

	/**
	 * Removes an actor from a movie
	 * 
	 * @return 200 - The movie without the actor 404 - If the movie does not
	 *         exist. 404 - If the actor does not exist
	 */
	@RequestMapping(value = "{movieId}/actors/{actorId}", method = RequestMethod.DELETE)
	public ResponseEntity<Movie> removeAnActorFromAMovie(@PathVariable("movieId") long movieId,
			@PathVariable("actorId") long actorId) throws ApiException {

		LOGGER.info("getMovies - Start");

		try {

			Movie movie = movieService.deleteActor(movieId, actorId);

			LOGGER.info("getMovies - Actor deleted");

			return new ResponseEntity<Movie>(movie, HttpStatus.OK);

		} catch (MovieNotFoundException e) {

			// TODO add a message to the response

			LOGGER.info("getMovies - Movie not found");
			throw new ApiException("Movie not found", HttpStatus.NOT_FOUND);

		} catch (ActorNotFoundException e) {

			// TODO add a message to the response
			LOGGER.info("getMovies - Actor not found");
			throw new ApiException("Actor not found", HttpStatus.NOT_FOUND);

		}

	}

	/**
	 * Lists the cast of the movie passed by parameter
	 * 
	 * @return
	 * @throws ApiException 
	 */
	@RequestMapping(value = "{movieId}/actors", method = RequestMethod.GET)
	public ResponseEntity<List<Actor>> listActors(@PathVariable("movieId") long movieId) throws ApiException {

		try {
			Movie movie = movieService.getMovieFromList(movieId);

			LOGGER.info("listActors - List of actors returned");
			return new ResponseEntity<List<Actor>>(movie.getCast(), HttpStatus.OK);
		} catch (MovieNotFoundException e) {

			LOGGER.info("listActors - Movie not found");
			throw new ApiException("Movie not found", HttpStatus.NOT_FOUND);

		}

	}
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(ApiException exception) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorMessage(exception.getMessage());
		errorResponse.setHttpStatusCode(String.valueOf(exception.getStatusCode()));
		return new ResponseEntity<ErrorResponse>(errorResponse, exception.getStatusCode());
		
	}

	public ActorService getActorService() {
		return actorService;
	}

	public void setActorService(ActorService actorService) {
		this.actorService = actorService;
	}

}
