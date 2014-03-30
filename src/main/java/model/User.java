package model;

import lib.Connection.MyDbConnection;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Yuvielle on 15.02.14.
 */
public class User {
    private int _id;
    private String _first_name;
    private String _last_name;
    private String _username;
    private String _password;
    public String tablename = "shop_user";

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getFirstName() {
        return _first_name;
    }

    public void setFirstName(String _first_name) {
        this._first_name = _first_name;
    }

    public String getLastName() {
        return _last_name;
    }

    public void setLastName(String _last_name) {
        this._last_name = _last_name;
    }

    public String getUsername() {
        return _username;
    }

    public void setUsername(String _username) {
        this._username = _username;
    }

    public String getPassword() {
        return _password;
    }


    private void setEncriptedPassword(String _password){
        this._password = _password;
    }

    public void setPassword(String _password) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(_password.getBytes("UTF-8"));
        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        this._password = hexString.toString();
    }

    static public int login(String username, String password) throws Exception {
        Connection conn = MyDbConnection.init().getConnection();
        Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet res = st.executeQuery("SELECT u.* FROM glr_user AS u WHERE u.username = \'" + username + "\'");
        if(!res.first()){
            return 0;
        }
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes("UTF-8"));
        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        if(res.getString("username").equals(username) && res.getString("password").equals(hexString.toString())){
            return res.getInt("id");
        }
        return 0;
    }
}
