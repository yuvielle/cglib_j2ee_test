package lib.saver;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Yuvielle on 15.02.14.
 */
public interface BaseSaver <KeyType,ValueType >{
    void insertObject(KeyType key, ValueType value) throws Exception;
    void updateObject(KeyType key, ValueType value) throws Exception;
    ValueType getObject(KeyType key, String tablename) throws Exception;
    void  deleteObject(KeyType key, String tablename) throws Exception;
    void clearHolder();
    ValueType removeObject(KeyType key, String tablename) throws Exception;
    boolean containsKey(KeyType key, String tablename)throws Exception;
    int size();
    public void reset() throws IOException, ClassNotFoundException;
    public ArrayList getCollection(String modelname) throws Exception;
    public void addWhere(String expression) throws Exception;
    public void addJoin(String tablename) throws Exception;
    public ArrayList executeQuery(String query) throws  Exception;
}
