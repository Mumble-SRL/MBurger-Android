package mumble.mburger.sdk.Common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBAuth.MBAuthData.MBAuthUser;
import mumble.mburger.sdk.MBAuth.MBAuthData.MBContractsAccepted;
import mumble.mburger.sdk.MBClient.MBData.MBAtomic.MBClass;
import mumble.mburger.sdk.MBClient.MBData.MBBlocks.MBBlock;
import mumble.mburger.sdk.MBClient.MBData.MBElements.MBAddressElement;
import mumble.mburger.sdk.MBClient.MBData.MBElements.MBCheckboxElement;
import mumble.mburger.sdk.MBClient.MBData.MBElements.MBDateElement;
import mumble.mburger.sdk.MBClient.MBData.MBElements.MBDropdownElement;
import mumble.mburger.sdk.MBClient.MBData.MBElements.MBGenericElement;
import mumble.mburger.sdk.MBClient.MBData.MBElements.MBImages;
import mumble.mburger.sdk.MBClient.MBData.MBElements.MBMediaElement;
import mumble.mburger.sdk.MBClient.MBData.MBElements.MBPollAnswers;
import mumble.mburger.sdk.MBClient.MBData.MBElements.MBSubElements.MBAnswer;
import mumble.mburger.sdk.MBClient.MBData.MBElements.MBSubElements.MBFile;
import mumble.mburger.sdk.MBClient.MBData.MBElements.MBSubElements.MBImage;
import mumble.mburger.sdk.MBClient.MBData.MBElements.MBTextElement;
import mumble.mburger.sdk.MBClient.MBData.MBElements.MBWYSIWYGElement;
import mumble.mburger.sdk.MBClient.MBData.MBProjects.MBContract;
import mumble.mburger.sdk.MBClient.MBData.MBProjects.MBProject;
import mumble.mburger.sdk.MBClient.MBData.MBSections.MBSection;
import mumble.mburger.sdk.MBClient.MBData.MBShopify.MBShopifyCollection;
import mumble.mburger.sdk.MBPay.MBPayData.MBShopifyShipping;
import mumble.mburger.sdk.MBPay.MBPayData.MBShopifyShippingMethods;
import mumble.mburger.sdk.MBPay.MBPayData.MBStripeCard;
import mumble.mburger.sdk.MBPay.MBPayData.MBStripeSubscription;

/**
 * Parsing class for converting API response in objects.
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBParser {

    /**
     * Parses the project
     */
    public static MBProject parseProject(JSONObject jsonObject) {
        long id = -1;
        String name = null;
        boolean hasBeacons = false;
        boolean hasUsers = false;
        boolean hasMultilanguage = false;
        boolean hasLiveMessages = false;
        boolean hasEvidence = false;
        boolean hasPush = false;
        boolean hasPayments = false;
        boolean hasShopify = false;

        long evidence_id = -1;
        long evidence_block_id = -1;
        long evidence_section_id = -1;
        String evidence_title = null;
        String evidence_image = null;
        ArrayList<MBContract> contracts = new ArrayList<>();
        ArrayList<MBShopifyCollection> collections = new ArrayList<>();

        try {
            if (MBCommonMethods.isJSONOk(jsonObject, "id")) {
                id = jsonObject.getLong("id");
            }

            if (MBCommonMethods.isJSONOk(jsonObject, "has_beacons")) {
                hasBeacons = jsonObject.getBoolean("has_beacons");
            }

            if (MBCommonMethods.isJSONOk(jsonObject, "has_users")) {
                hasUsers = jsonObject.getBoolean("has_users");
            }

            if (MBCommonMethods.isJSONOk(jsonObject, "has_multilanguage")) {
                hasMultilanguage = jsonObject.getBoolean("has_multilanguage");
            }

            if (MBCommonMethods.isJSONOk(jsonObject, "has_live_messages")) {
                hasLiveMessages = jsonObject.getBoolean("has_live_messages");
            }

            if (MBCommonMethods.isJSONOk(jsonObject, "has_evidence")) {
                hasEvidence = jsonObject.getBoolean("has_evidence");
            }

            if (MBCommonMethods.isJSONOk(jsonObject, "has_push")) {
                hasPush = jsonObject.getBoolean("has_push");
            }

            if (MBCommonMethods.isJSONOk(jsonObject, "evidence_id")) {
                evidence_id = jsonObject.getLong("evidence_id");
            }

            if (MBCommonMethods.isJSONOk(jsonObject, "evidence_block_id")) {
                evidence_block_id = jsonObject.getLong("evidence_block_id");
            }

            if (MBCommonMethods.isJSONOk(jsonObject, "evidence_section_id")) {
                evidence_section_id = jsonObject.getLong("evidence_section_id");
            }

            if (MBCommonMethods.isJSONOk(jsonObject, "evidence_title")) {
                evidence_title = jsonObject.getString("evidence_title");
            }

            if (MBCommonMethods.isJSONOk(jsonObject, "evidence_image")) {
                evidence_image = jsonObject.getString("evidence_image");
            }

            if (MBCommonMethods.isJSONOk(jsonObject, "has_payments")) {
                hasPayments = jsonObject.getBoolean("has_payments");
            }

            if (MBCommonMethods.isJSONOk(jsonObject, "has_shopify")) {
                hasShopify = jsonObject.getBoolean("has_shopify");
            }

            if (MBCommonMethods.isJSONOk(jsonObject, "contracts")) {
                JSONArray jContracts = jsonObject.getJSONArray("contracts");
                for (int i = 0; i < jContracts.length(); i++) {
                    JSONObject jContract = jContracts.getJSONObject(i);
                    long con_id = -1, con_created_at = -1, con_updated_at = -1;
                    String con_name = null, con_link = null, con_text = null;
                    boolean con_active = false;

                    if (MBCommonMethods.isJSONOk(jContract, "id")) {
                        con_id = jContract.getLong("id");
                    }

                    if (MBCommonMethods.isJSONOk(jContract, "name")) {
                        con_name = jContract.getString("name");
                    }

                    if (MBCommonMethods.isJSONOk(jContract, "link")) {
                        con_link = jContract.getString("link");
                    }

                    if (MBCommonMethods.isJSONOk(jContract, "text")) {
                        con_text = jContract.getString("text");
                    }

                    if (MBCommonMethods.isJSONOk(jContract, "active")) {
                        con_active = jContract.getBoolean("active");
                    }

                    if (MBCommonMethods.isJSONOk(jContract, "created_at")) {
                        con_created_at = jContract.getLong("created_at");
                    }

                    if (MBCommonMethods.isJSONOk(jContract, "updated_at")) {
                        con_updated_at = jContract.getLong("updated_at");
                    }

                    contracts.add(new MBContract(con_id, con_name, con_link, con_text, con_active, con_created_at, con_updated_at));
                }
            }

            if (MBCommonMethods.isJSONOk(jsonObject, "shopify_menu")) {
                JSONArray jShopifyMenu = jsonObject.getJSONArray("shopify_menu");
                collections = new ArrayList<>();
                for (int i = 0; i < jShopifyMenu.length(); i++) {
                    JSONObject jMenu = jShopifyMenu.getJSONObject(i);
                    collections.add(parseShopifyMenu(jMenu));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new MBProject(id, name, hasBeacons, hasUsers, hasMultilanguage, hasLiveMessages, hasEvidence, hasPush,
                hasPayments, hasShopify, evidence_id, evidence_block_id, evidence_section_id, evidence_title, evidence_image,
                contracts, collections);
    }

    /**
     * Parses a complete block with option to have sections and elements (if getElements = true, getSections should be = true)
     */
    public static MBBlock parseBlock(JSONObject jsonObject, boolean getSections, boolean getElements) {
        long id = -1;
        String title = null, subtitle = null;
        int order = 0;
        ArrayList<MBSection> sections = null;

        try {
            if (MBCommonMethods.isJSONOk(jsonObject, "id")) {
                id = jsonObject.getLong("id");
            }

            if (MBCommonMethods.isJSONOk(jsonObject, "title")) {
                title = jsonObject.getString("title");
            }

            if (MBCommonMethods.isJSONOk(jsonObject, "subtitle")) {
                subtitle = jsonObject.getString("subtitle");
            }

            if (MBCommonMethods.isJSONOk(jsonObject, "order")) {
                order = jsonObject.getInt("order");
            }

            if (getSections) {
                if (MBCommonMethods.isJSONOk(jsonObject, "sections")) {
                    sections = parseSections(jsonObject.getJSONArray("sections"), getElements);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new MBBlock(id, title, subtitle, sections, order);
    }

    /**
     * Parses an array of sections using the names decided on the MBurger dashboard
     */
    public static ArrayList<MBSection> parseSections(JSONArray jSections, boolean getElements) {
        ArrayList<MBSection> sections = new ArrayList<>();
        try {
            for (int i = 0; i < jSections.length(); i++) {
                JSONObject jSect = jSections.getJSONObject(i);
                MBSection section = parseSection(jSect, getElements);
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
    public static MBSection parseSection(JSONObject jSection, boolean getElements) {
        Map<String, MBClass> data = new HashMap<>();
        long id = -1;
        int order = 0;
        long available_at = -1;

        try {
            if (MBCommonMethods.isJSONOk(jSection, "id")) {
                id = jSection.getLong("id");
            }

            if (MBCommonMethods.isJSONOk(jSection, "order")) {
                order = jSection.getInt("order");
            }

            if (MBCommonMethods.isJSONOk(jSection, "available_at")) {
                available_at = jSection.getLong("available_at");
            }

            if (getElements) {
                if (MBCommonMethods.isJSONOk(jSection, "elements")) {
                    JSONObject jElements = jSection.getJSONObject("elements");

                    /*Iterates through the elements keys in order to obtain the objects needed*/
                    Iterator<String> iter = jElements.keys();
                    while (iter.hasNext()) {
                        String key = iter.next();
                        try {
                            JSONObject jElem = jElements.getJSONObject(key);
                            MBClass nObj = null;
                            if (MBCommonMethods.isJSONOk(jElem, "type")) {
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

        return new MBSection(id, order, data, available_at);
    }

    /**
     * Gets a map of elements from a JSONObject
     */
    public static Map<String, MBClass> parseElements(JSONObject jObj) {
        Map<String, MBClass> element = new HashMap<>();

        /*Iterates through the elements keys in order to obtain the objects needed*/
        Iterator<String> iter = jObj.keys();
        while (iter.hasNext()) {
            String key = iter.next();
            try {
                JSONObject jElem = jObj.getJSONObject(key);
                MBClass nObj = null;
                if (MBCommonMethods.isJSONOk(jElem, "type")) {
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
    private static MBClass getNClassFromElem(Object value, long id, String name, String type) {
        MBClass nObj = null;
        boolean found = false;

        try {
            if (type.equals(MBConstants.type_text)
                    || type.equals(MBConstants.type_textarea)
                    || type.equals(MBConstants.type_markdown)) {
                found = true;
                nObj = new MBTextElement(id, name, (String) value);
            }

            if (type.equals(MBConstants.type_image)) {
                found = true;
                JSONArray jImages = (JSONArray) value;
                ArrayList<MBImage> imgs = new ArrayList<>();
                for (int i = 0; i < jImages.length(); i++) {
                    JSONObject jImg = jImages.getJSONObject(i);
                    long img_id = -1;
                    String img_url = null;
                    long img_size = -1;
                    String img_mime_type = null;

                    if (MBCommonMethods.isJSONOk(jImg, "id")) {
                        img_id = jImg.getLong("id");
                    }

                    if (MBCommonMethods.isJSONOk(jImg, "url")) {
                        img_url = jImg.getString("url");
                    }

                    if (MBCommonMethods.isJSONOk(jImg, "size")) {
                        img_size = jImg.getLong("size");
                    }

                    if (MBCommonMethods.isJSONOk(jImg, "mime_type")) {
                        img_mime_type = jImg.getString("mime_type");
                    }

                    imgs.add(new MBImage(img_id, img_url, img_mime_type, img_size));
                }
                nObj = new MBImages(id, name, imgs);
            }

            if (type.equals(MBConstants.type_media_audio) ||
                    type.equals(MBConstants.type_media_document) ||
                    type.equals(MBConstants.type_media_file) ||
                    type.equals(MBConstants.type_media_video)) {
                found = true;
                JSONArray jMedia = (JSONArray) value;
                ArrayList<MBFile> MBFiles = new ArrayList<>();
                for (int i = 0; i < jMedia.length(); i++) {
                    JSONObject jF = jMedia.getJSONObject(i);
                    long file_id = -1;
                    String file_url = null;
                    long file_size = -1;
                    String file_mime_type = null;

                    if (MBCommonMethods.isJSONOk(jF, "id")) {
                        file_id = jF.getLong("id");
                    }

                    if (MBCommonMethods.isJSONOk(jF, "url")) {
                        file_url = jF.getString("url");
                    }

                    if (MBCommonMethods.isJSONOk(jF, "size")) {
                        file_size = jF.getLong("size");
                    }

                    if (MBCommonMethods.isJSONOk(jF, "mime_type")) {
                        file_mime_type = jF.getString("mime_type");
                    }

                    MBFiles.add(new MBFile(file_id, file_mime_type, file_size, file_url, type));
                }
                nObj = new MBMediaElement(id, name, MBFiles);
            }

            if (type.equals(MBConstants.type_wysiwyg)) {
                found = true;
                nObj = new MBWYSIWYGElement(id, name, (String) value);
            }

            if (type.equals(MBConstants.type_date)) {
                found = true;
                nObj = new MBDateElement(id, name, (long) value);
            }

            if (type.equals(MBConstants.type_address)) {
                found = true;
                JSONObject jAddress = (JSONObject) value;
                String address = null;
                double latitude = -1, longitude = -1;
                if (MBCommonMethods.isJSONOk(jAddress, "address")) {
                    address = jAddress.getString("address");
                }

                if (MBCommonMethods.isJSONOk(jAddress, "latitude")) {
                    latitude = jAddress.getDouble("latitude");
                }

                if (MBCommonMethods.isJSONOk(jAddress, "longitude")) {
                    longitude = jAddress.getDouble("longitude");
                }

                nObj = new MBAddressElement(id, name, address, latitude, longitude);
            }

            if (type.equals(MBConstants.type_checkbox)) {
                found = true;
                nObj = new MBCheckboxElement(id, name, (boolean) value);
            }

            if (type.equals(MBConstants.type_dropdown)) {
                found = true;
                nObj = new MBDropdownElement(id, name, (String) value);
            }

            if (type.equals(MBConstants.type_poll)) {
                found = true;
                JSONObject jValue = (JSONObject) value;
                JSONArray jAnswers = jValue.getJSONArray("answers");
                JSONArray jResults = jValue.getJSONArray("results");
                ArrayList<MBAnswer> answers = new ArrayList<>();
                for (int i = 0; i < jAnswers.length(); i++) {
                    if (!jAnswers.isNull(i)) {
                        String answer = jAnswers.getString(i);
                        int results = jResults.getInt(i);
                        answers.add(new MBAnswer(i, answer, results));
                    }
                }

                int answer_index = -1;
                if (MBCommonMethods.isJSONOk(jValue, "answered")) {
                    if (jValue.getBoolean("answered")) {
                        if (MBCommonMethods.isJSONOk(jValue, "answer")) {
                            answer_index = jValue.getInt("answer");
                        }
                    }
                }

                nObj = new MBPollAnswers(id, name, answers, jValue.getLong("ends_at"), answer_index);
            }

            if (!found) {
                nObj = new MBGenericElement(id, name, value);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return nObj;
    }

    /**
     * Parse an user from the profile API and returns the "user" object with what wak inserted valorized
     */
    public static MBAuthUser parseUser(JSONObject jUser) {
        try {

            long id = -1;
            String name = null, surname = null, email = null, auth_mode = null, image = null, phone = null, gender = null, data = null;
            if (MBCommonMethods.isJSONOk(jUser, "id")) {
                id = jUser.getLong("id");
            }

            if (MBCommonMethods.isJSONOk(jUser, "name")) {
                name = jUser.getString("name");
            }

            if (MBCommonMethods.isJSONOk(jUser, "surname")) {
                surname = jUser.getString("surname");
            }

            if (MBCommonMethods.isJSONOk(jUser, "email")) {
                email = jUser.getString("email");
            }

            if (MBCommonMethods.isJSONOk(jUser, "auth_mode")) {
                auth_mode = jUser.getString("auth_mode");
            }

            if (MBCommonMethods.isJSONOk(jUser, "phone")) {
                phone = jUser.getString("phone");
            }

            if (MBCommonMethods.isJSONOk(jUser, "gender")) {
                gender = jUser.getString("gender");
            }

            if (MBCommonMethods.isJSONOk(jUser, "data")) {
                data = jUser.getString("data");
            }

            if (MBCommonMethods.isJSONOk(jUser, "image")) {
                image = jUser.getString("image");
            }

            ArrayList<MBStripeSubscription> subscriptions = new ArrayList<>();
            if (MBCommonMethods.isJSONOk(jUser, "subscriptions")) {
                JSONArray jSubscriptions = jUser.getJSONArray("subscriptions");
                for (int i = 0; i < jSubscriptions.length(); i++) {
                    JSONObject jSubscription = jSubscriptions.getJSONObject(i);
                    subscriptions.add(parseSubscription(jSubscription));
                }
            }

            ArrayList<MBContractsAccepted> contracts = new ArrayList<>();
            if (MBCommonMethods.isJSONOk(jUser, "contracts")) {
                JSONArray jContracts = jUser.getJSONArray("contracts");
                for (int i = 0; i < jContracts.length(); i++) {
                    JSONObject jContract = jContracts.getJSONObject(i);
                    long c_id = -1;
                    boolean c_accepted = false;
                    if (MBCommonMethods.isJSONOk(jContract, "id")) {
                        c_id = jContract.getLong("id");
                    }

                    if (MBCommonMethods.isJSONOk(jContract, "accepted")) {
                        c_accepted = jContract.getBoolean("accepted");
                    }

                    contracts.add(new MBContractsAccepted(c_id, c_accepted));
                }
            }


            return new MBAuthUser(id, name, surname, email, phone, image, gender, data, auth_mode, subscriptions, contracts);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static MBStripeSubscription parseSubscription(JSONObject jsonObject) {
        long id = -1;
        String name = null, stripe_id = null, stripe_plan = null;
        int quantity = 1;
        long created_at = -1;
        long ends_at = -1, trial_ends_at = -1, expires_at = -1;
        boolean valid = false, trial = false, grace_period = false, cancelled = false;

        try {
            if (MBCommonMethods.isJSONOk(jsonObject, "id")) {
                id = jsonObject.getLong("id");
            }

            if (MBCommonMethods.isJSONOk(jsonObject, "name")) {
                name = jsonObject.getString("name");
            }

            if (MBCommonMethods.isJSONOk(jsonObject, "stripe_id")) {
                stripe_id = jsonObject.getString("stripe_id");
            }

            if (MBCommonMethods.isJSONOk(jsonObject, "stripe_plan")) {
                stripe_plan = jsonObject.getString("stripe_plan");
            }

            if (MBCommonMethods.isJSONOk(jsonObject, "quantity")) {
                quantity = jsonObject.getInt("quantity");
            }

            if (MBCommonMethods.isJSONOk(jsonObject, "ends_at")) {
                ends_at = TimeUnit.SECONDS.toMillis(jsonObject.getLong("ends_at"));
            }

            if (MBCommonMethods.isJSONOk(jsonObject, "trial_ends_at")) {
                trial_ends_at = TimeUnit.SECONDS.toMillis(jsonObject.getLong("trial_ends_at"));
            }

            if (MBCommonMethods.isJSONOk(jsonObject, "created_at")) {
                created_at = TimeUnit.SECONDS.toMillis(jsonObject.getLong("created_at"));
            }

            if (MBCommonMethods.isJSONOk(jsonObject, "expires_at")) {
                expires_at = TimeUnit.SECONDS.toMillis(jsonObject.getLong("expires_at"));
            }

            if (MBCommonMethods.isJSONOk(jsonObject, "valid")) {
                valid = jsonObject.getBoolean("valid");
            }

            if (MBCommonMethods.isJSONOk(jsonObject, "trial")) {
                trial = jsonObject.getBoolean("trial");
            }

            if (MBCommonMethods.isJSONOk(jsonObject, "grace_period")) {
                grace_period = jsonObject.getBoolean("grace_period");
            }

            if (MBCommonMethods.isJSONOk(jsonObject, "cancelled")) {
                cancelled = jsonObject.getBoolean("cancelled");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new MBStripeSubscription(id, name, stripe_id, stripe_plan, quantity, ends_at, trial_ends_at, created_at,
                expires_at, valid, trial, grace_period, cancelled);
    }

    public static ArrayList<MBStripeCard> parseCards(JSONArray jArr) {
        ArrayList<MBStripeCard> cards = new ArrayList<>();
        try {
            for (int i = 0; i < jArr.length(); i++) {
                JSONObject jObj = jArr.getJSONObject(i);
                String id = jObj.getString("id");
                String brand = jObj.getString("brand");
                String last4 = jObj.getString("last4");
                int exp_month = jObj.getInt("exp_month");
                int exp_year = jObj.getInt("exp_year");
                boolean isDeafult = jObj.getBoolean("default");
                cards.add(new MBStripeCard(id, last4, brand, exp_month, exp_year, isDeafult));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cards;
    }

    public static MBShopifyCollection parseShopifyMenu(JSONObject jChild) {
        long id = -1;
        String text = null, href = null, icon = null, target = null, title = null, image = null;
        ArrayList<MBShopifyCollection> children = null;

        try {
            if (MBCommonMethods.isJSONOk(jChild, "id")) {
                id = jChild.getLong("id");
            }
            if (MBCommonMethods.isJSONOk(jChild, "text")) {
                text = jChild.getString("text");
            }
            if (MBCommonMethods.isJSONOk(jChild, "href")) {
                href = jChild.getString("href");
            }
            if (MBCommonMethods.isJSONOk(jChild, "icon")) {
                icon = jChild.getString("icon");
            }
            if (MBCommonMethods.isJSONOk(jChild, "target")) {
                target = jChild.getString("target");
            }
            if (MBCommonMethods.isJSONOk(jChild, "title")) {
                title = jChild.getString("title");
            }
            if (MBCommonMethods.isJSONOk(jChild, "image")) {
                image = jChild.getString("image");
            }
            if (MBCommonMethods.isJSONOk(jChild, "children")) {
                children = new ArrayList<>();
                JSONArray jChildren = jChild.getJSONArray("children");
                for (int i = 0; i < jChildren.length(); i++) {
                    JSONObject jSubChild = jChildren.getJSONObject(i);
                    children.add(parseShopifyMenu(jSubChild));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new MBShopifyCollection(id, text, href, icon, target, title, image, children);
    }

    public static MBShopifyShippingMethods parseShippingMethods(JSONObject jChild) {
        ArrayList<MBShopifyShipping> weights = new ArrayList<>();
        ArrayList<MBShopifyShipping> prices = new ArrayList<>();

        try {
            if (MBCommonMethods.isJSONOk(jChild, "weights")) {
                JSONArray jWeights = jChild.getJSONArray("weights");
                for (int i = 0; i < jWeights.length(); i++) {
                    JSONObject jWeight = jWeights.getJSONObject(i);
                    weights.add(parseShipping(jWeight));
                }
            }
            if (MBCommonMethods.isJSONOk(jChild, "prices")) {
                JSONArray jPrices = jChild.getJSONArray("prices");
                for (int i = 0; i < jPrices.length(); i++) {
                    JSONObject jPrice = jPrices.getJSONObject(i);
                    prices.add(parseShipping(jPrice));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new MBShopifyShippingMethods(weights, prices);
    }

    public static MBShopifyShipping parseShipping(JSONObject jChild) {
        long id = -1, shipping_zone_id = -1;
        String name = null, price = null;

        try {
            if (MBCommonMethods.isJSONOk(jChild, "id")) {
                id = jChild.getLong("id");
            }

            if (MBCommonMethods.isJSONOk(jChild, "name")) {
                name = jChild.getString("name");
            }

            if (MBCommonMethods.isJSONOk(jChild, "price")) {
                price = jChild.getString("price");
            }

            if (MBCommonMethods.isJSONOk(jChild, "shipping_zone_id")) {
                shipping_zone_id = jChild.getLong("shipping_zone_id");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new MBShopifyShipping(id, name, price, shipping_zone_id);
    }
}