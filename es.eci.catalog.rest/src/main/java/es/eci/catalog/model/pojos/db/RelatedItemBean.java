
package es.eci.catalog.model.pojos.db;


import java.io.Serializable;

public class RelatedItemBean implements Serializable {

	private Integer _id;	
	private Integer[] items;
	
	public RelatedItemBean() {}
	
	public RelatedItemBean(
		Integer _id,
	Integer[] items
	) {
		this._id = _id;
		this.items = items;
	}
	
	
	public Integer getId() {
        return this._id;
    }

    public void setId(Integer _id) {
        this._id = _id;
    }
    	
  	public Integer[] getItems() {
  		return this.items;
  	}
  	
  	public void setItems(Integer[] items) {
  		this.items = items;
  	}
}			
		