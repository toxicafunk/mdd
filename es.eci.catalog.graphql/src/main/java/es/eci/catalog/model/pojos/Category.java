
package es.eci.catalog.model.pojos;


import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {

	private static final long serialVersionUID = 15282911185954L;

	private Integer id;	
	private String code;
	private String atgCode;
	private Category parentCategory;
	private String status;
	private List<Map> descriptions;
	private List<Map> fullDescriptions;
	private String startDate;
	private String endDate;
	private String invalidationDate;
	private Link reference;
	private Float noveltyDays;
	private List<Category> children;
	
	public Category() {}
	
	public Category(
		Integer id,
	String code
	,	String atgCode
	,	Category parentCategory
	,	String status
	,	List<Map> descriptions
	,	List<Map> fullDescriptions
	,	String startDate
	,	String endDate
	,	String invalidationDate
	,	Link reference
	,	Float noveltyDays
	,	List<Category> children
	) {
		this.id = id;
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
  	public Category getParentCategory() {
  		return this.parentCategory;
  	}
  	
  	public void setParentCategory(Category parentCategory) {
  		this.parentCategory = parentCategory;
  	}
  	public String getStatus() {
  		return this.status;
  	}
  	
  	public void setStatus(String status) {
  		this.status = status;
  	}
  	public List<Map> getDescriptions() {
  		return this.descriptions;
  	}
  	
  	public void setDescriptions(List<Map> descriptions) {
  		this.descriptions = descriptions;
  	}
  	public List<Map> getFullDescriptions() {
  		return this.fullDescriptions;
  	}
  	
  	public void setFullDescriptions(List<Map> fullDescriptions) {
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
  	public Link getReference() {
  		return this.reference;
  	}
  	
  	public void setReference(Link reference) {
  		this.reference = reference;
  	}
  	public Float getNoveltyDays() {
  		return this.noveltyDays;
  	}
  	
  	public void setNoveltyDays(Float noveltyDays) {
  		this.noveltyDays = noveltyDays;
  	}
  	public List<Category> getChildren() {
  		return this.children;
  	}
  	
  	public void setChildren(List<Category> children) {
  		this.children = children;
  	}
}			
		