import api from "./api";

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
	const data = await api("5002").post('post/getAll', {userId, userPostsId: userId},
		{
			headers: { "Content-Type": "application/json", },
		}).then(
			function (response) {
				return response.data;
			}
		);
	return data;
}

export async function getFriendRequests(userId) {
	const data = await api("5001").post('user/getPendingRequests', userId,
		{
			headers: { "Content-Type":  "plain/text", },
		}).then(
			function (response) {
				return response.data;
			}
		);
	return data;
}

export async function approveFollow(ownerId, requesterId) {
	const data = await api("5001").post('user/approveFollow', {userId: ownerId, followerUserId: requesterId},
		{
			headers: { "Content-Type":  "application/json", },
		}).then(
			function (response) {
				return response.data;
			}
		);
	return data;
}

export async function declineFollow(ownerId, requesterId) {
	const data = await api("5001").post('user/blockUser', {userId: ownerId, toBlockUserId: requesterId},
		{
			headers: { "Content-Type":  "application/json", },
		}).then(
			function (response) {
				return response.data;
			}
		);
	return data;
}

export async function ratePost(postId, raterId, rating) {

	rating = rating === 1 ? 'like' : 'unlike';

	const data = await api("5002").post(`post/${rating}`, 
		{
			userId: raterId, 
			postId
		},
		{
			headers: { "Content-Type":  "application/json", },
		}).then(
			function (response) {
				console.log(response)
				return response.data;
			}
		);
	return data;
}
