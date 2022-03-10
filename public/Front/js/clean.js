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
			var infoWindow = new google.maps.InfoWindow({
				maxWidth: 7000 ,
    			maxHeight: 1000,
			}

			);
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
						infoWindow.setContent('<div><h1 style="color:red;">'+datav.name +'</h1></div>'+
						' <img style="height:140px;width:400px;" src="/upload/images/'+datav.Image+'">'+
						'<div><h2 style="color:black">Type:'+datav.Type+'</h2></div>'+
						'<div><h2 style="color:black">Number phone:'+datav.Num_tel+'</h2></div>'+
						"a.href = https://www.geeksforgeeks.org"+
						'<a class="btn btn-primary" href="http://www.w3schools.com/js/js_htmldom_html.asp">Reserver</a>');
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
	
	
	
      
      
