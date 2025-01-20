<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>KwanKyu-Admin</title>

    <!-- Custom fonts for this template -->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
    <!-- Custom styles for this page -->
    <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    <%--  jquery  --%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>
    <!-- Page level plugins -->
    <script src="vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>
    <!-- Page level custom scripts -->
    <script src="js/demo/datatables-demo.js"></script>

    <style>
        .filebox input[type="file"] {
            position: absolute;
            width: 0;
            height: 0;
            padding: 0;
            overflow: hidden;
            border: 0;
        }

        .filebox label {
            display: inline-block;
            padding: 5px 10px;
            color: #fff;
            vertical-align: middle;
            background-color: #999999;
            cursor: pointer;
            height: 30px;
            margin-right: 20px;
            margin-left: 5px;
            font-size: .875rem;
            line-height: 1.5;
            border-radius: 0.2rem;
        }
    </style>
</head>

<body id="page-top">
<div id="wrapper">
    <jsp:include page="/WEB-INF/views/include/sideBar.jsp"/>
    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
            <jsp:include page="/WEB-INF/views/include/navMenu.jsp"/>
            <div class="container-fluid">
                <h1 class="h3 mb-2 text-gray-800">오늘의 환율</h1>
                <%--<p class="mb-4">테이블 설정 관련 참고 사이트:--%>
                <%--    <a target="_blank" href="https://datatables.net">official DataTables documentation</a>--%>
                <%--</p>--%>

                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h5 class="m-0 font-weight-bold text-primary">오늘의 환율</h5>
                    </div>

                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <caption></caption>
                                <colgroup>
                                    <col width="20%">
                                    <col width="*">
                                    <col width="*">
                                </colgroup>
                                <thead>
                                <tr style="text-align: center">
                                    <th scope="col">국가</th>
                                    <th scope="col">환율</th>
                                    <th scope="col">전일대비</th>
                                </tr>
                                </thead>

                                <tbody>
                                    <c:forEach items="${exchangeInfoList}" var="item" varStatus="status">
                                        <c:choose>
                                            <c:when test="${item.country eq '일본'}">
                                                <tr>
                                                    <td style="text-align: center;">${item.country}</td>
                                                    <td style="text-align: center;">${item.exchangeRate} 엔</td>
                                                    <td style="text-align: center;">${item.discribeRate}</td>
                                                </tr>
                                            </c:when>
                                            <c:when test="${item.country eq '미국'}">
                                                <tr>
                                                    <td style="text-align: center;">${item.country}</td>
                                                    <td style="text-align: center;">${item.exchangeRate} 달러</td>
                                                    <td style="text-align: center;">${item.discribeRate}</td>
                                                </tr>
                                            </c:when>
                                            <c:when test="${item.country eq '유럽연합'}">
                                                <tr>
                                                    <td style="text-align: center;">${item.country}(EU)</td>
                                                    <td style="text-align: center;">${item.exchangeRate} 유로</td>
                                                    <td style="text-align: center;">${item.discribeRate}</td>
                                                </tr>
                                            </c:when>
                                            <c:when test="${item.country eq '중국'}">
                                                <tr>
                                                    <td style="text-align: center;">${item.country}</td>
                                                    <td style="text-align: center;">${item.exchangeRate} 위안</td>
                                                    <td style="text-align: center;">${item.discribeRate}</td>
                                                </tr>
                                            </c:when>
                                        </c:choose>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <!--Footer-->
        <jsp:include page="/WEB-INF/views/include/footer.jsp"/>
        <!-- End of Footer -->
    </div>
</div>
<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>
</body>
</html>