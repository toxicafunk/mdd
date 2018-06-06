package es.eci.catalog.model.converters;


import es.eci.catalog.model.pojos.*;

import java.util.List;
import java.util.stream.Collectors;

import org.jooq.DSLContext;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class InverseCodecs {
	
	final Logger logger = LoggerFactory.getLogger(InverseCodecs.class);
	
		
	public static JsonObject entity2JsonObject(DSLContext ctx, ItemImage itemImage, List<String> initialize) {
	
		if (itemImage == null) return null;
	
		JsonObject jsonToEncode = new JsonObject();
		
		jsonToEncode.put("altText", itemImage.getAltText());
		jsonToEncode.put("image", itemImage.getImage());
		jsonToEncode.put("itemId", itemImage.getItemId());
		jsonToEncode.put("id", itemImage.getId());
		
		
		return jsonToEncode;
	}

		
	public static JsonObject entity2JsonObject(DSLContext ctx, Item item, List<String> initialize) {
	
		if (item == null) return null;
	
		JsonObject jsonToEncode = new JsonObject();
		
		jsonToEncode.put("name", item.getName());
		jsonToEncode.put("description", item.getDescription());
		jsonToEncode.put("code", item.getCode());
		if (initialize.contains("images")) {
			List<ItemImage> images = services.itemimages.Get.fecthByMasterId(ctx, item.getId());
			jsonToEncode.put("images", images);
		} else {
			List<ItemImage> images = services.itemimages.Get.fecthByMasterId(ctx, item.getId());
			jsonToEncode.put("images", images.stream().map(f -> f.getId()).collect(Collectors.toList()));
		}
		jsonToEncode.put("price", item.getPrice());
		jsonToEncode.put("retailPrice", item.getRetailPrice());
		jsonToEncode.put("costPrice", item.getCostPrice());
		jsonToEncode.put("id", item.getId());
		
		
		return jsonToEncode;
	}

		
	public static JsonObject entity2JsonObject(DSLContext ctx, Material material, List<String> initialize) {
	
		if (material == null) return null;
	
		JsonObject jsonToEncode = new JsonObject();
		
		jsonToEncode.put("quantity", material.getQuantity());
		if (initialize.contains("item")) {
			Item item = services.items.Get.fecthById(ctx, material.getId());
			jsonToEncode.put("item", item);
		} else {
			jsonToEncode.put("item", material.getItem());
		}
		jsonToEncode.put("courseId", material.getCourseId());
		jsonToEncode.put("id", material.getId());
		
		
		return jsonToEncode;
	}

		
	public static JsonObject entity2JsonObject(DSLContext ctx, Course course, List<String> initialize) {
	
		if (course == null) return null;
	
		JsonObject jsonToEncode = new JsonObject();
		
		jsonToEncode.put("name", course.getName());
		jsonToEncode.put("cycle", course.getCycle());
		if (initialize.contains("materials")) {
			List<Material> materials = services.materials.Get.fecthByMasterId(ctx, course.getId());
			jsonToEncode.put("materials", materials);
		} else {
			List<Material> materials = services.materials.Get.fecthByMasterId(ctx, course.getId());
			jsonToEncode.put("materials", materials.stream().map(f -> f.getId()).collect(Collectors.toList()));
		}
		jsonToEncode.put("teachingCenterId", course.getTeachingCenterId());
		jsonToEncode.put("id", course.getId());
		
		
		return jsonToEncode;
	}

		
	public static JsonObject entity2JsonObject(DSLContext ctx, TeachingCenter teachingCenter, List<String> initialize) {
	
		if (teachingCenter == null) return null;
	
		JsonObject jsonToEncode = new JsonObject();
		
		jsonToEncode.put("name", teachingCenter.getName());
		jsonToEncode.put("genericName", teachingCenter.getGenericName());
		jsonToEncode.put("email", teachingCenter.getEmail());
		jsonToEncode.put("phone", teachingCenter.getPhone());
		jsonToEncode.put("image", teachingCenter.getImage());
		jsonToEncode.put("code", teachingCenter.getCode());
		jsonToEncode.put("type", teachingCenter.getType());
		jsonToEncode.put("nature", teachingCenter.getNature());
		jsonToEncode.put("address", teachingCenter.getAddress());
		jsonToEncode.put("formattedAddress", teachingCenter.getFormattedAddress());
		jsonToEncode.put("postalCode", teachingCenter.getPostalCode());
		jsonToEncode.put("town", teachingCenter.getTown());
		jsonToEncode.put("province", teachingCenter.getProvince());
		jsonToEncode.put("autonomousRegion", teachingCenter.getAutonomousRegion());
		jsonToEncode.put("latitude", teachingCenter.getLatitude());
		jsonToEncode.put("longitude", teachingCenter.getLongitude());
		if (initialize.contains("courses")) {
			List<Course> courses = services.courses.Get.fecthByMasterId(ctx, teachingCenter.getId());
			jsonToEncode.put("courses", courses);
		} else {
			List<Course> courses = services.courses.Get.fecthByMasterId(ctx, teachingCenter.getId());
			jsonToEncode.put("courses", courses.stream().map(f -> f.getId()).collect(Collectors.toList()));
		}
		jsonToEncode.put("id", teachingCenter.getId());
		
		
		return jsonToEncode;
	}

		
	public static JsonObject entity2JsonObject(DSLContext ctx, SchedulingProcess schedulingProcess, List<String> initialize) {
	
		if (schedulingProcess == null) return null;
	
		JsonObject jsonToEncode = new JsonObject();
		
		jsonToEncode.put("name", schedulingProcess.getName());
		jsonToEncode.put("cronExpression", schedulingProcess.getCronExpression());
		jsonToEncode.put("script", schedulingProcess.getScript());
		jsonToEncode.put("id", schedulingProcess.getId());
		
		
		return jsonToEncode;
	}

		
	public static JsonObject entity2JsonObject(DSLContext ctx, SecurityAttribute securityAttribute, List<String> initialize) {
	
		if (securityAttribute == null) return null;
	
		JsonObject jsonToEncode = new JsonObject();
		
		jsonToEncode.put("name", securityAttribute.getName());
		jsonToEncode.put("securityEntityId", securityAttribute.getSecurityEntityId());
		jsonToEncode.put("id", securityAttribute.getId());
		
		
		return jsonToEncode;
	}

		
	public static JsonObject entity2JsonObject(DSLContext ctx, SecurityEntity securityEntity, List<String> initialize) {
	
		if (securityEntity == null) return null;
	
		JsonObject jsonToEncode = new JsonObject();
		
		jsonToEncode.put("name", securityEntity.getName());
		if (initialize.contains("attributes")) {
			List<SecurityAttribute> attributes = services.securityattributes.Get.fecthByMasterId(ctx, securityEntity.getId());
			jsonToEncode.put("attributes", attributes);
		} else {
			List<SecurityAttribute> attributes = services.securityattributes.Get.fecthByMasterId(ctx, securityEntity.getId());
			jsonToEncode.put("attributes", attributes.stream().map(f -> f.getId()).collect(Collectors.toList()));
		}
		jsonToEncode.put("id", securityEntity.getId());
		
		
		return jsonToEncode;
	}

		
	public static JsonObject entity2JsonObject(DSLContext ctx, SecurityOperationType securityOperationType, List<String> initialize) {
	
		if (securityOperationType == null) return null;
	
		JsonObject jsonToEncode = new JsonObject();
		
		jsonToEncode.put("name", securityOperationType.getName());
		jsonToEncode.put("id", securityOperationType.getId());
		
		
		return jsonToEncode;
	}

		
	public static JsonObject entity2JsonObject(DSLContext ctx, SecurityAccessPrivilege securityAccessPrivilege, List<String> initialize) {
	
		if (securityAccessPrivilege == null) return null;
	
		JsonObject jsonToEncode = new JsonObject();
		
		jsonToEncode.put("name", securityAccessPrivilege.getName());
		if (initialize.contains("operations")) {
			List<SecurityOperationType> operations = services.securityoperationtypes.Get.fecthByIds(ctx, securityAccessPrivilege.getOperations());
			jsonToEncode.put("operations", operations);
		} else {
			Integer[] idInts = securityAccessPrivilege.getOperations();
			if (idInts != null) {
				JsonArray ids = new JsonArray();
				for (int i = 0; i < idInts.length; i++) {
					if (securityAccessPrivilege.getOperations()[i] != null) {
						ids.add(securityAccessPrivilege.getOperations()[i]);
					}
				}
				
				if (!ids.isEmpty()) {
					jsonToEncode.put("operations", ids);
				}
			}
		}
		if (initialize.contains("entities")) {
			List<SecurityEntity> entities = services.securityentities.Get.fecthByIds(ctx, securityAccessPrivilege.getEntities());
			jsonToEncode.put("entities", entities);
		} else {
			Integer[] idInts = securityAccessPrivilege.getEntities();
			if (idInts != null) {
				JsonArray ids = new JsonArray();
				for (int i = 0; i < idInts.length; i++) {
					if (securityAccessPrivilege.getEntities()[i] != null) {
						ids.add(securityAccessPrivilege.getEntities()[i]);
					}
				}
				
				if (!ids.isEmpty()) {
					jsonToEncode.put("entities", ids);
				}
			}
		}
		if (initialize.contains("attributes")) {
			List<SecurityAttribute> attributes = services.securityattributes.Get.fecthByIds(ctx, securityAccessPrivilege.getAttributes());
			jsonToEncode.put("attributes", attributes);
		} else {
			Integer[] idInts = securityAccessPrivilege.getAttributes();
			if (idInts != null) {
				JsonArray ids = new JsonArray();
				for (int i = 0; i < idInts.length; i++) {
					if (securityAccessPrivilege.getAttributes()[i] != null) {
						ids.add(securityAccessPrivilege.getAttributes()[i]);
					}
				}
				
				if (!ids.isEmpty()) {
					jsonToEncode.put("attributes", ids);
				}
			}
		}
		jsonToEncode.put("securityRoleId", securityAccessPrivilege.getSecurityRoleId());
		jsonToEncode.put("id", securityAccessPrivilege.getId());
		
		
		return jsonToEncode;
	}

		
	public static JsonObject entity2JsonObject(DSLContext ctx, SecurityRowLevelPolicy securityRowLevelPolicy, List<String> initialize) {
	
		if (securityRowLevelPolicy == null) return null;
	
		JsonObject jsonToEncode = new JsonObject();
		
		jsonToEncode.put("name", securityRowLevelPolicy.getName());
		if (initialize.contains("entity")) {
			SecurityEntity entity = services.securityentities.Get.fecthById(ctx, securityRowLevelPolicy.getId());
			jsonToEncode.put("entity", entity);
		} else {
			jsonToEncode.put("entity", securityRowLevelPolicy.getEntity());
		}
		if (initialize.contains("operations")) {
			List<SecurityOperationType> operations = services.securityoperationtypes.Get.fecthByIds(ctx, securityRowLevelPolicy.getOperations());
			jsonToEncode.put("operations", operations);
		} else {
			Integer[] idInts = securityRowLevelPolicy.getOperations();
			if (idInts != null) {
				JsonArray ids = new JsonArray();
				for (int i = 0; i < idInts.length; i++) {
					if (securityRowLevelPolicy.getOperations()[i] != null) {
						ids.add(securityRowLevelPolicy.getOperations()[i]);
					}
				}
				
				if (!ids.isEmpty()) {
					jsonToEncode.put("operations", ids);
				}
			}
		}
		jsonToEncode.put("usingExpression", securityRowLevelPolicy.getUsingExpression());
		jsonToEncode.put("checkExpression", securityRowLevelPolicy.getCheckExpression());
		jsonToEncode.put("securityRoleId", securityRowLevelPolicy.getSecurityRoleId());
		jsonToEncode.put("id", securityRowLevelPolicy.getId());
		
		
		return jsonToEncode;
	}

		
	public static JsonObject entity2JsonObject(DSLContext ctx, SecurityRole securityRole, List<String> initialize) {
	
		if (securityRole == null) return null;
	
		JsonObject jsonToEncode = new JsonObject();
		
		jsonToEncode.put("name", securityRole.getName());
		if (initialize.contains("privileges")) {
			List<SecurityAccessPrivilege> privileges = services.securityaccessprivileges.Get.fecthByMasterId(ctx, securityRole.getId());
			jsonToEncode.put("privileges", privileges);
		} else {
			List<SecurityAccessPrivilege> privileges = services.securityaccessprivileges.Get.fecthByMasterId(ctx, securityRole.getId());
			jsonToEncode.put("privileges", privileges.stream().map(f -> f.getId()).collect(Collectors.toList()));
		}
		if (initialize.contains("policies")) {
			List<SecurityRowLevelPolicy> policies = services.securityrowlevelpolicies.Get.fecthByMasterId(ctx, securityRole.getId());
			jsonToEncode.put("policies", policies);
		} else {
			List<SecurityRowLevelPolicy> policies = services.securityrowlevelpolicies.Get.fecthByMasterId(ctx, securityRole.getId());
			jsonToEncode.put("policies", policies.stream().map(f -> f.getId()).collect(Collectors.toList()));
		}
		jsonToEncode.put("comments", securityRole.getComments());
		jsonToEncode.put("id", securityRole.getId());
		
		
		return jsonToEncode;
	}

		
	public static JsonObject entity2JsonObject(DSLContext ctx, SecurityUser securityUser, List<String> initialize) {
	
		if (securityUser == null) return null;
	
		JsonObject jsonToEncode = new JsonObject();
		
		jsonToEncode.put("username", securityUser.getUsername());
		jsonToEncode.put("password", securityUser.getPassword());
		if (initialize.contains("roles")) {
			List<SecurityRole> roles = services.securityroles.Get.fecthByIds(ctx, securityUser.getRoles());
			jsonToEncode.put("roles", roles);
		} else {
			Integer[] idInts = securityUser.getRoles();
			if (idInts != null) {
				JsonArray ids = new JsonArray();
				for (int i = 0; i < idInts.length; i++) {
					if (securityUser.getRoles()[i] != null) {
						ids.add(securityUser.getRoles()[i]);
					}
				}
				
				if (!ids.isEmpty()) {
					jsonToEncode.put("roles", ids);
				}
			}
		}
		jsonToEncode.put("email", securityUser.getEmail());
		jsonToEncode.put("birthDate", securityUser.getBirthDate());
		jsonToEncode.put("photo", securityUser.getPhoto());
		jsonToEncode.put("identityCards", securityUser.getIdentityCards());
		jsonToEncode.put("presentationVideo", securityUser.getPresentationVideo());
		if (initialize.contains("teachingCenters")) {
			List<TeachingCenter> teachingCenters = services.teachingcenters.Get.fecthByIds(ctx, securityUser.getTeachingCenters());
			jsonToEncode.put("teachingCenters", teachingCenters);
		} else {
			Integer[] idInts = securityUser.getTeachingCenters();
			if (idInts != null) {
				JsonArray ids = new JsonArray();
				for (int i = 0; i < idInts.length; i++) {
					if (securityUser.getTeachingCenters()[i] != null) {
						ids.add(securityUser.getTeachingCenters()[i]);
					}
				}
				
				if (!ids.isEmpty()) {
					jsonToEncode.put("teachingCenters", ids);
				}
			}
		}
		jsonToEncode.put("id", securityUser.getId());
		
		
		return jsonToEncode;
	}

}
