<template>
    <button class="imprimer secondary-btn">

        <svg version="1.1" id="Uploaded to svgrepo.com" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" 
             viewBox="0 0 32 32" xml:space="preserve">
        <path class="puchipuchi_een" d="M26,9H6V3c0-1.1,0.9-2,2-2h16c1.1,0,2,0.9,2,2V9z M6,19h20v10c0,1.1-0.9,2-2,2H8c-1.1,0-2-0.9-2-2
        V19z M10,23c0,0.552,0.447,1,1,1h10c0.553,0,1-0.448,1-1s-0.447-1-1-1H11C10.447,22,10,22.448,10,23z M10,27c0,0.552,0.447,1,1,1h10
        c0.553,0,1-0.448,1-1s-0.447-1-1-1H11C10.447,26,10,26.448,10,27z M31,12v7c0,1.1-0.9,2-2,2h-2v-3H5v3H3c-1.1,0-2-0.9-2-2v-7
        c0-1.1,0.9-2,2-2h26C30.1,10,31,10.9,31,12z M5,13c0-0.552-0.448-1-1-1s-1,0.448-1,1c0,0.552,0.448,1,1,1S5,13.552,5,13z M8,13
        c0-0.552-0.448-1-1-1s-1,0.448-1,1c0,0.552,0.448,1,1,1S8,13.552,8,13z"/>
        </svg>
        
        Imprimer
    </button>
</template>

<style scoped>
    @import '../assets/styles/colors.css';

    .imprimer {
        padding: 1.4vh;
        font-size: 2.5vh;

        display: flex;
        justify-content: center;
        align-items: center;
        gap: 0.5vw;

    }

    .imprimer svg {
        width: calc(0.7vw + 1.8vh);
        fill: var(--main-color);

        transition: inherit;
    }

    .imprimer:hover svg {
        width: calc(0.7vw + 1.8vh);
        fill: white;
    }
</style>