package ignacio.diaz.dataAccess.impl;

import java.util.ArrayList;
import java.util.List;

import diaz.ignacio.model.Actor;
import diaz.ignacio.model.Movie;

public final class DataAccessImpl {
	
	private static DataAccessImpl instance = null;
	
	private static List<Movie> movies;
	private static List<Actor> actors;
	private static final String URL_BASE = "http://localhost:8080/MoviesApplication/";
	
	private DataAccessImpl(){};
	
	public static DataAccessImpl getInstance() {
		if (null == instance) {
			instance = new DataAccessImpl();
			movies = new ArrayList<Movie>();
			actors = new ArrayList<Actor>();
			populate(movies, actors);
		}
		
		return instance;
	}

	public void addMovie(Movie movie) {
		movies.add(movie);
	}
	
	private static void populate(List<Movie> movies, List<Actor> actors) {
		
		Actor actor = new Actor();
		long id = 1;
		actor.setId(id);
		actor.setName("Carrie");
		actor.setLastName("Fisher");
		actor.setLink(URL_BASE + id);
		actors.add(actor);
		
		List<Actor> cast = new ArrayList<Actor>();
		cast.add(actor);
		
		actor = new Actor();
		id = 2;
		actor.setId(id);
		actor.setName("Mark");
		actor.setLastName("Hamill");
		actor.setLink(URL_BASE + id);
		actors.add(actor);
		
		cast.add(actor);
		
		Movie movie = new Movie();
		id = 12;
		movie.setId(id);
		movie.setName("Star Wars - Return of the Jedi");
		movie.setYear(1977);
		movie.setCast(cast);
		movie.setLink(URL_BASE + id);
		
		movies.add(movie);

		actor = new Actor();
		id = 3;
		actor.setId(id);
		actor.setName("Marlon");
		actor.setLastName("Brando");
		actor.setLink(URL_BASE + id);
		actors.add(actor);
		
		cast = new ArrayList<Actor>();
		cast.add(actor);
		
		actor = new Actor();
		id = 4;
		actor.setId(id);
		actor.setName("James");
		actor.setLastName("Caan");
		actor.setLink(URL_BASE + id);
		actors.add(actor);
		
		cast.add(actor);
		
		movie = new Movie();
		id = 11;
		movie.setId(id);
		movie.setName("The Goodfather");
		movie.setYear(1972);
		movie.setCast(cast);
		movie.setLink(URL_BASE + id);
		
		movies.add(movie);

	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		DataAccessImpl.movies = movies;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		DataAccessImpl.actors = actors;
	}

}
