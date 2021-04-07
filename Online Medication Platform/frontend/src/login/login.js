import React from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import AppBar from 'material-ui/AppBar';
import RaisedButton from 'material-ui/RaisedButton';
import TextField from 'material-ui/TextField';
import {withRouter} from "react-router";

import * as API_LOGIN from "./api/login-api"
import * as API_CAREGIVER from "../caregiver/api/caregiver-api";

class Login extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: ''
        }
        this.handleClick = this.handleClick.bind(this);
    }

    handleClick() {
        // console.log(this.state.username)
        // console.log(this.state.password)
        let user = {
            username: this.state.username,
            password: this.state.password
        };
        this.login(user);
    }

    login(user) {
        return API_LOGIN.login(user, (result, status, error) => {

            if (result !== null && (status === 200 || status === 201)) {
                console.log("Successfully logged in: " + result);
                localStorage.setItem('username', result.username);
                localStorage.setItem('role', result.role);
                localStorage.setItem('userId', result.id);


                if (result.role === "doctor") {
                    this.props.history.push('/doctor');
                }
                if (result.role === "caregiver") {
                    this.props.history.push('/caregiver');
                }
                if (result.role === "patient") {
                    this.props.history.push('/patient');
                }
            } else {
                this.setState(({
                    errorStatus: status,
                    error: error,

                }));

            }
        });
    }

    render() {
        return (
            <div style={{
                position: 'absolute', left: '50%', top: '50%',
                transform: 'translate(-50%, -50%)'
            }}>
                <MuiThemeProvider>
                    <div>
                        <AppBar
                            title="Login"
                        />
                        <TextField
                            hintText="Enter your Username"
                            floatingLabelText="Username"
                            onChange={(event, newValue) => this.setState({username: newValue})}
                        />
                        <br/>
                        <TextField
                            type="password"
                            hintText="Enter your Password"
                            floatingLabelText="Password"
                            onChange={(event, newValue) => this.setState({password: newValue})}
                        />
                        <br/>
                        <RaisedButton label="Submit" primary={true} style={style}
                                      onClick={(event) => this.handleClick(event)}/>
                    </div>
                </MuiThemeProvider>
            </div>
        );
    }
}


const style = {
    margin: 15,
};
export default withRouter(Login);