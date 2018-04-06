package mumble.nooko3.sdk.NData.NProjects;

import java.io.Serializable;

import mumble.nooko3.sdk.NConstants.NConst;

/**
 * Identifies a Nooko project, pretty much it's an abstraction of a Nooko app
 *
 * @author Enrico Ori
 * @version {@value NConst#version}
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

    /**
     * If the project has support for beacons
     */
    private boolean hasBeacons;

    /**
     * Is the project may have login and user management
     */
    private boolean hasUsers;

    /**
     * If the project is multilangual
     */
    private boolean hasMultilanguage;

    /**
     * If the project has live message functionality
     */
    private boolean hasLiveMessages;

    /**
     * UNKNOWN
     */
    private boolean hasEvidence;

    /**
     * If the project has support for push notifications
     */
    private boolean hasPush;

    public NProject(long id, String name, boolean hasBeacons, boolean hasUsers, boolean hasMultilanguage,
                    boolean hasLiveMessages, boolean hasEvidence, boolean hasPush) {
        this.id = id;
        this.name = name;
        this.hasBeacons = hasBeacons;
        this.hasUsers = hasUsers;
        this.hasMultilanguage = hasMultilanguage;
        this.hasLiveMessages = hasLiveMessages;
        this.hasEvidence = hasEvidence;
        this.hasPush = hasPush;
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

    public boolean hasBeacons() {
        return hasBeacons;
    }

    public void setHasBeacons(boolean hasBeacons) {
        this.hasBeacons = hasBeacons;
    }

    public boolean hasUsers() {
        return hasUsers;
    }

    public void setHasUsers(boolean hasUsers) {
        this.hasUsers = hasUsers;
    }

    public boolean hasMultilanguage() {
        return hasMultilanguage;
    }

    public void setHasMultilanguage(boolean hasMultilanguage) {
        this.hasMultilanguage = hasMultilanguage;
    }

    public boolean hasLiveMessages() {
        return hasLiveMessages;
    }

    public void setHasLiveMessages(boolean hasLiveMessages) {
        this.hasLiveMessages = hasLiveMessages;
    }

    public boolean hasEvidence() {
        return hasEvidence;
    }

    public void setHasEvidence(boolean hasEvidence) {
        this.hasEvidence = hasEvidence;
    }

    public boolean hasPush() {
        return hasPush;
    }

    public void setHasPush(boolean hasPush) {
        this.hasPush = hasPush;
    }
}
