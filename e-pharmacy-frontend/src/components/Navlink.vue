<script setup>
    import ScreenTypes from '@/scripts/ScreenTypes';
import { RouterLink } from 'vue-router';
</script>

<template>
    <RouterLink class="router-link" :to="href">
        <img :src="imgPath" :width="`${responsiveIconSize}px`" draggable="false" />
        <div v-if="isBig"
        class="route-name">
            <slot></slot>
        </div>

    </RouterLink>
</template>

<script>
    export default {
        props: {
            iconPath: {
                type: String,
                required: true
            },
            href: {
                type: String,
                required: true
            },
            isBig: {
                type: Boolean,
                default: true
            },
            iconSize: {
                type: Number,
                default: 32
            }
        },
        computed: {
            imgPath() {
                const url = new URL(this.iconPath, import.meta.url);
                return url.href;
            },
            responsiveIconSize() {
                const isMobile = this.$globalProperties.screenType == ScreenTypes.Mobile;
                return isMobile? parseInt(this.iconSize): this.iconSize;
            }
        }
    }
</script>

<style scoped>
    @import "../assets/styles/colors.css";

    .router-link {
        margin: 0;
        padding: 0;

        display: flex;
        align-items: center;
        justify-content: center;

        color: black;
        text-decoration: none;
        gap: 8px;
    }

    .router-link img {
        user-select: none;
    }

    .route-name {
        font-size: large;
        font-weight: 700;
    }
</style>