<script setup>
    import PageContainer from '@/components/PageContainer.vue';
    import OrdonnanceList from '@/components/OrdonnanceList.vue';
</script>

<template>
    <PageContainer class="page-container">
        <h1> Vos ordonnaces </h1>

        <OrdonnanceList class="ordonnance-list"/>
    </PageContainer>
</template>

<style scoped>

    .page-container {
        display: flex;
        align-items: center;
        flex-direction: column;

    }

    .page-container h1 {
        text-align: center;
        font-size: 4vh;
        font-weight: 900;
        margin-bottom: 64px;
    }

    .ordonnance-list {
        flex: auto;
        width: 100%;
    }

</style>