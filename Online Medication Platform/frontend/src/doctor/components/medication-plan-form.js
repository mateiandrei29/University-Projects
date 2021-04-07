import React from 'react';
import validate from "../../commons/validator";
import Button from "react-bootstrap/Button";
import * as API_MEDICATIONPLAN from "../api/medication-plan-api";
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";
import {Col, Row} from "reactstrap";
import {FormGroup, Input, Label} from 'reactstrap';


class MedicationPlanForm extends React.Component {
    constructor(props) {
        super(props);
        this.toggleForm = this.toggleForm.bind(this);
        this.reloadHandler = this.props.reloadHandler;

        this.state = {

            errorStatus: 0,
            error: null,

            formIsValid: false,

            formControls: {
                name: {
                    value: '',
                    placeholder: 'Name...',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true,
                        minLength: 5
                    }
                },

                startDate: {

                    value: '',
                    placeholder: 'Start Date...',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true
                    }
                },
                doctor: {
                    value: '',
                    placeholder: 'Doctor Name...',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true,
                        minLength: 3
                    }
                },


                patient: {
                    value: '',
                    placeholder: 'Patient name...',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true,
                        minLength: 3
                    }
                },
            }
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    toggleForm() {
        this.setState({collapseForm: !this.state.collapseForm});
    }

    handleChange = event => {

        const name = event.target.name;
        const value = event.target.value;

        const updatedControls = this.state.formControls;

        const updatedFormElement = updatedControls[name];

        updatedFormElement.value = value;
        updatedFormElement.touched = true;
        updatedFormElement.valid = validate(value, updatedFormElement.validationRules);
        updatedControls[name] = updatedFormElement;

        let formIsValid = true;
        for (let updatedFormElementName in updatedControls) {
            formIsValid = updatedControls[updatedFormElementName].valid && formIsValid;
        }

        this.setState({
            formControls: updatedControls,
            formIsValid: formIsValid
        });

    };

    registerMedicationPlan(medicationPlan) {
        return API_MEDICATIONPLAN.postMedicationPlan(medicationPlan, (result, status, error) => {


            if (result !== null && (status === 200 || status === 201)) {
                console.log("Successfully inserted medication plan with id: " + result);
                this.reloadHandler();
            } else {
                this.setState(({
                    errorStatus: status,
                    error: error
                }));
            }
        });
    }

    handleSubmit() {
        let medicationPlan = {
            name: this.state.formControls.name.value,
            startDate: this.state.formControls.startDate.value,
            doctor:{
                name: this.state.formControls.doctor.value
            },
            patient:{
                name:this.state.formControls.patient.value
            }
        };

        console.log(medicationPlan);
        this.registerMedicationPlan(medicationPlan);
    }



    render() {
        return (
            <div>

                <FormGroup id='name'>
                    <Label for='nameField'> Name: </Label>
                    <Input name='name' id='nameField'
                           placeholder={this.state.formControls.name.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.name.value}
                           touched={this.state.formControls.name.touched ? 1 : 0}
                           valid={this.state.formControls.name.valid}
                           required
                    />
                </FormGroup>

                <FormGroup id='startDate'>
                    <Label for='startDateField'> Start Date: </Label>
                    <Input name='startDate' id='startDateField'
                           placeholder={this.state.formControls.startDate.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.startDate.value}
                           touched={this.state.formControls.startDate.touched ? 1 : 0}
                           valid={this.state.formControls.startDate.valid}
                           required
                    />

                </FormGroup>

                <FormGroup id='doctor'>
                    <Label for='doctorField'> Doctor name: </Label>
                    <Input name='doctor' id='doctorField' placeholder={this.state.formControls.doctor.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.doctor.value}
                           touched={this.state.formControls.doctor.touched? 1 : 0}
                           valid={this.state.formControls.doctor.valid}
                           required
                    />
                </FormGroup>

                <FormGroup id='patient'>
                    <Label for='patientField'> Patient name: </Label>
                    <Input name='patient' id='patientField' placeholder={this.state.formControls.patient.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.patient.value}
                           touched={this.state.formControls.patient.touched? 1 : 0}
                           valid={this.state.formControls.patient.valid}
                           required
                    />
                </FormGroup>
                <Row>
                    <Col sm={{size: '4', offset: 8}}>
                        <Button type={"submit"} disabled={!this.state.formIsValid}
                                onClick={this.handleSubmit}> Submit </Button>
                    </Col>
                </Row>

                {
                    this.state.errorStatus > 0 &&
                    <APIResponseErrorMessage errorStatus={this.state.errorStatus} error={this.state.error}/>
                }
            </div>
        );
    }
}

export default MedicationPlanForm;