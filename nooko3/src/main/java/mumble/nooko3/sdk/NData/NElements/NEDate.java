package mumble.nooko3.sdk.NData.NElements;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import mumble.nooko3.sdk.Const;
import mumble.nooko3.sdk.NData.NAtomic.NClass;

/**
 * Identifies a date class with some utility methods to format and get milliseconds instead of seconds
 *
 * @author Enrico Ori
 * @version {@value mumble.nooko3.sdk.Const#version}
 */
public class NEDate extends NClass {

    /** Timestamp in seconds */
    private long timestamp;

    public NEDate(long id, String name, long timestamp) {
        this.timestamp = timestamp;
        initialize(id, name, Const.type_date);
    }

    @Override
    public void initialize(long id, String name, String type) {
        super.setId(id);
        super.setName(name);
        super.setType(type);
    }

    /** Get timestamp in milliseconds in order to be used better on Android */
    public long getTimestampMillisec() {
        return TimeUnit.SECONDS.toMillis(timestamp);
    }

    /** Format timestamp with the given format using the default device locale*/
    public String formatDate(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format, Locale.getDefault());
        return df.format(timestamp);
    }

    /** Format timestamp with the given format and locale*/
    public String formatDate(String format, Locale locale) {
        SimpleDateFormat df = new SimpleDateFormat(format, locale);
        return df.format(timestamp);
    }
}
