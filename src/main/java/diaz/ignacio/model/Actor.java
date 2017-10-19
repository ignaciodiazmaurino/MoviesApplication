package diaz.ignacio.model;

/**
 * Actor Class
 * @author ignaciodiazmaurino
 */
public class Actor {

	private Long id;
	private String name;
	private String lastName;
	private String link;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}

}
