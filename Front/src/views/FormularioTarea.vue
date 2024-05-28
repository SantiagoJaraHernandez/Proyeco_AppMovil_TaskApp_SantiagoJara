<template>
    <div>
      <h2>{{ modo === 'agregar' ? 'Agregar Tarea' : 'Editar Tarea' }}</h2>
      <form @submit.prevent="submitForm">
        <label for="titulo">Título:</label>
        <input type="text" id="titulo" v-model="formulario.titulo" required>
  
        <label for="descripcion">Descripción:</label>
        <textarea id="descripcion" v-model="formulario.descripcion"></textarea>
  
        <label for="prioridad">Prioridad:</label>
        <select id="prioridad" v-model="formulario.prioridad">
          <option value="Baja">Baja</option>
          <option value="Media">Media</option>
          <option value="Alta">Alta</option>
        </select>
  
        <label for="fechaVencimiento">Fecha de Vencimiento:</label>
        <input type="date" id="fechaVencimiento" v-model="formulario.fechaVencimiento">
  
        <button type="submit">{{ modo === 'agregar' ? 'Agregar' : 'Guardar Cambios' }}</button>
      </form>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    props: {
      modo: String,
      tareaEditar: Object
    },
    data() {
      return {
        formulario: {
          titulo: '',
          descripcion: '',
          prioridad: 'Baja',
          fechaVencimiento: ''
        }
      };
    },
    mounted() {
      if (this.modo === 'editar') {
        this.cargarDatosTarea();
      }
    },
    methods: {
      async cargarDatosTarea() {
        this.formulario = { ...this.tareaEditar };
      },
      async submitForm() {
        try {
          if (this.modo === 'agregar') {
            await axios.post('http://localhost:9002/api/tarea', this.formulario);
          } else {
            await axios.put(`http://localhost:9002/api/tarea/${this.tareaEditar.id}`, this.formulario);
          }
          // Aquí puedes emitir un evento para indicar que la tarea se agregó o editó correctamente
        } catch (error) {
          console.error('Error al agregar/editar la tarea:', error);
        }
      }
    }
  };
  </script>
  
  <style>
  h2 {
    color: #333;
    font-size: 20px;
    text-align: center;
  }
  label {
    display: block;
    margin-bottom: 5px;
  }
  input, textarea, select {
    width: 100%;
    margin-bottom: 10px;
    padding: 5px;
    box-sizing: border-box;
  }
  button {
    width: 100%;
    padding: 5px 10px;
    background-color: #007bff;
    color: #fff;
    border: none;
    cursor: pointer;
  }
  button:hover {
    background-color: #0056b3;
  }
</style>
