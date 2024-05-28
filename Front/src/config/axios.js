import axios from 'axios';

// Configuración global de Axios
axios.defaults.baseURL = 'http://localhost:9002'; // URL base de tu API
axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*'; // Permitir solicitudes desde cualquier origen
axios.defaults.headers.post['Content-Type'] = 'application/json'; // Tipo de contenido para las solicitudes POST

export default axios;
