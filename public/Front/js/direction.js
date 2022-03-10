
	mapboxgl.accessToken = 'pk.eyJ1IjoiZGFiaWl4IiwiYSI6ImNrem9ncGwyNjAwY2oydW9jYmI5bmxpOHYifQ.SHMyeNSFCyhT-EnFPKSJPw';
// This GeoJSON contains features that include an "icon"
// property. The value of the "icon" property corresponds
// to an image in the Mapbox Light style's sprite. (Note:
// the name of images is the value of the "icon" property
// + "-15".)
const places = {
'type': 'FeatureCollection',
'features': [
{
'type': 'Feature',
'properties': {
'icon': 'theatre'
},
'geometry': {
'type': 'Point',
'coordinates': [-77.038659, 38.931567]
}
},
{
'type': 'Feature',
'properties': {
'icon': 'theatre'
},
'geometry': {
'type': 'Point',
'coordinates': [-77.003168, 38.894651]
}
},
{
'type': 'Feature',
'properties': {
'icon': 'bar'
},
'geometry': {
'type': 'Point',
'coordinates': [-77.090372, 38.881189]
}
},
{
'type': 'Feature',
'properties': {
'icon': 'bicycle'
},
'geometry': {
'type': 'Point',
'coordinates': [-77.052477, 38.943951]
}
},
{
'type': 'Feature',
'properties': {
'icon': 'music'
},
'geometry': {
'type': 'Point',
'coordinates': [-77.031706, 38.914581]
}
},
{
'type': 'Feature',
'properties': {
'icon': 'music'
},
'geometry': {
'type': 'Point',
'coordinates': [-77.020945, 38.878241]
}
},
{
'type': 'Feature',
'properties': {
'icon': 'music'
},
'geometry': {
'type': 'Point',
'coordinates': [-77.007481, 38.876516]
}
}
]
};
 
const filterGroup = document.getElementById('filter-group');
const map = new mapboxgl.Map({
container: 'mapbox',
style: 'mapbox://styles/mapbox/light-v10',
center: [-77.04, 38.907],
zoom: 11.15
});
 
map.on('load', () => {
// Add a GeoJSON source containing place coordinates and information.
map.addSource('places', {
'type': 'geojson',
'data': places
});
 
for (const feature of places.features) {
const symbol = feature.properties.icon;
const layerID = `poi-${symbol}`;
 
// Add a layer for this symbol type if it hasn't been added already.
if (!map.getLayer(layerID)) {
map.addLayer({
'id': layerID,
'type': 'symbol',
'source': 'places',
'layout': {
// These icons are a part of the Mapbox Light style.
// To view all images available in a Mapbox style, open
// the style in Mapbox Studio and click the "Images" tab.
// To add a new image to the style at runtime see
// https://docs.mapbox.com/mapbox-gl-js/example/add-image/
'icon-image': `${symbol}-15`,
'icon-allow-overlap': true
},
'filter': ['==', 'icon', symbol]
});
 
// Add checkbox and label elements for the layer.
const input = document.createElement('input');
input.type = 'checkbox';
input.id = layerID;
input.checked = true;
filterGroup.appendChild(input);
 
const label = document.createElement('label');
label.setAttribute('for', layerID);
label.textContent = symbol;
filterGroup.appendChild(label);
 
// When the checkbox changes, update the visibility of the layer.
input.addEventListener('change', (e) => {
map.setLayoutProperty(
layerID,
'visibility',
e.target.checked ? 'visible' : 'none'
);
});
}
}
});
