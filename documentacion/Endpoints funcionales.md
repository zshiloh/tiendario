# 📋 Endpoints Funcionales - Sistema Tiendario

## 🌐 URLs Base
- **Desarrollo Local:** `http://localhost:8080`
- **Ngrok:** `Colocar al iniciar la clase...`

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
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJKdWFuIiwicm9sZSI6IkFETUlOSVNUUkFET1IiLCJpYXQiOjE2OTI5ODQwMDAsImV4cCI6MTY5Mjk4NzYwMH0.eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJKdWFuIiwicm9sZSI6IkFETUlOSVNUUkFET1IiLCJpYXQiOjE2OTI5ODQwMDAsImV4cCI6MTY5Mjk4NzYwMH0.6nyB2XEUBM-4_53CIKJBe_Vt6upW1C6igL7Ea2hHVyCwiz1EaGH_hOcpF3TkSfGp",
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
  "contrasena": "112233"
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
  "contrasena": "445566"
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

## 4. Iniciar Sesión - Usuario Ricardo
**URL:** `localhost:8080/login`  
**Tipo:** POST  
**Cabeceras:** Content-Type: application/json  
**Ingreso:**
```json
{
  "nombre": "Ricardo",
  "contrasena": "778899"
}
```
**Salida:** Login exitoso
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "nombre": "Ricardo",
  "rol": "ADMINISTRADOR"
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
**Salida:** Categorías existentes
```json
[
  {
    "id": 1,
    "nombre": "Bebidas",
    "descripcion": "Líquidos para beber"
  },
  {
    "id": 2,
    "nombre": "Snacks",
    "descripcion": "Productos para picar"
  },
  {
    "id": 3,
    "nombre": "Lacteos",
    "descripcion": "Productos derivados de la leche"
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
  "id": 4,
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
**Salida:** Productos existentes
```json
[
  {
    "id": 1,
    "nombre": "Coca Cola",
    "descripcion": "Bebida Gaseosa",
    "stock": 17,
    "precio": 2.5,
    "categoria": {
      "id": 1,
      "nombre": "Bebidas",
      "descripcion": "Líquidos para beber"
    },
    "proveedor": {
      "id": 1,
      "nombre": "Proveedor Uno",
      "contacto": "contacto@proveedoruno.com",
      "direccion": "Calle Principal 123"
    }
  },
  {
    "id": 2,
    "nombre": "Papas Fritas",
    "descripcion": "Bolsa de 100g",
    "stock": 30,
    "precio": 1.2,
    "categoria": {
      "id": 2,
      "nombre": "Snacks",
      "descripcion": "Productos para picar"
    },
    "proveedor": {
      "id": 2,
      "nombre": "Proveedor Dos",
      "contacto": "contacto@proveedordos.com",
      "direccion": "Av. Secundaria 456"
    }
  },
  {
    "id": 4,
    "nombre": "Leche entera",
    "descripcion": "Litro de leche entera",
    "stock": 20,
    "precio": 3.0,
    "categoria": {
      "id": 3,
      "nombre": "Lacteos",
      "descripcion": "Productos derivados de la leche"
    },
    "proveedor": {
      "id": 3,
      "nombre": "Proveedor Tres",
      "contacto": "contacto@proveedortres.com",
      "direccion": "Av. Nueva 789"
    }
  },
  {
    "id": 5,
    "nombre": "Galletas Oreo",
    "descripcion": "Galletas con crema sabor vainilla, paquete 154g",
    "stock": 75,
    "precio": 1.5,
    "categoria": {
      "id": 2,
      "nombre": "Snacks",
      "descripcion": "Productos para picar"
    },
    "proveedor": {
      "id": 1,
      "nombre": "Proveedor Uno",
      "contacto": "contacto@proveedoruno.com",
      "direccion": "Calle Principal 123"
    }
  },
  {
    "id": 6,
    "nombre": "Agua Mineral",
    "descripcion": "Agua Mineral natural 500ml",
    "stock": 50,
    "precio": 1.0,
    "categoria": {
      "id": 1,
      "nombre": "Bebidas",
      "descripcion": "Líquidos para beber"
    },
    "proveedor": {
      "id": 1,
      "nombre": "Proveedor Uno",
      "contacto": "contacto@proveedoruno.com",
      "direccion": "Calle Principal 123"
    }
  }
]
```

## 13. Obtener Producto por ID
**URL:** `localhost:8080/api/productos/1`  
**Tipo:** GET  
**Cabeceras:** Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ5...  
**Ingreso:** (sin body)  
**Salida:** Producto Coca Cola
```json
{
  "id": 1,
  "nombre": "Coca Cola",
  "descripcion": "Bebida Gaseosa",
  "stock": 17,
  "precio": 2.5,
  "categoria": {
    "id": 1,
    "nombre": "Bebidas",
    "descripcion": "Líquidos para beber"
  },
  "proveedor": {
    "id": 1,
    "nombre": "Proveedor Uno",
    "contacto": "contacto@proveedoruno.com",
    "direccion": "Calle Principal 123"
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
    "nombre": "Proveedor Uno",
    "contacto": "contacto@proveedoruno.com",
    "direccion": "Calle Principal 123"
  },
  {
    "id": 2,
    "nombre": "Proveedor Dos",
    "contacto": "contacto@proveedordos.com",
    "direccion": "Av. Secundaria 456"
  },
  {
    "id": 3,
    "nombre": "Proveedor Tres",
    "contacto": "contacto@proveedortres.com",
    "direccion": "Av. Nueva 789"
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
  "id": 13,
  "tipo": "SALIDA",
  "cantidad": 2,
  "fecha": "2025-08-17T15:00:00.000+00:00",
  "producto": {
    "id": 1,
    "nombre": "Coca Cola",
    "descripcion": "Bebida Gaseosa",
    "stock": 15,
    "precio": 2.5,
    "categoria": {
      "id": 1,
      "nombre": "Bebidas",
      "descripcion": "Líquidos para beber"
    },
    "proveedor": {
      "id": 1,
      "nombre": "Proveedor Uno",
      "contacto": "contacto@proveedoruno.com",
      "direccion": "Calle Principal 123"
    }
  },
  "usuario": {
    "id": 2,
    "nombre": "Dario",
    "correo": "dario@correo.com",
    "rol": {
      "id": 2,
      "nombre": "ENCARGADO_INVENTARIO",
      "descripcion": "Usuario que gestiona el inventario"
    }
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
| Ricardo | 778899 | ADMINISTRADOR | Acceso completo |
| Dario | 112233 | ENCARGADO_INVENTARIO | Productos, categorías, proveedores, movimientos |
| Anthony | 445566 | VENDEDOR | Solo consulta productos y categorías |

# 🏪 DATOS REALES DEL SISTEMA

## Productos Disponibles:
- **Coca Cola** (S/. 2.50) - Stock: 17
- **Papas Fritas** (S/. 1.20) - Stock: 30  
- **Leche entera** (S/. 3.00) - Stock: 20
- **Galletas Oreo** (S/. 1.50) - Stock: 75
- **Agua Mineral** (S/. 1.00) - Stock: 50

## Categorías:
- **Bebidas** - Líquidos para beber
- **Snacks** - Productos para picar  
- **Lacteos** - Productos derivados de la leche

## Proveedores:
- **Proveedor Uno** - contacto@proveedoruno.com
- **Proveedor Dos** - contacto@proveedordos.com
- **Proveedor Tres** - contacto@proveedortres.com

# 🌐 CONFIGURACIÓN PARA VALIDACIÓN CRUZADA

## URLs a usar:
- **Cambiar** `localhost:8080` por tu URL de ngrok
- **Ejemplo:** `Al iniciar la clase.../api/usuarios`

## Headers importantes:
- **Authorization:** `Bearer <token>`
- **Content-Type:** `application/json`

## Notas:
- Tokens expiran en **1 hora**
- Contraseñas están **encriptadas** con BCrypt
- Stock se **actualiza automáticamente** con movimientos