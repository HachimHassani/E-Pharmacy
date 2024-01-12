<template>
    <div :class="{
        'loading-container': true,
        'loading-container-hidden': !isLoading
    }">

        <div class="loading-icon-container">
            <img src="@/assets/icons/loading-white.svg"/>
        </div>
    </div> 
</template>

<style scoped>
    .loading-container {
        position: fixed;
        left: 0;
        right: 0;
        top: 0;
        bottom: 0;

        background-color: rgba(0, 0, 0, 0.181);

        z-index: 10000;

        display: flex;
        justify-content: center;
        align-items: center;

        transition: all 0.5s;
    }

    .loading-icon-container {
        animation: rotate 1s linear infinite;
        display: flex;
        justify-content: center;
        align-items: center;
    }


    @keyframes rotate {
        0% {
            transform: rotate(0deg);
        }
        100% {
            transform: rotate(360deg);
        }
    }

    .loading-container img {
        transition: all 0.5s ease-in-out;
    }

    .loading-container-hidden {
        visibility: hidden;
        opacity: 0%;
    }

    .loading-container-hidden img {
        transform: scale(3);
    }
</style>

<script>
    export default {
        props: {
            isLoading: {
                type: Boolean,
                default: false
            }
        }
    }
</script>