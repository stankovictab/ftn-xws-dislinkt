// import api from "./api"

export async function register(newUser){
    console.log("requests.ts/register()", newUser);
    // return api().post('register', newUser).then(
    //     res => { console.log(res) }
    // );
}

export async function login(credentials){
    console.log("requests.ts/login()", credentials);
    if(["Admin", "Client", ""].includes(credentials.username)){
        return {data: {token: "1234567890", user: {role: credentials.username}}};
    }
    // return api().post('login', credentials).then();
}

