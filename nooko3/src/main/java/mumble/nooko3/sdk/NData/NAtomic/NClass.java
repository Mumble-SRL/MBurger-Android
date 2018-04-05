package mumble.nooko3.sdk.NData.NAtomic;
import java.io.Serializable;

import mumble.nooko3.sdk.NConstants.Const;

/**
 * Basic class which all blocks are extending
 *
 * @author  Enrico Ori
 * @version {@value Const#version}
 */
public abstract class NClass implements Serializable{

    /** Unique id of the element */
    private long id;

    /** Name of the element */
    private String name;

    /** Type of the element, you can see all element types in the {@link Const Const.java} file */
    private String type;

    /** Every element must be initialized with id, name, type */
    public abstract void initialize(long id, String name, String type);

    /**Get id*/
    public long getId() {
        return id;
    }

    /**Set id*/
    public void setId(long id) {
        this.id = id;
    }

    /**Get name*/
    public String getName() {
        return name;
    }

    /**Set name*/
    public void setName(String name) {
        this.name = name;
    }

    /**Get type*/
    public String getType() {
        return type;
    }

    /**Set type*/
    public void setType(String type) {
        this.type = type;
    }
}
