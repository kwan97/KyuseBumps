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
                <h1 class="h3 mb-2 text-gray-800">상품 시세 조회</h1>
                <div id="test">

                </div>
                <%--                <p class="mb-4">테이블 설정 관련 참고 사이트:--%>
                <%--                    <a target="_blank" href="https://datatables.net">official DataTables documentation</a>--%>
                <%--                </p>--%>

                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h5 class="m-0 font-weight-bold text-primary">상품 시세 조회</h5>
                    </div>

                    <div class="card-header py-3">
                        <form id="crawlingForm">
                            <input type="hidden" id="category" name="category"/>
                            <div>
                                <button type="button" id="Kream1" onclick="categoryKream(1)" class="btn btn-primary btn-icon-split btn-sm">
                                    <span class="icon text-white-50">K</span>
                                    <span class="text">반팔</span>
                                </button>

                                <button type="button" id="Kream2" onclick="categoryKream(2)" class="btn btn-primary btn-icon-split btn-sm">
                                    <span class="icon text-white-50">K</span>
                                    <span class="text">후드집업</span>
                                </button>
                            </div>

                            <div style="margin-top: 10px;">
                                <button type="button" id="Naver1" onclick="categoryNaver(1)" class="btn btn-primary btn-icon-split btn-sm">
                                    <span class="icon text-white-50">N</span>
                                    <span class="text">반팔</span>
                                </button>

                                <button type="button" id="Naver2" onclick="categoryNaver(2)" class="btn btn-primary btn-icon-split btn-sm">
                                    <span class="icon text-white-50">N</span>
                                    <span class="text">후드집업</span>
                                </button>
                            </div>
                        </form>
                    </div>

                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <caption></caption>
                                <colgroup>
                                    <col width="7%">
                                    <col width="*%">
                                    <col width="*">
                                    <col width="13%">
                                </colgroup>
                                <thead>
                                <tr style="text-align: center">
                                    <th scope="col">번호</th>
                                    <th scope="col">상품이미지</th>
                                    <th scope="col">상품명</th>
                                    <th scope="col">가격</th>
                                </tr>
                                </thead>

                                <tbody id="tableBody">
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

<script>
    $(document).ready(function () {
        crawlingProductList()
    })

    //Kream용
    function categoryKream(key) {
        let keyword = $('#Kream'+key).find('span:eq(1)').text();
        $('#category').val(keyword);
        crawlingProductList();
    }

    //네이버 스마트스토어용
    function categoryNaver(key) {
        let keyword = $('#Naver'+key).find('span:eq(1)').text();
        $('#category').val(keyword);
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