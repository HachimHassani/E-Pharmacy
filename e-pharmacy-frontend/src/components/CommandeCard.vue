<script setup>
import { RouterLink } from 'vue-router';
</script>

<template>
    <div class="card">

        <div class="top-card">
            <div class="left">
                <h1> ID xxxxxx </h1>
                <h2> fait le XX/XX/XXXX</h2>
            </div>

            <div class="right">
                <h1> En cours </h1>
            </div>
        </div>

        <div class="progress-container">

            <div class="steps">
                <span class="rectangle"/>
                <span class="rectangle rectangle-second"/>

                <span class="circle" />
                <span class="circle" />
                <span class="circle" />
            </div>

            <div class="steps">
                <h1> Envoye </h1>
                <h1> En cours </h1>
                <h1> Arrive </h1>
            </div>

        </div>

    </div>
</template>

<style scoped>
@import "../assets/styles/colors.css";

    .card {
        background-color: white;
        padding: calc(1.3vh + 0.5vw);
        border-radius: 1vh;
        margin: 1.5vh;

        display: flex;
        flex-direction: column;

        gap: 3vh;
    }

    .top-card {
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
        font-size: 2.4vh;
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

    .progress-container {
        display: flex;
        flex-direction: column;
        gap: 1vh;
    }

    .steps {
        display: flex;
        justify-content: space-between;

        position: relative;
    }

    .steps h1 {
        font-weight: 700;
        font-size: calc(2.2vh + 0.3vw);
    }

    .circle {
        width: calc(4vh + 0.5vw + 0px);
        height: calc(4vh + 0.5vw + 0px);
        background-color: white;
        outline: 2px solid black;
        border-radius: 100%;
    }

    .rectangle {
        position: absolute;
        top: calc((4vh + 0.5vw) / 4 - 2px);
        left: calc((4vh + 0.5vw) * 0.95 - 1px);
        
        width: calc(50% - (4vh + 0.5vw) * (0.95 + 0.42) + 1px);
        height: calc((4vh + 0.5vw) / 2);

        border-top: 2px solid black;
        border-bottom: 2px solid black;

        background-color: white;

        z-index: 1;
    }

    .rectangle-second {
        left: calc(50% + (4vh + 0.5vw) * 0.45 - 1px);
    }

</style>