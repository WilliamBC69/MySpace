<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <!DOCTYPE html>
  <html lang="en">

  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Register</title>
    <link rel="stylesheet" href="css/register.css?${currentTimeMillis}" />
  </head>

  <body>
    <div class="loginForm">
      <h1>Register</h1>
      
      <form class="actualForm" action="Register" method="post">
        <!--try to register-->
        <input type="text" id="username" name="username" class="username" placeholder="Username" required />
        <br /><br />
        <input type="password" id="password" name="password" class="password" placeholder="Password" required />
        <c:choose>
          <c:when test="${empty RegisterError and empty RegisterSuccess}">
            <br><br>
          </c:when>
          <c:otherwise>
            <c:if test="${not empty RegisterError}">
              <p class="error">${RegisterError}</p>
            </c:if>
            <c:if test="${not empty RegisterSuccess}">
              <p class="success">${RegisterSuccess}</p>
              <script>
                setTimeout(function () {
                  window.location.href = 'login.jsp';
                }, 700); 
              </script>
            </c:if>
          </c:otherwise>
        </c:choose>
        <input type="submit" class="choice1" value="Register" />
      </form>
      <a style="color:rgb(81, 106, 147)" href="index.jsp" class="choice2">Already have an account?</a>
<!--take user back to login page-->
    </div>
  </body>

  </html>