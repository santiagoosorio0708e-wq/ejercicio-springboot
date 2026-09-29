# ??? Taller: Autenticación básica con manejo de sesión en Spring Boot

## Objetivo
Este proyecto demuestra cómo implementar un sistema básico de autenticación en Spring Boot utilizando el contexto de sesión de forma nativa (`@SessionScope`), sin depender de herramientas complejas como Spring Security o bases de datos como JPA.

## Características Principales
- Autenticar un usuario mediante nombre (`admin`) y contraseña (`1234`).
- Almacenar el estado de sesión del usuario utilizando anotaciones como `@SessionScope` en el contenedor de dependencias de Spring.
- Proteger endpoints para que solo sean funcionales si existe un usuario activo.
- Medir el tiempo de la sesión activa en segundos.

## Tecnologías y Dependencias
- **Java 17**
- **Spring Boot 3.x**
- `spring-boot-starter-web`

## Estructura del Código Clave
- `SessionUser.java`: Modelo base que representa los datos del usuario en sesión.
- `UserSession.java`: Componente con `@SessionScope` que vive durante el ciclo de vida de la sesión HTTP de un único usuario.
- `AuthController.java`: Controlador que expone los endpoints para iniciar sesión, visualizar el perfil y cerrar sesión.

---

## 🚀 Guía de Uso y Comandos

Para probar el API necesitas tener el servidor de Spring Boot corriendo.
Puedes levantarlo con el siguiente comando en la raíz de la carpeta:

```powershell
./mvnw.cmd spring-boot:run
```

Una vez levantado en el puerto `8080`, abre una **nueva terminal** y ejecuta los siguientes comandos usando `curl.exe` (necesario en Windows/PowerShell para evitar conflictos con `Invoke-WebRequest`):

### 1. Intentar acceder sin iniciar sesión (Acceso denegado)
```powershell
curl.exe -X GET http://localhost:8080/api/me
```
*Respuesta esperada:* `No estás autenticado.`

### 2. Iniciar sesión
```powershell
curl.exe -X POST http://localhost:8080/api/login -d "username=admin&password=1234" -c cookies.txt
```
*Nota: El parámetro `-c cookies.txt` guarda el ID de sesión generado por Spring Boot en un archivo local para que las siguientes peticiones "recuerden" quién eres.*

### 3. Revisar la sesión (Ver información del usuario)
```powershell
curl.exe -X GET http://localhost:8080/api/me -b cookies.txt
```
*Respuesta esperada:* 
```text
Usuario: admin
Sesión activa desde hace: X segundos
```

### 4. Cerrar sesión
```powershell
curl.exe -X POST http://localhost:8080/api/logout -b cookies.txt
```
*Respuesta esperada:* `Sesión cerrada.`
*(Si luego de esto vuelves a intentar el paso 3, el sistema ya no te reconocerá).*

---
*Taller completado exitosamente.*
