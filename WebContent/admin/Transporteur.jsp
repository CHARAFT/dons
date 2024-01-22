<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entities.Transporteur" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Boxicons -->
	<link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
	<!-- My CSS -->
	<link rel="stylesheet" href="style.css">
    <!-- Ajoutez les liens vers les feuilles de style, scripts, etc. si nécessaire -->
    <meta charset="UTF-8">
    <link rel="stylesheet" href="admin/style.css">
    <title>Liste des Transporteurs par Événement</title>
</head>
<body>

<%
    HttpSession n = request.getSession();
    Map<Integer, List<Transporteur>> transporteursParEvent = (Map<Integer, List<Transporteur>>) n.getAttribute("transporteursParEvent");

%>
<c:forEach var="entry" items="${transporteursParEvent}">
   
<div class="table-data">
				<div class="order">
					<div class="head">
						 <h2>Événement: ${entry.key.nom}</h2>
						<a href="#" class="document-details-button" data-toggle="modal" data-target="#invite-transporter-modal-${entry.key.id}">
						<i class='bx bx-plus' ></i>
						</a>
						<i class='bx bx-filter' ></i>
					</div>
					<div class="modal fade" id="invite-transporter-modal-${entry.key.id}" tabindex="-1" role="dialog" aria-labelledby="invite-transporter-modal-label-${entry.key}" aria-hidden="true">
					    <div class="modal-dialog modal-dialog-centered" role="document">
					        <div class="modal-content">
					            <div class="modal-header">
					                <h5 class="modal-title">Inviter un transporteur</h5>
					                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					                    <span aria-hidden="true">&times;</span>
					                </button>
					            </div>
					            <div class="modal-body">
					                <!-- Add a form for inviting a transporter -->
					                 <form action="/dons/TransporteursServlet?action=add&id=${entry.key.id}" method="post">
									        <!-- Ajoutez des champs pour le nom et l'email -->
									        <label for="nom">Nom :</label>
									        <input type="text" id="nom" name="nom" required><br>
									
									        <label for="email">Email :</label>
									        <input type="email" id="email" name="email" required><br>
									
									        <!-- Ajoutez d'autres champs si nécessaire -->
									
									        <input type="hidden" name="action" value="inviter">
									        <input type="submit" value="Inviter">
									    </form>     </div>
					            <div class="modal-footer">
					                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
					            </div>
					        </div>
					    </div>
					</div>
					
					
    <table >
        <thead>
            <tr>
                <th>Transporteur ID</th>
                <th>Nom</th>
                <!-- Ajoutez d'autres colonnes si nécessaire -->
            </tr>
        </thead>
        <tbody>
            <c:forEach var="transporteur" items="${entry.value}">
                <tr>
                    <td>${transporteur.id}</td>
                    <td>${transporteur.nom}</td>
                    <td><a href="/dons/TransporteursServlet?action=delete&id=${transporteur.id}" onclick="return confirm('Voulez-vous vraiment supprimer cet transporteur ?')"><i class="fa-solid fa-trash"></i></a>
                    
                   
                   
                </tr>
            </c:forEach>
        </tbody>
    </table>
 </div>
 </div>
</c:forEach>

<!-- Ajoutez des liens de pagination ou d'autres fonctionnalités selon vos besoins -->

</body>
</html>
