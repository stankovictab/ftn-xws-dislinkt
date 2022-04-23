import api from "./api"

export async function register(newUser) {
	console.log("requests.ts/register()", newUser);
	// return api("5001").post('register', newUser).then(
	//     res => { console.log(res) }
	// );
}

export async function login(credentials) {
	console.log("requests.ts/login()", credentials);
	return api("5001").post('user/login', credentials).then(
		function (response) {
			return response;
		}
	);
}

export async function getFriends() {
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
	// return api("5001").get('friends', user).then(
	//     res => { console.log(res) }
	// );
}

export async function getFeed(userId) {
	console.log("requests.ts/getFeed()");
	api("5002").post('post/getFeed', userId,
	{
		headers: { "Content-Type": "plain/text" },
	}).then(
		function (response) {
			console.log(response.data[0].description);
			return response;
		}
	);
	// return {
	// 	data: [
	// 		{
	// 			id: 0,
	// 			username: "Mateja Miletic",
	// 			avatar: "Mateja.png",
	// 			text: "We must secure the existence of our people and a future for white children.",
	// 			votes: 420,
	// 			comments: []
	// 		},
	// 		{
	// 			id: 1,
	// 			username: "Aleksandar Vucic",
	// 			avatar: "Precednik.jpg",
	// 			text: "Hvala Srbijo!",
	// 			votes: -5000000,
	// 			comments: [
	// 				{
	// 					id: 0,
	// 					avatar: 'Todor.png',
	// 					text: 'Penzionere treba istrebiti !!!',
	// 				},
	// 			]
	// 		},
	// 	]
	// }
	// return api().get('feed', user).then(
	//     res => { console.log(res) }
	// );
}