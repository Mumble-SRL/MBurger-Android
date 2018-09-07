package mumble.mburger.sdk.MBAdmin.MBAdminData;

import java.util.ArrayList;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;

/**
 * Used for admin API
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBAdminParameterFile {

    private String key;
    private ArrayList<MBAdminSingleFile> files;

    public MBAdminParameterFile(String key, ArrayList<MBAdminSingleFile> files) {
        this.key = key;
        this.files = files;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ArrayList<MBAdminSingleFile> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<MBAdminSingleFile> files) {
        this.files = files;
    }
}
