function getDirection(id){
    let URL = "https://localhost:8080/back/direction?idVehicule="+id;
    $.ajax({  
        type : 'GET',  
        url : URL,  
    }).then(function(data) {
        $('.greeting-id').append(data.id);
        $('.greeting-content').append(data.content);
     });
/*
    fetch(URL,context)
        .then(response => response.json())
            .then(response => displayFire(response))
            .catch(error =>(error));*/
}