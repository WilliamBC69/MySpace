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
    <form action="Friend" method="get" style="margin-right: 5px">
      <!--go to friend list-->
      <input type="submit" value="Friend" />
    </form>
  </div>

  <div class="home">
    <button onclick="window.location.href='Inside'" style="margin-right: 5px">
      Homepage
    </button>
    <button onclick="window.location.href='index.jsp'">Logout</button>
  </div>
</div>
