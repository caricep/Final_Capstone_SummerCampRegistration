import axios from 'axios';

export default {
	getAllCampers() {
		return axios.get('/campers');
	}
};
