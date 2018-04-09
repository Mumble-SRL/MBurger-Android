package mumble.nooko3.sdk.NKData.NKElements;

import mumble.nooko3.sdk.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKData.NKAtomic.NKClass;

/**
 * Identifies a class with title HTML text
 *
 * @author  Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKWYSIWYGElement extends NKClass {

    /**HTML Content of the WYSIWYG*/
    private String content;

    public NKWYSIWYGElement(long id, String name, String content) {
        this.content = content;
        initialize(id, name, NKConstants.type_wysiwyg);
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