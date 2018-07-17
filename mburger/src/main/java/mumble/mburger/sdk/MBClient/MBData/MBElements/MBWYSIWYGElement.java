package mumble.mburger.sdk.MBClient.MBData.MBElements;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBClient.MBData.MBAtomic.MBClass;

/**
 * Identifies a class with title HTML text
 *
 * @author  Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBWYSIWYGElement extends MBClass {

    /**HTML Content of the WYSIWYG*/
    private String content;

    public MBWYSIWYGElement(long id, String name, String content) {
        this.content = content;
        initialize(id, name, MBConstants.type_wysiwyg);
    }

    @Override
    public void initialize(long id, String name, String type) {
        super.setId(id);
        super.setName(name);
        super.setType(type);
    }

    /**Get content of the WYSIWYG*/
    public String getContent() {
        return content;
    }

    /**Set content of the WYSIWYG*/
    public void setContent(String content) {
        this.content = content;
    }
}