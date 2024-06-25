<%@page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../util/header.jsp" %>
<html>
<head>
    <title>회원 정보 수정</title>
</head>
<body>
<div class="container-fluid">
    <div class="main h-100">
        <form action="/member/update/${memberDTO.id}" method="post">
            <div class="row justify-content-center">
                <div class="col-4">
                    <label for="email">이메일</label>
                    <input type="text" name="email" id="email" class="form-control" oninput="disableButton()">
                    <a class="btn btn-outline-primary" onclick="validateUsername()">중복확인</a>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-4">
                    <label for="name">이름</label>
                    <input type="text" name="name" id="name" class="form-control">
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-4">
                    <label for="nickname">닉네임</label>
                    <input type="text" name="nickname" id="nickname" class="form-control">
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-4">
                    <label for="address">주소</label>
                    <input type="text" name="address" id="address" class="form-control">
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-4">
                    <label for="phoneNumber">전화번호</label>
                    <input type="text" name="phoneNumber" id="phoneNumber" class="form-control">
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-4 text-center">
                    <input id="btnSubmit" type="submit" class="btn btn-outline-primary" value="정보 수정" disabled>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    function validateUsername() {
        let email = $('#email').val();
        $.ajax({
            url: '/member/validateEmail',
            type: 'get',
            data: {
                'email': email
            },
            success: (result) => {
                if (result.result === 'success') {
                    Swal.fire({
                        'title': '가입 가능한 아이디입니다.',
                    }).then(
                        $('#btnSubmit').removeAttr('disabled'),
                        // $('#username').attr('disabled')
                    );
                } else {
                    Swal.fire({
                        'title': '중복된 이메일입니다.',
                        'icon': 'warning',
                    })
                }
            }
        });
    }

    function disableButton() {
        $('#btnSubmit').attr('disabled', 'true');
    }
</script>
</body>
</html>