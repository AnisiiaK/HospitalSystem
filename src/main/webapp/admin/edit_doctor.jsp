<%@ page import="com.dao.SpecialistDao" %>
<%@ page import="com.db.DBConnect" %>
<%@ page import="com.entity.Specialist" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dao.DoctorDao" %>
<%@ page import="com.entity.Doctor" %><%--
  Created by IntelliJ IDEA.
  User: Админ
  Date: 08.05.2023
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="../component/allcss.jsp" %>
    <style type="text/css"> <%--рамка навколо реєстрації--%>
    .paint-card {
        box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
    }
    </style>
</head>
<body>
<%@include file="navbar.jsp" %>

<div class="container-fluid p-3">
    <div class="row">

        <div class="col-md-5 offset-md-4"> <%--offset to make it in center--%>
            <div class="card paint-card">
                <div class="card-body">
                    <p class="fs-3 text-center">Edit Doctor Details</p>
                    <c:if test="${not empty errorMsg}">
                        <p class="fs-3 text-center text-danger">${errorMsg}</p>
                        <c:remove var="errorMsg" scope="session"/>
                    </c:if>
                    <c:if test="${not empty sucMsg}">
                        <div class="fs-3 text-center text-success" role="alert">${sucMsg}</div>
                        <c:remove var="sucMsg" scope="session"/>
                    </c:if>

                    <%
                        int id = Integer.parseInt(request.getParameter("id"));
                        DoctorDao dao2 = new DoctorDao(DBConnect.getConn());
                        Doctor d = dao2.getDoctorById(id);
                    %>
                    <form action="../updateDoctor" method="post">
                        <div class="mb-3">
                            <label class="form-label">Full Name</label> <input type="text"
                                                                               required name="fullname"
                                                                               class="form-control"
                                                                               value="<%=d.getFullName()%>">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">DOB</label> <input type="date"
                                                                         required name="dob" class="form-control"
                                                                         value="<%=d.getDob()%>">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Qualification</label> <input required
                                                                                   name="qualification" type="text"
                                                                                   class="form-control"
                                                                                   value="<%=d.getQualification()%>">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Specialist</label> <select name="spec"
                                                                                 required class="form-control">
                            <option><%=d.getSpecialist()%>
                            </option>

                            <%
                                SpecialistDao dao = new SpecialistDao(DBConnect.getConn());
                                List<Specialist> list = dao.getAllSpecialist();
                                for (Specialist s : list) {
                            %>
                            <option><%=s.getSpecialistName()%>
                            </option>
                            <%
                                }
                            %>

                        </select>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Email</label> <input type="text"
                                                                           required name="email" class="form-control"
                                                                           value="<%=d.getEmail()%>">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Mob No</label> <input type="text"
                                                                            required name="mobno" class="form-control"
                                                                            value="<%=d.getMobNo()%>">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Password</label> <input required
                                                                              name="password" type="password"
                                                                              class="form-control"
                                                                              value="<%=d.getPassword()%>">
                        </div>
                        <input type="hidden" name="id" value="<%=d.getId()%>">

                        <button type="submit" class="btn btn-primary col-md 12">Update</button>
                    </form>
                </div>
            </div>
        </div>

    </div>
</div>

</body>
</html>