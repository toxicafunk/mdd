
package es.eci.catalog.model.pojos.db;


import java.io.Serializable;

public class MapBean implements Serializable {

	private Integer _id;	
	private String key;
	private String value;
	
	public MapBean() {}
	
	public MapBean(
		Integer _id,
	String key
	,	String value
	) {
		this._id = _id;
		this.key = key;
		this.value = value;
	}
	
	
	public Integer getId() {
        return this._id;
    }

    public void setId(Integer _id) {
        this._id = _id;
    }
    	
  	public String getKey() {
  		return this.key;
  	}
  	
  	public void setKey(String key) {
  		this.key = key;
  	}
  	public String getValue() {
  		return this.value;
  	}
  	
  	public void setValue(String value) {
  		this.value = value;
  	}
}			
		