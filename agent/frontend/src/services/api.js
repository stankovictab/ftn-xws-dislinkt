import axios from "axios";

export default (port) => {
	return axios.create({
		// TODO: Change over to gateway's URL
		baseURL: "http://localhost:" + port
	})
}