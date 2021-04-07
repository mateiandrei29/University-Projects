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

import DoctorForm from "./components/doctor-form";
import * as API_DOCTOR from "./api/doctor-api"
import * as API_CAREGIVER from "../caregiver/api/caregiver-api"
import * as API_PATIENT from "../patient/api/patient-api"
import * as API_MEDICATION from "./api/medication-api"
import * as API_MEDICATIONPLAN from "./api/medication-plan-api"
import * as API_CUSTOM from "./api/custom-api"
import DoctorTable from "./components/doctor-table";
import CaregiverTable from "./components/caregiver-table";
import CaregiverForm from "./components/caregiver-form";
import PatientTable from "./components/patient-table";
import PatientForm from "./components/patient-form";
import MedicationTable from "./components/medication-table";
import MedicationForm from "./components/medication-form";
import MedicationPlanTable from "./components/medication-plan-table";
import MedicationPlanForm from "./components/medication-plan-form";
import CustomTable from "./components/custom-table";
import CustomForm from "./components/custom-form";

class DoctorContainer extends React.Component {
    constructor(props) {
        super(props);
        this.toggleFormDoctor = this.toggleFormDoctor.bind(this);
        this.toggleFormCaregiver = this.toggleFormCaregiver.bind(this);
        this.toggleFormPatient = this.toggleFormPatient.bind(this);
        this.toggleFormMedication = this.toggleFormMedication.bind(this);
        this.toggleFormMedicationPlan = this.toggleFormMedicationPlan.bind(this);
        this.toggleFormCustomMedication = this.toggleFormCustomMedication.bind(this);


        this.reloadDoctor = this.reloadDoctor.bind(this);
        this.reloadCaregiver = this.reloadCaregiver.bind(this);
        this.reloadPatient = this.reloadPatient.bind(this);
        this.reloadMedication = this.reloadMedication.bind(this);
        this.reloadMedicationPlan = this.reloadMedicationPlan.bind(this);
        this.reloadCustomMedication = this.reloadCustomMedication.bind(this);


        this.state = {
            selectedDoctor: false,
            selectedCaregiver: false,
            selectedPatient: false,
            selectedMedication: false,
            selectedMedicationPlan: false,
            selectedCustomMedication: false,
            collapseForm: false,
            tableDataDoctor: [],
            tableDataCaregiver: [],
            tableDataPatient: [],
            tableDataMedication: [],
            tableDataMedicationPlan: [],
            tableDataCustomMedication: [],
            isLoadedDoctor: false,
            isLoadedCaregiver: false,
            isLoadedPatient: false,
            isLoadedMedication: false,
            isLoadedMedicationPlan: false,
            isLoadedCustomMedication: false,
            errorStatus: 0,
            error: null
        };
    }

    componentDidMount() {
        this.fetchDoctor();
        this.fetchCaregiver();
        this.fetchPatient();
        this.fetchMedication();
        this.fetchMedicationPlan();
        this.fetchCustomMedication();
    }

    fetchDoctor() {
        return API_DOCTOR.getDoctors((result, status, err) => {
            if (result !== null && status === 200) {
                this.setState({
                    tableDataDoctor: result,
                    isLoadedDoctor: true
                });
            } else {
                this.setState(({
                    errorStatus: status,
                    error: err
                }));
            }

        });
    }

    fetchCaregiver() {
        return API_CAREGIVER.getCaregivers((result, status, err) => {
            if (result !== null && status === 200) {
                this.setState({
                    tableDataCaregiver: result,
                    isLoadedCaregiver: true
                });
            } else {
                this.setState(({
                    errorStatus: status,
                    error: err
                }));
            }

        });
    }

    fetchPatient() {
        return API_PATIENT.getPatients((result, status, err) => {
            if (result !== null && status === 200) {
                this.setState({
                    tableDataPatient: result,
                    isLoadedPatient: true
                });
            } else {
                this.setState(({
                    errorStatus: status,
                    error: err
                }));
            }

        });
    }

    fetchMedication() {
        return API_MEDICATION.getMedications((result, status, err) => {
            if (result !== null && status === 200) {
                this.setState({
                    tableDataMedication: result,
                    isLoadedMedication: true
                });
            } else {
                this.setState(({
                    errorStatus: status,
                    error: err
                }));
            }

        });
    }

    fetchMedicationPlan() {
        return API_MEDICATIONPLAN.getMedicationPlans((result, status, err) => {
            if (result !== null && status === 200) {
                this.setState({
                    tableDataMedicationPlan: result,
                    isLoadedMedicationPlan: true
                });
            } else {
                this.setState(({
                    errorStatus: status,
                    error: err
                }));
            }

        });
    }

    fetchCustomMedication() {
        return API_CUSTOM.getCustomMedication((result, status, err) => {
            if (result !== null && status === 200) {
                this.setState({
                    tableDataCustomMedication: result,
                    isLoadedCustomMedication: true
                });
            } else {
                this.setState(({
                    errorStatus: status,
                    error: err
                }));
            }

        });
    }


    toggleFormDoctor() {
        this.setState({selectedDoctor: !this.state.selectedDoctor});
    }

    toggleFormCaregiver() {
        this.setState({selectedCaregiver: !this.state.selectedCaregiver});
    }

    toggleFormPatient() {
        this.setState({selectedPatient: !this.state.selectedPatient});
    }

    toggleFormMedication() {
        this.setState({selectedMedication: !this.state.selectedMedication});
    }

    toggleFormMedicationPlan() {
        this.setState({selectedMedicationPlan: !this.state.selectedMedicationPlan});
    }


    toggleFormCustomMedication() {
        this.setState({selectedCustomMedication: !this.state.selectedCustomMedication});
    }


    reloadDoctor() {
        this.setState({
            isLoadedDoctor: false
        });
        this.toggleFormDoctor();
        this.fetchDoctor();
        this.fetchCaregiver();
        this.fetchPatient();
        this.fetchMedication();
        this.fetchMedicationPlan();
        this.fetchCustomMedication();

    }

    reloadCaregiver() {
        this.setState({
            isLoadedCaregiver: false
        });
        this.toggleFormCaregiver();
        this.fetchDoctor();
        this.fetchCaregiver();
        this.fetchPatient();
        this.fetchMedication();
        this.fetchMedicationPlan();
        this.fetchCustomMedication();

    }

    reloadPatient() {
        this.setState({
            isLoadedPatient: false
        });
        this.toggleFormPatient();
        this.fetchDoctor();
        this.fetchCaregiver();
        this.fetchPatient();
        this.fetchMedication();
        this.fetchMedicationPlan();
        this.fetchCustomMedication();

    }

    reloadMedication() {
        this.setState({
            isLoadedMedication: false
        });
        this.toggleFormMedication();
        this.fetchDoctor();
        this.fetchCaregiver();
        this.fetchPatient();
        this.fetchMedication();
        this.fetchMedicationPlan();
        this.fetchCustomMedication();

    }

    reloadMedicationPlan() {
        this.setState({
            isLoadedMedicationPlan: false
        });
        this.toggleFormMedicationPlan();
        this.fetchDoctor();
        this.fetchCaregiver();
        this.fetchPatient();
        this.fetchMedication();
        this.fetchMedicationPlan();
        this.fetchCustomMedication();
    }


    reloadCustomMedication() {
        this.setState({
            isLoadedCustomMedication: false
        });
        this.toggleFormCustomMedication();
        this.fetchDoctor();
        this.fetchCaregiver();
        this.fetchPatient();
        this.fetchMedication();
        this.fetchMedicationPlan();
        this.fetchCustomMedication();
    }

    render() {
        return (
            <div>
                <CardHeader>
                    <strong> Doctor Management </strong>
                </CardHeader>
                <Card>
                    <br/>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            <Button color="success" onClick={this.toggleFormDoctor}>Add Doctor </Button>
                        </Col>
                    </Row>
                    <br/>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            {this.state.isLoadedDoctor && <DoctorTable tableData={this.state.tableDataDoctor}/>}
                            {this.state.errorStatus > 0 && <APIResponseErrorMessage
                                errorStatus={this.state.errorStatus}
                                error={this.state.error}
                            />}
                        </Col>
                    </Row>
                </Card>

                <Card>
                    <br/>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            <Button color="success" onClick={this.toggleFormCaregiver}>Add Caregiver </Button>
                        </Col>
                    </Row>
                    <br/>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            {this.state.isLoadedCaregiver &&
                            <CaregiverTable tableData={this.state.tableDataCaregiver}/>}
                            {this.state.errorStatus > 0 && <APIResponseErrorMessage
                                errorStatus={this.state.errorStatus}
                                error={this.state.error}
                            />}
                        </Col>
                    </Row>
                </Card>

                <Card>
                    <br/>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            <Button color="success" onClick={this.toggleFormPatient}>Add Patient </Button>
                        </Col>
                    </Row>
                    <br/>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            {this.state.isLoadedPatient && <PatientTable tableData={this.state.tableDataPatient}/>}
                            {this.state.errorStatus > 0 && <APIResponseErrorMessage
                                errorStatus={this.state.errorStatus}
                                error={this.state.error}
                            />}
                        </Col>
                    </Row>
                </Card>

                <Card>
                    <br/>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            <Button color="success" onClick={this.toggleFormMedication}>Add Medication </Button>
                        </Col>
                    </Row>
                    <br/>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            {this.state.isLoadedMedication &&
                            <MedicationTable tableData={this.state.tableDataMedication}/>}
                            {this.state.errorStatus > 0 && <APIResponseErrorMessage
                                errorStatus={this.state.errorStatus}
                                error={this.state.error}
                            />}
                        </Col>
                    </Row>
                </Card>

                <Card>
                    <br/>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            <Button color="success" onClick={this.toggleFormMedicationPlan}>Add Medication
                                Plan </Button>
                        </Col>
                    </Row>
                    <br/>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            {this.state.isLoadedMedicationPlan &&
                            <MedicationPlanTable tableData={this.state.tableDataMedicationPlan}/>}
                            {this.state.errorStatus > 0 && <APIResponseErrorMessage
                                errorStatus={this.state.errorStatus}
                                error={this.state.error}
                            />}
                        </Col>
                    </Row>
                </Card>

                <Card>
                    <br/>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            <Button color="success" onClick={this.toggleFormCustomMedication}>Add Custom
                                Medication </Button>
                        </Col>
                    </Row>
                    <br/>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            {this.state.isLoadedCustomMedication &&
                            <CustomTable tableData={this.state.tableDataCustomMedication}/>}
                            {this.state.errorStatus > 0 && <APIResponseErrorMessage
                                errorStatus={this.state.errorStatus}
                                error={this.state.error}
                            />}
                        </Col>
                    </Row>
                </Card>

                <Modal isOpen={this.state.selectedDoctor} toggle={this.toggleFormDoctor}
                       className={this.props.className} size="lg">
                    <ModalHeader toggle={this.toggleFormDoctor}> Add Doctor: </ModalHeader>
                    <ModalBody>
                        <DoctorForm reloadHandler={this.reloadDoctor}/>
                    </ModalBody>
                </Modal>
                <Modal isOpen={this.state.selectedCaregiver} toggle={this.toggleFormCaregiver}
                       className={this.props.className} size="lg">
                    <ModalHeader toggle={this.toggleFormCaregiver}> Add Caregiver: </ModalHeader>
                    <ModalBody>
                        <CaregiverForm reloadHandler={this.reloadCaregiver}/>
                    </ModalBody>
                </Modal>

                <Modal isOpen={this.state.selectedPatient} toggle={this.toggleFormPatient}
                       className={this.props.className} size="lg">
                    <ModalHeader toggle={this.toggleFormPatient}> Add Patient: </ModalHeader>
                    <ModalBody>
                        <PatientForm reloadHandler={this.reloadPatient}/>
                    </ModalBody>
                </Modal>
                <Modal isOpen={this.state.selectedMedication} toggle={this.toggleFormMedication}
                       className={this.props.className} size="lg">
                    <ModalHeader toggle={this.toggleFormMedication}> Add Medication: </ModalHeader>
                    <ModalBody>
                        <MedicationForm reloadHandler={this.reloadMedication}/>
                    </ModalBody>
                </Modal>

                <Modal isOpen={this.state.selectedMedicationPlan} toggle={this.toggleFormMedicationPlan}
                       className={this.props.className} size="lg">
                    <ModalHeader toggle={this.toggleFormMedicationPlan}> Add Medication Plan: </ModalHeader>
                    <ModalBody>
                        <MedicationPlanForm reloadHandler={this.reloadMedicationPlan}/>
                    </ModalBody>
                </Modal>

                <Modal isOpen={this.state.selectedCustomMedication} toggle={this.toggleFormCustomMedication}
                       className={this.props.className} size="lg">
                    <ModalHeader toggle={this.toggleFormCustomMedication}> Add Custom Medication: </ModalHeader>
                    <ModalBody>
                        <CustomForm reloadHandler={this.reloadCustomMedication}/>
                    </ModalBody>
                </Modal>

            </div>
        )

    }
}

export default DoctorContainer;
