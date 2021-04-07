import React from "react"
import Table from "../../commons/tables/table";

const columns = [
    {
        Header: 'id',
        accessor: 'id',
        show: false
    },
    {
        Header: 'Username',
        accessor: 'username'
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
        Header: 'Caregiver id',
        accessor: 'caregiver.id',
        show: false
    }

];

const filters = [
    {
        accessor: 'name',
    }
];

class CaregiverPatientsTable extends React.Component {
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

export default CaregiverPatientsTable;