package mumble.nooko3.sdk.NControllers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import mumble.nooko3.sdk.NConstants.NConst;
import mumble.nooko3.sdk.NData.NAtomic.NClass;
import mumble.nooko3.sdk.NData.NBlocks.NBlock;
import mumble.nooko3.sdk.NData.NElements.NEAddress;
import mumble.nooko3.sdk.NData.NElements.NECheckbox;
import mumble.nooko3.sdk.NData.NElements.NEDate;
import mumble.nooko3.sdk.NData.NElements.NEDropdown;
import mumble.nooko3.sdk.NData.NElements.NEGeneric;
import mumble.nooko3.sdk.NData.NElements.NEImages;
import mumble.nooko3.sdk.NData.NElements.NEMedia;
import mumble.nooko3.sdk.NData.NElements.NEText;
import mumble.nooko3.sdk.NData.NElements.NEWYSIWYG;
import mumble.nooko3.sdk.NData.NElements.NSubElements.NFile;
import mumble.nooko3.sdk.NData.NElements.NSubElements.NImage;
import mumble.nooko3.sdk.NData.NProjects.NProject;
import mumble.nooko3.sdk.NData.NSections.NSection;

/**
 * Parsing class for converting API response in objects.
 *
 * @author Enrico Ori
 * @version {@value NConst#version}
 */
public class NParser {

    /**
     * Parses the project
     */
    public static NProject parseProject(JSONObject jsonObject) {
        long id = -1;
        String name = null;
        boolean hasBeacons = false;
        boolean hasUsers = false;
        boolean hasMultilanguage = false;
        boolean hasLiveMessages = false;
        boolean hasEvidence = false;
        boolean hasPush = false;

        try {
            if (NCommonMethods.isJSONOk(jsonObject, "id")) {
                id = jsonObject.getLong("id");
            }

            if (NCommonMethods.isJSONOk(jsonObject, "has_beacons")) {
                hasBeacons = jsonObject.getBoolean("has_beacons");
            }

            if (NCommonMethods.isJSONOk(jsonObject, "has_users")) {
                hasUsers = jsonObject.getBoolean("has_users");
            }

            if (NCommonMethods.isJSONOk(jsonObject, "has_multilanguage")) {
                hasMultilanguage = jsonObject.getBoolean("has_multilanguage");
            }

            if (NCommonMethods.isJSONOk(jsonObject, "has_live_messages")) {
                hasLiveMessages = jsonObject.getBoolean("has_live_messages");
            }

            if (NCommonMethods.isJSONOk(jsonObject, "has_evidence")) {
                hasEvidence = jsonObject.getBoolean("has_evidence");
            }

            if (NCommonMethods.isJSONOk(jsonObject, "has_push")) {
                hasPush = jsonObject.getBoolean("has_push");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new NProject(id, name, hasBeacons, hasUsers, hasMultilanguage, hasLiveMessages, hasEvidence, hasPush);
    }

    /**
     * Parses a complete block with option to have sections and elements (if getElements = true, getSections should be = true)
     */
    public static NBlock parseBlock(JSONObject jsonObject, boolean getSections, boolean getElements) {
        long id = -1;
        String title = null, subtitle = null;
        int order = 0;
        ArrayList<NSection> sections = null;

        try {
            if (NCommonMethods.isJSONOk(jsonObject, "id")) {
                id = jsonObject.getLong("id");
            }

            if (NCommonMethods.isJSONOk(jsonObject, "title")) {
                title = jsonObject.getString("title");
            }

            if (NCommonMethods.isJSONOk(jsonObject, "subtitle")) {
                subtitle = jsonObject.getString("subtitle");
            }

            if (NCommonMethods.isJSONOk(jsonObject, "order")) {
                order = jsonObject.getInt("order");
            }

            if (getSections) {
                if (NCommonMethods.isJSONOk(jsonObject, "sections")) {
                    sections = parseSections(jsonObject.getJSONArray("sections"), getElements);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new NBlock(id, title, subtitle, sections, order);
    }

    /**
     * Parses an array of sections using the names decided on the Nooko3 dashboard
     */
    public static ArrayList<NSection> parseSections(JSONArray jSections, boolean getElements) {
        ArrayList<NSection> sections = new ArrayList<>();
        try {
            for (int i = 0; i < jSections.length(); i++) {
                JSONObject jSect = jSections.getJSONObject(i);
                NSection section = parseSection(jSect, getElements);
                sections.add(section);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sections;
    }

    /**
     * Parses a single section using the names decided in the dashboard
     */
    public static NSection parseSection(JSONObject jSection, boolean getElements) {
        Map<String, NClass> data = new HashMap<>();
        long id = -1;
        int order = 0;

        try {
            if (NCommonMethods.isJSONOk(jSection, "id")) {
                id = jSection.getLong("id");
            }

            if (NCommonMethods.isJSONOk(jSection, "order")) {
                order = jSection.getInt("order");
            }

            if (getElements) {
                if (NCommonMethods.isJSONOk(jSection, "elements")) {
                    JSONObject jElements = jSection.getJSONObject("elements");

                    /*Iterates through the elements keys in order to obtain the objects needed*/
                    Iterator<String> iter = jElements.keys();
                    while (iter.hasNext()) {
                        String key = iter.next();
                        try {
                            JSONObject jElem = jElements.getJSONObject(key);
                            NClass nObj = null;
                            if (NCommonMethods.isJSONOk(jElem, "type")) {
                                nObj = getNClassFromElem(jElem.get("value"), jElem.getLong("id"), key, jElem.getString("type"));
                            }

                            if (nObj != null) {
                                data.put(key, nObj);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new NSection(id, order, data);
    }

    /**
     * Gets a map of elements from a JSONObject
     */
    public static Map<String, NClass> parseElements(JSONObject jObj) {
        Map<String, NClass> element = new HashMap<>();

        /*Iterates through the elements keys in order to obtain the objects needed*/
        Iterator<String> iter = jObj.keys();
        while (iter.hasNext()) {
            String key = iter.next();
            try {
                JSONObject jElem = jObj.getJSONObject(key);
                NClass nObj = null;
                if (NCommonMethods.isJSONOk(jElem, "type")) {
                    nObj = getNClassFromElem(jElem.get("value"), jElem.getLong("id"), key, jElem.getString("type"));
                }

                if (nObj != null) {
                    element.put(key, nObj);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return element;
    }

    /**
     * Gets a single Nooko class from type working with the value, if a known type is not found, a generic element will be created, which
     * has the value of a basic object
     */
    private static NClass getNClassFromElem(Object value, long id, String name, String type) {
        NClass nObj = null;
        boolean found = false;

        try {
            if (type.equals(NConst.type_text)) {
                found = true;
                nObj = new NEText(id, name, (String) value);
            }

            if (type.equals(NConst.type_image)) {
                found = true;
                JSONArray jImages = (JSONArray) value;
                ArrayList<NImage> nImages = new ArrayList<>();
                for (int i = 0; i < jImages.length(); i++) {
                    JSONObject jImg = jImages.getJSONObject(i);
                    long img_id = -1;
                    String img_url = null;
                    long img_size = -1;
                    String img_mime_type = null;

                    if (NCommonMethods.isJSONOk(jImg, "id")) {
                        img_id = jImg.getLong("id");
                    }

                    if (NCommonMethods.isJSONOk(jImg, "url")) {
                        img_url = jImg.getString("url");
                    }

                    if (NCommonMethods.isJSONOk(jImg, "size")) {
                        img_size = jImg.getLong("size");
                    }

                    if (NCommonMethods.isJSONOk(jImg, "mime_type")) {
                        img_mime_type = jImg.getString("mime_type");
                    }

                    nImages.add(new NImage(img_id, img_url, img_mime_type, img_size));
                }
                nObj = new NEImages(id, name, nImages);
            }

            if (type.equals(NConst.type_media_audio) ||
                    type.equals(NConst.type_media_document) ||
                    type.equals(NConst.type_media_file) ||
                    type.equals(NConst.type_media_video)) {
                found = true;
                JSONArray jMedia = new JSONArray((String) value);
                ArrayList<NFile> nFiles = new ArrayList<>();
                for (int i = 0; i < jMedia.length(); i++) {
                    JSONObject jF = jMedia.getJSONObject(i);
                    long file_id = -1;
                    String file_url = null;
                    long file_size = -1;
                    String file_mime_type = null;

                    if (NCommonMethods.isJSONOk(jF, "id")) {
                        file_id = jF.getLong("id");
                    }

                    if (NCommonMethods.isJSONOk(jF, "url")) {
                        file_url = jF.getString("url");
                    }

                    if (NCommonMethods.isJSONOk(jF, "size")) {
                        file_size = jF.getLong("size");
                    }

                    if (NCommonMethods.isJSONOk(jF, "mime_type")) {
                        file_mime_type = jF.getString("mime_type");
                    }

                    nFiles.add(new NFile(file_id, file_mime_type, file_size, file_url, type));
                }
                nObj = new NEMedia(id, name, nFiles);
            }

            if (type.equals(NConst.type_wysiwyg)) {
                found = true;
                nObj = new NEWYSIWYG(id, name, (String) value);
            }

            if (type.equals(NConst.type_date)) {
                found = true;
                nObj = new NEDate(id, name, (long) value);
            }

            if (type.equals(NConst.type_address)) {
                found = true;
                JSONObject jAddress = (JSONObject) value;
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

            if (type.equals(NConst.type_checkbox)) {
                found = true;
                nObj = new NECheckbox(id, name, (boolean) value);
            }

            if (type.equals(NConst.type_dropdown)) {
                found = true;
                nObj = new NEDropdown(id, name, (String) value);
            }

            if (!found) {
                nObj = new NEGeneric(id, name, value);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return nObj;
    }

}