import React from "react";
import Table from "../../commons/tables/table";


const columns = [
    {
        Header: 'Id',
        accessor: 'id',
        show:false
    },
    {
        Header: 'Medication Plan id',
        accessor: 'medicationPlan.id',
        show:false
    },
    {
        Header: 'Medication Plan Name',
        accessor: 'medicationPlan.name'
    },
    {
        Header: 'Dosage',
        accessor: 'dosage'
    },
    {
        Header: 'No. of days',
        accessor: 'noOfDays'
    },
    {
        Header: 'Times per Day',
        accessor: 'timesPerDay'
    },
    {
        Header: 'Medication Id',
        accessor: 'medication.id',
        show:false
    },
    {
        Header: 'Medication Name',
        accessor: 'medication.name'
    }

];






const filters = [
    {
        accessor: 'name',
    }
];

class CustomTable extends React.Component{
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

export default CustomTable;

