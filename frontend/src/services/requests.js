// import api from "./api"

export async function register(newUser){
    console.log("requests.ts/register()", newUser);
    // return api().post('register', newUser).then(
    //     res => { console.log(res) }
    // );
}

export async function login(credentials){
    console.log("requests.ts/login()", credentials);
    const users = {
        'Client': {
            role: 'Client',
            username: 'Nikocado Fat Fuck',
            jobDesc: 'Professional Mukbang Enjoyer',
            company: 'YouTube',
            avatar: "Nikocado.jpg"
        },
        'Admin':{
            role: 'Admin',
            username: 'Tran S. Vestite',
            jobDesc: 'Discord Mod',
            company: 'MGZ',
        }

    }
    if(["Admin", "Client", ""].includes(credentials.username)){
        return {data: {token: "1234567890", user: users[credentials.username]}};
    }
    // return api().post('login', credentials).then();
}

export async function getFriends(){
    console.log("requests.ts/getFriends()");
    return {
        data: [
            {
                username: "Nikocado Fat Fuck",
                avatar: "Nikocado.jpg"
            },
            {
                username: "Nikocado Fat Fuck",
                avatar: "Nikocado.jpg"
            },
            {
                username: "Nikocado Fat Fuck",
                avatar: "Nikocado.jpg"
            }

        ]
    }
    // return api().get('friends', user).then(
    //     res => { console.log(res) }
    // );
}