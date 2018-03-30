package mumble.nooko3.Datatypes.NBlocks;

import java.io.Serializable;
import java.util.Map;
import mumble.nooko3.Datatypes.NSections.NSection;

/**
 * Identifies a Nooko block, eg Home, News, POI...
 *
 * @author  Enrico Ori
 * @version {@value mumble.nooko3.Datatypes.Const#version}
 */
public class NBlock implements Serializable{

    /**Unique id of the block*/
    private long id;

    /**Name of the block*/
    private String name;

    /**Block sections given by section id*/
    private Map<Long, NSection> sections;

    public NBlock(long id, String name, Map<Long, NSection> sections) {
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

    /**Get block map sections*/
    public Map<Long, NSection> getSections() {
        return sections;
    }

    /**Set block map sections*/
    public void setSections(Map<Long, NSection> sections) {
        this.sections = sections;
    }

    /**Get single section given the section id, if not present returns null*/
    public NSection getSection(Long id){
        if(sections != null){
            if(sections.containsKey(id)) {
                return sections.get(id);
            }
        }

        return null;
    }
}
