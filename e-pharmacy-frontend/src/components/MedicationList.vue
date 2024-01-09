<script setup>
    import MedicationCard from './MedicationCard.vue';
    import Searchbar from './Searchbar.vue';
</script>

<template>

    <div class="meds-container">

        <Searchbar class="searchbar-container" />

        <div class="meds-grid">
            <MedicationCard v-for="i in Array(16)" />
        </div>
        
    </div>

</template>

<style scoped>
    @import "@/assets/styles/colors.css";

    .meds-container {
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

    .meds-grid {
        width: 100%;
        display: grid;
        /* thanks to chat-gpt */
        grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
    }

</style>