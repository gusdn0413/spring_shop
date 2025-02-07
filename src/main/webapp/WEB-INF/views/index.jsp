<%@page language="java" contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="util/jsUtil.jsp" %>
<html>
<head>
    <title>인덱스</title>
</head>
<body>
<div class="container-fluid">
    <div class="main h-100">
        <c:if test="${not empty loginFailed}">
            <div class="row justify-content-center">
                <div class="col-4 text-center">
                    <p style="color: red;">로그인 정보가 틀렸습니다</p>
                </div>
            </div>
        </c:if>
        <form action="/member/auth" method="post">
            <div class="row justify-content-center">
                <div class="col-4">
                    <label for="email">이메일</label>
                    <input type="text" class="form-control" name="email" id="email" required>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-4">
                    <label for="password">비밀번호</label>
                    <input type="password" class="form-control" name="password" id="password" required>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-2 text-center">
                    <input type="submit" class="btn btn btn-outline-primary" value="로그인">
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-2 text-center">
                    <a href="/member/register" class="btn btn btn-outline-secondary">회원가입</a>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>