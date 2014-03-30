package model;

import lib.saver.BaseSaver;

import java.io.IOException;
import java.util.List;

/**
 * Created by Yuvielle on 16.02.14.
 */
public class Image {
    private int _id;
    private String _name;
    private String _url;
    public String tablename = "shop_image";
    private BaseSaver saver;

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public String getUrl() {
        return _url;
    }

    public void setUrl(String _url) {
        this._url = _url;
    }

    public List getImages() throws Exception {
        return this.saver.getCollection("Image");
    }

    public void addImage(){

    }

    public Image findById(int id, String tablename) throws Exception {
        return (Image) this.saver.getObject(id, tablename);
    }
}
