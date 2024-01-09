<script setup>
    import Searchbar from './Searchbar.vue';
    import OrdonnanceCard from './OrdonnanceCard.vue';
</script>

<template>

    <div class="ordonnances-container">

        <Searchbar class="searchbar-container" />

        <div class="ordonnance-list">
            <OrdonnanceCard />
            <OrdonnanceCard />
            <OrdonnanceCard />
            <OrdonnanceCard />
            <OrdonnanceCard />
            <OrdonnanceCard />
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