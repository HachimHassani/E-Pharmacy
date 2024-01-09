<template>
    <div class="page-container">
        <div class="page">
            <slot></slot>

        </div>
    </div>
</template>

<style scoped>
    @import "../assets/styles/colors.css";

    .page-container {
        display: flex;
        justify-content: center;
        width: 100%;
    }

    .page {
        display: flex;
        align-items: center;
        flex-direction: column;

        width: 100%;
        max-width: 1200px;

        min-height: calc(100vh - 172px);

        background-color: white;
        box-shadow: 0px 200px 65px 1px var(--shadow-color);
        
        padding-top: 140px;
    }
</style>