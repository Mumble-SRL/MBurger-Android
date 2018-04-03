package mumble.nooko3.sdk.NData.NElements.NSubElements;

import java.io.Serializable;

/**
 * Single file inside the {@link mumble.nooko3.sdk.NData.NElements.NEMedia NEMedia}
 *
 * @author  Enrico Ori
 * @version {@value mumble.nooko3.sdk.Const#version}
 */
public class NFile implements Serializable{

    /**URL of the file*/
    private String url;

    public NFile(String url) {
        this.url = url;
    }

    /**Get the URL of the file*/
    public String getUrl() {
        return url;
    }

    /**Set the URL of the file*/
    public void setUrl(String url) {
        this.url = url;
    }

}
