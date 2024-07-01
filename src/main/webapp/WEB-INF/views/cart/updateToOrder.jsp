<%@page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../util/header.jsp" %>
<html>
<head>
    <title>장바구니 주문</title>
</head>
<body>

<div class="container-fluid">
    <div class="main h-100">
        <form action="/cart/updateToOrder/${cartDTO.id}" method="post">
            <div class="row justify-content-center">
                <div class="col-4">
                    <label for="password">비밀번호 입력</label>
                    <input type="password" name="password" id="password" class="form-control" required>
                </div>
                <div class="row justify-content-center">
                    <div class="col-4 text-center">
                        <input id="btnSubmit" type="submit" class="btn btn-outline-primary" value="장바구니 주문">
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>