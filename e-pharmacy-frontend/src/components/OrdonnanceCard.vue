<script setup>
    import AddToCartButton from '@/components/AddToCartButton.vue'
import ScreenTypes from '@/scripts/ScreenTypes';
</script>

<template>
    <div class="ordonnance-card">
        <div class="left">
            <h1> ID xxxxxx </h1>
            <h2> Nom du medecin </h2>

            <div class="details">
                Fait le xx/xx/xxxx <br/>
                a Nom de la ville
            </div>
        </div>

        <div class="right">
            <h1>999,99 MAD</h1>
            
            <div :class="{
                'buttons-container': true,
                'buttons-container-small': isSmall
            }">
                <AddToCartButton></AddToCartButton>
                <RouterLink v-if="!isSmall" to="/" class="details-btn">
                    Details
                </RouterLink>
            </div>
        </div>
    </div>
</template>

<style scoped>
    .ordonnance-card {
        background-color: white;
        padding: calc(1.3vh + 0.6vw);
        border-radius: 1vh;
        margin: 1.5vh;
        margin-top: 0px;

        display: flex;

        min-height: 110px;
    }

    .left {
        flex: auto;
        display: flex;
        flex-direction: column;
    }

    h1 {
        margin: 0px;
        font-size: calc(3vh + 0.2vw);
        font-weight: 800;
    }

    h2 {
        margin: 0px;
        font-size: calc(2.5vh + 0.1vw);
    }

    .details {
        padding-top: 1vh;
        font-size: calc(2vh + 0.1vw);
    }

    .right {
        display: flex;
        align-items: end;
        flex-direction: column;
        justify-content: space-between;

        gap: 1vh;
    }

    .buttons-container {
        display: flex;
        flex-direction: row-reverse;
        align-items: center;

        gap: 1vw;
    }

    
    .details-btn {
        padding: 1vh;
        font-size: 2.5vh;
        font-weight: 600;
        
        gap: 4px;
        color: var(--main-color);
        
    }

    /** style for small screens */
    .buttons-container-small {
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