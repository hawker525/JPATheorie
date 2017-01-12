<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@taglib uri='http://vdab.be/tags' prefix='v' %>
<!doctype html>
<html lang='nl'>
<head>
    <v:head title='${empty docent ? "Docent zoeken" : docent.naam}'/>
</head>
<body>
<v:menu/>
<h1>Docent zoeken</h1>
<form>
    <label>Nummer:<span>${fouten.id}</span>
        <input name='id' value='${param.id}'
               required autofocus type='number' min='1'></label>
    <input type='submit' value='Zoeken'>
</form>
<h2>Acties</h2>
<c:url value='/docenten/verwijderen.htm' var='verwijderURL'>
    <c:param name='id' value='${docent.id}'/>
</c:url>
<form action='${verwijderURL}' method='post'>
    <input type='submit' value='Verwijderen'>
</form>
<c:if test='${not empty param and empty fouten and empty docent}'>
    Docent niet gevonden
</c:if>
<c:if test='${not empty docent}'>
    ${docent.naam}, wedde: &euro; <fmt:formatNumber value='${docent.wedde}'/>, ${docent.geslacht == 'MAN' ? '&#x2642;' : '&#x2640;'}
</c:if>
</body>
</html>