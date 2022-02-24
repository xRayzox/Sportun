L.mapbox.accessToken = 'pk.eyJ1IjoiZGFiaWl4IiwiYSI6ImNrem9ncGwyNjAwY2oydW9jYmI5bmxpOHYifQ.SHMyeNSFCyhT-EnFPKSJPw';
const mapLeaflet = L.mapbox.map('map')
    .setView([36.8, 10.200057],12)
    .addLayer(L.mapbox.styleLayer('mapbox://styles/mapbox/streets-v11'));
   
    $(document).ready(function(){   
        $.ajax({  
         url:        '/map',  
         type:       'POST',   
         dataType:   'json',  
         async:      false,  
         
         success: function(data, status) {  
            var markers = data;
            for (i = 0; i < markers.length; i++) {
                var datav = markers[i]
      
        var myLatlng = new google.maps.LatLng(datav.lat, datav.lng);
        
        new mapboxgl.Marker()
        .setLngLat(myLatlng)
        .setPopup(
        new mapboxgl.Popup({ offset: 25 }) // add popups
        )
        .addTo(mapLeaflet);
        }
    },  
          
    error : function(xhr, textStatus, errorThrown) {  
     alert('Ajax request failed.');  
     
    }  
   });  
});  
