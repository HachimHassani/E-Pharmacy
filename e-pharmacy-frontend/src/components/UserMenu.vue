<script setup>
    import Navlink from '@/components/Navlink.vue'
</script>

<template>
    <!--User icon-->
    <img :class="{
        'user-icon': true,
        'user-icon-active': isActive
    }" 
    src="@/assets/icons/profil.svg" 
    draggable="false"
    @click="onIconClick()" />

    <!--User menu-->
    <div class="user-menu-container">
        <div :class="{
            'user-menu': true,
            'user-menu-active': isActive
        }">
            <h1> Ksiks Mohamed Haitam </h1>

            <div class="line-separation"/>

            <Navlink class="navlink" iconPath="../assets/icons/commands.svg" to="/patient/commandes"> Commandes </Navlink>

            <Navlink class="navlink logout" iconPath="../assets/icons/logout.svg" to=""> Se Deconnecter </Navlink>
        </div>
    </div>
</template>

<style scoped>
    @import "@/assets/styles/colors.css";

    .user-icon {
        width: 48px;
        cursor: pointer;

        border-radius: 100%;
        outline: 0px solid var(--main-color);

        transition: all 0.1s ease-out;
    }

    .user-icon:hover {
        transform: scale(1.17);
    }

    .user-icon-active {
        outline: 4px solid var(--main-color);
    }

    .user-menu {
        position: absolute;
        top: 96px;
        right: 1vh;

        max-width: 320px;
        width: calc(100% - 2vh);

        border-radius: 1vh;

        background-color: white;
        box-shadow: 0 2px 16px 2px var(--shadow-color);

        padding: 1vh;

        display: flex;
        flex-direction: column;
        align-items: start;

        gap: 1vh;

        transition: all 0.27s ease-out;

        /**hide */
        transform: translateY(-100px);
        opacity: 0%;
        visibility: hidden;
    }

    .user-menu-active {
        transform: translateY(0);
        opacity: 100%;
        visibility:visible;
    }

    .logout {
        color: var(--error-color);
    }

</style>

<script>
    export default {
        data() {
            return {
                isActive: false
            }
        },

        mounted() {
            // on click everywhere
            window.addEventListener('click', () => {
                if (this.isActive)
                    this.hideUserMenu();
            });
        },
        
        methods: {
            onIconClick() {
                if (this.isActive) 
                    this.hideUserMenu();
                else
                    this.showUserMenu();
            },

            showUserMenu() {
                setTimeout(() => {
                    this.isActive = true;
                }, 0);
            },
            hideUserMenu() {
                this.isActive = false;
            }
        }
    }
</script>