package lib.saver;

import lib.Connection.MyDbConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Yuvielle on 15.02.14.
 */
public class DbSaver implements BaseSaver {

    private Statement st;

    public DbSaver() throws Exception {
        Connection conn = MyDbConnection.init().getConnection();
        this.st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
    }

    @Override
    public void insertObject(Object key, Object value) throws IOException, ClassNotFoundException, SQLException {
        ResultSet res = this.st.executeQuery("SELECT p.* FROM %s AS p where 1=2");
        res.moveToInsertRow();
        HashMap record = (HashMap) value;
        for (Object k : record.keySet() ) {
            Object v = record.get(k.toString());
            res.updateObject(k.toString(), v);
        }
        res.insertRow();
    }

    @Override
    public void updateObject(Object key, Object value) throws IOException, ClassNotFoundException, SQLException {
        ResultSet res = this.st.executeQuery(String.format("SELECT p.* FROM %s AS p where id= %s", (String) value, (Integer)key));
        HashMap record = (HashMap) value;
        for (Object k : record.keySet() ) {
            Object v = record.get(k.toString());
            res.updateObject(k.toString(), v);
        }
        res.updateRow();
    }

    @Override
    public Object getObject(Object key, String tablename) throws Exception {
        String query = String.format("SELECT p.* FROM %s AS p where id= %s", tablename, (Integer)key);
        return this.executeQuery(query).iterator().next();
    }


    @Override
    public void deleteObject(Object key, String tablename) throws SQLException {
        ResultSet res = this.st.executeQuery(String.format("SELECT p.* FROM %s AS p where id= %s", tablename, (Integer)key));
        res.deleteRow();
    }

    @Override
    public void clearHolder() {

    }

    @Override
    public Object removeObject(Object key, String tablename) throws IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean containsKey(Object key, String tablename) throws SQLException {
        ResultSet res = this.st.executeQuery(String.format("SELECT p.* FROM %s AS p where id= %s", tablename, (Integer)key));
        if(res.next()){
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void reset() throws IOException, ClassNotFoundException {

    }

    @Override
    public ArrayList getCollection(String tablename) throws Exception {
        String query = String.format("SELECT p.* FROM %s AS p", tablename);
        return this.executeQuery(query);
    }

    @Override
    public void addWhere(String expression) throws Exception {

    }

    @Override
    public void addJoin(String tablename) throws Exception {

    }

    @Override
    public ArrayList executeQuery(String query) throws Exception {
        ResultSet res = st.executeQuery(query);
        ResultSetMetaData rsmd = res.getMetaData();
        int columnCount = rsmd.getColumnCount();
        ArrayList collection = new ArrayList();
        while (res.next()){
            HashMap record = new HashMap();
            for (int i = 1; i < columnCount + 1; i++ ) {
                String name = rsmd.getColumnName(i);
                rsmd.getColumnTypeName(i);
                record.put(name, res.getObject(name));
            }
            collection.add(record);
        }
        return collection;
    }
}
