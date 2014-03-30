import model.Catalog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by Yuvielle on 30.03.14.
 */
public class RefTestClass {

    public RefTestClass(){

    }
    public void list(HttpServletRequest request, HttpServletResponse response) {
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
}
