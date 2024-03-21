<head>
  <link rel="stylesheet" href="./css/nav.css?${currentTimeMillis}" />
</head>
<div class="nav">
  <div class="find">
    <form action="newFriend" method="get" style="margin-right: 5px">
      <!--go to potential friend list-->
      <input
        type="text"
        id="searchuser"
        name="searchuser"
        placeholder="Search"
        required
      />
      <input type="submit" value="Search" style="display: none" />
    </form>
  </div>

  <div class="friend">
    <form action="Friend" method="get" >
      <!--go to friend list-->
      <input type="image" src="./css/icon/friends.png" />
    </form>
  </div>

  <form action="Inside" method="get">
    <input type="image" src="./css/icon/home.png">
  </form>
  
  <form action="login.jsp" method="get">
    <input type="image" src="./css/icon/logout.png">
  </form>
  

</div>
