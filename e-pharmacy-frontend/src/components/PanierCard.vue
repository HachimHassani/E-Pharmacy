<script setup>
    import { RouterLink } from 'vue-router';
    import { formatPrice } from '@/scripts/Format';
    import PanierCardTypes from '@/scripts/PanierCardTypes';
</script>

<template>

    <div  v-if="enabled" :class="{
        'card-container': true,
        'card-container-disabled': !currentEnabled
    }">

        <div class="card">

            <div class="left">
                <h1> {{ title }} </h1>
                <h2> {{ subTitle }} </h2>
    
                <button v-if="canDelete" class="delete-button" @click="onDeleteClick()">
                    <img src="/icons/trash.svg" />
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
            canDelete: {
                type: Boolean,
                default: true
            },
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
            },
            enabled: {
                type: Boolean,
                default: true
            }

        },

        data() {
            return {
                currentEnabled: true,
            }
        },

        mounted() {
            this.currentEnabled = this.enabled;
        },

        methods: {
            onDeleteClick() {
                if (!this.currentEnabled) return;

                this.currentEnabled = false;
                // wait for animation to end
                setTimeout(() => {
                    this.$emit("onDelete");
                }, 1200);
            }
        },

        computed: {
            formatedPrice() {
                return formatPrice(this.price);
            }
        }
    }
</script>