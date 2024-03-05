<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <!DOCTYPE html>
  <html lang="en">

  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Inside</title>
  </head>

  <body>
    <div class="everything">
      <div style="display: flex; align-items: center">
        <h1 style="margin-right: 20px">${username}</h1>

        <jsp:include page="nav.jsp"/>
      </div>
      <form class="posting" action="Post" method="post">
        <!--make a new post-->
        <textarea name="content" placeholder="What's on your mind?" style="width: 20%; height: 100px; resize:none"
          required></textarea>
        <input type="submit" value="Post" />
      </form>

      <div>
        <c:if test="${empty posts}"><!--if theres no post-->
          <p>No posts to display.</p>
        </c:if>
        <c:forEach var="post" items="${posts}"><!--print out all posts in the list-->
          <div class="post">
              <p>${post.content}</p>
              <p>Posted on: ${post.date}</p>
          </div>
          <br>
        </c:forEach>
      </div>

  </body>

  </html>