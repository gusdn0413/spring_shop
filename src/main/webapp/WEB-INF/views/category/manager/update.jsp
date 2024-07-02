<%@page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../util/header.jsp" %>
<html>
<head>
    <title>상품 장바구니 추가</title>
</head>
<body>
<div class="container-fluid">
    <div class="main h-100">
        <form action="/category/manager/update/${categoryDTO.id}" method="post">
            <div class="row justify-content-center">
                <div class="col-4">
                    <label for="name">카테고리 이름</label>
                    <input type="text" name="name" id="name" class="form-control" value="${categoryDTO.name}" required>
                </div>
                <div class="row justify-content-center">
                    <div class="col-4 text-center">
                        <input id="btnSubmit" type="submit" class="btn btn-outline-primary" value="카테고리 수정">
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>