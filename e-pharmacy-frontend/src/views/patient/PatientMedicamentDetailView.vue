<script setup>
    import AddToCartButton from '@/components/AddToCartButton.vue';
    import PageContainer from '@/components/PageContainer.vue';
    import { formatPrice } from '@/scripts/Format';
    import Loading from '@/components/Loading.vue';
</script>

<template>
    <PageContainer class="page-container">
        <Loading :isLoading="isLoading"/>
        
        <img :src="medicament.imagePath"/>

        <div class="medicament-detail">
            <h1> {{ medicament.brandName }} </h1>

            <h2> {{ medicament.medicationName }} </h2>

            <div class="line-separation"/>

            <div class="description">
                {{ medicament.description }}
            </div>

            <div class="line-separation"/>

            <div class="price">
                {{ formatedPrice }}
            </div>

            <div class="state">
                <img src="/icons/ok.svg"/>
                En Stock
            </div>

            <div class="add-container">
                <input type="number" min="1" max="9999" value="1"/>
                <AddToCartButton 
                :item="{
                    'title': medicament.brandName,
                    'subTitle': medicament.medicationName,
                    'price': medicament.price
                }" style="flex: auto;"/>
            </div>
        </div>

    </PageContainer>
</template>

<style scoped>
    .page-container img {
        transform: rotate(8deg);
        width: 80%;
        max-width: 600px;
    }

    .medicament-detail {
        z-index: 1;

        width: calc(98% - 6vh);
        display: flex;
        flex-direction: column;

        margin-top: 1vh;
        padding: 2vh;

        background-color: var(--secondary-bg-color);
        border-top-right-radius: 15px;
        border-top-left-radius: 15px;

        gap: 2vh;

        padding: 3vh;
    }

    h1 {
        font-size: calc(3.3vh + 0.2vw);
        font-weight: 900;
        margin: 0;
    }

    h2 {
        font-size: calc(3.1vh + 0.2vw);
        font-weight: 700;
        margin: 0;
    }

    .description {
        font-size: calc(2.0vh + 0.1vw);
        font-weight: 500;
        margin-bottom: 64px;
    }

    .price {
        text-align: end;

        font-size: calc(2.6vh + 0.3vw);
        font-weight: 700;
        margin: 0;
    }

    /** state */

    .state {
        display: flex;
        align-items: center;
        justify-content: center;

        background-color: #0084ff41;
        color: var(--main-color);

        padding: 1vh;
        border-radius: 1vh;

        font-size: 2.5vh;
        font-weight: 700;

        height: 36px;

        gap: calc(1vw + 1vh);
    }

    .state img {
        width: 4vh;
    }

    /** buy container */
    .add-container {
        width: 100%;
        height: 64px;
        padding: 0px;

        display: flex;
        gap: calc(1vw +  1vh);
    }

    .add-container input {
        font-size: 2.5vh;
        font-weight: 700;

        padding: 0.6vh;
        padding-left: calc(0.5vw + 1vh);
    }

</style>

<script>
    export default {
        data() {
            return {
                medicament: {
                    brandName: "",
                    medicationName: "",
                    description: "",
                    price: 0,
                    imagePath: ""
                },
                isLoading: true
            }
        },

        mounted() {
            fetch('/placeholders/medicaments.json')
                .then((response) => response.json())
                .then((json) => {
                    setTimeout(() => {
                        this.medicament = json[this.$route.params.id];
                        this.isLoading = false;
                    }, Math.random() * 400);
                });
        },

        computed: {
            formatedPrice() {
                return formatPrice(this.medicament.price);
            }
        }
    }
</script>