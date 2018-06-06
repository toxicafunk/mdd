
package es.eci.catalog.model.pojos;


import java.io.Serializable;
import java.util.List;

public class Supplier implements Serializable {

	private static final long serialVersionUID = 152829114582311L;

	private Integer id;	
	private String code;
	private String name;
	
	public Supplier() {}
	
	public Supplier(
		Integer id,
	String code
	,	String name
	) {
		this.id = id;
		this.code = code;
		this.name = name;
	}
	
	
	public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    	
  	public String getCode() {
  		return this.code;
  	}
  	
  	public void setCode(String code) {
  		this.code = code;
  	}
  	public String getName() {
  		return this.name;
  	}
  	
  	public void setName(String name) {
  		this.name = name;
  	}
}			
		