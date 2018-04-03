package mumble.nooko3.sdk.NControllers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mumble.nooko3.sdk.Const;
import mumble.nooko3.sdk.NData.NAtomic.NClass;
import mumble.nooko3.sdk.NData.NBlocks.NBlock;
import mumble.nooko3.sdk.NData.NElements.NEAddress;
import mumble.nooko3.sdk.NData.NElements.NECheckbox;
import mumble.nooko3.sdk.NData.NElements.NEDate;
import mumble.nooko3.sdk.NData.NElements.NEImages;
import mumble.nooko3.sdk.NData.NElements.NEMedia;
import mumble.nooko3.sdk.NData.NElements.NEText;
import mumble.nooko3.sdk.NData.NElements.NETitle;
import mumble.nooko3.sdk.NData.NElements.NEWYSIWYG;
import mumble.nooko3.sdk.NData.NElements.NSubElements.NFile;
import mumble.nooko3.sdk.NData.NElements.NSubElements.NImage;
import mumble.nooko3.sdk.NData.NProjects.NProject;
import mumble.nooko3.sdk.NData.NSections.NSection;

/**
 * Parsing class for converting API response in objects.
 *
 * @author Enrico Ori
 * @version {@value mumble.nooko3.sdk.Const#version}
 */
public class NParser {

    /**
     * Parses the project
     */
    public static NProject parseProject(JSONObject jsonObject) {
        long id = -1;
        String name = null;

        try {
            if (NCommonMethods.isJSONOk(jsonObject, "id")) {
                id = jsonObject.getLong("id");
            }

            if (NCommonMethods.isJSONOk(jsonObject, "name")) {
                name = jsonObject.getString("name");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new NProject(id, name);
    }

    /**
     * Parses a complete block
     */
    public static NBlock parseBlock(JSONObject jsonObject, boolean getSections, boolean getElements) {
        long id = -1;
        String name = null;
        ArrayList<NSection> sections = null;

        try {
            if (NCommonMethods.isJSONOk(jsonObject, "id")) {
                id = jsonObject.getLong("id");
            }

            if (NCommonMethods.isJSONOk(jsonObject, "name")) {
                name = jsonObject.getString("name");
            }

            if(getSections) {
                if (NCommonMethods.isJSONOk(jsonObject, "sections")) {
                    sections = parseSections(jsonObject.getJSONArray("sections"), getElements);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new NBlock(id, name, sections);
    }

    /**
     * Parses sections for the block
     */
    public static ArrayList<NSection> parseSections(JSONArray jSections, boolean getElements) {
        ArrayList<NSection> sections = null;
        try {
            for (int i = 0; i < jSections.length(); i++) {
                JSONArray jSect = jSections.getJSONArray(i);
                NSection section = parseSection(jSect, getElements);
                sections.add(section);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sections;
    }

    /**
     * Parses a single section
     */
    public static NSection parseSection(JSONArray jSection, boolean getElement) {
        Map<String, NClass> data = new HashMap<>();
        long id = -1;
        String name = null;

        try {
            for (int i = 0; i < jSection.length(); i++) {
                JSONObject jElem = jSection.getJSONObject(i);
                String type = null;
                NClass nObj = null;
                if (NCommonMethods.isJSONOk(jElem, "id")) {
                    id = jElem.getLong("id");
                    continue;
                }

                if (NCommonMethods.isJSONOk(jElem, "name")) {
                    name = jElem.getString("name");
                    continue;
                }

                if(getElement) {
                    if (NCommonMethods.isJSONOk(jElem, "type")) {
                        nObj = getNClassFromElem(jElem.get("value"), id, name, type);
                    }

                    if (nObj != null) {
                        data.put(name, nObj);
                    }
                }
                else{
                    data.put(name, nObj);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new NSection(id, name, data);
    }

    private static NClass getNClassFromElem(Object value, long id, String name, String type) {
        NClass nObj = null;

        try {
            if (type.equals(Const.type_text)) {
                nObj = new NEText(id, name, (String) value);
            }

            if (type.equals(Const.type_images)) {
                JSONArray jImages = new JSONArray((String) value);
                ArrayList<NImage> nImages = new ArrayList<>();
                for (int i = 0; i < jImages.length(); i++) {
                    String url = jImages.getString(i);
                    nImages.add(new NImage(url));
                }
                nObj = new NEImages(id, name, nImages);
            }

            if (type.equals(Const.type_media)) {
                JSONArray jMedia = new JSONArray((String) value);
                ArrayList<NFile> nFiles = new ArrayList<>();
                for (int i = 0; i < jMedia.length(); i++) {
                    String url = jMedia.getString(i);
                    nFiles.add(new NFile(url));
                }
                nObj = new NEMedia(id, name, nFiles);
            }

            if (type.equals(Const.type_wysiwyg)) {
                nObj = new NEWYSIWYG(id, name, (String) value);
            }

            if (type.equals(Const.type_date)) {
                nObj = new NEDate(id, name, (long) value);
            }

            if (type.equals(Const.type_address)) {
                JSONObject jAddress = new JSONObject((String) value);
                String address = null;
                double latitude = -1, longitude = -1;
                if (NCommonMethods.isJSONOk(jAddress, "address")) {
                    address = jAddress.getString("address");
                }

                if (NCommonMethods.isJSONOk(jAddress, "latitude")) {
                    latitude = jAddress.getDouble("latitude");
                }

                if (NCommonMethods.isJSONOk(jAddress, "longitude")) {
                    longitude = jAddress.getDouble("longitude");
                }

                nObj = new NEAddress(id, name, address, latitude, longitude);
            }

            if (type.equals(Const.type_title)) {
                nObj = new NETitle(id, name, (String) value);
            }

            if (type.equals(Const.type_checkbox)) {
                nObj = new NECheckbox(id, name, (boolean) value);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return nObj;
    }

}