CREATE EXTENSION IF NOT EXISTS unaccent;

ALTER TEXT SEARCH CONFIGURATION spanish
  ALTER MAPPING FOR hword, hword_part, word WITH unaccent, spanish_stem;

ALTER TABLE IF EXISTS item_image_ 
  DROP CONSTRAINT item_image_master_;

ALTER TABLE IF EXISTS material_ 
  DROP CONSTRAINT material_master_;

ALTER TABLE IF EXISTS material_ 
  DROP CONSTRAINT material_item_; 

ALTER TABLE IF EXISTS course_ 
  DROP CONSTRAINT course_master_;

ALTER TABLE IF EXISTS security_attribute_ 
  DROP CONSTRAINT security_attribute_master_;

ALTER TABLE IF EXISTS security_access_privilege_ 
  DROP CONSTRAINT security_access_privilege_master_;

ALTER TABLE IF EXISTS security_row_level_policy_ 
  DROP CONSTRAINT security_row_level_policy_master_;

ALTER TABLE IF EXISTS security_row_level_policy_ 
  DROP CONSTRAINT security_row_level_policy_entity_; 

DROP TRIGGER IF EXISTS item_image_search_trigger_ ON item_image_;

DROP TRIGGER IF EXISTS item_search_trigger_ ON item_;

DROP TRIGGER IF EXISTS course_search_trigger_ ON course_;

DROP TRIGGER IF EXISTS teaching_center_search_trigger_ ON teaching_center_;

DROP TRIGGER IF EXISTS scheduling_process_search_trigger_ ON scheduling_process_;

DROP TRIGGER IF EXISTS security_attribute_search_trigger_ ON security_attribute_;

DROP TRIGGER IF EXISTS security_entity_search_trigger_ ON security_entity_;

DROP TRIGGER IF EXISTS security_operation_type_search_trigger_ ON security_operation_type_;

DROP TRIGGER IF EXISTS security_access_privilege_search_trigger_ ON security_access_privilege_;

DROP TRIGGER IF EXISTS security_row_level_policy_search_trigger_ ON security_row_level_policy_;

DROP TRIGGER IF EXISTS security_role_search_trigger_ ON security_role_;

DROP TRIGGER IF EXISTS security_user_search_trigger_ ON security_user_;

DROP INDEX IF EXISTS item_image_master_index_;

DROP INDEX IF EXISTS item_image_alt_text_index_;

DROP INDEX IF EXISTS item_name_index_;

DROP INDEX IF EXISTS item_code_index_;

DROP INDEX IF EXISTS item_price_index_;

DROP INDEX IF EXISTS material_master_index_;

DROP INDEX IF EXISTS material_quantity_index_;

DROP INDEX IF EXISTS material_item_index_;

DROP INDEX IF EXISTS course_master_index_;

DROP INDEX IF EXISTS course_name_index_;

DROP INDEX IF EXISTS course_cycle_index_;

DROP INDEX IF EXISTS teaching_center_name_index_;

DROP INDEX IF EXISTS teaching_center_email_index_;

DROP INDEX IF EXISTS teaching_center_code_index_;

DROP INDEX IF EXISTS scheduling_process_name_index_;

DROP INDEX IF EXISTS security_attribute_master_index_;

DROP INDEX IF EXISTS security_attribute_name_index_;

DROP INDEX IF EXISTS security_entity_name_index_;

DROP INDEX IF EXISTS security_operation_type_name_index_;

DROP INDEX IF EXISTS security_access_privilege_master_index_;

DROP INDEX IF EXISTS security_access_privilege_name_index_;

DROP INDEX IF EXISTS security_row_level_policy_master_index_;

DROP INDEX IF EXISTS security_row_level_policy_name_index_;

DROP INDEX IF EXISTS security_role_name_index_;

DROP INDEX IF EXISTS security_user_username_index_;

DROP INDEX IF EXISTS security_user_roles_index_;

DROP INDEX IF EXISTS security_user_email_index_;

DROP TABLE IF EXISTS item_image_;

DROP TABLE IF EXISTS item_;

DROP TABLE IF EXISTS material_;

DROP TABLE IF EXISTS course_;

DROP TABLE IF EXISTS teaching_center_;

DROP TABLE IF EXISTS scheduling_process_;

DROP TABLE IF EXISTS security_attribute_;

DROP TABLE IF EXISTS security_entity_;

DROP TABLE IF EXISTS security_operation_type_;

DROP TABLE IF EXISTS security_access_privilege_;

DROP TABLE IF EXISTS security_row_level_policy_;

DROP TABLE IF EXISTS security_role_;

DROP TABLE IF EXISTS security_user_;
DROP TYPE IF EXISTS person_;

CREATE OR REPLACE FUNCTION update_last_modified_column()
  RETURNS TRIGGER AS $$
  BEGIN
    new._last_modified_ = now();
    RETURN new;
  END;
  $$ language 'plpgsql';

CREATE TABLE item_image_ (
  _id_ SERIAL NOT NULL PRIMARY KEY, 
  _item_id_ integer NOT NULL,
  alt_text_ varchar, 
  image_ varchar, 
  _search_ tsvector,
  _types_ varchar[],
  _last_modified_ varchar DEFAULT current_timestamp,
  _version_ integer DEFAULT 1
);


CREATE TRIGGER item_image_update_last_modified_
  BEFORE UPDATE 
  ON item_image_ 
  FOR EACH ROW 
  EXECUTE PROCEDURE update_last_modified_column();

CREATE TABLE item_ (
  _id_ SERIAL NOT NULL PRIMARY KEY, 
  name_ varchar, 
  description_ varchar, 
  code_ varchar, 
  price_ double precision, 
  retail_price_ double precision, 
  cost_price_ double precision, 
  _search_ tsvector,
  _types_ varchar[],
  _last_modified_ varchar DEFAULT current_timestamp,
  _version_ integer DEFAULT 1
);


CREATE TRIGGER item_update_last_modified_
  BEFORE UPDATE 
  ON item_ 
  FOR EACH ROW 
  EXECUTE PROCEDURE update_last_modified_column();

CREATE TABLE material_ (
  _id_ SERIAL NOT NULL PRIMARY KEY, 
  _course_id_ integer NOT NULL,
  quantity_ integer, 
  item_ integer, 
  _search_ tsvector,
  _types_ varchar[],
  _last_modified_ varchar DEFAULT current_timestamp,
  _version_ integer DEFAULT 1
);


CREATE TRIGGER material_update_last_modified_
  BEFORE UPDATE 
  ON material_ 
  FOR EACH ROW 
  EXECUTE PROCEDURE update_last_modified_column();

CREATE TABLE course_ (
  _id_ SERIAL NOT NULL PRIMARY KEY, 
  _teaching_center_id_ integer NOT NULL,
  name_ varchar, 
  cycle_ varchar, 
  _search_ tsvector,
  _types_ varchar[],
  _last_modified_ varchar DEFAULT current_timestamp,
  _version_ integer DEFAULT 1
);


CREATE TRIGGER course_update_last_modified_
  BEFORE UPDATE 
  ON course_ 
  FOR EACH ROW 
  EXECUTE PROCEDURE update_last_modified_column();

CREATE TABLE teaching_center_ (
  _id_ SERIAL NOT NULL PRIMARY KEY, 
  name_ varchar, 
  generic_name_ varchar, 
  email_ varchar, 
  phone_ varchar, 
  image_ varchar, 
  code_ varchar, 
  type_ varchar, 
  nature_ varchar, 
  address_ varchar, 
  formatted_address_ varchar, 
  postal_code_ varchar, 
  town_ varchar, 
  province_ varchar, 
  autonomous_region_ varchar, 
  latitude_ double precision, 
  longitude_ double precision, 
  _search_ tsvector,
  _types_ varchar[],
  _last_modified_ varchar DEFAULT current_timestamp,
  _version_ integer DEFAULT 1
);


CREATE TRIGGER teaching_center_update_last_modified_
  BEFORE UPDATE 
  ON teaching_center_ 
  FOR EACH ROW 
  EXECUTE PROCEDURE update_last_modified_column();

CREATE TABLE scheduling_process_ (
  _id_ SERIAL NOT NULL PRIMARY KEY, 
  name_ varchar, 
  cron_expression_ varchar, 
  script_ varchar, 
  _search_ tsvector,
  _types_ varchar[],
  _last_modified_ varchar DEFAULT current_timestamp,
  _version_ integer DEFAULT 1
);


CREATE TRIGGER scheduling_process_update_last_modified_
  BEFORE UPDATE 
  ON scheduling_process_ 
  FOR EACH ROW 
  EXECUTE PROCEDURE update_last_modified_column();

CREATE TABLE security_attribute_ (
  _id_ SERIAL NOT NULL PRIMARY KEY, 
  _security_entity_id_ integer NOT NULL,
  name_ varchar, 
  _search_ tsvector,
  _types_ varchar[],
  _last_modified_ varchar DEFAULT current_timestamp,
  _version_ integer DEFAULT 1
);


CREATE TRIGGER security_attribute_update_last_modified_
  BEFORE UPDATE 
  ON security_attribute_ 
  FOR EACH ROW 
  EXECUTE PROCEDURE update_last_modified_column();

CREATE TABLE security_entity_ (
  _id_ SERIAL NOT NULL PRIMARY KEY, 
  name_ varchar, 
  _search_ tsvector,
  _types_ varchar[],
  _last_modified_ varchar DEFAULT current_timestamp,
  _version_ integer DEFAULT 1
);


CREATE TRIGGER security_entity_update_last_modified_
  BEFORE UPDATE 
  ON security_entity_ 
  FOR EACH ROW 
  EXECUTE PROCEDURE update_last_modified_column();

CREATE TABLE security_operation_type_ (
  _id_ SERIAL NOT NULL PRIMARY KEY, 
  name_ varchar, 
  _search_ tsvector,
  _types_ varchar[],
  _last_modified_ varchar DEFAULT current_timestamp,
  _version_ integer DEFAULT 1
);


CREATE TRIGGER security_operation_type_update_last_modified_
  BEFORE UPDATE 
  ON security_operation_type_ 
  FOR EACH ROW 
  EXECUTE PROCEDURE update_last_modified_column();

CREATE TABLE security_access_privilege_ (
  _id_ SERIAL NOT NULL PRIMARY KEY, 
  _security_role_id_ integer NOT NULL,
  name_ varchar, 
  operations_ integer[], 
  entities_ integer[], 
  attributes_ integer[], 
  _search_ tsvector,
  _types_ varchar[],
  _last_modified_ varchar DEFAULT current_timestamp,
  _version_ integer DEFAULT 1
);


CREATE TRIGGER security_access_privilege_update_last_modified_
  BEFORE UPDATE 
  ON security_access_privilege_ 
  FOR EACH ROW 
  EXECUTE PROCEDURE update_last_modified_column();

CREATE TABLE security_row_level_policy_ (
  _id_ SERIAL NOT NULL PRIMARY KEY, 
  _security_role_id_ integer NOT NULL,
  name_ varchar, 
  entity_ integer, 
  operations_ integer[], 
  using_expression_ varchar, 
  check_expression_ varchar, 
  _search_ tsvector,
  _types_ varchar[],
  _last_modified_ varchar DEFAULT current_timestamp,
  _version_ integer DEFAULT 1
);


CREATE TRIGGER security_row_level_policy_update_last_modified_
  BEFORE UPDATE 
  ON security_row_level_policy_ 
  FOR EACH ROW 
  EXECUTE PROCEDURE update_last_modified_column();

CREATE TABLE security_role_ (
  _id_ SERIAL NOT NULL PRIMARY KEY, 
  name_ varchar, 
  comments_ varchar, 
  _search_ tsvector,
  _types_ varchar[],
  _last_modified_ varchar DEFAULT current_timestamp,
  _version_ integer DEFAULT 1
);


CREATE TRIGGER security_role_update_last_modified_
  BEFORE UPDATE 
  ON security_role_ 
  FOR EACH ROW 
  EXECUTE PROCEDURE update_last_modified_column();

CREATE TYPE person_ AS (
  email_ varchar, 
  birth_date_ varchar, 
  photo_ varchar, 
  identity_cards_ varchar[], 
  presentation_video_ varchar
);
CREATE TABLE security_user_ (
  _id_ SERIAL NOT NULL PRIMARY KEY, 
  username_ varchar, 
  password_ varchar, 
  roles_ integer[], 
  teaching_centers_ integer[], 
  person_ person_, 
  _search_ tsvector,
  _types_ varchar[],
  _last_modified_ varchar DEFAULT current_timestamp,
  _version_ integer DEFAULT 1
);


CREATE TRIGGER security_user_update_last_modified_
  BEFORE UPDATE 
  ON security_user_ 
  FOR EACH ROW 
  EXECUTE PROCEDURE update_last_modified_column();

CREATE INDEX item_image_master_index_
  ON item_image_(_item_id_);

CREATE INDEX item_image_alt_text_index_
  ON item_image_(alt_text_);

CREATE INDEX item_name_index_
  ON item_(name_);

CREATE INDEX item_code_index_
  ON item_(code_);

CREATE INDEX item_price_index_
  ON item_(price_);

CREATE INDEX material_master_index_
  ON material_(_course_id_);

CREATE INDEX material_quantity_index_
  ON material_(quantity_);

CREATE INDEX material_item_index_
  ON material_(item_);

CREATE INDEX course_master_index_
  ON course_(_teaching_center_id_);

CREATE INDEX course_name_index_
  ON course_(name_);

CREATE INDEX course_cycle_index_
  ON course_(cycle_);

CREATE INDEX teaching_center_name_index_
  ON teaching_center_(name_);

CREATE INDEX teaching_center_email_index_
  ON teaching_center_(email_);

CREATE INDEX teaching_center_code_index_
  ON teaching_center_(code_);

CREATE INDEX scheduling_process_name_index_
  ON scheduling_process_(name_);

CREATE INDEX security_attribute_master_index_
  ON security_attribute_(_security_entity_id_);

CREATE INDEX security_attribute_name_index_
  ON security_attribute_(name_);

CREATE INDEX security_entity_name_index_
  ON security_entity_(name_);

CREATE INDEX security_operation_type_name_index_
  ON security_operation_type_(name_);

CREATE INDEX security_access_privilege_master_index_
  ON security_access_privilege_(_security_role_id_);

CREATE INDEX security_access_privilege_name_index_
  ON security_access_privilege_(name_);

CREATE INDEX security_row_level_policy_master_index_
  ON security_row_level_policy_(_security_role_id_);

CREATE INDEX security_row_level_policy_name_index_
  ON security_row_level_policy_(name_);

CREATE INDEX security_role_name_index_
  ON security_role_(name_);

CREATE INDEX security_user_username_index_
  ON security_user_(username_);

CREATE INDEX security_user_roles_index_
  ON security_user_(roles_);

CREATE TRIGGER item_image_search_trigger_
  BEFORE INSERT OR UPDATE 
  ON item_image_ 
  FOR EACH ROW 
  EXECUTE PROCEDURE tsvector_update_trigger(_search_, 'pg_catalog.spanish', alt_text_);

CREATE INDEX item_image_search_index_ 
  ON item_image_
  USING gin(_search_);

CREATE TRIGGER item_search_trigger_
  BEFORE INSERT OR UPDATE 
  ON item_ 
  FOR EACH ROW 
  EXECUTE PROCEDURE tsvector_update_trigger(_search_, 'pg_catalog.spanish', name_, description_, code_);

CREATE INDEX item_search_index_ 
  ON item_
  USING gin(_search_);

CREATE TRIGGER course_search_trigger_
  BEFORE INSERT OR UPDATE 
  ON course_ 
  FOR EACH ROW 
  EXECUTE PROCEDURE tsvector_update_trigger(_search_, 'pg_catalog.spanish', name_, cycle_);

CREATE INDEX course_search_index_ 
  ON course_
  USING gin(_search_);

CREATE TRIGGER teaching_center_search_trigger_
  BEFORE INSERT OR UPDATE 
  ON teaching_center_ 
  FOR EACH ROW 
  EXECUTE PROCEDURE tsvector_update_trigger(_search_, 'pg_catalog.spanish', name_, generic_name_, email_, phone_, code_, type_, nature_, address_, formatted_address_, postal_code_, town_, province_);

CREATE INDEX teaching_center_search_index_ 
  ON teaching_center_
  USING gin(_search_);

CREATE TRIGGER scheduling_process_search_trigger_
  BEFORE INSERT OR UPDATE 
  ON scheduling_process_ 
  FOR EACH ROW 
  EXECUTE PROCEDURE tsvector_update_trigger(_search_, 'pg_catalog.spanish', name_);

CREATE INDEX scheduling_process_search_index_ 
  ON scheduling_process_
  USING gin(_search_);

CREATE TRIGGER security_attribute_search_trigger_
  BEFORE INSERT OR UPDATE 
  ON security_attribute_ 
  FOR EACH ROW 
  EXECUTE PROCEDURE tsvector_update_trigger(_search_, 'pg_catalog.spanish', name_);

CREATE INDEX security_attribute_search_index_ 
  ON security_attribute_
  USING gin(_search_);

CREATE TRIGGER security_entity_search_trigger_
  BEFORE INSERT OR UPDATE 
  ON security_entity_ 
  FOR EACH ROW 
  EXECUTE PROCEDURE tsvector_update_trigger(_search_, 'pg_catalog.spanish', name_);

CREATE INDEX security_entity_search_index_ 
  ON security_entity_
  USING gin(_search_);

CREATE TRIGGER security_operation_type_search_trigger_
  BEFORE INSERT OR UPDATE 
  ON security_operation_type_ 
  FOR EACH ROW 
  EXECUTE PROCEDURE tsvector_update_trigger(_search_, 'pg_catalog.spanish', name_);

CREATE INDEX security_operation_type_search_index_ 
  ON security_operation_type_
  USING gin(_search_);

CREATE TRIGGER security_access_privilege_search_trigger_
  BEFORE INSERT OR UPDATE 
  ON security_access_privilege_ 
  FOR EACH ROW 
  EXECUTE PROCEDURE tsvector_update_trigger(_search_, 'pg_catalog.spanish', name_);

CREATE INDEX security_access_privilege_search_index_ 
  ON security_access_privilege_
  USING gin(_search_);

CREATE TRIGGER security_row_level_policy_search_trigger_
  BEFORE INSERT OR UPDATE 
  ON security_row_level_policy_ 
  FOR EACH ROW 
  EXECUTE PROCEDURE tsvector_update_trigger(_search_, 'pg_catalog.spanish', name_);

CREATE INDEX security_row_level_policy_search_index_ 
  ON security_row_level_policy_
  USING gin(_search_);

CREATE TRIGGER security_role_search_trigger_
  BEFORE INSERT OR UPDATE 
  ON security_role_ 
  FOR EACH ROW 
  EXECUTE PROCEDURE tsvector_update_trigger(_search_, 'pg_catalog.spanish', name_);

CREATE INDEX security_role_search_index_ 
  ON security_role_
  USING gin(_search_);

CREATE TRIGGER security_user_search_trigger_
  BEFORE INSERT OR UPDATE 
  ON security_user_ 
  FOR EACH ROW 
  EXECUTE PROCEDURE tsvector_update_trigger(_search_, 'pg_catalog.spanish', username_);

CREATE INDEX security_user_search_index_ 
  ON security_user_
  USING gin(_search_);

ALTER TABLE item_image_ 
  ADD CONSTRAINT item_image_master_
  FOREIGN KEY (_item_id_) REFERENCES item_(_id_)
  DEFERRABLE;

ALTER TABLE material_ 
  ADD CONSTRAINT material_master_
  FOREIGN KEY (_course_id_) REFERENCES course_(_id_)
  DEFERRABLE;

ALTER TABLE material_ 
  ADD CONSTRAINT material_item_
  FOREIGN KEY (item_) REFERENCES item_(_id_)
  DEFERRABLE;

ALTER TABLE course_ 
  ADD CONSTRAINT course_master_
  FOREIGN KEY (_teaching_center_id_) REFERENCES teaching_center_(_id_)
  DEFERRABLE;

ALTER TABLE security_attribute_ 
  ADD CONSTRAINT security_attribute_master_
  FOREIGN KEY (_security_entity_id_) REFERENCES security_entity_(_id_)
  DEFERRABLE;

ALTER TABLE security_access_privilege_ 
  ADD CONSTRAINT security_access_privilege_master_
  FOREIGN KEY (_security_role_id_) REFERENCES security_role_(_id_)
  DEFERRABLE;

ALTER TABLE security_row_level_policy_ 
  ADD CONSTRAINT security_row_level_policy_master_
  FOREIGN KEY (_security_role_id_) REFERENCES security_role_(_id_)
  DEFERRABLE;

ALTER TABLE security_row_level_policy_ 
  ADD CONSTRAINT security_row_level_policy_entity_
  FOREIGN KEY (entity_) REFERENCES security_entity_(_id_)
  DEFERRABLE;


INSERT INTO security_operation_type_ (
    name_,
	_types_
  ) 
  SELECT
    'SELECT',
    '{"SecurityOperationType"}'
  ;

INSERT INTO security_operation_type_ (
    name_,
	_types_
  ) 
  SELECT
    'INSERT',
    '{"SecurityOperationType"}'
  ;

INSERT INTO security_operation_type_ (
    name_,
	_types_
  ) 
  SELECT
    'UPDATE',
    '{"SecurityOperationType"}'
  ;

INSERT INTO security_operation_type_ (
    name_,
	_types_
  ) 
  SELECT
    'DELETE',
    '{"SecurityOperationType"}'
  ;

INSERT INTO security_role_ (
    name_,
	_types_
  ) 
  SELECT
    'Administrator',
    '{"SecurityRole"}'
  ;

INSERT INTO security_user_ (
    username_,
    password_,
    roles_,
	_types_
  ) 
  SELECT
    'admin',
    'admin',
    array_agg((SELECT _id_ FROM security_role_ WHERE name_ = 'Administrator')),
    '{"SecurityUser"}'
  ;
