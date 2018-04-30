package mumble.nooko3.sdk.NKData.NKElements;

import java.util.ArrayList;
import mumble.nooko3.sdk.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKData.NKAtomic.NKClass;
import mumble.nooko3.sdk.NKData.NKElements.NKSubElements.NKImage;

/**
 * Identifies an array of images, it will always be and array even if the image added in the backend is unique
 *
 * @author  Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKImages extends NKClass {

    /**Array of {@link NKImage NImages}*/
    private ArrayList<NKImage> images;

    public NKImages(long id, String name, ArrayList<NKImage> images) {
        this.images = images;
        initialize(id, name, NKConstants.type_image);
    }

    @Override
    public void initialize(long id, String name, String type) {
        super.setId(id);
        super.setName(name);
        super.setType(type);
    }

    /**Get the array of {@link NKImage NImages}*/
    public ArrayList<NKImage> getImages() {
        return images;
    }

    /**Set a new array of {@link NKImage NImages}*/
    public void setImages(ArrayList<NKImage> images) {
        this.images = images;
    }

    /**Get the first {@link NKImage NKImage}, which sometimes is enough as a preview*/
    public NKImage getFirstImage(){
        return this.images.get(0);
    }
}