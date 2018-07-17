package mumble.mburger.sdk.MBClient.MBData.MBElements;

import java.util.ArrayList;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBClient.MBData.MBAtomic.MBClass;
import mumble.mburger.sdk.MBClient.MBData.MBElements.MBSubElements.MBFile;

/**
 * Identifies an array of files, it will always be and array even if the file added in the backend is unique
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBMediaElement extends MBClass {

    /**
     * Array of {@link mumble.mburger.sdk.MBClient.MBData.MBElements.MBSubElements.MBFile MBFiles}
     */
    private ArrayList<MBFile> files;

    public MBMediaElement(long id, String name, ArrayList<MBFile> files) {
        this.files = files;
        initialize(id, name, MBConstants.type_media);
    }

    @Override
    public void initialize(long id, String name, String type) {
        super.setId(id);
        super.setName(name);
        super.setType(type);
    }

    /**
     * Get the array of {@link MBFile MBFiles}
     */
    public ArrayList<MBFile> getFiles() {
        return files;
    }

    /**
     * Set a new array of {@link MBFile MBFiles}
     */
    public void setFiles(ArrayList<MBFile> files) {
        this.files = files;
    }

    /**
     * Get the first {@link MBFile MBFile}, which sometimes is enough
     */
    public MBFile getFirstMedia() {
        return this.files.get(0);
    }
}