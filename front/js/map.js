const geojson = {
    type: 'FeatureCollection',
    features: [
      {
        type: 'Feature',
        geometry: {
          type: 'Point',
          coordinates: [-77.032, 38.913]
        },
        properties: {
          title: 'Mapbox',
          description: 'Washington, D.C.'
        }
      },
      {
        type: 'Feature',
        geometry: {
          type: 'Point',
          coordinates: [-122.414, 37.776]
        },
        properties: {
          title: 'Mapbox',
          description: 'San Francisco, California'
        }
      }
    ]
  };
  

mapboxgl.accessToken = 'pk.eyJ1IjoiZWxsaWdhcjM0IiwiYSI6ImNsM3ZjOHhoaDA5MXYzYnBsdHFxamsxcjYifQ.UyvHBJ_M2OpnPGJUV-BBYg';
const map = new mapboxgl.Map({
    container: 'map',
    zoom: 13,
    center: [4.8356922252436885, 45.76070528435303],
    style: 'mapbox://styles/mapbox/light-v10',
    antialias: true // create the gl context with MSAA antialiasing, so custom layers are antialiased
});


for (const feature of geojson.features) {
    // create a HTML element for each feature
    const el = document.createElement('div');
    el.className = 'marker';
     
    // make a marker for each feature and add it to the map
    new mapboxgl.Marker(el)
        .setLngLat(feature.geometry.coordinates)
        .setPopup(
            new mapboxgl.Popup({ offset: 25 }) // add popups
            .setHTML(
                `<h3>${feature.properties.title}</h3><p>${feature.properties.description}</p>`
            )
            )
    .addTo(map);
}

function addPoint(jsonPoint) {
    const marker1 = new mapboxgl.Marker()
        .setLngLat([4, 45])
        .addTo(map);
    map.addLayer({
        'id': 'points',
        'type': 'symbol',
        'source': 'points',
        'layout': {
        'icon-image': 'custom-marker',
        // get the title name from the source's "title" property
        'text-field': ['get', 'title'],
        'text-font': [
            'Open Sans Semibold',
            'Arial Unicode MS Bold'
        ],
        'text-offset': [0, 1.25],
        'text-anchor': 'top'
        }
    });
}