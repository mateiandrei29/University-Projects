import {HOST} from '../../commons/hosts';
import RestApiClient from "../../commons/api/rest-client";


const endpoint = {
    patient: '/patient'
};

function getPatients(callback) {
    let request = new Request(HOST.backend_api + endpoint.patient, {
        method: 'GET',
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function getPatientsByCaregiverId(caregiverId, callback) {
    let request = new Request(HOST.backend_api + endpoint.patient + '/' + caregiverId, {
        method: 'GET',
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}


function postPatient(user, callback) {
    let request = new Request(HOST.backend_api + endpoint.patient, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(user)
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}

function deletePatient(patientId, callback) {
    let request = new Request(HOST.backend_api + endpoint.patient + '/' + patientId, {
        method: 'DELETE',
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function putPatient(user, callback) {
    let request = new Request(HOST.backend_api + endpoint.patient, {
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
    getPatients,
    postPatient,
    getPatientsByCaregiverId,
    deletePatient,
    putPatient
};