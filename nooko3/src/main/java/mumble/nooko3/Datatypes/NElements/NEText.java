package mumble.nooko3.Datatypes.NElements;

import mumble.nooko3.Datatypes.Const;
import mumble.nooko3.Datatypes.NAtomic.NClass;

/**
 * Identifies a class with text and title
 *
 * @author  Enrico Ori
 * @version {@value mumble.nooko3.Datatypes.Const#version}
 */
public class NEText extends NClass {

    /**Title of the text*/
    private String title;

    /**Content of the text*/
    private String content;

    public NEText(long id, String name, String title, String content) {
        this.title = title;
        this.content = content;
        initialize(id, name, Const.type_text);
    }

    @Override
    public void initialize(long id, String name, String type) {
        super.setId(id);
        super.setName(name);
        super.setType(type);
    }

    /**Get title of the text object*/
    public String getTitle() {
        return title;
    }

    /**Set title of the text object*/
    public void setTitle(String title) {
        this.title = title;
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
