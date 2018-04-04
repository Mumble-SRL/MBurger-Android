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

    /**Numeric order of the section*/
    private int order;

    /**A Map which contains all the fields, identified by name*/
    private Map<String, NClass> data;

    public NSection(long id, int order, Map<String, NClass> data) {
        this.id = id;
        this.order = order;
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

    /**Get the numeric order of the section*/
    public int getOrder() {
        return order;
    }

    /**Set the numeric order of the section*/
    public void setOrder(int order) {
        this.order = order;
    }
}