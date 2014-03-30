package actions;

import model.Catalog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Yuvielle on 12.02.14.
 */
public class ManagerController {

    public static void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

    public static void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(request.getMethod().equalsIgnoreCase("POST")){

        }

    }

    public static void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(request.getMethod().equalsIgnoreCase("POST")){

        }

    }

    public static void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(request.getMethod().equalsIgnoreCase("POST")){

        }

    }

    public static void settings(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(request.getMethod().equalsIgnoreCase("POST")){
            String storage = request.getParameter("storage");
            request.getSession(false).setAttribute("storage", storage);
        }
        String current_storage = (String) request.getSession(false).getAttribute("storage");
        request.setAttribute("current_storage", current_storage);
    }
}
