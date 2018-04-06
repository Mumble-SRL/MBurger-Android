package mumble.nooko3.sdk.NMapper;

import mumble.nooko3.sdk.NData.NSections.NSection;

/**
 * Special arguments for mapping Nooko sections into custom objects, used with method
 * {@link Nooko3Mapper#mapToCustomObject(NSection, NFieldsMapping, Object, boolean)}
 */
public class NMappingArgs {

    /**
     * Get first image or media from a Nooko {@link mumble.nooko3.sdk.NData.NElements.NEImages NImages}
     */
    public static String mapping_first_image_media = "first";

    /**
     * Get only the address from a Nooko {@link mumble.nooko3.sdk.NData.NElements.NEAddress NAddress}
     */
    public static String mapping_address = "first";

    /**
     * Get only the latitude from a Nooko {@link mumble.nooko3.sdk.NData.NElements.NEAddress NAddress}
     */
    public static String mapping_latitude = "latitude";

    /**
     * Get only the longitude from a Nooko {@link mumble.nooko3.sdk.NData.NElements.NEAddress NAddress}
     */
    public static String mapping_longitude = "longitude";

}
