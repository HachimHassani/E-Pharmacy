import './assets/styles/main.css'

import { createApp, reactive } from 'vue'
import App from './App.vue'
import router from './router'
import ScreenTypes from './scripts/ScreenTypes'



const app = createApp(App)

const reactiveGlobalProperties = reactive({
    screenType: ScreenTypes.Desktop,
});
app.config.globalProperties.$globalProperties = reactiveGlobalProperties;

app.use(router)


app.mount('#app')
