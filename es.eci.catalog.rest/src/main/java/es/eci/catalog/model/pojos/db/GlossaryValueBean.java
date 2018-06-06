
package es.eci.catalog.model.pojos.db;


import java.io.Serializable;

public class GlossaryValueBean implements Serializable {

	private Integer _id;	
	private String code;
	private String term;
	private String normalizedTerm;
	private String externalCode;
	private String description;
	
	public GlossaryValueBean() {}
	
	public GlossaryValueBean(
		Integer _id,
	String code
	,	String term
	,	String normalizedTerm
	,	String externalCode
	,	String description
	) {
		this._id = _id;
		this.code = code;
		this.term = term;
		this.normalizedTerm = normalizedTerm;
		this.externalCode = externalCode;
		this.description = description;
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
  	public String getTerm() {
  		return this.term;
  	}
  	
  	public void setTerm(String term) {
  		this.term = term;
  	}
  	public String getNormalizedTerm() {
  		return this.normalizedTerm;
  	}
  	
  	public void setNormalizedTerm(String normalizedTerm) {
  		this.normalizedTerm = normalizedTerm;
  	}
  	public String getExternalCode() {
  		return this.externalCode;
  	}
  	
  	public void setExternalCode(String externalCode) {
  		this.externalCode = externalCode;
  	}
  	public String getDescription() {
  		return this.description;
  	}
  	
  	public void setDescription(String description) {
  		this.description = description;
  	}
}			
		