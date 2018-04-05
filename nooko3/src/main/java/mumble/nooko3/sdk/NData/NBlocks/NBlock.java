package mumble.nooko3.sdk.NData.NBlocks;

import java.io.Serializable;
import java.util.ArrayList;

import mumble.nooko3.sdk.NConstants.NConst;
import mumble.nooko3.sdk.NData.NSections.NSection;

/**
 * Identifies a Nooko block, eg Home, News, POI...
 *
 * @author  Enrico Ori
 * @version {@value NConst#version}
 */
public class NBlock implements Serializable{

    /**Unique id of the block*/
    private long id;

    /**Name of the block*/
    private String name;

    /**Block sections array*/
    private ArrayList<NSection> sections;

    /**Order of the block inside the project*/
    private int order;

    public NBlock(long id, String name, ArrayList<NSection> sections, int order) {
        this.id = id;
        this.name = name;
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
    public String getName() {
        return name;
    }

    /**Set block name*/
    public void setName(String name) {
        this.name = name;
    }

    /**Get block sections*/
    public ArrayList<NSection> getSections() {
        return sections;
    }

    /**Set block sections array*/
    public void setSections(ArrayList<NSection> sections) {
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
}
