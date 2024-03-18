<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="posts">
    <c:if test="${empty posts}"><!--if theres no post-->
      <h3>No posts to display.</h3>
    </c:if>
    <c:forEach var="post" items="${posts}"><!--print out all posts in the list-->
      <div class="post">
          <p>${post.content}</p>
          <div class="post-info">
            <a>Posted on: ${post.date}</a>
            <a class="likes">Likes: ${post.likes}</a>
          </div>
      </div>
      <br>
    </c:forEach>
  </div>