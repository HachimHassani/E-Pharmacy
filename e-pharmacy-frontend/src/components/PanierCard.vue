<script setup>
    import { RouterLink } from 'vue-router';
    import { formatPrice } from '@/scripts/Format';
    import PanierCardTypes from '@/scripts/PanierCardTypes';
</script>

<template>

    <div :class="{
        'card-container': true,
        'card-container-disabled': !enabled
    }">

        <div class="card">

            <div class="left">
                <h1> {{ title }} </h1>
                <h2> {{ subTitle }} </h2>
    
                <button class="delete-button" @click="onDeleteClick()">
                    <img src="../assets/icons/trash.svg" />
                    <div> Supprimer </div>
                </button>
            </div>
    
            <div class="right">
                <h1> {{ formatedPrice }} </h1>
            </div>
        
        </div>

    </div>


</template>

<style scoped>
    @import "@/assets/styles/panier_card.css";
</style>

<script>
    export default {
        props: {
            cardType: {
                type: String,
                default: PanierCardTypes.Ordonnance
            },
            price: {
                type: Number,
                required: true
            },
            title: {
                type: String,
                required: true
            },
            subTitle: {
                type: String,
                required: true
            }

        },

        data() {
            return {
                enabled: true
            }
        },

        methods: {
            onDeleteClick() {
                this.enabled = false;
            }
        },

        computed: {
            formatedPrice() {
                return formatPrice(this.price);
            }
        }
    }
</script>