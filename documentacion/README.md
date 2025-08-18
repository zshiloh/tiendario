# Tiendario - Sistema de Gestión de Inventario

# ZEGEL

---

<div align="center">

## CURSO
### COMPUTACION E INFORMATICA

---

## PROYECTO FINAL
### SISTEMA DE GESTIÓN DE INVENTARIO "TIENDARIO"
### ARQUITECTURA MONOLÍTICA CON SPRING BOOT

---

## PROFESOR
### CABALLERO CHIPANA ROMMEL RICARDO 

---

## INTEGRANTES
### GRUPO 1

- **[Juan Manuel Sarasi de la Cruz]**
- **[Dario Yauli Patiño Chagua]**
- **[Anthony Carlos Torre Lozano]**

---

**LIMA - PERÚ**

</div>

---

## 📋 Descripción del Proyecto

**Tiendario** es una API REST desarrollada con Spring Boot que proporciona una solución completa para la gestión de inventario de una tienda. El sistema permite administrar productos, categorías, proveedores, usuarios y realizar un seguimiento detallado de los movimientos de stock.

### 🎯 Caso de Uso Resuelto

El sistema resuelve la necesidad de una tienda de gestionar eficientemente su inventario mediante:
- Control de stock con entradas y salidas automatizadas
- Gestión de usuarios con diferentes niveles de acceso
- Administración de productos, categorías y proveedores
- Trazabilidad completa de movimientos de inventario
- Seguridad robusta con autenticación JWT

## 🏗️ Arquitectura

### Arquitectura Monolítica
El proyecto sigue una **arquitectura monolítica** implementada con Spring Boot, organizando el código en capas bien definidas:

```
├── configurations/     # Configuración de seguridad, JWT y Swagger
├── controllers/        # Controladores REST (Capa de presentación)
├── dto/                # Objetos de transferencia de datos
├── models/             # Entidades JPA (Capa de datos)
├── repositories/       # Repositorios de datos (Acceso a BD)
└── services/           # Lógica de negocio (Capa de servicios)
```

### Patrón MVC
- **Modelo (Model):** Entidades JPA para representar los datos
- **Vista (View):** Respuestas JSON de la API REST
- **Controlador (Controller):** Endpoints REST que manejan las peticiones

## 🛡️ Sistema de Seguridad

### Roles Implementados
1. **ADMINISTRADOR**
   - Acceso completo al sistema
   - Gestión de usuarios y roles
   - Todas las operaciones CRUD

2. **ENCARGADO_INVENTARIO**
   - Gestión completa de productos, categorías y proveedores
   - Registro de movimientos de stock
   - Consulta de reportes

3. **VENDEDOR**
   - Solo consulta de productos y categorías
   - Acceso de solo lectura al inventario

### Autenticación JWT
- **Login:** Genera token JWT válido por 1 hora
- **Validación:** Middleware que verifica tokens en cada request
- **Autorización:** Control de acceso basado en roles (RBAC)

## 🔧 Tecnologías Utilizadas

- **Framework:** Spring Boot 3.5.3
- **Lenguaje:** Java 21
- **Base de Datos:** MySQL
- **Seguridad:** Spring Security + JWT
- **Documentación:** Swagger/OpenAPI 3
- **ORM:** Spring Data JPA + Hibernate
- **Build Tool:** Maven

## 🗄️ Modelo de Datos

### Entidades Principales
- **Usuario:** Gestión de usuarios del sistema
- **Rol:** Definición de roles y permisos
- **Producto:** Catálogo de productos
- **Categoria:** Clasificación de productos
- **Proveedor:** Información de proveedores
- **MovimientoStock:** Historial de entradas y salidas

### Relaciones
- Usuario ↔ Rol (ManyToOne)
- Producto ↔ Categoria (ManyToOne)
- Producto ↔ Proveedor (ManyToOne)
- MovimientoStock ↔ Usuario (ManyToOne)
- MovimientoStock ↔ Producto (ManyToOne)

## 🚀 Funcionalidades Principales

### Gestión de Inventario
- ✅ Control automático de stock
- ✅ Registro de entradas y salidas
- ✅ Validación de stock suficiente
- ✅ Historial de movimientos

### Gestión de Productos
- ✅ CRUD completo de productos
- ✅ Búsqueda por nombre y categoría
- ✅ Asociación con proveedores

### Gestión de Usuarios
- ✅ Registro y autenticación
- ✅ Asignación de roles
- ✅ Encriptación de contraseñas

### APIs Documentadas
- ✅ Swagger UI integrado
- ✅ Documentación automática
- ✅ Pruebas desde interfaz web

## 🌐 Configuración CORS y Ngrok

El sistema está configurado para permitir peticiones desde aplicaciones frontend:

```java
// Configuración CORS
- Origen permitido: http://localhost:3000
- Métodos: GET, POST, PUT, DELETE, OPTIONS
- Headers: Todos (*)
- Credentials: Habilitado
```

### 🔗 Exposición con Ngrok
Para la **validación cruzada entre equipos**, el proyecto utiliza **ngrok** para exponer la API localmente:

```bash
# Ejecutar ngrok para exponer puerto 8080
ngrok http 8080
```

Esto genera una URL pública temporal como: `Al iniciar la clase...ngrok.io` que permite a otros equipos acceder a la API para las pruebas de validación cruzada.

## 📊 Endpoints Principales

### Autenticación
- `POST /login` - Iniciar sesión
- `POST /register` - Registrar usuario
- `POST /validate` - Validar token

### Gestión de Productos
- `GET /api/productos` - Listar productos
- `POST /api/productos` - Crear producto
- `PUT /api/productos/{id}` - Actualizar producto
- `DELETE /api/productos/{id}` - Eliminar producto

### Gestión de Inventario
- `GET /api/movimientos` - Historial de movimientos
- `POST /api/movimientos` - Registrar movimiento
- `GET /api/movimientos/usuario/{nombre}` - Por usuario
- `GET /api/movimientos/producto/{nombre}` - Por producto

## 🏃‍♂️ Instalación y Ejecución

### Prerrequisitos
- Java 21+
- MySQL 8.0+
- Maven 3.6+

### Configuración de Base de Datos
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/api_inventario
spring.datasource.username=root
spring.datasource.password=admin
```

### Ejecutar la Aplicación
```bash
mvn spring-boot:run
```

### Acceso a la Documentación
- **Swagger UI (Local):** http://localhost:8080/swagger-ui.html
- **Swagger UI (Ngrok):** https://5e84d1b57746.ngrok-free.app/swagger-ui.html
- **API Docs:** http://localhost:8080/v3/api-docs

### Para Validación Cruzada
1. **Ejecutar ngrok:** `ngrok http 8080`
2. **Compartir URL** generada con otros equipos
3. **Usar colección Postman** con la URL de ngrok
4. **Probar endpoints** desde Swagger con URL pública

## 👥 Equipo de Desarrollo

**Grupo 1 - Arquitectura Monolítica**
- Desarrollo de API REST con Spring Boot
- Implementación de seguridad JWT
- Documentación con Swagger
- Configuración CORS para frontend

---

*Proyecto desarrollado con Spring Boot 3.5.3 y Java 21*