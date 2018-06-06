
package es.eci.catalog.model.pojos;


import java.util.List;
import javax.persistence.Column;

public class SecurityAccessPrivilege implements EntityBean {

	private static final long serialVersionUID = 14937874444959L;

	@Column(name = "_ID_")
	private Integer id;
	@Column(name = "_TYPES_")
	private String[] types;
	@Column(name = "NAME_")
	private String name;
	@Column(name = "OPERATIONS_")
	private Integer[] operations;
	private List<SecurityOperationType> operationsList;
	@Column(name = "ENTITIES_")
	private Integer[] entities;
	private List<SecurityEntity> entitiesList;
	@Column(name = "ATTRIBUTES_")
	private Integer[] attributes;
	private List<SecurityAttribute> attributesList;
	
	@Column(name = "_security_role_id_")
	private Integer securityRoleId;
	
	public SecurityAccessPrivilege() {}
	
	public SecurityAccessPrivilege(
		Integer id,
				Integer securityRoleId,
	String name
	,						Integer[] operations 
	,			List<SecurityOperationType> operationsList
	,						Integer[] entities 
	,			List<SecurityEntity> entitiesList
	,						Integer[] attributes 
	,			List<SecurityAttribute> attributesList
	) {
		this.id = id;
		this.securityRoleId = securityRoleId;
		this.name = name;
		this.operations = operations;
		this.operationsList = operationsList;
		this.entities = entities;
		this.entitiesList = entitiesList;
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
	
	public Integer getSecurityRoleId() {
		return securityRoleId;
	}
	
	public void setSecurityRoleId(Integer securityRoleId) {
		this.securityRoleId =  securityRoleId;
	}
    	
  	public String getName() {
  		return this.name;
  	}
  	
  	public void setName(String name) {
  		this.name = name;
  	}
	public Integer[] getOperations() {
  		return this.operations;
  	}
  	
  	public void setOperations(Integer[] operations) {
  		this.operations = operations;
  	}
  	
  	public List<SecurityOperationType> getOperationsList() {
  		return this.operationsList;
  	}
  	
  	public void setOperationsList(List<SecurityOperationType> operationsList) {
  		this.operationsList = operationsList;
  	}
	public Integer[] getEntities() {
  		return this.entities;
  	}
  	
  	public void setEntities(Integer[] entities) {
  		this.entities = entities;
  	}
  	
  	public List<SecurityEntity> getEntitiesList() {
  		return this.entitiesList;
  	}
  	
  	public void setEntitiesList(List<SecurityEntity> entitiesList) {
  		this.entitiesList = entitiesList;
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
			+ ", operations = " + operations
			+ ", entities = " + entities
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
		SecurityAccessPrivilege other = (SecurityAccessPrivilege) obj;
		if (id == null) {
			if (other.getId() != null)
				return false;
		} else if (!id.equals(other.getId()))
			return false;
		return true;
	}
}			
		