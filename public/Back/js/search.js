$( document ).ready(function() {
    $("#search").keyup(function(e){
        /* La variable value va prendre la valeur insérer dans le champ de texte
        afin d’effectuer la recherche */
        var value = $(this).val();
        /* Ajax est lancé lors du remplissage du champ texte dont l’id est
        « search » pour faire la recherche */
        $.ajax({
            /* l’url est une chaine de caractères contenant l’adresse où la requête est
            envoyée */
            url : "/search",
            /* La méthode utilisée pour transférer les données est GET */
            type : 'GET',
            /*Ici search value va prendre la chaine entrée par un utilisateur dans la
            zone de recherche et sera placée après l’url */
            data: {
                'searchValue' : value
            },
            /*Cette fonction permet de vider le contenu du tableau pour recevoir le
            nouveau contenu*/
            success : function(re){
                if(re){
                    console.log(re);
                    $('#t tbody#search').empty();
                    $.each(JSON.parse(re), function(i,obj) {
                        
                        $('#t tbody#all').hide();
                        $('#t tbody#search').append('<tr> <td>'+obj.id+'</td> <td>'+obj.name+'</td> <td> '+obj.Description  + '</td><td>' +obj.title +' </td> <td>' +obj.Type +' </td>' +  '<td>'+obj.numTel+'</td>'+ '<td><img style="height:20px;width:20px;" src="/upload/images/'+obj.Image+'"></td>'+  '<td>'+obj.Location.lat+'</td>'+'<td>'+obj.Location.lng+'</td>'+'<td>'+obj.Location.region+'</td>'+'<td><a href="/deleteService/'+obj.id+'">Delete</a> <a href="/updateService/'+obj.id+'">Update</a></td>'+
                            '</td> </tr>')
                    });
                }
                else
                {
                    $('#t tbody#all').show();
                    $('#t tbody#search').empty();
                    $('#t tbody#search').fadeIn('fast');
                }
            },
        });
        return false;
    });
});