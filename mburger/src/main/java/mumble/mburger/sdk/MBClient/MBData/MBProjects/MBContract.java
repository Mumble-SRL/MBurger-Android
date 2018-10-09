package mumble.mburger.sdk.MBClient.MBData.MBProjects;

import java.io.Serializable;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;

/**
 * Identifies a MBurger contract on the website, used for GDPR and licensing stuff
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBContract implements Serializable{

    /**
     * Unique id of the contract
     */
    private long id;

    /**
     * Name of the contract
     */
    private String name;

    /**
     * Possible link for the contract
     */
    private String link;

    /**
     * Markdown text for the contract
     */
    private String text;

    /**
     * If the contract is active
     */
    private boolean active;

    /**
     * Milliseconds creation
     */
    private long created_at;


    /**
     * Milliseconds update
     */
    private long updated_at;

    public MBContract(long id, String name, String link, String text, boolean active, long created_at, long updated_at) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.text = text;
        this.active = active;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(long updated_at) {
        this.updated_at = updated_at;
    }
}
