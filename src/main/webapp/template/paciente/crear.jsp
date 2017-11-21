<%--
  Created by IntelliJ IDEA.
  User: JosueAngel
  Date: 19/11/2017
  Time: 03:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UsuarioEntity paciente = (UsuarioEntity) request.getAttribute("paciente");
    String error_message = (String) request.getAttribute("error_message");

    String nombres = "";
    String apellidos = "";
    String correo = "";
    String nroDocumento = "";

    if (request.getParameter("nombres") != null) {
        nombres = request.getParameter("nombres");
    }

    if (request.getParameter("apellidos") != null) {
        apellidos = request.getParameter("apellidos");
    }

    if (request.getParameter("correo") != null) {
        correo = request.getParameter("correo");
    }

    if (request.getParameter("nroDocumento") != null) {
        nroDocumento = request.getParameter("nroDocumento");
    }
%>

<%@ include file="../common/header.jsp" %>
<section id="container">
    <%@ include file="../common/nav.jsp" %>
    <%@ include file="../common/sidebar.jsp" %>

    <section id="main-content">
        <section class="wrapper">

            <div class="row mt">
                <div class="col-lg-12">
                    <div class="form-panel">
                        <form class="form-horizontal" action="crear" method="post">
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Nombres</label>
                                <div class="col-sm-10">
                                    <input name="nombres" type="text" class="form-control" value="<%=nombres%>">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Apellidos</label>
                                <div class="col-sm-10">
                                    <input name="apellidos" type="text" class="form-control" value="<%=apellidos%>">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Correo electrónico</label>
                                <div class="col-sm-10">
                                    <input name="correo" type="email" class="form-control" value="<%=correo%>">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Nro. documento</label>
                                <div class="col-sm-10">
                                    <input name="nroDocumento" type="text" class="form-control"
                                           value="<%=nroDocumento%>">
                                </div>
                            </div>

                            <div style="padding-top: 10px; text-align: right; border-top: 1px solid #e5e5e5;">

                                <% if (error_message != null) { %>
                                <div class="alert alert-danger alert-dismissable"
                                     style="margin-bottom: 10px; text-align: left">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×
                                    </button>
                                    <%=error_message%>
                                </div>
                                <% } %>

                                <button id="cancelar" type="button" class="btn btn-default">Cancelar</button>
                                <button id="crear" type="button" class="btn btn-primary">Crear</button>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </section>
    </section>
</section>
<%@ include file="../common/footer.jsp" %>

<% if (paciente != null) { %>
<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby=""
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">Confirmación</h4>
            </div>
            <div class="modal-body" style="text-align: center;">
                <p>El paciente ha sido creado:</p>
                <h4 style="margin-bottom: 0;"><%=paciente.getNombres() + " " + paciente.getApellidos()%>
                </h4>
                <p><%=paciente.getNroDocumento()%>
                </p>
            </div>
            <div class="modal-footer">
                <button id="volver" type="button" class="btn btn-default" data-dismiss="modal">Finalizar</button>
                <button id="reservar" type="button" class="btn btn-primary">Reservar cita</button>
            </div>
        </div>
    </div>
</div>
<% } %>

<script>
    $(function () {
        $('#confirmModal').modal({
            backdrop: 'static',
            keyboard: false
        });

        $('#crear').click(function () {
            $('form').submit();
        });

        $('#cancelar').click(function () {
            document.location.href = '<%=request.getContextPath()%>';
        });

        <% if(paciente != null){ %>
        $('#volver').click(function () {
            document.location.href = '<%=request.getContextPath()%>';
        });

        $('#reservar').click(function () {
            document.location.href = '<%=request.getContextPath()%>/cita/crear?nroDocumento=<%=paciente.getNroDocumento()%>';
        });

        $('#confirmModal').modal('show');
        <% } %>
    });
</script>

