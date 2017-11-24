<%@ page import="pe.lizard.citapp.cita.CitaEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="pe.lizard.citapp.util.DateUtil" %>
<%@ page import="pe.lizard.citapp.cita.CitaService" %>
<%@ page import="pe.lizard.citapp.cita.CitaListarServlet" %>
<%@ page import="pe.lizard.citapp.cita.CitaBuscarServlet" %><%--
  Created by IntelliJ IDEA.
  User: JosueAngel
  Date: 21/11/2017
  Time: 12:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<CitaEntity> citas = (List) request.getAttribute("citas");
    String error_message = (String) request.getAttribute("error_message");
%>

<% if (error_message != null) { %>
<tr>
    <td colspan="5" style="background-color: #fff;">
        <div class="alert alert-danger alert-dismissable" style="margin-bottom: 10px; text-align: left">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×
            </button>
            <%=error_message%>
        </div>
    </td>
</tr>
<% } else if (!citas.isEmpty()) {
    for (CitaEntity cita : citas) {
%>
<tr>
    <td><%=cita.getCodigo()%>
    </td>
    <td><%=cita.getPaciente().getNombres() + " " + cita.getPaciente().getApellidos()%>
    </td>
    <td><%=DateUtil.toString("dd/MM/yyyy", cita.getFecha())%>
    </td>
    <td><%=cita.getHorario().getDetalle()%>
    </td>
    <td>
        <%
            switch (cita.getEstado()) {
                case CitaService.CITA_RESERVADA:
        %>
        <span class="label label-info label-mini">RESERVADA</span>
        <%
                break;
            case CitaService.CITA_ATENDIDA:
        %>
        <span class="label label-success label-mini">ATENDIDA</span>
        <%
                break;
            case CitaService.CITA_CANCELADA:
        %>
        <span class="label label-warning label-mini">CANCELADA</span>
        <%
                    break;
            }
        %>
    </td>
    <td>
        <% if (cita.getEstado() == CitaService.CITA_RESERVADA) {%>
        <button class="btn btn-danger btn-xs cancelar" data-cita="<%=cita.getId()%>">
            <i class="fa fa-ban"></i>
        </button>
        <% } %>
    </td>
</tr>
<%
    }
} else { %>
<tr>
    <td colspan="5" style="background-color: #fff;">
        No se han encontrado citas
    </td>
</tr>
<% }%>

<script>
    $(function () {
        $('.cancelar').click(function () {
            $.get('buscar', {
                    action: $('#action').val(),
                    valor: $('#valor').val(),
                    cita: $(this).data('cita'),
                    estado: <%=CitaService.CITA_CANCELADA%>
                }, function (response) {
                    $('#buscar_citas_result').html(response);
                }
            );
        });
    });
</script>
