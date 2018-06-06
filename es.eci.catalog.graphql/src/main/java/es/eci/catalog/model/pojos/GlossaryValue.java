
package es.eci.catalog.model.pojos;


import java.io.Serializable;
import java.util.List;

public class GlossaryValue implements Serializable {

	private static final long serialVersionUID = 15282911185822L;

	private Integer id;	
	private String code;
	private String term;
	private String normalizedTerm;
	private String externalCode;
	private String description;
	
	public GlossaryValue() {}
	
	public GlossaryValue(
		Integer id,
	String code
	,	String term
	,	String normalizedTerm
	,	String externalCode
	,	String description
	) {
		this.id = id;
		this.code = code;
		this.term = term;
		this.normalizedTerm = normalizedTerm;
		this.externalCode = externalCode;
		this.description = description;
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
		