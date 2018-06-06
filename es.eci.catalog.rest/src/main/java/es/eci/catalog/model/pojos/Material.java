
package es.eci.catalog.model.pojos;


import java.util.List;
import javax.persistence.Column;

public class Material implements EntityBean {

	private static final long serialVersionUID = 14937874443942L;

	@Column(name = "_ID_")
	private Integer id;
	@Column(name = "_TYPES_")
	private String[] types;
	@Column(name = "QUANTITY_")
	private double quantity;
	@Column(name = "ITEM_")
	private Integer item;
	private Item itemObj;
	
	@Column(name = "_course_id_")
	private Integer courseId;
	
	public Material() {}
	
	public Material(
		Integer id,
				Integer courseId,
	double quantity
	,						Integer item
	,			Item itemObj
	) {
		this.id = id;
		this.courseId = courseId;
		this.quantity = quantity;
		this.item = item;
		this.itemObj = itemObj;
	}
	
	
	public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}
	
	public Integer getCourseId() {
		return courseId;
	}
	
	public void setCourseId(Integer courseId) {
		this.courseId =  courseId;
	}
    	
  	public double getQuantity() {
  		return this.quantity;
  	}
  	
  	public void setQuantity(double quantity) {
  		this.quantity = quantity;
  	}
  	public Integer getItem() {
  		return this.item;
  	}
  	
  	public void setItem(Integer item) {
  		this.item = item;
  	}
  	
  	public Item getItemObj() {
  		return this.itemObj;
  	}
  	
  	public void setItemObj(Item itemObj) {
  		this.itemObj = itemObj;
  	}
	
	@Override
	public String toString() {
		return "TeachingCenter ["
			+ "id = " + id
			+ ", quantity = " + quantity
			+ ", item = " + item
			+ "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Material other = (Material) obj;
		if (id == null) {
			if (other.getId() != null)
				return false;
		} else if (!id.equals(other.getId()))
			return false;
		return true;
	}
}			
		