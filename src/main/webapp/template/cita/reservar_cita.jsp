<%@ page import="pe.lizard.citapp.util.DateUtil" %>
<%@ page import="java.util.Date" %>
<%@ page import="pe.lizard.citapp.cita.CitaReservarServlet" %>
<%--
  Created by IntelliJ IDEA.
  User: JosueAngel
  Date: 19/11/2017
  Time: 03:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String nroDocumento = (String) request.getParameter("nroDocumento");
    if (nroDocumento == null) {
        nroDocumento = "";
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
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Nro. documento</label>
                                <div class=" col-sm-10">
                                    <div class="input-group">
                                        <input id="nroDocumento" name="nroDocumento" type="text"
                                               class="form-control col-sm-10" value="<%=nroDocumento%>"
                                               placeholder="Nro. documento">
                                        <span class="input-group-btn">
                                            <button id="buscar" class="btn btn-primary" type="button">
                                                <i class="fa fa-search"></i>
                                            </button>
                                        </span>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Fecha</label>
                                <div class="col-sm-10">
                                    <input id="fecha" name="fecha" type="date" class="form-control" disabled>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Hora</label>
                                <div class="col-sm-10">
                                    <select id="horario" name="horario" class="form-control" disabled> </select>
                                </div>
                            </div>

                            <div style="padding-top: 10px; text-align: right; border-top: 1px solid #e5e5e5;">
                                <div id="reservar_result"></div>
                                <button id="cancelar" type="button" class="btn btn-default">Cancelar</button>
                                <button id="reservar" type="button" class="btn btn-primary" disabled>Reservar</button>
                            </div>
                        </form>
                    </div>

                </div>
            </div>

        </section>
    </section>
</section>
<%@ include file="../common/footer.jsp" %>

<div id="buscar_documento_result"></div>

<script>
    $(function () {
        $('#buscar').click(function (e) {
            e.preventDefault();

            $.get('reservar', {
                    action: '<%=CitaReservarServlet.ACTION_BUSCAR_DOCUMENTO%>',
                    nroDocumento: $('#nroDocumento').val()
                }, function (response) {
                    $('#buscar_documento_result').html(response);
                }
            );
        });

        $('#fecha').change(function (e) {
            e.preventDefault();

            $.get('reservar', {
                action: '<%=CitaReservarServlet.ACTION_BUSCAR_HORARIO%>',
                fecha: e.target.value
            }, function (response) {
                $('#horario').html(response);
            });
        });

        $('#reservar').click(function (e) {
            e.preventDefault();

            $.post('reservar', {
                nroDocumento: $('#nroDocumento').val(),
                fecha: $('#fecha').val(),
                horario: $('#horario').val()
            }, function (response) {
                $('#reservar_result').html(response);
            });
        });

        $('#cancelar').click(function () {
            document.location.href = '<%=request.getContextPath()%>';
        });
    });
</script>