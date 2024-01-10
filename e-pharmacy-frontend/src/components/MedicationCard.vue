<script setup>
    import { formatPrice } from '@/scripts/Format';
</script>

<template>
    

    <div :class="{
        'med-card': true,
        'med-card-show': isVisible
    }" >
        <img v-if="isLoading" class="med-icon-loading" src="@/assets/icons/loading.svg"/>
        <img v-show="!isLoading" class="med-icon" :src="imagePath" @load="imageLoadingDone"/>

        <h1> {{ brandName }} </h1>
        <h2> {{ medicationName }} </h2>

        <div class="description"> 
            {{ description }}
        </div>

        <div class="price">{{ formatedPrice }} MAD</div>
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

        opacity: 0; /* Set initial opacity to 0 */

        transition: all 0.15s ease-out;
    }
    
    /** show medcard */
    .med-card-show {
        /* Apply the fade-in animation*/
        animation: fadeIn 1s ease-in-out; /* Use the defined keyframes with a duration of 1 second */
        animation-fill-mode: forwards;
    }

    /* Define keyframes for the fade-in animation */
    @keyframes fadeIn {
        from {
            opacity: 0;
        }
        to {
            opacity: 1;
        }
    }

    .med-card:hover {
        cursor: pointer;
        box-shadow: 0px 8px 32px 16px var(--shadow-color);
        z-index: 2;
    }

    /** med card img */
    .med-card img {
        width: 100%;
        margin-top: 16px;
        margin-bottom: 16px;

        aspect-ratio: 1;
        object-fit: contain;
    }

    .med-card:hover img {
        transform: rotate(-12deg) scale(1.1);
    }

    .med-icon {
        transform: rotate(-8deg) scale(100%);
        transition: transform 0.15s ease-out;
    }
    
    /** rotate animation */
    .med-icon-loading {
        animation: rotate 1s linear infinite;
    }

    @keyframes rotate {
        from {
            transform: scale(0.6) rotate(0deg);
        }
        to {
            transform: scale(0.6) rotate(360deg);
        }
    }

    /**med card info */

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

<script>
    export default {
        props: {
            imagePath: {
                type: String, 
                required: true
            },
            brandName: {
                type: String,
                required: true
            },
            medicationName: {
                type: String,
                required: true
            },
            description: {
                type: String,
                required: true
            },
            price: {
                type: Number,
                required: true
            },
            showDelai: {
                type: Number,
                default: 0
            }
        },

        data() {
            return {
                isLoading: true,
                isVisible: false,
                delaiTimer: null
            }
        },

        mounted() {
            this.delaiTimer = setTimeout(() => {
                this.isVisible = true;
            }, this.showDelai);
        },

        unmounted() {
            clearTimeout(this.delaiTimer);
        },

        methods: {
            imageLoadingDone() {
                setTimeout(() => {
                    this.isLoading = false;
                }, 1000);
            }
        },
        
        computed: {
            formatedPrice() {
                return formatPrice(this.price);
            }
        }
    }
</script>