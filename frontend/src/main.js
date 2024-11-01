import './assets/main.css';

import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import VueDatePicker from '@vuepic/vue-datepicker';
import ToastPlugin from 'vue-toast-notification';
import '@vuepic/vue-datepicker/dist/main.css';
import 'vue-toast-notification/dist/theme-bootstrap.css';

const app = createApp(App);

app.component('VueDatePicker', VueDatePicker);
app.use(router);
app.use(ToastPlugin);
app.mount('#app');
