package diaz.ignacio.service;

import java.util.List;

import diaz.ignacio.model.Actor;

public interface ActorService {
	
	/**
	 * Cretes an actor and store it in memory
	 * @param name		The name of the actor
	 * @param lastName	The lastName of the actor
	 * @return	the id of the actor created
	 */
	long createAnActor(String name, String lastName);
	
	/**
	 * Returns a list of actors
	 * @return all the actors in a list
	 */
	List<Actor> getActors();

}
