# 📋 Endpoints Funcionales - Sistema Tiendario
## (Con datos reales del sistema)

## 🌐 URLs Base
- **Desarrollo Local:** `http://localhost:8080`
- **Ngrok (Validación Cruzada):** `https://tu-url.ngrok.io`

---

# 🔐 AUTENTICACIÓN

## 1. Iniciar Sesión
**URL:** `localhost:8080/login`  
**Tipo:** POST  
**Cabeceras:** Content-Type: application/json  
**Ingreso:**
```json
{
  "nombre": "Juan",
  "contrasena": "123456"
}
```
**Salida:** Login exitoso
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJKdWFuIiwicm9sZSI6IkFETUlOSVNUUkFET1IiLCJpYXQiOjE2OTI5ODQwMDAsImV4cCI6MTY5Mjk4NzYwMH0.signature",
  "nombre": "Juan",
  "rol": "ADMINISTRADOR"
}
```

## 2. Iniciar Sesión - Usuario Encargado
**URL:** `localhost:8080/login`  
**Tipo:** POST  
**Cabeceras:** Content-Type: application/json  
**Ingreso:**
```json
{
  "nombre": "Dario",
  "contrasena": "123456"
}
```
**Salida:** Login exitoso
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "nombre": "Dario",
  "rol": "ENCARGADO_INVENTARIO"
}
```

## 3. Iniciar Sesión - Usuario Vendedor
**URL:** `localhost:8080/login`  
**Tipo:** POST  
**Cabeceras:** Content-Type: application/json  
**Ingreso:**
```json
{
  "nombre": "Anthony",
  "contrasena": "123456"
}
```
**Salida:** Login exitoso
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "nombre": "Anthony",
  "rol": "VENDEDOR"
}
```

## 4. Validar Token
**URL:** `localhost:8080/validate`  
**Tipo:** POST  
**Cabeceras:** Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...  
**Ingreso:** (sin body, token en header)  
**Salida:** Token válido
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "nombre": "Juan",
  "rol": "ADMINISTRADOR"
}
```

---

# 👥 GESTIÓN DE USUARIOS (Solo ADMINISTRADOR)

## 5. Listar Todos los Usuarios
**URL:** `localhost:8080/api/usuarios`  
**Tipo:** GET  
**Cabeceras:** Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ5...  
**Ingreso:** (sin body)  
**Salida:** Lista de usuarios existentes
```json
[
  {
    "id": 1,
    "nombre": "Juan",
    "correo": "juan@correo.com",
    "contrasena": "$2a$10$1qG10o1qGFkNSmVjfoqVueiXk.jxPFpijdUjQcErsQHX1L8mqHCH2",
    "rol": {
      "id": 1,
      "nombre": "ADMINISTRADOR",
      "descripcion": "Administrador con acceso completo"
    }
  },
  {
    "id": 2,
    "nombre": "Dario",
    "correo": "dario@correo.com",
    "contrasena": "$2a$10$1.gvGUKPXDTaB.yC.d.qHUERjdU1wdSEhrvFzaoPs9SInWG4CCtGe",
    "rol": {
      "id": 2,
      "nombre": "ENCARGADO_INVENTARIO",
      "descripcion": "Usuario que gestiona el inventario"
    }
  },
  {
    "id": 3,
    "nombre": "Anthony",
    "correo": "anthony@correo.com",
    "contrasena": "$2a$10$1.gvGUKPXDTaB.yC.d.qHUERjdU1wdSEhrvFzaoPs9SInWG4CCtGe",
    "rol": {
      "id": 3,
      "nombre": "VENDEDOR",
      "descripcion": "Vendedor con acceso de consulta"
    }
  }
]
```

## 6. Obtener Usuario por ID
**URL:** `localhost:8080/api/usuarios/1`  
**Tipo:** GET  
**Cabeceras:** Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ5...  
**Ingreso:** (sin body)  
**Salida:** Usuario Juan
```json
{
  "id": 1,
  "nombre": "Juan",
  "correo": "juan@correo.com",
  "contrasena": "$2a$10$1qG10o1qGFkNSmVjfoqVueiXk.jxPFpijdUjQcErsQHX1L8mqHCH2",
  "rol": {
    "id": 1,
    "nombre": "ADMINISTRADOR",
    "descripcion": "Administrador con acceso completo"
  }
}
```

## 7. Buscar Usuarios por Rol
**URL:** `localhost:8080/api/usuarios/buscar/rol/VENDEDOR`  
**Tipo:** GET  
**Cabeceras:** Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ5...  
**Ingreso:** (sin body)  
**Salida:** Usuarios vendedores
```json
[
  {
    "id": 3,
    "nombre": "Anthony",
    "correo": "anthony@correo.com",
    "contrasena": "$2a$10$1.gvGUKPXDTaB.yC.d.qHUERjdU1wdSEhrvFzaoPs9SInWG4CCtGe",
    "rol": {
      "id": 3,
      "nombre": "VENDEDOR",
      "descripcion": "Vendedor con acceso de consulta"
    }
  }
]
```

---

# 🏷️ GESTIÓN DE ROLES (Solo ADMINISTRADOR)

## 8. Listar Todos los Roles
**URL:** `localhost:8080/api/roles`  
**Tipo:** GET  
**Cabeceras:** Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ5...  
**Ingreso:** (sin body)  
**Salida:** Roles del sistema
```json
[
  {
    "id": 1,
    "nombre": "ADMINISTRADOR",
    "descripcion": "Administrador con acceso completo"
  },
  {
    "id": 2,
    "nombre": "ENCARGADO_INVENTARIO",
    "descripcion": "Usuario que gestiona el inventario"
  },
  {
    "id": 3,
    "nombre": "VENDEDOR",
    "descripcion": "Vendedor con acceso de consulta"
  }
]
```

## 9. Obtener Rol por ID
**URL:** `localhost:8080/api/roles/1`  
**Tipo:** GET  
**Cabeceras:** Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ5...  
**Ingreso:** (sin body)  
**Salida:** Rol ADMINISTRADOR
```json
{
  "id": 1,
  "nombre": "ADMINISTRADOR",
  "descripcion": "Administrador con acceso completo"
}
```

---

# 📂 GESTIÓN DE CATEGORÍAS

## 10. Listar Todas las Categorías
**URL:** `localhost:8080/api/categorias`  
**Tipo:** GET  
**Cabeceras:** Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ5...  
**Ingreso:** (sin body)  
**Salida:** Categorías existentes (ejemplo)
```json
[
  {
    "id": 1,
    "nombre": "Electrónicos",
    "descripcion": "Productos electrónicos y tecnológicos"
  },
  {
    "id": 2,
    "nombre": "Oficina",
    "descripcion": "Artículos de oficina y papelería"
  }
]
```

## 11. Crear Categoría (ADMINISTRADOR/ENCARGADO_INVENTARIO)
**URL:** `localhost:8080/api/categorias`  
**Tipo:** POST  
**Cabeceras:** Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ5..., Content-Type: application/json  
**Ingreso:**
```json
{
  "nombre": "Validación Cruzada",
  "descripcion": "Categoría para pruebas de otros equipos"
}
```
**Salida:** Categoría creada
```json
{
  "id": 3,
  "nombre": "Validación Cruzada",
  "descripcion": "Categoría para pruebas de otros equipos"
}
```

---

# 📦 GESTIÓN DE PRODUCTOS

## 12. Listar Todos los Productos
**URL:** `localhost:8080/api/productos`  
**Tipo:** GET  
**Cabeceras:** Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ5...  
**Ingreso:** (sin body)  
**Salida:** Productos existentes (ejemplo)
```json
[
  {
    "id": 1,
    "nombre": "Laptop HP",
    "descripcion": "Laptop HP Pavilion 15",
    "stock": 15,
    "precio": 2200.99,
    "categoria": {
      "id": 1,
      "nombre": "Electrónicos",
      "descripcion": "Productos electrónicos y tecnológicos"
    },
    "proveedor": {
      "id": 1,
      "nombre": "TechWorld",
      "contacto": "ventas@techworld.com",
      "direccion": "Av. Tecnología 123"
    }
  }
]
```

## 13. Obtener Producto por ID
**URL:** `localhost:8080/api/productos/1`  
**Tipo:** GET  
**Cabeceras:** Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ5...  
**Ingreso:** (sin body)  
**Salida:** Producto específico
```json
{
  "id": 1,
  "nombre": "Laptop HP",
  "descripcion": "Laptop HP Pavilion 15",
  "stock": 15,
  "precio": 2200.99,
  "categoria": {
    "id": 1,
    "nombre": "Electrónicos",
    "descripcion": "Productos electrónicos y tecnológicos"
  },
  "proveedor": {
    "id": 1,
    "nombre": "TechWorld",
    "contacto": "ventas@techworld.com",
    "direccion": "Av. Tecnología 123"
  }
}
```

---

# 🏢 GESTIÓN DE PROVEEDORES (ADMINISTRADOR/ENCARGADO_INVENTARIO)

## 14. Listar Todos los Proveedores
**URL:** `localhost:8080/api/proveedores`  
**Tipo:** GET  
**Cabeceras:** Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ5...  
**Ingreso:** (sin body)  
**Salida:** Proveedores existentes
```json
[
  {
    "id": 1,
    "nombre": "TechWorld",
    "contacto": "ventas@techworld.com",
    "direccion": "Av. Tecnología 123"
  },
  {
    "id": 2,
    "nombre": "OfficeMax Peru",
    "contacto": "info@officemax.pe",
    "direccion": "Jr. Comercio 456"
  }
]
```

---

# 📊 GESTIÓN DE MOVIMIENTOS DE STOCK (ADMINISTRADOR/ENCARGADO_INVENTARIO)

## 15. Listar Todos los Movimientos
**URL:** `localhost:8080/api/movimientos`  
**Tipo:** GET  
**Cabeceras:** Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ5...  
**Ingreso:** (sin body)  
**Salida:** Movimientos de stock
```json
[
  {
    "id": 1,
    "tipo": "ENTRADA",
    "cantidad": 20,
    "fecha": "2025-08-17T10:30:00.000+00:00",
    "producto": {
      "id": 1,
      "nombre": "Laptop HP"
    },
    "usuario": {
      "id": 2,
      "nombre": "Dario"
    }
  }
]
```

## 16. Registrar Movimiento de Stock
**URL:** `localhost:8080/api/movimientos`  
**Tipo:** POST  
**Cabeceras:** Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ5..., Content-Type: application/json  
**Ingreso:**
```json
{
  "tipo": "SALIDA",
  "cantidad": 2,
  "fecha": "2025-08-17T15:00:00.000Z",
  "producto": {
    "id": 1
  },
  "usuario": {
    "id": 2
  }
}
```
**Salida:** Movimiento registrado (stock se actualiza automáticamente)
```json
{
  "id": 2,
  "tipo": "SALIDA",
  "cantidad": 2,
  "fecha": "2025-08-17T15:00:00.000+00:00",
  "producto": {
    "id": 1,
    "nombre": "Laptop HP",
    "stock": 13
  },
  "usuario": {
    "id": 2,
    "nombre": "Dario"
  }
}
```

---

# ❌ RESPUESTAS DE ERROR

## Error 401 - No Autorizado
**Ejemplo:** Request sin token
```json
{
  "timestamp": "2025-08-17T20:15:30.123+00:00",
  "status": 401,
  "error": "Unauthorized",
  "message": "Full authentication is required to access this resource",
  "path": "/api/usuarios"
}
```

## Error 403 - Prohibido
**Ejemplo:** Vendedor intentando acceder a usuarios
```json
{
  "timestamp": "2025-08-17T20:15:30.123+00:00",
  "status": 403,
  "error": "Forbidden",
  "message": "Access Denied",
  "path": "/api/usuarios"
}
```

## Error 400 - Credenciales Inválidas
**Ejemplo:** Login con contraseña incorrecta
```json
"Credenciales inválidas"
```

---

# 📝 USUARIOS DISPONIBLES PARA PRUEBAS

| Usuario | Contraseña | Rol | Permisos |
|---------|------------|-----|----------|
| Juan | 123456 | ADMINISTRADOR | Acceso completo |
| Dario | 123456 | ENCARGADO_INVENTARIO | Productos, categorías, proveedores, movimientos |
| Anthony | 123456 | VENDEDOR | Solo consulta productos y categorías |

# 🌐 CONFIGURACIÓN PARA VALIDACIÓN CRUZADA

## URLs a usar:
- **Cambiar** `localhost:8080` por tu URL de ngrok
- **Ejemplo:** `https://abc123.ngrok.io/api/usuarios`

## Headers importantes:
- **Authorization:** `Bearer <token>`
- **Content-Type:** `application/json`

## Notas:
- Tokens expiran en **1 hora**
- Contraseñas están **encriptadas** con BCrypt
- Stock se **actualiza automáticamente** con movimientos