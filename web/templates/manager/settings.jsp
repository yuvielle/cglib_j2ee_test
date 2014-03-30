<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--
  Created by IntelliJ IDEA.
  User: Yuvielle
  Date: 11.08.13
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<t:wrapper_manager>
    <div id="mainContent">
        <h2>Test shop gallery</h2>
    <h1>Welcome</h1>
    <p><jsp:text>${message}</jsp:text></p>
        <c:choose>
            <c:when test="${k_exception != null && !k_exception.isEmpty()}"><p>k_exception:<jsp:text>${k_exception}</jsp:text></p></c:when>
            <c:when test="${exception != null && !exception.isEmpty()}"><p>exception:<jsp:text>${exception}</jsp:text></p></c:when>
        </c:choose>
        <div>
            <div id="forgotbox-text">Settings</div>
            <!--  start forgot-inner -->
            <div id="forgot-inner">
                <form action="" method="post">
                <table border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <th>Select storage:</th>
                        <td><select name="storage">
                            <option value="XmlSaver" <c:choose>
                                                        <c:when test="${current_storage == 'XmlSaver'}">selected
                                                        </c:when></c:choose>>Xml storage</option>
                            <option value="DbSaver"<c:choose>
                                <c:when test="${current_storage == 'DbSaver'}">selected
                                </c:when></c:choose>>Db storage</option>
                        </select></td>
                    </tr>
                    <input type="submit" name="submit" value="submit">
                </table>
                </form>
            </div>
            <!--  end forgot-inner -->
            <div class="clear"></div>
        </div>
    </div>
    <br class="clearfloat" />
</t:wrapper_manager>