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
                                            <th scope="col">구입일</th>
                                            <th scope="col">상품명</th>
                                            <th scope="col">상품코드</th>
                                            <th scope="col">재질</th>
                                            <th scope="col">수량</th>
                                            <th scope="col">가격</th>
                                            <th scope="col">구입처</th>
                                        </tr>
                                    </thead>

                                    <tbody id="tableBody">

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
        const fileData = $("#file").prop('files')[0];//첨부 파일

        if (fileData == null) {
            alert('파일을 업로드해주세요.');
        } else {
            // formData.append("file", fileData);
            // let formData = $('#saveForm').serialize();

            let formData = new FormData();
            formData.append("file", fileData);

            $.ajax({
                type: "POST",
                cache: false,
                processData: false,  //false로 선언 시 formData를 string으로 변환하지 않음
                contentType: false,  //false 로 선언 시 content-type 헤더가 multipart/form-data로 전송되게 함
                data: formData,
                url: "/file/readFile",
                success: function (data) {
                    let temp = '';
                    for (let i = 0; i < Object.values(data).length; i++) {
                        temp += '<tr>';
                        temp += '<td style="text-align: center;" ">' + (i+1) + '</td>';
                        // 열값 넣어주기
                        for (let j = 0; j < Object.values(data)[i].length; j++){
                            temp += '   <td style="text-align: center;">'+Object.values(data)[i][j]+'</td>';
                        }

                        temp += '</tr>';
                    }

                    $('#tableBody').html(temp);
                },
                error: function (data) {
                    alert('!!!!업로드 실패!!!!');
                }
            });
        }
    });

    function removeFile() {
        $('#fileInputBox').val('');
        $('#file').val('');
    }
</script>