import axios from 'axios';

export default {
	sendEmail(campers, templateId) {
		return axios.post('/emails', campers, templateId);
	}
};
