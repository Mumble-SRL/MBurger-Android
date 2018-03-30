package mumble.nooko3.Datatypes.NElements.NSubElements;

import java.io.Serializable;

/**
 * Single image inside the {@link mumble.nooko3.Datatypes.NElements.NEImages NEImages}
 *
 * @author  Enrico Ori
 * @version {@value mumble.nooko3.Datatypes.Const#version}
 */
public class NImage implements Serializable{

    /**URL of the image*/
    private String url;

    public NImage(String url) {
        this.url = url;
    }

    /**Get the URL of the image*/
    public String getUrl() {
        return url;
    }

    /**Set the URL of the image*/
    public void setUrl(String url) {
        this.url = url;
    }
}
