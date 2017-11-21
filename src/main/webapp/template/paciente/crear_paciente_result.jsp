<%@ page import="pe.lizard.citapp.usuario.UsuarioEntity" %><%--
  Created by IntelliJ IDEA.
  User: JosueAngel
  Date: 20/11/2017
  Time: 09:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String error_message = (String) request.getAttribute("error_message");
    UsuarioEntity paciente = (UsuarioEntity) request.getAttribute("paciente");
%>

<% if (error_message != null) { %>
<div class="alert alert-danger alert-dismissable"
     style="margin-bottom: 10px; text-align: left">
    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×
    </button>
    <%=error_message%>
</div>
<% } else {%>
<div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Paciente creado</h4>
            </div>
            <div class="modal-body" style="text-align: center;">
                <table class="table table-striped table-advance table-hover" style="margin-bottom: 0;">
                    <tbody>
                    <tr>
                        <td style="text-align: left"><b>Nombres:</b></td>
                        <td style="text-align: right"><%=paciente.getNombres()%>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: left"><b>Apellidos:</b></td>
                        <td style="text-align: right"><%=paciente.getApellidos()%>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: left"><b>Nro. documento:</b></td>
                        <td style="text-align: right"><%=paciente.getNroDocumento()%>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: left"><b>Correo:</b></td>
                        <td style="text-align: right"><%=paciente.getCorreo()%>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button id="finalizar" type="button" class="btn btn-default" data-dismiss="modal">Finalizar</button>
                <button id="reservar" type="button" class="btn btn-primary">Reservar cita</button>
            </div>
        </div>
    </div>
</div>


<script>
    $('#modal').modal({
        backdrop: 'static',
        keyboard: false,
        show: true
    });

    $('#finalizar').click(function () {
        document.location.href = '<%=request.getContextPath()%>';
    });

    $('#reservar').click(function () {
        document.location.href = '<%=request.getContextPath()%>/cita/reservar?nroDocumento=<%=paciente.getNroDocumento()%>';
    });
</script>
<% } %>
