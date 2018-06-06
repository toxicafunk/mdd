
package es.eci.catalog.model.pojos.db;


import java.io.Serializable;

public class EanBean implements Serializable {

	private Integer _id;	
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
	
	public EanBean() {}
	
	public EanBean(
		Integer _id,
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
		this._id = _id;
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
		