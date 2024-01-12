<script setup>
    import CompanyIcon from '@/components/CompanyIcon.vue';
    import { RouterLink } from 'vue-router';
</script>

<template>
    <div class="home-container">
        <div class="home">
            <CompanyIcon isBig="true"></CompanyIcon>
            
            <h1 class="home-title">
                Je suis un(e)
            </h1>           

            <div class="client-choices">

                <RouterLink class="main-btn choice" to="/patient"> 
                    Patient(e) 
                </RouterLink>

                <RouterLink class="main-btn choice" to="/patient"> 
                    Medecin
                </RouterLink>

                <RouterLink class="main-btn choice" to="/patient"> 
                    Pharmacie
                </RouterLink>

            </div>
        </div>
    </div>
</template>


<style scoped>
    @import "../assets/styles/colors.css";
    @import "../assets/styles/main.css";
    
    .home-container {
        padding: 0;
        margin: 0;

        width: 100%;
        height: 100%;

        display: flex;
        align-items: center;
        justify-content: center;
    }



    .home {
        width: 100%;
        height: 70vh;

        display: flex;
        align-items: center;
        justify-content: center;
        flex-direction: column;

        background-color: white;
        box-shadow: 0px 0px 64px 2px var(--shadow-color);
        
        padding: 16px;
    }

    .home-title {
        font-size: xxx-large;
    }

    .client-choices {
        display: flex;
        align-items: center;
        justify-content: space-between;

        width: 100%;
        max-width: 700px;

        gap: 16px;

        flex-wrap: wrap;
    }

    .choice {
        flex: auto;
    }

    .choice:hover {
        box-shadow: 0px 2px 16px 1px var(--shadow-color);
        text-align: center;

    }

</style>

<script>
    export default {

    }
</script>