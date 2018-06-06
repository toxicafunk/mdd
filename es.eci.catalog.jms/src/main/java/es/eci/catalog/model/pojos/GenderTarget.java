
package es.eci.catalog.model.pojos;


import java.io.Serializable;
import java.util.List;

public class GenderTarget implements Serializable {

	private static final long serialVersionUID = 15282911267519L;

	private Integer id;	
	private String code;
	private String label;
	
	public GenderTarget() {}
	
	public GenderTarget(
		Integer id,
	String code
	,	String label
	) {
		this.id = id;
		this.code = code;
		this.label = label;
	}
	
	
	public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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
		