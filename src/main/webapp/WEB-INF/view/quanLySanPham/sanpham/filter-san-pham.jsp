<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
        <div class="col l-3">
            <input type="text" class="form-control" name="tenSanPham" placeholder="tìm kiến" required>
        </div>
        <div class="col l-3">
            <select name="danhMuc" class="form-select">
                <option>Tất cả danh mục</option>
                <c:forEach items="${listDanhMuc}" var="danhMuc">
                    <option value="${danhMuc.id}">
                            ${danhMuc.ten}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="col l-3">
            <select name="chatLieu" class="form-select">
                <option>Tất cả chất liệu</option>
                <c:forEach items="${listChatLieu}" var="chatLieu">
                    <option value="${chatLieu.id}">
                            ${chatLieu.ten}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="col l-3">
            <select name="kieuDang" class="form-select">
                <option>Tất cả kiểu dáng</option>
                <c:forEach items="${listFormDang}" var="kieuDang">
                    <option value="${kieuDang.id}">
                            ${kieuDang.ten}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="col l-3">
            <button class="btn btn-primary">Tìm kiếm</button>
        </div>