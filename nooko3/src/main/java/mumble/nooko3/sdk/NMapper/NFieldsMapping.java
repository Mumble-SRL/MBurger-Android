package mumble.nooko3.sdk.NMapper;

import java.util.HashMap;

import mumble.nooko3.sdk.NConstants.NConst;
import mumble.nooko3.sdk.NData.NSections.NSection;

/**
 * Commodity class for mapping fields in custom objects to fields from Nooko3 sections, it is used with the method
 * {@link mumble.nooko3.sdk.NMapper.Nooko3Mapper#mapToCustomObject(NSection, NFieldsMapping, Object, boolean)}}.
 * You can specify different arguments to get specific data from fields (like the first image
 * or only the latitude from a {@link mumble.nooko3.sdk.NData.NElements.NEAddress NEAddress} object.
 * You can find all the arguments and how to use them in {@link NMappingArgs }
 *
 * @author Enrico Ori
 * @version {@value NConst#version}
 */
public class NFieldsMapping {

    private HashMap<String, String> fieldsMap;

    public NFieldsMapping() {
        this.fieldsMap = new HashMap<>();
    }

    public void putMap(String key, String value){
        fieldsMap.put(key, value);
    }

    public void putMap(String key, String value, String[] args){
        StringBuilder builderArgs = new StringBuilder(value);
        for(int i = 0; i < args.length; i++){
            String arg = args[i];
            builderArgs.append(".");
            builderArgs.append(arg);
        }

        fieldsMap.put(key, builderArgs.toString());
    }

    public HashMap<String, String> getFieldsMap() {
        return fieldsMap;
    }

    public boolean containsKey(String key){
        return fieldsMap.containsKey(key);
    }

    public String get(String key){
        if(containsKey(key)) {
            return fieldsMap.get(key);
        }

        return null;
    }
}
