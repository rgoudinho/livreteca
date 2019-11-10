<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template title="Cadastro de Usuário">
    <jsp:body>
        <h1>Adicionar livro</h1>

        <c:if test="${not empty errors}">
            <div class="row">
                <div class="col s12">
                    <div class="card-panel red white-text">
                        <ul>
                            <c:forEach var="error" items="${errors}">
                                <li>${error.message}</li>
                                </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </c:if>

        <form action="a/adicionar" method="POST">
            <div class="row">
                <div class="input-field col s12">
                    <input id="name" type="text" name="name" />
                    <label for="name" class="active">Nome do livro*</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <input id="writer" type="text" name="writer" />
                    <label for="writer" class="active">Escritor*</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <input id="amount" type="number" name="amount" />
                    <label for="amount" class="active">Quantidade*</label>
                </div>
            </div>
            <p>
                <button type="submit" class="waves-effect waves-light btn green darken-3">Enviar</button>
            </p>
        </form>
    </jsp:body>
</t:template>