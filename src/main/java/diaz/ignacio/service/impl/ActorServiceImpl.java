package diaz.ignacio.service.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import diaz.ignacio.model.Actor;
import diaz.ignacio.service.ActorService;
import ignacio.diaz.dataAccess.impl.DataAccessImpl;

public class ActorServiceImpl implements ActorService {

	private static final String URL_BASE = "http://localhost:8080/MoviesApplication/";
	private static final AtomicLong COUNTER = new AtomicLong();
	
	private DataAccessImpl dataAccess = DataAccessImpl.getInstance();

	@Override
	public long createAnActor(String name, String lastName) {
		
		Actor actor = new Actor();
		long id = COUNTER.incrementAndGet();
		actor.setName(name);
		actor.setLastName(lastName);
		actor.setId(id);
		actor.setLink(URL_BASE + "/" + id);
		dataAccess.getActors().add(actor);
		return id;
	}
	
	@Override
	public List<Actor> getActors() {
		return dataAccess.getActors();
	}

}
