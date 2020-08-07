import axios from 'axios';

export default {
	getAllCampers() {
		return axios.get('/campers');
	},
	updateCampers(camper) {
		return axios.put('/campers', camper);
	}
};
