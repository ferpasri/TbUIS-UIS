<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Use-cases</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<spring:url value="css/bootstrap.min.css"/>">
    <!-- Default stylesheet -->
    <link rel="stylesheet" href="<spring:url value="css/style.css"/>">
    <link rel="shortcut icon" href="<spring:url value="images/favico-uis.ico"/>" type="image/x-icon">
</head>

<body>
<!-- Top Menu -->
<c:import url="WEB-INF/pages/modules/menus/menu.jsp"/>
<div id="main-content" class="container">
    <h3>Constraints</h3>

    <div class="row">
        <div class="col-xl-6">
            <table class="table table-hover">
                <thead class="thead-inverse">
                <tr class="table-primary">
                    <th colspan="3"><strong>Limitations regarding university</strong></th>
                </tr>
                <tr>
                    <th>Name</th>
                    <th>Limit</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>Maximal number of subjects for students</td>
                    <td>7</td>
                </tr>
                <tr>
                    <td>Maximal number of subjects for teachers</td>
                    <td>5</td>
                </tr>
                <tr>
                    <td>Maximal number of teachers for subject</td>
                    <td>2</td>
                </tr>
                <tr>
                    <td>Maximal number of exam dates for subject</td>
                    <td>3</td>
                </tr>
                <tr>
                    <td>Minimal number of participants for exam date</td>
                    <td>1</td>
                </tr>
                <tr>
                    <td>Maximal number of participants for exam date</td>
                    <td>10</td>
                </tr>

                </tbody>
            </table>
        </div>

        <div class="col-xl-6">
            <table class="table table-hover">
                <thead class="thead-inverse">
                <tr class="table-primary">
                    <th colspan="3"><strong>Limitations regarding users</strong></th>
                </tr>
                <tr>
                    <th>Name</th>
                    <th>Limit</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>Minimal length of first name</td>
                    <td>2</td>
                </tr>
                <tr>
                    <td>Maximal length of first name</td>
                    <td>30</td>
                </tr>
                <tr>
                    <td>Minimal length of last name</td>
                    <td>2</td>
                </tr>
                <tr>
                    <td>Maximal length of last name</td>
                    <td>30</td>
                </tr>
                <tr>
                    <td>Minimal length of mail</td>
                    <td>8</td>
                </tr>
                <tr>
                    <td>Maximal length of mail</td>
                    <td>40</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

</div>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<!-- Bootstrap 4 -->
<script src="<spring:url value="js/jquery-3.1.1.slim.min.js"/>"></script>
<script src="<spring:url value="js/popper.min.js"/>"></script>
<script src="<spring:url value="js/bootstrap.min.js"/>"></script>

</body>
</html>
