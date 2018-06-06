package es.eci.catalog.model.converters;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import es.eci.catalog.model.pojos.*;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class Codecs {
	
	final Logger logger = LoggerFactory.getLogger(Codecs.class);
	
			
	public static ItemImage json2ItemImage(String json) {
		JsonObject contentJson = new JsonObject(json);
		return json2ItemImage(contentJson);
	}
	
	public static ItemImage json2ItemImage(JsonObject json) {
	
		if (json == null || json.isEmpty()) return new ItemImage();
		String altText = Optional.ofNullable(json.getString("altText")).orElse("");
		String image = Optional.ofNullable(json.getString("image")).orElse(null);
		int id = Optional.ofNullable(json.getInteger("id")).orElse(-1);
		int parentId = json.getInteger("itemId");
		
		ItemImage itemimage = new ItemImage();
		itemimage.setAltText(altText);
		itemimage.setImage(image);
		
		if (id != -1) {
			itemimage.setId(id);
		}
		
		itemimage.setItemId(parentId);
		itemimage.setTypes(new String[]{"ItemImage"});
		
		return itemimage;
	}

			
	public static Item json2Item(String json) {
		JsonObject contentJson = new JsonObject(json);
		return json2Item(contentJson);
	}
	
	public static Item json2Item(JsonObject json) {
	
		if (json == null || json.isEmpty()) return new Item();
		String name = Optional.ofNullable(json.getString("name")).orElse("");
		String description = Optional.ofNullable(json.getString("description")).orElse(null);
		String code = Optional.ofNullable(json.getString("code")).orElse("");
		Optional<JsonArray> imagesArr = Optional.ofNullable(json.getJsonArray("images"));
		List<ItemImage> images = new ArrayList<ItemImage>();
		imagesArr.orElse(new JsonArray()).forEach(
			j -> {
				if (j != null) {
					images.add(json2ItemImage((JsonObject) j));
				}
			}
		);
		Double price = Optional.ofNullable(json.getDouble("price")).orElse(0.0);
		Double retailPrice = Optional.ofNullable(json.getDouble("retailPrice")).orElse(0.0);
		Double costPrice = Optional.ofNullable(json.getDouble("costPrice")).orElse(0.0);
		int id = Optional.ofNullable(json.getInteger("id")).orElse(-1);
		
		Item item = new Item();
		item.setName(name);
		item.setDescription(description);
		item.setCode(code);
		item.setImagesList(images);
		item.setPrice(price);
		item.setRetailPrice(retailPrice);
		item.setCostPrice(costPrice);
		
		if (id != -1) {
			item.setId(id);
		}
		item.setTypes(new String[]{"Item"});
		
		return item;
	}

			
	public static Material json2Material(String json) {
		JsonObject contentJson = new JsonObject(json);
		return json2Material(contentJson);
	}
	
	public static Material json2Material(JsonObject json) {
	
		if (json == null || json.isEmpty()) return new Material();
		Double quantity = Optional.ofNullable(json.getDouble("quantity")).orElse(0.0);
		Item item = json2Item((JsonObject) json.getValue("item"));
		int id = Optional.ofNullable(json.getInteger("id")).orElse(-1);
		int parentId = json.getInteger("courseId");
		
		Material material = new Material();
		material.setQuantity(quantity);
		material.setItemObj(item);
		
		if (id != -1) {
			material.setId(id);
		}
		
		material.setCourseId(parentId);
		material.setTypes(new String[]{"Material"});
		
		return material;
	}

			
	public static Course json2Course(String json) {
		JsonObject contentJson = new JsonObject(json);
		return json2Course(contentJson);
	}
	
	public static Course json2Course(JsonObject json) {
	
		if (json == null || json.isEmpty()) return new Course();
		String name = Optional.ofNullable(json.getString("name")).orElse("");
		String cycle = Optional.ofNullable(json.getString("cycle")).orElse("");
		Optional<JsonArray> materialsArr = Optional.ofNullable(json.getJsonArray("materials"));
		List<Material> materials = new ArrayList<Material>();
		materialsArr.orElse(new JsonArray()).forEach(
			j -> {
				if (j != null) {
					materials.add(json2Material((JsonObject) j));
				}
			}
		);
		int id = Optional.ofNullable(json.getInteger("id")).orElse(-1);
		int parentId = json.getInteger("teachingCenterId");
		
		Course course = new Course();
		course.setName(name);
		course.setCycle(cycle);
		course.setMaterialsList(materials);
		
		if (id != -1) {
			course.setId(id);
		}
		
		course.setTeachingCenterId(parentId);
		course.setTypes(new String[]{"Course"});
		
		return course;
	}

			
	public static TeachingCenter json2TeachingCenter(String json) {
		JsonObject contentJson = new JsonObject(json);
		return json2TeachingCenter(contentJson);
	}
	
	public static TeachingCenter json2TeachingCenter(JsonObject json) {
	
		if (json == null || json.isEmpty()) return new TeachingCenter();
		String name = Optional.ofNullable(json.getString("name")).orElse("");
		String genericName = Optional.ofNullable(json.getString("genericName")).orElse("");
		String email = Optional.ofNullable(json.getString("email")).orElse("");
		String phone = Optional.ofNullable(json.getString("phone")).orElse("");
		String image = Optional.ofNullable(json.getString("image")).orElse(null);
		String code = Optional.ofNullable(json.getString("code")).orElse("");
		String type = Optional.ofNullable(json.getString("type")).orElse("");
		String nature = Optional.ofNullable(json.getString("nature")).orElse("");
		String address = Optional.ofNullable(json.getString("address")).orElse("");
		String formattedAddress = Optional.ofNullable(json.getString("formattedAddress")).orElse("");
		String postalCode = Optional.ofNullable(json.getString("postalCode")).orElse("");
		String town = Optional.ofNullable(json.getString("town")).orElse("");
		String province = Optional.ofNullable(json.getString("province")).orElse("");
		String autonomousRegion = Optional.ofNullable(json.getString("autonomousRegion")).orElse("");
		Double latitude = Optional.ofNullable(json.getDouble("latitude")).orElse(0.0);
		Double longitude = Optional.ofNullable(json.getDouble("longitude")).orElse(0.0);
		Optional<JsonArray> coursesArr = Optional.ofNullable(json.getJsonArray("courses"));
		List<Course> courses = new ArrayList<Course>();
		coursesArr.orElse(new JsonArray()).forEach(
			j -> {
				if (j != null) {
					courses.add(json2Course((JsonObject) j));
				}
			}
		);
		int id = Optional.ofNullable(json.getInteger("id")).orElse(-1);
		
		TeachingCenter teachingcenter = new TeachingCenter();
		teachingcenter.setName(name);
		teachingcenter.setGenericName(genericName);
		teachingcenter.setEmail(email);
		teachingcenter.setPhone(phone);
		teachingcenter.setImage(image);
		teachingcenter.setCode(code);
		teachingcenter.setType(type);
		teachingcenter.setNature(nature);
		teachingcenter.setAddress(address);
		teachingcenter.setFormattedAddress(formattedAddress);
		teachingcenter.setPostalCode(postalCode);
		teachingcenter.setTown(town);
		teachingcenter.setProvince(province);
		teachingcenter.setAutonomousRegion(autonomousRegion);
		teachingcenter.setLatitude(latitude);
		teachingcenter.setLongitude(longitude);
		teachingcenter.setCoursesList(courses);
		
		if (id != -1) {
			teachingcenter.setId(id);
		}
		teachingcenter.setTypes(new String[]{"TeachingCenter"});
		
		return teachingcenter;
	}

			
	public static SchedulingProcess json2SchedulingProcess(String json) {
		JsonObject contentJson = new JsonObject(json);
		return json2SchedulingProcess(contentJson);
	}
	
	public static SchedulingProcess json2SchedulingProcess(JsonObject json) {
	
		if (json == null || json.isEmpty()) return new SchedulingProcess();
		String name = Optional.ofNullable(json.getString("name")).orElse("");
		String cronExpression = Optional.ofNullable(json.getString("cronExpression")).orElse("");
		String script = Optional.ofNullable(json.getString("script")).orElse("");
		int id = Optional.ofNullable(json.getInteger("id")).orElse(-1);
		
		SchedulingProcess schedulingprocess = new SchedulingProcess();
		schedulingprocess.setName(name);
		schedulingprocess.setCronExpression(cronExpression);
		schedulingprocess.setScript(script);
		
		if (id != -1) {
			schedulingprocess.setId(id);
		}
		schedulingprocess.setTypes(new String[]{"SchedulingProcess"});
		
		return schedulingprocess;
	}

			
	public static SecurityAttribute json2SecurityAttribute(String json) {
		JsonObject contentJson = new JsonObject(json);
		return json2SecurityAttribute(contentJson);
	}
	
	public static SecurityAttribute json2SecurityAttribute(JsonObject json) {
	
		if (json == null || json.isEmpty()) return new SecurityAttribute();
		String name = Optional.ofNullable(json.getString("name")).orElse("");
		int id = Optional.ofNullable(json.getInteger("id")).orElse(-1);
		int parentId = json.getInteger("securityEntityId");
		
		SecurityAttribute securityattribute = new SecurityAttribute();
		securityattribute.setName(name);
		
		if (id != -1) {
			securityattribute.setId(id);
		}
		
		securityattribute.setSecurityEntityId(parentId);
		securityattribute.setTypes(new String[]{"SecurityAttribute"});
		
		return securityattribute;
	}

			
	public static SecurityEntity json2SecurityEntity(String json) {
		JsonObject contentJson = new JsonObject(json);
		return json2SecurityEntity(contentJson);
	}
	
	public static SecurityEntity json2SecurityEntity(JsonObject json) {
	
		if (json == null || json.isEmpty()) return new SecurityEntity();
		String name = Optional.ofNullable(json.getString("name")).orElse("");
		Optional<JsonArray> attributesArr = Optional.ofNullable(json.getJsonArray("attributes"));
		List<SecurityAttribute> attributes = new ArrayList<SecurityAttribute>();
		attributesArr.orElse(new JsonArray()).forEach(
			j -> {
				if (j != null) {
					attributes.add(json2SecurityAttribute((JsonObject) j));
				}
			}
		);
		int id = Optional.ofNullable(json.getInteger("id")).orElse(-1);
		
		SecurityEntity securityentity = new SecurityEntity();
		securityentity.setName(name);
		securityentity.setAttributesList(attributes);
		
		if (id != -1) {
			securityentity.setId(id);
		}
		securityentity.setTypes(new String[]{"SecurityEntity"});
		
		return securityentity;
	}

			
	public static SecurityOperationType json2SecurityOperationType(String json) {
		JsonObject contentJson = new JsonObject(json);
		return json2SecurityOperationType(contentJson);
	}
	
	public static SecurityOperationType json2SecurityOperationType(JsonObject json) {
	
		if (json == null || json.isEmpty()) return new SecurityOperationType();
		String name = Optional.ofNullable(json.getString("name")).orElse("");
		int id = Optional.ofNullable(json.getInteger("id")).orElse(-1);
		
		SecurityOperationType securityoperationtype = new SecurityOperationType();
		securityoperationtype.setName(name);
		
		if (id != -1) {
			securityoperationtype.setId(id);
		}
		securityoperationtype.setTypes(new String[]{"SecurityOperationType"});
		
		return securityoperationtype;
	}

			
	public static SecurityAccessPrivilege json2SecurityAccessPrivilege(String json) {
		JsonObject contentJson = new JsonObject(json);
		return json2SecurityAccessPrivilege(contentJson);
	}
	
	public static SecurityAccessPrivilege json2SecurityAccessPrivilege(JsonObject json) {
	
		if (json == null || json.isEmpty()) return new SecurityAccessPrivilege();
		String name = Optional.ofNullable(json.getString("name")).orElse("");
		Optional<JsonArray> operationsArr = Optional.ofNullable(json.getJsonArray("operations"));
		List<SecurityOperationType> operations = new ArrayList<SecurityOperationType>();
		operationsArr.orElse(new JsonArray()).forEach(
			j -> {
				if (j != null) {
					operations.add(json2SecurityOperationType((JsonObject) j));
				}
			}
		);
		Optional<JsonArray> entitiesArr = Optional.ofNullable(json.getJsonArray("entities"));
		List<SecurityEntity> entities = new ArrayList<SecurityEntity>();
		entitiesArr.orElse(new JsonArray()).forEach(
			j -> {
				if (j != null) {
					entities.add(json2SecurityEntity((JsonObject) j));
				}
			}
		);
		Optional<JsonArray> attributesArr = Optional.ofNullable(json.getJsonArray("attributes"));
		List<SecurityAttribute> attributes = new ArrayList<SecurityAttribute>();
		attributesArr.orElse(new JsonArray()).forEach(
			j -> {
				if (j != null) {
					attributes.add(json2SecurityAttribute((JsonObject) j));
				}
			}
		);
		int id = Optional.ofNullable(json.getInteger("id")).orElse(-1);
		int parentId = json.getInteger("securityRoleId");
		
		SecurityAccessPrivilege securityaccessprivilege = new SecurityAccessPrivilege();
		securityaccessprivilege.setName(name);
		securityaccessprivilege.setOperationsList(operations);
		securityaccessprivilege.setEntitiesList(entities);
		securityaccessprivilege.setAttributesList(attributes);
		
		if (id != -1) {
			securityaccessprivilege.setId(id);
		}
		
		securityaccessprivilege.setSecurityRoleId(parentId);
		securityaccessprivilege.setTypes(new String[]{"SecurityAccessPrivilege"});
		
		return securityaccessprivilege;
	}

			
	public static SecurityRowLevelPolicy json2SecurityRowLevelPolicy(String json) {
		JsonObject contentJson = new JsonObject(json);
		return json2SecurityRowLevelPolicy(contentJson);
	}
	
	public static SecurityRowLevelPolicy json2SecurityRowLevelPolicy(JsonObject json) {
	
		if (json == null || json.isEmpty()) return new SecurityRowLevelPolicy();
		String name = Optional.ofNullable(json.getString("name")).orElse("");
		SecurityEntity entity = json2SecurityEntity((JsonObject) json.getValue("entity"));
		Optional<JsonArray> operationsArr = Optional.ofNullable(json.getJsonArray("operations"));
		List<SecurityOperationType> operations = new ArrayList<SecurityOperationType>();
		operationsArr.orElse(new JsonArray()).forEach(
			j -> {
				if (j != null) {
					operations.add(json2SecurityOperationType((JsonObject) j));
				}
			}
		);
		String usingExpression = Optional.ofNullable(json.getString("usingExpression")).orElse("");
		String checkExpression = Optional.ofNullable(json.getString("checkExpression")).orElse("");
		int id = Optional.ofNullable(json.getInteger("id")).orElse(-1);
		int parentId = json.getInteger("securityRoleId");
		
		SecurityRowLevelPolicy securityrowlevelpolicy = new SecurityRowLevelPolicy();
		securityrowlevelpolicy.setName(name);
		securityrowlevelpolicy.setEntityObj(entity);
		securityrowlevelpolicy.setOperationsList(operations);
		securityrowlevelpolicy.setUsingExpression(usingExpression);
		securityrowlevelpolicy.setCheckExpression(checkExpression);
		
		if (id != -1) {
			securityrowlevelpolicy.setId(id);
		}
		
		securityrowlevelpolicy.setSecurityRoleId(parentId);
		securityrowlevelpolicy.setTypes(new String[]{"SecurityRowLevelPolicy"});
		
		return securityrowlevelpolicy;
	}

			
	public static SecurityRole json2SecurityRole(String json) {
		JsonObject contentJson = new JsonObject(json);
		return json2SecurityRole(contentJson);
	}
	
	public static SecurityRole json2SecurityRole(JsonObject json) {
	
		if (json == null || json.isEmpty()) return new SecurityRole();
		String name = Optional.ofNullable(json.getString("name")).orElse("");
		Optional<JsonArray> privilegesArr = Optional.ofNullable(json.getJsonArray("privileges"));
		List<SecurityAccessPrivilege> privileges = new ArrayList<SecurityAccessPrivilege>();
		privilegesArr.orElse(new JsonArray()).forEach(
			j -> {
				if (j != null) {
					privileges.add(json2SecurityAccessPrivilege((JsonObject) j));
				}
			}
		);
		Optional<JsonArray> policiesArr = Optional.ofNullable(json.getJsonArray("policies"));
		List<SecurityRowLevelPolicy> policies = new ArrayList<SecurityRowLevelPolicy>();
		policiesArr.orElse(new JsonArray()).forEach(
			j -> {
				if (j != null) {
					policies.add(json2SecurityRowLevelPolicy((JsonObject) j));
				}
			}
		);
		String comments = Optional.ofNullable(json.getString("comments")).orElse("");
		int id = Optional.ofNullable(json.getInteger("id")).orElse(-1);
		
		SecurityRole securityrole = new SecurityRole();
		securityrole.setName(name);
		securityrole.setPrivilegesList(privileges);
		securityrole.setPoliciesList(policies);
		securityrole.setComments(comments);
		
		if (id != -1) {
			securityrole.setId(id);
		}
		securityrole.setTypes(new String[]{"SecurityRole"});
		
		return securityrole;
	}

			
	public static SecurityUser json2SecurityUser(String json) {
		JsonObject contentJson = new JsonObject(json);
		return json2SecurityUser(contentJson);
	}
	
	public static SecurityUser json2SecurityUser(JsonObject json) {
	
		if (json == null || json.isEmpty()) return new SecurityUser();
		String username = Optional.ofNullable(json.getString("username")).orElse("");
		String password = Optional.ofNullable(json.getString("password")).orElse(null);
		Optional<JsonArray> rolesArr = Optional.ofNullable(json.getJsonArray("roles"));
		List<SecurityRole> roles = new ArrayList<SecurityRole>();
		rolesArr.orElse(new JsonArray()).forEach(
			j -> {
				if (j != null) {
					roles.add(json2SecurityRole((JsonObject) j));
				}
			}
		);
		String email = Optional.ofNullable(json.getString("email")).orElse("");
		String birthDate = Optional.ofNullable(json.getString("birthDate")).orElse(null);
		String photo = Optional.ofNullable(json.getString("photo")).orElse(null);
		String[] identityCards = (String[]) json.getValue("identityCards");
		String presentationVideo = Optional.ofNullable(json.getString("presentationVideo")).orElse(null);
		Optional<JsonArray> teachingCentersArr = Optional.ofNullable(json.getJsonArray("teachingCenters"));
		List<TeachingCenter> teachingCenters = new ArrayList<TeachingCenter>();
		teachingCentersArr.orElse(new JsonArray()).forEach(
			j -> {
				if (j != null) {
					teachingCenters.add(json2TeachingCenter((JsonObject) j));
				}
			}
		);
		int id = Optional.ofNullable(json.getInteger("id")).orElse(-1);
		
		SecurityUser securityuser = new SecurityUser();
		securityuser.setUsername(username);
		securityuser.setPassword(password);
		securityuser.setRolesList(roles);
		securityuser.setEmail(email);
		securityuser.setBirthDate(birthDate);
		securityuser.setPhoto(photo);
		securityuser.setIdentityCards(identityCards);
		securityuser.setPresentationVideo(presentationVideo);
		securityuser.setTeachingCentersList(teachingCenters);
		
		if (id != -1) {
			securityuser.setId(id);
		}
		securityuser.setTypes(new String[]{"SecurityUser"});
		
		return securityuser;
	}

}
