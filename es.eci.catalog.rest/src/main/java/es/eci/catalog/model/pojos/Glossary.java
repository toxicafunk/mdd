
package es.eci.catalog.model.pojos;


import java.io.Serializable;
import java.util.List;

public class Glossary implements Serializable {

	private static final long serialVersionUID = 152829114583013L;

	private Integer id;	
	private List<GlossaryValue> values;
	private Link reference;
	
	public Glossary() {}
	
	public Glossary(
		Integer id,
	List<GlossaryValue> values
	,	Link reference
	) {
		this.id = id;
		this.values = values;
		this.reference = reference;
	}
	
	
	public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    	
  	public List<GlossaryValue> getValues() {
  		return this.values;
  	}
  	
  	public void setValues(List<GlossaryValue> values) {
  		this.values = values;
  	}
  	public Link getReference() {
  		return this.reference;
  	}
  	
  	public void setReference(Link reference) {
  		this.reference = reference;
  	}
}			
		