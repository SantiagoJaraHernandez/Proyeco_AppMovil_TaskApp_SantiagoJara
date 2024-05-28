<template>
    <div>
      <header>
        <h1>Editar Tarea</h1>
      </header>
      <form @submit.prevent="guardarCambios" class="edit-form">
        <div class="form-group">
          <label for="titulo">Título:</label>
          <input type="text" id="titulo" v-model="formulario.titulo" required>
        </div>
        <div class="form-group">
          <label for="descripcion">Descripción:</label>
          <textarea id="descripcion" v-model="formulario.descripcion"></textarea>
        </div>
        <div class="form-group">
          <label for="fecha">Fecha:</label>
          <input type="date" id="fecha" v-model="formulario.fecha" required>
        </div>
        <div class="form-group">
          <label for="hora">Hora:</label>
          <input type="time" id="hora" v-model="formulario.hora" required>
        </div>
        <div class="form-group">
          <label for="prioridad">Prioridad:</label>
          <select id="prioridad" v-model="formulario.prioridad">
            <option v-for="option in orderedPriorities" :key="option.value" :value="option.value">{{ option.label }}</option>
          </select>
        </div>
        <button type="submit">Guardar Cambios</button>
      </form>
      <p v-if="mensaje" class="success-message">{{ mensaje }}</p>
      <p v-if="advertencia" class="warning-message">{{ advertencia }}</p>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        formulario: {
          id: null,
          titulo: '',
          descripcion: '',
          fecha: '',
          hora: '',
          prioridad: 'Baja',
        },
        mensaje: '',
        advertencia: '',
        priorities: [
          { label: 'Baja', value: 'Baja' },
          { label: 'Media', value: 'Media' },
          { label: 'Alta', value: 'Alta' }
        ]
      };
    },
    computed: {
      orderedPriorities() {
        // Ordenar las prioridades
        return this.priorities.slice().sort((a, b) => {
          return this.getPriorityIndex(a.value) - this.getPriorityIndex(b.value);
        });
      }
    },
    methods: {
      getPriorityIndex(priority) {
        // Devuelve el índice de prioridad para ordenar
        return ['Baja', 'Media', 'Alta'].indexOf(priority);
      },
      async obtenerTarea() {
        try {
          const tareaId = this.$route.params.id;
          const response = await axios.get(`http://localhost:9002/api/tarea/${tareaId}`);
          this.formulario = response.data;
        } catch (error) {
          console.error('Error al obtener la tarea:', error);
        }
      },
      async guardarCambios() {
        try {
          await axios.put(`http://localhost:9002/api/tarea/${this.formulario.id}`, this.formulario);
          this.$router.push('/tareas');
          this.mensaje = 'Los cambios han sido guardados correctamente.';
        } catch (error) {
          console.error('Error al guardar los cambios:', error);
        }
      }
    },
    mounted() {
      this.obtenerTarea();
    }
  };
  </script>  <style scoped>
  header {
    background-color: #00aeff;
    color: white;
    padding: 20px 0;
    text-align: center;
    margin-bottom: 20px;
  }
  
  h1 {
    margin: 0;
    font-size: 24px;
  }
  
  .edit-form {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 10px;
    max-width: 400px;
    margin: 0 auto;
  }
  
  .form-group {
    width: 100%;
  }
  
  label {
    font-weight: bold;
  }
  
  input,
  textarea,
  select {
    padding: 8px;
    border-radius: 4px;
    border: 1px solid #ccc;
    width: 100%;
  }
  
  button {
    padding: 8px 16px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }
  
  .date-time-container {
    width: 100%;
    position: relative;
  }
  
  .date-time-container input {
    width: calc(100% - 40px);
  }
  
  .success-message {
    color: green;
  }
  
  .warning-message {
    color: red;
  }
  
  @media only screen and (max-width: 480px) {
    .edit-form {
      max-width: 300px;
    }
  }
  </style>
  