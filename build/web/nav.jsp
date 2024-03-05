<head>
    <link rel="stylesheet"href="./css/nav.css">
</head>
<div class="nav">
    <div>
        <form action="newFriend" method="get" style="margin-right: 5px">
        <!--go to potential friend list-->
        <input type="text" id="searchuser" name="searchuser" placeholder="Search by name" required />
        <input type="submit" value="Search" />
          </form>
    </div>

      <div>
        <form action="Friend" method="get" style="margin-right: 5px">
          <!--go to friend list-->
          <input type="submit" value="Friend" />
        </form>
      </div>
  </div>

  <div>
    <form action="Message" method="get" style="margin-right: 5px">
      <!--go to message list-->
      <input type="submit" value="Message" />
    </form>
  </div>

  <div>
      <button onclick="window.location.href='Inside'" style="margin-right: 5px">
        Homepage
      </button>
      <button onclick="window.location.href='index.jsp'">Logout</button>
  </div>
</div>