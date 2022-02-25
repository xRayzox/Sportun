$.ajax({
    type: "POST",
    url: "/commentaire/new",
    data: {
        //tu mets ton data ici
    },
    dataType: "json",
    success: function(response) {
        //ici tu mets la nouvelle liste du commentaire
//exemple quand tu veux afficher ajouter le commentaire dans la nouvelle liste tu peux utiliser document.getElementById('listecommentaire').innerHTML += le nouveau commentaire
    }
});
