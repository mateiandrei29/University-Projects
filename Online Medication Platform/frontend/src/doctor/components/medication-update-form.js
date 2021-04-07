import React from 'react';
import validate from "../../commons/validator";
import Button from "react-bootstrap/Button";
import * as API_MEDICATION from "../api/medication-api";
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";
import {Col, Row} from "reactstrap";
import {FormGroup, Input, Label} from 'reactstrap';


class MedicationUpdateForm extends React.Component {
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
                    value: this.props.medicationInfo.id,
                    placeholder: 'Id...',
                    valid: true,
                    touched: false,
                    show: false
                },
                name: {
                    value: this.props.medicationInfo.name,
                    placeholder: 'Medication name...',
                    valid: true,
                    touched: false,
                    validationRules: {
                        isRequired: true
                    }
                },

                mg: {
                    value: this.props.medicationInfo.mg,
                    placeholder: 'How many mg?....',
                    valid: true,
                    touched: false,
                    validationRules: {
                        isRequired: true
                    }
                },
                sideEffects: {
                    value: this.props.medicationInfo.sideEffects,
                    placeholder: 'Side effects...',
                    valid: true,
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

    updateMedication(medication) {
        return API_MEDICATION.putMedication(medication, (result, status, error) => {

            if (result !== null && (status === 200 || status === 201)) {
                console.log("Successfully updated medication with id: " + result);
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
        let medication = {
            id: this.state.formControls.id.value,
            name: this.state.formControls.name.value,
            mg: this.state.formControls.mg.value,
            sideEffects: this.state.formControls.sideEffects.value
        };

        console.log(medication);
        this.updateMedication(medication);
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

                <FormGroup id='mg'>
                    <Label for='mgField'> Mg: </Label>
                    <Input name='mg' id='mgField'
                           placeholder={this.state.formControls.mg.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.mg.value}
                           touched={this.state.formControls.mg.touched ? 1 : 0}
                           valid={this.state.formControls.mg.valid}
                           required
                    />

                </FormGroup>

                <FormGroup id='sideEffects'>
                    <Label for='sideEffectsField'> Side Effects: </Label>
                    <Input name='sideEffects' id='sideEffectsField'
                           placeholder={this.state.formControls.sideEffects.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.sideEffects.value}
                           touched={this.state.formControls.sideEffects.touched ? 1 : 0}
                           valid={this.state.formControls.sideEffects.valid}
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

export default MedicationUpdateForm;