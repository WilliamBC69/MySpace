<!DOCTYPE html>

<html>

<head>
  <title>Login</title>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="./css/login.css?${currentTimeMillis}">
  <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
</head>

<body>
  <div class="loginForm">

    <h1>Login</h1>

    <form action="Login" method="post"><!--try to login-->
      <input type="text" id="username" name="username" class="username" placeholder="Username" required />
      <br><br>
      <input type="password" id="password" name="password" class="password" placeholder="Password" required />
      <c:if test="${not empty LoginError}">
        <p class="error">${LoginError}</p>
      </c:if>
      <input type="submit" name="action" class="login" value="Login">
    </form>

    <br />
    
    <form action="register.jsp" method="post"><!--take user to register-->
      <input type="submit" name="action" class="register" value="Register" />
    </form>
  </div>
</body>

</html>