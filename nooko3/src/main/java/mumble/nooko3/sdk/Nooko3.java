package mumble.nooko3.sdk;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javadz.beanutils.BeanUtils;
import mumble.nooko3.R;
import mumble.nooko3.sdk.NAsyncTasks.ATask_getBlock;
import mumble.nooko3.sdk.NAsyncTasks.ATask_getSection;
import mumble.nooko3.sdk.NConstants.NConst;
import mumble.nooko3.sdk.NConstants.NMappingArgs;
import mumble.nooko3.sdk.NConstants.NUserConst;
import mumble.nooko3.sdk.NControllers.NApiManager.NAMActivityUtils;
import mumble.nooko3.sdk.NControllers.NApiManager.NAMCONF;
import mumble.nooko3.sdk.NControllers.NApiResultsLIsteners.NApiBlockResultListener;
import mumble.nooko3.sdk.NControllers.NApiResultsLIsteners.NApiBlocksResultListener;
import mumble.nooko3.sdk.NControllers.NApiResultsLIsteners.NApiProjectResultListener;
import mumble.nooko3.sdk.NControllers.NApiResultsLIsteners.NApiSectionResultListener;
import mumble.nooko3.sdk.NControllers.NApiResultsLIsteners.NApiSectionsResultListener;
import mumble.nooko3.sdk.NControllers.NGenericApiResultListener;
import mumble.nooko3.sdk.NControllers.NFieldsMapping;
import mumble.nooko3.sdk.NData.NAtomic.NClass;
import mumble.nooko3.sdk.NData.NElements.NEAddress;
import mumble.nooko3.sdk.NData.NElements.NECheckbox;
import mumble.nooko3.sdk.NData.NElements.NEDate;
import mumble.nooko3.sdk.NData.NElements.NEDropdown;
import mumble.nooko3.sdk.NData.NElements.NEGeneric;
import mumble.nooko3.sdk.NData.NElements.NEImages;
import mumble.nooko3.sdk.NData.NElements.NEMedia;
import mumble.nooko3.sdk.NData.NElements.NEText;
import mumble.nooko3.sdk.NData.NElements.NEWYSIWYG;
import mumble.nooko3.sdk.NData.NSections.NSection;
import mumble.nooko3.sdk.NExceptions.NSDKInitializeException;
import mumble.nooko3.sdk.NAsyncTasks.ATask_getBlocks;
import mumble.nooko3.sdk.NAsyncTasks.ATask_getProject;
import mumble.nooko3.sdk.NAsyncTasks.ATask_getSections;

/**
 * Basic init class for Nooko3, which sets an array of constants used runtime.
 *
 * @author Enrico Ori
 * @version {@value NConst#version}
 */
public class Nooko3 {

    /**
     * Initialize the SDK with the default parameters
     * Caching = false
     * CachingTime = 3 days
     */
    public static void initialize(String api_key) {
        NUserConst.apiKey = api_key;
    }

    /**
     * Initialize the SDK with user parameters about caching on/off with the default caching time (3 days)
     */
    public static void initialize(String api_key, boolean cachingEnabled) {
        NUserConst.apiKey = api_key;
        NUserConst.cachingEnabled = cachingEnabled;
    }

    /**
     * Initialize the SDK with user parameters about caching duration, default caching is OFF but this method turn it ON
     */
    public static void initialize(String api_key, long cachingDuration) {
        NUserConst.apiKey = api_key;
        NUserConst.cachingEnabled = true;
        NUserConst.cachingTime = cachingDuration;
    }

    /**
     * Initialize an activity/fragment to receive project informations from API
     */
    public static BroadcastReceiver initializeNookoReceiverForProject(Activity activity, NGenericApiResultListener listener) {
        if(NUserConst.apiKey != null) {
            String[] receivers = new String[]{NAMCONF.ACTION_GET_PROJECT};
            return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        }
        else{
            throw new NSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive blocks from API
     */
    public static BroadcastReceiver initializeNookoReceiverForBlocks(Activity activity, NGenericApiResultListener listener) {
        if(NUserConst.apiKey != null) {
            String[] receivers = new String[]{NAMCONF.ACTION_GET_BLOCKS};
            return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        }
        else{
            throw new NSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive a single block from API
     */
    public static BroadcastReceiver initializeNookoReceiverForBlock(Activity activity, NGenericApiResultListener listener) {
        if(NUserConst.apiKey != null) {
            String[] receivers = new String[]{NAMCONF.ACTION_GET_BLOCK};
            return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        }
        else{
            throw new NSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive sections from API
     */
    public static BroadcastReceiver initializeNookoReceiverForSections(Activity activity, NGenericApiResultListener listener) {
        if(NUserConst.apiKey != null) {
            String[] receivers = new String[]{NAMCONF.ACTION_GET_SECTIONS};
            return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        }
        else{
            throw new NSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive a single section from API
     */
    public static BroadcastReceiver initializeNookoReceiverForSection(Activity activity, NGenericApiResultListener listener) {
        if(NUserConst.apiKey != null) {
            String[] receivers = new String[]{NAMCONF.ACTION_GET_SECTION};
            return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        }
        else{
            throw new NSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive custom data with custom actions
     */
    public static BroadcastReceiver initializeNookoReceiverCustom(Activity activity, NGenericApiResultListener listener, String[] receivers) {
        if(NUserConst.apiKey != null) {
            return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        }
        else{
            throw new NSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Removes the Nooko receiver from the activity/fragment, must pass BroadcastReceiver from initialization
     */
    public static void pauseNookoReceiver(Activity activity, BroadcastReceiver broadcastReceiver) {
        if(NUserConst.apiKey != null) {
            NAMActivityUtils.unregisterForApiManager(activity, broadcastReceiver);
        }
        else{
            throw new NSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Maps a section into a custom user object. You must provide a Map of "section keys" -> "object fields" in order to do a correct
     * matching with the section data, make also sure that all the fields are the same type as the matching field.
     * You should not make objects with objects inside (a part from the Nooko classes) with this method.
     *
     * @param section           is the section you wish to map
     * @param fieldsMap         is a map which contains the user object fields as keys and the section key as value,
     *                          images, addresses and media has special fields to obtain the first element or
     *                          just part of the object
     * @param destinationObject is an empty user object, which has at least getters and setters
     * @param getSimpleValues   represents if for media and images you wish to have only the url of the media or the whole object
     */
    public static Object mapToCustomObject(NSection section, NFieldsMapping fieldsMap,
                                           Object destinationObject, boolean getSimpleValues) {
        Field[] fields = destinationObject.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (fieldsMap.containsKey(field.getName())) {
                String sectionKey = fieldsMap.get(field.getName());
                try {
                    if (sectionKey.contains(".")) {
                        String firstPart = sectionKey.substring(0, sectionKey.indexOf("."));
                        String secondPart = sectionKey.substring(sectionKey.indexOf(".") + 1);
                        NClass sectionObject = section.getField(firstPart);

                        if (sectionObject instanceof NEImages) {
                            NEImages nImages = (NEImages) sectionObject;
                            if (secondPart.equals(NMappingArgs.mapping_first_image_media)) {
                                if (getSimpleValues) {
                                    BeanUtils.setProperty(destinationObject, field.getName(), nImages.getFirstImage().getUrl());
                                } else {
                                    BeanUtils.setProperty(destinationObject, field.getName(), nImages.getFirstImage());
                                }
                            }
                        }

                        if (sectionObject instanceof NEMedia) {
                            NEMedia nMedia = (NEMedia) sectionObject;
                            if (secondPart.equals(NMappingArgs.mapping_first_image_media)) {
                                if (getSimpleValues) {
                                    BeanUtils.setProperty(destinationObject, field.getName(), nMedia.getFirstMedia().getUrl());
                                } else {
                                    BeanUtils.setProperty(destinationObject, field.getName(), nMedia.getFirstMedia());
                                }
                            }
                        }

                        if (sectionObject instanceof NEAddress) {
                            NEAddress neAddress = (NEAddress) sectionObject;
                            if (secondPart.equals(NMappingArgs.mapping_latitude)) {
                                BeanUtils.setProperty(destinationObject, field.getName(), neAddress.getLatitude());
                            }

                            if (secondPart.equals(NMappingArgs.mapping_longitude)) {
                                BeanUtils.setProperty(destinationObject, field.getName(), neAddress.getLongitude());
                            }

                            if (secondPart.equals(NMappingArgs.mapping_address)) {
                                BeanUtils.setProperty(destinationObject, field.getName(), neAddress.getAddress());
                            }
                        }

                    } else {
                        NClass sectionObject = section.getField(sectionKey);
                        if (sectionObject instanceof NECheckbox) {
                            NECheckbox nCheckbox = (NECheckbox) sectionObject;
                            BeanUtils.setProperty(destinationObject, field.getName(), nCheckbox.getContent());
                        }

                        if (sectionObject instanceof NEDate) {
                            NEDate nDate = (NEDate) sectionObject;
                            BeanUtils.setProperty(destinationObject, field.getName(), nDate.getTimestamp());
                        }

                        if (sectionObject instanceof NEImages) {
                            NEImages nImages = (NEImages) sectionObject;
                            ArrayList<String> images = new ArrayList<>();
                            for (int i = 0; i < nImages.getImages().size(); i++) {
                                images.add(nImages.getImages().get(i).getUrl());
                            }

                            BeanUtils.setProperty(destinationObject, field.getName(), images);
                        }

                        if (sectionObject instanceof NEMedia) {
                            NEMedia nMedia = (NEMedia) sectionObject;
                            ArrayList<String> files = new ArrayList<>();
                            for (int i = 0; i < nMedia.getFiles().size(); i++) {
                                files.add(nMedia.getFiles().get(i).getUrl());
                            }

                            BeanUtils.setProperty(destinationObject, field.getName(), files);
                        }

                        if (sectionObject instanceof NEText) {
                            NEText nText = (NEText) sectionObject;
                            BeanUtils.setProperty(destinationObject, field.getName(), nText.getContent());
                        }

                        if (sectionObject instanceof NEWYSIWYG) {
                            NEWYSIWYG nWYSIWYG = (NEWYSIWYG) sectionObject;
                            BeanUtils.setProperty(destinationObject, field.getName(), nWYSIWYG.getContent());
                        }

                        if (sectionObject instanceof NEDropdown) {
                            NEDropdown nWYSIWYG = (NEDropdown) sectionObject;
                            BeanUtils.setProperty(destinationObject, field.getName(), nWYSIWYG.getContent());
                        }

                        if (sectionObject instanceof NEGeneric) {
                            NEGeneric nGeneric = (NEGeneric) sectionObject;
                            BeanUtils.setProperty(destinationObject, field.getName(), nGeneric.getContent());
                        }

                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        return destinationObject;
    }

    /**Asks project data from API*/
    public static void askForProject(Context context){
        if(NUserConst.apiKey != null) {
            new ATask_getProject(context).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks project data from API with custom action return*/
    public static void askForProject(Context context, String custom_action){
        if(NUserConst.apiKey != null) {
            new ATask_getProject(context, custom_action).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks project data from API with listener*/
    public static void askForProject(Context context, NApiProjectResultListener listener){
        if(NUserConst.apiKey != null) {
            new ATask_getProject(context, listener).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks blocks data from API with or without sections*/
    public static void askForBlocks(Context context, ArrayList<Object> filters, boolean getSections){
        if(NUserConst.apiKey != null) {
            new ATask_getBlocks(context, filters, getSections).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks blocks data from API with or without sections and elements*/
    public static void askForBlocks(Context context, ArrayList<Object> filters, boolean getSections, boolean getElements){
        if(NUserConst.apiKey != null) {
            new ATask_getBlocks(context, filters, getSections, getElements).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks blocks data from API with or without sections with a custom action return*/
    public static void askForBlocks(Context context, ArrayList<Object> filters, boolean getSections, String custom_action){
        if(NUserConst.apiKey != null) {
            new ATask_getBlocks(context, filters, custom_action, getSections).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks blocks data from API with or without sections with listener*/
    public static void askForBlocks(Context context, ArrayList<Object> filters, boolean getSections, NApiBlocksResultListener listener){
        if(NUserConst.apiKey != null) {
            new ATask_getBlocks(context, filters, listener, getSections).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks blocks data from API with or without sections and elements with a custom action return*/
    public static void askForBlocks(Context context, ArrayList<Object> filters, boolean getSections, boolean getElements, String custom_action){
        if(NUserConst.apiKey != null) {
            new ATask_getBlocks(context, filters, custom_action, getSections, getElements).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks blocks data from API with or without sections and elements with listener*/
    public static void askForBlocks(Context context, ArrayList<Object> filters, boolean getSections, boolean getElements, NApiBlocksResultListener listener){
        if(NUserConst.apiKey != null) {
            new ATask_getBlocks(context, filters, listener, getSections, getElements).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks sections data from API with or without elements*/
    public static void askForSections(Context context, ArrayList<Object> filters, boolean getElements){
        if(NUserConst.apiKey != null) {
            new ATask_getSections(context, filters, getElements).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks sections data from API with or without elements with a custom action return*/
    public static void askForSections(Context context, ArrayList<Object> filters, boolean getElements, String custom_action){
        if(NUserConst.apiKey != null) {
            new ATask_getSections(context, filters, custom_action, getElements).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks sections data from API with or without elements with listener*/
    public static void askForSections(Context context, ArrayList<Object> filters, boolean getElements, NApiSectionsResultListener listener){
        if(NUserConst.apiKey != null) {
            new ATask_getSections(context, filters, listener, getElements).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks section data from API with or without elements with listener*/
    public static void askForSection(Context context, long section_id, boolean getElements){
        if(NUserConst.apiKey != null) {
            new ATask_getSection(context, section_id, getElements).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }


    /**Asks section data from API with or without elements with custom action*/
    public static void askForSection(Context context, long section_id, boolean getElements, String custom_action){
        if(NUserConst.apiKey != null) {
            new ATask_getSection(context, section_id, custom_action, getElements).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks section data from API with or without elements with listener*/
    public static void askForSection(Context context, long section_id, boolean getElements, NApiSectionResultListener listener){
        if(NUserConst.apiKey != null) {
            new ATask_getSection(context, section_id, listener, getElements).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks blocks data from API with or without sections*/
    public static void askForBlock(Context context, long block_id, boolean getSections){
        if(NUserConst.apiKey != null) {
            new ATask_getBlock(context, block_id, getSections).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks blocks data from API with or without sections and elements*/
    public static void askForBlock(Context context, long block_id, boolean getSections, boolean getElements){
        if(NUserConst.apiKey != null) {
            new ATask_getBlock(context, block_id, getSections, getElements).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks blocks data from API with or without sections with a custom action return*/
    public static void askForBlock(Context context, long block_id, boolean getSections, String custom_action){
        if(NUserConst.apiKey != null) {
            new ATask_getBlock(context, block_id, custom_action, getSections).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks blocks data from API with or without sections with listener*/
    public static void askForBlock(Context context, long block_id, boolean getSections, NApiBlockResultListener listener){
        if(NUserConst.apiKey != null) {
            new ATask_getBlock(context, block_id, listener, getSections).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks blocks data from API with or without sections and elements with a custom action return*/
    public static void askForBlock(Context context, long block_id, boolean getSections, boolean getElements, String custom_action){
        if(NUserConst.apiKey != null) {
            new ATask_getBlock(context, block_id, custom_action, getSections, getElements).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**Asks blocks data from API with or without sections and elements with listener*/
    public static void askForBlock(Context context, long block_id, boolean getSections, boolean getElements, NApiBlockResultListener listener){
        if(NUserConst.apiKey != null) {
            new ATask_getBlock(context, block_id, listener, getSections, getElements).execute();
        }
        else{
            throw new NSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

}