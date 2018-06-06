
package es.eci.catalog.model.pojos.db;


import java.io.Serializable;

public class ProductTypeBean implements Serializable {

	private Integer _id;	
	private String code;
	private String atgCode;
	private Integer[] descriptions;
	private String[] parents;
	private boolean isMarketplace;
	private String categoryListCode;
	private String categoryListLabel;
	private Integer[] categoryCodes;
	private String importId;
	
	public ProductTypeBean() {}
	
	public ProductTypeBean(
		Integer _id,
	String code
	,	String atgCode
	,	Integer[] descriptions
	,	String[] parents
	,	boolean isMarketplace
	,	String categoryListCode
	,	String categoryListLabel
	,	Integer[] categoryCodes
	,	String importId
	) {
		this._id = _id;
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
  	public String getAtgCode() {
  		return this.atgCode;
  	}
  	
  	public void setAtgCode(String atgCode) {
  		this.atgCode = atgCode;
  	}
  	public Integer[] getDescriptions() {
  		return this.descriptions;
  	}
  	
  	public void setDescriptions(Integer[] descriptions) {
  		this.descriptions = descriptions;
  	}
  	public String[] getParents() {
  		return this.parents;
  	}
  	
  	public void setParents(String[] parents) {
  		this.parents = parents;
  	}
  	public boolean getIsMarketplace() {
  		return this.isMarketplace;
  	}
  	
  	public void setIsMarketplace(boolean isMarketplace) {
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
  	public Integer[] getCategoryCodes() {
  		return this.categoryCodes;
  	}
  	
  	public void setCategoryCodes(Integer[] categoryCodes) {
  		this.categoryCodes = categoryCodes;
  	}
  	public String getImportId() {
  		return this.importId;
  	}
  	
  	public void setImportId(String importId) {
  		this.importId = importId;
  	}
}			
		