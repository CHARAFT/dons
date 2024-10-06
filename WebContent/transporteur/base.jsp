<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

	<!-- Boxicons -->
	<link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
	<!-- My CSS -->
	<link rel="stylesheet" href="/dons/admin/style.css">

	<title>Dashboard</title>
</head>
<body>

<%
    HttpSession sessio = request.getSession();
    if (!sessio.isNew()) {
        int user_id = (int) sessio.getAttribute("user_id");
             
    }
   
%>
	<!-- SIDEBAR -->
	<section id="sidebar">
		<a href="#" class="brand">
						<img src="/dons/admin/images/logo1.png" style="height:40px; wieght:40px'"/>

			<span class="text">GivingLink</span>
		</a>
		<ul class="side-menu top">
			<li class="<%= (request.getParameter("action") == null) ? "active" : "" %>">
				<a href="/dons/transporteur/base.jsp">
					<i class='bx bxs-dashboard' ></i>
					<span class="text">Dashboard</span>
				</a>
			</li>
			<li class="<%= (request.getParameter("action") != null && request.getParameter("action").equals("evt")) ? "active" : "" %>">
				<a href="/dons/EventServlet?action=evt">
					<i class="fa fa-solid fa-calendar-check"></i>
					<!-- <i class='bx bxs-calendar-check' ></i> -->
					<span class="text"> Events</span>
				</a>
			<li class="<%= (request.getParameter("action") != null && request.getParameter("action").equals("listdons")) ? "active" : "" %>">
				<a href="/dons/DonServlet?action=listdons&id=${user_id}">
					<i class="fa fa-solid fa-hand-holding-medical"></i>
					<!-- <i class='bx bxs-group' ></i> -->
					<span class="text">Dons</span>
				</a>
			</li>
		</ul>
		<ul class="side-menu">
			<li>
				<a href="#">
					<i class='bx bxs-cog' ></i>
					<span class="text">Settings</span>
				</a>
			</li>
			<li>
				<a href="/dons/LoginServlet?action=logout" class="logout">
				
					<i class='bx bxs-log-out-circle' ></i>
					<span class="text">Logout</span>
				</a>
			</li>
		</ul>
	</section>
	<!-- SIDEBAR -->



	<!-- CONTENT -->
	<section id="content">
		<!-- NAVBAR -->
		<nav>
			<i class='bx bx-menu' ></i>
			<a href="#" class="nav-link">Categories</a>
			
			<form action="#">
				<div class="form-input">
					<input type="search" placeholder="Search...">
					<button type="submit" class="search-btn"><i class='bx bx-search' ></i></button>
				</div>
			</form>
			<input type="checkbox" id="switch-mode" hidden>
			<label for="switch-mode" class="switch-mode"></label>
			<a href="#" class="notification">
				<i class='bx bxs-bell' ></i>
				<span class="num">8</span>
			</a>
			<a href="#" class="profile">
				<img src="images/logo1.png">
			</a>
		</nav>
		<!-- NAVBAR -->

		<!-- MAIN -->
		<main>
			<div class="head-title">
				<div class="left">
					
					<ul class="breadcrumb">
						<li>
							<a href="#">Dashboard</a>
						</li>
						<li><i class='bx bx-chevron-right' ></i></li>
						<li>
							<a class="active" href="#">
							 <% String action = request.getParameter("action");
					           if ("evt".equals(action)) { %>
					               Events
					        <% } else if ("listdem".equals(action)) { %>
					               Demandes
					        <% } else if ("listdona".equals(action)) { %>
					               Donateurs
					        <% } else if ("listtr".equals(action)) { %>
					               Agences de transporteur
					        <% } else if ("listdons".equals(action)) { %>
					               Dons
					        <% } else { %>
					               Home
					        <% } %>
							</a>
						</li>
					</ul>
				</div>
				
			</div>
<% if (action==null){ %>
			<ul class="box-info">
				<li>
					<i class='bx bxs-calendar-check' ></i>
					<span class="text">
						<h3>10</h3>
						<p> Current Events</p>
					</span>
				</li>
				<li>
					<i class='bx bxs-group' ></i>
					<span class="text">
						<h3>28</h3>
						<p>Demandes</p>
					</span>
				</li>
				<li>
					<i class='bx bxs-dollar-circle' ></i>
					<span class="text">
						<h3>254</h3>
						<p>Donateurs</p>
					</span>
				</li>
			</ul>
			<%} %>
     <%  if ("evt".equals(action)) {  %>
			<%@ include file="eventlist.jsp" %>
			<% } %>
			<%  if ("listdons".equals(action)) {  %>
			<%@ include file="dons.jsp" %>
			<% } %>
		</main>
		<!-- MAIN -->
	</section>
	<!-- CONTENT -->
	

	<script src="/dons/admin/script.js"></script>
	<script src="https://kit.fontawesome.com/68ee66ea75.js" crossorigin="anonymous"></script>

</body>
</html>