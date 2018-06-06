
package es.eci.catalog.model.pojos;


import java.util.List;
import javax.persistence.Column;

public class TeachingCenter implements EntityBean {

	private static final long serialVersionUID = 14937874444484L;

	@Column(name = "_ID_")
	private Integer id;
	@Column(name = "_TYPES_")
	private String[] types;
	@Column(name = "NAME_")
	private String name;
	@Column(name = "GENERIC_NAME_")
	private String genericName;
	@Column(name = "EMAIL_")
	private String email;
	@Column(name = "PHONE_")
	private String phone;
	@Column(name = "IMAGE_")
	private String image;
	@Column(name = "CODE_")
	private String code;
	@Column(name = "TYPE_")
	private String type;
	@Column(name = "NATURE_")
	private String nature;
	@Column(name = "ADDRESS_")
	private String address;
	@Column(name = "FORMATTED_ADDRESS_")
	private String formattedAddress;
	@Column(name = "POSTAL_CODE_")
	private String postalCode;
	@Column(name = "TOWN_")
	private String town;
	@Column(name = "PROVINCE_")
	private String province;
	@Column(name = "AUTONOMOUS_REGION_")
	private String autonomousRegion;
	@Column(name = "LATITUDE_")
	private double latitude;
	@Column(name = "LONGITUDE_")
	private double longitude;
	@Column(name = "COURSES_")
	private Integer[] courses;
	private List<Course> coursesList;
	
	
	public TeachingCenter() {}
	
	public TeachingCenter(
		Integer id,
	String name
	,			String genericName
	,			String email
	,			String phone
	,			String image
	,			String code
	,			String type
	,			String nature
	,			String address
	,			String formattedAddress
	,			String postalCode
	,			String town
	,			String province
	,			String autonomousRegion
	,			double latitude
	,			double longitude
	,						Integer[] courses 
	,			List<Course> coursesList
	) {
		this.id = id;
		this.name = name;
		this.genericName = genericName;
		this.email = email;
		this.phone = phone;
		this.image = image;
		this.code = code;
		this.type = type;
		this.nature = nature;
		this.address = address;
		this.formattedAddress = formattedAddress;
		this.postalCode = postalCode;
		this.town = town;
		this.province = province;
		this.autonomousRegion = autonomousRegion;
		this.latitude = latitude;
		this.longitude = longitude;
		this.courses = courses;
		this.coursesList = coursesList;
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
  	public String getGenericName() {
  		return this.genericName;
  	}
  	
  	public void setGenericName(String genericName) {
  		this.genericName = genericName;
  	}
  	public String getEmail() {
  		return this.email;
  	}
  	
  	public void setEmail(String email) {
  		this.email = email;
  	}
  	public String getPhone() {
  		return this.phone;
  	}
  	
  	public void setPhone(String phone) {
  		this.phone = phone;
  	}
  	public String getImage() {
  		return this.image;
  	}
  	
  	public void setImage(String image) {
  		this.image = image;
  	}
  	public String getCode() {
  		return this.code;
  	}
  	
  	public void setCode(String code) {
  		this.code = code;
  	}
  	public String getType() {
  		return this.type;
  	}
  	
  	public void setType(String type) {
  		this.type = type;
  	}
  	public String getNature() {
  		return this.nature;
  	}
  	
  	public void setNature(String nature) {
  		this.nature = nature;
  	}
  	public String getAddress() {
  		return this.address;
  	}
  	
  	public void setAddress(String address) {
  		this.address = address;
  	}
  	public String getFormattedAddress() {
  		return this.formattedAddress;
  	}
  	
  	public void setFormattedAddress(String formattedAddress) {
  		this.formattedAddress = formattedAddress;
  	}
  	public String getPostalCode() {
  		return this.postalCode;
  	}
  	
  	public void setPostalCode(String postalCode) {
  		this.postalCode = postalCode;
  	}
  	public String getTown() {
  		return this.town;
  	}
  	
  	public void setTown(String town) {
  		this.town = town;
  	}
  	public String getProvince() {
  		return this.province;
  	}
  	
  	public void setProvince(String province) {
  		this.province = province;
  	}
  	public String getAutonomousRegion() {
  		return this.autonomousRegion;
  	}
  	
  	public void setAutonomousRegion(String autonomousRegion) {
  		this.autonomousRegion = autonomousRegion;
  	}
  	public double getLatitude() {
  		return this.latitude;
  	}
  	
  	public void setLatitude(double latitude) {
  		this.latitude = latitude;
  	}
  	public double getLongitude() {
  		return this.longitude;
  	}
  	
  	public void setLongitude(double longitude) {
  		this.longitude = longitude;
  	}
	public Integer[] getCourses() {
  		return this.courses;
  	}
  	
  	public void setCourses(Integer[] courses) {
  		this.courses = courses;
  	}
  	
  	public List<Course> getCoursesList() {
  		return this.coursesList;
  	}
  	
  	public void setCoursesList(List<Course> coursesList) {
  		this.coursesList = coursesList;
  	}
	
	@Override
	public String toString() {
		return "TeachingCenter ["
			+ "id = " + id
			+ ", name = " + name
			+ ", genericName = " + genericName
			+ ", email = " + email
			+ ", phone = " + phone
			+ ", image = " + image
			+ ", code = " + code
			+ ", type = " + type
			+ ", nature = " + nature
			+ ", address = " + address
			+ ", formattedAddress = " + formattedAddress
			+ ", postalCode = " + postalCode
			+ ", town = " + town
			+ ", province = " + province
			+ ", autonomousRegion = " + autonomousRegion
			+ ", latitude = " + latitude
			+ ", longitude = " + longitude
			+ ", courses = " + courses
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
		TeachingCenter other = (TeachingCenter) obj;
		if (id == null) {
			if (other.getId() != null)
				return false;
		} else if (!id.equals(other.getId()))
			return false;
		return true;
	}
}			
		