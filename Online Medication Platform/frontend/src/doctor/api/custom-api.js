import {HOST} from '../../commons/hosts';
import RestApiClient from "../../commons/api/rest-client";


const endpoint = {
    customMedication: '/customMedication'
};

function getCustomMedication(callback) {
    let request = new Request(HOST.backend_api + endpoint.customMedication, {
        method: 'GET',
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}


function postCustomMedication(user, callback){
    let request = new Request(HOST.backend_api + endpoint.customMedication , {
        method: 'POST',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(user)
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}

export {
    getCustomMedication,
    postCustomMedication
};