<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <!-- Les imports nécessaires, les liens vers les feuilles de style, etc. -->
    <title>Ajouter un événement</title>
</head>
<body>
    <h1>Ajouter un événement</h1>
    <form action="/dons/EventServlet?action=ajouter" method="post">
        <!-- Champs de formulaire pour ajouter un événement -->
        <input type="text" name="nom" placeholder="Nom de l'événement" required>
        <input type="text" name="lieu" placeholder="Lieu" required>
        <input type="date" name="debut" placeholder="Date de début" required>
        <input type="date" name="fin" placeholder="Date de fin" required>
        <textarea name="objectif" placeholder="Objectif"></textarea>
        <!-- Ajoutez d'autres champs selon vos besoins -->
        <button type="submit">Ajouter</button>
    </form>
</body>
</html>