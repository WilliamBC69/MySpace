<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Friend</title>
  </head>
  <body>
    <div style="display: flex; align-items: center">

      <h1 style="margin-right: 20px">${username}</h1>

      <jsp:include page="nav.jsp"/>
    </div>

    <h2>Friends</h2>

    <c:if test="${empty friends}"
      ><!--if theres no friend-->
      <p>No friends to display.</p>
    </c:if>
    <c:forEach var="friend" items="${friends}"
      ><!--print out all friends in the list-->
      <a>${friend.friendname}</a>
      <form action="Unfriend" method="post" style="display: inline;">
        <input type="hidden" name="friendname" value="${friend.friendname}"/>
        <input type="submit" value="Unfriend"/>
      </form>
      <br><br>
    </c:forEach>
    
  </body>
</html>
