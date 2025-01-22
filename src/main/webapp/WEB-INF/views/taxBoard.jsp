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
                <h1 class="h3 mb-2 text-gray-800">관세/판매 수수료 정보</h1>
                <%--<p class="mb-4">테이블 설정 관련 참고 사이트:--%>
                <%--    <a target="_blank" href="https://datatables.net">official DataTables documentation</a>--%>
                <%--</p>--%>

                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h5 class="m-0 font-weight-bold text-primary">관부과세/판매 수수료</h5>
                    </div>

                    <div class="col-lg-6 mb-4" style="max-width:100%">
                        <div class="card shadow mb-4" style="margin-top: 15px;">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">관부가세</h6>
                            </div>
                            <div class="card-body">
                                <div class="text-center">
                                    <p>
                                        <strong>[일본 의류 수입 관부가세 계산법]</strong><br>
                                        ( 물품가격 X 13%(관세) ) X 10%(부가세) = <strong>총 납부액</strong>
                                        <br><br>
                                    </p>
                                    <div class="table-responsive">
                                        <table class="table table-bordered">
                                            <caption></caption>
                                            <colgroup>
                                                <col width="*">
                                                <col width="*">
                                                <col width="*">
                                            </colgroup>
                                            <thead>
                                                <th>품목</th>
                                                <th>관세율</th>
                                                <th>부가세율</th>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>의류, 신발</td>
                                                    <td>13%</td>
                                                    <td>10%</td>
                                                </tr>
                                                <tr>
                                                    <td>가방, 모자, 시계, 안경</td>
                                                    <td>8%</td>
                                                    <td>10%</td>
                                                </tr>
                                                <tr>
                                                    <td>화장품, 향수</td>
                                                    <td>6.5%</td>
                                                    <td>10%</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">스마트스토어 판매 수수료</h6>
                            </div>
                            <div class="card-body">
                                <div class="text-center">
                                    <div class="table-responsive">
<%--                                    <img class="img-fluid px-3 px-sm-4 mt-3 mb-4" style="width: 25rem;"--%>
<%--                                         src="img/undraw_posting_photo.svg" alt="...">--%>
                                        <table class="table table-bordered">
                                            <caption></caption>
                                            <colgroup>
                                                <col width="*">
                                                <col width="*">
                                                <col width="*">
                                            </colgroup>
                                            <thead>
                                                <c:forEach items="${tdList}" var="items" begin="0" end="2">
                                                    <th>${items}</th>
                                                </c:forEach>
                                            </thead>
                                            <tbody id="mainTr">
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <p>※ VAT 포함 수수료입니다.</p>
                                <a target="_blank" rel="nofollow" href="https://help.sell.smartstore.naver.com/faq/content.help?faqId=3558">스마트스토어 이용 안내 페이지 →</a>
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
    </div>
</body>
</html>

<script>
    $(function () {
        makeTable()
    });

    function makeTable() {
        var arr = new Array();
        <c:forEach items="${tdList}" var="items">
            arr.push('${items}');
        </c:forEach>

        var html = '';
        for (var i = 3; i < ${fn:length(tdList)}; i++){
            if (i % 3 == 0){
                html += '</tr><tr><td>'+ arr[i] + '</td>';
            } else {
                html += '<td>' +arr[i]+ '</td>';
            }
        }

        $('#mainTr').html(html);
    }
</script>