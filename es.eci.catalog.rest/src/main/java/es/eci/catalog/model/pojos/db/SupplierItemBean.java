
package es.eci.catalog.model.pojos.db;


import java.io.Serializable;

public class SupplierItemBean implements Serializable {

	private Integer _id;	
	private Integer supplier;
	private Integer item;
	private float price;
	private float stock;
	private String activeFrom;
	private String activeTo;
	
	public SupplierItemBean() {}
	
	public SupplierItemBean(
		Integer _id,
	Integer supplier
	,	Integer item
	,	float price
	,	float stock
	,	String activeFrom
	,	String activeTo
	) {
		this._id = _id;
		this.supplier = supplier;
		this.item = item;
		this.price = price;
		this.stock = stock;
		this.activeFrom = activeFrom;
		this.activeTo = activeTo;
	}
	
	
	public Integer getId() {
        return this._id;
    }

    public void setId(Integer _id) {
        this._id = _id;
    }
    	
  	public Integer getSupplier() {
  		return this.supplier;
  	}
  	
  	public void setSupplier(Integer supplier) {
  		this.supplier = supplier;
  	}
  	public Integer getItem() {
  		return this.item;
  	}
  	
  	public void setItem(Integer item) {
  		this.item = item;
  	}
  	public float getPrice() {
  		return this.price;
  	}
  	
  	public void setPrice(float price) {
  		this.price = price;
  	}
  	public float getStock() {
  		return this.stock;
  	}
  	
  	public void setStock(float stock) {
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
		