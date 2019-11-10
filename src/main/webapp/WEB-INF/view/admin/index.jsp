<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:template title="Livreteca">
	<jsp:body>
		<h1>Livros</h1>
		<a href="a/adicionar" class="waves-effect waves-light btn">Adicionar</a>
		 <c:if test="${not empty message}">
            <div class="row">
                <div class="col s12">
                    <div class="card-panel red white-text">
						${message}
                    </div>
                </div>
            </div>
        </c:if>
		<ul class="collection">		
			<c:forEach var="book" items="${books}">
  				<li class="collection-item avatar">
  				<span class="title">Livro: ${book.getName()}</span>
     			<p>
     				Escritor: ${book.getWriter()}
     				<br>
        			Quantidade: ${book.getAmount()}
  				</p>	
  				<a href="a/editar?id=<c:out value='${book.getId()}' />" class="waves-effect waves-light btn">Editar</a>
  				<a href="a/excruir?id=<c:out value='${book.getId()}' />" class="waves-effect waves-light btn">Excruir</a>
			</c:forEach>
		</ul>
	</jsp:body>
</t:template>