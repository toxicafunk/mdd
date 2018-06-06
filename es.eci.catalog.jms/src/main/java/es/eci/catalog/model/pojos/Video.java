
package es.eci.catalog.model.pojos;


import java.io.Serializable;
import java.util.List;

public class Video implements Serializable {

	private static final long serialVersionUID = 15282911267276L;

	private Integer id;	
	private String title;
	private String description;
	private String url;
	
	public Video() {}
	
	public Video(
		Integer id,
	String title
	,	String description
	,	String url
	) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.url = url;
	}
	
	
	public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    	
  	public String getTitle() {
  		return this.title;
  	}
  	
  	public void setTitle(String title) {
  		this.title = title;
  	}
  	public String getDescription() {
  		return this.description;
  	}
  	
  	public void setDescription(String description) {
  		this.description = description;
  	}
  	public String getUrl() {
  		return this.url;
  	}
  	
  	public void setUrl(String url) {
  		this.url = url;
  	}
}			
		