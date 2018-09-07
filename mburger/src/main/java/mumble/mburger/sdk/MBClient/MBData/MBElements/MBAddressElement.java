package mumble.mburger.sdk.MBClient.MBData.MBElements;
import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBClient.MBData.MBAtomic.MBClass;

/**
 * Identifies an address with latitude and longitude
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBAddressElement extends MBClass {

    /** String representing the address */
    private String address;

    /** Latitude of the place */
    private double latitude;

    /** Longitude of the place */
    private double longitude;

    public MBAddressElement(long id, String name, String address, double latitude, double longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        initialize(id, name, MBConstants.type_address);
    }

    @Override
    public void initialize(long id, String name, String type) {
        super.setId(id);
        super.setName(name);
        super.setType(type);
    }

    /** Get String of the address */
    public String getAddress() {
        return address;
    }

    /** Set String of the address */
    public void setAddress(String address) {
        this.address = address;
    }

    /** Get latitude of the place */
    public double getLatitude() {
        return latitude;
    }

    /** Set latitude of the place */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /** Get longitude of the place */
    public double getLongitude() {
        return longitude;
    }

    /** Set longitude of the place */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
