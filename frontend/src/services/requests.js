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
                username: "Mateja Miletic",
                avatar: "Mateja.png",
                
            },
            {
                username: "Aleksandar Vucic",
                avatar: "Precednik.jpg"
            }
        ]
    }
    // return api().get('friends', user).then(
    //     res => { console.log(res) }
    // );
}

export async function getFeed(){
    console.log("requests.ts/getFeed()");
    return {
        data: [
            {   
                id: 0,
                username: "Mateja Miletic",
                avatar: "Mateja.png",
                text: "We must secure the existence of our people and a future for white children.",
                votes: 420,
            },
            {
                id: 1,
                username: "Aleksandar Vucic",
                avatar: "Precednik.jpg",
                text: "Hvala Srbijo!",
                votes: -5000000
            },

        ]
    }
    // return api().get('feed', user).then(
    //     res => { console.log(res) }
    // );
}