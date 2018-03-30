package mumble.nooko3.Datatypes.NElements;

import mumble.nooko3.Datatypes.Const;
import mumble.nooko3.Datatypes.NAtomic.NClass;

/**
 * Identifies a class with title HTML text
 *
 * @author  Enrico Ori
 * @version {@value mumble.nooko3.Datatypes.Const#version}
 */
public class NEWYSIWYG extends NClass {

    /**Title of the WYSIWYG*/
    private String title;

    /**HTML Content of the WYSIWYG*/
    private String content;

    public NEWYSIWYG(long id, String name, String title, String content) {
        this.title = title;
        this.content = content;
        initialize(id, name, Const.type_wysiwyg);
    }

    @Override
    public void initialize(long id, String name, String type) {
        super.setId(id);
        super.setName(name);
        super.setType(type);
    }

    /**Get title of the WYSIWYG*/
    public String getTitle() {
        return title;
    }

    /**Set title of the WYSIWYG*/
    public void setTitle(String title) {
        this.title = title;
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