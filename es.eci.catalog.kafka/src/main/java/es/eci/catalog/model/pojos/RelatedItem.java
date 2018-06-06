
package es.eci.catalog.model.pojos;


import java.io.Serializable;
import java.util.List;

public class RelatedItem implements Serializable {

	private static final long serialVersionUID = 152829114056310L;

	private Integer id;	
	private List<Item> items;
	
	public RelatedItem() {}
	
	public RelatedItem(
		Integer id,
	List<Item> items
	) {
		this.id = id;
		this.items = items;
	}
	
	
	public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    	
  	public List<Item> getItems() {
  		return this.items;
  	}
  	
  	public void setItems(List<Item> items) {
  		this.items = items;
  	}
}			
		