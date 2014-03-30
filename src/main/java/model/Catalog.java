package model;

import lib.DataStorage;
import lib.saver.BaseSaver;
import lib.saver.DbSaver;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Yuvielle on 15.02.14.
 */
public class Catalog {
    private int _id;
    private String _name;
    private String _descript;
    private String _url;
    private BigDecimal _price;
    public String tablename = "shop_product";
    private BaseSaver saver;
    private List<Image> images;
    private int _count;

    public Catalog() throws Exception {
    }

    public void setStorage(String storageType) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        this.saver = (BaseSaver) Class.forName("lib.saver." + storageType).newInstance();
    }

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

    public ArrayList getProducts() throws Exception {
        return this.saver.getCollection(this.getClass().getName());
    }

    public void addProduct(String storageType, HashMap o) throws Exception {
        Object saver = Class.forName("lib.saver." + storageType).newInstance();
        DataStorage ds = new DataStorage((BaseSaver) saver);
        ds.saveObject(0, o, true);
    }

    public int addCountProduct(String storageType, int id, int count) throws Exception {
        Object saver = Class.forName("lib.saver." + storageType).newInstance();
        DataStorage ds = new DataStorage((BaseSaver) saver);
        HashMap record = (HashMap) ds.getObject(id, "shop_product");
        int s_count = (Integer) record.get("count");
        int e_count = s_count + count;
        HashMap o = new HashMap();
        o.put("id", id);
        o.put("count", e_count);
        ds.saveObject(id, o, false);
        return e_count;
    }

    public int buyProduct(String storageType, int id, int count) throws Exception {
        Object saver = Class.forName("lib.saver." + storageType).newInstance();
        DataStorage ds = new DataStorage((BaseSaver) saver);
        HashMap record = (HashMap) ds.getObject(id, "shop_product");
        int s_count = (Integer) record.get("count");
        int e_count = 0;
        int o_count = 0;
        if(s_count > count){
            e_count = s_count - count;
            o_count = count;
        } else if( s_count > 0 ){
            e_count = 0;
            o_count = s_count;
        }
        HashMap o = new HashMap();
        o.put("count", e_count);
        o.put("id", id);
        ds.saveObject(id, o, false);
        return o_count;
    }

    public Catalog findById(int id) throws Exception {
        return (Catalog) this.saver.getObject(id, "shop_product");
    }

    public String getDescript() {
        return _descript;
    }

    public void setDescript(String _descript) {
        this._descript = _descript;
    }

    public BigDecimal getPrice() {
        return _price;
    }

    public void setPrice(BigDecimal _price) {
        this._price = _price;
    }


    public static Catalog getProductById(int id, String storageType) throws Exception {
        Object saver = Class.forName("lib.saver." + storageType).newInstance();
        DataStorage ds = new DataStorage((BaseSaver) saver);
        HashMap record = (HashMap) ds.getObject(id, "shop_product");
        Catalog catalog = new Catalog();
        catalog.setId((Integer) Integer.parseInt(String.valueOf(record.get("id"))));
        catalog.setName((String) record.get("name"));
        catalog.setUrl((String) record.get("url"));
        catalog.setPrice((BigDecimal) new BigDecimal(record.get("price").toString()));
        catalog.setCount((Integer) Integer.parseInt(String.valueOf(record.get("count"))));
        catalog.setDescript((String) record.get("descript"));
        return catalog;
    }

    public static ArrayList<Catalog> getProductList(String storageType) throws Exception {
        Object saver = Class.forName("lib.saver." + storageType).newInstance();
        DataStorage ds = new DataStorage((BaseSaver) saver);
        ArrayList a = ds.getCollection("shop_product");
        ArrayList<Catalog> collection = new ArrayList<Catalog>();
        ListIterator iterator = a.listIterator();
        while (iterator.hasNext()){
            Catalog catalog = new Catalog();
            HashMap record = (HashMap) iterator.next();
            catalog.setId((Integer) Integer.parseInt(String.valueOf(record.get("id"))));
            catalog.setName((String) record.get("name"));
            catalog.setUrl((String) record.get("url"));
            catalog.setPrice((BigDecimal) new BigDecimal(record.get("price").toString()));
            catalog.setCount((Integer) Integer.parseInt(String.valueOf(record.get("count"))));
            catalog.setDescript((String) record.get("descript"));
            collection.add(catalog);
        }
        return collection;
    }

    public int getCount() {
        return _count;
    }

    public void setCount(int _count) {
        this._count = _count;
    }
}
