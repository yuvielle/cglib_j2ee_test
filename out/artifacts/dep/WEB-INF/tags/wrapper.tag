<%@tag description="Wrapper Tag" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>free css template 184 - Jewelry Store</title>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css" />
    <!--[if IE 5]>
    <style type="text/css">
        /* place css box model fixes for IE 5* in this conditional comment */
        #sidebar1 { width: 200px; }
    </style>
    <![endif]--><!--[if IE]>
    <style type="text/css">
        /* place css fixes for all versions of IE in this conditional comment */
        #sidebar1 { padding-top: 30px; }
        #mainContent { zoom: 1; }
        /* the above proprietary zoom property gives IE the hasLayout it needs to avoid several bugs */
    </style>
    <![endif]--></head>

<body>
    <div id="container">
    <!-- begin #header -->
        <div id="header">
            <div>
                <div class="logo">Test <span>Store</span><br /></div>
            </div>
            <br class="clearfloat" />
            <div id="navcontainer">
                <ul id="navlist">
                    <li id="active"><a href="#" id="current">Home page </a></li>
                    <li><a href="#">Catalog</a></li>
                    <li><a href="${pageContext.request.contextPath}/?module=manager&action=list">Management</a></li>
                </ul>
            </div>
            <div class="headerPic">
                <div class="headerDetails"><a href="#">DETAILS HERE</a></div>
            </div>
        </div>
        <div id="sidebar1">
            <h2>Jewelry Catalog</h2>
            <ul id="sidebarlist">
                <li><a href="#">test item 1</a></li>
                <li><a href="#">test item 2n</a></li>
                <li><a href="#">test item 3</a></li>
                <li id="sidebaractive"><a href="#">test item 4</a></li>
            </ul>
        </div>
        <c:choose>
            <c:when test="${k_exception != null && k_exception.isEmpty()}"><p>k_exception:<jsp:text>${k_exception}</jsp:text></p></c:when>
            <c:when test="${exception != null && exception.isEmpty()}"><p>exception:<jsp:text>${exception}</jsp:text></p></c:when>
        </c:choose>

        <jsp:doBody/>
        <br>
        </div>
        <!-- end #container -->
        <!-- begin #footer -->
        <div id="footer">
            <div class="footerBody">
                <div class="copyright">
                    Terms of Use |
                    <a title="This page validates as XHTML 1.0 Strict" href="http://validator.w3.org/check/referer" class="footerLink"><abbr title="eXtensible HyperText Markup Language">XHTML</abbr></a> |
                    <a title="This page validates as CSS" href="http://jigsaw.w3.org/css-validator/check/referer" class="footerLink"><abbr title="Cascading Style Sheets">CSS</abbr></a>
                    <br />Copyright &copy; 2014 Test Store. Designed by
                    <a href="http://www.freecsstemplate.net" title="Free Css Templates">Free Css Templates</a>
                    | Best <a href="http://www.templatemonster.com">Website Templates</a> From TemplateMonster
                    <div style="text-align:left;">
                        Edit <a href="http://www.fbpager.com">Facebook Templates</a> with Fbpager | The biggest collection of
                        <a href="http://www.facebookpagetemplates.com">Free Facebook Templates</a> on the net
                    </div>
                </div>
                <div class="cards"><img src="${pageContext.request.contextPath}/images/cards.png" alt="" /></div>
            </div>
        </div>
        <!-- end #footer -->
</body>
</html>
