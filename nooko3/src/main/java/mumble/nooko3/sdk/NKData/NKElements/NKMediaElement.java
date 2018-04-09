package mumble.nooko3.sdk.NKData.NKElements;

import java.util.ArrayList;
import mumble.nooko3.sdk.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKData.NKAtomic.NKClass;
import mumble.nooko3.sdk.NKData.NKElements.NKSubElements.NKFile;

/**
 * Identifies an array of files, it will always be and array even if the file added in the backend is unique
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKMediaElement extends NKClass {

    /**
     * Array of {@link NKFile NFiles}
     */
    private ArrayList<NKFile> files;

    public NKMediaElement(long id, String name, ArrayList<NKFile> files) {
        this.files = files;
        initialize(id, name, NKConstants.type_media);
    }

    @Override
    public void initialize(long id, String name, String type) {
        super.setId(id);
        super.setName(name);
        super.setType(type);
    }

    /**
     * Get the array of {@link NKFile NFiles}
     */
    public ArrayList<NKFile> getFiles() {
        return files;
    }

    /**
     * Set a new array of {@link NKFile NFiles}
     */
    public void setFiles(ArrayList<NKFile> files) {
        this.files = files;
    }

    /**
     * Get the first {@link NKFile NKFile}, which sometimes is enough
     */
    public NKFile getFirstMedia() {
        return this.files.get(0);
    }
}