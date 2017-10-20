package diaz.ignacio.api.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import diaz.ignacio.model.ErrorResponse;
import diaz.ignacio.model.Movie;
import diaz.ignacio.service.ActorService;
import diaz.ignacio.service.MovieService;
import exceptions.ActorNotFoundException;
import exceptions.ApiException;

@RestController
@RequestMapping("/actors")
public class ActorController {
	
	private static final Logger LOGGER = LogManager.getLogger(ActorController.class);
	
	@Autowired
	private MovieService movieService;
	@Autowired
	private ActorService actorService;
	
	@RequestMapping(value = "/{actorId}", method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> getMoviesOfAnActor(@PathVariable("actorId") long actorId) throws ApiException {

		LOGGER.info("getMoviesOfAnActor - start");
		try {
			actorService.getActorFromList(actorId);
			List<Movie> movies = movieService.getMovies()
					.stream().
					filter(movie -> movie.getCast()
							.stream()
							.anyMatch(actor -> actor.getId().longValue() == actorId)
					)
					.collect(Collectors.toList());
			LOGGER.info("getMoviesOfAnActor - List of movies returned");
			return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
		} catch (ActorNotFoundException e) {
			LOGGER.info("getMoviesOfAnActor - Actor not found");
			throw new ApiException("Actor not found", HttpStatus.NOT_FOUND);
		}

	}
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(ApiException exception) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorMessage(exception.getMessage());
		errorResponse.setHttpStatusCode(String.valueOf(exception.getStatusCode()));
		return new ResponseEntity<ErrorResponse>(errorResponse, exception.getStatusCode());
		
	}
	
}
