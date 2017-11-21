<%@ page import="pe.lizard.citapp.util.Error" %>
<%--
  Created by IntelliJ IDEA.
  User: JosueAngel
  Date: 18/11/2017
  Time: 10:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String usuario = "";
    if (request.getParameter("usuario") != null) {
        usuario = request.getParameter("usuario");
    }
%>

<%@ include file="../common/header.jsp" %>
<div id="login-page">
    <div class="container">

        <form action="auth" method="post" class="form-login" style="box-shadow: 0 2px 1px rgba(0, 0, 0, 0.2);">
            <h2 class="form-login-heading">Iniciar sesión</h2>
            <div style="text-align: center; margin-top: 20px;">
                <img style="width: 60%;" src="<%=request.getContextPath()%>/assets/img/citapp.jpg"/>
            </div>
            <div class="login-wrap">
                <input name="usuario" type="text" class="form-control" placeholder="Usuario" value="<%=usuario%>"
                       autofocus><br>
                <input name="clave" type="password" class="form-control" placeholder="Contraseña"><br>
                <button class="btn btn-primary btn-block" type="submit">
                    <i class="fa fa-lock"></i> Iniciar sesión
                </button>
                <hr style="border-top: 1px solid #eee ">
                <div class="registration">
                    Aún no tienes una cuenta?<br>
                    <a class="" href="<%=request.getContextPath()%>/auth/signup">
                        Crear una cuenta
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