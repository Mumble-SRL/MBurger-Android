package mumble.nooko3.sdk.NKData.NKElements;

import mumble.nooko3.sdk.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKData.NKAtomic.NKClass;

/**
 * Identifies a generic element which simply has the content value of the element, it must be parsed and casted by the developer
 *
 * @author  Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKGenericElement extends NKClass {

    /**Content of the text*/
    private Object content;

    public NKGenericElement(long id, String name, Object content) {
        this.content = content;
        initialize(id, name, NKConstants.type_generic);
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
