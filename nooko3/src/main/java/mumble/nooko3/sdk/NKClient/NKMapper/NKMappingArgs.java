package mumble.nooko3.sdk.NKMapper;

import mumble.nooko3.sdk.NKData.NKElements.NKAddressElement;
import mumble.nooko3.sdk.NKData.NKElements.NKImages;
import mumble.nooko3.sdk.NKData.NKSections.NKSection;
import mumble.nooko3.sdk.NKClient.Nooko3Mapper;

/**
 * Special arguments for mapping Nooko sections into custom objects, used with method
 * {@link Nooko3Mapper#mapToCustomObject(NKSection, NKFieldsMapping, Object, boolean)}
 */
public class NKMappingArgs {

    /**
     * Get first image or media from a Nooko {@link NKImages NImages}
     */
    public static String mapping_first_image_media = "first";

    /**
     * Get only the address from a Nooko {@link NKAddressElement NAddress}
     */
    public static String mapping_address = "first";

    /**
     * Get only the latitude from a Nooko {@link NKAddressElement NAddress}
     */
    public static String mapping_latitude = "latitude";

    /**
     * Get only the longitude from a Nooko {@link NKAddressElement NAddress}
     */
    public static String mapping_longitude = "longitude";

}
