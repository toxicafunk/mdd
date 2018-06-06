
package es.eci.catalog.model.pojos;


import java.io.Serializable;
import java.util.List;

public class Brand implements Serializable {

	private static final long serialVersionUID = 15282911457853L;

	private Integer id;	
	private String name;
	private String code;
	private String atgCode;
	private String description;
	private Boolean displayBrand;
	
	public Brand() {}
	
	public Brand(
		Integer id,
	String name
	,	String code
	,	String atgCode
	,	String description
	,	Boolean displayBrand
	) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.atgCode = atgCode;
		this.description = description;
		this.displayBrand = displayBrand;
	}
	
	
	public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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
  	public Boolean getDisplayBrand() {
  		return this.displayBrand;
  	}
  	
  	public void setDisplayBrand(Boolean displayBrand) {
  		this.displayBrand = displayBrand;
  	}
}			
		