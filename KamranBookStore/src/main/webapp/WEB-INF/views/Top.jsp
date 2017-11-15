<div id="mainWrapper">
  <header> 
    <!-- This is the header content. It contains Logo and links -->
    <div id="logo"> 
      <!-- Company Logo text --> 
      <a href="home"> <img src="https://s3.amazonaws.com/media.prestontrail.org/craft/The-Bookstore-Online-Logo-600x.png?mtime=20160930115548" width="248" height="134" alt=""/> </a></div>
    <div id="headerLinks"><a href="logout" title="Logout">Logout</a><a href="viewCart" title="Cart">Cart</a></div>
    <div id="search">
    <form action="viewBooks" method="get">
    <input type="text" size="40" name="searchitem" placeholder="Search for books..." type="search"; style="font-size: 14pt; margin-left: 20px; float: none; opacity: 1; position: relative; bottom: 18px">
   	  <input id="searchButton" type="submit" value="Search"/>
   	 </form>
    </div>
    <div id="nav">
		  <ul>
		  <li><a href="home">Home</a>
		  <li><a href="../viewPersonalDetails">Profile</a>
				<ul>
			        <li><a href="viewPersonalDetails">View Personal Details</a></li>
			        <li><a href="changePassword">Change Password</a></li>
			        <li><a href="viewOrders">View Order History</a></li>
			        ${authorOptions}
	         	</ul>
		    </li>
		    ${adminOptions}
		    <li><a href="viewAllBooks">Books</a>
				<ul>
			        <li><a href="viewAllBooks">View All Books</a></li>
			        <li><a href="searchBook">Advanced Search for Books</a></li>
	         	</ul>
		    </li>
		    <li><a href="help">Help</a>
	        </li>
		    <li><a href="contact">Contact Us</a>
	      </ul>
    </div>
  </header>
  <div id="content">
    <nav class="sidebar"> 
      <div id="menubar">
      	<h1>Categories</h1>
      	<div class="menu">
          <ul>
					<li><a href="viewBiographyBooks">Biography</a></li>
                	<li><a href="viewFictionBooks">Fiction</a></li>
                	<li><a href="viewTechnologyBooks">Technology</a></li>
                	<li><a href="viewTravelBooks">Travel</a></li>
          </ul>
        </div>
      </div>
    </nav>