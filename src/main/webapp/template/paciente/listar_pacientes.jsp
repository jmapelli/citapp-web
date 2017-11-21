<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: JosueAngel
  Date: 19/11/2017
  Time: 03:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<UsuarioEntity> pacientes = (List) request.getAttribute("pacientes");
%>

<%@ include file="../../template/common/header.jsp" %>
<section id="container">
    <%@ include file="../../template/common/nav.jsp" %>
    <%@ include file="../../template/common/sidebar.jsp" %>

    <section id="main-content">
        <section class="wrapper">

            <div class="row mt">
                <div class="col-md-12">
                    <div class="content-panel">
                        <div style="overflow-x: auto">
                            <table class="table table-striped table-advance table-hover">
                                <thead>
                                <tr>
                                    <th>Nombres</th>
                                    <th>Apellidos</th>
                                    <th>Nro. documento</th>
                                    <th>Correo</th>
                                    <%--<th></th>--%>
                                </tr>
                                </thead>
                                <tbody>
                                <% for (UsuarioEntity paciente : pacientes) { %>
                                <tr>
                                    <td><%=paciente.getNombres()%>
                                    </td>
                                    <td><%=paciente.getApellidos()%>
                                    </td>
                                    <td><%=paciente.getNroDocumento()%>
                                    </td>
                                    <td><%=paciente.getCorreo()%>
                                    </td>
                                    <%--<td>--%>
                                    <%--<button class="btn btn-primary btn-xs"><i class="fa fa-pencil"></i></button>--%>
                                    <%--</td>--%>
                                </tr>
                                <% } %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

        </section>
    </section>
</section>
<%@ include file="../../template/common/footer.jsp" %>