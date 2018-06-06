package graphql

trait Event {
  def `type`: String
}

	
sealed trait ItemImageEvent extends Event

case class ItemImageUpdated (`type`: String, itemImage: ItemImage) extends ItemImageEvent
case class ItemImageDeleted(`type`: String, id: Int) extends ItemImageEvent
	
sealed trait ItemEvent extends Event

case class ItemUpdated (`type`: String, item: Item) extends ItemEvent
case class ItemDeleted(`type`: String, id: Int) extends ItemEvent
	
sealed trait MaterialEvent extends Event

case class MaterialUpdated (`type`: String, material: Material) extends MaterialEvent
case class MaterialDeleted(`type`: String, id: Int) extends MaterialEvent
	
sealed trait CourseEvent extends Event

case class CourseUpdated (`type`: String, course: Course) extends CourseEvent
case class CourseDeleted(`type`: String, id: Int) extends CourseEvent
	
sealed trait TeachingCenterEvent extends Event

case class TeachingCenterUpdated (`type`: String, teachingCenter: TeachingCenter) extends TeachingCenterEvent
case class TeachingCenterDeleted(`type`: String, id: Int) extends TeachingCenterEvent
	
sealed trait SchedulingProcessEvent extends Event

case class SchedulingProcessUpdated (`type`: String, schedulingProcess: SchedulingProcess) extends SchedulingProcessEvent
case class SchedulingProcessDeleted(`type`: String, id: Int) extends SchedulingProcessEvent
	
sealed trait SecurityAttributeEvent extends Event

case class SecurityAttributeUpdated (`type`: String, securityAttribute: SecurityAttribute) extends SecurityAttributeEvent
case class SecurityAttributeDeleted(`type`: String, id: Int) extends SecurityAttributeEvent
	
sealed trait SecurityEntityEvent extends Event

case class SecurityEntityUpdated (`type`: String, securityEntity: SecurityEntity) extends SecurityEntityEvent
case class SecurityEntityDeleted(`type`: String, id: Int) extends SecurityEntityEvent
	
sealed trait SecurityOperationTypeEvent extends Event

case class SecurityOperationTypeUpdated (`type`: String, securityOperationType: SecurityOperationType) extends SecurityOperationTypeEvent
case class SecurityOperationTypeDeleted(`type`: String, id: Int) extends SecurityOperationTypeEvent
	
sealed trait SecurityAccessPrivilegeEvent extends Event

case class SecurityAccessPrivilegeUpdated (`type`: String, securityAccessPrivilege: SecurityAccessPrivilege) extends SecurityAccessPrivilegeEvent
case class SecurityAccessPrivilegeDeleted(`type`: String, id: Int) extends SecurityAccessPrivilegeEvent
	
sealed trait SecurityRowLevelPolicyEvent extends Event

case class SecurityRowLevelPolicyUpdated (`type`: String, securityRowLevelPolicy: SecurityRowLevelPolicy) extends SecurityRowLevelPolicyEvent
case class SecurityRowLevelPolicyDeleted(`type`: String, id: Int) extends SecurityRowLevelPolicyEvent
	
sealed trait SecurityRoleEvent extends Event

case class SecurityRoleUpdated (`type`: String, securityRole: SecurityRole) extends SecurityRoleEvent
case class SecurityRoleDeleted(`type`: String, id: Int) extends SecurityRoleEvent
	
sealed trait SecurityUserEvent extends Event

case class SecurityUserUpdated (`type`: String, securityUser: SecurityUser) extends SecurityUserEvent
case class SecurityUserDeleted(`type`: String, id: Int) extends SecurityUserEvent
