function initMap(){  
    $(document).ready(function(){   
         $.ajax({  
          url:        '/map',  
          type:       'POST',   
          dataType:   'json',  
          async:      false,  
          
          success: function(data, status) {  
            var markers = data;
			var mapOptions = {
				center: new google.maps.LatLng(markers[0].lat, markers[0].lng),
				zoom: 12,
				mapTypeId: google.maps.MapTypeId.ROADMAP
			};
			var infoWindow = new google.maps.InfoWindow();
			var map = new google.maps.Map(document.getElementById("map"), mapOptions);
			for (i = 0; i < markers.length; i++) {
				var datav = markers[i]
				var myLatlng = new google.maps.LatLng(datav.lat, datav.lng);
				
				var marker = new google.maps.Marker({
					position: myLatlng,
					map: map,
					title: datav.name,
										
                  
				});
				(function (marker, datav) {
					google.maps.event.addListener(marker, "click", function (e) {
						infoWindow.setContent('<h1 style="color:blue;">'+datav.Description +datav.Num_tel+'</h1> <img style="height:100px;width:150px;" src="/upload/images/'+datav.Image+'">');
						infoWindow.open(map, marker);

                          

					});
				})(marker, datav);
			}
            
          },  
          
          error : function(xhr, textStatus, errorThrown) {  
           alert('Ajax request failed.');  
           
          }  
         });  
      });  
      
    
    }
	
	
	
      
      
