package mumble.nooko3.Datatypes.NElements;

import java.util.ArrayList;
import mumble.nooko3.Datatypes.Const;
import mumble.nooko3.Datatypes.NAtomic.NClass;
import mumble.nooko3.Datatypes.NElements.NSubElements.NImage;

/**
 * Identifies an array of images, it will always be and array even if the image added in the backend is unique
 *
 * @author  Enrico Ori
 * @version {@value mumble.nooko3.Datatypes.Const#version}
 */
public class NEImages extends NClass {

    /**Array of {@link NImage NImages}*/
    private ArrayList<NImage> images;

    public NEImages(long id, String name, ArrayList<NImage> images) {
        this.images = images;
        initialize(id, name, Const.type_images);
    }

    @Override
    public void initialize(long id, String name, String type) {
        super.setId(id);
        super.setName(name);
        super.setType(type);
    }

    /**Get the array of {@link NImage NImages}*/
    public ArrayList<NImage> getImages() {
        return images;
    }

    /**Set a new array of {@link NImage NImages}*/
    public void setImages(ArrayList<NImage> images) {
        this.images = images;
    }

    /**Get the first {@link NImage NImage}, which sometimes is enough as a preview*/
    public NImage getFirstImage(){
        return this.images.get(0);
    }
}