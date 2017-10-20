package diaz.ignacio.service;

import java.util.List;

import diaz.ignacio.model.Actor;
import exceptions.ActorNotFoundException;

public interface ActorService {

	/**
	 * Creates an actor and store it in memory
	 * 
	 * @param name		The name of the actor
	 * @param lastName	The lastName of the actor
	 * @return the id of the actor created
	 */
	Actor createAnActor(Actor actor);

	/**
	 * Returns a list of actors
	 * 
	 * @return all the actors in a list
	 */
	List<Actor> getActors();

	/**
	 * Return the actor who has the id passed by parameter
	 * @param id	The id of the actor
	 * @return		An actor
	 * @throws ActorNotFoundException
	 */
	Actor getActorFromList(Long id) throws ActorNotFoundException;

}
