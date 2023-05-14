<%--
  Created by IntelliJ IDEA.
  User: Админ
  Date: 10.05.2023
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@page import="com.entity.Doctor"%>
<%@page import="com.db.DBConnect"%>
<%@page import="com.dao.DoctorDao"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<%--    <meta charset="ISO-8859-1">--%>
    <title>Insert title here</title>
    <style type="text/css">
        .paint-card {
            box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
        }
    </style>
    <%@include file="../component/allcss.jsp"%>
</head>
<body>
<c:if test="${empty doctorObj }">
    <c:redirect url="../doctor_login.jsp"></c:redirect>
</c:if>


<%@include file="navbar.jsp"%>

<p class="text-center fs-3">Doctor Dashboard</p>

<%
    Doctor d = (Doctor) session.getAttribute("doctorObj");
    DoctorDao dao = new DoctorDao(DBConnect.getConn());
%>
<div class="container p-5">
    <div class="row">
        <div class="col-md-4 offset-md-2">
            <div class="card paint-card">
                <div class="card-body text-center text-success">
                    <i class="fas fa-user-md fa-3x"></i><br>
                    <p class="fs-4 text-center">
                        Doctor <br>  <%=dao.countDoctor()%>
                    </p>
                </div>
            </div>
        </div>


        <div class="col-md-4">
            <div class="card paint-card">
                <div class="card-body text-center text-success">
                    <i class="far fa-calendar-check fa-3x"></i><br>
                    <p class="fs-4 text-center">
                        Total Appointment <br>
                        <%=dao.countAppointmentByDoctorId(d.getId())%>
                    </p>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>
