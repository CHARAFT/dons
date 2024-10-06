<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
      integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    /><link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/apexcharts@3.28.2/dist/apexcharts.min.css">
<script src="https://cdn.jsdelivr.net/npm/apexcharts@3.28.2/dist/apexcharts.min.js"></script>
	<!-- Boxicons -->
	<link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
	<!-- My CSS -->
	<link rel="stylesheet" href="/dons/admin/style.css">

	<title>Dashboard</title>
</head>
<body>

<%
    List<Event> event = (List<Event>) session.getAttribute("events");
List<Don> don = (List<Don>) session.getAttribute("dons");
List<Demande> demande = (List<Demande>) session.getAttribute("demandes");
List<Donateur> donateur = (List<Donateur>) session.getAttribute("donateurs");

  int eventCount = (event != null) ? event.size() : 0;
   int donCount = (don != null) ? don.size() : 0;
   int demandeCount = (demande != null) ? demande.size() : 0;
   int donateurCount = (donateur != null) ? donateur.size() : 0;
   request.setAttribute("don", don);
%>
		<c:set var="donations" value="${don}" />

	<!-- SIDEBAR -->
	<section id="sidebar">
		<a href="#" class="brand">
			<img src="images/logo1.png" style="height:40px; wieght:40px'"/>
			<span class="text">GivingLink</span>
		</a>
		<ul class="side-menu top">
			<li class="<%= (request.getParameter("action") == null) ? "active" : "" %>">
				<a href="/dons/admin/base.jsp">
					<i class='bx bxs-dashboard' ></i>
					<span class="text">Dashboard</span>
				</a>
			</li>
			<li class="<%= (request.getParameter("action") != null && request.getParameter("action").equals("listev")) ? "active" : "" %>">
				<a href="/dons/EventServlet?action=listev">
					<i class="fa fa-solid fa-calendar-check"></i>
					<!-- <i class='bx bxs-calendar-check' ></i> -->
					<span class="text">Mes Events</span>
				</a>
			</li>
			<li class="<%= (request.getParameter("action") != null && request.getParameter("action").equals("listtr")) ? "active" : "" %>">
				<a href="/dons/TransporteursServlet?action=listtr">
					<i class='bx bxs-truck' ></i>
					<span class="text">Agence de Transport</span>
				</a>
			</li>
			<li class="<%= (request.getParameter("action") != null && request.getParameter("action").equals("listdem")) ? "active" : "" %>">
				<a href="/dons/DemandeServlet?action=listdem">
					<i class='bx bxs-message-dots' ></i>
					<span class="text">Demandes</span>
				</a>
			</li>
			<li class="<%= (request.getParameter("action") != null && request.getParameter("action").equals("listdona")) ? "active" : "" %>">
				<a href="/dons/DonateurServlet?action=listdona">
					<i class="fa fa-solid fa-hands-holding-child"></i>
					<!-- <i class='bx bxs-group' ></i> -->
					<span class="text">Donateurs</span>
				</a>
			</li>
			<li class="<%= (request.getParameter("action") != null && request.getParameter("action").equals("listdon")) ? "active" : "" %>">
				<a href="/dons/DonServlet?action=listdon">
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
							<a href="#">Dashboard </a>
						</li>
						<li><i class='bx bx-chevron-right' ></i></li>
						<li>
							<a class="active" href="#">
							 <% String action = request.getParameter("action");
					           if ("listev".equals(action)) { %>
					               Events
					        <% } else if ("listdem".equals(action)) { %>
					               Demandes
					        <% } else if ("listdona".equals(action)) { %>
					               Donateurs
					        <% } else if ("listtr".equals(action)) { %>
					               Agences de transporteur
					        <% } else if ("listdon".equals(action)) { %>
					               Dons
					        <% } else { %>
					               Home
					        <% } %>
							</a>
						</li>
					</ul>
				</div>
				
			</div>
<%if (action == null){ %>
			<ul class="box-info">
				<li>
					<i class='bx bxs-calendar-check' ></i>
					<span class="text">
						<h3><%= eventCount %></h3>
						<p> Current Events</p>
					</span>
				</li>
				<li>
					<i class='bx bxs-group' ></i>
					<span class="text">
						<h3><%= demandeCount %></h3>
						<p>Demandes</p>
					</span>
				</li>
				<li>
					<i class='bx bxs-dollar-circle' ></i>
					<span class="text">
						<h3><%= donateurCount %></h3>
						<p>Donateurs</p>
					</span>
				</li>
				
				
			</ul>
			<div class="chart">
            	<div id="donationChart"></div>
			</div><%} %>
     <%  if ("listev".equals(action)) {  %>
			<%@ include file="eventslist.jsp" %>
			<% } %>
				<% if ("listdem".equals(action)) {  %>
			<%@ include file="demandelist.jsp" %>
			<% } %>
				<%  if ("listdona".equals(action)) {  %>
			<%@ include file="donateurlist.jsp" %>
			<% } %>
			<%  if ("listtr".equals(action)) {  %>
			<%@ include file="Transporteur.jsp" %>
			<% } %>
			<%  if ("listdon".equals(action)) {  %>
			<%@ include file="donlist.jsp" %>
			<% } %>
		</main>
		<!-- MAIN -->
	</section>
	<!-- CONTENT -->
	

	<script src="script.js"></script>
	<script src="https://kit.fontawesome.com/68ee66ea75.js" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
	
	<script>
    // Extracting donation data for ApexCharts
    var donationData = [];

    <c:forEach var="donation" items="${donations}">
        // Assuming each donation has an 'event_id' property
        donationData.push({
            event: ${donation.event_id},
        });
    </c:forEach>
    // Counting donations per event
    var donationsPerEvent = {};

    donationData.forEach(function (donation) {
        var eventId = donation.event;
        donationsPerEvent[eventId] = (donationsPerEvent[eventId] || 0) + 1;
    });

    // Prepare data for ApexCharts
    var chartData = [];
    Object.keys(donationsPerEvent).forEach(function (eventId) {
        chartData.push({
            x: "Event " + eventId,
            y: donationsPerEvent[eventId]
        });
    });

    // ApexCharts configuration
    var chartOptions = {
        series: [{
            data: chartData
        }],
        chart: {
            height: 350,
            type: 'bar'
        },
        xaxis: {
            type: 'category',
            categories: Object.keys(donationsPerEvent).map(function (eventId) {
                return "Event " + eventId;
            })
        },
        yaxis: {
            title: {
                text: 'Number of Donations'
            }
        }
    };

    // Render ApexCharts
    var donationChart = new ApexCharts(document.querySelector("#donationChart"), chartOptions);
    donationChart.render();
</script>


</body>
</html>