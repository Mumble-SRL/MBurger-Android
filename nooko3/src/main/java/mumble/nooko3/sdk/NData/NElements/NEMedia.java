package mumble.nooko3.sdk.NData.NElements;

import java.util.ArrayList;
import mumble.nooko3.sdk.NConstants.Const;
import mumble.nooko3.sdk.NData.NAtomic.NClass;
import mumble.nooko3.sdk.NData.NElements.NSubElements.NFile;

/**
 * Identifies an array of files, it will always be and array even if the file added in the backend is unique
 *
 * @author Enrico Ori
 * @version {@value Const#version}
 */
public class NEMedia extends NClass {

    /**
     * Array of {@link mumble.nooko3.sdk.NData.NElements.NSubElements.NFile NFiles}
     */
    private ArrayList<NFile> files;

    public NEMedia(long id, String name, ArrayList<NFile> files) {
        this.files = files;
        initialize(id, name, Const.type_media);
    }

    @Override
    public void initialize(long id, String name, String type) {
        super.setId(id);
        super.setName(name);
        super.setType(type);
    }

    /**
     * Get the array of {@link NFile NFiles}
     */
    public ArrayList<NFile> getFiles() {
        return files;
    }

    /**
     * Set a new array of {@link NFile NFiles}
     */
    public void setFiles(ArrayList<NFile> files) {
        this.files = files;
    }

    /**
     * Get the first {@link NFile NFile}, which sometimes is enough
     */
    public NFile getFirstMedia() {
        return this.files.get(0);
    }
}