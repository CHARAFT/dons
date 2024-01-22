<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="entities.Demande" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<!DOCTYPE html>
<html>
<head>
    <!-- Ajoutez les liens vers les feuilles de style, scripts, etc. si nécessaire -->
    <meta charset="UTF-8">
    <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
	<!-- My CSS -->
	<link rel="stylesheet" href="admin/style.css">
    <title>Liste des demandes</title>
</head>
<body>
    <%
    HttpSession ssion = request.getSession();
    List<Demande> demandes = (List<Demande>) ssion.getAttribute("demandes");
%>
<div class="table-data">
				<div class="order">
					<div class="head">
						<h3>Recent Orders</h3>
						<i class='bx bx-search' ></i>
						<i class='bx bx-filter' ></i>
					</div>
				    <table >
				        <thead>
				            <tr>
				                <th>ID</th>
				                <th>Objet</th>
				                <th>Description</th>
				                <th>Quantité</th>
				                <th>Nature</th>
				                <th>Date</th>
				                <th> Statut</th>
				                <!-- Ajoutez d'autres colonnes si nécessaire -->
				            </tr>
				        </thead>
				        <tbody>
				            <c:forEach items="${demandes}" var="demande" >
				                <tr>
				                    <td>${demande.id}</td>
				                    <td>${demande.objet}</td>
				                    <td>${demande.desc}</td>
				                    <td>${demande.quantite}</td>
				                    <td>${demande.nature}</td>
				                    <td>${demande.date}</td>
				                    <td> <i class="fa-solid fa-toggle-on" style="color: #24cf02;"></i></td>
				                    <!-- Ajoutez d'autres colonnes si nécessaire -->
				                </tr>
				            </c:forEach>
				        </tbody>
				    </table>
</div>
    <!-- Ajoutez des liens de pagination ou d'autres fonctionnalités selon vos besoins -->

</body>
</html>