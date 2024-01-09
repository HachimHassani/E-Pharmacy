<script setup>
import CommandeCard from '@/components/CommandeCard.vue';
import PageContainer from '@/components/PageContainer.vue';

</script>

<template>
    <PageContainer>
        <h1> Vos commandes </h1>

        <div class="commandes-list">
            <CommandeCard v-for="i in Array(5)"/>
        </div>
    </PageContainer>
</template>

<style scoped>
    @import "@/assets/styles/colors.css";

    h1 {
        margin: 0px;
        font-size: calc(2.6vh + 0.3vw);
        font-weight: 800;
    }

    .commandes-list {
        margin-top: 6vh;

        width: 100%;
        flex: auto;

        border-top-right-radius: 16px;
        border-top-left-radius: 16px;
        background-color: var(--secondary-bg-color);
    
    }
</style>

