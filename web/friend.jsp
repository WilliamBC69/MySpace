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

      <form action="newFriend" method="get" style="margin-right: 5px">
        <!--go to friend list-->
        <input
          type="text"
          id="searchuser"
          name="searchuser"
          placeholder="Search by name"
          required
        />
        <input type="submit" style="width: 20px; height: 22px" />
      </form>

      <button onclick="window.location.href='Inside'" style="margin-right: 5px">
        Go back
      </button>
      <button onclick="window.location.href='index.jsp'">Logout</button>
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
