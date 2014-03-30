package actions;

/**
 * Created with IntelliJ IDEA.
 * User: Yuvielle
 * Date: 10.08.13
 * Time: 13:47
 * To change this template use File | Settings | File Templates.
 */

import model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

public class UserController {

    public static void register(HttpServletRequest request, HttpServletResponse response) throws Exception {

        if(request.getMethod().equalsIgnoreCase("POST")){
            String f_username = request.getParameter("username");
            String f_fullname = request.getParameter("fullname");
            String f_password = request.getParameter("password");
            String repeat_password = request.getParameter("repeat_password");
            Boolean is_valid = true;
            if(!f_password.equals(repeat_password)){
                request.setAttribute("equal_passwords", "password not equal with repeat password");
                is_valid = false;
            }
            Pattern email_pattern = Pattern.compile("^([A-Za-z0-9._%+-]+)@((?:[-a-z0-9]+\\.)+[a-z]{2,})$");
            if(!email_pattern.matcher(f_username).matches()){
                request.setAttribute("email_error", "email is not valid");
                is_valid = false;
            }
            if(is_valid){
                try{
                    User user = new User();
                    user.setFirstName(f_fullname);
                    user.setPassword(f_password);
                    user.setUsername(f_username);
                    //user.save();

                    int userId = user.getId();
                    request.getSession(false).setAttribute("user_id", userId);
                    request.setAttribute("message", "pass: " + request.getSession(false).getAttribute("user_id"));

                    //response.sendRedirect(request.getContextPath() + "?module=gallery&action=list");
                    return;
                }
                catch (Exception e){
                    request.setAttribute("exception", e.getMessage());
                }
            }
        }
        else{
            if(request.getSession(false).getAttribute("user_id") != null){
                request.setAttribute("message", "you registered already " + request.getSession(false).getAttribute("user_id"));
            }
            else{
                request.setAttribute("message", "you not in system naw");
            }
        }
    }

    public static void login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setAttribute("is_autorize", 0);
        if(request.getMethod().equalsIgnoreCase("POST")){

            String f_username = request.getParameter("username");
            String f_password = request.getParameter("password");
            int user_id = 0;

            try{
                user_id = User.login(f_username, f_password);
            }
                catch (Exception e){
                request.setAttribute("exception", e.getMessage());
            }
            if(user_id > 0){
                request.getSession(false).setAttribute("user_id",user_id);
                request.setAttribute("message", "pass: " + request.getSession().getAttribute("user_id"));
                response.sendRedirect(request.getContextPath() + "?module=gallery&action=list");
                return;
            }
            else{
                request.setAttribute("message", "login incorrect");
            }
        }
        else{
            if(request.getSession(false).getAttribute("user_id") != null){
                request.setAttribute("is_autorize", 1);
           }
            else{
                request.setAttribute("message", "login form");
            }
        }
    }

    public static void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession(false).removeAttribute("user_id");
        response.sendRedirect(request.getContextPath() + "?module=user&action=login");
        return;
    }
}
