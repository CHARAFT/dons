<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <!-- Mettez ici vos liens vers les feuilles de style, scripts, etc. -->
    <meta charset="UTF-8">
    <title>Inviter Transporteur</title>
</head>
<body>
    <h2>Inviter Transporteur</h2>
<% int eventID = (int) request.getAttribute("eventID"); %>
	<form action="/dons/TransporteurServlet?action=add&id=<%= eventID %>" method="post">
        <!-- Ajoutez des champs pour le nom et l'email -->
        <label for="nom">Nom :</label>
        <input type="text" id="nom" name="nom" required><br>

        <label for="email">Email :</label>
        <input type="email" id="email" name="email" required><br>

        <!-- Ajoutez d'autres champs si nécessaire -->

        <input type="hidden" name="action" value="inviter">
        <input type="submit" value="Inviter">
    </form>
</body>
</html>
