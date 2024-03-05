<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
      <meta charset="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <title>New Friends</title>
    </head>

    <body>
      <div style="display: flex; align-items: center">
        <h1 style="margin-right: 20px">${username}</h1>

        <jsp:include page="nav.jsp"/>
      </div>
      <h2>New Friends</h2>
      <c:if test="${empty newfriends}">
        <!--if theres no friend-->
        <p>No potential friends.</p>
      </c:if>
      <c:forEach var="newfriend" items="${newfriends}">
        <p>${newfriend.newfriendname}</p>
        <form action="Addfriend" method="post">
          <input type="hidden" name="friendname" value="${newfriend.newfriendname}" />
          <input type="submit" value="Add Friend" />
        </form>
      </c:forEach>
    </body>

    </html>