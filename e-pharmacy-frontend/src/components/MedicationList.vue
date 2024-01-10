<script setup>
    import Loading from './Loading.vue';
    import Searchbar from './Searchbar.vue';
</script>

<template>

    <div class="meds-container">
        
        <Searchbar class="searchbar-container" placeholder="Chercher un medicament" @onSearch="onSearch()"/>
        
        <Loading :isLoading="isLoading"/>
        
        <div class="meds-grid" ref="medsGrid">
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

<script>
    import MedicationCard from './MedicationCard.vue';
    import {defineComponent, createApp} from 'vue'

    export default {

        data() {
            return {
                isLoading: true,
                medciations: [],
                medicationCards: []
            }
        },

        mounted() {
            this.getMedications();

            window.addEventListener('scrollend', () => {
                this.addMedicationCards();
            });
        },

        methods: {
            onSearch() {
                this.clearMedicationCards();
                this.getMedications();
            },

            getMedications() {
                this.isLoading = true;
                setTimeout(() => {
                    this.addMedicationCards();
                    this.isLoading = false;
                }, 1600);
            },

            addMedicationCards() {
                for (let i = 0; i < 8; i++) {
                    // create card element
                    const card = document.createElement('div');
                    this.$refs.medsGrid.appendChild(card);

                    // add card element
                    createApp(MedicationCard, {
                        brandName: "Aureomycine",
                        medicationName: "Pommade ophtalmique",
                        description: "Aureomycin 1% – Pommade ophtalmique 5g",
                        price: 9.99,
                        imagePath: "/src/assets/placeholders/pomade-jaune.png",
                        showDelai: 50 * i
                    }).mount(card);
                }
            },

            clearMedicationCards() {
                this.$refs.medsGrid.innerHTML = "";
            }
        }
    }
</script>