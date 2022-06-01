
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
            .then(response => DisplayFacilities(response)) //printFacilities(facility)
            .catch(error => err_callback(error));
}

function DisplayFacilities(facility){
    console.log(facility[0])
    printFacilities(facility)
    console.log("OUIUIUIUIU");
}

function err_callback(error){
    console.log(error);
}
var marker;
var myIcon = L.icon({
    iconUrl: '../../img/cas1.png',
    iconSize: [35, 35],
    iconAnchor: [22, 94],
    popupAnchor: [-3, -76],

    });

function printFacilities(facility){
    for (let i = 0; i < facility.length; i++) {
        if((facility[i].name).includes("Cas5")){
            marker = L.marker([facility[i].lat, facility[i].lon], {
            icon: myIcon
            }).addTo(map);
            
        }
        else{
            marker = L.marker([facility[i].lat, facility[i].lon]).addTo(map);
        }
      }
     
}
