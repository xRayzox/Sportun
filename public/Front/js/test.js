

function initMap(){
  $(document).ready(function(){   
    $("#loadmap").on("click", function(event){  
       $.ajax({  
        url:        '/map',  
        type:       'POST',   
        dataType:   'json',  
        async:      true,  
        
        success: function(data, status) {  
         /*var e = $('<tr><th>Name</th><th>lat</th><th>lng</th><th>content</th></tr>');  
         $('#map').html('');  
         $('#map').append(e);  
         */
         /*for(i = 0; i < data.length; i++) {  
          student = data[i];  
          /*var e = $('<tr><td id = "name"></td><td id = "lat"></td><td id = "lng"></td><td id = "content"></td></tr>');
          
          $('#name', e).html(student['name']);  
          $('#lat', e).html(student['lat']);  
          $('#lng', e).html(student['lng']);  
          $('#content', e).html(student['content']);  
          $('#student').append(e); 

         }  */
         alert(data[0].name);
         alert(data[0].lat);
         alert(data[0].lng);
         alert(data[0].content);
         
        },  
        error : function(xhr, textStatus, errorThrown) {  
         alert('Ajax request failed.');  
         
        }  
       });  
    });  
   });  
  
    // Map options
    var options = {
      zoom:12,
      center:{lat:36.861268,lng:10.200057}
    }

    // New map
    var map = new google.maps.Map(document.getElementById('map'), options);
   

    // Listen for click on map
    //google.maps.event.addListener(map, 'click', function(event){
      // Add marker
     // addMarker({coords:event.latLng});
   // });

    /*
    // Add marker
    var marker = new google.maps.Marker({
      position:{lat:42.4668,lng:-70.9495},
      map:map,
      icon:'https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png'
    });

    var infoWindow = new google.maps.InfoWindow({
      content:'<h1>Lynn MA</h1>'
    });

    marker.addListener('click', function(){
      infoWindow.open(map, marker);
    });
    */

    // Array of markers
   /* var markers = [
      {
        coords:{lat:36.859320,lng:10.196560},
        iconImage:'https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png',
        content:'<h1 style="color:blue;">Lynn MA</h1>'
      },
      {
        coords:{lat:36.856127,lng:10.190576},
        content:'<h1 style="color:blue;">Amesbury MA</h1>'
      },
      {
        coords:{lat:data[0].lat,lng:data[0].lng}
      }
    ];*/
 // Add multiple markers to map
 var infoWindow = new google.maps.InfoWindow(), marker, i;
  // Place each marker on the map  
    for( i = 0; i < data.length; i++ ) {
      var position = new google.maps.LatLng(data[i][1], data[i][2]);
      bounds.extend(position);
      marker = new google.maps.Marker({
          position: position,
          map: map,
          title: data[i][0]
      });}
         // Add info window to marker    
         google.maps.event.addListener(marker, 'click', (function(marker, i) {
          return function() {
              infoWindow.setContent(data[i][3]);
              infoWindow.open(map, marker);
          }
      })(marker, i));
  }


    // Loop through markers
    for(var i = 0;i < markers.length;i++){
      
      // Add marker
      addMarker(markers[i]);
    }


    // Add Marker Function
    function addMarker(props){
      var position = new google.maps.LatLng(props[1], props[2]);
      bounds.extend(position);
      var marker = new google.maps.Marker({
        position:position,
        map:map,
        //icon:props.iconImage
      });

      // Check for customicon
      if(props.iconImage){
        // Set icon image
        marker.setIcon(props.iconImage);
      }

      // Check content
      if(props.content){
        var infoWindow = new google.maps.InfoWindow({
          content:props.content
        });

        marker.addListener('click', function(){
          infoWindow.open(map, marker);
        });
      }
    }
