package mumble.nooko3.sdk.NData.NElements;

import mumble.nooko3.sdk.NConstants.NConst;
import mumble.nooko3.sdk.NData.NAtomic.NClass;

/**
 * Identifies a class with title HTML text
 *
 * @author  Enrico Ori
 * @version {@value NConst#version}
 */
public class NEWYSIWYG extends NClass {

    /**HTML Content of the WYSIWYG*/
    private String content;

    public NEWYSIWYG(long id, String name, String content) {
        this.content = content;
        initialize(id, name, NConst.type_wysiwyg);
    }

    @Override
    public void initialize(long id, String name, String type) {
        super.setId(id);
        super.setName(name);
        super.setType(type);
    }

    /**Get content of the WYSIWYG*/
    public String getContent() {
        return content;
    }

    /**Set content of the WYSIWYG*/
    public void setContent(String content) {
        this.content = content;
    }
}