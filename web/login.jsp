<!DOCTYPE html>

<html>
  <head>
    <title>Login</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="./css/login.css?${currentTimeMillis}" />
    <meta
      http-equiv="Cache-Control"
      content="no-cache, no-store, must-revalidate"
    />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
  </head>

  <body>
    <div class="loginForm">

    <h1>Login</h1>

    <form class="actualForm" action="Login" method="post">
      <input type="text" id="username" name="username" class="username" placeholder="Username" required />
      <br><br>
      <input type="password" id="password" name="password" class="password" placeholder="Password" required />
      <c:if test="${not empty LoginError}">
        <p class="error">${LoginError}</p>
      </c:if>
      <input type="submit" name="action" class="choice1" value="Login">
    </form>
    <form action="register.jsp" method="post">
      <a style="color:rgb(81, 106, 147)" href="register.jsp" class="choice2">Don't have an account?</a>
    </form>
  </div>
  </body>
</html>
