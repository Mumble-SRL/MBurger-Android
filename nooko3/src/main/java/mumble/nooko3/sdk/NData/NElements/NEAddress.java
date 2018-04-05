package mumble.nooko3.sdk.NData.NElements;
import mumble.nooko3.sdk.NConstants.NConst;
import mumble.nooko3.sdk.NData.NAtomic.NClass;

/**
 * Identifies an address with latitude and longitude
 *
 * @author Enrico Ori
 * @version {@value NConst#version}
 */
public class NEAddress extends NClass {

    /** String representing the address */
    private String address;

    /** Latitude of the place */
    private double latitude;

    /** Longitude of the place */
    private double longitude;

    public NEAddress(long id, String name, String address, double latitude, double longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        initialize(id, name, NConst.type_address);
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
