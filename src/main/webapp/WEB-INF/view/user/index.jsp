<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:template title="Livreteca">
	<jsp:body>
		<h1>Livros</h1>
		<ul class="collection">
			<c:forEach var="book" items="${books}">
  				<li class="collection-item avatar">
  				<span class="title">Livro: ${book.getName()}</span>
      				<p>
      					Escritor: ${book.getWriter()}
     					<br>
        				Quantidade: ${book.getAmount()}
  					</p>	
  				<a href="u/emprestar" class="waves-effect waves-light btn">Editar</a>
			</c:forEach>
		</ul>
	</jsp:body>
</t:template>