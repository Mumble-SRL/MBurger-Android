package mumble.nooko3.sdk.NData.NElements;

import mumble.nooko3.sdk.Const;
import mumble.nooko3.sdk.NData.NAtomic.NClass;

/**
 * Identifies a boolean class
 *
 * @author  Enrico Ori
 * @version {@value Const#version}
 */
public class NECheckbox extends NClass {

    /**Content of the text*/
    private boolean content;

    public NECheckbox(long id, String name, boolean content) {
        this.content = content;
        initialize(id, name, Const.type_checkbox);
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
