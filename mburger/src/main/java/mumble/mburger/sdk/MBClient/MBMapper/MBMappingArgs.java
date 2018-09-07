package mumble.mburger.sdk.MBClient.MBMapper;

import mumble.mburger.sdk.MBClient.MBurgerMapper;

/**
 * Special arguments for mapping Nooko sections into custom objects, used with method
 * {@link MBurgerMapper#mapToCustomObject(mumble.mburger.sdk.MBClient.MBData.MBSections.MBSection, MBFieldsMapping, Object, boolean)}
 */
public class MBMappingArgs {

    /**
     * Get first image or media from a Nooko {@link mumble.mburger.sdk.MBClient.MBData.MBElements.MBImages NImages}
     */
    public static String mapping_first_image_media = "first";

    /**
     * Get only the address from a Nooko {@link mumble.mburger.sdk.MBClient.MBData.MBElements.MBAddressElement NAddress}
     */
    public static String mapping_address = "first";

    /**
     * Get only the latitude from a Nooko {@link mumble.mburger.sdk.MBClient.MBData.MBElements.MBAddressElement NAddress}
     */
    public static String mapping_latitude = "latitude";

    /**
     * Get only the longitude from a Nooko {@link mumble.mburger.sdk.MBData.NKElements.MBAddressElement NAddress}
     */
    public static String mapping_longitude = "longitude";

}
