<script setup>
    import ScreenTypes from '@/scripts/ScreenTypes';
    import CompanyIcon from './CompanyIcon.vue';
    import Navlink from './Navlink.vue';
    import UserMenu from './UserMenu.vue';
</script>

<template>
    <nav :class="{
        'navbar-scrolled': scrolled,
        'navbar-hidden': !isVisible
    }">
        <CompanyIcon :hideText="hideCompanyText" @click="$router.push('/patient')"></CompanyIcon>

        <div class="navbar-buttons">

            <Navlink iconPath="/icons/home.svg" :isBig="isNavlinkBig" to="/patient"> Acceuil </Navlink>

            <Navlink iconPath="/icons/meds.svg" :isBig="isNavlinkBig" iconSize="30" to="/patient/medicaments"> Medicaments </Navlink>

            <Navlink iconPath="/icons/notifs.svg" :isBig="isNavlinkBig" to="/patient/ordonnances"> Ordonnances </Navlink>

            <Navlink isBig="false" iconPath="/icons/cart.svg" :isBig="isNavlinkBig" iconSize="38" to="/patient/panier" ></Navlink>

            <UserMenu />
       
        </div>
    </nav>
</template>


<script>
    export default {
        data() {
            return {
                scrolled: false,
            }
        },

        mounted() {
            window.addEventListener('scroll', () => {
                this.scrolled = window.scrollY > 10;
            });
        },

        computed: {
            isNavlinkBig() {
                return this.$globalProperties.screenType == ScreenTypes.Desktop;
            },
            hideCompanyText() {
                return this.$globalProperties.screenType == ScreenTypes.Mobile;
            },
            isVisible() {
                return this.$route.path != '/'
            }
        }
    }
</script>


<style scoped>
    @import "../assets/styles/colors.css";

    nav {
        position: fixed;
        left: 0px;
        right: 0px;

        display: flex;
        padding: 12px;

        background: white;
        z-index: 1000;

        box-shadow: 0px 4px 16px 1px transparent;
    
        transition: all 0.8s;
    }

    .navbar-scrolled {
        box-shadow: 0px 4px 16px 1px var(--shadow-color);
    }

    .navbar-hidden {
        transform: translateY(-64px);
        opacity: 0%;
    }

    .navbar-buttons {
        flex: auto;
        display: flex;
        align-items: center;
        justify-content: end;
        gap: 2.2%;
    }
</style>