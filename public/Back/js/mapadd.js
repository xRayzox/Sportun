function initMap() {
    
        const myLatlng = { lat: 36.856665, lng: 10.189883 };
        const map = new google.maps.Map(document.getElementById("map"), {
          zoom: 12,
          center: myLatlng,
        });
       
        // Create the initial InfoWindow.
        let infoWindow = new google.maps.InfoWindow({
          content: "Click the map to get Lat/Lng!",
          position: myLatlng,
        });
      
        infoWindow.open(map);
        
        // Configure the click listener.
        map.addListener("click", (mapsMouseEvent) => {
          // Close the current InfoWindow.
          infoWindow.close();
         
          
          // Create a new InfoWindow.
          infoWindow = new google.maps.InfoWindow({
            position: mapsMouseEvent.latLng,
            
          });
          var obj = JSON.parse(JSON.stringify(mapsMouseEvent.latLng.toJSON()));
          document.getElementById('lat').value = obj.lat;
          document.getElementById('lng').value = obj.lng;
          console.log(obj.lat);
       
          
          infoWindow.setContent(
            JSON.stringify(mapsMouseEvent.latLng.toJSON())
          );
          
          infoWindow.open(map);
        
          
       
        });
      }
 
      
      