function getDirection(){
    let URL = "https://localhost:8080/";
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