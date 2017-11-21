<%@ page import="pe.lizard.citapp.horario.HorarioEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: JosueAngel
  Date: 20/11/2017
  Time: 04:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<HorarioEntity> horarios = (List) request.getAttribute("horarios_disponibles"); %>

<% if (horarios != null || !horarios.isEmpty()) {
    for (HorarioEntity horario : horarios) {
%>
<option value="<%=horario.getId()%>"><%=horario.getDetalle()%>
</option>
<%
    }
} else { %>
<option value="" disabled selected>No hay horarios disponibles</option>
<% } %>