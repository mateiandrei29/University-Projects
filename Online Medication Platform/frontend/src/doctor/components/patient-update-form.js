import React from 'react';
import validate from "../../commons/validator";
import Button from "react-bootstrap/Button";
import * as API_PATIENT from "../../patient/api/patient-api";
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";
import {Col, Row} from "reactstrap";
import {FormGroup, Input, Label} from 'reactstrap';


class PatientUpdateForm extends React.Component {
    constructor(props) {
        super(props);
        this.toggleForm = this.toggleForm.bind(this);
        this.reloadHandler = this.props.reloadHandler;
        this.state = {

            errorStatus: 0,
            error: null,

            formIsValid: false,

            formControls: {
                id: {
                    value: this.props.patientInfo.id,
                    placeholder: 'Id...',
                    valid: true,
                    touched: false,
                    show: false
                },
                username: {
                    value: this.props.patientInfo.username,
                    placeholder: 'Username...',
                    valid: true,
                    touched: false,
                    validationRules: {
                        isRequired: true,
                        minLength: 5
                    }
                },

                password: {

                    value: this.props.patientInfo.password,
                    placeholder: 'Password...',
                    valid: true,
                    touched: false,
                    validationRules: {
                        isRequired: true
                    }
                },
                email: {
                    value: this.props.patientInfo.email,
                    placeholder: 'Email...',
                    valid: true,
                    touched: false,
                    validationRules: {
                        emailValidator: true
                    }
                },
                name: {
                    value: this.props.patientInfo.name,
                    placeholder: 'Name...',
                    valid: true,
                    touched: false,
                    validationRules: {
                        isRequired: true,
                        minLength: 3
                    }
                },
                birthDate: {
                    value: this.props.patientInfo.birthDate,
                    placeholder: 'Birth Date...',
                    valid: true,
                    touched: false,
                    validationRules: {
                        isRequired: true,
                        minLength: 3
                    }
                },
                address: {
                    value: this.props.patientInfo.address,
                    placeholder: 'Address...',
                    valid: true,
                    touched: false,
                    validationRules: {
                        isRequired: true,
                        minLength: 3
                    }
                },
                caregiver: {
                    value: this.props.patientInfo.caregiver.name,
                    placeholder: 'Caregiver name...',
                    valid: true,
                    touched: false,
                    validationRules: {
                        isRequired: true,
                        minLength: 3
                    }
                }
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

    updatePatient(patient) {
        return API_PATIENT.putPatient(patient, (result, status, error) => {


            if (result !== null && (status === 200 || status === 201)) {
                console.log("Successfully updated patient with id: " + result);
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
        let patient = {
            id: this.state.formControls.id.value,
            username: this.state.formControls.username.value,
            password: this.state.formControls.password.value,
            email: this.state.formControls.email.value,
            name: this.state.formControls.name.value,
            birthDate: this.state.formControls.birthDate.value,
            address: this.state.formControls.address.value,
            caregiver: {
                name: this.state.formControls.caregiver.value
            }
        };

        console.log(patient);
        this.updatePatient(patient);
    }


    render() {
        return (
            <div>

                <FormGroup id='username'>
                    <Label for='usernameField'> Username: </Label>
                    <Input name='username' id='usernameField'
                           placeholder={this.state.formControls.username.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.username.value}
                           touched={this.state.formControls.username.touched ? 1 : 0}
                           valid={this.state.formControls.username.valid}
                           required
                    />

                </FormGroup>

                <FormGroup id='password'>
                    <Label for='passwordField'> Password: </Label>
                    <Input name='password' id='passwordField'
                           type="password"
                           placeholder={this.state.formControls.password.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.password.value}
                           touched={this.state.formControls.password.touched ? 1 : 0}
                           valid={this.state.formControls.password.valid}
                           required
                    />

                </FormGroup>

                <FormGroup id='email'>
                    <Label for='emailField'> Email: </Label>
                    <Input name='email' id='emailField' placeholder={this.state.formControls.email.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.email.value}
                           touched={this.state.formControls.email.touched ? 1 : 0}
                           valid={this.state.formControls.email.valid}
                           required
                    />
                    {this.state.formControls.email.touched && !this.state.formControls.email.valid &&
                    <div className={"error-message"}> * Email must have a valid format</div>}
                </FormGroup>

                <FormGroup id='name'>
                    <Label for='nameField'> Name: </Label>
                    <Input name='name' id='nameField' placeholder={this.state.formControls.name.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.name.value}
                           touched={this.state.formControls.name.touched ? 1 : 0}
                           valid={this.state.formControls.name.valid}
                           required
                    />
                </FormGroup>


                <FormGroup id='birthDate'>
                    <Label for='birthDateField'> Birth Date: </Label>

                    <Input name='birthDate' id='birthDateField'
                           placeholder={this.state.formControls.birthDate.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.birthDate.value}
                           touched={this.state.formControls.birthDate.touched ? 1 : 0}
                           valid={this.state.formControls.birthDate.valid}
                           required
                    />
                </FormGroup>

                <FormGroup id='address'>
                    <Label for='addressField'> Address: </Label>
                    <Input name='address' id='addressField' placeholder={this.state.formControls.address.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.address.value}
                           touched={this.state.formControls.address.touched ? 1 : 0}
                           valid={this.state.formControls.address.valid}
                           required
                    />
                </FormGroup>
                <FormGroup id='caregiver'>
                    <Label for='caregiverField'> Caregiver name: </Label>
                    <Input name='caregiver' id='caregiverField'
                           placeholder={this.state.formControls.caregiver.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.caregiver.value}
                           touched={this.state.formControls.caregiver.touched ? 1 : 0}
                           valid={this.state.formControls.caregiver.valid}
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

export default PatientUpdateForm;