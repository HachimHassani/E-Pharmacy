import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    scrollBehavior(to, from, savedPosition) {
        window.scroll(0, 0);
    },
    routes: [
        {
            path: '/',
            name: 'home',
            component: () => import('../views/HomeView.vue'),
        },
        {
            path: '/patient',
            name: 'patient',

            children: [
                {
                    path: '',
                    name:'patient-home',
                    component: () => import('../views/patient/PatientHomeView.vue'),
                },
                {
                    path:'medicaments',
                    name: 'patient-medicaments',
                    component: () => import('../views/patient/PatientMedicamentsView.vue')
                },
                {
                    path: 'medicament/:id',
                    name: 'patient-medicament-detail',
                    component: () => import('../views/patient/PatientMedicamentDetailView.vue')
                },
                {
                    path: 'ordonnances',
                    name: 'patient-ordonnances',
                    component: () => import('../views/patient/PatientOrdonnancesView.vue')
                },
                {
                    path: 'ordonnance/:id',
                    name: 'patient-ordonnance-detail',
                    component: () => import('../views/patient/PatientOrdonnanceDetailView.vue')
                },
                {
                    path: 'panier',
                    name: 'patient-panier',
                    component: () => import('../views/patient/PatientPanierView.vue')
                },
                {
                    path: 'commandes',
                    name: 'patient-commandes',
                    component: () => import('../views/patient/PatientCommandesView.vue')
                }
            ]
        }
    ]
})

export default router
