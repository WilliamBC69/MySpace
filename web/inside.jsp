<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <!DOCTYPE html>
  <html lang="en">

  <head>
    <meta charset="UTF-8" />  
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>${username}'s Homepage</title>
    <link rel="stylesheet" href="./css/inside.css?${currentTimeMillis}">
  </head>

  <body>
    <div class="everything">
      <div class="topbar">
        <h1 style="margin-right: 20px">${username}</h1>
        <div class="nav">
          <jsp:include page="nav.jsp"/>
        </div>
      </div>

      <div class="post-handle">
        <div class="posting">
          <form action="Post" method="post">
            <!--make a new post-->
            <textarea name="content" placeholder="What's on your mind?" required></textarea>
            <input type="submit" value="Post" />
          </form>
        </div>
        <div class="container">
          <div class="posts">
            <c:if test="${empty posts}"><!--if theres no post-->
              <h3>No posts to display.</h3>
            </c:if>
            <c:forEach var="post" items="${posts}"><!--print out all posts in the list-->
              <div class="post">
                  <p>${post.content}</p>
                  <div class="post-info">
                    <a class="post-date">Posted on: ${post.date}</a>
                    <div class="like-info">
                      <form action="DeletePost" method="post">
                        <input type="hidden" name="location" value="inside">
                        <input type="hidden" name="friendname" value="${username}">
                        <input type="hidden" name="postID" value="${post.postID}">
                        <input type="image" src="./css/icon/trash.png" style="margin-right: 5px;">
                      </form>
                      <form action="Likes" method="post">
                        <input type="hidden" name="location" value="inside">
                        <input type="hidden" name="friendname" value="${username}">
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
</div>
  </body>
  </html>