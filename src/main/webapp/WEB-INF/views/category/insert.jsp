<%@page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../util/header.jsp" %>
<html>
<head>
    <title>카테고리 등록</title>
</head>
<body>
<div class="container-fluid">
    <div class="main h-100">
        <form action="/category/insert" method="post">
            <div class="row justify-content-center">
                <div class="col-4">
                    <label for="name">카테고리 이름</label>
                    <input type="text" name="name" id="name" class="form-control">
                </div>
                <div class="row justify-content-center">
                    <div class="col-4 text-center">
                        <input id="btnSubmit" type="submit" class="btn btn-outline-primary" value="카테고리 등록">
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>