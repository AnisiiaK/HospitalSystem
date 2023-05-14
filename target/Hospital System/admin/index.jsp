<%@ page import="com.dao.DoctorDao" %>
<%@ page import="com.db.DBConnect" %><%--
  Created by IntelliJ IDEA.
  User: Админ
  Date: 07.05.2023
  Time: 02:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="../component/allcss.jsp"%>
    <style type="text/css"> <%--рамка навколо реєстрації--%>
    .paint-card {
        box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
    }
    </style>
</head>
<body>
<%@include file="navbar.jsp"%>
<c:if test="${empty adminObj}">
    <c:redirect url="../admin_login.jsp"></c:redirect>
</c:if>

<%--<h1>Admin Dashboard</h1>--%>

<div class="container p-5">
    <p class="text-center fs-3">Admin Dashboard</p>

    <c:if test="${not empty errorMsg}">
        <p class="fs-3 text-center text-danger">${errorMsg}</p>
        <c:remove var="errorMsg" scope="session" />
    </c:if>
    <c:if test="${not empty succMsg}">
        <div class="fs-3 text-center text-success" role="alert">${succMsg}</div>
        <c:remove var="succMsg" scope="session" />
    </c:if>
    <%
        DoctorDao dao = new DoctorDao(DBConnect.getConn());
    %>
    <div class="row">

        <div class="col-md-4"><a class="nav-link active" href="doctor.jsp">

            <div class="card paint-card">
<%--                <li class="nav-item"><a class="nav-link active" href="doctor.jsp">DOCTOR</a></li>--%>
                <div class="card-body text-center text-success">
                    <i class="fas fa-user-md fa-3x"></i><br>
                    <p class="fs-4 text-center">
                        Doctor <br><%=dao.countDoctor()%>
<%--                        Doctor <br>5--%>
                    </p>
                </div>
            </div>
        </a></div>


        <div class="col-md-4"><a class="nav-link active" href="#">
            <div class="card paint-card" >
                <div class="card-body text-center text-success">
                    <i class="fas fa-user-circle fa-3x"></i><br>
                    <p class="fs-4 text-center">
                        User <br><%=dao.countUser()%>
<%--                        User <br>43--%>

                    </p>
                </div>
            </div>
        </a></div>

        <div class="col-md-4"><a class="nav-link active" href="#">
            <div class="card paint-card">
                <div class="card-body text-center text-success">
                    <i class="far fa-calendar-check fa-3x"></i><br>
                    <p class="fs-4 text-center">
                        Total Appointment <br><%=dao.countAppointment()%>
<%--                        Total Appointment <br>453--%>

                    </p>
                </div>
            </div>
        </a></div>

        <div class="col-md-4 mt-2">
            <a class="nav-link active" href="#exampleModal">

            <div class="card paint-card"
                data-bs-toggle="modal"
                data-bs-target="#exampleModal">

                <div class="card-body text-center text-success">
                    <i class="far fa-calendar-check fa-3x"></i><br>
                    <p class="fs-4 text-center">
                        Specialist <br><%=dao.countSpecialist()%>
<%--                        Specialist <br>34--%>

                    </p>
                </div>
            </div>

            </a></div>

    </div>
</div>



<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="../addSpecialist" method="post">
                    <div class="form-group">
                        <label> Enter Specialist Name</label>
                        <input type="text" name="specName" class="form-control">
                    </div>
                    <div class="text-center mt-3">
                        <button type="submit" class="btn btn-primary">Add</button>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>



</body>
</html>
