
package es.eci.catalog.model.pojos.db;


import java.io.Serializable;

public class ImageBean implements Serializable {

	private Integer _id;	
	private String url;
	private String description;
	
	public ImageBean() {}
	
	public ImageBean(
		Integer _id,
	String url
	,	String description
	) {
		this._id = _id;
		this.url = url;
		this.description = description;
	}
	
	
	public Integer getId() {
        return this._id;
    }

    public void setId(Integer _id) {
        this._id = _id;
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
		