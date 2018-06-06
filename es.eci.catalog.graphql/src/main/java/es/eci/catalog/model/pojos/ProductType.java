
package es.eci.catalog.model.pojos;


import java.io.Serializable;
import java.util.List;

public class ProductType implements Serializable {

	private static final long serialVersionUID = 15282911186137L;

	private Integer id;	
	private String code;
	private String atgCode;
	private List<Map> descriptions;
	private String[] parents;
	private Boolean isMarketplace;
	private String categoryListCode;
	private String categoryListLabel;
	private List<Category> categoryCodes;
	private String importId;
	
	public ProductType() {}
	
	public ProductType(
		Integer id,
	String code
	,	String atgCode
	,	List<Map> descriptions
	,	String[] parents
	,	Boolean isMarketplace
	,	String categoryListCode
	,	String categoryListLabel
	,	List<Category> categoryCodes
	,	String importId
	) {
		this.id = id;
		this.code = code;
		this.atgCode = atgCode;
		this.descriptions = descriptions;
		this.parents = parents;
		this.isMarketplace = isMarketplace;
		this.categoryListCode = categoryListCode;
		this.categoryListLabel = categoryListLabel;
		this.categoryCodes = categoryCodes;
		this.importId = importId;
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
  	public String getAtgCode() {
  		return this.atgCode;
  	}
  	
  	public void setAtgCode(String atgCode) {
  		this.atgCode = atgCode;
  	}
  	public List<Map> getDescriptions() {
  		return this.descriptions;
  	}
  	
  	public void setDescriptions(List<Map> descriptions) {
  		this.descriptions = descriptions;
  	}
  	public String[] getParents() {
  		return this.parents;
  	}
  	
  	public void setParents(String[] parents) {
  		this.parents = parents;
  	}
  	public Boolean getIsMarketplace() {
  		return this.isMarketplace;
  	}
  	
  	public void setIsMarketplace(Boolean isMarketplace) {
  		this.isMarketplace = isMarketplace;
  	}
  	public String getCategoryListCode() {
  		return this.categoryListCode;
  	}
  	
  	public void setCategoryListCode(String categoryListCode) {
  		this.categoryListCode = categoryListCode;
  	}
  	public String getCategoryListLabel() {
  		return this.categoryListLabel;
  	}
  	
  	public void setCategoryListLabel(String categoryListLabel) {
  		this.categoryListLabel = categoryListLabel;
  	}
  	public List<Category> getCategoryCodes() {
  		return this.categoryCodes;
  	}
  	
  	public void setCategoryCodes(List<Category> categoryCodes) {
  		this.categoryCodes = categoryCodes;
  	}
  	public String getImportId() {
  		return this.importId;
  	}
  	
  	public void setImportId(String importId) {
  		this.importId = importId;
  	}
}			
		