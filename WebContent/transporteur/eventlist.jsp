<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
    
<%@ page import="DAO.EventDAO" %>
<%@ page import="entities.Event" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>

<head>
	<link rel="stylesheet" href="style.css">
	
	<script >
	function copyEventLink(blogTitle) {
        // Créez le lien de partage avec le titre et la description
        var blogLink = "https://votre-site.com/blog?title=" + encodeURIComponent(blogTitle) ;

        // Copiez le lien dans le presse-papiers
        navigator.clipboard.writeText(blogLink)
            .then(function() {
                // Affichez le message "lien copié"
                alert("Lien copié : " + blogLink);
            })
            .catch(function(err) {
                console.error('Unable to copy text to clipboard.', err);
            });
    }</script>

    <!-- Other scripts and styles -->



<!-- Boxicons -->
	<link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
	<!-- My CSS -->

<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
<%
    HttpSession sssion = request.getSession();
    List<Event> events = (List<Event>) sssion.getAttribute("events");
%>


    <div class="post">
			<c:forEach items="${events}" var="event">
			<c:set var="imagePath" value="${event.image}" />
	    <div class="post-box ">
            <img src="${pageContext.request.contextPath}${imagePath}"  class="post-img">
            
            <a href="#" class="post-title">${event.nom}</a>
            <span class="post-date"> ${event.debut}...${event.fin}</span>
        
			<div class="profile">
	                <a href="#" class="document-details-button" data-toggle="modal" data-target="#view-modal-${event.id}"><i class="fa-solid fa-eye"></i></a>
	            	<a href="#"><i onclick="copyEventLink('${event.nom}')" class="fa-solid fa-share-nodes"></i></a>
	                		
			</div>
			
        </div>
			
  			
                <div class="modal fade" id="document-modal-${event.id}" tabindex="-1" role="dialog" aria-labelledby="document-modal-label" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered" role="document">
                                <div class="modal-content">
	                                <div class="modal-header">
					                    <h5 class="modal-title">Éditer un événement</h5>
					                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					                        <span aria-hidden="true">&times;</span>
					                    </button>
					            	 </div>
					                <div class="modal-body" id="editModalContent">
					                    <!-- Le formulaire d'édition sera chargé ici -->
					                    <form action="/dons/EventServlet?action=editer" method="post">
									        <!-- Champs de formulaire pré-remplis pour éditer un événement existant -->
									        <input type="hidden" name="action" value="edit">
									        <input type="hidden" name="eventId" value="${event.id}"><br>
									        <input type="text" name="nom" value="${event.nom}" required><br>
									        <input type="text" name="lieu" value="${event.lieu}" required><br>
									        <input type="date" name="debut" value="${event.debut}" required><br>
									        <input type="date" name="fin" value="${event.fin}" required><br>
									        <textarea name="objectif">${event.objectif}</textarea><br>
									        <!-- Ajoutez d'autres champs pré-remplis selon vos besoins -->
									        <button type="submit">Enregistrer les modifications</button>
									    </form>
					                </div>
				                
				                
				                 
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                                </div>
                                </div>
                      </div>
			</div>
			
							<div class="modal fade" id="view-modal-${event.id}" tabindex="-1" role="dialog" aria-labelledby="view-modal-label" aria-hidden="true">
							    <div class="modal-dialog modal-dialog-centered" role="document">
							        <div class="modal-content">
							            <div class="modal-header">
							                <h5 class="modal-title">${event.nom}</h5>
							                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
							                    <span aria-hidden="true">&times;</span>
							                </button>
							            </div>
							            <div class="modal-body">
							            <img src="${pageContext.request.contextPath}${imagePath}" alt="Event Image">
							                <p><strong>Nom:</strong> ${event.nom}</p>
							                <p><strong>Date de début:</strong> ${event.debut}</p>
							                <p><strong>Date de fin:</strong> ${event.fin}</p>
							                <p><strong>Objectif:</strong> ${event.objectif}</p>
							                <p><strong>Lieu:</strong> ${event.lieu}</p>
							                <!-- Add other event details here -->
							            </div>
							            <div class="modal-footer">
							                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
							            </div>
							        </div>
							    </div>
							</div>
						<div class="modal fade" id="add-event-modal" tabindex="-1" role="dialog" aria-labelledby="add-event-modal-label" aria-hidden="true">
						    <div class="modal-dialog modal-dialog-centered" role="document">
						        <div class="modal-content">
						            <div class="modal-header">
						                <h5 class="modal-title">Ajouter un événement</h5>
						                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						                    <span aria-hidden="true">&times;</span>
						                </button>
						            </div>
						            <div class="modal-body">
						                <!-- Add a form for adding a new event -->
						                <form action="/dons/EventServlet?action=ajouter" method="post" enctype="multipart/form-data">
						                    <!-- Add form fields for event details -->
						                    <input type="text" name="nom" placeholder="Nom" required><br>
						                    <input type="text" name="lieu" placeholder="Lieu" required><br>
						                    <input type="date" name="debut" required><br>
						                    <input type="date" name="fin" required><br>
						                    <textarea name="objectif" placeholder="Objectif"></textarea><br>
						                    <label for="image">Choisissez une image :</label>
   											<input type="file" name="image" id="image" accept="image/*">
						                    <!-- Add other form fields as needed -->
						                    <button type="submit">Ajouter</button>
						                </form>
						            </div>
						            <div class="modal-footer">
						                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
						            </div>
						        </div>
						    </div>
						</div>
							

            </c:forEach>		
								                		
				</div>				
						
				

                       

</body>
</html>