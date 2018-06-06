
package es.eci.catalog.model.pojos.db;


import java.io.Serializable;

public class GlossaryBean implements Serializable {

	private Integer _id;	
	private Integer[] values;
	private Integer reference;
	
	public GlossaryBean() {}
	
	public GlossaryBean(
		Integer _id,
	Integer[] values
	,	Integer reference
	) {
		this._id = _id;
		this.values = values;
		this.reference = reference;
	}
	
	
	public Integer getId() {
        return this._id;
    }

    public void setId(Integer _id) {
        this._id = _id;
    }
    	
  	public Integer[] getValues() {
  		return this.values;
  	}
  	
  	public void setValues(Integer[] values) {
  		this.values = values;
  	}
  	public Integer getReference() {
  		return this.reference;
  	}
  	
  	public void setReference(Integer reference) {
  		this.reference = reference;
  	}
}			
		