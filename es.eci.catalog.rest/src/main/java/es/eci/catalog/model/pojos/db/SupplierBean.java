
package es.eci.catalog.model.pojos.db;


import java.io.Serializable;

public class SupplierBean implements Serializable {

	private Integer _id;	
	private String code;
	private String name;
	
	public SupplierBean() {}
	
	public SupplierBean(
		Integer _id,
	String code
	,	String name
	) {
		this._id = _id;
		this.code = code;
		this.name = name;
	}
	
	
	public Integer getId() {
        return this._id;
    }

    public void setId(Integer _id) {
        this._id = _id;
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
		