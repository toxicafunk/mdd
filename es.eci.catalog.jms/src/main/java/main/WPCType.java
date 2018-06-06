package main;

/**
 * "001" -> Reference referenciaEspana (pimReference)
 * "011" -> PimMessage categoriaCampaign
 * "012" -> PimMessage categoriaFichaProducto (pimProductType)
 * "013" -> PimMessage categoriaVenta (pimSaleshirarchy) -> notifiedChange -> valueListCheckerRouter
 * "051" -> PimMessageWithSpecificAtt autores (PimMessageWithSpecificAtt) -> notifiedChange -> valueListCheckerRouter
 * "052" -> PimMessageWithSpecificAndLink actores (PimMessageWithSpecificAndLink) -> notifiedChange -> valueListCheckerRouter
 * "054" -> PimMessageWithSpecificAndLink glosario (pimGlossary)
 * "055" -> PimMessageWithSpecificAndLink interOut (pimPerformer) -> notifiedChange -> valueListCheckerRouterPimMessageWithSpecificAndLink
 * "056" -> PimMessageWithSpecificAtt marcas (PimMessageWithSpecificAtt) -> notifiedChange -> valueListCheckerRouter
 * "090" -> PimMessage features (pimFeatureWorker) -> notifiedChange -> valueListCheckerRouter
 *
 * @author erodriguez
 *
 */
public enum WPCType {
	REFERENCESESP("001"),
	CAMPAIGNS("011"),
	PRODUCTTYPES("012"),
	SALESHIERARCHY("013"),
	ACTORS("051"),
	AUTHORS("52"),
	GLOSSARY("054"),
	PERFORMERS("055"),
	BRANDS("056"),
	FEATURES("090"),
	UNDEFINED("-1");
	
	private String code;

	private WPCType(String code) {
            this.code = code;
    }

	public String getCode() {
		return this.code;
	}
}
