const geojson = [
    {
        "value": "fire",
        "list": []
    },
    {
        "value": "facility",
        "list": []
    },
    {
        "value": "vehicule",
        "list": []
    }
];

async function Start(){
    geojson[0].list = await getFire();
    geojson[1].list = await GetFacilities();
    geojson[2].list = await GetVehicle();
    console.log(geojson)
    DisplayAll();
}

mapboxgl.accessToken = 'pk.eyJ1IjoiZWxsaWdhcjM0IiwiYSI6ImNsM3ZjOHhoaDA5MXYzYnBsdHFxamsxcjYifQ.UyvHBJ_M2OpnPGJUV-BBYg';
const map = new mapboxgl.Map({
    container: 'map',
    zoom: 12,
    center: [4.8356922252436885, 45.76070528435303],
    style: 'mapbox://styles/mapbox/light-v10',
    antialias: true // create the gl context with MSAA antialiasing, so custom layers are antialiased
});


async function DisplayAll() {
    filterValue = "";
    htmlValue = ""
    for (const obj of geojson) {
        if (obj.value == "fire") {
            filterValue = 'filter_red';
            urlIcon = 'media/img/point/fire.png';
            /*htmlValue = `<h3>Fire ${feature.id}</h3>
                        <ul>
                            <li>
                                <p>Fire type : ${feature.type}</p>
                            </li>
                            <li>
                                <p>intensity : ${feature.intensity}</p>
                            </li>
                            <li>
                                <p>range : ${feature.range}</p>
                            </li>
                        </ul>`;*/
        }
    
        else if (obj.value == "facility") {
            urlIcon = 'media/img/point/caserne.png';
            /*htmlValue = `<h3>Fire ${feature.id}</h3>
                        <ul>
                            <li>
                                <p>Fire type : ${feature.type}</p>
                            </li>
                            <li>
                                <p>intensity : ${feature.intensity}</p>
                            </li>
                            <li>
                                <p>range : ${feature.range}</p>
                            </li>
                        </ul>`;*/
        }
        else if (obj.value == "vehicule"){
            filterValue = 'filter_red';
            urlIcon = 'media/img/point/camion.png';
            /*htmlValue = `<h3>Fire ${feature.list.id}</h3>
                        <ul>
                            <li>
                                <p>Fire type : ${feature.type}</p>
                            </li>
                            <li>
                                <p>intensity : ${feature.intensity}</p>
                            </li>
                            <li>
                                <p>range : ${feature.range}</p>
                            </li>
                        </ul>`;*/
        }
        else { break; }
        
        for (const feature of obj.list) {

            if (obj.value == "facility") {
                htmlValue = `<h3>Fire ${feature.id}</h3>
                <ul>
                    <li>
                        <p> maxVehicleSpace : ${feature.maxVehicleSpace}</p>
                    </li>
                    <li>
                        <p>peopleCapacity : ${feature.peopleCapacity}</p>
                    </li>
                    <li>
                        <p>vehicleIdSet : ${feature.vehicleIdSet}</p>
                    </li>
                    <li>
                        <p>peopleIdSet : ${feature.peopleIdSet}</p>
                    </li>
                </ul>`;
                if ((feature.name).includes("Cas5")) {
                    filterValue = 'filter_green';
                    
                }
                else {
                    filterValue = 'filter_gray';
                }
            }
            if (obj.value == "vehicule") {
                htmlValue = `<h3>Fire ${feature.id}</h3>
                        <ul>
                            <li>
                                <p> crewMember : ${feature.crewMember}</p>
                            </li>
                            <li>
                                <p>fuel : ${feature.fuel}</p>
                            </li>
                            <li>
                                <p>liquidQuantity : ${feature.liquidQuantity}</p>
                            </li>
                            <li>
                                <p>liquidType : ${feature.liquidType}</p>
                            </li>
                        </ul>`;
                if ((feature.facilityRefID) == 84) {
                    filterValue = 'filter_blue';
                }
                else {
                    filterValue = 'filter_gray';
                }
            }
                console.log(filterValue);
            // create a HTML element for each feature
            const el = document.createElement('div');
            el.className = "marker " + filterValue;
            el.style.backgroundImage = "url(" + urlIcon + ")";
            
            // make a marker for each feature and add it to the map
            new mapboxgl.Marker(el)
                .setLngLat([feature.lon, feature.lat])
                .setPopup(
                    new mapboxgl.Popup({ offset: 25 }) // add popups
                    .setHTML(
                        htmlValue
                    )
                )
            .addTo(map);
        }
    }
}