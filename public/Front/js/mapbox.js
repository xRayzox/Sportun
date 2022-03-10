mapboxgl.accessToken = 'pk.eyJ1IjoiZGFiaWl4IiwiYSI6ImNrem9ncGwyNjAwY2oydW9jYmI5bmxpOHYifQ.SHMyeNSFCyhT-EnFPKSJPw';
$(document).ready(function(){   
    $.ajax({  
     url:        '/mapbox',  
     type:       'POST',   
     dataType:   'json',  
     async:      false,  
     
     success: function(data, status) {  
        var markers = data;
        navigator.geolocation.getCurrentPosition(function(position) {
  coordinates = [position.coords.longitude, position.coords.latitude];
  console.log(coordinates);
var map = new mapboxgl.Map({
container: 'mapbox', // container ID
style: 'mapbox://styles/mapbox/streets-v11', // style URL
center: [position.coords.longitude, position.coords.latitude], // starting position [lng, lat]
zoom: 9 // starting zoom
});


mapboxgl.setRTLTextPlugin(
    'https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-rtl-text/v0.2.3/mapbox-gl-rtl-text.js',
    null,
    true // Lazy load the plugin
    );  
// Add Full.
map.addControl(new mapboxgl.FullscreenControl());
map.addControl(new mapboxgl.NavigationControl());
 
// Add geolocate control to the map.
// Initialize the geolocate control.
let geolocate = new mapboxgl.GeolocateControl({
    positionOptions: {
      enableHighAccuracy: true
    },
    trackUserLocation: true
  });
//////////


  // Add the control to the map.
  map.addControl(geolocate);
  geolocate.on('geolocate', function(e) {
      var lon = e.coords.longitude;
      var lat = e.coords.latitude
      var position = [lon, lat];
      console.log(position);
});

  

for (var feature of data) {
    // create a HTML element for each feature
    var el = document.createElement('div');
    el.className = 'marker';
    
    // make a marker for each feature and add it to the map
    new mapboxgl.Marker(el)
    .setLngLat([feature.lng,feature.lat])
    .setPopup(
    new mapboxgl.Popup({ offset: -23 }) // add popups
    .setHTML(
        `<h2 style="color:red;"> ${feature.name} </h2>
        <img style="height:140px;width:600px;" src="/upload/images/${feature.Image}">
        <h3 style="color:black">Type:${feature.Type}</h3>
        <h4 style="color:black">Number phone:${feature.Num_tel}</h4>
        <a class="btn btn-primary" href="/singleServ/${feature.id}">Consulter</a>`
    )
    )
    .addTo(map);
    
}
  


  const start = [position.coords.longitude, position.coords.latitude];
  // create a function to make a directions request
async function getRoute(end) {
    // make a directions request using cycling profile
    // an arbitrary start will always be the same
    // only the end or destination will change
    const query = await fetch(
      `https://api.mapbox.com/directions/v5/mapbox/cycling/${start[0]},${start[1]};${end[0]},${end[1]}?steps=true&geometries=geojson&access_token=${mapboxgl.accessToken}`,
      { method: 'GET' }
    );
    const json = await query.json();
    const data = json.routes[0];
    const route = data.geometry.coordinates;
    const geojson = {
      type: 'Feature',
      properties: {},
      geometry: {
        type: 'LineString',
        coordinates: route
      }
    };
    // if the route already exists on the map, we'll reset it using setData
    if (map.getSource('route')) {
      map.getSource('route').setData(geojson);
    }
    // otherwise, we'll make a new request
    else {
      map.addLayer({
        id: 'route',
        type: 'line',
        source: {
          type: 'geojson',
          data: geojson
        },
        layout: {
          'line-join': 'round',
          'line-cap': 'round'
        },
        paint: {
          'line-color': '#3887be',
          'line-width': 5,
          'line-opacity': 0.75
        }
      });
    }
    // add turn instructions here at the end
  }
  
  map.on('load', () => {
    // make an initial directions request that
    // starts and ends at the same location
    getRoute(start);
  
    // Add starting point to the map
    map.addLayer({
      id: 'point',
      type: 'circle',
      source: {
        type: 'geojson',
        data: {
          type: 'FeatureCollection',
          features: [
            {
              type: 'Feature',
              properties: {},
              geometry: {
                type: 'Point',
                coordinates: start
              }
            }
          ]
        }
      },
      paint: {
        'circle-radius': 10,
        'circle-color': '#4264FB'
         
      }
    });
  ////button
 
  $('#tot').on('change', function () {
    var drt=$("#tot option:selected").index();
    console.log(drt);
        const coords =[markers[drt-1].lng,markers[drt-1].lat]
        //const coords = Object.keys(event.lngLat).map((key) => event.lngLat[key]);
      
        const end = {
          type: 'FeatureCollection',
          features: [
            {
              type: 'Feature',
              properties: {},
              geometry: {
                type: 'Point',
                coordinates: coords
              }
            }
          ]
        };
        if (map.getLayer('end')) {
          map.getSource('end').setData(end);
        } else {
          map.addLayer({
            id: 'end',
            type: 'circle',
            source: {
              type: 'geojson',
              data: {
                type: 'FeatureCollection',
                features: [
                  {
                    type: 'Feature',
                    properties: {},
                    geometry: {
                      type: 'Point',
                      coordinates: coords
                    }
                  }
                ]
              }
            },
            paint: {
              'circle-radius': 10,
              'circle-color': '#f30'
            }
          });
        }
        getRoute(coords);
      });
  });
  $('#tot').on('change', function () {
    var dropdownselected=$("#tot option:selected").index();
    console.log(dropdownselected);
    });


  
       
      function forwardGeocoder(query) {
      const matchingFeatures = [];
      for (const feature of data) {
      // Handle queries with different capitalization
      // than the source data by calling toLowerCase().
    
      // Add a tree emoji as a prefix for custom
      // data results using carmen geojson format:
      // https://github.com/mapbox/carmen/blob/master/carmen-geojson.md
      feature['place_name'] = `🌲 ${feature.Title} 🌲`;
      feature['center'] = [feature.lng,feature.lat];
      feature['place_type'] = feature.type;
      matchingFeatures.push(feature);
      
      }
      return matchingFeatures;
      }
       
      // Add the control to the map.
      map.addControl(
      new MapboxGeocoder({
      accessToken: mapboxgl.accessToken,
      localGeocoder: forwardGeocoder,
      zoom: 14,
      placeholder: 'Enter search e.g. California GYM',
      mapboxgl: mapboxgl
      })
      );
  });
  
    
    
},       
error : function(xhr, textStatus, errorThrown) {  
 alert('Ajax request failed.');  
 
}  
});  
});  


