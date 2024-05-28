<template>
    <div class="dashboard">
      <!-- Encabezado -->
      <header class="header">
        <button class="menu-button" @click="toggleMenu">
          <i class="fas fa-bars"></i>
        </button>
        <h1>Tareas</h1>
      </header>
  
      <!-- Menú lateral -->
      <aside :class="{ 'menu-open': menuAbierto }" class="menu-lateral">
        <nav>
          <router-link to="/">Inicio</router-link>
          <router-link to="/agregar-tarea">Agregar Tarea</router-link>
          <!-- Puedes añadir más enlaces según sea necesario -->
        </nav>
      </aside>
  
      <!-- Contenido principal -->
      <main class="content">
        <!-- Contenido de las tareas -->
        <div v-if="tareas.length === 0" class="no-tareas">No hay tareas disponibles</div>
        <div class="tareas-container" v-else>
          <div class="tarea" v-for="tarea in tareasOrdenadas" :key="tarea.id">
            <div class="tarea-info">
              <!-- Prioridad -->
              <div :class="'prioridad-' + tarea.prioridad.toLowerCase()">
                {{ tarea.prioridad }}
              </div>
              <!-- Resto de la información de la tarea -->
              <p><strong>Título:</strong> {{ tarea.titulo }}</p>
              <p><strong>Descripción:</strong> {{ tarea.descripcion }}</p>
              <p><strong>Tiempo Restante:</strong> {{ tiempoRestante(tarea.fechaVencimiento) }}</p>
            </div>
            <div class="tarea-buttons">
              <!-- Botones de editar y eliminar -->
              <button class="editar" @click="editarTarea(tarea.id)">
                <i class="fas fa-edit"></i>
              </button>
              <button class="eliminar" @click="eliminarTarea(tarea.id)">
                <i class="fas fa-trash-alt"></i>
              </button>
            </div>
          </div>
        </div>
        <!-- Botón para agregar tarea -->
        <router-link to="/agregar-tarea" class="boton-agregar">
          <i class="fas fa-plus"></i>
        </router-link>
        <!-- Mensajes -->
        <p v-if="mensaje">{{ mensaje }}</p>
        <p v-if="advertencia" class="advertencia">{{ advertencia }}</p>
      </main>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  import moment from 'moment';
  
  export default {
    data() {
      return {
        tareas: [],
        mensaje: '',
        advertencia: '',
        menuAbierto: false
      };
    },
    mounted() {
      this.obtenerTareas();
    },
    computed: {
      // Computed property para ordenar las tareas por prioridad
      tareasOrdenadas() {
        return this.tareas.sort((a, b) => {
          // Definir el orden de las prioridades
          const ordenPrioridades = ['Alta', 'Media', 'Baja'];
          // Obtener el índice de cada prioridad en el orden definido
          const prioridadA = ordenPrioridades.indexOf(a.prioridad);
          const prioridadB = ordenPrioridades.indexOf(b.prioridad);
          // Ordenar las tareas en función de su prioridad
          return prioridadA - prioridadB;
        });
      }
    },
    methods: {
      async obtenerTareas() {
        try {
          const response = await axios.get('http://localhost:9002/api/tarea');
          this.tareas = response.data;
        } catch (error) {
          console.error('Error al obtener las tareas:', error);
        }
      },
      async eliminarTarea(id) {
        try {
          await axios.delete(`http://localhost:9002/api/tarea/${id}`);
          this.tareas = this.tareas.filter(tarea => tarea.id !== id);
          this.advertencia = 'La tarea ha sido eliminada.';
        } catch (error) {
          console.error('Error al eliminar la tarea:', error);
        }
      },
      editarTarea(id) {
        // Redirigir a la página de edición de tarea con la ID de la tarea seleccionada
        this.$router.push({ name: 'EditarTarea', params: { id: id } });
      },
      toggleMenu() {
        this.menuAbierto = !this.menuAbierto;
      },
      tiempoRestante(fechaVencimiento) {
    // Convertir la fecha de vencimiento en un objeto moment
    const vencimiento = moment(fechaVencimiento);
    
    // Obtener el día, mes y año de la fecha de vencimiento
    const dia = vencimiento.format('D');
    const mes = vencimiento.format('MMMM');
    const año = vencimiento.format('YYYY');
    
    // Obtener la hora y los minutos de la fecha de vencimiento
    const hora = vencimiento.format('HH');
    const minutos = vencimiento.format('mm');
    
    // Construir la cadena de fecha de vencimiento en el formato especificado
    const fechaFormateada = `Tienes hasta el ${dia} de ${mes} del ${año} a las ${hora}:${minutos}`;
    
    return fechaFormateada;
}


    }
  };
  </script>
  
  <style scoped>
  .dashboard {
    display: flex;
  }
  
  .header {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    background-color: #007bff;
    color: white;
    padding: 10px 20px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    z-index: 1000;
  }
  
  .header h1 {
    margin: 0;
    font-size: 24px;
  }
  
  .menu-button {
    background: none;
    border: none;
    color: white;
    font-size: 24px;
    cursor: pointer;
  }
  
  .menu-lateral {
    position: fixed;
    top: 0;
    left: -250px;
    width: 250px;
    height: 100%;
    background-color: #343a40;
    color: white;
    padding: 20px;
    transition: left 0.3s ease;
    z-index: 999;
  }
  
  .menu-lateral.menu-open {
    left: 0;
  }
  
  .menu-lateral nav {
    display: flex;
    flex-direction: column;
  }
  
  .menu-lateral a {
    color: white;
    text-decoration: none;
  margin-bottom: 10px;
}

.content {
  margin-top: 60px;
  padding: 20px;
  flex: 1;
  background-color: #f0f2f5;
  min-height: 100vh; /* Asegurar que la altura mínima cubra toda la pantalla */
}

.no-tareas {
  text-align: center;
  color: #666;
}

.tareas-container {
  margin-top: 10px;
  width: 100%;
  max-width: 600px;
  overflow-y: auto;
  max-height: calc(100vh - 150px);
}

.tarea {
  background-color: #fff;
  padding: 15px;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-bottom: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  width: 100%;
}

.tarea-info {
  flex: 1;
  color: #333;
}

.tarea-buttons {
  display: flex;
  align-items: center;
  justify-content: flex-end;
  width: 100%;
  margin-top: 10px;
}

.tarea-buttons button {
  background: none;
  border: none;
  cursor: pointer;
  padding: 5px;
}

.tarea-buttons button i {
  font-size: 18px;
  color: #777;
  transition: color 0.3s ease;
}

.tarea-buttons button.editar i:hover {
  color: #007bff;
}

.tarea-buttons button.eliminar i:hover {
  color: #dc3545;
}

.boton-agregar {
  position: fixed;
  bottom: 20px;
  right: 20px;
  font-size: 36px;
  background-color: #007bff;
  color: white;
  width: 60px;
  height: 60px;
  border-radius: 50%;
  text-align: center;
  line-height: 60px;
  text-decoration: none;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.boton-agregar i {
  color: white;
}

.advertencia {
  margin-top: 10px;
  color: #ff7b00;
  text-align: center;
}

.prioridad-alta {
  color: white;
  background-color: red;
  padding: 4px 8px;
  border-radius: 4px;
  margin-bottom: 5px;
}

.prioridad-media {
  color: white;
  background-color: yellow;
  padding: 4px 8px;
  border-radius: 4px;
  margin-bottom: 5px;
}

.prioridad-baja {
  color: white;
  background-color: blue;
  padding: 4px 8px;
  border-radius: 4px;
  margin-bottom: 5px;
}
</style>
  