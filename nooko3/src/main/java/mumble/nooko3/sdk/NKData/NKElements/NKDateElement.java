package mumble.nooko3.sdk.NKData.NKElements;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import mumble.nooko3.sdk.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKData.NKAtomic.NKClass;

/**
 * Identifies a date class with some utility methods to format and get milliseconds instead of seconds
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKDateElement extends NKClass {

    /**
     * Timestamp in seconds
     */
    private long timestamp;

    public NKDateElement(long id, String name, long timestamp) {
        this.timestamp = timestamp;
        initialize(id, name, NKConstants.type_date);
    }

    @Override
    public void initialize(long id, String name, String type) {
        super.setId(id);
        super.setName(name);
        super.setType(type);
    }

    /**
     * Get timestamp in seconds
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Get timestamp in milliseconds in order to be used better on Android
     */
    public long getTimestampMillisec() {
        return TimeUnit.SECONDS.toMillis(timestamp);
    }

    /**
     * Format timestamp with the given format using the default device locale
     */
    public String formatDate(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format, Locale.getDefault());
        return df.format(timestamp);
    }

    /**
     * Format timestamp with the given format and locale
     */
    public String formatDate(String format, Locale locale) {
        SimpleDateFormat df = new SimpleDateFormat(format, locale);
        return df.format(timestamp);
    }
}
