
package es.eci.catalog.model.pojos;


import java.util.List;
import javax.persistence.Column;

public class SchedulingProcess implements EntityBean {

	private static final long serialVersionUID = 14937874444655L;

	@Column(name = "_ID_")
	private Integer id;
	@Column(name = "_TYPES_")
	private String[] types;
	@Column(name = "NAME_")
	private String name;
	@Column(name = "CRON_EXPRESSION_")
	private String cronExpression;
	@Column(name = "SCRIPT_")
	private String script;
	
	
	public SchedulingProcess() {}
	
	public SchedulingProcess(
		Integer id,
	String name
	,			String cronExpression
	,			String script
	) {
		this.id = id;
		this.name = name;
		this.cronExpression = cronExpression;
		this.script = script;
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
  	public String getCronExpression() {
  		return this.cronExpression;
  	}
  	
  	public void setCronExpression(String cronExpression) {
  		this.cronExpression = cronExpression;
  	}
  	public String getScript() {
  		return this.script;
  	}
  	
  	public void setScript(String script) {
  		this.script = script;
  	}
	
	@Override
	public String toString() {
		return "TeachingCenter ["
			+ "id = " + id
			+ ", name = " + name
			+ ", cronExpression = " + cronExpression
			+ ", script = " + script
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
		SchedulingProcess other = (SchedulingProcess) obj;
		if (id == null) {
			if (other.getId() != null)
				return false;
		} else if (!id.equals(other.getId()))
			return false;
		return true;
	}
}			
		