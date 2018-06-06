
package es.eci.catalog.model.pojos;


import java.io.Serializable;
import java.util.List;

public class Ean implements Serializable {

	private static final long serialVersionUID = 152829114583815L;

	private Integer id;	
	private String code;
	private String eciRef;
	private String[] eciRefList;
	private String webVariantCode;
	private String internalProviderCode;
	private String referenceType;
	private String productTypeId;
	private String[] parentCategories;
	private String lastDate;
	private String unpublishDate;
	
	public Ean() {}
	
	public Ean(
		Integer id,
	String code
	,	String eciRef
	,	String[] eciRefList
	,	String webVariantCode
	,	String internalProviderCode
	,	String referenceType
	,	String productTypeId
	,	String[] parentCategories
	,	String lastDate
	,	String unpublishDate
	) {
		this.id = id;
		this.code = code;
		this.eciRef = eciRef;
		this.eciRefList = eciRefList;
		this.webVariantCode = webVariantCode;
		this.internalProviderCode = internalProviderCode;
		this.referenceType = referenceType;
		this.productTypeId = productTypeId;
		this.parentCategories = parentCategories;
		this.lastDate = lastDate;
		this.unpublishDate = unpublishDate;
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
  	public String getEciRef() {
  		return this.eciRef;
  	}
  	
  	public void setEciRef(String eciRef) {
  		this.eciRef = eciRef;
  	}
  	public String[] getEciRefList() {
  		return this.eciRefList;
  	}
  	
  	public void setEciRefList(String[] eciRefList) {
  		this.eciRefList = eciRefList;
  	}
  	public String getWebVariantCode() {
  		return this.webVariantCode;
  	}
  	
  	public void setWebVariantCode(String webVariantCode) {
  		this.webVariantCode = webVariantCode;
  	}
  	public String getInternalProviderCode() {
  		return this.internalProviderCode;
  	}
  	
  	public void setInternalProviderCode(String internalProviderCode) {
  		this.internalProviderCode = internalProviderCode;
  	}
  	public String getReferenceType() {
  		return this.referenceType;
  	}
  	
  	public void setReferenceType(String referenceType) {
  		this.referenceType = referenceType;
  	}
  	public String getProductTypeId() {
  		return this.productTypeId;
  	}
  	
  	public void setProductTypeId(String productTypeId) {
  		this.productTypeId = productTypeId;
  	}
  	public String[] getParentCategories() {
  		return this.parentCategories;
  	}
  	
  	public void setParentCategories(String[] parentCategories) {
  		this.parentCategories = parentCategories;
  	}
  	public String getLastDate() {
  		return this.lastDate;
  	}
  	
  	public void setLastDate(String lastDate) {
  		this.lastDate = lastDate;
  	}
  	public String getUnpublishDate() {
  		return this.unpublishDate;
  	}
  	
  	public void setUnpublishDate(String unpublishDate) {
  		this.unpublishDate = unpublishDate;
  	}
}			
		