package mumble.mburger.sdk.MBClient.MBData.MBProjects;

import java.io.Serializable;
import java.util.ArrayList;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBClient.MBData.MBShopify.MBShopifyCollection;

/**
 * Identifies a MBurger project, pretty much it's an abstraction of a MBurger app
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBProject implements Serializable {

    /**
     * Unique id of the project
     */
    private long id;

    /**
     * Name of the project
     */
    private String name;

    /**
     * If the project has support for beacons
     */
    private boolean hasBeacons;

    /**
     * Is the project may have login and user management
     */
    private boolean hasUsers;

    /**
     * If the project is multilangual
     */
    private boolean hasMultilanguage;

    /**
     * If the project has live message functionality
     */
    private boolean hasLiveMessages;

    /**
     * UNKNOWN
     */
    private boolean hasEvidence;

    /**
     * If the project has support for push notifications
     */
    private boolean hasPush;

    /**
     * If the project has payment with Stripe plugin
     */
    private boolean hasPayment;

    /**
     * If the project has Shopify plugin
     */
    private boolean hasShopify;

    /**
     * Shopify collections
     */
    private ArrayList<MBShopifyCollection> shopifyCollections;

    private long evidence_id, evidence_block_id, evidence_section_id;
    private String evidence_title, evidence_image;

    /**
     * Project has license contracts
     */
    private ArrayList<MBContract> contracts;

    public MBProject(long id, String name, boolean hasBeacons, boolean hasUsers, boolean hasMultilanguage,
                     boolean hasLiveMessages, boolean hasEvidence, boolean hasPush, boolean has_payments, boolean has_shopify,
                     long evidence_id, long evidence_block_id, long evidence_section_id, String evidence_title,
                     String evidence_image, ArrayList<MBContract> contracts, ArrayList<MBShopifyCollection> shopifyCollections) {
        this.id = id;
        this.name = name;
        this.hasBeacons = hasBeacons;
        this.hasUsers = hasUsers;
        this.hasMultilanguage = hasMultilanguage;
        this.hasLiveMessages = hasLiveMessages;
        this.hasEvidence = hasEvidence;
        this.hasPush = hasPush;
        this.hasPayment = has_payments;
        this.hasShopify = has_shopify;
        this.evidence_id = evidence_id;
        this.evidence_block_id = evidence_block_id;
        this.evidence_section_id = evidence_section_id;
        this.evidence_title = evidence_title;
        this.evidence_image = evidence_image;
        this.contracts = contracts;
        this.shopifyCollections = shopifyCollections;
    }

    /**
     * Get project id
     */
    public long getId() {
        return id;
    }

    /**
     * Set project id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get project name
     */
    public String getName() {
        return name;
    }

    /**
     * Set project name
     */
    public void setName(String name) {
        this.name = name;
    }

    public boolean hasBeacons() {
        return hasBeacons;
    }

    public void setHasBeacons(boolean hasBeacons) {
        this.hasBeacons = hasBeacons;
    }

    public boolean hasUsers() {
        return hasUsers;
    }

    public void setHasUsers(boolean hasUsers) {
        this.hasUsers = hasUsers;
    }

    public boolean hasMultilanguage() {
        return hasMultilanguage;
    }

    public void setHasMultilanguage(boolean hasMultilanguage) {
        this.hasMultilanguage = hasMultilanguage;
    }

    public boolean hasLiveMessages() {
        return hasLiveMessages;
    }

    public void setHasLiveMessages(boolean hasLiveMessages) {
        this.hasLiveMessages = hasLiveMessages;
    }

    public boolean hasEvidence() {
        return hasEvidence;
    }

    public void setHasEvidence(boolean hasEvidence) {
        this.hasEvidence = hasEvidence;
    }

    public boolean hasPush() {
        return hasPush;
    }

    public void setHasPush(boolean hasPush) {
        this.hasPush = hasPush;
    }

    public long getEvidence_id() {
        return evidence_id;
    }

    public void setEvidence_id(long evidence_id) {
        this.evidence_id = evidence_id;
    }

    public long getEvidence_block_id() {
        return evidence_block_id;
    }

    public void setEvidence_block_id(long evidence_block_id) {
        this.evidence_block_id = evidence_block_id;
    }

    public long getEvidence_section_id() {
        return evidence_section_id;
    }

    public void setEvidence_section_id(long evidence_section_id) {
        this.evidence_section_id = evidence_section_id;
    }

    public String getEvidence_title() {
        return evidence_title;
    }

    public void setEvidence_title(String evidence_title) {
        this.evidence_title = evidence_title;
    }

    public String getEvidence_image() {
        return evidence_image;
    }

    public void setEvidence_image(String evidence_image) {
        this.evidence_image = evidence_image;
    }

    public ArrayList<MBContract> getContracts() {
        return contracts;
    }

    public void setContracts(ArrayList<MBContract> contracts) {
        this.contracts = contracts;
    }

    public boolean isHasPayment() {
        return hasPayment;
    }

    public void setHasPayment(boolean hasPayment) {
        this.hasPayment = hasPayment;
    }

    public boolean isHasShopify() {
        return hasShopify;
    }

    public void setHasShopify(boolean hasShopify) {
        this.hasShopify = hasShopify;
    }

    public ArrayList<MBShopifyCollection> getShopifyCollections() {
        return shopifyCollections;
    }

    public void setShopifyCollections(ArrayList<MBShopifyCollection> shopifyCollections) {
        this.shopifyCollections = shopifyCollections;
    }
}
