package mumble.nooko3.Datatypes.NSections;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import mumble.nooko3.Datatypes.NAtomic.NClass;

/**
 * Identifies a Nooko section, eg. a simple news.
 *
 * @author  Enrico Ori
 * @version {@value mumble.nooko3.Datatypes.Const#version}
 */
public class NSection implements Serializable {

    /**Unique Section ID*/
    private long id;

    /**A Map which contains all the fields, identified by name*/
    private Map<String, NClass> data;

    public NSection(long id, Map<String, NClass> data) {
        this.id = id;
        this.data = data;
    }

    /**Get Section ID*/
    public long getId() {
        return id;
    }

    /**Set Section ID*/
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
}