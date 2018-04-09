package mumble.nooko3.sdk.NKData.NKSections;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import mumble.nooko3.sdk.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKData.NKAtomic.NKClass;

/**
 * Identifies a Nooko section, eg. a simple news.
 *
 * @author  Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKSection implements Serializable {

    /**Unique id of the section*/
    private long id;

    /**Numeric order of the section*/
    private int order;

    /**A Map which contains all the fields, identified by name*/
    private Map<String, NKClass> data;

    public NKSection(long id, int order, Map<String, NKClass> data) {
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
    public Map<String, NKClass> getData() {
        return data;
    }

    /**Set Map with the fields*/
    public void setData(HashMap<String, NKClass> data) {
        this.data = data;
    }

    /**Get a NKClass given a name, null if not present*/
    public NKClass getField(String key){
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