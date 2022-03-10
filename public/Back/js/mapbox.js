mapboxgl.accessToken = 'pk.eyJ1IjoiZGFiaWl4IiwiYSI6ImNrem9ncGwyNjAwY2oydW9jYmI5bmxpOHYifQ.SHMyeNSFCyhT-EnFPKSJPw';
mapboxgl.setRTLTextPlugin(
    'https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-rtl-text/v0.2.3/mapbox-gl-rtl-text.js',
    null,
    true // Lazy load the plugin
    );
var map = new mapboxgl.Map({
    container: 'map', // container ID
    style: 'mapbox://styles/mapbox/streets-v9', // style URL
    center: [10.244055, 36.693474], // starting position [lng, lat]
    zoom: 9 // starting zoom
    });
    // Add Full.
    map.addControl(new mapboxgl.FullscreenControl());
    map.addControl(new mapboxgl.NavigationControl());
    // Add geolocate control to the map.
map.addControl(
    new mapboxgl.GeolocateControl({
    positionOptions: {
    enableHighAccuracy: true
    },
    // When active the map will receive updates to the device's location as it changes.
    trackUserLocation: true,
    // Draw an arrow next to the location dot to indicate which direction the device is heading.
    showUserHeading: true
    })
    );
    
    map.on('style.load', function() {
        map.on('click', function(e) {
        
          var coordinates = e.lngLat;
          new mapboxgl.Popup()
            .setLngLat(coordinates)
            .setHTML('you clicked here: <br/>' + coordinates)
            .addTo(map);
            document.getElementById('lat').value = coordinates.lat;
            document.getElementById('lng').value = coordinates.lng;
            
                $.get(
          "https://api.mapbox.com/geocoding/v5/mapbox.places/"+coordinates.lng+","+coordinates.lat+".json?types=poi&access_token=pk.eyJ1IjoiZGFiaWl4IiwiYSI6ImNrem9ncGwyNjAwY2oydW9jYmI5bmxpOHYifQ.SHMyeNSFCyhT-EnFPKSJPw",
          function(data) {
            console.log(data.features[0].place_name);
            document.getElementById('region').value = data.features[0].place_name;
          }
          
          
        ).fail(function(jqXHR, textStatus, errorThrown) {
          alert("There was an error while geocoding: " + errorThrown);
        });
         
        });
      });
      
    
     
