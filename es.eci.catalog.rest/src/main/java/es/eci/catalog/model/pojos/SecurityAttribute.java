
package es.eci.catalog.model.pojos;


import java.util.List;
import javax.persistence.Column;

public class SecurityAttribute implements EntityBean {

	private static final long serialVersionUID = 14937874444726L;

	@Column(name = "_ID_")
	private Integer id;
	@Column(name = "_TYPES_")
	private String[] types;
	@Column(name = "NAME_")
	private String name;
	
	@Column(name = "_security_entity_id_")
	private Integer securityEntityId;
	
	public SecurityAttribute() {}
	
	public SecurityAttribute(
		Integer id,
				Integer securityEntityId,
	String name
	) {
		this.id = id;
		this.securityEntityId = securityEntityId;
		this.name = name;
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
	
	public Integer getSecurityEntityId() {
		return securityEntityId;
	}
	
	public void setSecurityEntityId(Integer securityEntityId) {
		this.securityEntityId =  securityEntityId;
	}
    	
  	public String getName() {
  		return this.name;
  	}
  	
  	public void setName(String name) {
  		this.name = name;
  	}
	
	@Override
	public String toString() {
		return "TeachingCenter ["
			+ "id = " + id
			+ ", name = " + name
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
		SecurityAttribute other = (SecurityAttribute) obj;
		if (id == null) {
			if (other.getId() != null)
				return false;
		} else if (!id.equals(other.getId()))
			return false;
		return true;
	}
}			
		