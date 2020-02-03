<%--
  Author: Jiri Matyas
  Date: 21.10.2017
  Time: 13:49
--%>
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
    <!-- <h3>Unlogged user use-cases</h3>-->
    <h3>Use-cases</h3>
    <table class="table table-hover">
        <thead class="thead-inverse">
        <tr>
            <th colspan="3"><strong>Actor: Unlogged user</strong></th>
        </tr>
        <tr>
            <th>ID</th>
            <th>Brief description</th>
            <th>
                <div class="table-responsive">Description</div>
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><a href="https://projects.kiv.zcu.cz/tbuis/web/files/uis/uc/en/html/use-case-01.html" target="_blank">UC.01</a></td>
            <td>Login</td>
            <td>A registered user logs into the application</td>
        </tr>
        <tr>
            <th colspan="3"><strong>Actor: General logged user &mdash; student or teacher</strong></th>
        </tr>
        <tr>
            <th>ID</th>
            <th>Brief description</th>
            <th>
                <div class="table-responsive">Description</div>
            </th>
        </tr>
        <tr>
            <td><a href="https://projects.kiv.zcu.cz/tbuis/web/files/uis/uc/en/html/use-case-02.html" target="_blank">UC.02</a></td>
            <td>Logout</td>
            <td>A logged-in user logs out from the application</td>
        </tr>
        <tr>
            <td><a href="https://projects.kiv.zcu.cz/tbuis/web/files/uis/uc/en/html/use-case-03.html" target="_blank">UC.03</a></td>
            <td>Get/Set user's personal data</td>
            <td>A logged-in user can check details (first name, last name, email) and can change some of them</td>
        </tr>
        <tr>
            <th colspan="3"><strong>Actor: Logged users &mdash; Student</strong></th>
        </tr>
        <tr>
            <th>ID</th>
            <th>Brief description</th>
            <th>
                <div class="table-responsive">Description</div>
            </th>
        </tr>
        <tr>
            <td><a href="https://projects.kiv.zcu.cz/tbuis/web/files/uis/uc/en/html/use-case-04.html" target="_blank">UC.04</a></td>
            <td>Get/Unenroll enrolled subjects</td>
            <td>A student checks his/her enrolled subjects and eventually unenrolls some of them</td>
        </tr>
        <tr>
            <td><a href="https://projects.kiv.zcu.cz/tbuis/web/files/uis/uc/en/html/use-case-05.html" target="_blank">UC.05</a></td>
            <td>Get information about passed subjects</td>
            <td>A student checks list of subjects that he/she passed</td>
        </tr>
        <tr>
            <td><a href="https://projects.kiv.zcu.cz/tbuis/web/files/uis/uc/en/html/use-case-06.html" target="_blank">UC.06</a></td>
            <td>Get/Enroll unenrolled subjects</td>
            <td>A student checks subjects that he/she does not study&nbsp;now and eventually enrolls some of them</td>
        </tr>
        <tr>
            <td><a href="https://projects.kiv.zcu.cz/tbuis/web/files/uis/uc/en/html/use-case-07.html" target="_blank">UC.07</a></td>
            <td>Get/Unregister examination dates</td>
            <td>A student checks the examination dates on which he/she is registered and eventually unregisters some of them</td>
        </tr>
        <tr>
            <td><a href="https://projects.kiv.zcu.cz/tbuis/web/files/uis/uc/en/html/use-case-08.html" target="_blank">UC.08</a></td>
            <td>Get/Register examination dates</td>
            <td>A student checks the examination dates which he/she is not registered for and eventually register some of them</td>
        </tr>
        <tr>
            <td><a href="https://projects.kiv.zcu.cz/tbuis/web/files/uis/uc/en/html/use-case-09.html" target="_blank">UC.09</a></td>
            <td>Get information about exam's participants</td>
            <td>A student checks the list of participants (schoolmates) on some examination date</td>
        </tr>
        <tr>
            <th colspan="3"><strong>Actor: Logged user &mdash; Teacher</strong></th>
        </tr>
        <tr>
            <th>ID</th>
            <th>Brief description</th>
            <th>
                <div class="table-responsive">Description</div>
            </th>
        </tr>
        <tr>
            <td><a href="https://projects.kiv.zcu.cz/tbuis/web/files/uis/uc/en/html/use-case-10.html" target="_blank">UC.10</a></td>
            <td>Get/Remove registered subjects</td>
            <td>A teacher checks the subjects he/she teaches actually and eventually to remove some of them</td>
        </tr>
        <tr>
            <td><a href="https://projects.kiv.zcu.cz/tbuis/web/files/uis/uc/en/html/use-case-11.html" target="_blank">UC.11</a></td>
            <td>Get information about students on subject</td>
            <td>A teacher checks the list of students enrolled on some subject</td>
        </tr>
        <tr>
            <td><a href="https://projects.kiv.zcu.cz/tbuis/web/files/uis/uc/en/html/use-case-12.html" target="_blank">UC.12</a></td>
            <td>Get/Cancel examination dates</td>
            <td>A teacher checks examination dates he/she has released and eventually cancel some of them</td>
        </tr>
        <tr>
            <td><a href="https://projects.kiv.zcu.cz/tbuis/web/files/uis/uc/en/html/use-case-13.html" target="_blank">UC.13</a></td>
            <td>Get information about exam's participants</td>
            <td>A teacher checks the list of participants on some examination date</td>
        </tr>
        <tr>
            <td><a href="https://projects.kiv.zcu.cz/tbuis/web/files/uis/uc/en/html/use-case-14.html" target="_blank">UC.14</a></td>
            <td>Release a new examination date</td>
            <td>A teacher releases a new examination date for the subject he/she teaches</td>
        </tr>
        <tr>
            <td><a href="https://projects.kiv.zcu.cz/tbuis/web/files/uis/uc/en/html/use-case-15.html" target="_blank">UC.15</a></td>
            <td>Set student's evaluation by Evaluation Form</td>
            <td>A teacher evaluates a student who participated in the examination date using a simple form</td>
        </tr>
        <tr>
            <td><a href="https://projects.kiv.zcu.cz/tbuis/web/files/uis/uc/en/html/use-case-16.html" target="_blank">UC.16</a></td>
            <td>Set/Update student's evaluation by Evaluation Table</td>
            <td>A teacher can evaluate or check/update existing evaluation of a student who participated in the examination date using the evaluation table</td>
        </tr>
        <tr>
            <td><a href="https://projects.kiv.zcu.cz/tbuis/web/files/uis/uc/en/html/use-case-17.html" target="_blank">UC.17</a></td>
            <td>Get/Participate in untaught subjects</td>
            <td>A teacher checks subjects that he/she does not teach and eventually participate in some of them</td>
        </tr>
        <tr>
            <td><a href="https://projects.kiv.zcu.cz/tbuis/web/files/uis/uc/en/html/use-case-18.html" target="_blank">UC.18</a></td>
            <td>Get a list of all teachers</td>
            <td>A teacher can observe the list of all teachers with the subjects they teach</td>
        </tr>
        </tbody>
        <thead>
        <tr>
            <th colspan="3"><strong>Actor: Unlogged user &mdash; a special activity for testing</strong></th>
        </tr>
        <tr>
            <th>ID</th>
            <th>Brief description</th>
            <th>
                <div class="table-responsive">Description</div>
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><a href="https://projects.kiv.zcu.cz/tbuis/web/files/uis/uc/en/html/use-case-19.html" target="_blank">UC.19</a></td>
            <td>Restore DB</td>
            <td>An unregistered user restores a content of the database into predefined values</td>
        </tr>
        <tr>
            <td><a href="https://projects.kiv.zcu.cz/tbuis/web/files/uis/uc/en/html/use-case-20.html" target="_blank">UC.20</a></td>
            <td>Geting information</td>
            <td>An unregistered user checks important information about possibilities of UIS (a list of use-cases, a content of predefined database and application constraints)</td>
        </tr>
        <tr>
            <td><a href="https://projects.kiv.zcu.cz/tbuis/web/files/uis/uc/en/html/use-case-21.html" target="_blank">UC.21</a></td>
            <td>Export dat</td>
            <td>An unregistered user exports whole application data in JSON or XML format from database</td>
        </tr>
        <tr>
            <td><a href="https://projects.kiv.zcu.cz/tbuis/web/files/uis/uc/en/html/use-case-22.html" target="_blank">UC.22</a></td>
            <td>Import dat</td>
            <td>An unregistered user imports whole application data in JSON or XML format into database</td>
        </tr>
    </table>
    <h3>Diagram</h3>
    <img src="<spring:url value="/images/use-case.png"/>" class="img-fluid" alt="Use case diagram">
</div><!-- /.container -->


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<!-- Bootstrap 4 -->
<script src="<spring:url value="js/jquery-3.1.1.slim.min.js"/>"></script>
<script src="<spring:url value="js/popper.min.js"/>"></script>
<script src="<spring:url value="js/bootstrap.min.js"/>"></script>

</body>
</html>
