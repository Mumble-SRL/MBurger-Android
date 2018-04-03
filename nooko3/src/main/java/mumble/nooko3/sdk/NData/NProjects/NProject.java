package mumble.nooko3.sdk.NData.NProjects;

import java.io.Serializable;

/**
 * Identifies a Nooko project, pretty much it's an abstraction of a Nooko app
 *
 * @author Enrico Ori
 * @version {@value mumble.nooko3.sdk.Const#version}
 */
public class NProject implements Serializable {

    /**
     * Unique id of the project
     */
    private long id;

    /**
     * Name of the project
     */
    private String name;

    public NProject(long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Get project id
     */
    public long getId() {
        return id;
    }

    /**
     * Set project id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get project name
     */
    public String getName() {
        return name;
    }

    /**
     * Set project name
     */
    public void setName(String name) {
        this.name = name;
    }
}
