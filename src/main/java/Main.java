import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;

/**
 * Created by IntelliJ IDEA.
 * User: Yuvielle
 * Date: 17.10.12
 * Time: 23:14
 * To change this template use File | Settings | File Templates.
 */
public class Main  extends HttpServlet {

    public Hashtable<String,String> controllers = new Hashtable<String, String>();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        performTask(request, response);
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

        performTask(request, response);
    }

    private void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //create list of controller class names for dynamic instance in dispatcher
    	controllers.put("manager", "actions.ManagerController");
        controllers.put("gallery", "actions.GalleryController");

        //add content type for web outpet to response
        response.setContentType("text/html");

        //get module and action for dispatching
    	String action = request.getParameter("action");
        String module = request.getParameter("module");

        //get storage type for use match strategy
        String storage = (String) request.getSession(false).getAttribute("storage");

        //if storage is null (begin work application) we'll use default storage
        if(storage == null){
            request.getSession(false).setAttribute("storage", "XmlSaver");
        }
        RequestDispatcher view = null;

        //if we not get module and action from request, we have to set default module and controller
        if (module != null) {

            try{
                Class<?> controller = Class.forName(controllers.get(module));
               // Class<?> c = Class.forName("RefTestClass");
                FastClass fc = FastClass.create(controller);
                Object obj = controller.newInstance();

                Method method = controller.getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
                FastMethod fm = fc.getMethod(method);
                Object[] arguments = new Object[]{request, response};
                fm.invoke(null, arguments);
                       // method.invoke(null, request, response);
            } catch (ClassNotFoundException e){
                request.setAttribute("exception", "ClassNotFoundException from dispatcher: " + e.getMessage());
            } catch (NoSuchMethodException me){
                request.setAttribute("exception", "NoSuchMethodException from dispatcher: " + me.getMessage());
            } catch (IllegalAccessException iax){
                request.setAttribute("exception", "IllegalAccessException iax from dispatcher: " + iax.getMessage());
            } catch (InvocationTargetException it){
                request.setAttribute("exception",  "InvocationTargetException from dispatcher: " + it.getMessage());
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
            view = request.getRequestDispatcher("/templates/" + module + "/" + action + ".jsp");
        }

        //set view file
        if (view == null){
            response.sendRedirect(request.getContextPath() + "?module=gallery&action=list");
            return;
        }

        //run
        view.forward(request,response);
        return;
    }


    class A {
        public int value =  0;

        public void add(int x) {
            value += x;
        }
    }

}
