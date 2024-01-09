<script setup>
    import { RouterLink } from 'vue-router';
</script>

<template>
    <div class="card">
        <div class="left">
            <h1> ID xxxxxx </h1>
            <h2> Nom du medecin </h2>

            <button class="delete-button">
                <img src="../assets/icons/trash.svg" />
                <div> Supprimer </div>
            </button>
        </div>

        <div class="right">
            <h1>999,99 MAD</h1>

        </div>

    </div>


</template>

<style scoped>
    @import "../assets/styles/colors.css";

    .card {
        background-color: white;
        padding: 1.3vh;
        border-radius: 1vh;
        margin: 1.5vh;
        margin-top: 0px;

        display: flex;
        justify-content: space-between;
    }

    .left {
        display: flex;
        justify-content: space-between;
        flex-direction: column;

        gap: 0.6vw;
    }

    h1 {
        margin: 0px;
        font-size: calc(2.6vh + 0.3vw);
        font-weight: 800;
    }

    h2 {
        margin: 0px;
        font-size: 2.2vh;
    }

    .details {
        font-size: 2vh;
    }

    .right {
        display: flex;
        align-items: end;
        flex-direction: column;
        justify-content: space-between;

        gap: 1vh;
    }

    .delete-button {
        margin: 0;
        margin-top: 1vh;

        padding: 0;

        width: min-content;

        color: var(--error-color);
        font-size: calc(2vh + 0.2vw);

        display: flex;
        align-items: center;
        gap: 1vh;

        background-color: transparent;
        outline: none;
        border: none;

        cursor: pointer;
    }

    .delete-button img {
        width: calc(2.5vh + 0.2vw);;
    }
</style>