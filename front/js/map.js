const geojson = {
    "fire": [
        {"id":57,"type":"C_Flammable_Gases","intensity":50,"range":50,"lon":4.8260937761478795,"lat":45.732333858926715},
        {"id":59,"type":"B_Gasoline","intensity":50,"range":50,"lon":4.808583763718545,"lat":45.793118996773316}
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


for (const feature of geojson.fire) {
    // create a HTML element for each feature
    const el = document.createElement('div');
    el.className = 'marker';
     
    // make a marker for each feature and add it to the map
    new mapboxgl.Marker(el)
        .setLngLat([feature.lon, feature.lat])
        .setPopup(
            new mapboxgl.Popup({ offset: 25 }) // add popups
            .setHTML(
                `<h3>Fire ${feature.id}</h3>
                <ul>
                    <li>
                        <p>intensity : ${feature.intensity}</p>
                    </li>
                    <li>
                        <p>latitude : ${feature.lat}</p>
                    </li>
                    <li>
                        <p>longitude : ${feature.lon}</p>
                    </li>
                    <li>
                        <p>range : ${feature.range}</p>
                    </li>
                    <li>
                        <p>type : ${feature.type}</p>
                    </li>
                </ul>`
            )
            )
    .addTo(map);
}