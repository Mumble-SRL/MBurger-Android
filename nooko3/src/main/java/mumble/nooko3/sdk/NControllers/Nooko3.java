package mumble.nooko3.sdk.NControllers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import javadz.beanutils.BeanUtils;
import mumble.nooko3.sdk.NControllers.NApiManager.NAMActivityUtils;
import mumble.nooko3.sdk.NControllers.NApiManager.NAMCONF;
import mumble.nooko3.sdk.NData.NAtomic.NClass;
import mumble.nooko3.sdk.NData.NElements.NEAddress;
import mumble.nooko3.sdk.NData.NElements.NECheckbox;
import mumble.nooko3.sdk.NData.NElements.NEDate;
import mumble.nooko3.sdk.NData.NElements.NEDropdown;
import mumble.nooko3.sdk.NData.NElements.NEImages;
import mumble.nooko3.sdk.NData.NElements.NEMedia;
import mumble.nooko3.sdk.NData.NElements.NEText;
import mumble.nooko3.sdk.NData.NElements.NETitle;
import mumble.nooko3.sdk.NData.NElements.NEWYSIWYG;
import mumble.nooko3.sdk.NData.NSections.NSection;
import mumble.nooko3.sdk.UserConst;

/**
 * Basic init class for Nooko3, which sets an array of constants used runtime.
 *
 * @author Enrico Ori
 * @version {@value mumble.nooko3.sdk.Const#version}
 */
public class Nooko3 {

    /**
     * Initialize the SDK with the default parameters
     * Caching = false
     * CachingTime = 3 days
     */
    public static void initialize(Context context) {

    }

    /**
     * Initialize the SDK with user parameters about caching duration, default caching is ON
     */
    public static void initialize(Context context, long cachingDuration) {
        UserConst.cachingTime = cachingDuration;
    }

    /**
     * Initialize the SDK with user parameters about caching on/off
     */
    public static void initialize(Context context, boolean cachingEnabled) {
        UserConst.cachingEnabled = cachingEnabled;
    }

    /**
     * Initialize an activity/fragment to receive blocks from API
     */
    public static BroadcastReceiver initializeNookoReceiverForBlocks(Activity activity, ApiResultListener listener) {
        String[] receivers = new String[]{NAMCONF.ACTION_GET_BLOCK};
        return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
    }

    /**
     * Initialize an activity/fragment to receive sections from API
     */
    public static BroadcastReceiver initializeNookoReceiverForSections(Activity activity, ApiResultListener listener) {
        String[] receivers = new String[]{NAMCONF.ACTION_GET_SECTION};
        return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
    }

    /**
     * Initialize an activity/fragment to receive custom data with custom actions
     */
    public static BroadcastReceiver initializeNookoReceiverCustom(Activity activity, ApiResultListener listener, String[] receivers) {
        return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
    }

    /**
     * Removes the Nooko receiver from the activity/fragment, must pass BroadcastReceiver from initialization
     */
    public static void pauseNookoReceiver(Activity activity, BroadcastReceiver broadcastReceiver) {
        NAMActivityUtils.unregisterForApiManager(activity, broadcastReceiver);
    }

    /**
     * Maps a simple section into a custom user object.
     *
     * @param section           is the section you wish to map
     * @param fieldsMap         is a map which contains the user object fields as keys and the section key as value,
     *                          images, addresses and media has special fields to obtain the first element or
     *                          just part of the object
     * @param destinationObject is an empty user object, which has at least getters and setters
     */
    public static Object mapToCustomObject(NSection section, HashMap<String, String> fieldsMap, Object destinationObject) {
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
                            if (secondPart.equals("getFirstImage")) {
                                BeanUtils.setProperty(destinationObject, field.getName(), nImages.getFirstImage());
                            }
                        }

                        if (sectionObject instanceof NEMedia) {
                            NEMedia nMedia = (NEMedia) sectionObject;
                            if (secondPart.equals("getFirstMedia")) {
                                BeanUtils.setProperty(destinationObject, field.getName(), nMedia.getFirstMedia());
                            }
                        }

                        if (sectionObject instanceof NEAddress) {
                            NEAddress neAddress = (NEAddress) sectionObject;
                            if (secondPart.equals("getLatitude")) {
                                BeanUtils.setProperty(destinationObject, field.getName(), neAddress.getLatitude());
                            }

                            if (secondPart.equals("getLongitude")) {
                                BeanUtils.setProperty(destinationObject, field.getName(), neAddress.getLongitude());
                            }

                            if (secondPart.equals("getAddress")) {
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

                        if (sectionObject instanceof NETitle) {
                            NETitle nTitle = (NETitle) sectionObject;
                            BeanUtils.setProperty(destinationObject, field.getName(), nTitle.getContent());
                        }

                        if (sectionObject instanceof NEWYSIWYG) {
                            NEWYSIWYG nWYSIWYG = (NEWYSIWYG) sectionObject;
                            BeanUtils.setProperty(destinationObject, field.getName(), nWYSIWYG.getContent());
                        }

                        if (sectionObject instanceof NEDropdown) {
                            NEDropdown nWYSIWYG = (NEDropdown) sectionObject;
                            BeanUtils.setProperty(destinationObject, field.getName(), nWYSIWYG.getContent());
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
}