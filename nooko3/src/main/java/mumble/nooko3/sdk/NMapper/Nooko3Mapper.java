package mumble.nooko3.sdk.NMapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javadz.beanutils.BeanUtils;
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
}
