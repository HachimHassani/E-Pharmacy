<script setup>
import AddToCartButton from '@/components/AddToCartButton.vue';
import PageContainer from '@/components/PageContainer.vue';
import PanierCard from '@/components/PanierCard.vue';
import PrintOrdonnanceButton from '@/components/PrintOrdonnanceButton.vue'
import ScreenTypes from '@/scripts/ScreenTypes';
</script>


<template>
    <PageContainer class="page-container">

        <!-- Section-->
        <div class="ordonnance-container">

            <h1> Votre ordonnance </h1>

            <!--Prix donnancee-->
            <div class="split-section" style="font-size: calc(2.4vh + 0.5vw);">
                <div class="left">
                    ID xxxxxxxxx
                </div>

                <div class="right" style="font-weight: 900;">
                    99,99 MAD
                </div>
            </div>

            <span class="line-separation" />

            <!--Infoordonnancee-->
            <div class="split-section">
                <div class="left">
                    Nom du medecin
                </div>

                <div class="right">
                    Dr. Tbib
                </div>
            </div>

            <div class="split-section">
                <div class="left">
                    Nom du patient
                </div>

                <div class="right">
                    Mr. mrid
                </div>
            </div>

            <div class="split-section">
                <div class="left">
                    Date
                </div>

                <div class="right">
                    XX/XX/XXXX
                </div>
            </div>

            <div class="split-section">
                <div class="left">
                    Lieu
                </div>

                <div class="right">
                    chi mdina
                </div>
            </div>

            <span class="line-separation" />

            <!--Prescription info-->
            <div class="split-section">
                <div class="left">
                    Prescription
                </div>
            </div>

            <PanierCard v-for="i in Array(3)" style="width: 100%;" />

            <div :class="{
                'buttons-list': true,
                'buttons-list-small': isSmall
                }">
                <AddToCartButton style="width: 100%;"/>
                <PrintOrdonnanceButton style="width: 100%;" />
            </div>

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

        width: 100%;
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
        computed: {
            isSmall() {
                return this.$globalProperties.screenType == ScreenTypes.Mobile;
            }
        }
    }
</script>