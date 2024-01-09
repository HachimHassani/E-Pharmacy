<script setup>
import PageContainer from "../../components/PageContainer.vue";
import MedicationList from "../../components/MedicationList.vue";
</script>

<template>
    <PageContainer class="page-container">
        <h1> Commandez sans ordonance </h1>
        <MedicationList class="medication-list" />
    </PageContainer>
</template>

<style scoped>

    .page-container h1 {
        text-align: center;
        font-size: 4vh;
        font-weight: 900;
        margin-bottom: 64px;
    }

    .medication-list {
        flex: auto;
        width: 100%;
    }
</style>