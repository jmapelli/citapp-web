<%--
  Created by IntelliJ IDEA.
  User: JosueAngel
  Date: 19/11/2017
  Time: 03:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                                <label class="col-sm-2 col-sm-2 control-label">Nombres</label>
                                <div class="col-sm-10">
                                    <input id="nombres" name="nombres" type="text" class="form-control">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Apellidos</label>
                                <div class="col-sm-10">
                                    <input id="apellidos" name="apellidos" type="text" class="form-control">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Correo electrónico</label>
                                <div class="col-sm-10">
                                    <input id="correo" name="correo" type="email" class="form-control">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Nro. documento</label>
                                <div class="col-sm-10">
                                    <input id="nroDocumento" name="nroDocumento" type="text" class="form-control">
                                </div>
                            </div>

                            <div style="padding-top: 10px; text-align: right; border-top: 1px solid #e5e5e5;">
                                <div id="crear_result"></div>
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

<script>
    $(function () {
        $('#crear').click(function (e) {
            e.preventDefault();

            $.post('crear', {
                nombres: $('#nombres').val(),
                apellidos: $('#apellidos').val(),
                correo: $('#correo').val(),
                nroDocumento: $('#nroDocumento').val()
            }, function (response) {
                $('#crear_result').html(response);
            });
        });

        $('#cancelar').click(function () {
            document.location.href = '<%=request.getContextPath()%>';
        });
    });
</script>

