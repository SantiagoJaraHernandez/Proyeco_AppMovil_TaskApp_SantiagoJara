# Proyecto de Gestión de Tareas

## Descripción del Proyecto

El proyecto de Gestión de Tareas es una aplicación móvil diseñada para ayudar a los usuarios a organizar y gestionar sus tareas diarias de manera eficiente. La aplicación permite a los usuarios agregar, editar y eliminar tareas, así como establecer prioridades. Está diseñada con un enfoque en la simplicidad y la usabilidad, asegurando que los usuarios puedan administrar sus tareas sin complicaciones desde sus dispositivos móviles.

## Características Principales

1. **Agregar Tareas**
   - Los usuarios pueden crear nuevas tareas proporcionando un título, descripción, fecha y hora, y prioridad.

2. **Editar Tareas**
   - Las tareas existentes pueden ser editadas para actualizar cualquier información, incluyendo el título, descripción, fecha y hora, y prioridad.

3. **Eliminar Tareas**
   - Los usuarios pueden eliminar tareas que ya no son necesarias.

4. **Listar Tareas**
   - Las tareas se listan en la página principal, mostrando la información básica de cada tarea.

5. **Prioridad de Tareas**
   - Las tareas pueden tener diferentes niveles de prioridad: baja, media y alta.
   - Las tareas se organizan según su prioridad.

## Tecnologías Utilizadas

- **Frontend:**
  - Vue.js con Ionic: Un framework de JavaScript para construir aplicaciones móviles híbridas.
  - HTML5 y CSS3: Para estructurar y estilizar la aplicación.
  - Axios: Para realizar solicitudes HTTP al backend.

- **Backend:**
  - Spring Boot: Para crear una API RESTful.
  - Base de datos SQL: Para almacenar las tareas.

## Estructura del Proyecto

### Frontend

El frontend está diseñado con Vue.js e Ionic, utilizando componentes reutilizables para las diferentes partes de la aplicación. Los principales componentes incluyen:

- `Home.vue`: Página principal donde se listan todas las tareas.
- `EditarTarea.vue`: Página para editar una tarea existente.
- `AgregarTarea.vue`: Página para agregar una nueva tarea.

### Backend

El backend está construido con Spring Boot, proporcionando endpoints para manejar las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre las tareas. La base de datos SQL almacena las tareas.

### Rutas del API

- `GET /api/tarea`: Obtener todas las tareas.
- `GET /api/tarea/:id`: Obtener una tarea por su ID.
- `POST /api/tarea`: Crear una nueva tarea.
- `PUT /api/tarea/:id`: Actualizar una tarea existente.
- `DELETE /api/tarea/:id`: Eliminar una tarea por su ID.

## Interfaz de Usuario

La interfaz de usuario está diseñada para ser intuitiva y fácil de usar. Se ha implementado un diseño responsivo para asegurar que la aplicación se vea y funcione bien en diferentes tamaños de pantalla de dispositivos móviles.

### Estilo

Se utilizan estilos modernos con colores suaves y claros para una apariencia amigable. Se han agregado efectos de transición y sombras para mejorar la experiencia visual.

## Instalación y Ejecución

### Requisitos Previos

- Node.js y npm instalados en el sistema.
- Java y Maven instalados en el sistema.
- Una base de datos SQL en ejecución.
- Ionic CLI instalado globalmente (`npm install -g @ionic/cli`).

### Pasos de Instalación

1. Clonar el repositorio del proyecto.
2. Navegar al directorio del proyecto.
3. Instalar las dependencias del frontend con `npm install`.
4. Configurar las variables de entorno necesarias para el backend y frontend.
5. Construir y ejecutar el backend con Maven:
    ```sh
    mvn clean install
    mvn spring-boot:run
    ```
6. Iniciar el servidor frontend con Ionic:
    ```sh
    ionic serve
    ```

### Despliegue en Dispositivos Móviles

Para probar y desplegar la aplicación en dispositivos móviles, utiliza los siguientes comandos:

- Para Android:
    ```sh
    ionic capacitor run android
    ```

- Para iOS:
    ```sh
    ionic capacitor run ios
    ```
