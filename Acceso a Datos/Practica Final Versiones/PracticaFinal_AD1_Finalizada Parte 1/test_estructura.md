# Guía de Testing para verificarEstructuraCarpetas()

## Escenarios de Prueba

### Escenario 1: Estructura Completa (Caso Normal)
**Estado inicial:**
- Todas las carpetas existen: PLANTAS, EMPLEADOS, EMPLEADOS/BAJA, TICKETS, DEVOLUCIONES
- Archivos en su lugar: PLANTAS/plantas.xml, PLANTAS/plantas.dat, EMPLEADOS/empleados.dat

**Resultado esperado:**
```
Verificando estructura de directorios y archivos...
Verificación completada. Todos los archivos y directorios están presentes.
```

---

### Escenario 2: Archivos en Raíz sin Estructura
**Preparación:**
1. Mueve `PLANTAS/plantas.xml` → `plantas.xml` (raíz)
2. Mueve `PLANTAS/plantas.dat` → `plantas.dat` (raíz)
3. Mueve `EMPLEADOS/empleados.dat` → `empleados.dat` (raíz)
4. Elimina las carpetas: PLANTAS, EMPLEADOS, TICKETS, DEVOLUCIONES

**Resultado esperado:**
```
Verificando estructura de directorios y archivos...
 Creando estructura de directorios...
  Directorio creado: PLANTAS
  Directorio creado: EMPLEADOS
  Directorio creado: EMPLEADOS/BAJA
  Directorio creado: TICKETS
  Directorio creado: DEVOLUCIONES
  Archivo movido: plantas.xml -> PLANTAS/plantas.xml
  Archivo movido: plantas.dat -> PLANTAS/plantas.dat
  Archivo movido: empleados.dat -> EMPLEADOS/empleados.dat
  Archivo creado: PLANTAS/plantasbaja.dat
  Archivo creado: PLANTAS/plantasbaja.xml
  Archivo creado: EMPLEADOS/BAJA/empleadosBaja.dat

 Verificación completada. Estructura creada y archivos organizados.
```

**Verificaciones:**
- ✓ Carpeta PLANTAS existe
- ✓ Carpeta EMPLEADOS existe
- ✓ Carpeta EMPLEADOS/BAJA existe
- ✓ Archivo PLANTAS/plantas.xml existe
- ✓ Archivo PLANTAS/plantas.dat existe
- ✓ Archivo EMPLEADOS/empleados.dat existe
- ✓ Archivo PLANTAS/plantasbaja.dat existe (vacío)
- ✓ Archivo PLANTAS/plantasbaja.xml existe (con estructura XML)
- ✓ Archivo EMPLEADOS/BAJA/empleadosBaja.dat existe (vacío)

---

### Escenario 3: Solo Faltan Carpetas
**Preparación:**
1. Elimina solo las carpetas (mantén archivos si existen en su lugar)
2. O crea un proyecto nuevo sin carpetas

**Resultado esperado:**
```
Verificando estructura de directorios y archivos...
 Creando estructura de directorios...
  Directorio creado: PLANTAS
  Directorio creado: EMPLEADOS
  ...
 ALERTA: 'PLANTAS/plantas.xml' no existe. Por favor añade datos.
```

---

### Escenario 4: Archivos Parciales en Raíz
**Preparación:**
1. Solo `plantas.xml` en la raíz
2. Elimina carpetas

**Resultado esperado:**
- Crea estructura
- Mueve solo `plantas.xml`
- Muestra alertas para archivos faltantes

---

## Cómo Ejecutar las Pruebas

### Opción 1: Desde IDE (Eclipse/IntelliJ/VS Code)
1. Abre el proyecto
2. Ejecuta `Main.java` (método main)
3. Observa la salida en consola

### Opción 2: Desde Terminal/CMD
```bash
# Compilar
javac -d bin src/PF/*.java

# Ejecutar
java -cp bin PF.Main
```

### Opción 3: Script de Prueba Automatizado
Puedes crear un script que:
1. Haga backup de la estructura actual
2. Ejecute cada escenario
3. Restaure el estado original

---

## Checklist de Verificación

Después de cada prueba, verifica:

- [ ] Carpetas creadas correctamente
- [ ] Archivos movidos a ubicaciones correctas
- [ ] Archivos de baja creados (vacíos o con estructura inicial)
- [ ] Mensajes de consola correctos
- [ ] No hay errores de excepción
- [ ] El programa continúa ejecutándose después de la verificación

---

## Notas Importantes

- Los archivos de baja se crean vacíos (excepto plantasbaja.xml que tiene estructura XML básica)
- Si un archivo ya existe en su destino, NO se sobrescribe
- La función NO elimina archivos, solo los mueve desde la raíz
- Si faltan archivos críticos, el programa muestra alertas pero continúa

