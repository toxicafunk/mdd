package main;

import services.maps.MapsCodec;
import services.maps.MapsInverseCodec;
import services.links.LinksCodec;
import services.links.LinksInverseCodec;
import services.glossaryvalues.GlossaryValuesCodec;
import services.glossaryvalues.GlossaryValuesInverseCodec;
import services.brands.BrandsCodec;
import services.brands.BrandsInverseCodec;
import services.categories.CategoriesCodec;
import services.categories.CategoriesInverseCodec;
import services.images.ImagesCodec;
import services.images.ImagesInverseCodec;
import services.videos.VideosCodec;
import services.videos.VideosInverseCodec;
import services.producttypes.ProductTypesCodec;
import services.producttypes.ProductTypesInverseCodec;
import services.items.ItemsCodec;
import services.items.ItemsInverseCodec;
import services.gendertargets.GenderTargetsCodec;
import services.gendertargets.GenderTargetsInverseCodec;
import services.relateditems.RelatedItemsCodec;
import services.relateditems.RelatedItemsInverseCodec;
import services.suppliers.SuppliersCodec;
import services.suppliers.SuppliersInverseCodec;
import services.supplieritems.SupplierItemsCodec;
import services.supplieritems.SupplierItemsInverseCodec;
import services.glossaries.GlossariesCodec;
import services.glossaries.GlossariesInverseCodec;
import services.lkups.LkupsCodec;
import services.lkups.LkupsInverseCodec;
import services.eans.EansCodec;
import services.eans.EansInverseCodec;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;


/**
 * * Run with:
 *  java -jar target/dc-integrations-1.0-SNAPSHOT-fat.jar -conf config.json
 *  
 * @author erodriguez
 *
 */
public class Main extends AbstractVerticle {
	
	final Logger logger = LoggerFactory.getLogger(Main.class);
	
	protected final String CONTENT_TYPE_LABEL = "Content-Type";
    protected final String CONTENT_TYPE_HEADER = "application/json; charset=utf-8";
	
	@Override
	public void start(Future<Void> fut) throws Exception {
	
		vertx.eventBus().registerCodec(new MapsCodec());
		vertx.eventBus().registerCodec(new MapsInverseCodec());
		vertx.eventBus().registerCodec(new LinksCodec());
		vertx.eventBus().registerCodec(new LinksInverseCodec());
		vertx.eventBus().registerCodec(new GlossaryValuesCodec());
		vertx.eventBus().registerCodec(new GlossaryValuesInverseCodec());
		vertx.eventBus().registerCodec(new BrandsCodec());
		vertx.eventBus().registerCodec(new BrandsInverseCodec());
		vertx.eventBus().registerCodec(new CategoriesCodec());
		vertx.eventBus().registerCodec(new CategoriesInverseCodec());
		vertx.eventBus().registerCodec(new ImagesCodec());
		vertx.eventBus().registerCodec(new ImagesInverseCodec());
		vertx.eventBus().registerCodec(new VideosCodec());
		vertx.eventBus().registerCodec(new VideosInverseCodec());
		vertx.eventBus().registerCodec(new ProductTypesCodec());
		vertx.eventBus().registerCodec(new ProductTypesInverseCodec());
		vertx.eventBus().registerCodec(new ItemsCodec());
		vertx.eventBus().registerCodec(new ItemsInverseCodec());
		vertx.eventBus().registerCodec(new GenderTargetsCodec());
		vertx.eventBus().registerCodec(new GenderTargetsInverseCodec());
		vertx.eventBus().registerCodec(new RelatedItemsCodec());
		vertx.eventBus().registerCodec(new RelatedItemsInverseCodec());
		vertx.eventBus().registerCodec(new SuppliersCodec());
		vertx.eventBus().registerCodec(new SuppliersInverseCodec());
		vertx.eventBus().registerCodec(new SupplierItemsCodec());
		vertx.eventBus().registerCodec(new SupplierItemsInverseCodec());
		vertx.eventBus().registerCodec(new GlossariesCodec());
		vertx.eventBus().registerCodec(new GlossariesInverseCodec());
		vertx.eventBus().registerCodec(new LkupsCodec());
		vertx.eventBus().registerCodec(new LkupsInverseCodec());
		vertx.eventBus().registerCodec(new EansCodec());
		vertx.eventBus().registerCodec(new EansInverseCodec());

		JsonObject options;
		options = setDeploymentOptions("services.maps.get");
		vertx.deployVerticle("services.maps.Get", 
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.maps.get");
				});
		
		options = setDeploymentOptions("services.maps.patch");
		vertx.deployVerticle("services.maps.Patch",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.maps.patch");
				});
		
		options = setDeploymentOptions("services.maps.post");
		vertx.deployVerticle("services.maps.Post",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.maps.post");
				});
		
		options = setDeploymentOptions("services.maps.delete");
		vertx.deployVerticle("services.maps.Delete",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.maps.delete");
				});
		
		options = setDeploymentOptions("services.links.get");
		vertx.deployVerticle("services.links.Get", 
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.links.get");
				});
		
		options = setDeploymentOptions("services.links.patch");
		vertx.deployVerticle("services.links.Patch",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.links.patch");
				});
		
		options = setDeploymentOptions("services.links.post");
		vertx.deployVerticle("services.links.Post",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.links.post");
				});
		
		options = setDeploymentOptions("services.links.delete");
		vertx.deployVerticle("services.links.Delete",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.links.delete");
				});
		
		options = setDeploymentOptions("services.glossaryvalues.get");
		vertx.deployVerticle("services.glossaryvalues.Get", 
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.glossaryvalues.get");
				});
		
		options = setDeploymentOptions("services.glossaryvalues.patch");
		vertx.deployVerticle("services.glossaryvalues.Patch",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.glossaryvalues.patch");
				});
		
		options = setDeploymentOptions("services.glossaryvalues.post");
		vertx.deployVerticle("services.glossaryvalues.Post",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.glossaryvalues.post");
				});
		
		options = setDeploymentOptions("services.glossaryvalues.delete");
		vertx.deployVerticle("services.glossaryvalues.Delete",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.glossaryvalues.delete");
				});
		
		options = setDeploymentOptions("services.brands.get");
		vertx.deployVerticle("services.brands.Get", 
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.brands.get");
				});
		
		options = setDeploymentOptions("services.brands.patch");
		vertx.deployVerticle("services.brands.Patch",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.brands.patch");
				});
		
		options = setDeploymentOptions("services.brands.post");
		vertx.deployVerticle("services.brands.Post",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.brands.post");
				});
		
		options = setDeploymentOptions("services.brands.delete");
		vertx.deployVerticle("services.brands.Delete",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.brands.delete");
				});
		
		options = setDeploymentOptions("services.categories.get");
		vertx.deployVerticle("services.categories.Get", 
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.categories.get");
				});
		
		options = setDeploymentOptions("services.categories.patch");
		vertx.deployVerticle("services.categories.Patch",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.categories.patch");
				});
		
		options = setDeploymentOptions("services.categories.post");
		vertx.deployVerticle("services.categories.Post",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.categories.post");
				});
		
		options = setDeploymentOptions("services.categories.delete");
		vertx.deployVerticle("services.categories.Delete",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.categories.delete");
				});
		
		options = setDeploymentOptions("services.images.get");
		vertx.deployVerticle("services.images.Get", 
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.images.get");
				});
		
		options = setDeploymentOptions("services.images.patch");
		vertx.deployVerticle("services.images.Patch",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.images.patch");
				});
		
		options = setDeploymentOptions("services.images.post");
		vertx.deployVerticle("services.images.Post",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.images.post");
				});
		
		options = setDeploymentOptions("services.images.delete");
		vertx.deployVerticle("services.images.Delete",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.images.delete");
				});
		
		options = setDeploymentOptions("services.videos.get");
		vertx.deployVerticle("services.videos.Get", 
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.videos.get");
				});
		
		options = setDeploymentOptions("services.videos.patch");
		vertx.deployVerticle("services.videos.Patch",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.videos.patch");
				});
		
		options = setDeploymentOptions("services.videos.post");
		vertx.deployVerticle("services.videos.Post",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.videos.post");
				});
		
		options = setDeploymentOptions("services.videos.delete");
		vertx.deployVerticle("services.videos.Delete",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.videos.delete");
				});
		
		options = setDeploymentOptions("services.producttypes.get");
		vertx.deployVerticle("services.producttypes.Get", 
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.producttypes.get");
				});
		
		options = setDeploymentOptions("services.producttypes.patch");
		vertx.deployVerticle("services.producttypes.Patch",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.producttypes.patch");
				});
		
		options = setDeploymentOptions("services.producttypes.post");
		vertx.deployVerticle("services.producttypes.Post",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.producttypes.post");
				});
		
		options = setDeploymentOptions("services.producttypes.delete");
		vertx.deployVerticle("services.producttypes.Delete",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.producttypes.delete");
				});
		
		options = setDeploymentOptions("services.items.get");
		vertx.deployVerticle("services.items.Get", 
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.items.get");
				});
		
		options = setDeploymentOptions("services.items.patch");
		vertx.deployVerticle("services.items.Patch",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.items.patch");
				});
		
		options = setDeploymentOptions("services.items.post");
		vertx.deployVerticle("services.items.Post",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.items.post");
				});
		
		options = setDeploymentOptions("services.items.delete");
		vertx.deployVerticle("services.items.Delete",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.items.delete");
				});
		
		options = setDeploymentOptions("services.gendertargets.get");
		vertx.deployVerticle("services.gendertargets.Get", 
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.gendertargets.get");
				});
		
		options = setDeploymentOptions("services.gendertargets.patch");
		vertx.deployVerticle("services.gendertargets.Patch",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.gendertargets.patch");
				});
		
		options = setDeploymentOptions("services.gendertargets.post");
		vertx.deployVerticle("services.gendertargets.Post",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.gendertargets.post");
				});
		
		options = setDeploymentOptions("services.gendertargets.delete");
		vertx.deployVerticle("services.gendertargets.Delete",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.gendertargets.delete");
				});
		
		options = setDeploymentOptions("services.relateditems.get");
		vertx.deployVerticle("services.relateditems.Get", 
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.relateditems.get");
				});
		
		options = setDeploymentOptions("services.relateditems.patch");
		vertx.deployVerticle("services.relateditems.Patch",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.relateditems.patch");
				});
		
		options = setDeploymentOptions("services.relateditems.post");
		vertx.deployVerticle("services.relateditems.Post",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.relateditems.post");
				});
		
		options = setDeploymentOptions("services.relateditems.delete");
		vertx.deployVerticle("services.relateditems.Delete",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.relateditems.delete");
				});
		
		options = setDeploymentOptions("services.suppliers.get");
		vertx.deployVerticle("services.suppliers.Get", 
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.suppliers.get");
				});
		
		options = setDeploymentOptions("services.suppliers.patch");
		vertx.deployVerticle("services.suppliers.Patch",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.suppliers.patch");
				});
		
		options = setDeploymentOptions("services.suppliers.post");
		vertx.deployVerticle("services.suppliers.Post",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.suppliers.post");
				});
		
		options = setDeploymentOptions("services.suppliers.delete");
		vertx.deployVerticle("services.suppliers.Delete",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.suppliers.delete");
				});
		
		options = setDeploymentOptions("services.supplieritems.get");
		vertx.deployVerticle("services.supplieritems.Get", 
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.supplieritems.get");
				});
		
		options = setDeploymentOptions("services.supplieritems.patch");
		vertx.deployVerticle("services.supplieritems.Patch",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.supplieritems.patch");
				});
		
		options = setDeploymentOptions("services.supplieritems.post");
		vertx.deployVerticle("services.supplieritems.Post",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.supplieritems.post");
				});
		
		options = setDeploymentOptions("services.supplieritems.delete");
		vertx.deployVerticle("services.supplieritems.Delete",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.supplieritems.delete");
				});
		
		options = setDeploymentOptions("services.glossaries.get");
		vertx.deployVerticle("services.glossaries.Get", 
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.glossaries.get");
				});
		
		options = setDeploymentOptions("services.glossaries.patch");
		vertx.deployVerticle("services.glossaries.Patch",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.glossaries.patch");
				});
		
		options = setDeploymentOptions("services.glossaries.post");
		vertx.deployVerticle("services.glossaries.Post",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.glossaries.post");
				});
		
		options = setDeploymentOptions("services.glossaries.delete");
		vertx.deployVerticle("services.glossaries.Delete",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.glossaries.delete");
				});
		
		options = setDeploymentOptions("services.lkups.get");
		vertx.deployVerticle("services.lkups.Get", 
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.lkups.get");
				});
		
		options = setDeploymentOptions("services.lkups.patch");
		vertx.deployVerticle("services.lkups.Patch",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.lkups.patch");
				});
		
		options = setDeploymentOptions("services.lkups.post");
		vertx.deployVerticle("services.lkups.Post",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.lkups.post");
				});
		
		options = setDeploymentOptions("services.lkups.delete");
		vertx.deployVerticle("services.lkups.Delete",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.lkups.delete");
				});
		
		options = setDeploymentOptions("services.eans.get");
		vertx.deployVerticle("services.eans.Get", 
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.eans.get");
				});
		
		options = setDeploymentOptions("services.eans.patch");
		vertx.deployVerticle("services.eans.Patch",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.eans.patch");
				});
		
		options = setDeploymentOptions("services.eans.post");
		vertx.deployVerticle("services.eans.Post",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.eans.post");
				});
		
		options = setDeploymentOptions("services.eans.delete");
		vertx.deployVerticle("services.eans.Delete",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(options),
				result -> {
					logger.info("Deployed successfully to address services.eans.delete");
				});
		
		vertx.deployVerticle("web.RestVerticle",
						new DeploymentOptions()
						.setWorker(true)
						.setConfig(config()),
				result -> {
					logger.info("Deployed successfully RestVerticle");
				});
	}
	
	private JsonObject setDeploymentOptions(String address) {
		JsonObject options = config().copy();
		options.put("address", address);
		return options;
	}
	
}
