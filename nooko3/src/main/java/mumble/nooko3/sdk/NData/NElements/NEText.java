package mumble.nooko3.sdk.NData.NElements;

import mumble.nooko3.sdk.Const;
import mumble.nooko3.sdk.NData.NAtomic.NClass;

/**
 * Identifies a class with text and title
 *
 * @author  Enrico Ori
 * @version {@value mumble.nooko3.sdk.Const#version}
 */
public class NEText extends NClass {

    /**Content of the text*/
    private String content;

    public NEText(long id, String name, String content) {
        this.content = content;
        initialize(id, name, Const.type_text);
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
