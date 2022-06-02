
async function GetFacilities(){
    const GET_CHUCK_URL="http://vps.cpe-sn.fr:8081/facility";
    let context =   {
                        method: 'GET',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                          },
                    };
    return fetch(GET_CHUCK_URL,context)
        .then(response => response.json())
        .catch(error => err_callback(error));
}

var marker;

function DisplayFacilities(facility){
    /*for (let i = 0; i < facility.length; i++) {
        const el = document.createElement('div');
        if((facility[i].name).includes("Cas5")){
            el.className = 'marker_notre_facility';
            new mapboxgl.Marker(el).setLngLat([facility[i].lon,facility[i].lat]).addTo(map);
            
        }
        else{
            el.className = 'marker_facility';
            new mapboxgl.Marker(el).setLngLat([facility[i].lon,facility[i].lat]).addTo(map);
        }
      }
    console.log("OUIUIUIUIU");*/
    return facility;
}

function err_callback(error){
    console.log(error);
}

