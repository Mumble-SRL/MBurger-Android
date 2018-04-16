package mumble.nooko3.sdk.NKData.NKElements.NKSubElements;

import java.io.Serializable;

import mumble.nooko3.sdk.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKData.NKElements.NKMediaElement;

/**
 * Single file inside the {@link NKMediaElement NKMediaElement}
 *
 * @author  Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKFile implements Serializable{

    /**Id of the single file*/
    private long id;

    /**Mime type of the file*/
    private String mime_type;

    /**Size of the file in byes*/
    private long size;

    /**URL of the file*/
    private String url;

    /**Basic type of the file (audio, document...)*/
    private String basicType;

    public NKFile(long id, String mime_type, long size, String url, String basicType) {
        this.id = id;
        this.mime_type = mime_type;
        this.size = size;
        this.url = url;
        this.basicType = basicType;
    }

    /**Get the URL of the file*/
    public String getUrl() {
        return url;
    }

    /**Set the URL of the file*/
    public void setUrl(String url) {
        this.url = url;
    }

    /**Get the unique ID of the file*/
    public long getId() {
        return id;
    }

    /**Set the unique ID of the file*/
    public void setId(long id) {
        this.id = id;
    }

    /**Get the mime type of the file*/
    public String getMime_type() {
        return mime_type;
    }

    /**Set the mime type of the file*/
    public void setMime_type(String mime_type) {
        this.mime_type = mime_type;
    }

    /**Get the size of the file in bytes*/
    public long getSize() {
        return size;
    }

    /**Sets the size of the file in bytes*/
    public void setSize(long size) {
        this.size = size;
    }

    /**Get the basic type of the file*/
    public String getBasicType() {
        return basicType;
    }

    /**Set the basic type of the file*/
    public void setBasicType(String basicType) {
        this.basicType = basicType;
    }
}