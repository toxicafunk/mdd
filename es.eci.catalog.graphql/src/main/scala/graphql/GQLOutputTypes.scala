package graphql


import monix.execution.Scheduler.Implicits.global
import scala.concurrent.Future

import sangria.execution.deferred._
import sangria.macros.derive._
import sangria.schema._

import scala.concurrent.ExecutionContext

object GQLOutputTypes {

  case class Ctx(execution: Execution)
  
  val itemImageFetcher = Fetcher(
      (ctx: Ctx, ids: Seq[Int]) => Future {
        ids.flatMap(id => ctx.execution.itemImage(id))
      }
  )(HasId(_.id))
  val itemFetcher = Fetcher(
      (ctx: Ctx, ids: Seq[Int]) => Future {
        ids.flatMap(id => ctx.execution.item(id))
      }
  )(HasId(_.id))
  val materialFetcher = Fetcher(
      (ctx: Ctx, ids: Seq[Int]) => Future {
        ids.flatMap(id => ctx.execution.material(id))
      }
  )(HasId(_.id))
  val courseFetcher = Fetcher(
      (ctx: Ctx, ids: Seq[Int]) => Future {
        ids.flatMap(id => ctx.execution.course(id))
      }
  )(HasId(_.id))
  val teachingCenterFetcher = Fetcher(
      (ctx: Ctx, ids: Seq[Int]) => Future {
        ids.flatMap(id => ctx.execution.teachingCenter(id))
      }
  )(HasId(_.id))
  val schedulingProcessFetcher = Fetcher(
      (ctx: Ctx, ids: Seq[Int]) => Future {
        ids.flatMap(id => ctx.execution.schedulingProcess(id))
      }
  )(HasId(_.id))
  val securityAttributeFetcher = Fetcher(
      (ctx: Ctx, ids: Seq[Int]) => Future {
        ids.flatMap(id => ctx.execution.securityAttribute(id))
      }
  )(HasId(_.id))
  val securityEntityFetcher = Fetcher(
      (ctx: Ctx, ids: Seq[Int]) => Future {
        ids.flatMap(id => ctx.execution.securityEntity(id))
      }
  )(HasId(_.id))
  val securityOperationTypeFetcher = Fetcher(
      (ctx: Ctx, ids: Seq[Int]) => Future {
        ids.flatMap(id => ctx.execution.securityOperationType(id))
      }
  )(HasId(_.id))
  val securityAccessPrivilegeFetcher = Fetcher(
      (ctx: Ctx, ids: Seq[Int]) => Future {
        ids.flatMap(id => ctx.execution.securityAccessPrivilege(id))
      }
  )(HasId(_.id))
  val securityRowLevelPolicyFetcher = Fetcher(
      (ctx: Ctx, ids: Seq[Int]) => Future {
        ids.flatMap(id => ctx.execution.securityRowLevelPolicy(id))
      }
  )(HasId(_.id))
  val securityRoleFetcher = Fetcher(
      (ctx: Ctx, ids: Seq[Int]) => Future {
        ids.flatMap(id => ctx.execution.securityRole(id))
      }
  )(HasId(_.id))
  val securityUserFetcher = Fetcher(
      (ctx: Ctx, ids: Seq[Int]) => Future {
        ids.flatMap(id => ctx.execution.securityUser(id))
      }
  )(HasId(_.id))

  val resolver: DeferredResolver[Ctx] = DeferredResolver.fetchersWithFallback( new CatalogResolver
	, itemImageFetcher
	, itemFetcher
	, materialFetcher
	, courseFetcher
	, teachingCenterFetcher
	, schedulingProcessFetcher
	, securityAttributeFetcher
	, securityEntityFetcher
	, securityOperationTypeFetcher
	, securityAccessPrivilegeFetcher
	, securityRowLevelPolicyFetcher
	, securityRoleFetcher
	, securityUserFetcher
  )

  val Id = Argument("id", IntType)
  val Limit = Argument("limit", OptionInputType(IntType))
  val Offset = Argument("offset", OptionInputType(IntType))
  val Search = Argument("search", OptionInputType(StringType))

  case class ItemImageDeferred(ids: Option[List[Int]], limit: Option[Int], offset: Option[Int], search: Option[String]) extends Deferred[Seq[ItemImage]]
  case class ItemDeferred(ids: Option[List[Int]], limit: Option[Int], offset: Option[Int], search: Option[String]) extends Deferred[Seq[Item]]
  case class MaterialDeferred(ids: Option[List[Int]], limit: Option[Int], offset: Option[Int], search: Option[String]) extends Deferred[Seq[Material]]
  case class CourseDeferred(ids: Option[List[Int]], limit: Option[Int], offset: Option[Int], search: Option[String]) extends Deferred[Seq[Course]]
  case class TeachingCenterDeferred(ids: Option[List[Int]], limit: Option[Int], offset: Option[Int], search: Option[String]) extends Deferred[Seq[TeachingCenter]]
  case class SchedulingProcessDeferred(ids: Option[List[Int]], limit: Option[Int], offset: Option[Int], search: Option[String]) extends Deferred[Seq[SchedulingProcess]]
  case class SecurityAttributeDeferred(ids: Option[List[Int]], limit: Option[Int], offset: Option[Int], search: Option[String]) extends Deferred[Seq[SecurityAttribute]]
  case class SecurityEntityDeferred(ids: Option[List[Int]], limit: Option[Int], offset: Option[Int], search: Option[String]) extends Deferred[Seq[SecurityEntity]]
  case class SecurityOperationTypeDeferred(ids: Option[List[Int]], limit: Option[Int], offset: Option[Int], search: Option[String]) extends Deferred[Seq[SecurityOperationType]]
  case class SecurityAccessPrivilegeDeferred(ids: Option[List[Int]], limit: Option[Int], offset: Option[Int], search: Option[String]) extends Deferred[Seq[SecurityAccessPrivilege]]
  case class SecurityRowLevelPolicyDeferred(ids: Option[List[Int]], limit: Option[Int], offset: Option[Int], search: Option[String]) extends Deferred[Seq[SecurityRowLevelPolicy]]
  case class SecurityRoleDeferred(ids: Option[List[Int]], limit: Option[Int], offset: Option[Int], search: Option[String]) extends Deferred[Seq[SecurityRole]]
  case class SecurityUserDeferred(ids: Option[List[Int]], limit: Option[Int], offset: Option[Int], search: Option[String]) extends Deferred[Seq[SecurityUser]]
  
  class CatalogResolver extends DeferredResolver[Ctx] {
    def resolve(deferred: Vector[Deferred[Any]], ctx: Ctx, queryState: Any)(implicit ec: ExecutionContext) = {
      val defMap = deferred.collect {
      	case ItemImageDeferred(ids, limit, offset, search)  => ("itemimages" -> ctx.execution.itemImages(ids, limit, offset, search))
      	case ItemDeferred(ids, limit, offset, search)  => ("items" -> ctx.execution.items(ids, limit, offset, search))
      	case MaterialDeferred(ids, limit, offset, search)  => ("materials" -> ctx.execution.materials(ids, limit, offset, search))
      	case CourseDeferred(ids, limit, offset, search)  => ("courses" -> ctx.execution.courses(ids, limit, offset, search))
      	case TeachingCenterDeferred(ids, limit, offset, search)  => ("teachingcenters" -> ctx.execution.teachingCenters(ids, limit, offset, search))
      	case SchedulingProcessDeferred(ids, limit, offset, search)  => ("schedulingprocesses" -> ctx.execution.schedulingProcesses(ids, limit, offset, search))
      	case SecurityAttributeDeferred(ids, limit, offset, search)  => ("securityattributes" -> ctx.execution.securityAttributes(ids, limit, offset, search))
      	case SecurityEntityDeferred(ids, limit, offset, search)  => ("securityentities" -> ctx.execution.securityEntities(ids, limit, offset, search))
      	case SecurityOperationTypeDeferred(ids, limit, offset, search)  => ("securityoperationtypes" -> ctx.execution.securityOperationTypes(ids, limit, offset, search))
      	case SecurityAccessPrivilegeDeferred(ids, limit, offset, search)  => ("securityaccessprivileges" -> ctx.execution.securityAccessPrivileges(ids, limit, offset, search))
      	case SecurityRowLevelPolicyDeferred(ids, limit, offset, search)  => ("securityrowlevelpolicies" -> ctx.execution.securityRowLevelPolicies(ids, limit, offset, search))
      	case SecurityRoleDeferred(ids, limit, offset, search)  => ("securityroles" -> ctx.execution.securityRoles(ids, limit, offset, search))
      	case SecurityUserDeferred(ids, limit, offset, search)  => ("securityusers" -> ctx.execution.securityUsers(ids, limit, offset, search))
      }
      
      deferred flatMap {
      	case ItemImageDeferred(ids, limit, offset, search)  => defMap.filter(_._1 == "itemimages").map(_._2).toVector
      	case ItemDeferred(ids, limit, offset, search)  => defMap.filter(_._1 == "items").map(_._2).toVector
      	case MaterialDeferred(ids, limit, offset, search)  => defMap.filter(_._1 == "materials").map(_._2).toVector
      	case CourseDeferred(ids, limit, offset, search)  => defMap.filter(_._1 == "courses").map(_._2).toVector
      	case TeachingCenterDeferred(ids, limit, offset, search)  => defMap.filter(_._1 == "teachingcenters").map(_._2).toVector
      	case SchedulingProcessDeferred(ids, limit, offset, search)  => defMap.filter(_._1 == "schedulingprocesses").map(_._2).toVector
      	case SecurityAttributeDeferred(ids, limit, offset, search)  => defMap.filter(_._1 == "securityattributes").map(_._2).toVector
      	case SecurityEntityDeferred(ids, limit, offset, search)  => defMap.filter(_._1 == "securityentities").map(_._2).toVector
      	case SecurityOperationTypeDeferred(ids, limit, offset, search)  => defMap.filter(_._1 == "securityoperationtypes").map(_._2).toVector
      	case SecurityAccessPrivilegeDeferred(ids, limit, offset, search)  => defMap.filter(_._1 == "securityaccessprivileges").map(_._2).toVector
      	case SecurityRowLevelPolicyDeferred(ids, limit, offset, search)  => defMap.filter(_._1 == "securityrowlevelpolicies").map(_._2).toVector
      	case SecurityRoleDeferred(ids, limit, offset, search)  => defMap.filter(_._1 == "securityroles").map(_._2).toVector
      	case SecurityUserDeferred(ids, limit, offset, search)  => defMap.filter(_._1 == "securityusers").map(_._2).toVector
     } map(Future(_))
    }
  }

  implicit val ItemImageType = ObjectType(
    "ItemImage",
    "",

	fields[Unit, ItemImage](
	  Field("id", IntType, 
      	description = Some("Id of ItemImage"),
      	resolve = _.value.id)
	, Field("itemId", IntType, 
      	description = Some("Parent Id of item"),
      	resolve = _.value.itemId)
	, Field("altText", StringType,	
        description = Some("Texto alternativo de la imagen"),
        resolve = _.value.altText)
	, Field("image", StringType,	
        description = Some("Imagen"),
        resolve = _.value.image)
    )
  )
  
  implicit val ItemType = ObjectType(
    "Item",
    "",

	fields[Unit, Item](
	  Field("id", IntType, 
      	description = Some("Id of Item"),
      	resolve = _.value.id)
	, Field("name", StringType,	
        description = Some("Nombre del producto"),
        resolve = _.value.name)
	, Field("description", OptionType(StringType),	
        description = Some("Descripción"),
        resolve = _.value.description)
	, Field("code", OptionType(StringType),	
        description = Some("Código del producto (GTIN, EAN, ISBN)"),
        resolve = _.value.code)
	, Field("images", OptionType(ListType(ItemImageType)),	
        description = Some("Imágenes"),
        arguments = Limit :: Offset :: Search :: Nil,
        		
        resolve = c => ItemImageDeferred(c.value.images, c arg Limit, c arg Offset, c arg Search)
	  )
	, Field("price", OptionType(FloatType),	
        description = Some("Precio"),
        resolve = _.value.price)
	, Field("retailPrice", OptionType(FloatType),	
        description = Some("Precio de venta"),
        resolve = _.value.retailPrice)
	, Field("costPrice", OptionType(FloatType),	
        description = Some("Precio de coste"),
        resolve = _.value.costPrice)
    )
  )
  
  implicit val MaterialType = ObjectType(
    "Material",
    "",

	fields[Unit, Material](
	  Field("id", IntType, 
      	description = Some("Id of Material"),
      	resolve = _.value.id)
	, Field("courseId", IntType, 
      	description = Some("Parent Id of course"),
      	resolve = _.value.courseId)
	, Field("quantity", FloatType,	
        description = Some("Cantidad"),
        resolve = _.value.quantity)
	, Field("item", ItemType,	
        description = Some("Ítem (libro o material escolar)"),
        resolve = c ⇒ itemFetcher.defer(c.value.item)
	  )
    )
  )
  
  implicit val CourseType = ObjectType(
    "Course",
    "",

	fields[Unit, Course](
	  Field("id", IntType, 
      	description = Some("Id of Course"),
      	resolve = _.value.id)
	, Field("teachingCenterId", IntType, 
      	description = Some("Parent Id of teachingCenter"),
      	resolve = _.value.teachingCenterId)
	, Field("name", StringType,	
        description = Some("Nombre del curso"),
        resolve = _.value.name)
	, Field("cycle", StringType,	
        description = Some("Ciclo formativo"),
        resolve = _.value.cycle)
	, Field("materials", OptionType(ListType(MaterialType)),	
        description = Some("Libros y material escolar"),
        arguments = Limit :: Offset :: Search :: Nil,
        		
        resolve = c => MaterialDeferred(c.value.materials, c arg Limit, c arg Offset, c arg Search)
	  )
    )
  )
  
  implicit val TeachingCenterType = ObjectType(
    "TeachingCenter",
    "",

	fields[Unit, TeachingCenter](
	  Field("id", IntType, 
      	description = Some("Id of TeachingCenter"),
      	resolve = _.value.id)
	, Field("name", StringType,	
        description = Some("Nombre del centro"),
        resolve = _.value.name)
	, Field("genericName", OptionType(StringType),	
        description = Some("Denominación genérica del centro"),
        resolve = _.value.genericName)
	, Field("email", OptionType(StringType),	
        description = Some("Dirección de correo electrónico"),
        resolve = _.value.email)
	, Field("phone", OptionType(StringType),	
        description = Some("Teléfono"),
        resolve = _.value.phone)
	, Field("image", OptionType(StringType),	
        description = Some("Escudo / Logotipo"),
        resolve = _.value.image)
	, Field("code", StringType,	
        description = Some("Código del centro"),
        resolve = _.value.code)
	, Field("type", OptionType(StringType),	
        description = Some("Tipo de centro"),
        resolve = _.value.`type`)
	, Field("nature", OptionType(StringType),	
        description = Some("Naturaleza"),
        resolve = _.value.nature)
	, Field("address", OptionType(StringType),	
        description = Some("Dirección"),
        resolve = _.value.address)
	, Field("formattedAddress", OptionType(StringType),	
        description = Some("Dirección formateada"),
        resolve = _.value.formattedAddress)
	, Field("postalCode", OptionType(StringType),	
        description = Some("Código postal"),
        resolve = _.value.postalCode)
	, Field("town", OptionType(StringType),	
        description = Some("Localidad"),
        resolve = _.value.town)
	, Field("province", OptionType(StringType),	
        description = Some("Provincia"),
        resolve = _.value.province)
	, Field("autonomousRegion", OptionType(StringType),	
        description = Some("Comunidad autónoma"),
        resolve = _.value.autonomousRegion)
	, Field("latitude", OptionType(FloatType),	
        description = Some("Latitud"),
        resolve = _.value.latitude)
	, Field("longitude", OptionType(FloatType),	
        description = Some("Longitud"),
        resolve = _.value.longitude)
	, Field("courses", OptionType(ListType(CourseType)),	
        description = Some("Cursos"),
        arguments = Limit :: Offset :: Search :: Nil,
        		
        resolve = c => CourseDeferred(c.value.courses, c arg Limit, c arg Offset, c arg Search)
	  )
    )
  )
  
  implicit val SchedulingProcessType = ObjectType(
    "SchedulingProcess",
    "",

	fields[Unit, SchedulingProcess](
	  Field("id", IntType, 
      	description = Some("Id of SchedulingProcess"),
      	resolve = _.value.id)
	, Field("name", StringType,	
        description = Some("Nombre del proceso"),
        resolve = _.value.name)
	, Field("cronExpression", StringType,	
        description = Some("Expresión de planificación (CRON)"),
        resolve = _.value.cronExpression)
	, Field("script", StringType,	
        description = Some("Script (JavaScript)"),
        resolve = _.value.script)
    )
  )
  
  implicit val SecurityAttributeType = ObjectType(
    "SecurityAttribute",
    "",

	fields[Unit, SecurityAttribute](
	  Field("id", IntType, 
      	description = Some("Id of SecurityAttribute"),
      	resolve = _.value.id)
	, Field("securityEntityId", IntType, 
      	description = Some("Parent Id of securityEntity"),
      	resolve = _.value.securityEntityId)
	, Field("name", StringType,	
        description = Some("Nombre del atributo"),
        resolve = _.value.name)
    )
  )
  
  implicit val SecurityEntityType = ObjectType(
    "SecurityEntity",
    "",

	fields[Unit, SecurityEntity](
	  Field("id", IntType, 
      	description = Some("Id of SecurityEntity"),
      	resolve = _.value.id)
	, Field("name", StringType,	
        description = Some("Nombre de la entidad"),
        resolve = _.value.name)
	, Field("attributes", OptionType(ListType(SecurityAttributeType)),	
        description = Some(""),
        arguments = Limit :: Offset :: Search :: Nil,
        		
        resolve = c => SecurityAttributeDeferred(c.value.attributes, c arg Limit, c arg Offset, c arg Search)
	  )
    )
  )
  
  implicit val SecurityOperationTypeType = ObjectType(
    "SecurityOperationType",
    "",

	fields[Unit, SecurityOperationType](
	  Field("id", IntType, 
      	description = Some("Id of SecurityOperationType"),
      	resolve = _.value.id)
	, Field("name", StringType,	
        description = Some("Nombre"),
        resolve = _.value.name)
    )
  )
  
  implicit val SecurityAccessPrivilegeType = ObjectType(
    "SecurityAccessPrivilege",
    "",

	fields[Unit, SecurityAccessPrivilege](
	  Field("id", IntType, 
      	description = Some("Id of SecurityAccessPrivilege"),
      	resolve = _.value.id)
	, Field("securityRoleId", IntType, 
      	description = Some("Parent Id of securityRole"),
      	resolve = _.value.securityRoleId)
	, Field("name", StringType,	
        description = Some("Nombre"),
        resolve = _.value.name)
	, Field("operations", OptionType(ListType(SecurityOperationTypeType)),	
        description = Some("Operaciones permitidas"),
        arguments = Limit :: Offset :: Search :: Nil,
        		
        resolve = c => SecurityOperationTypeDeferred(c.value.operations, c arg Limit, c arg Offset, c arg Search)
	  )
	, Field("entities", ListType(SecurityEntityType),	
        description = Some("Entidades"),
        arguments = Limit :: Offset :: Search :: Nil,
        		
        resolve = c => SecurityEntityDeferred(Some(c.value.entities), c arg Limit, c arg Offset, c arg Search)
	  )
	, Field("attributes", OptionType(ListType(SecurityAttributeType)),	
        description = Some("Atributos"),
        arguments = Limit :: Offset :: Search :: Nil,
        		
        resolve = c => SecurityAttributeDeferred(c.value.attributes, c arg Limit, c arg Offset, c arg Search)
	  )
    )
  )
  
  implicit val SecurityRowLevelPolicyType = ObjectType(
    "SecurityRowLevelPolicy",
    "",

	fields[Unit, SecurityRowLevelPolicy](
	  Field("id", IntType, 
      	description = Some("Id of SecurityRowLevelPolicy"),
      	resolve = _.value.id)
	, Field("securityRoleId", IntType, 
      	description = Some("Parent Id of securityRole"),
      	resolve = _.value.securityRoleId)
	, Field("name", StringType,	
        description = Some("Nombre"),
        resolve = _.value.name)
	, Field("entity", SecurityEntityType,	
        description = Some("Entidades"),
        resolve = c ⇒ securityEntityFetcher.defer(c.value.entity)
	  )
	, Field("operations", OptionType(ListType(SecurityOperationTypeType)),	
        description = Some("Operaciones permitidas"),
        arguments = Limit :: Offset :: Search :: Nil,
        		
        resolve = c => SecurityOperationTypeDeferred(c.value.operations, c arg Limit, c arg Offset, c arg Search)
	  )
	, Field("usingExpression", OptionType(StringType),	
        description = Some("Regla de filtrado"),
        resolve = _.value.usingExpression)
	, Field("checkExpression", OptionType(StringType),	
        description = Some("Regla de comprobación para escritura"),
        resolve = _.value.checkExpression)
    )
  )
  
  implicit val SecurityRoleType = ObjectType(
    "SecurityRole",
    "",

	fields[Unit, SecurityRole](
	  Field("id", IntType, 
      	description = Some("Id of SecurityRole"),
      	resolve = _.value.id)
	, Field("name", StringType,	
        description = Some("Nombre del perfil"),
        resolve = _.value.name)
	, Field("privileges", OptionType(ListType(SecurityAccessPrivilegeType)),	
        description = Some(""),
        arguments = Limit :: Offset :: Search :: Nil,
        		
        resolve = c => SecurityAccessPrivilegeDeferred(c.value.privileges, c arg Limit, c arg Offset, c arg Search)
	  )
	, Field("policies", OptionType(ListType(SecurityRowLevelPolicyType)),	
        description = Some(""),
        arguments = Limit :: Offset :: Search :: Nil,
        		
        resolve = c => SecurityRowLevelPolicyDeferred(c.value.policies, c arg Limit, c arg Offset, c arg Search)
	  )
	, Field("comments", OptionType(StringType),	
        description = Some(""),
        resolve = _.value.comments)
    )
  )
  
  implicit val SecurityUserType = ObjectType(
    "SecurityUser",
    "",

	fields[Unit, SecurityUser](
	  Field("id", IntType, 
      	description = Some("Id of SecurityUser"),
      	resolve = _.value.id)
	, Field("username", StringType,	
        description = Some("Nombre de usuario"),
        resolve = _.value.username)
	, Field("password", StringType,	
        description = Some("Contraseña de acceso"),
        resolve = _.value.password)
	, Field("roles", ListType(SecurityRoleType),	
        description = Some("Roles de seguridad"),
        arguments = Limit :: Offset :: Search :: Nil,
        		
        resolve = c => SecurityRoleDeferred(Some(c.value.roles), c arg Limit, c arg Offset, c arg Search)
	  )
	, Field("email", OptionType(StringType),	
        description = Some("Correo electrónico"),
        resolve = _.value.email)
	, Field("birthDate", OptionType(StringType),	
        description = Some("Fecha de nacimiento"),
        resolve = _.value.birthDate)
	, Field("photo", OptionType(StringType),	
        description = Some("Fotografía"),
        resolve = _.value.photo)
	, Field("identityCards", OptionType(ListType(StringType)),	
        description = Some("Documentos de identificación"),
        arguments = Limit :: Offset :: Search :: Nil,
        resolve = _.value.identityCards)
	, Field("presentationVideo", OptionType(StringType),	
        description = Some("Vídeo de presentación"),
        resolve = _.value.presentationVideo)
	, Field("teachingCenters", OptionType(ListType(TeachingCenterType)),	
        description = Some(""),
        arguments = Limit :: Offset :: Search :: Nil,
        		
        resolve = c => TeachingCenterDeferred(c.value.teachingCenters, c arg Limit, c arg Offset, c arg Search)
	  )
    )
  )
  
}
