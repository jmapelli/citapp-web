<%@ page import="pe.lizard.citapp.cita.CitaBuscarServlet" %><%--
  Created by IntelliJ IDEA.
  User: JosueAngel
  Date: 20/11/2017
  Time: 11:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../../template/common/header.jsp" %>
<section id="container">
    <%@ include file="../../template/common/nav.jsp" %>
    <%@ include file="../../template/common/sidebar.jsp" %>

    <section id="main-content">
        <section class="wrapper">

            <div class="row mt">
                <div class="col-md-12">
                    <div class="form-panel">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Buscar</label>
                                <div class="col-sm-10">
                                    <select id="action" class="form-control">
                                        <option value="<%=CitaBuscarServlet.ACTION_FINDBYRESERVA%>">Por reserva</option>
                                        <option value="<%=CitaBuscarServlet.ACTION_FINDBYPACIENTE%>">Por paciente
                                        </option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Valor</label>
                                <div class=" col-sm-10">
                                    <div class="input-group">
                                        <input id="valor" type="text"
                                               class="form-control col-sm-10">
                                        <span class="input-group-btn">
                                            <button id="buscar" class="btn btn-primary" type="button">
                                                <i class="fa fa-search"></i>
                                            </button>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <br>
                        <div style="overflow-x: auto">
                            <table class="table table-striped table-advance table-hover">
                                <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Nombres y apellidos</th>
                                    <th>Fecha cita</th>
                                    <th>Horario</th>
                                </tr>
                                </thead>
                                <tbody id="buscar_citas_result">
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

<script>
    $(function () {
        $('#buscar').click(function (e) {
            e.preventDefault();

            $.get('buscar', {
                    action: $('#action').val(),
                    valor: $('#valor').val()
                }, function (response) {
                    $('#buscar_citas_result').html(response);
                }
            );
        });
    });
</script>