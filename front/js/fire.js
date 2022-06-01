/**
* Get all fire 
* @author   groupe2
* @param    none
* @return   none
*/
function getFire(){

    const URL="vps.cpe-sn.fr:8081/fire";
    let context =   {
                        method: 'GET',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                          }
                    };
    fetch(URL,context)
        .then(response => response)
            .then(response => response)
            .catch(error => err_callback(error));
}


