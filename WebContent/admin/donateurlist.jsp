<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
    
<%@ page import="DAO.DonnateurDAO" %>
<%@ page import="entities.Donateur" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Boxicons -->
	<link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
	<!-- My CSS -->
	<link rel="stylesheet" href="style.css">
<title>Listes des donateurs</title>
</head>
<body>
<%
    HttpSession sion = request.getSession();
    List<Donateur> donateurs = (List<Donateur>) sion.getAttribute("donateurs");
%>

<div class="table-data">
				<div class="order">
					<div class="head">
						<h3>Recent Donateurs</h3>
						<i class='bx bx-search' ></i>
						<i class='bx bx-filter' ></i>
					</div>
					<table>
						<thead>
							<tr>
								<th>Nom</th>
								<th>CIN</th>
								<th>Email</th>
								<th>Addresse</th>
								<th>Numero de téléphone</th>
								<th>Action:</th>
								
							</tr>
						</thead>
						<tbody>
			<c:forEach items="${donateurs}" var="donateur">
                <tr>
					<td>${donateur.nom}</td>
					<td>${donateur.CIN}</td>
                    <td>${donateur.email}</td>
					<td>${donateur.address}</td>                    
					<td>${donateur.num_tel}</td>
					<td>
		                <a href="#" class="document-details-button" data-toggle="modal" data-target="#view-donor-modal-${donateur.id}"><i class="fa-solid fa-eye"></i></a>
		                <a href="/dons/DonateurServlet?id=${donateur.id}&action=supprimer" onclick="return confirm('Voulez-vous vraiment supprimer ce donnateur ?')"><i class="fa-solid fa-trash"></i></a>
		            </td>
                </tr>
                <div class="modal fade" id="view-donor-modal-${donateur.id}" tabindex="-1" role="dialog" aria-labelledby="view-donor-modal-label" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">${donateur.nom}</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p><strong>Nom:</strong> ${donateur.nom}</p>
                <p><strong>Email:</strong> ${donateur.email}</p>
                <p><strong>CIN:</strong> ${donateur.CIN}</p>
                <p><strong>Address:</strong> ${donateur.address}</p>
                <p><strong>Telephone:</strong> ${donateur.num_tel}</p>
                
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
            </div>
        </div>
    </div>
</div>
                
            </c:forEach>		<tr>
								
						</tbody>
					</table>
				</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
				
</body>
</html>