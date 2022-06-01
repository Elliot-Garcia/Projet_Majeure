/**
* Get all fire
* @author   la casern de notre dame
* @param    none
* @return   none
*/
function getFire(){
    const URL="http://vps.cpe-sn.fr:8081/fire/";
    let context =   {
                        method: 'GET',
                        headers: {
                            'Accept': 'application/json',
                          }
                    };
    fetch(URL,context)
        .then(response => response.json())
            .then(response => displayFire(response))
            .catch(error =>(error));
}

/**
* Get one fire
* @author   la casern de notre dame
* @param    none
* @return   none
*/
function getOneFire(){
    // get id of fire
    id = 57;

    let URL = "http://vps.cpe-sn.fr:8081/fire/";
    let context =   {
                        method: 'GET',
                        headers: {
                            'Accept': 'application/json',
                          }
                    };
    URL = URL + id;

    fetch(URL,context)
        .then(response => response.json())
            .then(response => displayFire(response))
            .catch(error =>(error));
}

/**
* dsiplay a fire list
* @author   la casern de notre dame
* @param    the fire list
* @return   none
*/
function displayFire(fireList){

    const filter = infoFilter();
    const keyFilter = Object.keys(filter)

    var resFire = [];

    for ( const element of fireList){
        var bool = true;

        for ( const key of keyFilter){
            if (element[key] > filter[key]){
                bool &= false;
            }
        }

        if (bool == true){
            resFire.push(element)
        }
    }

    console.log("res fire: ", JSON.stringify({'fire':resFire}));

}

/**
* dsiplay errors
* @author   la casern de notre dame
* @param   error
* @return   none
*/
function err_callback(error){
    console.log("Error: " + error);
}

/**
* get filter information
* @author   la casern de notre dame
* @param   error
* @return   none
*/
function infoFilter(){
    var data = {};
    var getInput = document.querySelectorAll(".field");
    getInput.forEach( element => data[ element.children[1].name ] = element.children[1].value)

    return(data);
}

/**
* filterFire
* @author   la casern de notre dame
* @param   error
* @return   none
*/
function filterFire(){
    const filter = infoFilter();
    const fire = getFire();
    
    var resFire = [];

    for ( const element of fire){
        console.log(element);
    }

}