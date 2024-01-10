<script setup>
    import PageContainer from "../../components/PageContainer.vue";
    import MedicationList from "../../components/MedicationList.vue";
import MedicationCard from "@/components/MedicationCard.vue";
import OrdonnanceCard from "@/components/OrdonnanceCard.vue";
import CommandeCard from "@/components/CommandeCard.vue";
</script>

<template>
    <PageContainer class="page-container">
        <h1> Bienvenue dans My Pharmacy </h1>

        <div class="home-page-container">

            <!--Medicaments section-->
            <section>
                <div class="section-header">
                    <h1> Commandez sans ordonnace </h1>
                    <RouterLink to="/patient/medicaments" class="section-link"> Voir plus </RouterLink>
                </div>
                
                <div class="meds-grid">
                
                    <MedicationCard v-for="i in [0, 1, 2, 3]" brandName="Aureomycine"
                    medicationName="Pommade ophtalmique"
                    description="Aureomycin 1% – Pommade ophtalmique 5g"
                    :price="9.99"
                    imagePath="/src/assets/placeholders/pomade-jaune.png"/>
                
                </div>
            </section>
  
            <div class="line-separation" style="width: 90%"> </div>

            <!--Ordonnances section-->
            <section>
                <div class="section-header">
                    <h1> Vos dernieres ordonnances </h1>
                    <RouterLink to="/patient/ordonnances" class="section-link"> Voir plus </RouterLink>
                </div>
            
                <div class="list">
                    <OrdonnanceCard v-for="i in [0, 1, 2]" />
                </div>
            </section>

            <div class="line-separation" style="width: 90%"> </div>

            <!--Commandes section-->
            <section>
                <div class="section-header">
                    <h1> Vos dernieres commandes </h1>
                    <RouterLink to="/patient/commandes" class="section-link"> Voir plus </RouterLink>
                </div>
        
                <div class="list">
                    <CommandeCard v-for="i in [0, 1, 2]"
                    :commandeId="15332"
                    date="XX-XX-XXXX"
                    :stade="i"/>
                </div>
            </section>
        </div>
    </PageContainer>
</template>

<style scoped>
    @import "@/assets/styles/colors.css";

    .page-container h1 {
        text-align: center;
        font-size: 4vh;
        font-weight: 900;
        margin-bottom: 64px;
    }

    .home-page-container {
        flex: auto;
        width: 100%;
        background-color: var(--secondary-bg-color);
    
        border-top-right-radius: 2vh ;
        border-top-left-radius: 2vh ;

        display: flex;
        flex-direction: column;
        align-items: center;

        gap: 3vh;
    }

    section {
        width: 100%;
    }

    .section-header {
        display: flex;
        align-items: center;
        justify-content: space-between;

        padding: calc(2vh + 1vw);
    }

    .section-header h1 {
        font-size: 3vh;
        padding: 0;
        margin: 0;
    }

    .section-link {
        font-size: 3vh;
        padding: 0;
        color: var(--main-color);
        text-decoration: underline;
        font-weight: 700;
    }

    .meds-grid {
        width: 100%;
        display: grid;
        /* thanks to chat-gpt */
        grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
    }
</style>