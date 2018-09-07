package mumble.mburger.sdk.MBClient.MBData.MBSections;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBClient.MBData.MBAtomic.MBClass;

/**
 * Identifies a Nooko section, eg. a simple news.
 *
 * @author  Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBSection implements Serializable {

    /**Unique id of the section*/
    private long id;

    /**Numeric order of the section*/
    private int order;

    /**A Map which contains all the fields, identified by name*/
    private Map<String, MBClass> data;

    /**Creation date of the Section*/
    private long available_at;

    public MBSection(long id, int order, Map<String, MBClass> data, long available_at) {
        this.id = id;
        this.order = order;
        this.data = data;
        this.available_at = available_at;
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
    public Map<String, MBClass> getData() {
        return data;
    }

    /**Set Map with the fields*/
    public void setData(HashMap<String, MBClass> data) {
        this.data = data;
    }

    /**Get a MBClass given a name, null if not present*/
    public MBClass getField(String key){
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

    /**Get the creation timestamp of the section (seconds)*/
    public long getAvailable_at() {
        return available_at;
    }

    /**Get the creation timestamp of the section in milliseconds*/
    public long getAvailable_atMillis() {
        return TimeUnit.SECONDS.toMillis(available_at);
    }

    /**Sets the creation timestamp of the section*/
    public void setAvailable_at(long available_at) {
        this.available_at = available_at;
    }
}