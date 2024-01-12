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
import router from '@/router';

    export default {

        data() {
            return {
                isLoading: true,
                medications: [],
                page: 0,
                pageSize: 8
            }
        },

        mounted() {
            this.getMedications();

            window.addEventListener('scroll', () => {
                if (window.innerHeight + window.scrollY >= window.document.body.offsetHeight)
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
                
                fetch('/src/assets/placeholders/medicaments.json')
                    .then((response) => response.json())
                    .then((json) => {
                        setTimeout(() => {
                            this.medications = json;
                            this.addMedicationCards();
                            this.isLoading = false;
                        }, 600);
                    });
                
            },

            addMedicationCards() {
                for (let i = this.page * this.pageSize; i < (this.page + 1) * this.pageSize && i < this.medications.length; i++) {
                    // create card element
                    const card = document.createElement('div');
                    this.$refs.medsGrid.appendChild(card);

                    // add card element
                    const cardApp = createApp(MedicationCard, {
                        medicamentId: i,
                        brandName: this.medications[i].brandName,
                        medicationName: this.medications[i].medicationName,
                        description: this.medications[i].description,
                        price: this.medications[i].price,
                        imagePath: this.medications[i].imagePath,
                        showDelai: 50 * (i - this.page * this.pageSize)
                    });

                    cardApp.use(router);
                    cardApp.mount(card);
                }

                this.page++;
            },

            clearMedicationCards() {
                this.$refs.medsGrid.innerHTML = "";
            }
        }
    }
</script>