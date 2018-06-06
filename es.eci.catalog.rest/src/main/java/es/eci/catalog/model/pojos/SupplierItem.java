
package es.eci.catalog.model.pojos;


import java.io.Serializable;
import java.util.List;

public class SupplierItem implements Serializable {

	private static final long serialVersionUID = 152829114582612L;

	private Integer id;	
	private Supplier supplier;
	private Item item;
	private Float price;
	private Float stock;
	private String activeFrom;
	private String activeTo;
	
	public SupplierItem() {}
	
	public SupplierItem(
		Integer id,
	Supplier supplier
	,	Item item
	,	Float price
	,	Float stock
	,	String activeFrom
	,	String activeTo
	) {
		this.id = id;
		this.supplier = supplier;
		this.item = item;
		this.price = price;
		this.stock = stock;
		this.activeFrom = activeFrom;
		this.activeTo = activeTo;
	}
	
	
	public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    	
  	public Supplier getSupplier() {
  		return this.supplier;
  	}
  	
  	public void setSupplier(Supplier supplier) {
  		this.supplier = supplier;
  	}
  	public Item getItem() {
  		return this.item;
  	}
  	
  	public void setItem(Item item) {
  		this.item = item;
  	}
  	public Float getPrice() {
  		return this.price;
  	}
  	
  	public void setPrice(Float price) {
  		this.price = price;
  	}
  	public Float getStock() {
  		return this.stock;
  	}
  	
  	public void setStock(Float stock) {
  		this.stock = stock;
  	}
  	public String getActiveFrom() {
  		return this.activeFrom;
  	}
  	
  	public void setActiveFrom(String activeFrom) {
  		this.activeFrom = activeFrom;
  	}
  	public String getActiveTo() {
  		return this.activeTo;
  	}
  	
  	public void setActiveTo(String activeTo) {
  		this.activeTo = activeTo;
  	}
}			
		