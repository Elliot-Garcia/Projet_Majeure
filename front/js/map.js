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
    DisplayAll();
}

var intervalId = window.setInterval(function(){
    Start();
  }, 1000);

mapboxgl.accessToken = 'pk.eyJ1IjoiZWxsaWdhcjM0IiwiYSI6ImNsM3ZjOHhoaDA5MXYzYnBsdHFxamsxcjYifQ.UyvHBJ_M2OpnPGJUV-BBYg';
const map = new mapboxgl.Map({
    container: 'map',
    zoom: 12,
    center: [4.8356922252436885, 45.76070528435303],
    style: 'mapbox://styles/mapbox/light-v10',
    antialias: true // create the gl context with MSAA antialiasing, so custom layers are antialiased
});

async function DisplayAll() {

    const boxes = document.querySelectorAll('.marker');

    boxes.forEach(box => {
        box.remove();
    });

    filterValue = "";
    htmlValue = ""
    for (const obj of geojson) {
        if (obj.value == "fire") {
            filterValue = 'filter_red';
            urlIcon = 'media/img/point/fire.png';
        }
    
        else if (obj.value == "facility") {
            urlIcon = 'media/img/point/caserne.png';
        }
        else if (obj.value == "vehicule"){
            filterValue = 'filter_red';
            urlIcon = 'media/img/point/camion.png';
        }
        else { break; }
        
        for (const feature of obj.list) {
            if(obj.value == "fire"){
                htmlValue = `<h3>Fire ${feature.id}</h3>
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
                        </ul>`;
                       
                if ((feature.type) == "A") {
                    filterValue = 'filter_green';
                        
                } else if ((feature.type) == "B_Gasoline") {
                    filterValue = 'filter_gray';
                    
                } else if ((feature.type) == "B_Alcohol") {
                    filterValue = 'filter_red';
                    
                } else if ((feature.type) == "B_Plastics") {
                    filterValue = 'filter_yellow';
                    
                } else if ((feature.type) == "C_Flammable_Gases") {
                    filterValue = 'filter_black';
                    
                } else if ((feature.type) == "D_Metals") {
                    filterValue = 'filter_blue';
                    
                } else if ((feature.type) == "E_Electric") {
                    filterValue = 'filter_pink';    
                } 
            }
            if (obj.value == "facility") {
                htmlValue = `<h3>Caserne ${feature.id}</h3>
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
                if ((feature.name).includes("C5")) {
                    filterValue = 'filter_blue';
                    
                }
                else {
                    filterValue = 'filter_black';
                }
            }
            if (obj.value == "vehicule") {
                htmlValue = `<h3>${feature.type} ${feature.id}</h3>
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
                if ((feature.facilityRefID) == 663369) {
                    filterValue = 'filter_blue';
                }
                else {
                    filterValue = 'filter_black';
                }
            }
            // create a HTML element for each feature
            const el = document.createElement('div');
            el.className = "marker " + filterValue;
            el.style.backgroundImage = "url(" + urlIcon + ")";
            
            // make a marker for each feature and add it to the map
            const marker = new mapboxgl.Marker(el)
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

/* Set the width of the sidebar to 250px (show it) */
function openNav() {
    document.getElementById("mySidepanel").style.width = "250px";
  }
  
  /* Set the width of the sidebar to 0 (hide it) */
  function closeNav() {
    document.getElementById("mySidepanel").style.width = "0";
  }
