<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>

</head>
<style>
    table {
        border-collapse: collapse;
        width: 100%;
    }

    th, td {
        padding: 8px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    tr:hover {
        background-color: #f5f5f5;
    }
</style>
<body>
<%--navbar--%>
<jsp:include page="../../sidebar/navbar.jsp"/>
<%--sidebar--%>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <jsp:include page="../../sidebar/sidebar.jsp"/>
        <div class="col py-3">
            <%--            code giao diện màu sắc --%>
            <div class="container">
                <nav class="navbar navbar-light" style="height:30px">
                    <div class="container-fluid">
                        <a class="navbar-brand" href="#"><font color="black">Danh Sách</font></a>
                    </div>
                </nav>
                <br>
<%--                filter sản phẩm--%>
                <div class="row">
                    <form action="/san-pham/filter">
                        <div class="row">
                            <jsp:include page="filter-san-pham.jsp"></jsp:include>
                        </div>
                    </form>

                  <div class="col l-3">
                      <a href="/san-pham/new" class="btn btn-primary">Tạo Mới</a>
                  </div>
                </div>
                <hr>
                <table>
                    <thead>
                    <tr>
                        <th scope="col">STT</th>
                        <th scope="col">Ảnh</th>
                        <th scope="col">Tên Sản Phẩm</th>
                        <th scope="col">Giá</th>
                        <th scope="col">Ngày Tạo</th>
                        <th scope="col">Trang Thái</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listSanPham.content}" var="sanPham" varStatus="i">
                        <tr onclick="window.location.href='/san-pham/hien-thi/${sanPham.id}'">
                            <th scope="row">${i.index+page}</th>
                            <td>
                                <img src="/image/${sanPham.img}">
                            </td>
                            <td>${sanPham.ten}</td>
                            <td><fmt:formatNumber pattern="#,###" value="${sanPham.giaBan}">

                            </fmt:formatNumber></td>
                            <td>
                                <fmt:formatDate value="${sanPham.ngayTao}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                            <td>
                                <button style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;"
                                        type="button"
                                        class="${sanPham.trangThai == 0 ? 'btn btn-success' : 'btn btn-danger'}">
                                        ${sanPham.trangThai == 0 ? 'Kinh doanh' : 'Ngừng kinh doanh'}</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <%--                  phân trang--%>
                <div class="container-fluid mt-5">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center">
                            <li class="page-item ${pageNo<=1?"disabled":""}"><a class="page-link"
                                                                                href="/san-pham/hien-thi?page=${pageNo-1}">Previous</a>
                            </li
                            <c:forEach begin="1" end="${listSanPham.getTotalPages()}" var="i">
                                <li class="page-item"><a class="page-link ${i == pageNo ? 'active ' : ''}"
                                                         href="/san-pham/hien-thi?page=${i}">${i}</a></li>
                            </c:forEach>
                            <li class="page-item ${pageNo>=listSanPham.getTotalPages()?"disabled":""}"><a
                                    class="page-link"
                                    href="/san-pham/hien-thi?page=${pageNo+1}">Next</a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
</script>
</body>
</html>