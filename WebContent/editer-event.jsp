<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
   
    /* Styles pour le modal */
.modal-content {
    border: none;
    border-radius: 0;
}

.modal-header {
    background-color: #6e98eb;
    color: #fff;
    border-bottom: none;
}

.modal-title {
    color: #fff;
}

.modal-body {
    padding: 20px;
}

.modal-footer {
    border-top: none;
}

/* Styles pour l'historique */
.modal-body h4 {
    margin-bottom: 10px;
    font-weight: bold;
}

.modal-body ul {
    list-style: none;
    padding-left: 0;
}

.modal-body ul li {
    margin-bottom: 5px;
}

</style>
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

    <!-- Les imports nécessaires, les liens vers les feuilles de style, etc. -->
    <title>Éditer un événement</title>
</head>
<body>
    <h1>Éditer un événement</h1>
    <form action="/dons/EventServlet?action=editer" method="post">
        <!-- Champs de formulaire pré-remplis pour éditer un événement existant -->
        <input type="hidden" name="action" value="edit">
        <input type="hidden" name="eventId" value="${event.id}">
        <input type="text" name="nom" value="${event.nom}" required>
        <input type="text" name="lieu" value="${event.lieu}" required>
        <input type="date" name="debut" value="${event.debut}" required>
        <input type="date" name="fin" value="${event.fin}" required>
        <textarea name="objectif">${event.objectif}</textarea>
        <!-- Ajoutez d'autres champs pré-remplis selon vos besoins -->
        <button type="submit">Enregistrer les modifications</button>
    </form>
 <a href="#" class="document-details-button" data-toggle="modal" data-target="#document-modal-${event.id}">Détails<i class="fa-solid fa-eye"></i></a>

                            <!-- Modale -->
                            <div class="modal fade" id="document-modal-${event.id}" tabindex="-1" role="dialog" aria-labelledby="document-modal-label" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered" role="document">
                                <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="document-modal-label">${event.nom}</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Fermer">
                                    <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <p><strong>Nom du document:</strong> ${event.nom}</p>
                                    <p><strong>Date de création:</strong> ${event.nom}</p>
                                    <p><strong>Date de mise à jour:</strong> ${event.nom}</p>
                                    <p><strong>Description:</strong> ${event.nom}</p>
                                    <p><strong>Mots-clés:</strong> ${event.nom}</p>
                                    <p><strong>Fichier:</strong> <a href="/dons/TransporteurServlet?action=edit" target="_blank">Télécharger</a></p>
                                    <h4>Historique du document:</h4>
                                    
                                
                                    <!-- Autres informations du document -->
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                                </div>
                                </div>
                            </div>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.5.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // Wait for the document to be ready
    $(document).ready(function () {
        // Add a click event listener to the document details button
        $('.document-details-button').on('click', function () {
            // Extract the event ID from the button's data-target attribute
            var eventId = $(this).data('target').split('-').pop();

            // Build the modal ID using the extracted event ID
            var modalId = '#document-modal-' + eventId;

            // Show the modal
            $(modalId).modal('show');
        });
    });
</script>
</body>
</html>
 