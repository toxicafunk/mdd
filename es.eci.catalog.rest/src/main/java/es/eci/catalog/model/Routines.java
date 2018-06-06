/*
 * This file is generated by jOOQ.
*/
package es.eci.catalog.model;


import es.eci.catalog.model.routines.Unaccent1;
import es.eci.catalog.model.routines.Unaccent2;
import es.eci.catalog.model.routines.UnaccentInit;
import es.eci.catalog.model.routines.UnaccentLexize;
import es.eci.catalog.model.routines.UpdateLastModifiedColumn;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.Field;


/**
 * Convenience access to all stored procedures and functions in public
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Routines {

    /**
     * Call <code>public.unaccent</code>
     */
    public static String unaccent1(Configuration configuration, Object __1, String __2) {
        Unaccent1 f = new Unaccent1();
        f.set__1(__1);
        f.set__2(__2);

        f.execute(configuration);
        return f.getReturnValue();
    }

    /**
     * Get <code>public.unaccent</code> as a field.
     */
    public static Field<String> unaccent1(Object __1, String __2) {
        Unaccent1 f = new Unaccent1();
        f.set__1(__1);
        f.set__2(__2);

        return f.asField();
    }

    /**
     * Get <code>public.unaccent</code> as a field.
     */
    public static Field<String> unaccent1(Field<Object> __1, Field<String> __2) {
        Unaccent1 f = new Unaccent1();
        f.set__1(__1);
        f.set__2(__2);

        return f.asField();
    }

    /**
     * Call <code>public.unaccent</code>
     */
    public static String unaccent2(Configuration configuration, String __1) {
        Unaccent2 f = new Unaccent2();
        f.set__1(__1);

        f.execute(configuration);
        return f.getReturnValue();
    }

    /**
     * Get <code>public.unaccent</code> as a field.
     */
    public static Field<String> unaccent2(String __1) {
        Unaccent2 f = new Unaccent2();
        f.set__1(__1);

        return f.asField();
    }

    /**
     * Get <code>public.unaccent</code> as a field.
     */
    public static Field<String> unaccent2(Field<String> __1) {
        Unaccent2 f = new Unaccent2();
        f.set__1(__1);

        return f.asField();
    }

    /**
     * Call <code>public.unaccent_init</code>
     */
    public static Object unaccentInit(Configuration configuration, Object __1) {
        UnaccentInit f = new UnaccentInit();
        f.set__1(__1);

        f.execute(configuration);
        return f.getReturnValue();
    }

    /**
     * Get <code>public.unaccent_init</code> as a field.
     */
    public static Field<Object> unaccentInit(Object __1) {
        UnaccentInit f = new UnaccentInit();
        f.set__1(__1);

        return f.asField();
    }

    /**
     * Get <code>public.unaccent_init</code> as a field.
     */
    public static Field<Object> unaccentInit(Field<Object> __1) {
        UnaccentInit f = new UnaccentInit();
        f.set__1(__1);

        return f.asField();
    }

    /**
     * Call <code>public.unaccent_lexize</code>
     */
    public static Object unaccentLexize(Configuration configuration, Object __1, Object __2, Object __3, Object __4) {
        UnaccentLexize f = new UnaccentLexize();
        f.set__1(__1);
        f.set__2(__2);
        f.set__3(__3);
        f.set__4(__4);

        f.execute(configuration);
        return f.getReturnValue();
    }

    /**
     * Get <code>public.unaccent_lexize</code> as a field.
     */
    public static Field<Object> unaccentLexize(Object __1, Object __2, Object __3, Object __4) {
        UnaccentLexize f = new UnaccentLexize();
        f.set__1(__1);
        f.set__2(__2);
        f.set__3(__3);
        f.set__4(__4);

        return f.asField();
    }

    /**
     * Get <code>public.unaccent_lexize</code> as a field.
     */
    public static Field<Object> unaccentLexize(Field<Object> __1, Field<Object> __2, Field<Object> __3, Field<Object> __4) {
        UnaccentLexize f = new UnaccentLexize();
        f.set__1(__1);
        f.set__2(__2);
        f.set__3(__3);
        f.set__4(__4);

        return f.asField();
    }

    /**
     * Call <code>public.update_last_modified_column</code>
     */
    public static Object updateLastModifiedColumn(Configuration configuration) {
        UpdateLastModifiedColumn f = new UpdateLastModifiedColumn();

        f.execute(configuration);
        return f.getReturnValue();
    }

    /**
     * Get <code>public.update_last_modified_column</code> as a field.
     */
    public static Field<Object> updateLastModifiedColumn() {
        UpdateLastModifiedColumn f = new UpdateLastModifiedColumn();

        return f.asField();
    }
}