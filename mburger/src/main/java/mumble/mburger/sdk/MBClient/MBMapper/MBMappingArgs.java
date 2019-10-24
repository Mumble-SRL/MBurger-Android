package mumble.mburger.sdk.MBClient.MBMapper;

import mumble.mburger.sdk.MBClient.MBurgerMapper;

/**
 * Special arguments for mapping MBurger sections into custom objects, used with method
 * {@link MBurgerMapper#mapToCustomObject(mumble.mburger.sdk.MBClient.MBData.MBSections.MBSection, MBFieldsMapping, Object, boolean)}
 */
public class MBMappingArgs {

    /**
     * Get first image or media from a MBurger {@link mumble.mburger.sdk.MBClient.MBData.MBElements.MBImages MBImages}
     */
    public static String mapping_first_image_media = "first";

    /**
     * Get only the address from a MBurger {@link mumble.mburger.sdk.MBClient.MBData.MBElements.MBAddressElement MBAddress}
     */
    public static String mapping_address = "first";

    /**
     * Get only the latitude from a MBurger {@link mumble.mburger.sdk.MBClient.MBData.MBElements.MBAddressElement MBAddress}
     */
    public static String mapping_latitude = "latitude";

    /**
     * Get only the longitude from a MBurger {@link mumble.mburger.sdk.MBClient.MBData.MBElements.MBAddressElement MBAddress}
     */
    public static String mapping_longitude = "longitude";

}
