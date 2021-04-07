import React from "react";
import Table from "../../commons/tables/table";


const columns = [
    {
        Header: 'Id',
        accessor: 'id',
        show:false
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
        Header: 'Doctor id',
        accessor: 'doctor.id',
        show:false
    },
    {
        Header: 'Patient Name',
        accessor: 'patient.name'
    },
    {
        Header: 'Patient id',
        accessor: 'patient.id',
        show:false
    }
];

const filters = [
    {
        accessor: 'name',
    }
];

class MedicationPlanTable extends React.Component{
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

export default MedicationPlanTable;

