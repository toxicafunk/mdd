
package es.eci.catalog.model.pojos;


import java.io.Serializable;
import java.util.List;

public class Image implements Serializable {

	private static final long serialVersionUID = 15282911404895L;

	private Integer id;	
	private String url;
	private String description;
	
	public Image() {}
	
	public Image(
		Integer id,
	String url
	,	String description
	) {
		this.id = id;
		this.url = url;
		this.description = description;
	}
	
	
	public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    	
  	public String getUrl() {
  		return this.url;
  	}
  	
  	public void setUrl(String url) {
  		this.url = url;
  	}
  	public String getDescription() {
  		return this.description;
  	}
  	
  	public void setDescription(String description) {
  		this.description = description;
  	}
}			
		