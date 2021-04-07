import {HOST} from '../../commons/hosts';
import RestApiClient from "../../commons/api/rest-client";


const endpoint = {
    medication: '/medication'
};

function getMedications(callback) {
    let request = new Request(HOST.backend_api + endpoint.medication, {
        method: 'GET',
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}


function postMedication(user, callback){
    let request = new Request(HOST.backend_api + endpoint.medication , {
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

function deleteMedication(medicationId, callback) {
    let request = new Request(HOST.backend_api + endpoint.medication + '/' + medicationId, {
        method: 'DELETE',
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function putMedication(user, callback) {
    let request = new Request(HOST.backend_api + endpoint.medication, {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(user)
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}

export {
    getMedications,
    postMedication,
    deleteMedication,
    putMedication
};