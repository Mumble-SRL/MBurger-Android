package mumble.mburger.sdk.MBClient.MBData.MBBlocks;

import java.io.Serializable;
import java.util.ArrayList;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBClient.MBData.MBSections.MBSection;

/**
 * Identifies a MBurger block, eg Home, News, POI...
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBBlock implements Serializable {

    /**
     * Unique id of the block
     */
    private long id;

    /**
     * Title of the block
     */
    private String title;

    /**
     * Name of the block
     */
    private String subtitle;

    /**
     * Block sections array
     */
    private ArrayList<MBSection> sections;

    /**
     * Order of the block inside the project
     */
    private int order;

    /**
     * Structure of the block, may be null
     */
    private String jArrStructure;

    public MBBlock(long id, String title, String subtitle, ArrayList<MBSection> sections, int order, String jArrStructure) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.sections = sections;
        this.order = order;
        this.jArrStructure = jArrStructure;
    }

    /**
     * Get block id
     */
    public long getId() {
        return id;
    }

    /**
     * Set block id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get block name
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set block name
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get block sections
     */
    public ArrayList<MBSection> getSections() {
        return sections;
    }

    /**
     * Set block sections array
     */
    public void setSections(ArrayList<MBSection> sections) {
        this.sections = sections;
    }

    /**
     * Get block order
     */
    public int getOrder() {
        return order;
    }

    /**
     * Set block order
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * Get block subtitle
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * Set block subtitle
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getjArrStructure() {
        return jArrStructure;
    }

    public void setjArrStructure(String jArrStructure) {
        this.jArrStructure = jArrStructure;
    }
}
