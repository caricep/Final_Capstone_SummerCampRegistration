import axios from 'axios';

export default {
	getHistory() {
		return axios.get('/history');
	}
};
