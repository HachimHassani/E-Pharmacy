import './assets/styles/main.css'

import { createApp, reactive } from 'vue'
import App from './App.vue'
import router from './router'
import ScreenTypes from './scripts/ScreenTypes'
import VueCookies from 'vue-cookies'

const app = createApp(App)

const reactiveGlobalProperties = reactive({
    screenType: ScreenTypes.Desktop
});
app.config.globalProperties.$globalProperties = reactiveGlobalProperties;

app.use(router)
app.use(VueCookies);

app.mount('#app')
