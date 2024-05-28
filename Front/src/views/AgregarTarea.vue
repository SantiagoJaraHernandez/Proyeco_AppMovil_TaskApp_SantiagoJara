<template>
  <div class="agregar-tarea-wrapper">
    <header class="header">
      <button @click="volverAtras" class="btn-volver">
        <i class="fas fa-arrow-left"></i></button>
      <h1>Agregar Tarea</h1>
    </header>
    <div class="agregar-tarea-container">
      <form @submit.prevent="submitForm" class="form-tarea">
        <div class="form-group">
          <label for="titulo">Título:</label>
          <input type="text" id="titulo" v-model="formulario.titulo" required>
        </div>
        <div class="form-group">
          <label for="descripcion">Descripción:</label>
          <textarea id="descripcion" v-model="formulario.descripcion"></textarea>
        </div>
        <div class="form-group">
          <label for="prioridad">Prioridad:</label>
          <select id="prioridad" v-model="formulario.prioridad">
            <option value="Baja">Baja</option>
            <option value="Media">Media</option>
            <option value="Alta">Alta</option>
          </select>
        </div>
        <div class="form-group">
          <label for="fechaVencimiento">Fecha de Vencimiento:</label>
          <input type="date" id="fechaVencimiento" v-model="formulario.fechaVencimiento">
        </div>
        <div class="form-group">
          <label for="horaVencimiento">Hora de Vencimiento:</label>
          <input type="time" id="horaVencimiento" v-model="formulario.horaVencimiento">
        </div>
        <button type="submit" class="btn-agregar">Agregar</button>
      </form>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      formulario: {
        titulo: '',
        descripcion: '',
        prioridad: 'Baja',
        fechaVencimiento: '',
        horaVencimiento: ''
      }
    };
  },
  methods: {
    async submitForm() {
      try {
        const tarea = {
          ...this.formulario,
          fechaVencimiento: `${this.formulario.fechaVencimiento}T${this.formulario.horaVencimiento}`
        };
        await axios.post('http://localhost:9002/api/tarea', tarea);
        this.$emit('tarea-agregada');
        this.$router.push('/tareas');
      } catch (error) {
        console.error('Error al agregar la tarea:', error);
      }
    },
    volverAtras() {
      this.$router.push('/tareas');
    }
  }
};
</script>

<style scoped>
body {
  background-color: #f1f1f1;
  margin: 0;
  font-family: Arial, sans-serif;
}

.agregar-tarea-wrapper {
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 100vh;
  padding: 20px;
  box-sizing: border-box;
}

.header {
  background-color: #007bff;
  color: white;
  width: 100%;
  padding: 20px 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.btn-volver {
  background: none;
  border: none;
  color: white;
  font-size: 16px;
  cursor: pointer;
  margin-left: 20px;
}

.agregar-tarea-container {
  background-color: #ffffff;
  padding: 20px;
  max-width: 500px;
  width: 100%;
  margin: 20px auto;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

h2 {
  color: #050505;
  font-size: 24px;
  text-align: center;
  margin-bottom: 20px;
}

.form-tarea {
  display: flex;
  flex-direction: column;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  color: #555;
  margin-bottom: 5px;
}

input[type="text"],
input[type="date"],
input[type="time"],
textarea,
select {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 16px;
}

textarea {
  resize: vertical;
  min-height: 100px;
}

.btn-agregar {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px 15px;
  font-size: 16px;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.btn-agregar:hover {
  background-color: #0056b3;
}
</style>
