package mumble.mburger.sdk.MBAdmin.MBAdminData;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;

/**
 * Used for admin API
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBAdminSingleFile {

    private String name, mime_type, file_path;

    public MBAdminSingleFile(String name, String mime_type, String file_path) {
        this.name = name;
        this.mime_type = mime_type;
        this.file_path = file_path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMime_type() {
        return mime_type;
    }

    public void setMime_type(String mime_type) {
        this.mime_type = mime_type;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
}
