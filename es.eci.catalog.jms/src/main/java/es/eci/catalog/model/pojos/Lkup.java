
package es.eci.catalog.model.pojos;


import java.io.Serializable;
import java.util.List;

public class Lkup implements Serializable {

	private static final long serialVersionUID = 152829112678014L;

	private Integer id;	
	private String code;
	private String name;
	private String lookupType;
	private Map descriptions;
	private String atgCode;
	private String externalCode;
	private String logo;
	private String audit;
	private String priority;
	private Glossary glossary;
	private Link reference;
	
	public Lkup() {}
	
	public Lkup(
		Integer id,
	String code
	,	String name
	,	String lookupType
	,	Map descriptions
	,	String atgCode
	,	String externalCode
	,	String logo
	,	String audit
	,	String priority
	,	Glossary glossary
	,	Link reference
	) {
		this.id = id;
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
  	public String getLookupType() {
  		return this.lookupType;
  	}
  	
  	public void setLookupType(String lookupType) {
  		this.lookupType = lookupType;
  	}
  	public Map getDescriptions() {
  		return this.descriptions;
  	}
  	
  	public void setDescriptions(Map descriptions) {
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
  	public Glossary getGlossary() {
  		return this.glossary;
  	}
  	
  	public void setGlossary(Glossary glossary) {
  		this.glossary = glossary;
  	}
  	public Link getReference() {
  		return this.reference;
  	}
  	
  	public void setReference(Link reference) {
  		this.reference = reference;
  	}
}			
		