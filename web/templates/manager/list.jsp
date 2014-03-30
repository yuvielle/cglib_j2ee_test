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
    <c:choose>
        <c:when test="${products != null && !products.isEmpty()}">
                <c:forEach items="${products}" var="product">
                    <c:choose>
                        <c:when test="${product.count > 0}">
                            <div class="shopId">
                                <div class="shopIdHeader">
                                    <div class="shopIdPrice">${product.price}, осталось ${product.count}</div>
                                    <div><a href="${pageContext.request.contextPath}?module=gallery&action=detail&id=${product.id}"><input type="button" value="buy!" class="buttonbuy" /></a></div>
                                </div>
                            <div><a href="${pageContext.request.contextPath}?module=gallery&action=detail&id=${product.id}"><img src="${pageContext.request.contextPath}/images/pic${product.id}.jpg" alt="" /></a></div>
                            <div class="shopIdTitle">${product.name}</div>
                            <div>${product.descript}</div>
                            </div>
                        </c:when>
                    </c:choose>
                </c:forEach>
        </c:when>
    </c:choose>
    </div>
    <br class="clearfloat" />
</t:wrapper_manager>