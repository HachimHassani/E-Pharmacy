<script setup>
    import Searchbar from './Searchbar.vue';
    import OrdonnanceCard from './OrdonnanceCard.vue';
import Loading from './Loading.vue';
import { getOrdonnancePrice } from '@/scripts/Ordonnance';
</script>

<template>
    
    <div class="ordonnances-container">
        <Loading :isLoading="isLoading"/>

        <Searchbar class="searchbar-container" placeholder="Cherchez votre ordonnance" />

        <div class="ordonnance-list">
            <OrdonnanceCard v-for="ordonnance in ordonnances" 
            :ordonnanceId="ordonnance.ordonnanceId"
            :nomMedecin="ordonnance.nomMedecin"
            :date="ordonnance.date"
            :lieu="ordonnance.lieu"
            :price="getOrdonnancePrice(ordonnance)"/>
        </div>
    </div>


</template>

<style scoped>
    @import "../assets/styles/colors.css";

    .ordonnances-container {
        border-top-right-radius: 16px;
        border-top-left-radius: 16px;
        background-color: var(--secondary-bg-color);
    
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .searchbar-container {
        width:95%;
    }

    .ordonnance-list {
        width: 100%;
        /*
        display: grid;
        grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
        */
    }

</style>

<script>
    export default {
        data() {
            return {
                isLoading: true,
                ordonnances: []
            }
        },

        mounted() {
            fetch('/placeholders/ordonnances.json')
                .then((response) => response.json())
                .then((json) => {
                    setTimeout(() => {
                        this.ordonnances = json;
                        this.isLoading = false;
                    }, 700);
                });
        },


    }
</script>