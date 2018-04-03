package mumble.nooko3.sdk.NData.NBlocks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import mumble.nooko3.sdk.NData.NSections.NSection;

/**
 * Identifies a Nooko block, eg Home, News, POI...
 *
 * @author  Enrico Ori
 * @version {@value mumble.nooko3.sdk.Const#version}
 */
public class NBlock implements Serializable{

    /**Unique id of the block*/
    private long id;

    /**Name of the block*/
    private String name;

    /**Block sections array*/
    private ArrayList<NSection> sections;

    public NBlock(long id, String name, ArrayList<NSection> sections) {
        this.id = id;
        this.name = name;
        this.sections = sections;
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
}
