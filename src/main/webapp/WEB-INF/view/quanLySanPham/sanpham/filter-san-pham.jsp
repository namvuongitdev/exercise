<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="col l-3">
    <input type="text" class="form-control" name="search" placeholder="tìm kiến" value="${filter.search}">
</div>
<div class="col l-3">
    <select name="danhMuc" class="form-select">
        <option value="">Tất cả danh mục</option>
        <c:forEach items="${listDanhMuc}" var="danhMuc">
            <option value="${danhMuc.id}" ${filter.danhMuc == danhMuc.id ? 'selected' : ''}>
                    ${danhMuc.ten}
            </option>
        </c:forEach>
    </select>
</div>
<div class="col l-3">
    <select name="chatLieu" class="form-select">
        <option value="">Tất cả chất liệu</option>
        <c:forEach items="${listChatLieu}" var="chatLieu">
            <option value="${chatLieu.id}" ${filter.chatLieu == chatLieu.id ? 'selected' : ''}>
                    ${chatLieu.ten}
            </option>
        </c:forEach>
    </select>
</div>
<div class="col l-3">
    <select name="kieuDang" class="form-select">
        <option value="">Tất cả kiểu dáng</option>
        <c:forEach items="${listFormDang}" var="kieuDang" >
            <option value="${kieuDang.id}" ${filter.kieuDang == kieuDang.id ? 'selected' : ''}>
                    ${kieuDang.ten}
            </option>
        </c:forEach>
    </select>
</div>
<div class="col l-3">
    <select name="trangThai" class="form-select">
        <option value="">Tất cả trang thái</option>
        <option value="0" ${filter.trangThai == 0 ? 'selected' : ''}>
            Kinh doanh
        </option>
        <option value="1" ${filter.trangThai == 1 ? 'selected' : ''}>
           Ngừng Kinh doanh
        </option>
    </select>
</div>
<div class="col l-3">
    <select name="sapXep" class="form-select">
        <option value="">Tất cả</option>
        <option value="ngayTao" ${filter.sapXep == 'ngayTao' ? 'selected' : ''}>Mới nhất</option>
        <option value="price-asc" ${filter.sapXep == 'price-asc' ? 'selected' : ''}>
            Thứ tự theo giá: thấp đến cao
        </option>
        <option value="price-desc" ${filter.sapXep == 'price-desc' ? 'selected' : ''}>
            Thứ tự theo giá: cao xuống thấp
        </option>
    </select>
</div>
<div class="col l-3">
    <button class="btn btn-primary">Tìm kiếm</button>
</div>