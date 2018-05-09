package mumble.nooko3.sdk.NKData.NKBlocks;

import java.io.Serializable;
import java.util.ArrayList;

import mumble.nooko3.sdk.Common.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKData.NKSections.NKSection;

/**
 * Identifies a Nooko block, eg Home, News, POI...
 *
 * @author  Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKBlock implements Serializable{

    /**Unique id of the block*/
    private long id;

    /**Title of the block*/
    private String title;

    /**Name of the block*/
    private String subtitle;

    /**Block sections array*/
    private ArrayList<NKSection> sections;

    /**Order of the block inside the project*/
    private int order;

    public NKBlock(long id, String title, String subtitle, ArrayList<NKSection> sections, int order) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.sections = sections;
        this.order = order;
    }

    /**Get block id*/
    public long getId() {
        return id;
    }

    /**Set block id*/
    public void setId(long id) {
        this.id = id;
    }

    /**Get block name*/
    public String getTitle() {
        return title;
    }

    /**Set block name*/
    public void setTitle(String title) {
        this.title = title;
    }

    /**Get block sections*/
    public ArrayList<NKSection> getSections() {
        return sections;
    }

    /**Set block sections array*/
    public void setSections(ArrayList<NKSection> sections) {
        this.sections = sections;
    }

    /**Get block order*/
    public int getOrder() {
        return order;
    }

    /**Set block order*/
    public void setOrder(int order) {
        this.order = order;
    }

    /**Get block subtitle*/
    public String getSubtitle() {
        return subtitle;
    }

    /**Set block subtitle*/
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
