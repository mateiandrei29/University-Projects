import React from 'react';
import APIResponseErrorMessage from "../commons/errorhandling/api-response-error-message";
import {
    Button,
    Card,
    CardHeader,
    Col,
    Modal,
    ModalBody,
    ModalHeader,
    Row
} from 'reactstrap';
import {Client} from '@stomp/stompjs';

import CaregiverPatientsTable from "./components/caregiver-patients-table";
import * as API_PATIENT from "../patient/api/patient-api"

class CaregiverPatientsContainer extends React.Component {
    constructor(props) {
        super(props);
        this.reload = this.reload.bind(this);
        this.state = {
            selected: false,
            collapseForm: false,
            tableData: [],
            isLoaded: false,
            errorStatus: 0,
            error: null
        };
    }

    componentDidMount() {
        this.fetchPatientsByCaregiver();
        this.subscribe();

    }

    subscribe() {
        this.client = new Client();

        this.client.configure({
            brokerURL: 'wss://medapp-ds2020-backend.herokuapp.com/notification/websocket',
            onConnect: () => {
                console.log('onConnect');

                this.client.subscribe('/message/notify', message => {
                    console.log(message);
                    alert(message.body);

                });
            }
        });
        this.client.activate();
    }

    fetchPatientsByCaregiver() {
        return API_PATIENT.getPatientsByCaregiverId(localStorage.getItem('userId'), (result, status, err) => {
            if (result !== null && status === 200) {
                this.setState({
                    tableData: result,
                    isLoaded: true
                });
            } else {
                this.setState(({
                    errorStatus: status,
                    error: err
                }));
            }

        });
    }


    toggleForm() {
        this.setState({selected: !this.state.selected});
    }


    reload() {
        this.setState({
            isLoaded: false
        });
        this.fetchPatientsByCaregiver();
    }

    render() {
        return (
            <div>
                <CardHeader>
                    <strong> Caregiver Management </strong>
                </CardHeader>
                <Card>

                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            {this.state.isLoaded && <CaregiverPatientsTable tableData={this.state.tableData}/>}
                            {this.state.errorStatus > 0 && <APIResponseErrorMessage
                                errorStatus={this.state.errorStatus}
                                error={this.state.error}
                            />}
                        </Col>
                    </Row>
                </Card>


            </div>
        )

    }
}

export default CaregiverPatientsContainer;