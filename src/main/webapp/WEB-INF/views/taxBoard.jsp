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
                        <h5 class="m-0 font-weight-bold text-primary">관세/판매 수수료 정보</h5>
                    </div>

                    <div class="col-lg-6 mb-4" style="max-width:100%">
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">관세 정보</h6>
                            </div>
                            <div class="card-body">
                                <div class="text-center">
<%--                                    <img class="img-fluid px-3 px-sm-4 mt-3 mb-4" style="width: 25rem;"--%>
<%--                                         src="img/undraw_posting_photo.svg" alt="...">--%>
                                </div>
                                <p>
                                    >>일본 의류 수입 관부가세 계산법<br>
                                    ( 물품가격 X 관세(13%) ) X 부가세(10%) = 총 납부액
                                    <br><br>
                                    >>
                                </p>
                                <a target="_blank" rel="nofollow" href="https://undraw.co/">Browse Illustrations on
                                    unDraw →</a>
                            </div>
                        </div>

                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">판매 수수료</h6>
                            </div>
                            <div class="card-body">
                                <div class="text-center">
<%--                                    <img class="img-fluid px-3 px-sm-4 mt-3 mb-4" style="width: 25rem;"--%>
<%--                                         src="img/undraw_posting_photo.svg" alt="...">--%>
                                </div>
                                <p></p>
                                    ${taxList}
                                <a target="_blank" rel="nofollow" href="https://undraw.co/">Browse Illustrations on
                                    unDraw →</a>
                            </div>
                        </div>

                    </div>
                </div>

                <%--                <div class="card shadow mb-4">--%>
                <%--                    <div class="card-header py-3">--%>
                <%--                        <h5 class="m-0 font-weight-bold text-primary">판매 수수료 정보</h5>--%>
                <%--                    </div>--%>

                <%--                    <div class="card-header py-3">--%>
                <%--                        <form id="sellForm">--%>
                <%--                            <div class="input-group" style="width: 500px;">--%>
                <%--                                <input type="text" id="searchText" name="searchText" class="form-control bg-light border-0 small" placeholder="Search for..."--%>
                <%--                                       aria-label="Search" aria-describedby="basic-addon2">--%>
                <%--                                <div class="input-group-append">--%>
                <%--                                    <button class="btn btn-primary" type="button" onclick="searchProduct()">--%>
                <%--                                        <i class="fas fa-search fa-sm"></i>--%>
                <%--                                    </button>--%>
                <%--                                </div>--%>
                <%--                            </div>--%>
                <%--                        </form>--%>
                <%--                    </div>--%>
                <%----%>
                <%--                    <div class="card-body">--%>
                <%--                        <div class="table-responsive">--%>
                <%--                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">--%>
                <%--                                <caption></caption>--%>
                <%--                                <colgroup>--%>
                <%--                                    <col width="7%">--%>
                <%--                                    <col width="*%">--%>
                <%--                                    <col width="*">--%>
                <%--                                    <col width="13%">--%>
                <%--                                </colgroup>--%>
                <%--                                <thead>--%>
                <%--                                <tr style="text-align: center">--%>
                <%--                                    <th scope="col">번호</th>--%>
                <%--                                    <th scope="col">상품이미지</th>--%>
                <%--                                    <th scope="col">상품명</th>--%>
                <%--                                    <th scope="col">가격</th>--%>
                <%--                                </tr>--%>
                <%--                                </thead>--%>

                <%--                                <tbody id="tableBody">--%>
                <%--                                </tbody>--%>
                <%--                            </table>--%>
                <%--                        </div>--%>
                <%--                    </div>--%>
                <%----%>
                <%--                </div>--%>
                <%--            </div>--%>
                <%--        </div>--%>
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

<script>
    function searchProduct() {
        let keyword = $('#searchText').val();
        crawlingProductList();
    }

    function crawlingProductList() {
        $.ajax({
            type: "POST",
            cache: false,
            // processData: false,  //false로 선언 시 formData를 string으로 변환하지 않음
            // contentType: false,  //false 로 선언 시 content-type 헤더가 multipart/form-data로 전송되게 함
            data: $('#crawlingForm').serialize(),
            url: "/crawling/getCrawlingKream",
            success: function (data) {
                if (data.success) {
                    let temp = '';

                    for (let i = 0; i < Object.values(data.resultMap.crawlingModelList).length; i++) {
                        temp += '<tr>';
                        temp += '   <td style="text-align: center;" ">' + (i + 1) + '</td>';
                        temp += '   <td><img src="' + Object.values(data.resultMap.crawlingModelList)[i].imageUrl + '"/></td>';
                        temp += '   <td style="text-align: center;"><a href="'
                            + Object.values(data.resultMap.crawlingModelList)[i].accessUrl + '" target="_blank">'
                            + Object.values(data.resultMap.crawlingModelList)[i].title + '</a></td>';
                        temp += '   <td style="text-align: center;">' + Object.values(data.resultMap.crawlingModelList)[i].price + '</td>';
                        temp += '</tr>'
                    }
                    $('#tableBody').html(temp);

                    let html = '';
                    html += '<img src="' + Object.values(data.resultMap.crawlingModelList)[0].imageUrl + '">'
                    $('#test').html(html);
                } else {
                    alert(data.message);
                    return;
                }
            }
        })
    }
</script>