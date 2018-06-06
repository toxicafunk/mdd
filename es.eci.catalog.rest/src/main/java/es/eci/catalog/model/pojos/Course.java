
package es.eci.catalog.model.pojos;


import java.util.List;
import javax.persistence.Column;

public class Course implements EntityBean {

	private static final long serialVersionUID = 14937874444163L;

	@Column(name = "_ID_")
	private Integer id;
	@Column(name = "_TYPES_")
	private String[] types;
	@Column(name = "NAME_")
	private String name;
	@Column(name = "CYCLE_")
	private String cycle;
	@Column(name = "MATERIALS_")
	private Integer[] materials;
	private List<Material> materialsList;
	
	@Column(name = "_teaching_center_id_")
	private Integer teachingCenterId;
	
	public Course() {}
	
	public Course(
		Integer id,
				Integer teachingCenterId,
	String name
	,			String cycle
	,						Integer[] materials 
	,			List<Material> materialsList
	) {
		this.id = id;
		this.teachingCenterId = teachingCenterId;
		this.name = name;
		this.cycle = cycle;
		this.materials = materials;
		this.materialsList = materialsList;
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
	
	public Integer getTeachingCenterId() {
		return teachingCenterId;
	}
	
	public void setTeachingCenterId(Integer teachingCenterId) {
		this.teachingCenterId =  teachingCenterId;
	}
    	
  	public String getName() {
  		return this.name;
  	}
  	
  	public void setName(String name) {
  		this.name = name;
  	}
  	public String getCycle() {
  		return this.cycle;
  	}
  	
  	public void setCycle(String cycle) {
  		this.cycle = cycle;
  	}
	public Integer[] getMaterials() {
  		return this.materials;
  	}
  	
  	public void setMaterials(Integer[] materials) {
  		this.materials = materials;
  	}
  	
  	public List<Material> getMaterialsList() {
  		return this.materialsList;
  	}
  	
  	public void setMaterialsList(List<Material> materialsList) {
  		this.materialsList = materialsList;
  	}
	
	@Override
	public String toString() {
		return "TeachingCenter ["
			+ "id = " + id
			+ ", name = " + name
			+ ", cycle = " + cycle
			+ ", materials = " + materials
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
		Course other = (Course) obj;
		if (id == null) {
			if (other.getId() != null)
				return false;
		} else if (!id.equals(other.getId()))
			return false;
		return true;
	}
}			
		