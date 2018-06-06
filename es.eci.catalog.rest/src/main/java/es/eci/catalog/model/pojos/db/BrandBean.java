
package es.eci.catalog.model.pojos.db;


import java.io.Serializable;

public class BrandBean implements Serializable {

	private Integer _id;	
	private String name;
	private String code;
	private String atgCode;
	private String description;
	private boolean displayBrand;
	
	public BrandBean() {}
	
	public BrandBean(
		Integer _id,
	String name
	,	String code
	,	String atgCode
	,	String description
	,	boolean displayBrand
	) {
		this._id = _id;
		this.name = name;
		this.code = code;
		this.atgCode = atgCode;
		this.description = description;
		this.displayBrand = displayBrand;
	}
	
	
	public Integer getId() {
        return this._id;
    }

    public void setId(Integer _id) {
        this._id = _id;
    }
    	
  	public String getName() {
  		return this.name;
  	}
  	
  	public void setName(String name) {
  		this.name = name;
  	}
  	public String getCode() {
  		return this.code;
  	}
  	
  	public void setCode(String code) {
  		this.code = code;
  	}
  	public String getAtgCode() {
  		return this.atgCode;
  	}
  	
  	public void setAtgCode(String atgCode) {
  		this.atgCode = atgCode;
  	}
  	public String getDescription() {
  		return this.description;
  	}
  	
  	public void setDescription(String description) {
  		this.description = description;
  	}
  	public boolean getDisplayBrand() {
  		return this.displayBrand;
  	}
  	
  	public void setDisplayBrand(boolean displayBrand) {
  		this.displayBrand = displayBrand;
  	}
}			
		