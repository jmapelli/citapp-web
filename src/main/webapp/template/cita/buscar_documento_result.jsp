<%@ page import="pe.lizard.citapp.usuario.UsuarioEntity" %><%--
  Created by IntelliJ IDEA.
  User: JosueAngel
  Date: 20/11/2017
  Time: 06:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UsuarioEntity paciente = (UsuarioEntity) request.getAttribute("paciente");
%>

<div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby=""
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" style="text-align: center">Paciente</h4>
            </div>
            <div class="modal-body" style="text-align: center;">

                <h4 style="margin-bottom: 0;">
                    <% if (paciente != null) { %>
                    <%=paciente.getNombres() + " " + paciente.getApellidos()%>
                    <% } else { %>
                    <%="El paciente no existe"%>
                    <% } %>
                </h4>
            </div>
            <div class="modal-footer">
                <% if (paciente != null) { %>
                <button id="aceptar" type="button" class="btn btn-primary" data-dismiss="modal">Aceptar</button>
                <% } else { %>
                <button id="creaPaciente" type="button" class="btn btn-primary">Crear paciente</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                <% } %>
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

    $('#creaPaciente').click(function () {
        document.location.href = '<%=request.getContextPath()%>/paciente/crear';
    });

    <% if(paciente != null) { %>
    $('#nroDocumento').prop('disabled', false);
    $('#fecha').prop('disabled', false);
    $('#horario').prop('disabled', false);
    $('#reservar').prop('disabled', false);
    <% } %>
</script>