# 🚀 Guía de Validación Cruzada - Sistema Tiendario

---

## 📋 REQUISITOS PREVIOS

### ✅ Lo que necesitas tener:
- **Postman** instalado en tu computadora
- **Archivo** descargar `tiendario_collection.json` (te lo proporcionamos)
- **URL de ngrok** (te la proporcionamos el día de la validación)
- **20-30 minutos** para realizar todas las pruebas

---

## 🔧 PASO 1: IMPORTAR LA COLECCIÓN

### 1.1 Abrir Postman
- Ejecuta **Postman** en tu computadora

### 1.2 Importar archivo JSON
1. **Clic en "Import"** (botón en la parte superior)
2. **Seleccionar "Upload Files"**
3. **Buscar y seleccionar** `tiendario_collection.json`
4. **Clic "Import"**

### 1.3 Verificar importación exitosa
Deberías ver en el panel izquierdo:
```
📁 Tiendario API
  📁 Autenticación
    - POST Iniciar sesión
    - POST Registrarse
    - POST Validar token
  📁 Usuarios
    - GET Todos los usuarios
    - GET Usuario por ID
    - POST Crear usuario
    - etc.
  📁 Productos
  📁 Categorías
  📁 Movimientos
  📁 Proveedores
  📁 Roles
```

---

## 🌐 PASO 2: CONFIGURAR URL DE NGROK

### 2.1 Verificar variables de colección
1. **Seleccionar** en "Tiendario API [ngrok]"
2. **Ir a pestaña "Variables"**

### 2.2 Variables disponibles
Deberías ver:
- **`localhost`**: `http://localhost:8080`
- **`ngrok`**: `https://5e84d1b57746.ngrok-free.app` (URL que te proporcionamos)
- **`token`**: (vacío, se llena automáticamente)

### 2.3 ¿Necesitas cambiar la URL de ngrok?
Si la URL de ngrok no se encuentra o es diferente:
1. **Cambiar valor "Initial value"** Donde dice `URL` cambia por la nueva URL
2. **Valor "Current value"** Se coloca automaticamente `Si no lo hace, coloca la URL manualmente`
3. **Clic "Save" o CTRL + S**

---

## 🔐 PASO 3: REALIZAR LOGIN (OBLIGATORIO)

### 3.1 Seleccionar endpoint de login
1. **Expandir** carpeta "Autenticación"
2. **Clic en** "POST Iniciar sesión"

### 3.2 Verificar configuración
- **URL debe mostrar:** `{{ngrok}}/login`
- **Method:** POST
- **Body:** Ya debe tener credenciales configuradas

### 3.3 Credenciales disponibles
**Opción 1 - Administrador (recomendado):**
```json
{
  "nombre": "Juan",
  "contrasena": "123456"
}
```

**Opción 2 - Encargado de Inventario:**
```json
{
  "nombre": "Dario",
  "contrasena": "112233"
}
```

**Opción 3 - Vendedor (permisos limitados):**
```json
{
  "nombre": "Anthony",
  "contrasena": "445566"
}
```

### 3.4 Ejecutar login
1. **Verificar body** con credenciales
2. **Clic "Send"**
3. **Verificar respuesta 200 OK**

### 3.5 Respuesta esperada
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "nombre": "Juan",
  "rol": "ADMINISTRADOR"
}
```

### 3.6 Token automático
- El **token se guarda automáticamente** en la variable `{{token}}`
- **No necesitas copiarlo manualmente**

---

## ✅ PASO 4: PRUEBAS OBLIGATORIAS

### 4.1 Verificar acceso a usuarios (Solo administradores)

**Endpoint:** "GET Todos los usuarios"
1. **Ir a** carpeta "Usuarios"
2. **Seleccionar** "GET Todos los usuarios"
3. **Verificar URL:** `{{ngrok}}/api/usuarios`
4. **Clic "Send"**

**Resultado esperado:**
- **Status:** 200 OK
- **Response:** Lista de 5 usuarios (Juan, Dario, Anthony, Ricardo, UserTest)

### 4.2 Verificar productos

**Endpoint:** "GET Todos los productos"
1. **Ir a** carpeta "Productos"
2. **Seleccionar** "GET Todos los productos"
3. **Clic "Send"**

**Resultado esperado:**
- **Status:** 200 OK
- **Response:** Lista de productos con categorías y proveedores

### 4.3 Crear una categoría nueva

**Endpoint:** "POST Crear categoría"
1. **Ir a** carpeta "Categorías"
2. **Seleccionar** "POST Crear categoría"
3. **Modificar body:**
```json
{
  "nombre": "Prueba [Nombre de tu equipo]",
  "descripcion": "Categoría creada por equipo [tu nombre] para validación"
}
```
4. **Clic "Send"**

**Resultado esperado:**
- **Status:** 200 OK
- **Response:** Categoría creada con ID asignado

### 4.4 Verificar que la categoría se creó

**Endpoint:** "GET Todas las categorías"
1. **Seleccionar** "GET Todas las categorías"
2. **Clic "Send"**
3. **Verificar** que tu categoría aparece en la lista

---

## 🛡️ PASO 5: PROBAR SEGURIDAD (ROLES)

### 5.1 Login como vendedor
1. **Usar** "POST Iniciar sesión"
2. **Cambiar body** a:
```json
{
  "nombre": "Anthony",
  "contrasena": "123456"
}
```
3. **Clic "Send"**

### 5.2 Intentar acceder a usuarios (debe fallar)
1. **Probar** "GET Todos los usuarios"
2. **Resultado esperado:** **403 Forbidden**

### 5.3 Verificar acceso permitido
1. **Probar** "GET Todos los productos"
2. **Resultado esperado:** **200 OK** (vendedores pueden ver productos)

---

## 📊 PASO 6: PROBAR MOVIMIENTOS DE STOCK

### 6.1 Login como administrador o encargado
```json
{
  "nombre": "Dario",
  "contrasena": "112233"
}
```

### 6.2 Ver movimientos existentes
1. **Ir a** "Movimientos" → "GET Todos los movimientos"
2. **Clic "Send"**
3. **Anotar stock actual** de algún producto

### 6.3 Crear movimiento de entrada
1. **Seleccionar** "POST Crear movimiento"
2. **Body:**
```json
{
    "tipo": "ENTRADA",
    "cantidad": 8,
    "fecha": "2025-08-17",
    "producto": {"id": 1},
    "usuario": {"id": 2}
}
```
3. **Clic "Send"**

### 6.4 Verificar actualización automática de stock
1. **Probar** "GET Producto por ID" con ID 1
2. **Verificar** que el stock aumentó en 5

---

## 📝 PASO 7: DOCUMENTAR RESULTADOS

### 7.1 Endpoints que DEBEN funcionar:
- ✅ POST /login (con las 3 credenciales)
- ✅ GET /api/usuarios (solo admin)
- ✅ GET /api/productos (todos los roles)
- ✅ POST /api/categorias (admin/encargado)
- ✅ GET /api/categorias (todos)
- ✅ POST /api/movimientos (admin/encargado)

### 7.2 Seguridad que DEBE aplicarse:
- ✅ Vendedor NO puede acceder a /api/usuarios (403)
- ✅ Sin token NO puede acceder a endpoints protegidos (401)
- ✅ Token expirado NO funciona (401)

### 7.3 Funcionalidades que DEBEN verificarse:
- ✅ Login genera token válido
- ✅ CRUD de categorías funciona
- ✅ Movimientos actualizan stock automáticamente
- ✅ Diferentes roles tienen diferentes permisos

---

## ❌ PROBLEMAS COMUNES Y SOLUCIONES

### Problema 1: "Could not get response"
**Causa:** URL de ngrok incorrecta o servidor no corriendo
**Solución:** Verificar URL de ngrok con el equipo

### Problema 2: "401 Unauthorized"
**Causa:** Token no válido o expirado
**Solución:** Hacer login nuevamente

### Problema 3: "403 Forbidden"
**Causa:** Usuario sin permisos para ese endpoint
**Solución:** ¡Está bien! Es la seguridad funcionando

### Problema 4: Variables no se ven
**Causa:** Colección mal importada
**Solución:** Re-importar el archivo JSON

### Problema 5: Token no se guarda automáticamente
**Causa:** Script del login no funcionó
**Solución:** Copiar token manualmente y pegarlo en Authorization header

---

## 📊 REPORTE FINAL

### Al terminar, debes poder confirmar:

**✅ FUNCIONALIDADES BÁSICAS:**
- [ ] Login funciona con 3 usuarios diferentes
- [ ] Endpoints protegidos requieren token
- [ ] CRUD de categorías funciona
- [ ] Consulta de productos funciona

**✅ SEGURIDAD:**
- [ ] Roles restringen acceso correctamente
- [ ] Tokens expiran apropiadamente
- [ ] Sin token = sin acceso

**✅ LÓGICA DE NEGOCIO:**
- [ ] Movimientos de stock actualizan inventario
- [ ] Datos se persisten correctamente
- [ ] Relaciones entre entidades funcionan

---

## 📞 CONTACTO

Si tienes problemas durante la validación:
1. **Verificar** que el servidor esté corriendo (ngrok activo)
2. **Revisar** que la URL de ngrok sea correcta
3. **Comprobar** que el token sea válido (hacer login nuevamente)