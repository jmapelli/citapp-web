<%@ page import="pe.lizard.citapp.cita.CitaEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="pe.lizard.citapp.cita.CitaService" %>
<%@ page import="pe.lizard.citapp.util.DateUtil" %>
<%@ page import="java.util.Date" %>
<%@ page import="pe.lizard.citapp.cita.CitaListarServlet" %><%--
  Created by IntelliJ IDEA.
  User: JosueAngel
  Date: 23/11/2017
  Time: 04:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String error_message = (String) request.getAttribute("error_message");
    String fecha = (String) request.getAttribute("fecha");
    List<CitaEntity> citas = (List) request.getAttribute("citas");
    String hoy = DateUtil.toString("yyyy-MM-dd", new Date());
%>

<div class="task-content">
    <% if (error_message != null) { %>
    <!-- TODO error message -->
    <% } else if (citas != null && !citas.isEmpty()) { %>
    <ul class="task-list">
        <% for (CitaEntity cita : citas) {%>
        <li>
            <div class="task-checkbox"
                 style="width: 65px;
                 border-right: 1px solid #eaeaea;
                 margin-right: 6px;
                 text-align: right; padding: 0 5px;">
                <%--<i class=" fa fa-clock-o" style="margin-right: 5px;"></i>--%>
                <%=cita.getHorario().getDetalle()%>
            </div>
            <div class="task-title">
                <span class="task-title-sp">
                    <%=cita.getPaciente().getNombres() + " " + cita.getPaciente().getApellidos()%>
                </span>
                <% if (cita.getEstado() != CitaService.CITA_ATENDIDA && hoy.equals(fecha)) { %>
                <div class="pull-right">
                    <button class="btn btn-success btn-xs atender" data-cita="<%=cita.getId()%>">
                        <i class=" fa fa-check"></i>
                    </button>
                </div>
                <% } %>
            </div>
        </li>
        <% } %>
    </ul>
    <% } else { %>
    No hay citas.
    <% } %>
</div>

<script>
    $(function () {
        $('.atender').click(function () {

            $.get('', {
                    action: '<%=CitaListarServlet.ACTION_DAILY%>',
                    fecha: $('#fecha').val(),
                    cita: $(this).data('cita'),
                    estado: <%=CitaService.CITA_ATENDIDA%>
                }, function (response) {
                    $('#listar_citas_result').html(response);
                }
            );
        });
    });
</script>