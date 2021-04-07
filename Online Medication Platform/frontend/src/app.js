import React from 'react'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import NavigationBar from './navigation-bar'
import Home from './home/home';
import PersonContainer from './person/person-container'
import { Redirect } from 'react-router-dom';

import ErrorPage from './commons/errorhandling/error-page';
import styles from './commons/styles/project-style.css';
import DoctorContainer from "./doctor/doctor-container";
import Login from "./login/login";
import CaregiverPatientsContainer from "./caregiver/caregiver-patients-container";
import PatientMediPlansContainer from "./patient/patient-medi-plans-container";
import LoginService from "./login/login-service";

class App extends React.Component {


    render() {

        return (
            <div className={styles.back}>
                <Router>
                    <div>
                        <NavigationBar/>
                        <Switch>

                            <Route
                                exact
                                path='/'
                                render={() => <Home/>}
                            />
                            <Route
                                exact
                                path='/login'
                                render={props => (
                                    LoginService.isLoggedIn() ?
                                        <Redirect to="/" />
                                        :<Login {...props} />

                                )} />
                            />

                            <Route
                                exact
                                path='/person'
                                render={props => (
                                    LoginService.isLoggedIn() &&
                                    LoginService.getRole() === "doctor" ?
                                        <PersonContainer {...props} />
                                        :<Redirect to="/" />

                                )} />
                            <Route
                                exact
                                path='/caregiver'
                                render={props => (
                                    LoginService.isLoggedIn() &&
                                    LoginService.getRole() === "caregiver" ?
                                        <CaregiverPatientsContainer {...props} />
                                        :<Redirect to="/" />

                                )} />
                            />

                            <Route
                                exact
                                path='/patient'
                                render={props => (
                                    LoginService.isLoggedIn() &&
                                    LoginService.getRole() === "patient" ?
                                        <PatientMediPlansContainer {...props} />
                                        :<Redirect to="/" />

                                )} />
                            />
                            <Route
                                exact
                                path='/doctor'
                                render={props => (
                                    LoginService.isLoggedIn() &&
                                    LoginService.getRole() === "doctor" ?
                                        <DoctorContainer {...props} />
                                        :<Redirect to="/" />

                                )} />
                            />

                            {/*Error*/}
                            <Route
                                exact
                                path='/error'
                                render={() => <ErrorPage/>}
                            />

                            <Route render={() => <ErrorPage/>}/>
                        </Switch>
                    </div>
                </Router>
            </div>
        )
    };
}

export default App
