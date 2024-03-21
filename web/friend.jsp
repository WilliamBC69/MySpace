<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>${username}'s Friend</title>
    <link rel="stylesheet" href="./css/friend.css?${currentTimeMillis}">

  </head>
  <body>
    <div class="topbar">
      <h1 style="margin-right: 20px">${username}</h1>
      <div class="nav">
        <jsp:include page="nav.jsp"/>
      </div>
    </div>

    <div class="allFriends">
      <h2>Friends</h2>
      <div class="container">
        <c:if test="${empty friends}"
        ><!--if theres no friend-->
        <div class="displayFriend">
        <h3>No friends to display.</h3>
        </div>
          </c:if>
          <c:forEach var="friend" items="${friends}"
          ><!--print out all friends in the list-->
          <div class="displayFriend">
        
            <a class="friendname">${friend.friendname}</a>
            <div class="unfriend">
              <form action="Unfriend" method="post" style="display: inline;">
                <input type="hidden" name="friendname" value="${friend.friendname}"/>
                <input type="submit" value="Unfriend"/>
              </form>
            </div>
            <div class="viewfriend">
              <form action="viewProfile" method="get" style="display:inline">
                <input type="hidden" name="friendname" value="${friend.friendname}"/>
                <input type="submit" value="View Profile"/>
              </form>
            </div>
          </div>
            <br><br>
          </c:forEach>
      </div>
    </div>
    
  </body>
</html>
