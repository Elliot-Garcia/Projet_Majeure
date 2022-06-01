
function GetFacilities(){
    const GET_CHUCK_URL="http://vps.cpe-sn.fr:8081/facility";
    let context =   {
                        method: 'GET',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                          },
                    };
    fetch(GET_CHUCK_URL,context)
        .then(response => response.json())
            .then(response => DisplayFacilities(response))
            .catch(error => err_callback(error));
}

var marker;
var myIcon = mapboxgl.icon({
    iconUrl: '../media/point/caserne.png',
    iconSize: [35, 35],
    iconAnchor: [22, 94],
    popupAnchor: [-3, -76],

    });

function DisplayFacilities(facility){
    for (let i = 0; i < facility.length; i++) {
        const el = document.createElement('div');
        el.className = 'marker';
        if((facility[i].name).includes("Cas5")){
            new mapboxgl.Marker(el).setLngLat([facility[i].lon,facility[i].lat]).addTo(map);
            
        }
        else{
            new mapboxgl.Marker(el).setLngLat([facility[i].lon,facility[i].lat]).addTo(map);
        }
      }
    console.log("OUIUIUIUIU");
}

function err_callback(error){
    console.log(error);
}

