package mumble.mburger.sdk.MBClient.MBData.MBShopify;

import java.io.Serializable;
import java.util.ArrayList;

public class MBShopifyCollection implements Serializable{

    private long id;
    private String text, href, icon, target, title, image;
    private ArrayList<MBShopifyCollection> children;

    public MBShopifyCollection(long id, String text, String href, String icon, String target,
                               String title, String image, ArrayList<MBShopifyCollection> children) {
        this.id = id;
        this.text = text;
        this.href = href;
        this.icon = icon;
        this.target = target;
        this.title = title;
        this.image = image;
        this.children = children;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<MBShopifyCollection> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<MBShopifyCollection> children) {
        this.children = children;
    }
}
