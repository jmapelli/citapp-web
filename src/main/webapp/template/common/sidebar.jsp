<%--
  Created by IntelliJ IDEA.
  User: JosueAngel
  Date: 19/11/2017
  Time: 01:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UsuarioEntity ue = (UsuarioEntity) request.getSession().getAttribute("usuario");
%>

<aside>
    <div id="sidebar" class="nav-collapse ">
        <ul class="sidebar-menu" id="nav-accordion">
            <p class="centered">
                <a href="profile.html">
                    <img src="<%=request.getContextPath()%>/assets/img/icon-doc-men.jpg" class="img-circle" width="60">
                </a>
            </p>
            <h5 class="centered" style="margin-bottom: 2px;">
                Dr(a). <%= ue.getNombres() + " " + ue.getApellidos() %>
            </h5>
            <span style="color: #fff; display: block; font-size: 10px; text-align: center"><%=ue.getCorreo()%></span>

            <li class="mt">
                <a href="<%=request.getContextPath()%>/auth?action=<%=AuthLoginServlet.ACTION_LOGOUT%>">
                    <i class="fa fa-sign-out"></i>
                    <span>Cerrar sesión</span>
                </a>
            </li>

        </ul>
    </div>
</aside>