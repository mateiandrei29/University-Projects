class LoginService {

    isLoggedIn() {
        const id = localStorage.getItem('userId');

        return !!id;

    }

    logout(){
        localStorage.removeItem('username');
        localStorage.removeItem('role');
        localStorage.removeItem('userId');
    }

    getRole(){
        return localStorage.getItem('role');
    }

}

export default new(LoginService);