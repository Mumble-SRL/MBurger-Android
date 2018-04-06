package mumble.nooko3.sdk.NData.NElements;

import mumble.nooko3.sdk.NConstants.NConst;
import mumble.nooko3.sdk.NData.NAtomic.NClass;

/**
 * Identifies a class title
 *
 * @author  Enrico Ori
 * @version {@value mumble.nooko3.sdk.NConstants.NConst#version}
 */
public class NETitle extends NClass {

    /**Content of the text*/
    private String content;

    public NETitle(long id, String name, String content) {
        this.content = content;
        initialize(id, name, NConst.type_text);
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
