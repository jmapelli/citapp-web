<%@ page import="pe.lizard.citapp.util.Error" %>
<%--
  Created by IntelliJ IDEA.
  User: JosueAngel
  Date: 18/11/2017
  Time: 10:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/header.jsp" %>
<div id="login-page">
    <div class="container">

        <form action="signup" method="post" class="form-login" style="box-shadow: 0 2px 1px rgba(0, 0, 0, 0.2);">
            <h2 class="form-login-heading">Registrar usuario</h2>
            <div class="login-wrap">
                <input name="usuario" type="text" class="form-control" placeholder="Usuario" autofocus><br>
                <input name="clave" type="password" class="form-control" placeholder="Contraseña"><br>
                <input name="nombres" type="text" class="form-control" placeholder="Nombres"><br>
                <input name="apellidos" type="text" class="form-control" placeholder="Apellidos"><br>
                <input name="correo" type="email" class="form-control" placeholder="Correo electrónico"><br>
                <button class="btn btn-primary btn-block" type="submit">
                    <i class="fa fa-lock"></i> Registrar
                </button>
                <hr style="border-top: 1px solid #eee ">
                <div class="registration">
                    Ya tiene una cuenta?<br>
                    <a class="" href="<%=request.getContextPath()%>/auth">
                        Inicia sesión
                    </a>
                </div>
                <%
                    if (request.getAttribute("error_status") != null
                            && request.getAttribute("error_status").equals(Error.ERROR_STATUS_OK)) {
                %>
                <br>
                <div class="alert alert-danger alert-dismissable" style="margin-bottom: 0">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                    <%=request.getAttribute("error_message")%>
                </div>
                <%
                    }
                %>
            </div>

        </form>

    </div>
</div>
<%@ include file="../common/footer.jsp" %>