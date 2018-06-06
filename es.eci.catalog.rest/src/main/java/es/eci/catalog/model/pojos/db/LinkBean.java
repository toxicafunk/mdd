
package es.eci.catalog.model.pojos.db;


import java.io.Serializable;

public class LinkBean implements Serializable {

	private Integer _id;	
	private String idExt;
	private String hierarchyId;
	private String categoryId;
	
	public LinkBean() {}
	
	public LinkBean(
		Integer _id,
	String idExt
	,	String hierarchyId
	,	String categoryId
	) {
		this._id = _id;
		this.idExt = idExt;
		this.hierarchyId = hierarchyId;
		this.categoryId = categoryId;
	}
	
	
	public Integer getId() {
        return this._id;
    }

    public void setId(Integer _id) {
        this._id = _id;
    }
    	
  	public String getIdExt() {
  		return this.idExt;
  	}
  	
  	public void setIdExt(String idExt) {
  		this.idExt = idExt;
  	}
  	public String getHierarchyId() {
  		return this.hierarchyId;
  	}
  	
  	public void setHierarchyId(String hierarchyId) {
  		this.hierarchyId = hierarchyId;
  	}
  	public String getCategoryId() {
  		return this.categoryId;
  	}
  	
  	public void setCategoryId(String categoryId) {
  		this.categoryId = categoryId;
  	}
}			
		