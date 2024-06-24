<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pacis
  Date: 08/06/2023
  Time: 09:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
    <style>
        .container{
            width: 100%;
        }

    </style>
    <title>Website Download Manager - Download</title>
</head>
<body>
    <jsp:include page="component/Navbar.jsp"></jsp:include>
    <div class="container mt-5">
        <h1 class="text-primary fw-bold">Enter a website URL</h1>
        <c:if test="${not empty error}">
            <div class="alert alert-danger" role="alert">
                    ${error}
            </div>
        </c:if>
        <div class="mt-5">
            <form action="/download" method="post">
                <div class="form-group">
                    <label for="url">URL</label>
                    <input id="url" type="url" required name="url" class="form-control" placeholder="Enter your url">
                </div>
                <div class="mt-5">
                    <button type="submit" class="btn btn-primary">Download</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
