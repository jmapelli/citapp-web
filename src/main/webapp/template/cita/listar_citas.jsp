<%@ page import="java.util.Date" %>
<%@ page import="pe.lizard.citapp.util.DateUtil" %>
<%@ page import="pe.lizard.citapp.cita.CitaListarServlet" %><%--
  Created by IntelliJ IDEA.
  User: JosueAngel
  Date: 23/11/2017
  Time: 02:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String fecha = DateUtil.toString("yyyy-MM-dd", new Date());
%>

<%@ include file="../common/header.jsp" %>
<section id="container">
    <%@ include file="../common/nav.jsp" %>
    <%@ include file="../common/sidebar.jsp" %>

    <section id="main-content">
        <section class="wrapper">

            <div class="row mt">
                <div class="col-lg-12">
                    <section class="task-panel tasks-widget">
                        <div class="panel-heading">
                            <div class="input-group">
                                <span class="input-group-btn">
                                    <button id="preview" class="btn btn-primary" type="button">
                                        <i class="fa fa-chevron-left"></i>
                                    </button>
                                </span>
                                <input id="fecha" type="date" class="form-control"
                                       value="<%=fecha%>" style="text-align: center;">
                                <span class="input-group-btn">
                                    <button id="next" class="btn btn-primary" type="button">
                                        <i class="fa fa-chevron-right"></i>
                                    </button>
                                </span>
                            </div>
                        </div>
                        <div id="listar_citas_result" class="panel-body">
                        </div>
                    </section>
                </div>
            </div>

        </section>
    </section>
</section>
<%@ include file="../common/footer.jsp" %>

<script>
    $(function () {
        cargar_citas($('#fecha').val());

        $('#fecha').change(function (e) {
            e.preventDefault();
            cargar_citas(e.target.value);
        });

        $('#preview').click(function () {
            var fecha = $('#fecha').val();
            fecha = fecha.replace("-", "/");
            fecha = new Date(fecha);
            fecha = sumarDias(-1, fecha);

            $('#fecha').val(fecha);
            cargar_citas(fecha);
        });

        $('#next').click(function () {
            var fecha = $('#fecha').val();
            fecha = fecha.replace("-", "/");
            fecha = new Date(fecha);
            fecha = sumarDias(1, fecha);

            $('#fecha').val(fecha);
            cargar_citas(fecha);
        });

        function cargar_citas(fecha) {
            var params = {};
            params.action = '<%=CitaListarServlet.ACTION_DAILY%>';
            params.fecha = fecha;

            $.get('', params, function (response) {
                    $('#listar_citas_result').html(response);
                }
            );
        }

        function sumarDias(dias, fecha) {
            fecha = fecha.setDate(fecha.getDate() + dias);
            fecha = new Date(fecha).toLocaleDateString();

            if (fecha.length === 9) {
                fecha = '0' + fecha;
            }

            fecha = fecha.split('/').reverse().join('-');

            return fecha;
        }
    });
</script>