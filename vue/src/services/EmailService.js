import axios from 'axios';

export default {
	welcomeEmail(campers, templateId) {
		return axios.post('/welcome-email', campers, templateId);
	}
};
