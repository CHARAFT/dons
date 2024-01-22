<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <!-- Les imports nécessaires, les liens vers les feuilles de style, etc. -->
    <title>Consulter un événement</title>
</head>
<body>
    <h1>Consulter un événement</h1>
    <!-- Affichez les détails de l'événement selon vos besoins -->
    <p>Nom: ${event.nom}</p>
    <p>Lieu: ${event.lieu}</p>
    <p>Date de début: ${event.debut}</p>
    <p>Date de fin: ${event.fin}</p>
    <p>Objectif: ${event.objectif}</p>
    <!-- Ajoutez d'autres champs selon vos besoins -->
</body>
</html>
