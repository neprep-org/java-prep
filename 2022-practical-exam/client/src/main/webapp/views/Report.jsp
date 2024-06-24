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
    <title>Website Download Manager - Report</title>
</head>
<body>
    <jsp:include page="component/Navbar.jsp"></jsp:include>

    <div class="container">
        <h2 class="text-primary fw-bold text-center">Report</h2>
        <div class="report-container">
            <table class="table table-striped table-hover">
                <thead class="table-header">
                    <tr>
                        <td>Website name</td>
                        <td>Start date time</td>
                        <td>End date time</td>
                        <td>Total elapsed time(MS)</td>
                        <td>Total Kilobytes downloaded</td>
                    </tr>
                </thead>
                <tbody class="table-body">
                    <c:forEach items="${websites}" var="website">
                        <tr>
                            <td><a href="/links/${website.id}">${website.websiteName}</a></td>
                            <td>${website.downloadStartDateTime}</td>
                            <td>${website.downloadEndDateTime}</td>
                            <td>${website.totalElapsedTime} MS</td>
                            <td>${website.totalDownloadedKilobytes} Kbs</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
