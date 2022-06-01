/**
                 {
                    "crewMember": 0,
                    "facilityRefID": 0,
                    "fuel": 0,
                    "id": 0,
                    "lat": 0,
                    "liquidQuantity": 0,
                    "liquidType": "ALL",
                    "lon": 0,
                    "type": "CAR"
                  } 
*/

function AddVehicle(){
    const GET_CHUCK_URL="http://vps.cpe-sn.fr:8081/vehicle/4360022a-90ca-4691-a340-bfb990c37bfc"; 
    const data = getInput();
    let context =   {
                        method: 'POST',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                          },
                        body : JSON.stringify(data)
                    };
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
    let mess = "an error occured during the addition of the card";
    if (response.status == 200){
        mess = "Your card was addes sucesfully";
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
* Function that gets all element to creat a JSON card
* @author   groupe2
* @param    none
* @return   card under JSON format
*/
function getInput(){
    var data = {};
    var getInput = document.querySelectorAll(".field");
    getInput.forEach( element => data[ element.children[1].name ] = element.children[1].value)

    return(data);
}