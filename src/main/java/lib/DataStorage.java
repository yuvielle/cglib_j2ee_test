package lib;

import lib.saver.BaseSaver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Yuvielle on 16.02.14.
 */
public class DataStorage {

    private BaseSaver s;

    //set base strategy class for dynamic change storage by user
    public DataStorage(BaseSaver s){
        this.s = s;
    }

    public void setDataStorage(BaseSaver s){
        this.s = s;
    }

    //save to storage
    public void saveObject(Integer k, HashMap o, boolean is_new ) throws Exception {
        if(is_new){
            this.s.insertObject(k,o);
        }
        else{
            this.s.updateObject(k,o);
        }
    }

    //get list of objects for show to user
    public ArrayList getCollection(String modelname) throws Exception {
        return this.s.getCollection(modelname);
    }

    public Object getObject(Integer id, String tablename) throws Exception {
        return  this.s.getObject(id, tablename);
    }

    public void addJoin(String tablename) throws Exception {
        this.s.addJoin(tablename);
    }

    public void addWhere(String expression) throws Exception {
        this.s.addWhere(expression);
    }
    public Object execute(String query) throws Exception {
        return this.s.executeQuery(query);
    }
}
