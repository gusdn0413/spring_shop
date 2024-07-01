<%@page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../util/header.jsp" %>
<html>
<head>
    <title>아이템 등록</title>
</head>
<body>
<div class="container-fluid">
    <div class="main h-100">
        <form action="/item/seller/insert/${categoryId}" method="post">
            <div class="row justify-content-center">
                <div class="col-4">
                    <label for="name">상품 이름</label>
                    <input type="text" name="name" id="name" class="form-control">
                </div>
                <div class="col-4">
                    <label for="price">상품 가격</label>
                    <input type="number" name="price" id="price" class="form-control">
                </div>
                <div class="col-4">
                    <label for="quantity">상품 수량</label>
                    <input type="number" name="quantity" id="quantity" class="form-control">
                </div>
                <div class="row justify-content-center">
                    <div class="col-4 text-center">
                        <input id="btnSubmit" type="submit" class="btn btn-outline-primary" value="아이템 등록">
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>