
/*$jsonData = array();  
$idx = 0;  
foreach({{map}}}} as $map) {  
   $temp = array(
      'name' => $map->getName(),  
      'lat' => $map->getLat(),  
      'lng' => map->getLng(), 
      'content' => $map->getContent(), 
   );   
   $jsonData[$idx++] = $temp;  
} 
return new JsonResponse($jsonData); */








function initMap() {
  const myLatLng = { lat: 36.861268, lng: 10.200057 };
  const map = new google.maps.Map(document.getElementById("map"), {
    zoom: 13,
    center: myLatLng,
  });
  const contentString ='<h1>Uluru</h1>';
  // Listen for click on map
 
  //insert marks 
  const marker = new google.maps.Marker({
    position: myLatLng,
    map,
    title: "Uluru (Ayers Rock)",
    content: contentString,
  });
 
// click mark
  marker.addListener("click", () => {
    infowindow.open({
      anchor: marker,
      map,
      shouldFocus: false,
    });
  });
  const infowindow = new google.maps.InfoWindow({
    content: contentString,
  });

/* ////////////stock donnée de  base 
 
// Array of markers
var markers = [
  {
    coords:{lat:42.4668,lng:-70.9495},
    iconImage:'https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png',
    content:'<h1>Lynn MA</h1>'
  },
  {
    coords:{lat:42.8584,lng:-70.9300},
    content:'<h1>Amesbury MA</h1>'
  },
  {
    coords:{lat:42.7762,lng:-71.0773}
  }
];

*/
  /*  ///////////////////loop base de donnée/////////////
  // Loop through markers
  for(var i = 0;i < markers.length;i++){
    // Add marker
    addMarker(markers[i]);
  }*/


}







/*function initMap(){
      // Map options
      var options = {
        zoom:8,
        center:{lat:42.3601,lng:-71.0589}
      }

      // New map
      var map = new google.maps.Map(document.getElementById('map'), options);

      // Listen for click on map
      google.maps.event.addListener(map, 'click', function(event){
        // Add marker
        addMarker({coords:event.latLng});
      });

      
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

      // Array of markers
      var markers = [
        {
          coords:{lat:42.4668,lng:-70.9495},
          iconImage:'https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png',
          content:'<h1>Lynn MA</h1>'
        },
        {
          coords:{lat:42.8584,lng:-70.9300},
          content:'<h1>Amesbury MA</h1>'
        },
        {
          coords:{lat:42.7762,lng:-71.0773}
        }
      ];

      // Loop through markers
      for(var i = 0;i < markers.length;i++){
        // Add marker
        addMarker(markers[i]);
      }

      // Add Marker Function
      function addMarker(props){
        var marker = new google.maps.Marker({
          position:props.coords,
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
    }*/