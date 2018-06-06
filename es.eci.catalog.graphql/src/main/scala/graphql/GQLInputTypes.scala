package graphql

import sangria.macros.derive._
import sangria.schema._
import sangria.marshalling.CoercedScalaResultMarshaller
import sangria.marshalling.FromInput

import scalaz._
import Scalaz._

object GQLInputTypes {

  implicit val itemimages = new FromInput[ItemImage] {
    val marshaller = CoercedScalaResultMarshaller.default

    def fromResult(node: marshaller.Node) = {
      val ad = node.asInstanceOf[Map[String, Any]]

      ItemImage(
      	id = if (ad.contains("id")) ad("id").asInstanceOf[Int] else -1
      , itemId = ad("itemId").asInstanceOf[Int]
	      ,	altText = if (ad.contains("altText")) ad("altText").asInstanceOf[String] else ""
	      ,	image = if (ad.contains("image")) ad("image").asInstanceOf[String] else null
      )
    }
  }

  implicit val ItemImageInputType = InputObjectType[ItemImage](
    "ItemImageInput", "", List(
    InputField("id", OptionInputType(IntType), "Id de la entidad")
    , InputField("itemId", IntType, "Id de la entidad padre")
    , InputField("altText", StringType, "Texto alternativo de la imagen")
    , InputField("image", StringType, "Imagen")
    )
  )
  
  implicit val items = new FromInput[Item] {
    val marshaller = CoercedScalaResultMarshaller.default

    def fromResult(node: marshaller.Node) = {
      val ad = node.asInstanceOf[Map[String, Any]]

      Item(
      	id = if (ad.contains("id")) ad("id").asInstanceOf[Int] else -1
	      ,	name = if (ad.contains("name")) ad("name").asInstanceOf[String] else ""
	      ,	description = if (ad.contains("description")) ad("description").asInstanceOf[Option[String]] else null
	      ,	code = if (ad.contains("code")) ad("code").asInstanceOf[Option[String]] else Some("")
		  ,	images = if (ad.contains("images")) ad("images").asInstanceOf[Option[List[Int]]] else None
	      ,	price = if (ad.contains("price")) ad("price").asInstanceOf[Option[Double]] else Some(0.0)
	      ,	retailPrice = if (ad.contains("retailPrice")) ad("retailPrice").asInstanceOf[Option[Double]] else Some(0.0)
	      ,	costPrice = if (ad.contains("costPrice")) ad("costPrice").asInstanceOf[Option[Double]] else Some(0.0)
      )
    }
  }

  implicit val ItemInputType = InputObjectType[Item](
    "ItemInput", "", List(
    InputField("id", OptionInputType(IntType), "Id de la entidad")
    , InputField("name", StringType, "Nombre del producto")
    , InputField("description", OptionInputType(StringType), "Descripción")
    , InputField("code", OptionInputType(StringType), "Código del producto (GTIN, EAN, ISBN)")
    , InputField("images", OptionInputType(ListInputType(IntType)), "Imágenes")
    , InputField("price", OptionInputType(FloatType), "Precio")
    , InputField("retailPrice", OptionInputType(FloatType), "Precio de venta")
    , InputField("costPrice", OptionInputType(FloatType), "Precio de coste")
    )
  )
  
  implicit val materials = new FromInput[Material] {
    val marshaller = CoercedScalaResultMarshaller.default

    def fromResult(node: marshaller.Node) = {
      val ad = node.asInstanceOf[Map[String, Any]]

      Material(
      	id = if (ad.contains("id")) ad("id").asInstanceOf[Int] else -1
      , courseId = ad("courseId").asInstanceOf[Int]
	      ,	quantity = if (ad.contains("quantity")) ad("quantity").asInstanceOf[Double] else 0.0
		  ,	item = if (ad.contains("item")) ad("item").asInstanceOf[Int] else -1
      )
    }
  }

  implicit val MaterialInputType = InputObjectType[Material](
    "MaterialInput", "", List(
    InputField("id", OptionInputType(IntType), "Id de la entidad")
    , InputField("courseId", IntType, "Id de la entidad padre")
    , InputField("quantity", FloatType, "Cantidad")
    , InputField("item", ItemInputType, "Ítem (libro o material escolar)")
    )
  )
  
  implicit val courses = new FromInput[Course] {
    val marshaller = CoercedScalaResultMarshaller.default

    def fromResult(node: marshaller.Node) = {
      val ad = node.asInstanceOf[Map[String, Any]]

      Course(
      	id = if (ad.contains("id")) ad("id").asInstanceOf[Int] else -1
      , teachingCenterId = ad("teachingCenterId").asInstanceOf[Int]
	      ,	name = if (ad.contains("name")) ad("name").asInstanceOf[String] else ""
	      ,	cycle = if (ad.contains("cycle")) ad("cycle").asInstanceOf[String] else ""
		  ,	materials = if (ad.contains("materials")) ad("materials").asInstanceOf[Option[List[Int]]] else None
      )
    }
  }

  implicit val CourseInputType = InputObjectType[Course](
    "CourseInput", "", List(
    InputField("id", OptionInputType(IntType), "Id de la entidad")
    , InputField("teachingCenterId", IntType, "Id de la entidad padre")
    , InputField("name", StringType, "Nombre del curso")
    , InputField("cycle", StringType, "Ciclo formativo")
    , InputField("materials", OptionInputType(ListInputType(IntType)), "Libros y material escolar")
    )
  )
  
  implicit val teachingcenters = new FromInput[TeachingCenter] {
    val marshaller = CoercedScalaResultMarshaller.default

    def fromResult(node: marshaller.Node) = {
      val ad = node.asInstanceOf[Map[String, Any]]

      TeachingCenter(
      	id = if (ad.contains("id")) ad("id").asInstanceOf[Int] else -1
	      ,	name = if (ad.contains("name")) ad("name").asInstanceOf[String] else ""
	      ,	genericName = if (ad.contains("genericName")) ad("genericName").asInstanceOf[Option[String]] else Some("")
	      ,	email = if (ad.contains("email")) ad("email").asInstanceOf[Option[String]] else Some("")
	      ,	phone = if (ad.contains("phone")) ad("phone").asInstanceOf[Option[String]] else Some("")
	      ,	image = if (ad.contains("image")) ad("image").asInstanceOf[Option[String]] else null
	      ,	code = if (ad.contains("code")) ad("code").asInstanceOf[String] else ""
	      ,	`type` = if (ad.contains("type")) ad("type").asInstanceOf[Option[String]] else Some("")
	      ,	nature = if (ad.contains("nature")) ad("nature").asInstanceOf[Option[String]] else Some("")
	      ,	address = if (ad.contains("address")) ad("address").asInstanceOf[Option[String]] else Some("")
	      ,	formattedAddress = if (ad.contains("formattedAddress")) ad("formattedAddress").asInstanceOf[Option[String]] else Some("")
	      ,	postalCode = if (ad.contains("postalCode")) ad("postalCode").asInstanceOf[Option[String]] else Some("")
	      ,	town = if (ad.contains("town")) ad("town").asInstanceOf[Option[String]] else Some("")
	      ,	province = if (ad.contains("province")) ad("province").asInstanceOf[Option[String]] else Some("")
	      ,	autonomousRegion = if (ad.contains("autonomousRegion")) ad("autonomousRegion").asInstanceOf[Option[String]] else Some("")
	      ,	latitude = if (ad.contains("latitude")) ad("latitude").asInstanceOf[Option[Double]] else Some(0.0)
	      ,	longitude = if (ad.contains("longitude")) ad("longitude").asInstanceOf[Option[Double]] else Some(0.0)
		  ,	courses = if (ad.contains("courses")) ad("courses").asInstanceOf[Option[List[Int]]] else None
      )
    }
  }

  implicit val TeachingCenterInputType = InputObjectType[TeachingCenter](
    "TeachingCenterInput", "", List(
    InputField("id", OptionInputType(IntType), "Id de la entidad")
    , InputField("name", StringType, "Nombre del centro")
    , InputField("genericName", OptionInputType(StringType), "Denominación genérica del centro")
    , InputField("email", OptionInputType(StringType), "Dirección de correo electrónico")
    , InputField("phone", OptionInputType(StringType), "Teléfono")
    , InputField("image", OptionInputType(StringType), "Escudo / Logotipo")
    , InputField("code", StringType, "Código del centro")
    , InputField("type", OptionInputType(StringType), "Tipo de centro")
    , InputField("nature", OptionInputType(StringType), "Naturaleza")
    , InputField("address", OptionInputType(StringType), "Dirección")
    , InputField("formattedAddress", OptionInputType(StringType), "Dirección formateada")
    , InputField("postalCode", OptionInputType(StringType), "Código postal")
    , InputField("town", OptionInputType(StringType), "Localidad")
    , InputField("province", OptionInputType(StringType), "Provincia")
    , InputField("autonomousRegion", OptionInputType(StringType), "Comunidad autónoma")
    , InputField("latitude", OptionInputType(FloatType), "Latitud")
    , InputField("longitude", OptionInputType(FloatType), "Longitud")
    , InputField("courses", OptionInputType(ListInputType(IntType)), "Cursos")
    )
  )
  
  implicit val schedulingprocesses = new FromInput[SchedulingProcess] {
    val marshaller = CoercedScalaResultMarshaller.default

    def fromResult(node: marshaller.Node) = {
      val ad = node.asInstanceOf[Map[String, Any]]

      SchedulingProcess(
      	id = if (ad.contains("id")) ad("id").asInstanceOf[Int] else -1
	      ,	name = if (ad.contains("name")) ad("name").asInstanceOf[String] else ""
	      ,	cronExpression = if (ad.contains("cronExpression")) ad("cronExpression").asInstanceOf[String] else ""
	      ,	script = if (ad.contains("script")) ad("script").asInstanceOf[String] else ""
      )
    }
  }

  implicit val SchedulingProcessInputType = InputObjectType[SchedulingProcess](
    "SchedulingProcessInput", "", List(
    InputField("id", OptionInputType(IntType), "Id de la entidad")
    , InputField("name", StringType, "Nombre del proceso")
    , InputField("cronExpression", StringType, "Expresión de planificación (CRON)")
    , InputField("script", StringType, "Script (JavaScript)")
    )
  )
  
  implicit val securityattributes = new FromInput[SecurityAttribute] {
    val marshaller = CoercedScalaResultMarshaller.default

    def fromResult(node: marshaller.Node) = {
      val ad = node.asInstanceOf[Map[String, Any]]

      SecurityAttribute(
      	id = if (ad.contains("id")) ad("id").asInstanceOf[Int] else -1
      , securityEntityId = ad("securityEntityId").asInstanceOf[Int]
	      ,	name = if (ad.contains("name")) ad("name").asInstanceOf[String] else ""
      )
    }
  }

  implicit val SecurityAttributeInputType = InputObjectType[SecurityAttribute](
    "SecurityAttributeInput", "", List(
    InputField("id", OptionInputType(IntType), "Id de la entidad")
    , InputField("securityEntityId", IntType, "Id de la entidad padre")
    , InputField("name", StringType, "Nombre del atributo")
    )
  )
  
  implicit val securityentities = new FromInput[SecurityEntity] {
    val marshaller = CoercedScalaResultMarshaller.default

    def fromResult(node: marshaller.Node) = {
      val ad = node.asInstanceOf[Map[String, Any]]

      SecurityEntity(
      	id = if (ad.contains("id")) ad("id").asInstanceOf[Int] else -1
	      ,	name = if (ad.contains("name")) ad("name").asInstanceOf[String] else ""
		  ,	attributes = if (ad.contains("attributes")) ad("attributes").asInstanceOf[Option[List[Int]]] else None
      )
    }
  }

  implicit val SecurityEntityInputType = InputObjectType[SecurityEntity](
    "SecurityEntityInput", "", List(
    InputField("id", OptionInputType(IntType), "Id de la entidad")
    , InputField("name", StringType, "Nombre de la entidad")
    , InputField("attributes", OptionInputType(ListInputType(IntType)), "")
    )
  )
  
  implicit val securityoperationtypes = new FromInput[SecurityOperationType] {
    val marshaller = CoercedScalaResultMarshaller.default

    def fromResult(node: marshaller.Node) = {
      val ad = node.asInstanceOf[Map[String, Any]]

      SecurityOperationType(
      	id = if (ad.contains("id")) ad("id").asInstanceOf[Int] else -1
	      ,	name = if (ad.contains("name")) ad("name").asInstanceOf[String] else ""
      )
    }
  }

  implicit val SecurityOperationTypeInputType = InputObjectType[SecurityOperationType](
    "SecurityOperationTypeInput", "", List(
    InputField("id", OptionInputType(IntType), "Id de la entidad")
    , InputField("name", StringType, "Nombre")
    )
  )
  
  implicit val securityaccessprivileges = new FromInput[SecurityAccessPrivilege] {
    val marshaller = CoercedScalaResultMarshaller.default

    def fromResult(node: marshaller.Node) = {
      val ad = node.asInstanceOf[Map[String, Any]]

      SecurityAccessPrivilege(
      	id = if (ad.contains("id")) ad("id").asInstanceOf[Int] else -1
      , securityRoleId = ad("securityRoleId").asInstanceOf[Int]
	      ,	name = if (ad.contains("name")) ad("name").asInstanceOf[String] else ""
		  ,	operations = if (ad.contains("operations")) ad("operations").asInstanceOf[Option[List[Int]]] else None
		  ,	entities = if (ad.contains("entities")) ad("entities").asInstanceOf[List[Int]] else List(-1)
		  ,	attributes = if (ad.contains("attributes")) ad("attributes").asInstanceOf[Option[List[Int]]] else None
      )
    }
  }

  implicit val SecurityAccessPrivilegeInputType = InputObjectType[SecurityAccessPrivilege](
    "SecurityAccessPrivilegeInput", "", List(
    InputField("id", OptionInputType(IntType), "Id de la entidad")
    , InputField("securityRoleId", IntType, "Id de la entidad padre")
    , InputField("name", StringType, "Nombre")
    , InputField("operations", OptionInputType(ListInputType(IntType)), "Operaciones permitidas")
    , InputField("entities", ListInputType(IntType), "Entidades")
    , InputField("attributes", OptionInputType(ListInputType(IntType)), "Atributos")
    )
  )
  
  implicit val securityrowlevelpolicies = new FromInput[SecurityRowLevelPolicy] {
    val marshaller = CoercedScalaResultMarshaller.default

    def fromResult(node: marshaller.Node) = {
      val ad = node.asInstanceOf[Map[String, Any]]

      SecurityRowLevelPolicy(
      	id = if (ad.contains("id")) ad("id").asInstanceOf[Int] else -1
      , securityRoleId = ad("securityRoleId").asInstanceOf[Int]
	      ,	name = if (ad.contains("name")) ad("name").asInstanceOf[String] else ""
		  ,	entity = if (ad.contains("entity")) ad("entity").asInstanceOf[Int] else -1
		  ,	operations = if (ad.contains("operations")) ad("operations").asInstanceOf[Option[List[Int]]] else None
	      ,	usingExpression = if (ad.contains("usingExpression")) ad("usingExpression").asInstanceOf[Option[String]] else Some("")
	      ,	checkExpression = if (ad.contains("checkExpression")) ad("checkExpression").asInstanceOf[Option[String]] else Some("")
      )
    }
  }

  implicit val SecurityRowLevelPolicyInputType = InputObjectType[SecurityRowLevelPolicy](
    "SecurityRowLevelPolicyInput", "", List(
    InputField("id", OptionInputType(IntType), "Id de la entidad")
    , InputField("securityRoleId", IntType, "Id de la entidad padre")
    , InputField("name", StringType, "Nombre")
    , InputField("entity", SecurityEntityInputType, "Entidades")
    , InputField("operations", OptionInputType(ListInputType(IntType)), "Operaciones permitidas")
    , InputField("usingExpression", OptionInputType(StringType), "Regla de filtrado")
    , InputField("checkExpression", OptionInputType(StringType), "Regla de comprobación para escritura")
    )
  )
  
  implicit val securityroles = new FromInput[SecurityRole] {
    val marshaller = CoercedScalaResultMarshaller.default

    def fromResult(node: marshaller.Node) = {
      val ad = node.asInstanceOf[Map[String, Any]]

      SecurityRole(
      	id = if (ad.contains("id")) ad("id").asInstanceOf[Int] else -1
	      ,	name = if (ad.contains("name")) ad("name").asInstanceOf[String] else ""
		  ,	privileges = if (ad.contains("privileges")) ad("privileges").asInstanceOf[Option[List[Int]]] else None
		  ,	policies = if (ad.contains("policies")) ad("policies").asInstanceOf[Option[List[Int]]] else None
	      ,	comments = if (ad.contains("comments")) ad("comments").asInstanceOf[Option[String]] else Some("")
      )
    }
  }

  implicit val SecurityRoleInputType = InputObjectType[SecurityRole](
    "SecurityRoleInput", "", List(
    InputField("id", OptionInputType(IntType), "Id de la entidad")
    , InputField("name", StringType, "Nombre del perfil")
    , InputField("privileges", OptionInputType(ListInputType(IntType)), "")
    , InputField("policies", OptionInputType(ListInputType(IntType)), "")
    , InputField("comments", OptionInputType(StringType), "")
    )
  )
  
  implicit val securityusers = new FromInput[SecurityUser] {
    val marshaller = CoercedScalaResultMarshaller.default

    def fromResult(node: marshaller.Node) = {
      val ad = node.asInstanceOf[Map[String, Any]]

      SecurityUser(
      	id = if (ad.contains("id")) ad("id").asInstanceOf[Int] else -1
	      ,	username = if (ad.contains("username")) ad("username").asInstanceOf[String] else ""
	      ,	password = if (ad.contains("password")) ad("password").asInstanceOf[String] else null
		  ,	roles = if (ad.contains("roles")) ad("roles").asInstanceOf[List[Int]] else List(-1)
	      ,	email = if (ad.contains("email")) ad("email").asInstanceOf[Option[String]] else Some("")
	      ,	birthDate = if (ad.contains("birthDate")) ad("birthDate").asInstanceOf[Option[String]] else null
	      ,	photo = if (ad.contains("photo")) ad("photo").asInstanceOf[Option[String]] else null
	      ,	identityCards = if (ad.contains("identityCards")) ad("identityCards").asInstanceOf[Option[List[String]]] else null
	      ,	presentationVideo = if (ad.contains("presentationVideo")) ad("presentationVideo").asInstanceOf[Option[String]] else null
		  ,	teachingCenters = if (ad.contains("teachingCenters")) ad("teachingCenters").asInstanceOf[Option[List[Int]]] else None
      )
    }
  }

  implicit val SecurityUserInputType = InputObjectType[SecurityUser](
    "SecurityUserInput", "", List(
    InputField("id", OptionInputType(IntType), "Id de la entidad")
    , InputField("username", StringType, "Nombre de usuario")
    , InputField("password", StringType, "Contraseña de acceso")
    , InputField("roles", ListInputType(IntType), "Roles de seguridad")
    , InputField("email", OptionInputType(StringType), "Correo electrónico")
    , InputField("birthDate", OptionInputType(StringType), "Fecha de nacimiento")
    , InputField("photo", OptionInputType(StringType), "Fotografía")
    , InputField("identityCards", OptionInputType(ListInputType(StringType)), "Documentos de identificación")
    , InputField("presentationVideo", OptionInputType(StringType), "Vídeo de presentación")
    , InputField("teachingCenters", OptionInputType(ListInputType(IntType)), "")
    )
  )
  
}
