package mumble.mburger.sdk.MBClient.MBData.MBElements;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBClient.MBData.MBAtomic.MBClass;

/**
 * Identifies a class with text and title
 *
 * @author  Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBTextElement extends MBClass {

    /**Content of the text*/
    private String content;

    public MBTextElement(long id, String name, String content) {
        this.content = content;
        initialize(id, name, MBConstants.type_text);
    }

    @Override
    public void initialize(long id, String name, String type) {
        super.setId(id);
        super.setName(name);
        super.setType(type);
    }

    /**Get content of the text object*/
    public String getContent() {
        return content;
    }

    /**Set content of the text object*/
    public void setContent(String content) {
        this.content = content;
    }
}
