<script setup>
    import ScreenTypes from '@/scripts/ScreenTypes';
</script>


<template>
    <div :class="{
        'searchbar-container': true, 
        'searchbar-container-small': isSmall
        }">
        
        <input class="search-input" type="text" placeholder="Search..">
        <button class="main-btn search-submit"> Rechercher </button>

    </div>
</template>

<style scoped>
    .searchbar-container {
        margin: 0px;
        margin-top: 64px;
        margin-bottom: 64px;

        display: flex;
        justify-content: center;

        gap: 16px;
    }

    .search-input {
        flex: 0.8;
        padding-left: 42px;
        background-image: url("../assets/icons/search.svg");
        background-size: 36px;
        background-position-y: center;
        background-position-x: 4px;
        background-repeat: no-repeat;

        font-size: 24px;
        font-weight: 800;

        border: none;
        outline: 0px solid var(--stroke-color);
        border-radius: 8px;

        color: var(--stroke-color);

        transition: all 0.1s ease-out;

    }

    .search-input::placeholder {
        font-weight: 600;
        font-size: 20px;
        color: var(--stroke-color);
    }

    .search-input:focus {
        outline: 2px solid var(--stroke-color);
    }

    .search-submit {
        padding: 1.3vmin;
        font-size: 20px;
        flex: 0.2;
    }

    /** Smaller searchbar */

    .searchbar-container-small {
        margin-left: 16px;
        margin-right: 16px;

        flex-direction: column;
        gap: 16px;
    }

    .searchbar-container-small .search-input {
        flex: none;
        padding-left: 50px;
        background-size: 40px;
        
        height: 52px;
    }

    .searchbar-container-small .search-submit {
        flex: none;
        width: 100%;

        height: 52px;
    }


</style>

<script>
    export default {
        computed: {
            isSmall() {
                return this.$globalProperties.screenType != ScreenTypes.Desktop;
            }
        }
    }
</script>