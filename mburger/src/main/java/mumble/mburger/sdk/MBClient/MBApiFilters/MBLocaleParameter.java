package mumble.mburger.sdk.MBClient.MBApiFilters;

import java.io.Serializable;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;

/**
 * Class to filter data retrievement through API in a specific locale
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBLocaleParameter implements Serializable {

    /**Value which should be filtered for*/
    private String locale;

    public MBLocaleParameter(String locale) {
        this.locale = locale;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
