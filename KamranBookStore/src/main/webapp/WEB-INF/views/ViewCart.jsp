<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/Home.css" rel="stylesheet" type="text/css">
<style type="text/css">
body {
	background-color: #1A1A1A;
	background-image: url();
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
      <h1>Shopping Cart</h1>
      	<c:choose>
	<c:when test="${cart.total > '0'}">
	<c:forEach items="${cart.cartItems}" var="c">
	<table class="cartTable">
		<tr>
		<th class="imgcol"><a href="displayBook?isbn=${c.book.isbn}"><img id="cartImg" src="${c.book.imageUrl}" height="12px" width="30px"></a></th>
		<th>
		<p class="cartText">Title : <a href="displayBook?isbn=${c.book.isbn}">${c.book.title}</a></p>
		<p class="cartText">Price : £${c.book.price}</p>
		<p class="cartText">Subtotal : £${c.subTotal}</p>
		<form class="updateForm" action="updateQuantity?cartItemId=${c.cartItemId}" method="post">
			<input name="quantity" size="1" value="${c.quantity}">
			<input type="Submit" value="Update" />
		</form>
		</th>
		</tr>
	</table>
		<hr>
	</c:forEach>
	<div class="total"><h3>Total : £${cart.total}</h3></div>
	<div class="proceedCheckout"><a href="proceedCheckout?cartId=${cart.cartId}"> Proceed to Checkout </a></div>
    </c:when>
    <c:otherwise>
    <h3>Your Shopping Basket is empty.</h3>
    <p>Your Shopping Basket lives to serve. Give it purpose--fill it with books and see the magic happen.</p>
    </c:otherwise>
</c:choose>
      
        
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