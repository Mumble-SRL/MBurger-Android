package mumble.nooko3.sdk.NKData.NKElements;

import mumble.nooko3.sdk.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKData.NKAtomic.NKClass;

/**
 * Identifies a dropdown value, may be linked to a poll or a list
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKDropdownElement extends NKClass {

    /**
     * Content of the text
     */
    private String content;

    public NKDropdownElement(long id, String name, String content) {
        this.content = content;
        initialize(id, name, NKConstants.type_text);
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
