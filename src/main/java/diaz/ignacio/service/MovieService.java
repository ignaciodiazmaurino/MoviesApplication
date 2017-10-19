package diaz.ignacio.service;

import java.util.List;

import diaz.ignacio.model.Actor;
import diaz.ignacio.model.Movie;
import exceptions.ActorNotFoundException;
import exceptions.MovieAlreadyExistsError;
import exceptions.MovieNotFoundException;

public interface MovieService {

	/**
	 * Creates a new movie
	 * 
	 * @param name		The name of the movie
	 * @param year		Year when the movie was filmed
	 * @param actors	Actors of the movie
	 * @return 			Id of the movie created
	 * @throws			{@link MovieAlreadyExistsError}
	 */
	Movie createMovie(Movie movie) throws MovieAlreadyExistsError;
	
	/**
	 * Add an actor to the movie
	 * 
	 * @param movieId	The Id of the movie
	 * @param actor		Actor to be added
	 * @throws 			{@link MovieNotFoundException}
	 * @throws 			{@link ActorNotFoundException}
	 */
	Movie addActor(Long movieId, Actor actor) throws MovieNotFoundException, ActorNotFoundException;
	
	/**
	 * Delete an actor from a movie
	 * 
	 * @param movieId	The Id of the movie
	 * @param actorId	Actor to be added
	 * @throws 			{@link ActorNotFoundException}
	 * @throws			{@link MovieNotFoundException}
	 */
	Movie deleteActor(Long movieId, Long actorId) throws MovieNotFoundException, ActorNotFoundException;
	
	/**
	 * Return a list with all the movies
	 * 
	 * @return	A list of movies 
	 */
	List <Movie> getMovies();
}
