import React from "react";
import Table from "../../commons/tables/table";
import _ from "lodash";

const columns = [
    {
        Header: 'Id',
        accessor: 'id',
        show: false
    },
    {
        Header: 'Name',
        accessor: 'name'
    },
    {
        Header: 'Start Date',
        accessor: 'startDate'
    },
    {
        Header: 'Doctor Name',
        accessor: 'doctor.name'
    },
    {
        Header: 'Medication',
        id: 'medi',
        accessor: data => {
            let customMedication = [];
            // _.map(data.customMedication, customMedication=>{
            //     customMedication.push(customMedication.dosage);
            // });
            _.map(data.customMedication, custom => {
                customMedication.push(custom.medication.name);
            });
            return customMedication.join(', ');
        }
    },
    {
        Header: 'Doctor id',
        accessor: 'doctor.id',
        show: false
    },

];

const filters = [
    {
        accessor: 'name',
    }
];

class PatientMediPlansTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            tableData: this.props.tableData
        };
    }

    render() {
        return (
            <Table
                data={this.state.tableData}
                columns={columns}
                search={filters}
                pageSize={5}
            />
        )
    }

}

export default PatientMediPlansTable;

