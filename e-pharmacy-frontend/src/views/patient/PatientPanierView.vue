<script setup>
    import PageContainer from '@/components/PageContainer.vue';
    import PanierCard from '@/components/PanierCard.vue'
</script>


<template>
    <PageContainer class="page-container">
        <h1>
            Votre panier
        </h1>

        <!--Liste of command items-->
        <div class="panier-list">
            <PanierCard v-for="i in Array(3)"/>
        </div>

        <!--Delete all button-->
        <div class="delete-all-container">
            <button class="delete-btn">
                <svg  viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path d="M5.755,20.283,4,8H20L18.245,20.283A2,2,0,0,1,16.265,22H7.735A2,2,0,0,1,5.755,20.283ZM21,4H16V3a1,1,0,0,0-1-1H9A1,1,0,0,0,8,3V4H3A1,1,0,0,0,3,6H21a1,1,0,0,0,0-2Z"/></svg>
                Vider le panier
            </button>
        </div>

        <!--Command Section-->
        <div class="command-container">

            <h1> Votre commande </h1>

            <!--Total Commande-->
            <div class="price-info">
                <div class="price-name">
                    Total commande TTC
                </div>

                <div class="price">
                    99,99 MAD
                </div>
            </div>

            <span class="line-separation"/>

            <!--Prix livraison-->
            <div class="price-info">
                <div class="price-name">
                    Frais de livraison
                </div>

                <div class="price">
                    14,99 MAD
                </div>
            </div>

            <span class="line-separation" />

            <!--Total Commande-->
            <div class="price-info">
                <div class="price-name">
                    Total TTC
                </div>

                <div class="price">
                    99,99 MAD
                </div>
            </div>

            <button class="main-btn commander-btn">
                Commander
            </button>

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

    .panier-list {
        width: 98%;
    }

    /** Delete button */

    .delete-all-container {
        width: 98%;


        display: flex;
        justify-content: end;
    }

    .delete-btn {
        outline: 0.3vh solid var(--error-color);
        color: var(--error-color);
        background-color: white;
        border: none;

        margin: 1.5vh;

        text-decoration: none;
        font-size: 2.5vh;
        font-weight: 600;

        padding: 1.5vh;
        padding-left: 2vh;
        padding-right: 2vh;

        border-radius: 0.5vh;

        text-align: center;

        transition: all 0.15s ease-out;

        display: flex;
        justify-content: center;
        align-items: center;
        gap: 0.5vw;
    }

    .delete-btn svg {
        width: 3vh;
        fill: var(--error-color);
    }

    .delete-btn:hover {
        cursor: pointer;
        background-color: var(--error-color);
        color: white;
    }

    .delete-btn:hover svg {
        fill: white;
    }

    /** Command section */
    .command-container {
        width: calc(98% - 6vh);
        display: flex;
        flex-direction: column;
        align-items: center;

        margin-top: 1vh;
        padding: 2vh;

        background-color: var(--secondary-bg-color);
        border-top-right-radius: 15px;
        border-top-left-radius: 15px;
    }

    .commander-btn {
        width: 100%;

        font-size: 4vh;
        font-weight: 800;

        padding: 2vh;
    }

    .price-info {
        display: flex;
        justify-content: space-between;

        width: 100%;
        padding: 2.5vh;
    }

    .price-name {
        font-size: 2.8vh;
        font-weight: 500;
    }

    .price {
        font-size: 2.8vh;
        font-weight: 700;
    }

</style>