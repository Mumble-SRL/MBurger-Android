package mumble.mburger.sdk.MBClient.MBData.MBElements;

import java.util.ArrayList;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBClient.MBData.MBAtomic.MBClass;
import mumble.mburger.sdk.MBClient.MBData.MBElements.MBSubElements.MBImage;

/**
 * Identifies an array of images, it will always be and array even if the image added in the backend is unique
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBImages extends MBClass {

    /**
     * Array of {@link mumble.mburger.sdk.MBClient.MBData.MBElements.MBSubElements.MBImage MBImages}
     */
    private ArrayList<MBImage> images;

    public MBImages(long id, String name, ArrayList<MBImage> images) {
        this.images = images;
        initialize(id, name, MBConstants.type_image);
    }

    @Override
    public void initialize(long id, String name, String type) {
        super.setId(id);
        super.setName(name);
        super.setType(type);
    }

    /**
     * Get the array of {@link MBImage MBImages}
     */
    public ArrayList<MBImage> getImages() {
        return images;
    }

    /**
     * Set a new array of {@link MBImage MBImages}
     */
    public void setImages(ArrayList<MBImage> images) {
        this.images = images;
    }

    /**
     * Get the first {@link MBImage MBImage}, which sometimes is enough as a preview
     */
    public MBImage getFirstImage() {
        return this.images.get(0);
    }
}