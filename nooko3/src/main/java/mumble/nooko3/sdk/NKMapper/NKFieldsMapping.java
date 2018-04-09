package mumble.nooko3.sdk.NKMapper;

import java.util.HashMap;

import mumble.nooko3.sdk.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKData.NKElements.NKAddressElement;
import mumble.nooko3.sdk.NKData.NKSections.NKSection;
import mumble.nooko3.sdk.Nooko3Mapper;

/**
 * Commodity class for mapping fields in custom objects to fields from Nooko3 sections, it is used with the method
 * {@link Nooko3Mapper#mapToCustomObject(NKSection, NKFieldsMapping, Object, boolean)}}.
 * You can specify different arguments to get specific data from fields (like the first image
 * or only the latitude from a {@link NKAddressElement NKAddressElement} object.
 * You can find all the arguments and how to use them in {@link NKMappingArgs}
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKFieldsMapping {

    private HashMap<String, String> fieldsMap;

    public NKFieldsMapping() {
        this.fieldsMap = new HashMap<>();
    }

    public void putMap(String customObjectField, String sectionName){
        fieldsMap.put(customObjectField, sectionName);
    }

    public void putMap(String customObjectField, String sectionName, String[] mappingArguments){
        StringBuilder builderArgs = new StringBuilder(sectionName);
        for(int i = 0; i < mappingArguments.length; i++){
            String arg = mappingArguments[i];
            builderArgs.append(".");
            builderArgs.append(arg);
        }

        fieldsMap.put(customObjectField, builderArgs.toString());
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
