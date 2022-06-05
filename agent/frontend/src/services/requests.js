import api from "./api";

// export async function register(newUser) {
// 	console.log("requests.ts/register()", newUser);
// 	// return api("5001").post('register', newUser).then(
// 	//     res => { console.log(res) }
// 	// );
// }

export async function login(credentials) {
	// console.log("requests.ts/login()", credentials);
	return api("5003").post('agent/login', credentials).then(
		function (response) {
			return response;
		}
	);
}

export async function getFriends() {
	// console.log("requests.ts/getFriends()");
	return {
		data: [
			{
				username: "John Doe",
				avatar: "placeholder.png"
			},
			{
				username: "Jane Doe",
				avatar: "placeholder.png"
			}
		]
	};
	// return api("5001").get('friends', user).then(
	//     res => { console.log(res) }
	// );
}

export async function getFeed(userId) {
	const data = await api("5002").post('post/getFeed', userId,
		{
			headers: { "Content-Type": "plain/text" },
		}).then(
			function (response) {
				return response.data;
			}
		);
	return data;
}

export async function getOwnPosts(userId) {
	const data = await api("5002").post('post/getAll', { userId, userPostsId: userId },
		{
			headers: { "Content-Type": "application/json", },
		}).then(
			function (response) {
				return response.data;
			}
		);
	return data;
}

export async function createOffer(firmId, jobTitle, jobDescription, dislinktShare) {
	const data = await api("5003").post(
		'/offer/create',
		{
			firmId,
			jobTitle,
			jobDescription,
			dislinktShare
		},
		{
			headers: { "Content-Type": "application/json", },
		}).then(
			function (response) {
				return response.data;
			}
		);
	return data;
}
