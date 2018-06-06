
package es.eci.catalog.model.pojos.db;


import java.io.Serializable;

public class GenderTargetBean implements Serializable {

	private Integer _id;	
	private String code;
	private String label;
	
	public GenderTargetBean() {}
	
	public GenderTargetBean(
		Integer _id,
	String code
	,	String label
	) {
		this._id = _id;
		this.code = code;
		this.label = label;
	}
	
	
	public Integer getId() {
        return this._id;
    }

    public void setId(Integer _id) {
        this._id = _id;
    }
    	
  	public String getCode() {
  		return this.code;
  	}
  	
  	public void setCode(String code) {
  		this.code = code;
  	}
  	public String getLabel() {
  		return this.label;
  	}
  	
  	public void setLabel(String label) {
  		this.label = label;
  	}
}			
		