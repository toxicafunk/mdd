
package es.eci.catalog.model.pojos.db;


import java.io.Serializable;

public class VideoBean implements Serializable {

	private Integer _id;	
	private String title;
	private String description;
	private String url;
	
	public VideoBean() {}
	
	public VideoBean(
		Integer _id,
	String title
	,	String description
	,	String url
	) {
		this._id = _id;
		this.title = title;
		this.description = description;
		this.url = url;
	}
	
	
	public Integer getId() {
        return this._id;
    }

    public void setId(Integer _id) {
        this._id = _id;
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
		