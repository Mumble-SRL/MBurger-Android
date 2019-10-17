package mumble.mburger.sdk.MBClient.MBData.MBProjects;

import java.io.Serializable;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;

/**
 * Identifies a MBurger evidence object
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBEvidenceObject implements Serializable{

    /**
     * Unique id of the object
     */
    private long id;

    /**
     * Block id of the object
     */
    private long block_id;

    /**
     * Section id of the object
     */
    private long section_id;

    /**
     * Title of the evidence content
     */
    private String title;

    /**
     * Image of the evidence content
     */
    private String image;

    public MBEvidenceObject(long id, long block_id, long section_id, String title, String image) {
        this.id = id;
        this.block_id = block_id;
        this.section_id = section_id;
        this.title = title;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBlock_id() {
        return block_id;
    }

    public void setBlock_id(long block_id) {
        this.block_id = block_id;
    }

    public long getSection_id() {
        return section_id;
    }

    public void setSection_id(long section_id) {
        this.section_id = section_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
