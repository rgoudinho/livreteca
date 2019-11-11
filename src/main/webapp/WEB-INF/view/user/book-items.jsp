<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:template title="Livreteca">
	<jsp:body>
		<h1>Itens Livros</h1>
		<ul class="collection">
			<c:forEach var="bookItem" items="${bookItems}">
  				<li class="collection-item avatar">
  				<span class="title">Livro: ${bookItem.getBook().getName()}</span>
      				<p>
      					<c:if test = "${bookItem.borrowed == true}">
      						Emprestado
      					</c:if>
      					<c:if test = "${bookItem.borrowed == false}">
      						Não emprestado
      					</c:if>
  					</p>	
  					<form action="u/emprestar?id=<c:out value='${bookItem.getId()}'/>" method="post">
  						<input type="submit" class="waves-effect waves-light btn" value="Emprestar">
  					</form>
			</c:forEach>
		</ul>
	</jsp:body>
</t:template>