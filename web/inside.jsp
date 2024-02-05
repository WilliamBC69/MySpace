<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <!DOCTYPE html>
  <html lang="en">

  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Inside</title>
    <link rel="stylesheet" href="css/inside.css" />
  </head>

  <body>
    <div class="everything">
      <div class="topMenu">
        <h1 style="margin-right: 20px">${username}</h1>

        <div class="menu">
          <form action="newFriend" method="get" style="margin-right: 5px">
            <input type="text" id="searchuser" name="searchuser" placeholder="Search by name" required />
            <input type="submit" style="width: 20px; height: 22px" />
          </form>

          <form action="Friend" method="get" style="margin-right: 5px">
            <!--go to friend list-->
            <input type="submit" value="Friend" />
          </form>

          <button onclick="window.location.href='index.jsp'">Logout</button>
        </div>
      </div>

      <form class="posting" action="Post" method="post">
        <!--make a new post-->
        <textarea name="content" placeholder="What's on your mind?" style="width: 20%; height: 100px; resize:none"
          required></textarea>
        <input type="submit" value="Post" />
      </form>

      <c:if test="${empty posts}"><!--if theres no post-->
        <p>No posts to display.</p>
      </c:if>
      <c:forEach var="post" items="${posts}"><!--print out all posts in the list-->
        <p>${post.content}</p>
        <p>Posted on: ${post.date}</p>
      </c:forEach>

    </div>
  </body>

  </html>