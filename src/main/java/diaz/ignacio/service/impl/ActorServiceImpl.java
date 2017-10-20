package diaz.ignacio.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import diaz.ignacio.model.Actor;
import diaz.ignacio.service.ActorService;
import exceptions.ActorNotFoundException;
import ignacio.diaz.dataAccess.impl.DataAccessImpl;

public class ActorServiceImpl implements ActorService {

	private static final String URL_BASE = "http://localhost:8080/MoviesApplication/";
	private static final AtomicLong COUNTER = new AtomicLong();
	
	private DataAccessImpl dataAccess = DataAccessImpl.getInstance();

	@Override
	public Actor createAnActor(Actor actor) {
		
		List<Actor> actors = dataAccess.getActors();
		
		Optional<Actor> optionalActor = actors.stream()
				.filter(a -> a.getName().equals(actor.getName()) && a.getLastName().equals(actor.getLastName()))
				.findAny();
		if(optionalActor.isPresent()) {
			return optionalActor.get();
		}
			
		long id = COUNTER.incrementAndGet();
		actor.setId(id);
		actor.setLink(URL_BASE + "/" + id);
		dataAccess.getActors().add(actor);
		return actor;
	}
	
	@Override
	public Actor getActorFromList(Long id) throws ActorNotFoundException {
		Optional<Actor> filteredActor = dataAccess.getActors().stream()
				.filter(a -> id.longValue() == a.getId().longValue()).findFirst();

		Actor actor = filteredActor.orElseThrow(() -> new ActorNotFoundException());

		return actor;
	}
	
	@Override
	public List<Actor> getActors() {
		return dataAccess.getActors();
	}

}
