import React from 'react'
import logo from './commons/images/icon.png';

import {
    Button,
    DropdownItem,
    DropdownMenu,
    DropdownToggle,
    Nav,
    Navbar,
    NavbarBrand,
    NavLink,
    UncontrolledDropdown
} from 'reactstrap';
import RaisedButton from "material-ui/RaisedButton";
import LoginService from "./login/login-service";

const textStyle = {
    color: 'white',
    textDecoration: 'none'
};

const NavigationBar = () => (
    <div>
        <Navbar color="dark" light expand="md">
            <NavbarBrand href="/">
                <img src={logo} width={"50"}
                     height={"35"} />
            </NavbarBrand>
            <Nav className="mr-auto" navbar>


                <UncontrolledDropdown nav inNavbar>
                    <DropdownToggle style={textStyle} nav caret>
                       Menu
                    </DropdownToggle>
                    <DropdownMenu right >

                        <DropdownItem>
                            {LoginService.isLoggedIn() &&LoginService.getRole()==="doctor" &&<NavLink href="/doctor">Doctor Page</NavLink>}
                        </DropdownItem>
                        <DropdownItem>
                            {LoginService.isLoggedIn() &&LoginService.getRole()==="caregiver" &&<NavLink href="/caregiver">Caregiver Page</NavLink>}
                        </DropdownItem>
                        <DropdownItem>
                                {LoginService.isLoggedIn() &&LoginService.getRole()==="patient" &&<NavLink href="/patient">Patient Page</NavLink>}
                        </DropdownItem>


                    </DropdownMenu>
                </UncontrolledDropdown>

                {!LoginService.isLoggedIn() && <Button style={textStyle} href="/login">Login</Button>}

                {LoginService.isLoggedIn() &&  <Button style={textStyle} href="/" onClick={LoginService.logout} >Logout</Button>}


            </Nav>
        </Navbar>
    </div>
);

export default NavigationBar
