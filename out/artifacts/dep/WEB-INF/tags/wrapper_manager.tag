<%@tag description="Wrapper Tag" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Internet Dreams</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/screen.css" type="text/css" media="screen" title="default" />
    <!--[if IE]>
    <link rel="stylesheet" media="all" type="text/css" href="${pageContext.request.contextPath}/css/pro_dropline_ie.css" />
    <![endif]-->

    <!--  jquery core -->
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-1.4.1.min.js" type="text/javascript"></script>

    <!--  checkbox styling script -->
    <script src="${pageContext.request.contextPath}/js/jquery/ui.core.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/jquery/ui.checkbox.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/jquery/jquery.bind.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function(){
            $('input').checkBox();
            $('#toggle-all').click(function(){
                $('#toggle-all').toggleClass('toggle-checked');
                $('#mainform input[type=checkbox]').checkBox('toggle');
                return false;
            });
        });
    </script>

    <![if !IE 7]>

    <!--  styled select box script version 1 -->
    <script src="${pageContext.request.contextPath}/js/jquery/jquery.selectbox-0.5.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('.styledselect').selectbox({ inputClass: "selectbox_styled" });
        });
    </script>


    <![endif]>

    <!--  styled select box script version 2 -->
    <script src="${pageContext.request.contextPath}/js/jquery/jquery.selectbox-0.5_style_2.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('.styledselect_form_1').selectbox({ inputClass: "styledselect_form_1" });
            $('.styledselect_form_2').selectbox({ inputClass: "styledselect_form_2" });
        });
    </script>

    <!--  styled select box script version 3 -->
    <script src="${pageContext.request.contextPath}/js/jquery/jquery.selectbox-0.5_style_2.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('.styledselect_pages').selectbox({ inputClass: "styledselect_pages" });
        });
    </script>

    <!--  styled file upload script -->
    <script src="${pageContext.request.contextPath}/js/jquery/jquery.filestyle.js" type="text/javascript"></script>
    <script type="text/javascript" charset="utf-8">
        $(function() {
            $("input.file_1").filestyle({
                image: "images/forms/choose-file.gif",
                imageheight : 21,
                imagewidth : 78,
                width : 310
            });
        });
    </script>

    <!-- Custom jquery scripts -->
    <script src="${pageContext.request.contextPath}/js/jquery/custom_jquery.js" type="text/javascript"></script>

    <!-- Tooltips -->
    <script src="${pageContext.request.contextPath}/js/jquery/jquery.tooltip.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/jquery/jquery.dimensions.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function() {
            $('a.info-tooltip ').tooltip({
                track: true,
                delay: 0,
                fixPNG: true,
                showURL: false,
                showBody: " - ",
                top: -35,
                left: 5
            });
        });
    </script>


    <!--  date picker script -->
    <link rel="stylesheet" href="css/datePicker.css" type="text/css" />
    <script src="${pageContext.request.contextPath}/js/jquery/date.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/jquery/jquery.datePicker.js" type="text/javascript"></script>
    <script type="text/javascript" charset="utf-8">
        $(function()
        {

// initialise the "Select date" link
            $('#date-pick')
                    .datePicker(
                    // associate the link with a date picker
                    {
                        createButton:false,
                        startDate:'01/01/2005',
                        endDate:'31/12/2020'
                    }
            ).bind(
                    // when the link is clicked display the date picker
                    'click',
                    function()
                    {
                        updateSelects($(this).dpGetSelected()[0]);
                        $(this).dpDisplay();
                        return false;
                    }
            ).bind(
                    // when a date is selected update the SELECTs
                    'dateSelected',
                    function(e, selectedDate, $td, state)
                    {
                        updateSelects(selectedDate);
                    }
            ).bind(
                    'dpClosed',
                    function(e, selected)
                    {
                        updateSelects(selected[0]);
                    }
            );

            var updateSelects = function (selectedDate)
            {
                var selectedDate = new Date(selectedDate);
                $('#d option[value=' + selectedDate.getDate() + ']').attr('selected', 'selected');
                $('#m option[value=' + (selectedDate.getMonth()+1) + ']').attr('selected', 'selected');
                $('#y option[value=' + (selectedDate.getFullYear()) + ']').attr('selected', 'selected');
            }
// listen for when the selects are changed and update the picker
            $('#d, #m, #y')
                    .bind(
                    'change',
                    function()
                    {
                        var d = new Date(
                                $('#y').val(),
                                $('#m').val()-1,
                                $('#d').val()
                        );
                        $('#date-pick').dpSetSelected(d.asString());
                    }
            );

// default the position of the selects to today
            var today = new Date();
            updateSelects(today.getTime());

// and update the datePicker to reflect it...
            $('#d').trigger('change');
        });
    </script>

    <!-- MUST BE THE LAST SCRIPT IN <HEAD></HEAD></HEAD> png fix -->
    <script src="${pageContext.request.contextPath}/js/jquery/jquery.pngFix.pack.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $(document).pngFix( );
        });
    </script>
</head>
<body>
<!-- Start: page-top-outer -->
<div id="page-top-outer">

    <!-- Start: page-top -->
    <div id="page-top">

        <!-- start logo -->
        <div id="logo">
            <a href=""><img src="${pageContext.request.contextPath}/images/shared/logo.png" width="156" height="40" alt="" /></a>
        </div>
        <!-- end logo -->

    </div>
    <!-- End: page-top -->

</div>
<!-- End: page-top-outer -->

<div class="clear">&nbsp;</div>

<!--  start nav-outer-repeat................................................................................................. START -->
<div class="nav-outer-repeat">
    <!--  start nav-outer -->
    <div class="nav-outer">

        <!-- start nav-right -->
        <div id="nav-right">

            <div class="nav-divider">&nbsp;</div>
            <div class="showhide-account"><img src="${pageContext.request.contextPath}/images/shared/nav/nav_myaccount.gif" width="93" height="14" alt="" /></div>
            <div class="nav-divider">&nbsp;</div>
            <a href="" id="logout"><img src="${pageContext.request.contextPath}/images/shared/nav/nav_logout.gif" width="64" height="14" alt="" /></a>
            <div class="clear">&nbsp;</div>

        </div>
        <!-- end nav-right -->


        <!--  start nav -->
        <div class="nav">
            <div class="table">

                <ul class="select"><li><a href="${pageContext.request.contextPath}/?module=gallery&action=list"><b>shop</b><!--[if IE 7]><!--></a><!--<![endif]-->
                    <!--[if lte IE 6]><table><tr><td><![endif]-->
                    <div class="select_sub">
                        <ul class="sub">
                            <li><a href="${pageContext.request.contextPath}/?module=gallery&action=list">shop</a></li>
                        </ul>
                    </div>
                    <!--[if lte IE 6]></td></tr></table></a><![endif]-->
                </li>
                </ul>

                <div class="nav-divider">&nbsp;</div>

                <ul class="select"><li><a href="${pageContext.request.contextPath}/?module=manager&action=settings"><b>settings</b><!--[if IE 7]><!--></a><!--<![endif]-->
                    <!--[if lte IE 6]><table><tr><td><![endif]-->
                    <div class="select_sub">
                        <ul class="sub">
                            <li><a href="${pageContext.request.contextPath}/?module=manager&action=settings">settings</a></li>
                        </ul>
                    </div>
                    <!--[if lte IE 6]></td></tr></table></a><![endif]-->
                </li>
                </ul>

                <div class="nav-divider">&nbsp;</div>

                <ul class="current"><li><a href="#nogo"><b>Products</b><!--[if IE 7]><!--></a><!--<![endif]-->
                    <!--[if lte IE 6]><table><tr><td><![endif]-->
                    <div class="select_sub show">
                        <ul class="sub">
                            <li><a href="#nogo">View all products</a></li>
                            <li class="sub_show"><a href="#nogo">Add product</a></li>
                            <li><a href="#nogo">Delete products</a></li>
                        </ul>
                    </div>
                    <!--[if lte IE 6]></td></tr></table></a><![endif]-->
                </li>
                </ul>
                <div class="clear"></div>
            </div>
            <div class="clear"></div>
        </div>
        <!--  start nav -->

    </div>
    <div class="clear"></div>
    <!--  start nav-outer -->
</div>
<!--  start nav-outer-repeat................................................... END -->

<div class="clear"></div>

<!-- start content-outer ........................................................................................................................START -->
<div id="content-outer">
<!-- start content -->
<div id="content">

<!--  start page-heading -->
<div id="page-heading">
    <h1>Add product</h1>
</div>
<!-- end page-heading -->

            <c:choose>
                <c:when test="${k_exception != null && k_exception.isEmpty()}"><p>k_exception:<jsp:text>${k_exception}</jsp:text></p></c:when>
                <c:when test="${exception != null && exception.isEmpty()}"><p>exception:<jsp:text>${exception}</jsp:text></p></c:when>
            </c:choose>

            <jsp:doBody/>

<div class="clear">&nbsp;</div>

</div>
<!--  end content -->
<div class="clear">&nbsp;</div>
</div>
<!--  end content-outer........................................................END -->

<div class="clear">&nbsp;</div>

<!-- start footer -->
<div id="footer">
    <!--  start footer-left -->
    <div id="footer-left">

        Admin Skin &copy; Copyright Internet Dreams Ltd. <span id="spanYear"></span> <a href="">www.netdreams.co.uk</a>. All rights reserved.</div>
    <!--  end footer-left -->
    <div class="clear">&nbsp;</div>
</div>
<!-- end footer -->

</body>

