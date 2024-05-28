import { createRouter, createWebHistory } from 'vue-router';
import TareasPage from '../views/Tareas.vue';
import LoginPage from '../views/Login.vue';
import AgregarTarea from '../views/AgregarTarea.vue';
import EditarTarea from '../views/EditarTarea.vue';
const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/tareas',
    name: 'Tareas',
    component: TareasPage
  },
  {
    path: '/login',
    name: 'login',
    component: LoginPage
  },
  {
    path: '/agregar-tarea',
    name: 'AgregarTarea',
    component: AgregarTarea
  },
  {
    path: '/editar-tarea/:id',
    name: 'EditarTarea',
    component: EditarTarea
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});


export default router;
