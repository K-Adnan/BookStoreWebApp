<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="PageDirectives.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/Home.css" rel="stylesheet" type="text/css">
<style type="text/css">
body {
	background-color: #1A1A1A;
	background-repeat: repeat-x;
}
</style>
</head>
<body>
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
    <div class="mainContent">
      	  <div class="message">${message}</div>
      <div class="bookDetails">
          <div class="leftDiv">
          		<div class="bookImage"><img src="${book.imageUrl}"></div>
				<div class="price">Price : <fmt:formatNumber value = "${book.price}" type = "currency"/></div>
				${editMessage}
				<form class="cartForm" action="addBookToBasket?isbn=${book.isbn}" method="post">
					<input name="quantity" size="1" value="1"> <input type="Submit"
					value="Add to Cart" />
				</form>
      	  </div>
      	  <div class="rightDiv">
          		<div class="bookTitle"><h3>${book.title}</h3> <div class="year">(${book.releaseYear})</div> </div>
          <c:forEach items="${book.authors}" var="a">
			<div class="authorBook">By: <a href="viewBooksByAuthor?author=${a.emailAddress}">${a.firstName} ${a.lastName}, </a></div>
		  </c:forEach>
			<div class="rating"><strong><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${book.avgCustomerRating}" />/5</strong> (${book.numberOfReviews} Reviews)</div>
			<div class="bookDetails">
				<h4>Summary</h4>
				<p class="summary">${book.summary}</p>
				<p>Pages : ${book.numberOfPages}</p>
				ISBN : ${book.isbn}<br/>
				<p class="reviewForm">Add Customer Rating</p>
				<form action="rateBook?isbn=${book.isbn}" method="post">
					<select name="rating">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					</select> <input type="submit" value="Rate">
				</form>
			</div>
      	  </div>
      </div>
    </div>
  </div>
  <footer> 
    <!-- This is the footer with default 3 divs -->
    <div><span style="line-height: 5px"> <p><strong>Books4u</strong></p>
      <p>Cottons Centre</p>
      <p> Cottons Lane</p>
      <p> London SE1 2QG</p></span>
    </div>
    <div id="2col">
    	<span style="line-height: 10px"><p>Tel: 020 3141 5926</p> 
    	<p>Email: info@books4u.com</p></span>
    </div>
    <div>
      <span style="line-height: 10px"><p>&copy; 2017 Kamran Ahmed Adnan</p>
      <p>All Rights Reserved</p></span>
    </div>
  </footer>
</div>
</body>
</html>