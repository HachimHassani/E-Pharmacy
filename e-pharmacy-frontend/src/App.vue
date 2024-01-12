<script setup>
    import { RouterLink, RouterView } from 'vue-router'
    import Navbar from './components/Navbar.vue';
    import ScreenTypes from './scripts/ScreenTypes';
</script>

<template>
    <header>
    </header>

    <Navbar></Navbar>

    <RouterView class="view-container"/>

</template>

<style scoped>
    .view-container {
        min-height: 100vh;
    }
</style>

<script>
    export default {
        mounted() {
            // init cookies
            this.$cookies.set('panier', []);

            // init screen type
            this.$globalProperties.screenType = this.getScreenType(window.innerWidth, window.innerHeight);

            // listen to window on change
            window.addEventListener('resize', (event) => {
                this.$globalProperties.screenType = this.getScreenType(window.innerWidth, window.innerHeight);
            }, true);
        },

        methods: {
            getScreenType(width, height) {
                if (width > 1200) {
                    return ScreenTypes.Desktop;
                }
                else if (width > 650) {
                    return ScreenTypes.Tablet;
                }
                else {
                    return ScreenTypes.Mobile;
                }
            }
        }
    }
</script>