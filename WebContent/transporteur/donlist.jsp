<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
    
<%@ page import="DAO.DonDAO" %>
<%@ page import="entities.Don" %>
<%@ page import="entities.Transporteur" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
    HttpSession sio = request.getSession();
    List<Don> dons = (List<Don>) sio.getAttribute("dons");
    HttpSession so = request.getSession();
    List<Transporteur> transporteurs = (List<Transporteur>) so.getAttribute("transporteurs");
%>
<div class="table-data">
				<div class="order">
					<div class="head">
						<h3>Recent Dons</h3>
						<i class='bx bx-search' ></i>
						<i class='bx bx-filter' ></i>
					</div>
					<form action="/dons/DonServlet" method="post">
					<table>
						<thead>
						
							<tr>
								<th>Id</th>
								<th>Quantité</th>
					            <th>Nature</th>
					            <th>Ville</th>
					            <th>Adresse</th>
					            <th>Date de Collecte</th>
								
							</tr>
						<c:forEach items="${dons}" var="don">
						<tr class="don-row" data-don-id="${don.id}">
							<td>${don.id}</td>
							<td>${don.quantite}</td>
							<td>${don.nature}</td>
		                    <td>${don.ville}</td>
							<td>${don.address}</td>                    
							<td>${don.date_De_collecte}</td>
							<td><!-- Ajoutez des liens ou des boutons pour les actions -->
				                <a href="#" class="document-details-button" data-toggle="modal" data-target="#view-don-modal-${don.id}"><i class="fa-solid fa-eye"></i></a>
				                				            </td>
				             <td><input type="checkbox" name="selectedDons" value="${don.id}"  class="don-checkbox" id="don-${don.id}">
				             <i class="fa-solid fa-check" style="color: #b9c3d5; display: none;"></i></td>
				               
				            <div class="modal fade" id="view-don-modal-${don.id}" tabindex="-1" role="dialog" aria-labelledby="view-donor-modal-label" aria-hidden="true">
							    <div class="modal-dialog modal-dialog-centered" role="document">
							        <div class="modal-content">
							            <div class="modal-header">
							                <h5 class="modal-title">Don:${don.id}</h5>
							                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
							                    <span aria-hidden="true">&times;</span>
							                </button>
							            </div>
							            <div class="modal-body">
							                <p><strong>Quantite:</strong> ${don.quantite}</p>
							                <p><strong>Nature</strong> ${don.nature}</p>
							                <p><strong>Ville</strong> ${don.ville}</p>
							                <p><strong>Address:</strong> ${don.address}</p>
							                <p><strong>Date de Collecte</strong> ${don.date_De_collecte}</p>
							                
							            </div>
							            <div class="modal-footer">
							                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
							            </div>
							        </div>
							    </div>
							</div>
		                </tr>
                 </c:forEach>
						</thead></tbody>
						</table>
						
						<label for="transporteur">Choisir un transporteur :</label>
					    <select name="transporteur" id="transporteur" required>
					        <c:forEach items="${transporteurs}" var="transporteur">
					            <option value="${transporteur.id}">${transporteur.nom}</option>
					        </c:forEach>
					    </select>
					
					    <input type="hidden" name="action" value="envoyerAuTransporteur">
					    <input type="submit" id="send-to-transporter-button" value="Envoyer au transporteur">
						</form>
<script defer>
    // Ajoutez ce script pour gérer la visibilité du symbole de coche
    document.addEventListener('DOMContentLoaded', function () {
        var checkboxes = document.querySelectorAll('.don-checkbox');

        checkboxes.forEach(function (checkbox) {
            checkbox.addEventListener('change', function () {
                var checkIcon = checkbox.parentElement.querySelector('.fa-check');
                if (checkbox.checked) {
                    checkIcon.style.display = 'inline-block';
                } else {
                    checkIcon.style.display = 'none';
                }
            });
        });
    });
</script>
<script defer >
    document.addEventListener('DOMContentLoaded', function () {
        // Fonction pour obtenir les sélections stockées dans le local storage
        function getStoredSelections() {
            var storedSelections = localStorage.getItem('selectedDons');
            return storedSelections ? JSON.parse(storedSelections) : [];
        }

        // Fonction pour mettre à jour les sélections dans le local storage
        function updateStoredSelections(selectedDons) {
            localStorage.setItem('selectedDons', JSON.stringify(selectedDons));
        }

        // Fonction pour afficher les coches
        function showCheckIcons() {
            var storedSelections = getStoredSelections();

            storedSelections.forEach(function (donId) {
                var checkbox = document.getElementById('don-' + donId);
                var donRow = checkbox.closest('.don-row');
                var checkIcon = donRow.querySelector('.fa-check');

                checkbox.style.display = 'none';
                checkIcon.style.display = 'inline-block';
                checkbox.checked = true;
            });
        }

        // Initialiser les sélections à partir du local storage
        showCheckIcons();

        // Ajouter un écouteur de clic sur le bouton "Envoyer au transporteur"
        var sendButton = document.getElementById('send-to-transporter-button');
        sendButton.addEventListener('click', function () {
            var donCheckboxes = document.querySelectorAll('.don-checkbox');
            donCheckboxes.forEach(function (checkbox) {
                var donId = checkbox.value;
                var donRow = checkbox.closest('.don-row');
                var checkIcon = donRow.querySelector('.fa-check');

                checkbox.style.display = 'none';
                checkIcon.style.display = 'inline-block';
                checkbox.checked = true;
            });

            // Mettre à jour les sélections dans le local storage
            var selectedDons = Array.from(donCheckboxes)
                .filter(function (checkbox) {
                    return checkbox.checked;
                })
                .map(function (checkbox) {
                    return checkbox.value;
                });
            updateStoredSelections(selectedDons);
        });
    });
</script>


</body>
</html>