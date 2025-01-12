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
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
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
                <h1 class="h3 mb-2 text-gray-800">구매/주문 이력 조회</h1>
<%--                <p class="mb-4">테이블 설정 관련 참고 사이트:--%>
<%--                    <a target="_blank" href="https://datatables.net">official DataTables documentation</a>--%>
<%--                </p>--%>

                <form id="saveForm" method="post" autocomplete="off" enctype="multipart/form-data">
                    <div class="card shadow mb-4">

                        <div class="card-header py-3">
                            <h5 class="m-0 font-weight-bold text-primary">구매/주문 이력 파일 업로드</h5>
                        </div>

                        <div class="card-header py-3">
                            <div class="filebox" style="float: left">
                                <input id="fileInputBox" class="form-control form-control-sm" type="text"
                                       style="width: 400px; float:left;" placeholder="첨부파일" readonly/>
                                <label for="file">파일 선택</label>
                                <input type="file" id="file" name="file"/>
                            </div>
                            <div>
                                <button type="button" onclick="addFile();" id="insertBtn"
                                        class="btn btn-primary btn-icon-split btn-sm">
                                    <span class="icon text-white-50">+</span>
                                    <span class="text">업로드</span>
                                </button>
                                <button type="button" onclick="removeFile(this);"
                                        class="btn btn-danger btn-icon-split btn-sm">
                                    <span class="icon text-white-50"><i class="fas fa-trash"></i></span>
                                    <span class="text">삭제</span>
                                </button>
                            </div>
                        </div>

                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <caption></caption>
                                    <colgroup>
                                        <col width="10%">
                                        <col width="20%">
                                        <col width="*">
                                        <col width="10%">
                                        <col width="*">
                                        <col width="10%">
                                    </colgroup>
                                    <thead>
                                    <tr style="text-align: center">
                                        <th scope="col">번호</th>
                                        <th scope="col">제목</th>
                                        <th scope="col">내용</th>
                                        <th scope="col">작성일</th>
                                        <th scope="col">작성자</th>
                                        <th scope="col">비고</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <tr>
                                        <td style="text-align: center">1</td>
                                        <td>Customer Support</td>
                                        <td>New York</td>
                                        <td>2011/01/25</td>
                                        <td>test</td>
                                        <td>$112,000</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                    </div>
                </form>

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
    //파일명 input칸에 노출
    $("#file").on('change', function () {
        var fileName = $("#file").val();
        $("#fileInputBox").val(fileName);
    });

    $('#insertBtn').on('click', function () {
        alert('hi')
        // const fileData = $("#file").prop('files')[0];//첨부 파일
        //
        // // formData.append("file", fileData);
        //
        // let formData = $('#saveForm').serialize();
        //
        // $.post('/file/readFile').done(function (data) {
        //     cosole.log(data);
        // }).fail(function (date) {
        //     console.log('ERROR:: ', data);
        // })
    });

    function removeFile() {
        $('#fileInputBox').val('');
        $('#file').val('');
    }
</script>