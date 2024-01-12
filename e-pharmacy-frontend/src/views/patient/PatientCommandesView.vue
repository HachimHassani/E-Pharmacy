<script setup>
    import CommandeCard from '@/components/CommandeCard.vue';
    import Loading from '@/components/Loading.vue';
    import PageContainer from '@/components/PageContainer.vue';
</script>

<template>
    <Loading :isLoading="isLoading"/>

    <PageContainer>
        <h1> Vos commandes </h1>

        <div class="commandes-list">
            <CommandeCard v-for="commande in commandes"
            :commandeId="commande.commandeId" 
            :date="commande.date"
            :stade="commande.stade"
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

<script>
    export default {
        data() {
            return {
                isLoading: true,
                commandes: []
            }
        },
        mounted() {
            fetch('/src/assets/placeholders/commandes.json')
                .then((response) => response.json())
                .then((json) => {
                    setTimeout(() => {
                        this.commandes = json;
                        this.isLoading = false;
                    }, 700);
                });
        }
        
    }
</script>