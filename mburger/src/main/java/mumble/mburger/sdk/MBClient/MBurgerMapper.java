package mumble.mburger.sdk.MBClient;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javadz.beanutils.BeanUtils;
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
    public static Object mapToCustomObject(MBSection section, mumble.mburger.sdk.NKMapper.MBFieldsMapping fieldsMap,
                                           Object destinationObject, boolean getSimpleValues) {
        HashMap<String, String> map = fieldsMap.getFieldsMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String field = entry.getKey();
            String sectionKey = fieldsMap.get(field);
            try {
                if (sectionKey.contains(".")) {
                    String firstPart = sectionKey.substring(0, sectionKey.indexOf("."));
                    String secondPart = sectionKey.substring(sectionKey.indexOf(".") + 1);
                    MBClass sectionObject = section.getField(firstPart);

                    if (sectionObject instanceof MBImages) {
                        MBImages nImages = (MBImages) sectionObject;
                        if (secondPart.equals(mumble.mburger.sdk.NKMapper.MBMappingArgs.mapping_first_image_media)) {
                            if (getSimpleValues) {
                                BeanUtils.setProperty(destinationObject, field, nImages.getFirstImage().getUrl());
                            } else {
                                BeanUtils.setProperty(destinationObject, field, nImages.getFirstImage());
                            }
                        }
                    }

                    if (sectionObject instanceof MBMediaElement) {
                        MBMediaElement nMedia = (MBMediaElement) sectionObject;
                        if (secondPart.equals(mumble.mburger.sdk.NKMapper.MBMappingArgs.mapping_first_image_media)) {
                            if (getSimpleValues) {
                                BeanUtils.setProperty(destinationObject, field, nMedia.getFirstMedia().getUrl());
                            } else {
                                BeanUtils.setProperty(destinationObject, field, nMedia.getFirstMedia());
                            }
                        }
                    }

                    if (sectionObject instanceof MBAddressElement) {
                        MBAddressElement MBAddressElement = (MBAddressElement) sectionObject;
                        if (secondPart.equals(mumble.mburger.sdk.NKMapper.MBMappingArgs.mapping_latitude)) {
                            BeanUtils.setProperty(destinationObject, field, MBAddressElement.getLatitude());
                        }

                        if (secondPart.equals(mumble.mburger.sdk.NKMapper.MBMappingArgs.mapping_longitude)) {
                            BeanUtils.setProperty(destinationObject, field, MBAddressElement.getLongitude());
                        }

                        if (secondPart.equals(mumble.mburger.sdk.NKMapper.MBMappingArgs.mapping_address)) {
                            BeanUtils.setProperty(destinationObject, field, MBAddressElement.getAddress());
                        }
                    }

                } else {
                    MBClass sectionObject = section.getField(sectionKey);
                    if (sectionObject instanceof MBCheckboxElement) {
                        MBCheckboxElement nCheckbox = (MBCheckboxElement) sectionObject;
                        BeanUtils.setProperty(destinationObject, field, nCheckbox.getContent());
                    }

                    if (sectionObject instanceof MBDateElement) {
                        MBDateElement nDate = (MBDateElement) sectionObject;
                        BeanUtils.setProperty(destinationObject, field, nDate.getTimestamp());
                    }

                    if (sectionObject instanceof MBImages) {
                        MBImages nImages = (MBImages) sectionObject;
                        if (getSimpleValues) {
                            ArrayList<String> images = new ArrayList<>();
                            for (int i = 0; i < nImages.getImages().size(); i++) {
                                images.add(nImages.getImages().get(i).getUrl());
                            }

                            BeanUtils.setProperty(destinationObject, field, images);
                        } else {
                            BeanUtils.setProperty(destinationObject, field, nImages);
                        }
                    }

                    if (sectionObject instanceof MBMediaElement) {
                        MBMediaElement nMedia = (MBMediaElement) sectionObject;
                        ArrayList<String> files = new ArrayList<>();
                        for (int i = 0; i < nMedia.getFiles().size(); i++) {
                            files.add(nMedia.getFiles().get(i).getUrl());
                        }

                        BeanUtils.setProperty(destinationObject, field, files);
                    }

                    if (sectionObject instanceof MBTextElement) {
                        MBTextElement nText = (MBTextElement) sectionObject;
                        BeanUtils.setProperty(destinationObject, field, nText.getContent());
                    }

                    if (sectionObject instanceof MBWYSIWYGElement) {
                        MBWYSIWYGElement nWYSIWYG = (MBWYSIWYGElement) sectionObject;
                        BeanUtils.setProperty(destinationObject, field, nWYSIWYG.getContent());
                    }

                    if (sectionObject instanceof MBDropdownElement) {
                        MBDropdownElement nDropdownElem = (MBDropdownElement) sectionObject;
                        BeanUtils.setProperty(destinationObject, field, nDropdownElem.getContent());
                    }

                    if (sectionObject instanceof MBAddressElement) {
                        MBAddressElement nAddress = (MBAddressElement) sectionObject;
                        BeanUtils.setProperty(destinationObject, field, nAddress);
                    }

                    if (sectionObject instanceof MBPollAnswers) {
                        MBPollAnswers nPollAnswers = (MBPollAnswers) sectionObject;
                        BeanUtils.setProperty(destinationObject, field, nPollAnswers);
                    }

                    if (sectionObject instanceof MBGenericElement) {
                        MBGenericElement nGeneric = (MBGenericElement) sectionObject;
                        BeanUtils.setProperty(destinationObject, field, nGeneric.getContent());
                    }

                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return destinationObject;
    }
}
