<%@page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../util/header.jsp" %>
<html>
<head>
    <title>상품 장바구니 추가</title>
</head>
<body>
상품 이름 : ${itemDTO.name}
<hr/>
남은 수량 : ${itemDTO.quantity}
<hr/>
상품 가격 : ${itemDTO.price}

<div class="container-fluid">
    <div class="main h-100">
        <form action="/order/insert/${itemDTO.id}" method="post">
            <div class="row justify-content-center">
                <div class="col-4">
                    <label for="quantity">구매할 수량</label>
                    <input type="number" name="quantity" id="quantity" class="form-control" required>
                </div>
                <div class="row justify-content-center">
                    <div class="col-4 text-center">
                        <input id="btnSubmit" type="submit" class="btn btn-outline-primary" value="구매">
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>