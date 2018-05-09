package mumble.nooko3.sdk.NKClient.NKApiFilters;

import java.io.Serializable;

import mumble.nooko3.sdk.Common.NKConstants.NKConstants;

/**
 * Class to filter data through geofence
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKGeofenceParameter implements Serializable{

    /**Up-left (North-East) part latitude*/
    private double latitudeNE;

    /**Up-left (North-East) part longitude*/
    private double longitudeNE;

    /**Down-right (South-West) part latitude*/
    private double latitudeSW;

    /**Down-right (South-West) part longitude*/
    private double longitudeSW;

    public NKGeofenceParameter(double latitudeNE, double longitudeNE, double latitudeSW, double longitudeSW) {
        this.latitudeNE = latitudeNE;
        this.longitudeNE = longitudeNE;
        this.latitudeSW = latitudeSW;
        this.longitudeSW = longitudeSW;
    }

    /**Get North-East part latitude*/
    public double getLatitudeNE() {
        return latitudeNE;
    }

    /**Set North-East part latitude*/
    public void setLatitudeNE(double latitudeNE) {
        this.latitudeNE = latitudeNE;
    }

    /**Get North-East part longitude*/
    public double getLongitudeNE() {
        return longitudeNE;
    }

    /**Set North-East part longitude*/
    public void setLongitudeNE(double longitudeNE) {
        this.longitudeNE = longitudeNE;
    }

    /**Get South-West part latitude*/
    public double getLatitudeSW() {
        return latitudeSW;
    }

    /**Set South-West part latitude*/
    public void setLatitudeSW(double latitudeSW) {
        this.latitudeSW = latitudeSW;
    }

    /**Get South-West part longitude*/
    public double getLongitudeSW() {
        return longitudeSW;
    }

    /**Set South-West part longitude*/
    public void setLongitudeSW(double longitudeSW) {
        this.longitudeSW = longitudeSW;
    }
}
