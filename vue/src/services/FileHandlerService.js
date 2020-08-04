import axios from 'axios';

export default {
	uploadFile(file) {
		return axios.post('/file-upload', file, {
			headers: {
				'Content-Type': 'multipart/form-data'
			}
		});
	}
};
