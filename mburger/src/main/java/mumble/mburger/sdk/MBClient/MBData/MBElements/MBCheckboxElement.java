package mumble.mburger.sdk.MBClient.MBData.MBElements;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBClient.MBData.MBAtomic.MBClass;

/**
 * Identifies a boolean class
 *
 * @author  Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBCheckboxElement extends MBClass {

    /**Content of the text*/
    private boolean content;

    public MBCheckboxElement(long id, String name, boolean content) {
        this.content = content;
        initialize(id, name, MBConstants.type_checkbox);
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
