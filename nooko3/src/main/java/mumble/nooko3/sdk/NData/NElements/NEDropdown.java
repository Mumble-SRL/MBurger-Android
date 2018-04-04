package mumble.nooko3.sdk.NData.NElements;

import mumble.nooko3.sdk.Const;
import mumble.nooko3.sdk.NData.NAtomic.NClass;

/**
 * Identifies a dropdown value, may be linked to a poll or a list
 *
 * @author Enrico Ori
 * @version {@value Const#version}
 */
public class NEDropdown extends NClass {

    /**
     * Content of the text
     */
    private String content;

    public NEDropdown(long id, String name, String content) {
        this.content = content;
        initialize(id, name, Const.type_text);
    }

    @Override
    public void initialize(long id, String name, String type) {
        super.setId(id);
        super.setName(name);
        super.setType(type);
    }

    /**
     * Get content of the dropdown object
     */
    public String getContent() {
        return content;
    }

    /**
     * Set content of the dropdown object
     */
    public void setContent(String content) {
        this.content = content;
    }
}
