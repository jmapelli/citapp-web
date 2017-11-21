<%@ page import="pe.lizard.citapp.cita.CitaEntity" %>
<%@ page import="pe.lizard.citapp.util.DateUtil" %><%--
  Created by IntelliJ IDEA.
  User: JosueAngel
  Date: 20/11/2017
  Time: 07:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String error_message = (String) request.getAttribute("error_message");
    CitaEntity cita = (CitaEntity) request.getAttribute("cita");
%>

<% if (error_message != null) { %>
<div class="alert alert-danger alert-dismissable" style="margin-bottom: 10px; text-align: left">
    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×
    </button>
    <%=error_message%>
</div>
<% } else {%>

<div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby=""
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Cita reservada</h4>
            </div>
            <div class="modal-body" style="text-align: center;">
                <table class="table table-striped table-advance table-hover" style="margin-bottom: 0;">
                    <tbody>
                    <tr>
                        <td style="text-align: left"><b>Código:</b></td>
                        <td style="text-align: right"><%=cita.getCodigo()%>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: left"><b>Paciente:</b></td>
                        <td style="text-align: right"><%=cita.getPaciente().getNombres() + " " + cita.getPaciente().getApellidos()%>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: left"><b>Nro. documento:</b></td>
                        <td style="text-align: right"><%=cita.getPaciente().getNroDocumento()%>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: left"><b>Correo:</b></td>
                        <td style="text-align: right"><%=cita.getPaciente().getCorreo()%>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: left"><b>Día:</b></td>
                        <td style="text-align: right"><%=DateUtil.toString("dd/MM/yyyy", cita.getFecha())%>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: left"><b>Hora:</b></td>
                        <td style="text-align: right"><%=cita.getHorario().getDetalle()%>
                        </td>
                    </tr>

                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button id="aceptar" type="button" class="btn btn-primary" data-dismiss="modal">Aceptar</button>
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

    $('#aceptar').click(function () {
        document.location.href = '<%=request.getContextPath()%>';
    });
</script>
<% } %>