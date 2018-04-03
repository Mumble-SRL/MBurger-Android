package mumble.nooko3.sdk.NData.NSections;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import mumble.nooko3.sdk.NData.NAtomic.NClass;

/**
 * Identifies a Nooko section, eg. a simple news.
 *
 * @author  Enrico Ori
 * @version {@value mumble.nooko3.sdk.Const#version}
 */
public class NSection implements Serializable {

    /**Unique id of the section*/
    private long id;

    /**Name of the section*/
    private String name;

    /**A Map which contains all the fields, identified by name*/
    private Map<String, NClass> data;

    public NSection(long id, String name, Map<String, NClass> data) {
        this.id = id;
        this.name = name;
        this.data = data;
    }

    /**Get unique id of the section*/
    public long getId() {
        return id;
    }

    /**Set unique id of the section*/
    public void setId(long id) {
        this.id = id;
    }

    /**Get the name of the section*/
    public String getName() {
        return name;
    }

    /**Set the name of the section*/
    public void setName(String name) {
        this.name = name;
    }

    /**Get Map with the fields*/
    public Map<String, NClass> getData() {
        return data;
    }

    /**Set Map with the fields*/
    public void setData(HashMap<String, NClass> data) {
        this.data = data;
    }

    /**Get a NClass given a name, null if not present*/
    public NClass getField(String key){
        if(data != null){
            if(data.containsKey(key)){
                return data.get(key);
            }
        }

        return null;
    }
}