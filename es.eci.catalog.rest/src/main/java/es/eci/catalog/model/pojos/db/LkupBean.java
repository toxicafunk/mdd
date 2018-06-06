
package es.eci.catalog.model.pojos.db;


import java.io.Serializable;

public class LkupBean implements Serializable {

	private Integer _id;	
	private String code;
	private String name;
	private String lookupType;
	private Integer descriptions;
	private String atgCode;
	private String externalCode;
	private String logo;
	private String audit;
	private String priority;
	private Integer glossary;
	private Integer reference;
	
	public LkupBean() {}
	
	public LkupBean(
		Integer _id,
	String code
	,	String name
	,	String lookupType
	,	Integer descriptions
	,	String atgCode
	,	String externalCode
	,	String logo
	,	String audit
	,	String priority
	,	Integer glossary
	,	Integer reference
	) {
		this._id = _id;
		this.code = code;
		this.name = name;
		this.lookupType = lookupType;
		this.descriptions = descriptions;
		this.atgCode = atgCode;
		this.externalCode = externalCode;
		this.logo = logo;
		this.audit = audit;
		this.priority = priority;
		this.glossary = glossary;
		this.reference = reference;
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
  	public String getLookupType() {
  		return this.lookupType;
  	}
  	
  	public void setLookupType(String lookupType) {
  		this.lookupType = lookupType;
  	}
  	public Integer getDescriptions() {
  		return this.descriptions;
  	}
  	
  	public void setDescriptions(Integer descriptions) {
  		this.descriptions = descriptions;
  	}
  	public String getAtgCode() {
  		return this.atgCode;
  	}
  	
  	public void setAtgCode(String atgCode) {
  		this.atgCode = atgCode;
  	}
  	public String getExternalCode() {
  		return this.externalCode;
  	}
  	
  	public void setExternalCode(String externalCode) {
  		this.externalCode = externalCode;
  	}
  	public String getLogo() {
  		return this.logo;
  	}
  	
  	public void setLogo(String logo) {
  		this.logo = logo;
  	}
  	public String getAudit() {
  		return this.audit;
  	}
  	
  	public void setAudit(String audit) {
  		this.audit = audit;
  	}
  	public String getPriority() {
  		return this.priority;
  	}
  	
  	public void setPriority(String priority) {
  		this.priority = priority;
  	}
  	public Integer getGlossary() {
  		return this.glossary;
  	}
  	
  	public void setGlossary(Integer glossary) {
  		this.glossary = glossary;
  	}
  	public Integer getReference() {
  		return this.reference;
  	}
  	
  	public void setReference(Integer reference) {
  		this.reference = reference;
  	}
}			
		