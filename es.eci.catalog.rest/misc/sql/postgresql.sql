
create extension if not exists unaccent;
alter text search configuration spanish
  alter mapping for hword, hword_part, word with unaccent, spanish_stem;
alter table if exists CATEGORIES 
  drop constraint CATEGORIES_PARENT_CATEGORY; 

alter table if exists CATEGORIES 
  drop constraint CATEGORIES_REFERENCE; 

alter table if exists ITEMS 
  drop constraint ITEMS_CATEGORY; 

alter table if exists ITEMS 
  drop constraint ITEMS_BRAND; 

alter table if exists ITEMS 
  drop constraint ITEMS_MAIN_PRODUCT; 

alter table if exists SUPPLIER_ITEMS 
  drop constraint SUPPLIER_ITEMS_SUPPLIER; 

alter table if exists SUPPLIER_ITEMS 
  drop constraint SUPPLIER_ITEMS_ITEM; 

alter table if exists GLOSSARIES 
  drop constraint GLOSSARIES_REFERENCE; 

alter table if exists LKUPS 
  drop constraint LKUPS_DESCRIPTIONS; 

alter table if exists LKUPS 
  drop constraint LKUPS_GLOSSARY; 

alter table if exists LKUPS 
  drop constraint LKUPS_REFERENCE; 

drop trigger if exists BRANDS_SEARCH_TRG on BRANDS;
drop trigger if exists ITEMS_SEARCH_TRG on ITEMS;
drop trigger if exists RELATED_ITEMS_SEARCH_TRG on RELATED_ITEMS;
drop trigger if exists SUPPLIERS_SEARCH_TRG on SUPPLIERS;

drop index if exists BRANDS_NAME_IDX;
drop index if exists ITEMS_NAME_IDX;
drop index if exists ITEMS_PRODUCT_TYPE_IDX;
drop index if exists ITEMS_COLOR_IDX;
drop index if exists ITEMS_SIZE_IDX;
drop index if exists RELATED_ITEMS_ITEMS_IDX;
drop index if exists SUPPLIERS_NAME_IDX;
drop index if exists SUPPLIER_ITEMS_SUPPLIER_IDX;
drop index if exists SUPPLIER_ITEMS_ITEM_IDX;
drop index if exists SUPPLIER_ITEMS_PRICE_IDX;

drop table if exists MAPS;
drop table if exists LINKS;
drop table if exists GLOSSARY_VALUES;
drop table if exists BRANDS;
drop table if exists CATEGORIES;
drop table if exists IMAGES;
drop table if exists VIDEOS;
drop table if exists PRODUCT_TYPES;
drop table if exists ITEMS;
drop table if exists GENDER_TARGETS;
drop table if exists RELATED_ITEMS;
drop table if exists SUPPLIERS;
drop table if exists SUPPLIER_ITEMS;
drop table if exists GLOSSARIES;
drop table if exists LKUPS;
drop table if exists EANS;

create or replace function update_last_modified_column()
  returns trigger as $$
  begin
    new._LAST_MODIFIED = now();
    return new;
  end;
  $$ language 'plpgsql';

create table MAPS (
  _ID serial not null primary key, 
  KEY varchar,
  VALUE varchar,
  _SEARCH tsvector,
  _TYPES varchar[],
  _LAST_MODIFIED varchar default current_timestamp,
  _VERSION integer DEFAULT 1);

create trigger MAPS_UPDATE_LAST_MODIFIED
  before update 
  on MAPS
  for each row 
  execute procedure update_last_modified_column();

create table LINKS (
  _ID serial not null primary key, 
  ID_EXT varchar,
  HIERARCHY_ID varchar,
  CATEGORY_ID varchar,
  _SEARCH tsvector,
  _TYPES varchar[],
  _LAST_MODIFIED varchar default current_timestamp,
  _VERSION integer DEFAULT 1);

create trigger LINKS_UPDATE_LAST_MODIFIED
  before update 
  on LINKS
  for each row 
  execute procedure update_last_modified_column();

create table GLOSSARY_VALUES (
  _ID serial not null primary key, 
  CODE varchar,
  TERM varchar,
  NORMALIZED_TERM varchar,
  EXTERNAL_CODE varchar,
  DESCRIPTION varchar,
  _SEARCH tsvector,
  _TYPES varchar[],
  _LAST_MODIFIED varchar default current_timestamp,
  _VERSION integer DEFAULT 1);

create trigger GLOSSARY_VALUES_UPDATE_LAST_MODIFIED
  before update 
  on GLOSSARY_VALUES
  for each row 
  execute procedure update_last_modified_column();

create table BRANDS (
  _ID serial not null primary key, 
  NAME varchar,
  CODE varchar,
  ATG_CODE varchar,
  DESCRIPTION varchar,
  DISPLAY_BRAND boolean,
  _SEARCH tsvector,
  _TYPES varchar[],
  _LAST_MODIFIED varchar default current_timestamp,
  _VERSION integer DEFAULT 1);

create trigger BRANDS_UPDATE_LAST_MODIFIED
  before update 
  on BRANDS
  for each row 
  execute procedure update_last_modified_column();

create table CATEGORIES (
  _ID serial not null primary key, 
  CODE varchar,
  ATG_CODE varchar,
  PARENT_CATEGORY integer,
  STATUS varchar,
  DESCRIPTIONS integer[],
  FULL_DESCRIPTIONS integer[],
  START_DATE varchar,
  END_DATE varchar,
  INVALIDATION_DATE varchar,
  REFERENCE integer,
  NOVELTY_DAYS real,
  CHILDREN integer[],
  _SEARCH tsvector,
  _TYPES varchar[],
  _LAST_MODIFIED varchar default current_timestamp,
  _VERSION integer DEFAULT 1);

create trigger CATEGORIES_UPDATE_LAST_MODIFIED
  before update 
  on CATEGORIES
  for each row 
  execute procedure update_last_modified_column();

create table IMAGES (
  _ID serial not null primary key, 
  URL varchar,
  DESCRIPTION varchar,
  _SEARCH tsvector,
  _TYPES varchar[],
  _LAST_MODIFIED varchar default current_timestamp,
  _VERSION integer DEFAULT 1);

create trigger IMAGES_UPDATE_LAST_MODIFIED
  before update 
  on IMAGES
  for each row 
  execute procedure update_last_modified_column();

create table VIDEOS (
  _ID serial not null primary key, 
  TITLE varchar,
  DESCRIPTION varchar,
  URL varchar,
  _SEARCH tsvector,
  _TYPES varchar[],
  _LAST_MODIFIED varchar default current_timestamp,
  _VERSION integer DEFAULT 1);

create trigger VIDEOS_UPDATE_LAST_MODIFIED
  before update 
  on VIDEOS
  for each row 
  execute procedure update_last_modified_column();

create table PRODUCT_TYPES (
  _ID serial not null primary key, 
  CODE varchar,
  ATG_CODE varchar,
  DESCRIPTIONS integer[],
  PARENTS varchar[],
  IS_MARKETPLACE boolean,
  CATEGORY_LIST_CODE varchar,
  CATEGORY_LIST_LABEL varchar,
  CATEGORY_CODES integer[],
  IMPORT_ID varchar,
  _SEARCH tsvector,
  _TYPES varchar[],
  _LAST_MODIFIED varchar default current_timestamp,
  _VERSION integer DEFAULT 1);

create trigger PRODUCT_TYPES_UPDATE_LAST_MODIFIED
  before update 
  on PRODUCT_TYPES
  for each row 
  execute procedure update_last_modified_column();

create table ITEMS (
  _ID serial not null primary key, 
  EAN varchar,
  PROVIDER_REF varchar,
  START_DATE varchar,
  NAME varchar,
  DESCRIPTION varchar,
  TAGS varchar[],
  EXTENDED_DESCRIPTION varchar,
  WEIGHT real,
  WEIGHT_UNIT varchar,
  PHOTOS varchar[],
  SUPPLEMENTARY_ITEMS integer[],
  SOLD_SEPARATELY boolean,
  PRODUCT_ID varchar,
  PRODUCT_TYPE varchar[],
  REGULATIONS varchar[],
  SECURITY_INFO varchar[],
  CATEGORY integer,
  KEYWORDS varchar[],
  IMAGE varchar,
  SECONDARY_IMAGES varchar[],
  VIDEOS varchar[],
  SUB_TYPE varchar,
  STYLE varchar,
  WASHING_INSTRUCTIONS varchar,
  SERVICE_DATE varchar,
  SERVICE_COMMENTS varchar,
  BRAND integer,
  DEFAULT_CATEGORIES integer[],
  ADDITIONAL_INFORMATION varchar,
  MAIN_PRODUCT integer,
  COLOR varchar,
  COLOR1 varchar,
  COLOR2 varchar,
  SIZE varchar,
  _SEARCH tsvector,
  _TYPES varchar[],
  _LAST_MODIFIED varchar default current_timestamp,
  _VERSION integer DEFAULT 1);

create trigger ITEMS_UPDATE_LAST_MODIFIED
  before update 
  on ITEMS
  for each row 
  execute procedure update_last_modified_column();

create table GENDER_TARGETS (
  _ID serial not null primary key, 
  CODE varchar,
  LABEL varchar,
  _SEARCH tsvector,
  _TYPES varchar[],
  _LAST_MODIFIED varchar default current_timestamp,
  _VERSION integer DEFAULT 1);

create trigger GENDER_TARGETS_UPDATE_LAST_MODIFIED
  before update 
  on GENDER_TARGETS
  for each row 
  execute procedure update_last_modified_column();

create table RELATED_ITEMS (
  _ID serial not null primary key, 
  ITEMS integer[],
  _SEARCH tsvector,
  _TYPES varchar[],
  _LAST_MODIFIED varchar default current_timestamp,
  _VERSION integer DEFAULT 1);

create trigger RELATED_ITEMS_UPDATE_LAST_MODIFIED
  before update 
  on RELATED_ITEMS
  for each row 
  execute procedure update_last_modified_column();

create table SUPPLIERS (
  _ID serial not null primary key, 
  CODE varchar,
  NAME varchar,
  _SEARCH tsvector,
  _TYPES varchar[],
  _LAST_MODIFIED varchar default current_timestamp,
  _VERSION integer DEFAULT 1);

create trigger SUPPLIERS_UPDATE_LAST_MODIFIED
  before update 
  on SUPPLIERS
  for each row 
  execute procedure update_last_modified_column();

create table SUPPLIER_ITEMS (
  _ID serial not null primary key, 
  SUPPLIER integer,
  ITEM integer,
  PRICE real,
  STOCK real,
  ACTIVE_FROM varchar,
  ACTIVE_TO varchar,
  _SEARCH tsvector,
  _TYPES varchar[],
  _LAST_MODIFIED varchar default current_timestamp,
  _VERSION integer DEFAULT 1);

create trigger SUPPLIER_ITEMS_UPDATE_LAST_MODIFIED
  before update 
  on SUPPLIER_ITEMS
  for each row 
  execute procedure update_last_modified_column();

create table GLOSSARIES (
  _ID serial not null primary key, 
  VALUES integer[],
  REFERENCE integer,
  _SEARCH tsvector,
  _TYPES varchar[],
  _LAST_MODIFIED varchar default current_timestamp,
  _VERSION integer DEFAULT 1);

create trigger GLOSSARIES_UPDATE_LAST_MODIFIED
  before update 
  on GLOSSARIES
  for each row 
  execute procedure update_last_modified_column();

create table LKUPS (
  _ID serial not null primary key, 
  CODE varchar,
  NAME varchar,
  LOOKUP_TYPE varchar,
  DESCRIPTIONS integer,
  ATG_CODE varchar,
  EXTERNAL_CODE varchar,
  LOGO varchar,
  AUDIT varchar,
  PRIORITY varchar,
  GLOSSARY integer,
  REFERENCE integer,
  _SEARCH tsvector,
  _TYPES varchar[],
  _LAST_MODIFIED varchar default current_timestamp,
  _VERSION integer DEFAULT 1);

create trigger LKUPS_UPDATE_LAST_MODIFIED
  before update 
  on LKUPS
  for each row 
  execute procedure update_last_modified_column();

create table EANS (
  _ID serial not null primary key, 
  CODE varchar,
  ECI_REF varchar,
  ECI_REF_LIST varchar[],
  WEB_VARIANT_CODE varchar,
  INTERNAL_PROVIDER_CODE varchar,
  REFERENCE_TYPE varchar,
  PRODUCT_TYPE_ID varchar,
  PARENT_CATEGORIES varchar[],
  LAST_DATE varchar,
  UNPUBLISH_DATE varchar,
  _SEARCH tsvector,
  _TYPES varchar[],
  _LAST_MODIFIED varchar default current_timestamp,
  _VERSION integer DEFAULT 1);

create trigger EANS_UPDATE_LAST_MODIFIED
  before update 
  on EANS
  for each row 
  execute procedure update_last_modified_column();

create index BRANDS_NAME_IDX
  on BRANDS(NAME);

create index ITEMS_NAME_IDX
  on ITEMS(NAME);

create index ITEMS_PRODUCT_TYPE_IDX
  on ITEMS(PRODUCT_TYPE);

create index ITEMS_COLOR_IDX
  on ITEMS(COLOR);

create index ITEMS_SIZE_IDX
  on ITEMS(SIZE);

create index RELATED_ITEMS_ITEMS_IDX
  on RELATED_ITEMS(ITEMS);

create index SUPPLIERS_NAME_IDX
  on SUPPLIERS(NAME);

create index SUPPLIER_ITEMS_SUPPLIER_IDX
  on SUPPLIER_ITEMS(SUPPLIER);

create index SUPPLIER_ITEMS_ITEM_IDX
  on SUPPLIER_ITEMS(ITEM);

create index SUPPLIER_ITEMS_PRICE_IDX
  on SUPPLIER_ITEMS(PRICE);

create trigger BRANDS_SEARCH_TRG
  before insert or update 
  on BRANDS 
  for each row 
  execute procedure tsvector_update_trigger(_SEARCH, 'pg_catalog.spanish', NAME);

create index BRANDS_SEARCH_IDX 
  on BRANDS
  using gin(_search);

create trigger ITEMS_SEARCH_TRG
  before insert or update 
  on ITEMS 
  for each row 
  execute procedure tsvector_update_trigger(_SEARCH, 'pg_catalog.spanish', EAN, PROVIDER_REF, START_DATE, NAME, DESCRIPTION);

create index ITEMS_SEARCH_IDX 
  on ITEMS
  using gin(_search);

create trigger RELATED_ITEMS_SEARCH_TRG
  before insert or update 
  on RELATED_ITEMS 
  for each row 
  execute procedure tsvector_update_trigger(_SEARCH, 'pg_catalog.spanish', ITEMS);

create index RELATED_ITEMS_SEARCH_IDX 
  on RELATED_ITEMS
  using gin(_search);

create trigger SUPPLIERS_SEARCH_TRG
  before insert or update 
  on SUPPLIERS 
  for each row 
  execute procedure tsvector_update_trigger(_SEARCH, 'pg_catalog.spanish', NAME);

create index SUPPLIERS_SEARCH_IDX 
  on SUPPLIERS
  using gin(_search);

alter table CATEGORIES 
  add constraint CATEGORIES_PARENT_CATEGORY
  foreign key (PARENT_CATEGORY) references CATEGORIES(_ID);

alter table CATEGORIES 
  add constraint CATEGORIES_REFERENCE
  foreign key (REFERENCE) references LINKS(_ID);

alter table ITEMS 
  add constraint ITEMS_CATEGORY
  foreign key (CATEGORY) references CATEGORIES(_ID);

alter table ITEMS 
  add constraint ITEMS_BRAND
  foreign key (BRAND) references BRANDS(_ID);

alter table ITEMS 
  add constraint ITEMS_MAIN_PRODUCT
  foreign key (MAIN_PRODUCT) references ITEMS(_ID);

alter table SUPPLIER_ITEMS 
  add constraint SUPPLIER_ITEMS_SUPPLIER
  foreign key (SUPPLIER) references SUPPLIERS(_ID);

alter table SUPPLIER_ITEMS 
  add constraint SUPPLIER_ITEMS_ITEM
  foreign key (ITEM) references ITEMS(_ID);

alter table GLOSSARIES 
  add constraint GLOSSARIES_REFERENCE
  foreign key (REFERENCE) references LINKS(_ID);

alter table LKUPS 
  add constraint LKUPS_DESCRIPTIONS
  foreign key (DESCRIPTIONS) references MAPS(_ID);

alter table LKUPS 
  add constraint LKUPS_GLOSSARY
  foreign key (GLOSSARY) references GLOSSARIES(_ID);

alter table LKUPS 
  add constraint LKUPS_REFERENCE
  foreign key (REFERENCE) references LINKS(_ID);

insert into SUPPLIERS (
    NAME,
	_TYPES
  ) 
  VALUES (
					
						
    'Supplier 1',
    '{"Supplier"}'
  );

insert into SUPPLIERS (
    NAME,
	_TYPES
  ) 
  VALUES (
					
						
    'Supplier 2',
    '{"Supplier"}'
  );

insert into SUPPLIERS (
    NAME,
	_TYPES
  ) 
  VALUES (
					
						
    'Supplier 3',
    '{"Supplier"}'
  );

insert into ITEMS (
    NAME,
    DESCRIPTION,
    WASHING_INSTRUCTIONS,
    BRAND,
	_TYPES
  ) 
  VALUES (
					
						
    'Gabardina de mujer Roberto Verino con cuello solapa y manga francesa',
					
						
    'Gabardina con cuello de solapa, manga francesa con trabilla ajustable y cinturón',
					
						
    'No lavar a máquina, no planchar',
					
						
    (select _ID from BRANDS where NAME = 'Roberto Verino'),
    '{"Item", "Product"}'
  );

insert into ITEMS (
    EAN,
    PRODUCT_ID,
    START_DATE,
    NAME,
    DESCRIPTION,
    WASHING_INSTRUCTIONS,
    BRAND,
	_TYPES
  ) 
  VALUES (
					
						
    '5449000121059',
					
						
    '012543234334',
					
						
    '2016-09-01',
					
						
    'Peluca Reggae',
					
						
    'Peluca Reggae molona estilo Bob Marley',
					
						
    'No lavar, se cuida sola',
					
						
    (select _ID from BRANDS where NAME = 'Levis'),
    '{"Item", "Product", "Service"}'
  );
