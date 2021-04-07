import React from "react"
import Table from "../../commons/tables/table";
import {Button, Modal, ModalBody, ModalHeader} from "reactstrap";
import * as API_PATIENT from "../../patient/api/patient-api";
import * as API_DOCTOR from "../api/doctor-api";
import PatientUpdateForm from "./patient-update-form";
import DoctorUpdateForm from "./doctor-update-form";

const columns = [];

const filters = [
    {
        accessor: 'name',
    }
];

class DoctorTable extends React.Component {
    constructor(props) {
        super(props);
        this.toggleForm = this.toggleForm.bind(this);

        this.state = {
            tableData: this.props.tableData,
            columns: [
                {
                    Header: 'Username',
                    accessor: 'username'
                },
                {
                    Header: 'Password',
                    accessor: 'password'
                },
                {
                    Header: 'Email',
                    accessor: 'email'
                },
                {
                    Header: 'Name',
                    accessor: 'name'
                },
                {
                    Header: 'Birth Date',
                    accessor: 'birthDate'
                },
                {
                    Header: 'Address',
                    accessor: 'address'
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
            doctorInfo:''

        };
    }
    toggleForm(doctorInfo) {
        this.setState({selected: !this.state.selected});
        this.setState({doctorInfo: doctorInfo});
    }

    reload() {
        this.setState({
            isLoaded: false
        });
        window.location.reload(false);
    }
    handleDelete(doctorInfo) {
        return API_DOCTOR.deleteDoctor(doctorInfo.id,(result, status, err) => {
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
                    <ModalHeader toggle={this.toggleForm}> Update Doctor: </ModalHeader>
                    <ModalBody>
                        <DoctorUpdateForm doctorInfo={this.state.doctorInfo} reloadHandler={this.reload}/>
                    </ModalBody>
                </Modal>
            </div>
        )
    }

}

export default DoctorTable;