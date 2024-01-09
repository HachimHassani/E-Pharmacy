<template>
    <div :class="{
        'company-icon': true,
        'company-icon-small': !isBig,
        'company-icon-big': isBig
    }"> 
        <img src="../assets/icons/my-pharmacy.svg" />
        <h1 v-if="!hideText"> My Pharmacy </h1>
    </div>
</template>

<script>
    export default {
        props: {
            isBig: {
                type: Boolean,
                default: false
            },
            hideText: {
                type: Boolean,
                default: false,
            }
        },
    }
</script>

<style scoped>

    .company-icon {
        display: flex;
        justify-content: center;
        align-content: center;

        gap: 16px;
        color: var(--main-color);
    }

    .company-icon img {
        width: 52px;
    }

    .company-icon h1 {
        font-size: 32px;
        font-weight: 800;

        margin: auto;

        display: flex;
    }

    .company-icon-big img {
        width: calc(9vh + 1vw);
    }

    .company-icon-big h1 {
        font-size: calc(4vh + 1vw);
        font-weight: 900;
    }

</style>