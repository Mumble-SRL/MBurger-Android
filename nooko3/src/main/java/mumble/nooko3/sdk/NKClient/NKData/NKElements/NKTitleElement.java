package mumble.nooko3.sdk.NKData.NKElements;

import mumble.nooko3.sdk.Common.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKData.NKAtomic.NKClass;

/**
 * Identifies a class title
 *
 * @author  Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKTitleElement extends NKClass {

    /**Content of the text*/
    private String content;

    public NKTitleElement(long id, String name, String content) {
        this.content = content;
        initialize(id, name, NKConstants.type_text);
    }

    @Override
    public void initialize(long id, String name, String type) {
        super.setId(id);
        super.setName(name);
        super.setType(type);
    }

    /**Get content of the title object*/
    public String getContent() {
        return content;
    }

    /**Set content of the title object*/
    public void setContent(String content) {
        this.content = content;
    }
}
