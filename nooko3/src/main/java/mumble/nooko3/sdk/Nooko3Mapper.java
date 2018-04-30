package mumble.nooko3.sdk;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javadz.beanutils.BeanUtils;
import mumble.nooko3.sdk.NKData.NKAtomic.NKClass;
import mumble.nooko3.sdk.NKData.NKElements.NKAddressElement;
import mumble.nooko3.sdk.NKData.NKElements.NKCheckboxElement;
import mumble.nooko3.sdk.NKData.NKElements.NKDateElement;
import mumble.nooko3.sdk.NKData.NKElements.NKDropdownElement;
import mumble.nooko3.sdk.NKData.NKElements.NKGenericElement;
import mumble.nooko3.sdk.NKData.NKElements.NKImages;
import mumble.nooko3.sdk.NKData.NKElements.NKMediaElement;
import mumble.nooko3.sdk.NKData.NKElements.NKPollAnswers;
import mumble.nooko3.sdk.NKData.NKElements.NKTextElement;
import mumble.nooko3.sdk.NKData.NKElements.NKWYSIWYGElement;
import mumble.nooko3.sdk.NKData.NKSections.NKSection;
import mumble.nooko3.sdk.NKMapper.NKFieldsMapping;
import mumble.nooko3.sdk.NKMapper.NKMappingArgs;

/**
 * Class to map custom user objects from a Nooko section. Only works with Classes with values or Nooko objects,
 * not with user innested objects
 */
public class Nooko3Mapper {

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
    public static Object mapToCustomObject(NKSection section, NKFieldsMapping fieldsMap,
                                           Object destinationObject, boolean getSimpleValues) {
        Field[] fields = destinationObject.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (fieldsMap.containsKey(field.getName())) {
                String sectionKey = fieldsMap.get(field.getName());
                try {
                    if (sectionKey.contains(".")) {
                        String firstPart = sectionKey.substring(0, sectionKey.indexOf("."));
                        String secondPart = sectionKey.substring(sectionKey.indexOf(".") + 1);
                        NKClass sectionObject = section.getField(firstPart);

                        if (sectionObject instanceof NKImages) {
                            NKImages nImages = (NKImages) sectionObject;
                            if (secondPart.equals(NKMappingArgs.mapping_first_image_media)) {
                                if (getSimpleValues) {
                                    BeanUtils.setProperty(destinationObject, field.getName(), nImages.getFirstImage().getUrl());
                                } else {
                                    BeanUtils.setProperty(destinationObject, field.getName(), nImages.getFirstImage());
                                }
                            }
                        }

                        if (sectionObject instanceof NKMediaElement) {
                            NKMediaElement nMedia = (NKMediaElement) sectionObject;
                            if (secondPart.equals(NKMappingArgs.mapping_first_image_media)) {
                                if (getSimpleValues) {
                                    BeanUtils.setProperty(destinationObject, field.getName(), nMedia.getFirstMedia().getUrl());
                                } else {
                                    BeanUtils.setProperty(destinationObject, field.getName(), nMedia.getFirstMedia());
                                }
                            }
                        }

                        if (sectionObject instanceof NKAddressElement) {
                            NKAddressElement NKAddressElement = (NKAddressElement) sectionObject;
                            if (secondPart.equals(NKMappingArgs.mapping_latitude)) {
                                BeanUtils.setProperty(destinationObject, field.getName(), NKAddressElement.getLatitude());
                            }

                            if (secondPart.equals(NKMappingArgs.mapping_longitude)) {
                                BeanUtils.setProperty(destinationObject, field.getName(), NKAddressElement.getLongitude());
                            }

                            if (secondPart.equals(NKMappingArgs.mapping_address)) {
                                BeanUtils.setProperty(destinationObject, field.getName(), NKAddressElement.getAddress());
                            }
                        }

                    } else {
                        NKClass sectionObject = section.getField(sectionKey);
                        if (sectionObject instanceof NKCheckboxElement) {
                            NKCheckboxElement nCheckbox = (NKCheckboxElement) sectionObject;
                            BeanUtils.setProperty(destinationObject, field.getName(), nCheckbox.getContent());
                        }

                        if (sectionObject instanceof NKDateElement) {
                            NKDateElement nDate = (NKDateElement) sectionObject;
                            BeanUtils.setProperty(destinationObject, field.getName(), nDate.getTimestamp());
                        }

                        if (sectionObject instanceof NKImages) {
                            NKImages nImages = (NKImages) sectionObject;
                            if (getSimpleValues) {
                                ArrayList<String> images = new ArrayList<>();
                                for (int i = 0; i < nImages.getImages().size(); i++) {
                                    images.add(nImages.getImages().get(i).getUrl());
                                }

                                BeanUtils.setProperty(destinationObject, field.getName(), images);
                            } else {
                                BeanUtils.setProperty(destinationObject, field.getName(), nImages);
                            }
                        }

                        if (sectionObject instanceof NKMediaElement) {
                            NKMediaElement nMedia = (NKMediaElement) sectionObject;
                            ArrayList<String> files = new ArrayList<>();
                            for (int i = 0; i < nMedia.getFiles().size(); i++) {
                                files.add(nMedia.getFiles().get(i).getUrl());
                            }

                            BeanUtils.setProperty(destinationObject, field.getName(), files);
                        }

                        if (sectionObject instanceof NKTextElement) {
                            NKTextElement nText = (NKTextElement) sectionObject;
                            BeanUtils.setProperty(destinationObject, field.getName(), nText.getContent());
                        }

                        if (sectionObject instanceof NKWYSIWYGElement) {
                            NKWYSIWYGElement nWYSIWYG = (NKWYSIWYGElement) sectionObject;
                            BeanUtils.setProperty(destinationObject, field.getName(), nWYSIWYG.getContent());
                        }

                        if (sectionObject instanceof NKDropdownElement) {
                            NKDropdownElement nWYSIWYG = (NKDropdownElement) sectionObject;
                            BeanUtils.setProperty(destinationObject, field.getName(), nWYSIWYG.getContent());
                        }

                        if (sectionObject instanceof NKAddressElement) {
                            NKAddressElement nAddress = (NKAddressElement) sectionObject;
                            BeanUtils.setProperty(destinationObject, field.getName(), nAddress);
                        }

                        if (sectionObject instanceof NKPollAnswers) {
                            NKPollAnswers nPollAnswers = (NKPollAnswers) sectionObject;
                            BeanUtils.setProperty(destinationObject, field.getName(), nPollAnswers);
                        }

                        if (sectionObject instanceof NKGenericElement) {
                            NKGenericElement nGeneric = (NKGenericElement) sectionObject;
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
}
