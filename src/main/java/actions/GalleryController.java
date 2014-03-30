package actions;

import lib.Connection.MyDbConnection;
import model.Catalog;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Yuvielle
 * Date: 12.02.14
 * Time: 19:07
 * To change this template use File | Settings | File Templates.
 */
public class GalleryController {

    public static void list(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<Catalog> products = new ArrayList<Catalog>();
        String storage = (String) request.getSession(false).getAttribute("storage");
        try{
            products = Catalog.getProductList(storage);
        } catch(Exception e){
            request.setAttribute("k_exception", "catch exception: " + e.getMessage());
        }
        request.setAttribute("products", products);
        request.setAttribute("message", "count of pics " + products.size() + " storage=" + storage);
    }

    public static void detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String storage = (String) request.getSession(false).getAttribute("storage");
        Integer id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("message", "detail test message");
        Catalog record = Catalog.getProductById(id, storage);
        request.setAttribute("product", record);
    }

    public static void buy(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(request.getMethod().equalsIgnoreCase("POST")){
            String storage = (String) request.getSession(false).getAttribute("storage");
            Integer id = Integer.parseInt(request.getParameter("id"));
            Integer count = Integer.parseInt(request.getParameter("count"));
            Catalog product = new Catalog();
            product.setStorage("DbSaver");
            int o_count = product.buyProduct(storage, id, count);
        }

    }
}
