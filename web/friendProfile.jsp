<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>${friendname}'s Profile</title>
    <link rel="stylesheet" href="./css/friendProfile.css?${currentTimeMillis}">

  </head>
  <body>
    <div class="topbar">
      <h1 style="margin-right: 20px">${username}</h1>
      <div class="nav">
        <jsp:include page="nav.jsp"/>
      </div>
    </div>
    
    <div class="friendProfile">
        <h2>${friendname}'s Profile</h2>
        <div class="container">
          <div class="posts">
              <c:if test="${empty posts}"><!--if theres no post-->
                <p>No posts to display.</p>
              </c:if>
              <c:forEach var="post" items="${posts}"><!--print out all posts in the list-->
                <div class="post">
                    <p>${post.content}</p>
                    <div class="post-info">
                      <a class="post-date">Posted on: ${post.date}</a>
                      <div class="like-info">
                        <form action="Likes" method="post">
                          <input type="hidden" name="location" value="friendInside">
                          <input type="hidden" name="friendname" value="${friendname}">
                          <input type="hidden" name="postID" value="${post.postID}">
                          <input type="image" src="./css/icon/like.png" style="margin-right: 5px;">
                        </form>
                        <a class="likes">${post.likes}</a>
                      </div>
                    </div>
                </div>
                <br>
              </c:forEach>
            </div>
        </div>
    </div>
  </body>
</html>
