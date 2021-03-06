package mumble.mburger.sdk.MBClient.MBData.MBElements.MBSubElements;

import java.io.Serializable;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;

/**
 * Single image inside the {@link mumble.mburger.sdk.MBClient.MBData.MBElements.MBImages MBImages}
 *
 * @author  Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBImage implements Serializable{

    /**Id of the single image*/
    private long id;

    /**Mime type of the image*/
    private String mime_type;

    /**Size of the image in byes*/
    private long size;

    /**URL of the image*/
    private String url;

    public MBImage(long id, String url, String mime_type, long size) {
        this.id = id;
        this.mime_type = mime_type;
        this.size = size;
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

    /**Get the unique id of the image*/
    public long getId() {
        return id;
    }

    /**Set the unique id of the image*/
    public void setId(long id) {
        this.id = id;
    }

    /**Get the Mime Type of the image*/
    public String getMime_type() {
        return mime_type;
    }

    /**Set the Mime Type of the image*/
    public void setMime_type(String mime_type) {
        this.mime_type = mime_type;
    }

    /**Get the size of the image*/
    public long getSize() {
        return size;
    }

    /**Set the size of the image*/
    public void setSize(long size) {
        this.size = size;
    }
}
