
package es.eci.catalog.model.pojos;


import java.util.List;
import javax.persistence.Column;

public class SecurityRole implements EntityBean {

	private static final long serialVersionUID = 149378744452211L;

	@Column(name = "_ID_")
	private Integer id;
	@Column(name = "_TYPES_")
	private String[] types;
	@Column(name = "NAME_")
	private String name;
	@Column(name = "PRIVILEGES_")
	private Integer[] privileges;
	private List<SecurityAccessPrivilege> privilegesList;
	@Column(name = "POLICIES_")
	private Integer[] policies;
	private List<SecurityRowLevelPolicy> policiesList;
	@Column(name = "COMMENTS_")
	private String comments;
	
	
	public SecurityRole() {}
	
	public SecurityRole(
		Integer id,
	String name
	,						Integer[] privileges 
	,			List<SecurityAccessPrivilege> privilegesList
	,						Integer[] policies 
	,			List<SecurityRowLevelPolicy> policiesList
	,			String comments
	) {
		this.id = id;
		this.name = name;
		this.privileges = privileges;
		this.privilegesList = privilegesList;
		this.policies = policies;
		this.policiesList = policiesList;
		this.comments = comments;
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
	public Integer[] getPrivileges() {
  		return this.privileges;
  	}
  	
  	public void setPrivileges(Integer[] privileges) {
  		this.privileges = privileges;
  	}
  	
  	public List<SecurityAccessPrivilege> getPrivilegesList() {
  		return this.privilegesList;
  	}
  	
  	public void setPrivilegesList(List<SecurityAccessPrivilege> privilegesList) {
  		this.privilegesList = privilegesList;
  	}
	public Integer[] getPolicies() {
  		return this.policies;
  	}
  	
  	public void setPolicies(Integer[] policies) {
  		this.policies = policies;
  	}
  	
  	public List<SecurityRowLevelPolicy> getPoliciesList() {
  		return this.policiesList;
  	}
  	
  	public void setPoliciesList(List<SecurityRowLevelPolicy> policiesList) {
  		this.policiesList = policiesList;
  	}
  	public String getComments() {
  		return this.comments;
  	}
  	
  	public void setComments(String comments) {
  		this.comments = comments;
  	}
	
	@Override
	public String toString() {
		return "TeachingCenter ["
			+ "id = " + id
			+ ", name = " + name
			+ ", privileges = " + privileges
			+ ", policies = " + policies
			+ ", comments = " + comments
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
		SecurityRole other = (SecurityRole) obj;
		if (id == null) {
			if (other.getId() != null)
				return false;
		} else if (!id.equals(other.getId()))
			return false;
		return true;
	}
}			
		