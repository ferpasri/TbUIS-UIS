<%--
  Created by IntelliJ IDEA.
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
        <tr class="table-primary">
            <th colspan="3"><strong>Actor: Unlogged user</strong></th>
        </tr>
        <tr>
            <th>Use-case number</th>
            <th>Use-case name</th>
            <th>Description</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>1</td>
            <td>Web browsing</td>
            <td>Use-case allows an unregistered user to view web pages that are accessible to an unregistered user (home page, Db content page, use cases page, export / import page, logon page).
            </td>
        </tr>
        <tr>
            <td>2</td>
            <td>Login</td>
            <td>Use-case allows an unregistered user to log into the application.</td>
        </tr>
        <tr>
            <td>3</td>
            <td>Export/Import dat</td>
            <td>Use-case allows an unregistered user to import and export application data in JSON and XML format.
            </td>
        </tr>
        <tr>
            <td>4</td>
            <td>Restore DB</td>
            <td>Use-case allows an unregistered user to restore a state of the database in which database was after the start of the application.
            </td>
        </tr>
        <tr class="table-primary">
            <th colspan="3"><strong>Actor: General logged user</strong></th>
        </tr>
        <tr>
            <th>Use-case number</th>
            <th>Use-case name</th>
            <th>Description</th>
        </tr>
        <tr>
            <td>5</td>
            <td>Logout</td>
            <td>Use-case allows the logged-in user to log off from the application.</td>
        </tr>
        <tr>
            <td>6</td>
            <td>Get/Set user data</td>
            <td>Use-case enables a logged-in user to check their details (name, surname, email) and change them if necessary.</td>
        </tr>
        <tr class="table-primary">
            <th colspan="3"><strong>Actor: Logged user - Student</strong></th>
        </tr>
        <tr>
            <th>Use-case number</th>
            <th>Use-case name</th>
            <th>Description</th>
        </tr>
        <tr>
            <td>7</td>
            <td>Get/Unenroll enrolled subjects</td>
            <td>Use-case allows a logged-in student to check his or her enrolled subjects and eventually unenroll them.</td>
        </tr>
        <tr>
            <td>8</td>
            <td>Get/Enroll unenrolled subjects</td>
            <td>Use-case allows a logged-in student to check items that he / she does not study and eventually enroll them.</td>
        </tr>
        <tr>
            <td>9</td>
            <td>Get/Unregister examination dates</td>
            <td>Use-case allows a logged-in student to check the examination dates on which he / she is registered and eventually unregister himself / herself.
            </td>
        </tr>
        <tr>
            <td>10</td>
            <td>Get/Register examination dates</td>
            <td>Use-case allows a logged-in student to check the examination dates which he / she is not registered for and eventually register himself / herself.
            </td>
        </tr>
        <tr class="table-primary">
            <th colspan="3"><strong>Actor: Logged user - Teacher</strong></th>
        </tr>
        <tr>
            <th>Use-case number</th>
            <th>Use-case name</th>
            <th>Description</th>
        </tr>
        <tr>
            <td>11</td>
            <td>Get/Remove registered subjects</td>
            <td>Use-case allows a logged-in teacher to check the subjects he / she teaches and eventually to remove them.</td>
        </tr>
        <tr>
            <td>12</td>
            <td>Get/Participate in unregistered subjects</td>
            <td>Use-case allows a logged-in teacher to check subjects that he / she does not teach and eventually regester them.</td>
        </tr>
        <tr>
            <td>13</td>
            <td>Get/Cancel examination dates</td>
            <td>Use-case allows a logged-in teacher to check the examination dates he / she has created and eventually cancel them.</td>
        </tr>
        <tr>
            <td>14</td>
            <td>Create a new Examination date</td>
            <td>Use-case allows a logged-in teacher to create a new examination date for the subject he / she teaches.</td>
        </tr>
        <tr>
            <td>15</td>
            <td>Create/Update Evaluation by Evaluation Form</td>
            <td>Use-case allows a logged-in teacher to evaluate a student who participated in the examination date using
                the generated form and later to change this evaluation.
            </td>
        </tr>
        <tr>
            <td>16</td>
            <td>Create/Update Evaluation by Evaluation Table</td>
            <td>Use-case allows a logged-in teacher to evaluate a student who participated in the examination date using
                the evaluation table and later to change this evaluation.
            </td>
        </tr>
        <tr>
            <td>17</td>
            <td>Get a list of all teachers</td>
            <td>Use-case allows a logged-in teacher to show the table of all teachers in the application with the subjects they teach.</td>
        </tr>
        </tbody>
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
