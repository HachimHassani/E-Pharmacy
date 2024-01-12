<script setup>
</script>

<template>
    <button class="ajouter main-btn" @click="onClick()">
        <img src="/icons/cart-white.svg" />
        Ajouter au panier
    </button>
</template>

<style scoped>

    .ajouter {
        padding: 1.5vh;
        font-size: 2.5vh;

        display: flex;
        justify-content: center;
        align-items: center;
        gap: 0.5vw;

    }

    .ajouter img {
        width: calc(0.7vw + 1.8vh);
    }
</style>

<script>
    export default {
        props: {
            item: {
                type: Object
            }
        },

        methods: {
            onClick() {
                let items = this.$cookies.get('panier');
                items.push(this.item);
                this.$cookies.set('panier', items);
            
                this.$router.push('/patient/panier')
            }
        }
    }
</script>