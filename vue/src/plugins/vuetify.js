import Vue from 'vue';
import Vuetify from 'vuetify/lib';

Vue.use(Vuetify);

// https://www.schemecolor.com/camping-time.php
export default new Vuetify({
	theme: {
		themes: {
			light: {
				primary: '#01a3ac',
				success: '#3B6B35',
				info: '#BFAA72',
				error: '#9F7B21'
			}
		}
	}
});
