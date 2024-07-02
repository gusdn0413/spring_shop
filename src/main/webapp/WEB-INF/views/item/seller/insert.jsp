<%@page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../util/header.jsp" %>
<html>
<head>
    <title>아이템 등록</title>
    <script src="https://cdn.ckeditor.com/ckeditor5/41.4.2/classic/ckeditor.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
    <script src="https://ckeditor.com/apps/ckfiinder/3.5.0/ckfinder.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="main h-100">
        <form action="/item/seller/insert/${categoryId}" method="post" enctype="multipart/form-data">
            <div class="row justify-content-center">
                <div class="col-4">
                    <label for="name">상품 이름</label>
                    <input type="text" name="name" id="name" class="form-control">
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-4">
                    <label for="price">상품 가격</label>
                    <input type="number" name="price" id="price" class="form-control">
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-4">
                    <label for="quantity">상품 수량</label>
                    <input type="number" name="quantity" id="quantity" class="form-control">
                </div>
            </div>
            <div class="row justify-content-center mb-3">
                <div class="col-6">
                    <textarea name="content" id="input_content"></textarea>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-4 text-center">
                    <input id="btnSubmit" type="submit" class="btn btn-outline-primary" value="아이템 등록">
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    ClassicEditor
        .create(document.querySelector('#input_content'),{
            ckfinder: {
                uploadUrl: '/item/seller/uploads'
            }
        })
        .catch(error => {
            console.log(error);})
</script>
</body>
</html>