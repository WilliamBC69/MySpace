<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
      <meta charset="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <title>${username}'s Potential Friends</title>
      <link rel="stylesheet" href="./css/newfriend.css?${currentTimeMillis}">

    </head>

    <body>
      <div class="topbar">
        <h1>${username}</h1>
        <div class="nav">
          <jsp:include page="nav.jsp"/>
        </div>
      </div>
      <div class="displayNewFriendWrapper">
        <h2>New Friends</h2>
        <div class="container">
          <c:if test="${empty newfriends}">
            <!--if theres no friend-->
            <h3>No potential friends.</h3>
          </c:if>
          <c:forEach var="newfriend" items="${newfriends}">
            <div class="displayNewFriend">
              <p>${newfriend.newfriendname}</p>
              <form action="Addfriend" method="post">
                <input type="hidden" name="friendname" value="${newfriend.newfriendname}" />
                <input type="submit" value="Add Friend" />
              </form>
            </div>
          </c:forEach>
        </div>
      </div>
    </body>

    </html>