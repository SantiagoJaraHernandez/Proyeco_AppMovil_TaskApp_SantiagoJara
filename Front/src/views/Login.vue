<template>
    <div class="login-container">
      <img class="logo" src="@/assets/logo.png" alt="Logo">
  
      <div class="login-form">
        <h2 class="form-title">Iniciar sesión</h2>
        <form @submit.prevent="login">
          <div class="form-group">
            <label for="email" :class="{ 'focused': isEmailFocused }">Correo electrónico</label>
            <input type="email" id="email" v-model="email" placeholder="Correo Electronico" @focus="isEmailFocused = true" @blur="isEmailFocused = false">
          </div>
  
          <div class="form-group">
            <label for="password" :class="{ 'focused': isPasswordFocused }">Contraseña</label>
            <input type="password" id="password" v-model="password" placeholder="Contraseña" @focus="isPasswordFocused = true" @blur="isPasswordFocused = false">
          </div>
  
          <button type="submit">
            <ion-icon name="log-in-outline" size="xxl"></ion-icon> <!-- Icono más grande -->
            <span class="button-text">Iniciar sesión</span> <!-- Texto centrado -->
          </button>
        </form>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        email: '',
        password: '',
        isEmailFocused: false,
        isPasswordFocused: false
      };
    },
    methods: {
      login() {
        if (this.email === 'admin@test.com' && this.password === 'admin1234') {
          this.$router.push('/tareas');
        } else {
          // Lógica para mostrar un mensaje de error si el inicio de sesión es incorrecto
          alert('Correo electrónico o contraseña incorrectos');
        }
      }
    }
  };
  </script>
  
  <style scoped>
  .login-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100vh;
    background: linear-gradient(to bottom right, #ff8080, #80ff80);
  }
  
  .logo {
    width: 300px;
  }
  
  .login-form {
    background-color: rgba(255, 255, 255, 0.8);
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  }
  
  .form-title {
    margin-bottom: 20px;
    color: #007bff;
    text-align: center; /* Alineación del título al centro */
  }
  
  .form-group {
    margin-bottom: 15px;
  }
  
  label {
    font-weight: bold;
    transition: color 0.3s ease;
  }
  
  label.focused {
    color: #007bff;
  }
  
  input {
    width: 100%;
    padding: 10px;
    border: 1px solid #000000;
    border-radius: 5px;
  }
  
  button {
    width: 100%;
    padding: 10px; /* Ajuste del padding */
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
    display: flex;
    align-items: center;
    justify-content: center; /* Alineación del texto al centro */
  }
  
  button:hover {
    background-color: #0056b3;
  }
  
  .button-text {
    margin-left: 5px; /* Espacio entre el icono y el texto */
  }
  </style>
  