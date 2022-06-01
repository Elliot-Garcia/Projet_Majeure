
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

function DisplayFacilities(facility){
    console.log(facility)
    console.log("OUIUIUIUIU");
}

function err_callback(error){
    console.log(error);
}