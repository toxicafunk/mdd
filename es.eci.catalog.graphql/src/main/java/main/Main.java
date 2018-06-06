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

import java.util.Optional;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;


/**
 * * Run with:
 *  java -jar target/dc-jms-integrations-1.0-SNAPSHOT-fat -conf config.json
 *  
 * @author erodriguez
 *
 */
public class Main extends AbstractVerticle {
	
	final Logger logger = LoggerFactory.getLogger(Main.class);
	
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
		
		vertx.deployVerticle("web.GraphQLVerticle", new DeploymentOptions().setWorker(true).setConfig(config()),
				result -> {
					logger.info("Deployed successfully GraphQLVerticle");
				});
	}
		
		@Override
	public void stop() throws Exception {
		super.stop();
	}
}
