package diaz.ignacio.api.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import diaz.ignacio.model.Actor;
import diaz.ignacio.model.Movie;
import diaz.ignacio.service.MovieService;
import exceptions.ActorNotFoundException;
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

	/**
	 * Creates a movie with the data passed in the request body
	 * 
	 * @param movie
	 * @return	201 - The movie created
	 * 			409 - If the movie was already created
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
		
		LOGGER.info("createMovie - Start");

		try {
			movieService.createMovie(movie);
		} catch (MovieAlreadyExistsError e) {

			return new ResponseEntity<Movie>(HttpStatus.CONFLICT);
		}
		
		LOGGER.info("createMovie - End");

		return new ResponseEntity<Movie>(movie, HttpStatus.CREATED);

	}

	/**
	 * Returs all the movies existent in memory
	 * 
	 * @return	200 - A movies List
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> getMovies() {

		LOGGER.info("getMovies - Start");

		List<Movie> movies = movieService.getMovies();

		LOGGER.info("getMovies - End");

		return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);

	}

	/**
	 * Modifies the movie adding the actor passed in the request body
	 * 
	 * @return The movie with the actor added
	 */
	@RequestMapping(value = "{movieId}", method = RequestMethod.PUT)
	public ResponseEntity<Movie> addAnActor(@RequestBody Actor actor) {
		return null;

	}

	/**
	 * Removes an actor from a movie
	 * 
	 * @return	200 - The movie without the actor
	 * 			404 - If the movie does not exist
	 * 			404 - If the actor does not exist
	 */
	@RequestMapping(value = "{movieId}/actors/{actorId}", method = RequestMethod.DELETE)
	public ResponseEntity<Movie> removeAnActorFromAMovie(@PathVariable("movieId") long movieId,
			@PathVariable("actorId") long actorId) {
		
		LOGGER.info("getMovies - Start");

		try {

			Movie movie = movieService.deleteActor(movieId, actorId);

			LOGGER.info("getMovies - end");

			return new ResponseEntity<Movie>(movie, HttpStatus.OK);

		} catch (MovieNotFoundException e) {

			//TODO add a message to the response

			LOGGER.info("getMovies - end");
			return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);

		} catch (ActorNotFoundException e) {

			//TODO add a message to the response
			return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);

		}
		

	}

	/**
	 * Lists the cast of the movie passed by parameter
	 * 
	 * @return
	 */
	@RequestMapping(value = "{movieId}/actors", method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> listActors() {
		return null;

	}

}
