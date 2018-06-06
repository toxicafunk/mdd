
package es.eci.catalog.model.pojos;


import java.util.List;
import javax.persistence.Column;

public class ItemImage implements EntityBean {

	private static final long serialVersionUID = 14937874443550L;

	@Column(name = "_ID_")
	private Integer id;
	@Column(name = "_TYPES_")
	private String[] types;
	@Column(name = "ALT_TEXT_")
	private String altText;
	@Column(name = "IMAGE_")
	private String image;
	
	@Column(name = "_item_id_")
	private Integer itemId;
	
	public ItemImage() {}
	
	public ItemImage(
		Integer id,
				Integer itemId,
	String altText
	,			String image
	) {
		this.id = id;
		this.itemId = itemId;
		this.altText = altText;
		this.image = image;
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
	
	public Integer getItemId() {
		return itemId;
	}
	
	public void setItemId(Integer itemId) {
		this.itemId =  itemId;
	}
    	
  	public String getAltText() {
  		return this.altText;
  	}
  	
  	public void setAltText(String altText) {
  		this.altText = altText;
  	}
  	public String getImage() {
  		return this.image;
  	}
  	
  	public void setImage(String image) {
  		this.image = image;
  	}
	
	@Override
	public String toString() {
		return "TeachingCenter ["
			+ "id = " + id
			+ ", altText = " + altText
			+ ", image = " + image
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
		ItemImage other = (ItemImage) obj;
		if (id == null) {
			if (other.getId() != null)
				return false;
		} else if (!id.equals(other.getId()))
			return false;
		return true;
	}
}			
		