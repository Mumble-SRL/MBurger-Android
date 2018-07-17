package mumble.mburger.sdk.NKMapper;

import java.util.HashMap;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBClient.MBurgerMapper;

/**
 * Commodity class for mapping fields in custom objects to fields from MBurger sections, it is used with the method
 * {@link MBurgerMapper#mapToCustomObject(mumble.mburger.sdk.MBData.NKSections.MBSection, mumble.mburger.sdk.NKMapper.MBFieldsMapping, Object, boolean)}}.
 * You can specify different arguments to get specific data from fields (like the first image
 * or only the latitude from a {@link mumble.mburger.sdk.MBData.NKElements.MBAddressElement MBAddressElement} object.
 * You can find all the arguments and how to use them in {@link NKMappingArgs}
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBFieldsMapping {

    private HashMap<String, String> fieldsMap;

    public MBFieldsMapping() {
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
