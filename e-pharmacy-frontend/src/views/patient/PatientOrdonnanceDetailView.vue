<script setup>
import AddToCartButton from '@/components/AddToCartButton.vue';
import PageContainer from '@/components/PageContainer.vue';
import PanierCard from '@/components/PanierCard.vue';
import PrintOrdonnanceButton from '@/components/PrintOrdonnanceButton.vue'
import { formatPrice } from '@/scripts/Format';
import ScreenTypes from '@/scripts/ScreenTypes';
import Loading from '@/components/Loading.vue';
</script>


<template>
    <PageContainer>

        <Loading :isLoading="isLoading" />

        <!-- Section-->
        <div ref="ordonnancePage" class="ordonnance-container">

            <h1> Votre ordonnance </h1>

            <!--Prix donnancee-->
            <div class="split-section" style="font-size: calc(2.4vh + 0.5vw);">
                <div class="left">
                    ID {{ ordonnance.ordonnanceId }}
                </div>

                <div class="right" style="font-weight: 900;">
                    {{ price }}
                </div>
            </div>

            <span class="line-separation" />

            <!--Infoordonnancee-->
            <div class="split-section">
                <div class="left">
                    Nom du medecin
                </div>

                <div class="right">
                    {{ ordonnance.nomMedecin }}
                </div>
            </div>

            <div class="split-section">
                <div class="left">
                    Nom du patient
                </div>

                <div class="right">
                    {{ ordonnance.nomPatient }}
                </div>
            </div>

            <div class="split-section">
                <div class="left">
                    Date
                </div>

                <div class="right">
                    {{ ordonnance.date }}
                </div>
            </div>

            <div class="split-section">
                <div class="left">
                    Lieu
                </div>

                <div class="right">
                    {{ ordonnance.lieu }}
                </div>
            </div>

            <span class="line-separation" />

            <!--Prescription info-->
            <div class="split-section">
                <div class="left">
                    Prescription
                </div>
            </div>

            <PanierCard v-for="medicament in ordonnance.medicaments" 
            :title="medicament.brandName"
            :subTitle="medicament.medicationName"
            :price="medicament.price"
            :canDelete="false"
            style="width: 100%;" />

            
        </div>

        <div :class="{
            'buttons-list': true,
            'buttons-list-small': isSmall
            }">
            <AddToCartButton :item="{
                'title': `ID ${ordonnance.ordonnanceId}`,
                'subTitle': ordonnance.nomMedecin,
                'price': price
            }" style="width: 100%;"/>
            <PrintOrdonnanceButton style="width: 100%;" @click="printOrdonnance()"/>
        </div>
        
    </PageContainer>
</template>

<style scoped>
    /** Page */
    h1 {
        text-align: center;
        font-size: 4vh;
        font-weight: 900;
        margin-bottom: 4vh;
    }


    /** ordonnance section */
    .ordonnance-container {
        width: calc(98% - 6vh);
        display: flex;
        flex-direction: column;
        align-items: center;

        margin-top: 1vh;
        padding: 2vh;

        border-top-right-radius: 15px;
        border-top-left-radius: 15px;
    }

    .split-section {
        display: flex;
        justify-content: space-between;

        width: 100%;
        padding: 2.5vh;

        font-size: calc(2.4vh + 0.3vw);
        font-weight: 900;
    }

    .right {
        font-weight: 500;
    }

    .buttons-list {
        display: flex;
        flex-direction:row-reverse;

        width: calc(98% - 6vh);
        padding: 2vh;
        gap: 1vh;
    }

    /** small screen */
    .buttons-list-small {
        flex-direction: column;
    }

</style>

<script>
    export default {
        data() {
            return {
                isLoading: true,
                ordonnance: {
                    ordonnanceId: 0,
                    nomMedecin: "",
                    nomPatient: "",
                    date: "00-00-0000",
                    lieu: "",
                    medicaments: []
                }
            }
        },

        mounted() {
            fetch('/src/assets/placeholders/ordonnances.json')
                .then((response) => response.json())
                .then((json) => {
                    setTimeout(() => {
                        this.ordonnance = json.filter(o => o.ordonnanceId == this.$route.params.id)[0];
                        this.isLoading = false;
                    }, 800);
                });
        },

        methods: {
            printOrdonnance() {
                let w = window.open();
                
                w.document.write(`
                <html>
                    <head>
                        ${document.head.innerHTML} 
                        <style>
                            /* Include styles from the current document */
                            @media print {
                                /* Define print-specific styles */
                                body::before {
                                    content: "Ordonnance ${ this.ordonnance.ordonnanceId }";
                                    display: block;
                                    margin-bottom: 10px;
                                }
                            }
                        </style>
                    </head>
                    <body>
                        <div style="width: 100%; display: flex; flex-direction: column; align-items: center;">
                            ${this.$refs.ordonnancePage.innerHTML}
                        </div>
                    </body>
                </html>
                `);

                w.document.close();
                w.setTimeout(function () {
                    w.print();
                    w.close();
                }, 1);          
            }
        },

        computed: {
            price() {
                let total = 0;

                this.ordonnance.medicaments.forEach((med) => {
                    total += med.price;
                });

                return formatPrice(total);
            },
            isSmall() {
                return this.$globalProperties.screenType == ScreenTypes.Mobile;
            }
        }
    }
</script>