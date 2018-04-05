package mumble.nooko3.sdk.NData.NElements;

import mumble.nooko3.sdk.NConstants.NConst;
import mumble.nooko3.sdk.NData.NAtomic.NClass;

/**
 * Identifies a generic element which simply has the content value of the element, it must be parsed and casted by the developer
 *
 * @author  Enrico Ori
 * @version {@value NConst#version}
 */
public class NEGeneric extends NClass {

    /**Content of the text*/
    private Object content;

    public NEGeneric(long id, String name, Object content) {
        this.content = content;
        initialize(id, name, NConst.type_generic);
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
