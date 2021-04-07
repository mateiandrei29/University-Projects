import React from "react";
import Table from "../../commons/tables/table";
import {Button, Modal, ModalBody, ModalHeader} from "reactstrap";
import * as API_PATIENT from "../../patient/api/patient-api";
import PatientUpdateForm from "./patient-update-form";
import * as API_MEDICATION from "../api/medication-api";
import MedicationUpdateForm from "./medication-update-form";


const filters = [
    {
        accessor: 'name',
    }
];

class MedicationTable extends React.Component {
    constructor(props) {
        super(props);
        this.toggleForm = this.toggleForm.bind(this);


        this.state = {
            tableData: this.props.tableData,
            columns: [
                {
                    Header: 'id',
                    accessor: 'id',
                    show: false
                },
                {
                    Header: 'Name',
                    accessor: 'name'
                },
                {
                    Header: 'mg',
                    accessor: 'mg'
                },
                {
                    Header: 'Side Effects ',
                    accessor: 'sideEffects'
                },
                {
                    Header: "Update",
                    Cell: row => (
                        <div>
                            <Button color="info" onClick={() => this.toggleForm(row.original)}>Update</Button>

                        </div>
                    )
                },
                {
                    Header: "Delete",
                    Cell: row => (
                        <div>
                            <Button color="info" onClick={() => this.handleDelete(row.original)}>Delete</Button>

                        </div>
                    )
                }
            ],
            medicationInfo: ''
        };
    }

    toggleForm(medicationInfo) {
        this.setState({selected: !this.state.selected});
        this.setState({medicationInfo: medicationInfo});
    }

    reload() {
        this.setState({
            isLoaded: false
        });
        window.location.reload(false);

    }

    handleDelete(medicationInfo) {
        return API_MEDICATION.deleteMedication(medicationInfo.id,(result, status, err) => {
            if (result !== null && status === 200) {
                this.setState({
                    tableData: result,
                    isLoaded: true
                });
                window.location.reload(false);
            } else {
                this.setState(({
                    errorStatus: status,
                    error: err
                }));
            }

        });
    }

    render() {
        return (
            <div>
                <Table
                    data={this.state.tableData}
                    columns={this.state.columns}
                    search={filters}
                    pageSize={5}
                />

                <Modal isOpen={this.state.selected} toggle={this.toggleForm}
                       className={this.props.className} size="lg">
                    <ModalHeader toggle={this.toggleForm}> Update Medication: </ModalHeader>
                    <ModalBody>
                        <MedicationUpdateForm medicationInfo={this.state.medicationInfo} reloadHandler={this.reload}/>
                    </ModalBody>
                </Modal>
            </div>

        )
    }

}

export default MedicationTable;

