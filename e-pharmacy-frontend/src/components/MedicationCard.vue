<script setup>

</script>

<template>
    <div class="med-card">
        <img src="../assets/placeholders/pomade-jaune.png" />

        <h1> Aureomycine </h1>
        <h2> Pommade ophtalmique </h2>

        <div class="description"> 
            Aureomycin 1% – Pommade ophtalmique 5g
        </div>

        <div class="price">9,99 MAD</div>
    </div>
</template>

<style scoped>
    @import "../assets/styles/colors.css";

    .med-card { 
        min-height: 380px;
        background-color: white;

        border: 1px solid var(--stroke-color);
        margin: 0px;

        padding: 24px;

        display: flex;
        flex-direction: column;
        gap: 8px;

        transition: all 0.15s ease-out;
    }

    .med-card:hover {
        cursor: pointer;
        box-shadow: 0px 8px 32px 16px var(--shadow-color);
        z-index: 2;
    }

    .med-card img {
        width: 100%;
        transform: rotate(-8deg);
        margin-top: 16px;
        margin-bottom: 16px;

        transition: inherit;
    }

    .med-card:hover img {
        transform: rotate(-12deg) scale(1.1);
    }

    .med-card h1 {
        margin: 0;
        font-weight: 800;
        font-size: 32px;
    }
    .med-card h2 {
        margin: 0;
        font-size: 20px;
    }

    .description {
        font-size: larger;
    }

    .price {
        flex: auto;

        display: flex;
        align-items: end;
        justify-content: end;

        font-weight: 800;
        font-size: 24px;
    }
</style>