package mumble.nooko3.sdk.NKData.NKElements;

import mumble.nooko3.sdk.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKData.NKAtomic.NKClass;

/**
 * Identifies a boolean class
 *
 * @author  Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKCheckboxElement extends NKClass {

    /**Content of the text*/
    private boolean content;

    public NKCheckboxElement(long id, String name, boolean content) {
        this.content = content;
        initialize(id, name, NKConstants.type_checkbox);
    }

    @Override
    public void initialize(long id, String name, String type) {
        super.setId(id);
        super.setName(name);
        super.setType(type);
    }

    /**Get content of the checkbox object*/
    public boolean getContent() {
        return content;
    }

    /**Set content of the checkbox object*/
    public void setContent(boolean content) {
        this.content = content;
    }
}
