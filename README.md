# Pokedex App

## Descripción del Proyecto
Esta es una aplicación de Android desarrollada en Kotlin utilizando Jetpack Compose que permite a los usuarios buscar información sobre cualquier Pokémon a través de la PokeAPI. La aplicación ofrece una interfaz simple donde los usuarios pueden ingresar el nombre de un Pokémon en una barra de búsqueda y visualizar sus detalles organizados en una tabla, incluyendo su ID, peso, altura, experiencia base, tipos y estadísticas.

## Instrucciones para Ejecutar
### Requisitos Previos
1. Tener instalado **Android Studio Flamingo o superior**.
2. Disponer de un **emulador o un dispositivo Android** para ejecutar la aplicación.
3. Conexión a internet para consumir la API.

### Pasos de Instalación y Ejecución
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/pokedex-app.git
   ```
2. Abrir el proyecto en Android Studio.
3. Asegurar que el archivo `build.gradle` contenga las dependencias necesarias.
4. Compilar y ejecutar la aplicación en un emulador o dispositivo físico.

## Arquitectura de la Aplicación
La aplicación sigue el patrón MVVM (Model-View-ViewModel):
- **Model:** Contiene las clases de datos que representan la respuesta de la API.
- **ViewModel:** Gestiona la lógica de negocio y las llamadas a la API utilizando Retrofit y coroutines.
- **View:** Utiliza Jetpack Compose para mostrar la interfaz gráfica y reflejar los datos obtenidos.

Los principales componentes son:
- **RetrofitInstance:** Configura Retrofit para consumir la PokeAPI.
- **PokemonApi:** Define la interfaz de Retrofit para realizar las peticiones HTTP.
- **PokemonViewModel:** Administra el estado y las llamadas a la API.
- **PokemonScreen:** Contiene la interfaz de usuario con la barra de búsqueda y la tabla de datos.
- **PokemonTable:** Formatea y muestra la información del Pokémon en un diseño estructurado.

## Desafíos Encontrados y Soluciones
1. **Manejo de errores en la API:**
   - Se implementó un manejo de excepciones para evitar fallos en la aplicación cuando el usuario ingresa un nombre incorrecto.
   - Solución: Se usa `try-catch` en la función `fetchPokemon()` dentro del ViewModel.

2. **Carga de datos asíncronos:**
   - La API puede tardar en responder, lo que puede afectar la experiencia del usuario.
   - Solución: Se utilizó `MutableStateFlow` para gestionar el estado de carga y mostrar un `CircularProgressIndicator` mientras se obtiene la información.

3. **Formateo de datos:**
   - La API devuelve nombres de estadísticas en formato con guiones (ejemplo: `special-attack`).
   - Solución: Se usó la función `replace("-", " ").capitalize()` para mejorar la legibilidad.

## Dependencias Utilizadas
- **Retrofit**: Para consumir la API de Pokémon y manejar solicitudes HTTP.
  ```gradle
  implementation 'com.squareup.retrofit2:retrofit:2.9.0'
  implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
  ```
- **Gson**: Para convertir los datos JSON de la API en objetos de Kotlin.
  ```gradle
  implementation 'com.google.code.gson:gson:2.8.9'
  ```
- **Jetpack Compose**: Para construir la interfaz de usuario de forma declarativa.
  ```gradle
  implementation "androidx.compose.ui:ui:1.3.1"
  implementation "androidx.compose.material:material:1.3.1"
  implementation "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1"
  ```
- **Coroutines**: Para manejar operaciones asíncronas de manera eficiente.
  ```gradle
  implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4'
  ```

Esta aplicación proporciona una base sólida para futuras mejoras, como la inclusión de imágenes adicionales, habilidades y evolución de los Pokémon.

