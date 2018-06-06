
package es.eci.catalog.model.pojos;


import java.util.List;
import javax.persistence.Column;

public class SecurityUser implements EntityBean {

	private static final long serialVersionUID = 149378744452812L;

	@Column(name = "_ID_")
	private Integer id;
	@Column(name = "_TYPES_")
	private String[] types;
	@Column(name = "USERNAME_")
	private String username;
	@Column(name = "PASSWORD_")
	private String password;
	@Column(name = "ROLES_")
	private Integer[] roles;
	private List<SecurityRole> rolesList;
	@Column(name = "EMAIL_")
	private String email;
	@Column(name = "BIRTH_DATE_")
	private String birthDate;
	@Column(name = "PHOTO_")
	private String photo;
	@Column(name = "IDENTITY_CARDS_")
	private String[] identityCards;
	@Column(name = "PRESENTATION_VIDEO_")
	private String presentationVideo;
	@Column(name = "TEACHING_CENTERS_")
	private Integer[] teachingCenters;
	private List<TeachingCenter> teachingCentersList;
	
	
	public SecurityUser() {}
	
	public SecurityUser(
		Integer id,
	String username
	,			String password
	,						Integer[] roles 
	,			List<SecurityRole> rolesList
	,			String email
	,			String birthDate
	,			String photo
	,			String[] identityCards
	,			String presentationVideo
	,						Integer[] teachingCenters 
	,			List<TeachingCenter> teachingCentersList
	) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.rolesList = rolesList;
		this.email = email;
		this.birthDate = birthDate;
		this.photo = photo;
		this.identityCards = identityCards;
		this.presentationVideo = presentationVideo;
		this.teachingCenters = teachingCenters;
		this.teachingCentersList = teachingCentersList;
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
	
    	
  	public String getUsername() {
  		return this.username;
  	}
  	
  	public void setUsername(String username) {
  		this.username = username;
  	}
  	public String getPassword() {
  		return this.password;
  	}
  	
  	public void setPassword(String password) {
  		this.password = password;
  	}
	public Integer[] getRoles() {
  		return this.roles;
  	}
  	
  	public void setRoles(Integer[] roles) {
  		this.roles = roles;
  	}
  	
  	public List<SecurityRole> getRolesList() {
  		return this.rolesList;
  	}
  	
  	public void setRolesList(List<SecurityRole> rolesList) {
  		this.rolesList = rolesList;
  	}
  	public String getEmail() {
  		return this.email;
  	}
  	
  	public void setEmail(String email) {
  		this.email = email;
  	}
  	public String getBirthDate() {
  		return this.birthDate;
  	}
  	
  	public void setBirthDate(String birthDate) {
  		this.birthDate = birthDate;
  	}
  	public String getPhoto() {
  		return this.photo;
  	}
  	
  	public void setPhoto(String photo) {
  		this.photo = photo;
  	}
  	public String[] getIdentityCards() {
  		return this.identityCards;
  	}
  	
  	public void setIdentityCards(String[] identityCards) {
  		this.identityCards = identityCards;
  	}
  	public String getPresentationVideo() {
  		return this.presentationVideo;
  	}
  	
  	public void setPresentationVideo(String presentationVideo) {
  		this.presentationVideo = presentationVideo;
  	}
	public Integer[] getTeachingCenters() {
  		return this.teachingCenters;
  	}
  	
  	public void setTeachingCenters(Integer[] teachingCenters) {
  		this.teachingCenters = teachingCenters;
  	}
  	
  	public List<TeachingCenter> getTeachingCentersList() {
  		return this.teachingCentersList;
  	}
  	
  	public void setTeachingCentersList(List<TeachingCenter> teachingCentersList) {
  		this.teachingCentersList = teachingCentersList;
  	}
	
	@Override
	public String toString() {
		return "TeachingCenter ["
			+ "id = " + id
			+ ", username = " + username
			+ ", password = " + password
			+ ", roles = " + roles
			+ ", email = " + email
			+ ", birthDate = " + birthDate
			+ ", photo = " + photo
			+ ", identityCards = " + identityCards
			+ ", presentationVideo = " + presentationVideo
			+ ", teachingCenters = " + teachingCenters
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
		SecurityUser other = (SecurityUser) obj;
		if (id == null) {
			if (other.getId() != null)
				return false;
		} else if (!id.equals(other.getId()))
			return false;
		return true;
	}
}			
		