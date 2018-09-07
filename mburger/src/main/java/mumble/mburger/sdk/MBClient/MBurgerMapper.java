package mumble.mburger.sdk.MBClient;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mumble.mburger.sdk.MBClient.MBData.MBAtomic.MBClass;
import mumble.mburger.sdk.MBClient.MBData.MBElements.MBAddressElement;
import mumble.mburger.sdk.MBClient.MBData.MBElements.MBCheckboxElement;
import mumble.mburger.sdk.MBClient.MBData.MBElements.MBDateElement;
import mumble.mburger.sdk.MBClient.MBData.MBElements.MBDropdownElement;
import mumble.mburger.sdk.MBClient.MBData.MBElements.MBGenericElement;
import mumble.mburger.sdk.MBClient.MBData.MBElements.MBImages;
import mumble.mburger.sdk.MBClient.MBData.MBElements.MBMediaElement;
import mumble.mburger.sdk.MBClient.MBData.MBElements.MBPollAnswers;
import mumble.mburger.sdk.MBClient.MBData.MBElements.MBTextElement;
import mumble.mburger.sdk.MBClient.MBData.MBElements.MBWYSIWYGElement;
import mumble.mburger.sdk.MBClient.MBData.MBSections.MBSection;
import mumble.mburger.sdk.MBClient.MBMapper.MBFieldsMapping;
import mumble.mburger.sdk.MBClient.MBMapper.MBMappingArgs;

/**
 * Class to map custom user objects from a Nooko section. Only works with Classes with values or Nooko objects,
 * not with user innested objects
 */
public class MBurgerMapper {

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
    public static Object mapToCustomObject(MBSection section, MBFieldsMapping fieldsMap,
                                           Object destinationObject, boolean getSimpleValues) {
        HashMap<String, String> map = fieldsMap.getFieldsMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String field = entry.getKey();
            String sectionKey = fieldsMap.get(field);
            try {
                Field objField = destinationObject.getClass().getDeclaredField(field);
                objField.setAccessible(true);
                if (sectionKey.contains(".")) {
                    String firstPart = sectionKey.substring(0, sectionKey.indexOf("."));
                    String secondPart = sectionKey.substring(sectionKey.indexOf(".") + 1);
                    MBClass sectionObject = section.getField(firstPart);

                    if (sectionObject instanceof MBImages) {
                        MBImages nImages = (MBImages) sectionObject;
                        if (secondPart.equals(MBMappingArgs.mapping_first_image_media)) {
                            if (getSimpleValues) {
                                objField.set(destinationObject, nImages.getFirstImage().getUrl());
                            } else {
                                objField.set(destinationObject, nImages.getFirstImage());
                            }
                        }
                    }

                    if (sectionObject instanceof MBMediaElement) {
                        MBMediaElement nMedia = (MBMediaElement) sectionObject;
                        if (secondPart.equals(MBMappingArgs.mapping_first_image_media)) {
                            if (getSimpleValues) {
                                objField.set(destinationObject, nMedia.getFirstMedia().getUrl());
                            } else {
                                objField.set(destinationObject, nMedia.getFirstMedia());
                            }
                        }
                    }

                    if (sectionObject instanceof MBAddressElement) {
                        MBAddressElement MBAddressElement = (MBAddressElement) sectionObject;
                        if (secondPart.equals(MBMappingArgs.mapping_latitude)) {
                            objField.set(destinationObject, MBAddressElement.getLatitude());
                        }

                        if (secondPart.equals(MBMappingArgs.mapping_longitude)) {
                            objField.set(destinationObject, MBAddressElement.getLongitude());
                        }

                        if (secondPart.equals(MBMappingArgs.mapping_address)) {
                            objField.set(destinationObject, MBAddressElement.getAddress());
                        }
                    }

                } else {
                    MBClass sectionObject = section.getField(sectionKey);
                    if (sectionObject instanceof MBCheckboxElement) {
                        MBCheckboxElement nCheckbox = (MBCheckboxElement) sectionObject;
                        objField.set(destinationObject, nCheckbox.getContent());
                    }

                    if (sectionObject instanceof MBDateElement) {
                        MBDateElement nDate = (MBDateElement) sectionObject;
                        objField.set(destinationObject, nDate.getTimestamp());
                    }

                    if (sectionObject instanceof MBImages) {
                        MBImages nImages = (MBImages) sectionObject;
                        if (getSimpleValues) {
                            ArrayList<String> images = new ArrayList<>();
                            for (int i = 0; i < nImages.getImages().size(); i++) {
                                images.add(nImages.getImages().get(i).getUrl());
                            }

                            objField.set(destinationObject, images);
                        } else {
                            objField.set(destinationObject, nImages);
                        }
                    }

                    if (sectionObject instanceof MBMediaElement) {
                        MBMediaElement nMedia = (MBMediaElement) sectionObject;
                        ArrayList<String> files = new ArrayList<>();
                        for (int i = 0; i < nMedia.getFiles().size(); i++) {
                            files.add(nMedia.getFiles().get(i).getUrl());
                        }

                        objField.set(destinationObject, files);
                    }

                    if (sectionObject instanceof MBTextElement) {
                        MBTextElement nText = (MBTextElement) sectionObject;
                        objField.set(destinationObject, nText.getContent());
                    }

                    if (sectionObject instanceof MBWYSIWYGElement) {
                        MBWYSIWYGElement nWYSIWYG = (MBWYSIWYGElement) sectionObject;
                        objField.set(destinationObject, nWYSIWYG.getContent());
                    }

                    if (sectionObject instanceof MBDropdownElement) {
                        MBDropdownElement nDropdownElem = (MBDropdownElement) sectionObject;
                        objField.set(destinationObject, nDropdownElem.getContent());
                    }

                    if (sectionObject instanceof MBAddressElement) {
                        MBAddressElement nAddress = (MBAddressElement) sectionObject;
                        objField.set(destinationObject, nAddress);
                    }

                    if (sectionObject instanceof MBPollAnswers) {
                        MBPollAnswers nPollAnswers = (MBPollAnswers) sectionObject;
                        objField.set(destinationObject, nPollAnswers);
                    }

                    if (sectionObject instanceof MBGenericElement) {
                        MBGenericElement nGeneric = (MBGenericElement) sectionObject;
                        objField.set(destinationObject, nGeneric.getContent());
                    }

                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

        return destinationObject;
    }
}