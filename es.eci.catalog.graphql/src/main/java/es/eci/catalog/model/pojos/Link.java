
package es.eci.catalog.model.pojos;


import java.io.Serializable;
import java.util.List;

public class Link implements Serializable {

	private static final long serialVersionUID = 15282911185761L;

	private Integer id;	
	private String idExt;
	private String hierarchyId;
	private String categoryId;
	
	public Link() {}
	
	public Link(
		Integer id,
	String idExt
	,	String hierarchyId
	,	String categoryId
	) {
		this.id = id;
		this.idExt = idExt;
		this.hierarchyId = hierarchyId;
		this.categoryId = categoryId;
	}
	
	
	public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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
		