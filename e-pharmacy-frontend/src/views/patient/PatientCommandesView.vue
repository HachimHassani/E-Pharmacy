<script setup>
    import CommandeCard from '@/components/CommandeCard.vue';
    import PageContainer from '@/components/PageContainer.vue';
</script>

<template>
    <PageContainer>
        <h1> Vos commandes </h1>

        <div class="commandes-list">
            <CommandeCard v-for="i in [0, 1, 2]"
            :commandeId="11472" 
            date="00/00/0000"
            :stade="i"
            />
        </div>
    </PageContainer>
</template>

<style scoped>
    @import "@/assets/styles/colors.css";

    h1 {
        margin: 0px;
        font-size: calc(2.6vh + 1vw);
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

