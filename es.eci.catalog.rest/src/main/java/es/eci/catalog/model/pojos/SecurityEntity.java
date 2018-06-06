
package es.eci.catalog.model.pojos;


import java.util.List;
import javax.persistence.Column;

public class SecurityEntity implements EntityBean {

	private static final long serialVersionUID = 14937874444887L;

	@Column(name = "_ID_")
	private Integer id;
	@Column(name = "_TYPES_")
	private String[] types;
	@Column(name = "NAME_")
	private String name;
	@Column(name = "ATTRIBUTES_")
	private Integer[] attributes;
	private List<SecurityAttribute> attributesList;
	
	
	public SecurityEntity() {}
	
	public SecurityEntity(
		Integer id,
	String name
	,						Integer[] attributes 
	,			List<SecurityAttribute> attributesList
	) {
		this.id = id;
		this.name = name;
		this.attributes = attributes;
		this.attributesList = attributesList;
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
	
    	
  	public String getName() {
  		return this.name;
  	}
  	
  	public void setName(String name) {
  		this.name = name;
  	}
	public Integer[] getAttributes() {
  		return this.attributes;
  	}
  	
  	public void setAttributes(Integer[] attributes) {
  		this.attributes = attributes;
  	}
  	
  	public List<SecurityAttribute> getAttributesList() {
  		return this.attributesList;
  	}
  	
  	public void setAttributesList(List<SecurityAttribute> attributesList) {
  		this.attributesList = attributesList;
  	}
	
	@Override
	public String toString() {
		return "TeachingCenter ["
			+ "id = " + id
			+ ", name = " + name
			+ ", attributes = " + attributes
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
		SecurityEntity other = (SecurityEntity) obj;
		if (id == null) {
			if (other.getId() != null)
				return false;
		} else if (!id.equals(other.getId()))
			return false;
		return true;
	}
}			
		