
package es.eci.catalog.model.pojos;


import java.util.List;
import javax.persistence.Column;

public class SecurityRowLevelPolicy implements EntityBean {

	private static final long serialVersionUID = 149378744450910L;

	@Column(name = "_ID_")
	private Integer id;
	@Column(name = "_TYPES_")
	private String[] types;
	@Column(name = "NAME_")
	private String name;
	@Column(name = "ENTITY_")
	private Integer entity;
	private SecurityEntity entityObj;
	@Column(name = "OPERATIONS_")
	private Integer[] operations;
	private List<SecurityOperationType> operationsList;
	@Column(name = "USING_EXPRESSION_")
	private String usingExpression;
	@Column(name = "CHECK_EXPRESSION_")
	private String checkExpression;
	
	@Column(name = "_security_role_id_")
	private Integer securityRoleId;
	
	public SecurityRowLevelPolicy() {}
	
	public SecurityRowLevelPolicy(
		Integer id,
				Integer securityRoleId,
	String name
	,						Integer entity
	,			SecurityEntity entityObj
	,						Integer[] operations 
	,			List<SecurityOperationType> operationsList
	,			String usingExpression
	,			String checkExpression
	) {
		this.id = id;
		this.securityRoleId = securityRoleId;
		this.name = name;
		this.entity = entity;
		this.entityObj = entityObj;
		this.operations = operations;
		this.operationsList = operationsList;
		this.usingExpression = usingExpression;
		this.checkExpression = checkExpression;
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
  	public Integer getEntity() {
  		return this.entity;
  	}
  	
  	public void setEntity(Integer entity) {
  		this.entity = entity;
  	}
  	
  	public SecurityEntity getEntityObj() {
  		return this.entityObj;
  	}
  	
  	public void setEntityObj(SecurityEntity entityObj) {
  		this.entityObj = entityObj;
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
  	public String getUsingExpression() {
  		return this.usingExpression;
  	}
  	
  	public void setUsingExpression(String usingExpression) {
  		this.usingExpression = usingExpression;
  	}
  	public String getCheckExpression() {
  		return this.checkExpression;
  	}
  	
  	public void setCheckExpression(String checkExpression) {
  		this.checkExpression = checkExpression;
  	}
	
	@Override
	public String toString() {
		return "TeachingCenter ["
			+ "id = " + id
			+ ", name = " + name
			+ ", entity = " + entity
			+ ", operations = " + operations
			+ ", usingExpression = " + usingExpression
			+ ", checkExpression = " + checkExpression
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
		SecurityRowLevelPolicy other = (SecurityRowLevelPolicy) obj;
		if (id == null) {
			if (other.getId() != null)
				return false;
		} else if (!id.equals(other.getId()))
			return false;
		return true;
	}
}			
		