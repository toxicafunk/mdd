
package es.eci.catalog.model.pojos.db;


import java.io.Serializable;

public class CategoryBean implements Serializable {

	private Integer _id;	
	private String code;
	private String atgCode;
	private Integer parentCategory;
	private String status;
	private Integer[] descriptions;
	private Integer[] fullDescriptions;
	private String startDate;
	private String endDate;
	private String invalidationDate;
	private Integer reference;
	private float noveltyDays;
	private Integer[] children;
	
	public CategoryBean() {}
	
	public CategoryBean(
		Integer _id,
	String code
	,	String atgCode
	,	Integer parentCategory
	,	String status
	,	Integer[] descriptions
	,	Integer[] fullDescriptions
	,	String startDate
	,	String endDate
	,	String invalidationDate
	,	Integer reference
	,	float noveltyDays
	,	Integer[] children
	) {
		this._id = _id;
		this.code = code;
		this.atgCode = atgCode;
		this.parentCategory = parentCategory;
		this.status = status;
		this.descriptions = descriptions;
		this.fullDescriptions = fullDescriptions;
		this.startDate = startDate;
		this.endDate = endDate;
		this.invalidationDate = invalidationDate;
		this.reference = reference;
		this.noveltyDays = noveltyDays;
		this.children = children;
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
  	public Integer getParentCategory() {
  		return this.parentCategory;
  	}
  	
  	public void setParentCategory(Integer parentCategory) {
  		this.parentCategory = parentCategory;
  	}
  	public String getStatus() {
  		return this.status;
  	}
  	
  	public void setStatus(String status) {
  		this.status = status;
  	}
  	public Integer[] getDescriptions() {
  		return this.descriptions;
  	}
  	
  	public void setDescriptions(Integer[] descriptions) {
  		this.descriptions = descriptions;
  	}
  	public Integer[] getFullDescriptions() {
  		return this.fullDescriptions;
  	}
  	
  	public void setFullDescriptions(Integer[] fullDescriptions) {
  		this.fullDescriptions = fullDescriptions;
  	}
  	public String getStartDate() {
  		return this.startDate;
  	}
  	
  	public void setStartDate(String startDate) {
  		this.startDate = startDate;
  	}
  	public String getEndDate() {
  		return this.endDate;
  	}
  	
  	public void setEndDate(String endDate) {
  		this.endDate = endDate;
  	}
  	public String getInvalidationDate() {
  		return this.invalidationDate;
  	}
  	
  	public void setInvalidationDate(String invalidationDate) {
  		this.invalidationDate = invalidationDate;
  	}
  	public Integer getReference() {
  		return this.reference;
  	}
  	
  	public void setReference(Integer reference) {
  		this.reference = reference;
  	}
  	public float getNoveltyDays() {
  		return this.noveltyDays;
  	}
  	
  	public void setNoveltyDays(float noveltyDays) {
  		this.noveltyDays = noveltyDays;
  	}
  	public Integer[] getChildren() {
  		return this.children;
  	}
  	
  	public void setChildren(Integer[] children) {
  		this.children = children;
  	}
}			
		