<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/home">
        <div class="sidebar-brand-icon rotate-n-15">
            K
        </div>
        <div class="sidebar-brand-text mx-3">KwanKyu Admin</div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <li class="nav-item">
        <a class="nav-link" href="/home">
<%--            <i class="fas fa-fw fa-tachometer-alt"></i>--%>
            H<span>&nbsp;&nbsp;&nbsp;HOME</span>
        </a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

<%--    <!-- Nav Item - Utilities Collapse Menu -->--%>
<%--    <li class="nav-item">--%>
<%--        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"--%>
<%--           aria-expanded="true" aria-controls="collapseUtilities">--%>
<%--            <i class="fas fa-fw fa-wrench"></i>--%>
<%--            <span>Utilities</span>--%>
<%--        </a>--%>
<%--        <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"--%>
<%--             data-parent="#accordionSidebar">--%>
<%--            <div class="bg-white py-2 collapse-inner rounded">--%>
<%--                <h6 class="collapse-header">Custom Utilities:</h6>--%>
<%--                <a class="collapse-item" href="utilities-color.jsp">Colors</a>--%>
<%--                <a class="collapse-item" href="utilities-border.jsp">Borders</a>--%>
<%--                <a class="collapse-item" href="utilities-animation.jsp">Animations</a>--%>
<%--                <a class="collapse-item" href="utilities-other.jsp">Other</a>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </li>--%>

    <!-- Heading -->
    <div class="sidebar-heading">
        Charts/Graphs
    </div>

    <!-- Nav Item - Tables -->
    <li class="nav-item">
        <a class="nav-link" href="/tables">
            <i class="fas fa-fw fa-table"></i>
            <span>구매/주문 조회</span>
        </a>
    </li>

    <!-- Nav Item - Charts -->
    <li class="nav-item">
        <a class="nav-link" href="/charts">
            <i class="fas fa-fw fa-chart-area"></i>
            <span>주문별 차트</span>
        </a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        Search
    </div>

    <!-- Nav Item - Tables -->
    <li class="nav-item">
        <a class="nav-link" href="/tables">
            <i class="fas fa-fw fa-table"></i>
            <span>스토어 한눈에보기</span>
        </a>
    </li>

    <!-- Nav Item - Charts -->
    <li class="nav-item">
        <a class="nav-link" href="/charts">
            <i class="fas fa-fw fa-chart-area"></i>
            <span>지역별 스토어 리스트</span>
        </a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        Interface
    </div>

    <!-- Nav Item - Pages Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
           aria-expanded="true" aria-controls="collapseTwo">
            <i class="fas fa-fw fa-cog"></i>
            <span>페이지 설정</span>
        </a>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <a class="collapse-item" href="/buttons">버튼</a>
                <a class="collapse-item" href="/cards">카드섹션</a>
            </div>
        </div>
    </li>

    <!-- Nav Item - Pages Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
           aria-expanded="true" aria-controls="collapsePages">
            <i class="fas fa-fw fa-folder"></i>
            <span>페이지 관리</span>
        </a>
        <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Login Screens:</h6>
                <a class="collapse-item" href="/login">로그인</a>
                <a class="collapse-item" href="/register">회원가입</a>
                <a class="collapse-item" href="/forgot-password">패스워드 찾기</a>
                <div class="collapse-divider"></div>
                <h6 class="collapse-header">Other Pages:</h6>
                <a class="collapse-item" href="/errorPage">404 Page</a>
                <a class="collapse-item" href="blank.jsp">Blank Page</a>
            </div>
        </div>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>
</ul>
