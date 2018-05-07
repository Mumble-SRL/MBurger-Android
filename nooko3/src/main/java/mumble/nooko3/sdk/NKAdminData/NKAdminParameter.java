package mumble.nooko3.sdk.NKAdminData;

import java.io.Serializable;

import mumble.nooko3.sdk.NKConstants.NKConstants;

/**
 * Used for admin API
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKAdminParameter implements Serializable{

    private String key, value, filepath, mime_type;
    private boolean file = false;

    public NKAdminParameter(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public NKAdminParameter(String key, String value, String filepath, String mime_type) {
        this.key = key;
        this.value = value;
        this.filepath = filepath;
        this.mime_type = mime_type;
        this.file = true;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMime_type() {
        return mime_type;
    }

    public void setMime_type(String mime_type) {
        this.mime_type = mime_type;
    }

    public boolean isFile() {
        return file;
    }

    public void setFile(boolean file) {
        this.file = file;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
