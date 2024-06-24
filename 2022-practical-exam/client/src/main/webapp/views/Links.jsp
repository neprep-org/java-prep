<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

        .report-container{
            padding: 2% 10%;
        }

    </style>
    <title>Website Download Manager - Website Links</title>
</head>
<body>
<jsp:include page="component/Navbar.jsp"></jsp:include>
<div class="container">
    <h2 class="text-primary fw-bold text-center">Website Links</h2>
    <div class="report-container">
        <table class="table table-striped table-hover">
            <thead class="table-header">
            <tr>
                <td>Link name</td>
                <td>Website name</td>
                <td>Total elapsed time(MS)</td>
                <td>Total Kilobytes downloaded</td>
            </tr>
            </thead>
            <tbody class="table-body">
            <c:forEach items="${links}" var="link">
                <tr>
                    <td>${link.linkName}</td>
                    <td>${link.website.websiteName}</td>
                    <td>${link.totalElapsedTime} MS</td>
                    <td>${link.totalDownloadedKilobytes} Kbs</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
