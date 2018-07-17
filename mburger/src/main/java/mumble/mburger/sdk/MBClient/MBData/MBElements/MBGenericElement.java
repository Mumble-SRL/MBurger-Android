package mumble.mburger.sdk.MBClient.MBData.MBElements;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBClient.MBData.MBAtomic.MBClass;

/**
 * Identifies a generic element which simply has the content value of the element, it must be parsed and casted by the developer
 *
 * @author  Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBGenericElement extends MBClass {

    /**Content of the text*/
    private Object content;

    public MBGenericElement(long id, String name, Object content) {
        this.content = content;
        initialize(id, name, MBConstants.type_generic);
    }

    @Override
    public void initialize(long id, String name, String type) {
        super.setId(id);
        super.setName(name);
        super.setType(type);
    }

    /**Get content of the generic object*/
    public Object getContent() {
        return content;
    }

    /**Set content of the generic object*/
    public void setContent(Object content) {
        this.content = content;
    }
}
