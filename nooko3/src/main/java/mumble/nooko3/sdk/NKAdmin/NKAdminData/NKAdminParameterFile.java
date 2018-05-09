package mumble.nooko3.sdk.NKAdmin.NKAdminData;

import java.util.ArrayList;

import mumble.nooko3.sdk.Common.NKConstants.NKConstants;

/**
 * Used for admin API
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKAdminParameterFile {

    private String key;
    private ArrayList<NKAdminSingleFile> files;

    public NKAdminParameterFile(String key, ArrayList<NKAdminSingleFile> files) {
        this.key = key;
        this.files = files;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ArrayList<NKAdminSingleFile> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<NKAdminSingleFile> files) {
        this.files = files;
    }
}
