package mumble.nooko3.sdk.Common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import mumble.nooko3.sdk.Common.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKAuth.NKAuthData.NKAuthUser;
import mumble.nooko3.sdk.NKPay.NKPayData.NKStripeSubscription;
import mumble.nooko3.sdk.NKData.NKAtomic.NKClass;
import mumble.nooko3.sdk.NKData.NKBlocks.NKBlock;
import mumble.nooko3.sdk.NKData.NKElements.NKAddressElement;
import mumble.nooko3.sdk.NKData.NKElements.NKCheckboxElement;
import mumble.nooko3.sdk.NKData.NKElements.NKDateElement;
import mumble.nooko3.sdk.NKData.NKElements.NKDropdownElement;
import mumble.nooko3.sdk.NKData.NKElements.NKGenericElement;
import mumble.nooko3.sdk.NKData.NKElements.NKImages;
import mumble.nooko3.sdk.NKData.NKElements.NKMediaElement;
import mumble.nooko3.sdk.NKData.NKElements.NKPollAnswers;
import mumble.nooko3.sdk.NKData.NKElements.NKSubElements.NKAnswer;
import mumble.nooko3.sdk.NKData.NKElements.NKSubElements.NKFile;
import mumble.nooko3.sdk.NKData.NKElements.NKSubElements.NKImage;
import mumble.nooko3.sdk.NKData.NKElements.NKTextElement;
import mumble.nooko3.sdk.NKData.NKElements.NKWYSIWYGElement;
import mumble.nooko3.sdk.NKData.NKProjects.NKProject;
import mumble.nooko3.sdk.NKData.NKSections.NKSection;

/**
 * Parsing class for converting API response in objects.
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKParser {

    /**
     * Parses the project
     */
    public static NKProject parseProject(JSONObject jsonObject) {
        long id = -1;
        String name = null;
        boolean hasBeacons = false;
        boolean hasUsers = false;
        boolean hasMultilanguage = false;
        boolean hasLiveMessages = false;
        boolean hasEvidence = false;
        boolean hasPush = false;

        try {
            if (NKCommonMethods.isJSONOk(jsonObject, "id")) {
                id = jsonObject.getLong("id");
            }

            if (NKCommonMethods.isJSONOk(jsonObject, "has_beacons")) {
                hasBeacons = jsonObject.getBoolean("has_beacons");
            }

            if (NKCommonMethods.isJSONOk(jsonObject, "has_users")) {
                hasUsers = jsonObject.getBoolean("has_users");
            }

            if (NKCommonMethods.isJSONOk(jsonObject, "has_multilanguage")) {
                hasMultilanguage = jsonObject.getBoolean("has_multilanguage");
            }

            if (NKCommonMethods.isJSONOk(jsonObject, "has_live_messages")) {
                hasLiveMessages = jsonObject.getBoolean("has_live_messages");
            }

            if (NKCommonMethods.isJSONOk(jsonObject, "has_evidence")) {
                hasEvidence = jsonObject.getBoolean("has_evidence");
            }

            if (NKCommonMethods.isJSONOk(jsonObject, "has_push")) {
                hasPush = jsonObject.getBoolean("has_push");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new NKProject(id, name, hasBeacons, hasUsers, hasMultilanguage, hasLiveMessages, hasEvidence, hasPush);
    }

    /**
     * Parses a complete block with option to have sections and elements (if getElements = true, getSections should be = true)
     */
    public static NKBlock parseBlock(JSONObject jsonObject, boolean getSections, boolean getElements) {
        long id = -1;
        String title = null, subtitle = null;
        int order = 0;
        ArrayList<NKSection> sections = null;

        try {
            if (NKCommonMethods.isJSONOk(jsonObject, "id")) {
                id = jsonObject.getLong("id");
            }

            if (NKCommonMethods.isJSONOk(jsonObject, "title")) {
                title = jsonObject.getString("title");
            }

            if (NKCommonMethods.isJSONOk(jsonObject, "subtitle")) {
                subtitle = jsonObject.getString("subtitle");
            }

            if (NKCommonMethods.isJSONOk(jsonObject, "order")) {
                order = jsonObject.getInt("order");
            }

            if (getSections) {
                if (NKCommonMethods.isJSONOk(jsonObject, "sections")) {
                    sections = parseSections(jsonObject.getJSONArray("sections"), getElements);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new NKBlock(id, title, subtitle, sections, order);
    }

    /**
     * Parses an array of sections using the names decided on the Nooko3 dashboard
     */
    public static ArrayList<NKSection> parseSections(JSONArray jSections, boolean getElements) {
        ArrayList<NKSection> sections = new ArrayList<>();
        try {
            for (int i = 0; i < jSections.length(); i++) {
                JSONObject jSect = jSections.getJSONObject(i);
                NKSection section = parseSection(jSect, getElements);
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
    public static NKSection parseSection(JSONObject jSection, boolean getElements) {
        Map<String, NKClass> data = new HashMap<>();
        long id = -1;
        int order = 0;
        long available_at = -1;

        try {
            if (NKCommonMethods.isJSONOk(jSection, "id")) {
                id = jSection.getLong("id");
            }

            if (NKCommonMethods.isJSONOk(jSection, "order")) {
                order = jSection.getInt("order");
            }

            if (NKCommonMethods.isJSONOk(jSection, "available_at")) {
                available_at = jSection.getLong("available_at");
            }

            if (getElements) {
                if (NKCommonMethods.isJSONOk(jSection, "elements")) {
                    JSONObject jElements = jSection.getJSONObject("elements");

                    /*Iterates through the elements keys in order to obtain the objects needed*/
                    Iterator<String> iter = jElements.keys();
                    while (iter.hasNext()) {
                        String key = iter.next();
                        try {
                            JSONObject jElem = jElements.getJSONObject(key);
                            NKClass nObj = null;
                            if (NKCommonMethods.isJSONOk(jElem, "type")) {
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

        return new NKSection(id, order, data, available_at);
    }

    /**
     * Gets a map of elements from a JSONObject
     */
    public static Map<String, NKClass> parseElements(JSONObject jObj) {
        Map<String, NKClass> element = new HashMap<>();

        /*Iterates through the elements keys in order to obtain the objects needed*/
        Iterator<String> iter = jObj.keys();
        while (iter.hasNext()) {
            String key = iter.next();
            try {
                JSONObject jElem = jObj.getJSONObject(key);
                NKClass nObj = null;
                if (NKCommonMethods.isJSONOk(jElem, "type")) {
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
    private static NKClass getNClassFromElem(Object value, long id, String name, String type) {
        NKClass nObj = null;
        boolean found = false;

        try {
            if (type.equals(NKConstants.type_text) || type.equals(NKConstants.type_textarea)) {
                found = true;
                nObj = new NKTextElement(id, name, (String) value);
            }

            if (type.equals(NKConstants.type_image)) {
                found = true;
                JSONArray jImages = (JSONArray) value;
                ArrayList<NKImage> NKImages = new ArrayList<>();
                for (int i = 0; i < jImages.length(); i++) {
                    JSONObject jImg = jImages.getJSONObject(i);
                    long img_id = -1;
                    String img_url = null;
                    long img_size = -1;
                    String img_mime_type = null;

                    if (NKCommonMethods.isJSONOk(jImg, "id")) {
                        img_id = jImg.getLong("id");
                    }

                    if (NKCommonMethods.isJSONOk(jImg, "url")) {
                        img_url = jImg.getString("url");
                    }

                    if (NKCommonMethods.isJSONOk(jImg, "size")) {
                        img_size = jImg.getLong("size");
                    }

                    if (NKCommonMethods.isJSONOk(jImg, "mime_type")) {
                        img_mime_type = jImg.getString("mime_type");
                    }

                    NKImages.add(new NKImage(img_id, img_url, img_mime_type, img_size));
                }
                nObj = new NKImages(id, name, NKImages);
            }

            if (type.equals(NKConstants.type_media_audio) ||
                    type.equals(NKConstants.type_media_document) ||
                    type.equals(NKConstants.type_media_file) ||
                    type.equals(NKConstants.type_media_video)) {
                found = true;
                JSONArray jMedia = (JSONArray) value;
                ArrayList<NKFile> NKFiles = new ArrayList<>();
                for (int i = 0; i < jMedia.length(); i++) {
                    JSONObject jF = jMedia.getJSONObject(i);
                    long file_id = -1;
                    String file_url = null;
                    long file_size = -1;
                    String file_mime_type = null;

                    if (NKCommonMethods.isJSONOk(jF, "id")) {
                        file_id = jF.getLong("id");
                    }

                    if (NKCommonMethods.isJSONOk(jF, "url")) {
                        file_url = jF.getString("url");
                    }

                    if (NKCommonMethods.isJSONOk(jF, "size")) {
                        file_size = jF.getLong("size");
                    }

                    if (NKCommonMethods.isJSONOk(jF, "mime_type")) {
                        file_mime_type = jF.getString("mime_type");
                    }

                    NKFiles.add(new NKFile(file_id, file_mime_type, file_size, file_url, type));
                }
                nObj = new NKMediaElement(id, name, NKFiles);
            }

            if (type.equals(NKConstants.type_wysiwyg)) {
                found = true;
                nObj = new NKWYSIWYGElement(id, name, (String) value);
            }

            if (type.equals(NKConstants.type_date)) {
                found = true;
                nObj = new NKDateElement(id, name, (long) value);
            }

            if (type.equals(NKConstants.type_address)) {
                found = true;
                JSONObject jAddress = (JSONObject) value;
                String address = null;
                double latitude = -1, longitude = -1;
                if (NKCommonMethods.isJSONOk(jAddress, "address")) {
                    address = jAddress.getString("address");
                }

                if (NKCommonMethods.isJSONOk(jAddress, "latitude")) {
                    latitude = jAddress.getDouble("latitude");
                }

                if (NKCommonMethods.isJSONOk(jAddress, "longitude")) {
                    longitude = jAddress.getDouble("longitude");
                }

                nObj = new NKAddressElement(id, name, address, latitude, longitude);
            }

            if (type.equals(NKConstants.type_checkbox)) {
                found = true;
                nObj = new NKCheckboxElement(id, name, (boolean) value);
            }

            if (type.equals(NKConstants.type_dropdown)) {
                found = true;
                nObj = new NKDropdownElement(id, name, (String) value);
            }

            if (type.equals(NKConstants.type_poll)) {
                found = true;
                JSONObject jValue = (JSONObject) value;
                JSONArray jAnswers = jValue.getJSONArray("answers");
                JSONArray jResults = jValue.getJSONArray("results");
                ArrayList<NKAnswer> answers = new ArrayList<>();
                for (int i = 0; i < jAnswers.length(); i++) {
                    if (!jAnswers.isNull(i)) {
                        String answer = jAnswers.getString(i);
                        int results = jResults.getInt(i);
                        answers.add(new NKAnswer(i, answer, results));
                    }
                }

                int answer_index = -1;
                if (NKCommonMethods.isJSONOk(jValue, "answered")) {
                    if (jValue.getBoolean("answered")) {
                        if (NKCommonMethods.isJSONOk(jValue, "answer")) {
                            answer_index = jValue.getInt("answer");
                        }
                    }
                }

                nObj = new NKPollAnswers(id, name, answers, jValue.getLong("ends_at"), answer_index);
            }

            if (!found) {
                nObj = new NKGenericElement(id, name, value);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return nObj;
    }

    /**
     * Parse an user from the profile API and returns the "user" object with what wak inserted valorized
     */
    public static NKAuthUser parseUser(JSONObject jUser) {
        try {

            long id = -1;
            String name = null, surname = null, email = null, auth_mode = null, image = null, phone = null, gender = null, data = null;
            if (NKCommonMethods.isJSONOk(jUser, "id")) {
                id = jUser.getLong("id");
            }

            if (NKCommonMethods.isJSONOk(jUser, "name")) {
                name = jUser.getString("name");
            }

            if (NKCommonMethods.isJSONOk(jUser, "surname")) {
                surname = jUser.getString("surname");
            }

            if (NKCommonMethods.isJSONOk(jUser, "email")) {
                email = jUser.getString("email");
            }

            if (NKCommonMethods.isJSONOk(jUser, "auth_mode")) {
                auth_mode = jUser.getString("auth_mode");
            }

            if (NKCommonMethods.isJSONOk(jUser, "phone")) {
                phone = jUser.getString("phone");
            }

            if (NKCommonMethods.isJSONOk(jUser, "gender")) {
                gender = jUser.getString("gender");
            }

            if (NKCommonMethods.isJSONOk(jUser, "data")) {
                data = jUser.getString("data");
            }

            if (NKCommonMethods.isJSONOk(jUser, "image")) {
                image = jUser.getString("image");
            }

            ArrayList<NKStripeSubscription> subscriptions = new ArrayList<>();
            if (NKCommonMethods.isJSONOk(jUser, "subscriptions")) {
                JSONArray jSubscriptions = jUser.getJSONArray("subscriptions");
                for (int i = 0; i < jSubscriptions.length(); i++) {
                    JSONObject jSubscription = jSubscriptions.getJSONObject(i);
                    subscriptions.add(parseSubscription(jSubscription));
                }
            }

            return new NKAuthUser(id, name, surname, email, phone, image, gender, data, auth_mode, subscriptions);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static NKStripeSubscription parseSubscription(JSONObject jsonObject) {
        long id = -1;
        String name = null, stripe_id = null, stripe_plan = null;
        int quantity = 1;
        long created_at = -1;
        boolean ends_at = false, trial_ends_at = false, valid = false, trial = false, grace_period = false, cancelled = false;

        try {
            if (NKCommonMethods.isJSONOk(jsonObject, "id")) {
                id = jsonObject.getLong("id");
            }

            if (NKCommonMethods.isJSONOk(jsonObject, "name")) {
                name = jsonObject.getString("name");
            }

            if (NKCommonMethods.isJSONOk(jsonObject, "stripe_id")) {
                stripe_id = jsonObject.getString("stripe_id");
            }

            if (NKCommonMethods.isJSONOk(jsonObject, "stripe_plan")) {
                stripe_plan = jsonObject.getString("stripe_plan");
            }

            if (NKCommonMethods.isJSONOk(jsonObject, "quantity")) {
                quantity = jsonObject.getInt("quantity");
            }

            if (NKCommonMethods.isJSONOk(jsonObject, "ends_at")) {
                ends_at = jsonObject.getBoolean("ends_at");
            }

            if (NKCommonMethods.isJSONOk(jsonObject, "trial_ends_at")) {
                trial_ends_at = jsonObject.getBoolean("trial_ends_at");
            }

            if (NKCommonMethods.isJSONOk(jsonObject, "created_at")) {
                created_at = TimeUnit.SECONDS.toMillis(jsonObject.getLong("created_at"));
            }

            if (NKCommonMethods.isJSONOk(jsonObject, "valid")) {
                valid = jsonObject.getBoolean("valid");
            }

            if (NKCommonMethods.isJSONOk(jsonObject, "trial")) {
                trial = jsonObject.getBoolean("trial");
            }

            if (NKCommonMethods.isJSONOk(jsonObject, "grace_period")) {
                grace_period = jsonObject.getBoolean("grace_period");
            }

            if (NKCommonMethods.isJSONOk(jsonObject, "cancelled")) {
                cancelled = jsonObject.getBoolean("cancelled");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new NKStripeSubscription(id, name, stripe_id, stripe_plan, quantity, ends_at, trial_ends_at, created_at,
                valid, trial, grace_period, cancelled);
    }
}