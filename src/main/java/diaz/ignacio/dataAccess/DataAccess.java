package diaz.ignacio.dataAccess;

import java.util.List;

import diaz.ignacio.model.Actor;
import diaz.ignacio.model.Movie;

public interface DataAccess {

	List<Actor> getActors();
	void setActors(List<Actor> actors);
	
	List<Movie> getMovies();
	void setMovies(List<Movie> movies);
	
	void addMovie(Movie movie);
}
