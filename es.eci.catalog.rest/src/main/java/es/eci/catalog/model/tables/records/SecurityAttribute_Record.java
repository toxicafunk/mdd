/*
 * This file is generated by jOOQ.
*/
package es.eci.catalog.model.tables.records;


import es.eci.catalog.model.tables.SecurityAttribute_;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SecurityAttribute_Record extends UpdatableRecordImpl<SecurityAttribute_Record> implements Record7<Integer, Integer, String, Object, String[], String, Integer> {

    private static final long serialVersionUID = -297339999;

    /**
     * Setter for <code>public.security_attribute_._id_</code>.
     */
    public void set_Id_(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.security_attribute_._id_</code>.
     */
    public Integer get_Id_() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.security_attribute_._security_entity_id_</code>.
     */
    public void set_SecurityEntityId_(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.security_attribute_._security_entity_id_</code>.
     */
    public Integer get_SecurityEntityId_() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>public.security_attribute_.name_</code>.
     */
    public void setName_(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.security_attribute_.name_</code>.
     */
    public String getName_() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.security_attribute_._search_</code>.
     */
    public void set_Search_(Object value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.security_attribute_._search_</code>.
     */
    public Object get_Search_() {
        return (Object) get(3);
    }

    /**
     * Setter for <code>public.security_attribute_._types_</code>.
     */
    public void set_Types_(String... value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.security_attribute_._types_</code>.
     */
    public String[] get_Types_() {
        return (String[]) get(4);
    }

    /**
     * Setter for <code>public.security_attribute_._last_modified_</code>.
     */
    public void set_LastModified_(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.security_attribute_._last_modified_</code>.
     */
    public String get_LastModified_() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.security_attribute_._version_</code>.
     */
    public void set_Version_(Integer value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.security_attribute_._version_</code>.
     */
    public Integer get_Version_() {
        return (Integer) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Integer, Integer, String, Object, String[], String, Integer> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Integer, Integer, String, Object, String[], String, Integer> valuesRow() {
        return (Row7) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return SecurityAttribute_.SECURITY_ATTRIBUTE_._ID_;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return SecurityAttribute_.SECURITY_ATTRIBUTE_._SECURITY_ENTITY_ID_;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return SecurityAttribute_.SECURITY_ATTRIBUTE_.NAME_;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Object> field4() {
        return SecurityAttribute_.SECURITY_ATTRIBUTE_._SEARCH_;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String[]> field5() {
        return SecurityAttribute_.SECURITY_ATTRIBUTE_._TYPES_;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return SecurityAttribute_.SECURITY_ATTRIBUTE_._LAST_MODIFIED_;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field7() {
        return SecurityAttribute_.SECURITY_ATTRIBUTE_._VERSION_;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return get_Id_();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value2() {
        return get_SecurityEntityId_();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getName_();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object value4() {
        return get_Search_();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] value5() {
        return get_Types_();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return get_LastModified_();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value7() {
        return get_Version_();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecurityAttribute_Record value1(Integer value) {
        set_Id_(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecurityAttribute_Record value2(Integer value) {
        set_SecurityEntityId_(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecurityAttribute_Record value3(String value) {
        setName_(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecurityAttribute_Record value4(Object value) {
        set_Search_(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecurityAttribute_Record value5(String... value) {
        set_Types_(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecurityAttribute_Record value6(String value) {
        set_LastModified_(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecurityAttribute_Record value7(Integer value) {
        set_Version_(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecurityAttribute_Record values(Integer value1, Integer value2, String value3, Object value4, String[] value5, String value6, Integer value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SecurityAttribute_Record
     */
    public SecurityAttribute_Record() {
        super(SecurityAttribute_.SECURITY_ATTRIBUTE_);
    }

    /**
     * Create a detached, initialised SecurityAttribute_Record
     */
    public SecurityAttribute_Record(Integer _Id_, Integer _SecurityEntityId_, String name_, Object _Search_, String[] _Types_, String _LastModified_, Integer _Version_) {
        super(SecurityAttribute_.SECURITY_ATTRIBUTE_);

        set(0, _Id_);
        set(1, _SecurityEntityId_);
        set(2, name_);
        set(3, _Search_);
        set(4, _Types_);
        set(5, _LastModified_);
        set(6, _Version_);
    }
}
