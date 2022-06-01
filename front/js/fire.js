/**
* Get all fire 
* @author   groupe2
* @param    none
* @return   none
*/
function getFire(){

    const URL="vps.cpe-sn.fr:8081/fire/";
    let context =   {
                        method: 'GET',
                        headers: {
                            'Accept': 'application/json',
                          }
                    };

    console.log("OUI")
    fetch(URL,context)
        .then(response => response)
            .then(response => displayFire(response))
            .catch(error =>(error));
}

function displayFire(response){
    console.log(response);
}