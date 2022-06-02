/**
 * ADD vehicle
 */
function AddVehicle(){
    const GET_CHUCK_URL="http://vps.cpe-sn.fr:8081/vehicle/b6b21258-84b8-42dd-bdf4-35014914c964"; 
    const data = getInput();
    let context =   {
                        method: 'POST',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                          },
                        body : JSON.stringify(data)
                    };
    console.log(data)
    fetch(GET_CHUCK_URL,context)
        .then(response => response)
            .then(response => callback(response))
            .catch(error => err_callback(error));
    console.log(response)
    return response;
}

/**
 * DELETE ALL VEHICLE
 */
function DeleteAllVehicle(){
    const GET_CHUCK_URL="http://vps.cpe-sn.fr:8081/vehicle/b6b21258-84b8-42dd-bdf4-35014914c964"; 
    let context =   {
                        method: 'DELETE',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                          },
                    };
    fetch(GET_CHUCK_URL,context)
        .then(response => response)
            .then(response => callback(response))
            .catch(error => err_callback(error));
}

/**
 * DELETE VEHICLE WITH ID=ID
 */
function DeleteOneVehicle(){
    const data = getID();
    console.log("tetetet")
    console.log(data)
    const GET_CHUCK_URL="http://vps.cpe-sn.fr:8081/vehicle/b6b21258-84b8-42dd-bdf4-35014914c964/"+data;
    let context =   {
                        method: 'DELETE',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                          },
                    };
    console.log(data)
    fetch(GET_CHUCK_URL,context)
        .then(response => response)
            .then(response => callback(response))
            .catch(error => err_callback(error));
}

/**
 * DELETE VEHICLE WITH ID=ID
 */
 function DeleteOneVehicle(){
    const data = getID();
    console.log("tetetet")
    console.log(data)
    const GET_CHUCK_URL="http://vps.cpe-sn.fr:8081/vehicle/b6b21258-84b8-42dd-bdf4-35014914c964/"+data;
    let context =   {
                        method: 'DELETE',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                          },
                    };
    console.log(data)
    fetch(GET_CHUCK_URL,context)
        .then(response => response)
            .then(response => callback(response))
            .catch(error => err_callback(error));
}




/**
* Function that show to the user the result of the api fetch
* @author   groupe2
* @param    response of the fetched api
* @return   none
*/
function callback(response){
    let mess = "an error occured during the process";
    if (response.status == 200){
        mess = "sucess";
    }
    document.getElementById("succesMessage").innerHTML = mess;
}

/**
* Function that log errors
* @author   groupe2
* @param    error of the fetched api
* @return   none
*/
function err_callback(error){
    console.log(error);
}

/**
* Function that gets all element to creat a JSON 
* @author   groupe2
* @param    none
* @return   
*/
function getInput(){
    var data = {};
    var getInput = document.querySelectorAll(".field");
    getInput.forEach( element => data[ element.children[1].name ] = element.children[1].value)

    return(data);
}

/**
 * GET ID OF VEHICLE GIVE BY USER
 * @returns 
 */
function getID(){
    var getInput = document.querySelectorAll('[name="id"]');
    return getInput[0].value;
}



async function GetVehicle(){
    const GET_CHUCK_URL="http://vps.cpe-sn.fr:8081/vehicle";
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

function DisplayVehicle(vehicle){
    for (let i = 0; i < vehicle.length; i++) {
        const el = document.createElement('div');
        
        if((vehicle[i].facilityRefID) == 84){
            el.className = 'marker_notre_vehicle';
            new mapboxgl.Marker(el)
                .setLngLat([vehicle[i].lon,vehicle[i].lat])
                .setPopup(
                    new mapboxgl.Popup({ offset: 25 }) // add popups
                    .setHTML(
                        `<h3>Vehicule ${vehicle[i].id}</h3>
                        <ul>
                            <li>
                                <p>CrewMember : ${vehicle[i].crewMember}</p>
                            </li>
                            <li>
                                <p>fuel : ${vehicle[i].fuel}</p>
                            </li>
                            <li>
                                <p>LiquidQuantity : ${vehicle[i].liquidQuantity}</p>
                            </li>
                            <li>
                                <p>LiquidType : ${vehicle[i].liquidType}</p>
                            </li>
                        </ul>`
                    )
                    )
                .addTo(map);
            
        }
        else{
            el.className = 'marker_vehicle';
            new mapboxgl.Marker(el).setLngLat([vehicle[i].lon,vehicle[i].lat]).addTo(map);
        }
      }
    console.log("OUIUIUIUIU");
}



