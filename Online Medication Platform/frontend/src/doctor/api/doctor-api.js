import {HOST} from '../../commons/hosts';
import RestApiClient from "../../commons/api/rest-client";


const endpoint = {
    doctor: '/doctor'
};

function getDoctors(callback) {
    let request = new Request(HOST.backend_api + endpoint.doctor, {
        method: 'GET',
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}


function postDoctor(user, callback){
    let request = new Request(HOST.backend_api + endpoint.doctor , {
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

function deleteDoctor(doctorId, callback) {
    let request = new Request(HOST.backend_api + endpoint.doctor + '/' + doctorId, {
        method: 'DELETE',
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function putDoctor(user, callback) {
    let request = new Request(HOST.backend_api + endpoint.doctor, {
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
    getDoctors,
    postDoctor,
    deleteDoctor,
    putDoctor
};