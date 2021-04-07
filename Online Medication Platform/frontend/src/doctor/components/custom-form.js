import React from 'react';
import validate from "../../commons/validator";
import Button from "react-bootstrap/Button";
import * as API_CUSTOM from "../api/custom-api";
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";
import {Col, Row} from "reactstrap";
import {FormGroup, Input, Label} from 'reactstrap';


class CustomForm extends React.Component {
    constructor(props) {
        super(props);
        this.toggleForm = this.toggleForm.bind(this);
        this.reloadHandler = this.props.reloadHandler;

        this.state = {

            errorStatus: 0,
            error: null,

            formIsValid: false,

            formControls: {
                dosage: {
                    value: '',
                    placeholder: 'Dosage...',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true,
                    }
                },

                noOfDays: {

                    value: '',
                    placeholder: 'No of days...',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true
                    }
                },
                timesPerDay: {
                    value: '',
                    placeholder: 'Times Per Day...',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true,
                    }
                },

                medication: {
                    value: '',
                    placeholder: 'Medication name...',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true,
                        minLength: 3
                    }
                },

                medicationPlan: {
                    value: '',
                    placeholder: 'Medication Plan name...',
                    valid: false,
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

    registerCustomMedication(customMedication) {
        return API_CUSTOM.postCustomMedication(customMedication, (result, status, error) => {


            if (result !== null && (status === 200 || status === 201)) {
                console.log("Successfully inserted custom medication with id: " + result);
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
        let customMedication = {
            dosage: this.state.formControls.dosage.value,
            noOfDays: this.state.formControls.noOfDays.value,
            timesPerDay: this.state.formControls.timesPerDay.value,
            medication:{
                name: this.state.formControls.medication.value
            },
            medicationPlan:{
                name:this.state.formControls.medicationPlan.value
            }
        };

        console.log(customMedication);
        this.registerCustomMedication(customMedication);
    }



    render() {
        return (
            <div>

                <FormGroup id='dosage'>
                    <Label for='dosageField'> Dosage: </Label>
                    <Input name='dosage' id='dosageField'
                           placeholder={this.state.formControls.dosage.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.dosage.value}
                           touched={this.state.formControls.dosage.touched ? 1 : 0}
                           valid={this.state.formControls.dosage.valid}
                           required
                    />
                </FormGroup>

                <FormGroup id='noOfDays'>
                    <Label for='noOfDaysField'> No. of days: </Label>
                    <Input name='noOfDays' id='noOfDaysField'
                           placeholder={this.state.formControls.noOfDays.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.noOfDays.value}
                           touched={this.state.formControls.noOfDays.touched ? 1 : 0}
                           valid={this.state.formControls.noOfDays.valid}
                           required
                    />

                </FormGroup>

                <FormGroup id='timesPerDay'>
                    <Label for='timesPerDayield'> Times Per Day: </Label>
                    <Input name='timesPerDay' id='timesPerDayField'
                           placeholder={this.state.formControls.timesPerDay.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.timesPerDay.value}
                           touched={this.state.formControls.timesPerDay.touched ? 1 : 0}
                           valid={this.state.formControls.timesPerDay.valid}
                           required
                    />

                </FormGroup>

                <FormGroup id='medication'>
                    <Label for='medicationField'> Medication name: </Label>
                    <Input name='medication' id='medicationField' placeholder={this.state.formControls.medication.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.medication.value}
                           touched={this.state.formControls.medication.touched? 1 : 0}
                           valid={this.state.formControls.medication.valid}
                           required
                    />
                </FormGroup>

                <FormGroup id='medicationPlan'>
                    <Label for='medicationPlanField'> Medication Plan name: </Label>
                    <Input name='medicationPlan' id='medicationPlanField' placeholder={this.state.formControls.medicationPlan.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.medicationPlan.value}
                           touched={this.state.formControls.medicationPlan.touched? 1 : 0}
                           valid={this.state.formControls.medicationPlan.valid}
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

export default CustomForm;