import axios from 'axios';

export default {
	sendWelcome(campers, template) {
		return axios.post('/emails/welcome', campers, template);
	},
	sendInvoice(campers, template) {
		console.log("We're in the service");
		return axios.post('/emails/invoice', campers, template);
	},
	sendNews(campers, template) {
		return axios.post('/emails/newsletter', campers, template);
	}
};
