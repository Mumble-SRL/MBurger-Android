package mumble.nooko3.sdk.NKData.NKElements;

import mumble.nooko3.sdk.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKData.NKAtomic.NKClass;

/**
 * Identifies a class with text and title
 *
 * @author  Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKTextElement extends NKClass {

    /**Content of the text*/
    private String content;

    public NKTextElement(long id, String name, String content) {
        this.content = content;
        initialize(id, name, NKConstants.type_text);
    }

    @Override
    public void initialize(long id, String name, String type) {
        super.setId(id);
        super.setName(name);
        super.setType(type);
    }

    /**Get content of the text object*/
    public String getContent() {
        return content;
    }

    /**Set content of the text object*/
    public void setContent(String content) {
        this.content = content;
    }
}
