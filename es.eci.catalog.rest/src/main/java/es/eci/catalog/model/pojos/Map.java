
package es.eci.catalog.model.pojos;


import java.io.Serializable;
import java.util.List;

public class Map implements Serializable {

	private static final long serialVersionUID = 15282911457740L;

	private Integer id;	
	private String key;
	private String value;
	
	public Map() {}
	
	public Map(
		Integer id,
	String key
	,	String value
	) {
		this.id = id;
		this.key = key;
		this.value = value;
	}
	
	
	public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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
		