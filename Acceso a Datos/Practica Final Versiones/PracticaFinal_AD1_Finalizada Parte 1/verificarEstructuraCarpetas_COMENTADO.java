/**
 * ====================================================================================
 * FUNCIÓN: verificarEstructuraCarpetas()
 * ====================================================================================
 * 
 * PROPÓSITO:
 * Esta función verifica y crea automáticamente la estructura de directorios necesaria
 * para el funcionamiento del programa. Además, detecta archivos iniciales en la raíz
 * del proyecto y los mueve a sus ubicaciones correctas dentro de la estructura.
 * También crea los archivos de baja necesarios si no existen.
 * 
 * FUNCIONALIDADES:
 * 1. Verifica si existen todas las carpetas necesarias
 * 2. Crea las carpetas faltantes automáticamente
 * 3. Detecta archivos iniciales en la raíz del proyecto
 * 4. Mueve los archivos desde la raíz a sus ubicaciones correctas
 * 5. Crea archivos de baja vacíos si no existen
 * 6. Verifica que los archivos críticos estén presentes
 * 7. Muestra mensajes informativos sobre el proceso
 * 
 * ====================================================================================
 */

// Declaración del método como público y estático
// - público: puede ser llamado desde cualquier parte del programa
// - estático: pertenece a la clase, no necesita instancia de Main
// - void: no retorna ningún valor
public static void verificarEstructuraCarpetas() {
	
	// ====================================================================================
	// SECCIÓN 1: INICIALIZACIÓN Y MENSAJE INICIAL
	// ====================================================================================
	
	// Muestra un mensaje informativo al usuario indicando que se está verificando
	// la estructura de directorios y archivos
	System.out.println("Verificando estructura de directorios y archivos...");
	
	// Variable booleana que indica si todo el proceso se completó correctamente
	// Se inicializa en true y se cambia a false si ocurre algún error
	// Esta variable se usa al final para determinar qué mensaje mostrar
	boolean todoOk = true;
	
	// Variable booleana que indica si se creó la estructura de directorios
	// Se usa para mostrar un mensaje diferente al final si se creó la estructura
	// o si ya existía previamente
	boolean estructuraCreada = false;

	// ====================================================================================
	// SECCIÓN 2: DEFINICIÓN DE DIRECTORIOS NECESARIOS
	// ====================================================================================
	
	// Array de Strings que contiene todos los directorios que el programa necesita
	// para funcionar correctamente
	String[] directorios = {
		"PLANTAS",              // Carpeta principal para almacenar datos de plantas
		"EMPLEADOS",            // Carpeta principal para almacenar datos de empleados
		"EMPLEADOS/BAJA",       // Subcarpeta dentro de EMPLEADOS para empleados dados de baja
		                        // Nota: mkdirs() crea automáticamente la carpeta padre si no existe
		"TICKETS",              // Carpeta para almacenar los tickets de venta generados
		"DEVOLUCIONES"          // Carpeta para almacenar los tickets de devolución
	};

	// ====================================================================================
	// SECCIÓN 3: VERIFICACIÓN DE ESTRUCTURA DE DIRECTORIOS
	// ====================================================================================
	
	// Variable booleana que indica si falta alguna carpeta de la estructura
	// Se inicializa en false (asumiendo que todo está bien)
	boolean estructuraFalta = false;
	
	// Bucle for-each que recorre cada directorio del array
	// Para cada directorio, verifica si existe
	for (String dir : directorios) {
		// Crea un objeto File con la ruta del directorio
		// new File(dir) crea una referencia al archivo/carpeta, no lo crea físicamente
		// Si el directorio no existe, establece estructuraFalta en true
		if (!new File(dir).exists()) {
			estructuraFalta = true;
			// Nota: No usamos break aquí porque queremos verificar TODOS los directorios
			// para tener información completa, aunque ya sepamos que falta alguno
		}
	}

	// ====================================================================================
	// SECCIÓN 4: DETECCIÓN DE ARCHIVOS INICIALES EN LA RAÍZ
	// ====================================================================================
	
	// Crea objetos File para los archivos iniciales que podrían estar en la raíz
	// Estos son los archivos que el usuario podría haber colocado directamente
	// en el directorio raíz del proyecto antes de ejecutar el programa
	File plantasXMLRaiz = new File("plantas.xml");      // Archivo XML con datos de plantas
	File plantasDatRaiz = new File("plantas.dat");       // Archivo binario con precios y stock
	File empleadosDatRaiz = new File("empleados.dat");    // Archivo binario con datos de empleados
	
	// Evalúa si AL MENOS UNO de los archivos existe en la raíz
	// Usa el operador lógico OR (||) que retorna true si cualquiera de las
	// condiciones es verdadera
	// Esto permite detectar si hay archivos que necesitan ser movidos
	boolean archivosEnRaiz = plantasXMLRaiz.exists() || plantasDatRaiz.exists() || empleadosDatRaiz.exists();
	
	// ====================================================================================
	// SECCIÓN 5: MENSAJES DE DEBUG (INFORMACIÓN PARA EL DESARROLLADOR/USUARIO)
	// ====================================================================================
	
	// Si se encontraron archivos en la raíz, muestra información detallada
	// Esto ayuda a entender qué archivos se van a mover
	if (archivosEnRaiz) {
		// Mensaje general indicando que se encontraron archivos
		System.out.println("Archivos encontrados en la raíz:");
		
		// Verifica y muestra cada archivo individualmente si existe
		// Esto proporciona información específica sobre qué archivos se detectaron
		if (plantasXMLRaiz.exists()) System.out.println("  - plantas.xml");
		if (plantasDatRaiz.exists()) System.out.println("  - plantas.dat");
		if (empleadosDatRaiz.exists()) System.out.println("  - empleados.dat");
	}

	// ====================================================================================
	// SECCIÓN 6: CREACIÓN DE ESTRUCTURA DE DIRECTORIOS
	// ====================================================================================
	
	// Si falta alguna carpeta de la estructura, procede a crearlas
	if (estructuraFalta) {
		// Mensaje informativo para el usuario
		System.out.println("Creando estructura de directorios...");
		
		// Bucle que recorre cada directorio necesario
		for (String dir : directorios) {
			// Crea un objeto File para el directorio actual
			File carpeta = new File(dir);
			
			// Verifica si el directorio NO existe antes de intentar crearlo
			// Esto evita intentar crear directorios que ya existen
			if (!carpeta.exists()) {
				// Intenta crear el directorio (y todos los directorios padre necesarios)
				// mkdirs() retorna true si se creó exitosamente, false si falló
				if (carpeta.mkdirs()) {
					// Si se creó correctamente, muestra mensaje de confirmación
					System.out.println("Directorio creado: " + dir);
				} else {
					// Si falló la creación, muestra error y marca todoOk como false
					// System.err.println envía el mensaje al flujo de error (rojo en consola)
					System.err.println("Error al crear directorio: " + dir);
					todoOk = false;  // Marca que hubo un problema
				}
			}
			// Si el directorio ya existe, no hace nada (continúa con el siguiente)
		}
		
		// Marca que se creó la estructura (para el mensaje final)
		estructuraCreada = true;
	}

	// ====================================================================================
	// SECCIÓN 7: MOVIMIENTO DE ARCHIVOS DESDE LA RAÍZ
	// ====================================================================================
	
	// Si se detectaron archivos en la raíz, procede a moverlos a sus ubicaciones correctas
	if (archivosEnRaiz) {
		// Bloque try-catch para manejar posibles excepciones durante el movimiento
		// IOException puede ocurrir por problemas de permisos, archivos bloqueados, etc.
		try {
			// ========================================================================
			// SUBSECCIÓN 7.1: MOVER plantas.xml
			// ========================================================================
			
			// Verifica si el archivo plantas.xml existe en la raíz
			if (plantasXMLRaiz.exists()) {
				// Crea objetos Path usando Java NIO (más robusto que File.renameTo)
				// Paths.get() convierte un String en un objeto Path
				Path origenXML = Paths.get("plantas.xml");              // Ruta origen (raíz)
				Path destinoXML = Paths.get("PLANTAS/plantas.xml");     // Ruta destino (dentro de PLANTAS)
				
				// Verifica que el archivo destino NO exista antes de mover
				// Esto evita sobrescribir archivos existentes
				if (!Files.exists(destinoXML)) {
					// Mueve el archivo desde la raíz a PLANTAS/
					// Files.move() es más robusto que File.renameTo() porque:
					// - Funciona mejor entre diferentes sistemas de archivos
					// - Proporciona mejor manejo de errores
					// - Es más eficiente en algunos casos
					// StandardCopyOption.REPLACE_EXISTING: reemplaza si existe (aunque ya verificamos)
					Files.move(origenXML, destinoXML, StandardCopyOption.REPLACE_EXISTING);
					
					// Mensaje de confirmación para el usuario
					System.out.println("Archivo movido: plantas.xml -> PLANTAS/plantas.xml");
				} else {
					// Si el archivo destino ya existe, no lo mueve y muestra mensaje informativo
					System.out.println("Archivo PLANTAS/plantas.xml ya existe, no se mueve desde raíz");
				}
			}
			
			// ========================================================================
			// SUBSECCIÓN 7.2: MOVER plantas.dat
			// ========================================================================
			
			// Mismo proceso que para plantas.xml pero para el archivo binario de plantas
			if (plantasDatRaiz.exists()) {
				// Crea las rutas de origen y destino
				Path origenDat = Paths.get("plantas.dat");
				Path destinoDat = Paths.get("PLANTAS/plantas.dat");
				
				// Verifica que no exista en el destino
				if (!Files.exists(destinoDat)) {
					// Mueve el archivo
					Files.move(origenDat, destinoDat, StandardCopyOption.REPLACE_EXISTING);
					System.out.println("Archivo movido: plantas.dat -> PLANTAS/plantas.dat");
				} else {
					System.out.println("Archivo PLANTAS/plantas.dat ya existe, no se mueve desde raíz");
				}
			}
			
			// ========================================================================
			// SUBSECCIÓN 7.3: MOVER empleados.dat
			// ========================================================================
			
			// Mismo proceso para el archivo de empleados
			if (empleadosDatRaiz.exists()) {
				// Crea las rutas de origen y destino
				Path origenEmp = Paths.get("empleados.dat");
				Path destinoEmp = Paths.get("EMPLEADOS/empleados.dat");
				
				// Verifica que no exista en el destino
				if (!Files.exists(destinoEmp)) {
					// Mueve el archivo
					Files.move(origenEmp, destinoEmp, StandardCopyOption.REPLACE_EXISTING);
					System.out.println("Archivo movido: empleados.dat -> EMPLEADOS/empleados.dat");
				} else {
					System.out.println("Archivo EMPLEADOS/empleados.dat ya existe, no se mueve desde raíz");
				}
			}
			
		} catch (IOException e) {
			// Si ocurre una excepción durante el movimiento de archivos:
			// 1. Muestra un mensaje de error con la descripción
			System.err.println("Error al mover archivos: " + e.getMessage());
			// 2. Imprime el stack trace completo para depuración
			e.printStackTrace();
			// 3. Marca que hubo un error
			todoOk = false;
		}
	} else {
		// Si no se encontraron archivos en la raíz, muestra mensaje informativo
		// Esto ayuda al usuario a entender por qué no se movieron archivos
		System.out.println("No se encontraron archivos en la raíz para mover");
	}

	// ====================================================================================
	// SECCIÓN 8: CREACIÓN DE ARCHIVOS DE BAJA
	// ====================================================================================
	
	// Los archivos de baja se usan para almacenar registros de plantas y empleados
	// que han sido dados de baja pero que se mantienen para referencia histórica
	
	// ========================================================================
	// SUBSECCIÓN 8.1: CREAR ARCHIVOS DE BAJA DE PLANTAS
	// ========================================================================
	
	// Verifica que la carpeta PLANTAS exista antes de crear archivos dentro
	if (new File("PLANTAS").exists()) {
		
		// Intenta crear el archivo plantasbaja.dat (archivo binario para plantas dadas de baja)
		try {
			// Crea un objeto File para el archivo de baja de plantas
			File plantasBajaDat = new File("PLANTAS/plantasbaja.dat");
			
			// Verifica si el archivo NO existe antes de crearlo
			if (!plantasBajaDat.exists()) {
				// Crea un archivo vacío
				// createNewFile() retorna true si se creó, false si ya existía
				plantasBajaDat.createNewFile();
				System.out.println("Archivo creado: PLANTAS/plantasbaja.dat");
			}
			// Si el archivo ya existe, no hace nada (no lo sobrescribe)
		} catch (IOException e) {
			// Si hay un error al crear el archivo (permisos, disco lleno, etc.)
			System.err.println("Error al crear plantasbaja.dat: " + e.getMessage());
			// Nota: No marcamos todoOk = false aquí porque este archivo no es crítico
			// El programa puede funcionar sin él inicialmente
		}

		// Intenta crear el archivo plantasbaja.xml (archivo XML para plantas dadas de baja)
		try {
			File plantasBajaXML = new File("PLANTAS/plantasbaja.xml");
			
			// Verifica si el archivo NO existe
			if (!plantasBajaXML.exists()) {
				// Crea un BufferedWriter para escribir en el archivo
				// BufferedWriter es más eficiente que FileWriter directo porque
				// almacena datos en buffer antes de escribir al disco
				BufferedWriter bw = new BufferedWriter(new FileWriter(plantasBajaXML));
				
				// Escribe la declaración XML estándar
				// version="1.0": versión del estándar XML
				// encoding="UTF-8": codificación de caracteres (soporta acentos, ñ, etc.)
				bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				bw.newLine();  // Inserta un salto de línea
				
				// Escribe la etiqueta raíz del documento XML
				bw.write("<plantasBaja>");
				bw.newLine();
				
				// Cierra la etiqueta raíz (documento XML vacío pero válido)
				bw.write("</plantasBaja>");
				
				// Cierra el BufferedWriter
				// IMPORTANTE: Siempre cerrar los streams para liberar recursos
				// y asegurar que los datos se escriban al disco
				bw.close();
				
				System.out.println("Archivo creado: PLANTAS/plantasbaja.xml");
			}
		} catch (IOException e) {
			// Manejo de errores al crear el archivo XML
			System.err.println("Error al crear plantasbaja.xml: " + e.getMessage());
		}
	}

	// ========================================================================
	// SUBSECCIÓN 8.2: CREAR ARCHIVO DE BAJA DE EMPLEADOS
	// ========================================================================
	
	// Verifica que la carpeta EMPLEADOS/BAJA exista
	if (new File("EMPLEADOS/BAJA").exists()) {
		try {
			// Crea un objeto File para el archivo de baja de empleados
			File empleadosBajaDat = new File("EMPLEADOS/BAJA/empleadosBaja.dat");
			
			// Verifica si el archivo NO existe
			if (!empleadosBajaDat.exists()) {
				// Crea un archivo vacío
				empleadosBajaDat.createNewFile();
				System.out.println("Archivo creado: EMPLEADOS/BAJA/empleadosBaja.dat");
			}
		} catch (IOException e) {
			// Manejo de errores
			System.err.println("Error al crear empleadosBaja.dat: " + e.getMessage());
		}
	}

	// ====================================================================================
	// SECCIÓN 9: VERIFICACIÓN DE ARCHIVOS CRÍTICOS
	// ====================================================================================
	
	// Los archivos críticos son aquellos sin los cuales el programa NO puede funcionar
	// Si faltan, el programa mostrará alertas pero intentará continuar
	
	// Array con las rutas de los archivos críticos que deben existir
	String[] archivosCriticos = {
		"PLANTAS/plantas.xml",      // Archivo XML con información de plantas (código, nombre, foto, descripción)
		"PLANTAS/plantas.dat",      // Archivo binario con precios y stock de plantas
		"EMPLEADOS/empleados.dat"   // Archivo binario con datos de empleados (ID, nombre, contraseña, cargo)
	};
	
	// Bucle for-each que recorre cada archivo crítico
	for (String archivo : archivosCriticos) {
		// Crea un objeto File y verifica si NO existe
		if (!new File(archivo).exists()) {
			// Si el archivo no existe, muestra una alerta
			// System.err.println muestra en rojo (flujo de error) para mayor visibilidad
			System.err.println("ALERTA: '" + archivo + "' no existe. Por favor añade datos.");
			
			// Marca que hay un problema crítico
			todoOk = false;
		}
		// Si el archivo existe, continúa con el siguiente sin hacer nada
	}

	// ====================================================================================
	// SECCIÓN 10: MENSAJE FINAL
	// ====================================================================================
	
	// Muestra un mensaje final resumiendo el resultado de la verificación
	if (todoOk) {
		// Si todo está bien (todoOk == true):
		if (estructuraCreada) {
			// Si se creó la estructura, muestra mensaje indicando que se creó y organizó
			// \n al inicio y final para separar visualmente el mensaje
			System.out.println("\nVerificación completada. Estructura creada y archivos organizados.\n");
		} else {
			// Si la estructura ya existía, muestra mensaje indicando que todo está presente
			System.out.println("Verificación completada. Todos los archivos y directorios están presentes.\n");
		}
	} else {
		// Si hubo problemas (todoOk == false), muestra advertencia
		// \n al inicio y final para separar visualmente
		System.err.println("\nFaltan archivos o directorios críticos. El programa puede fallar.\n");
	}
	
	// Fin del método - retorna implícitamente (void)
}

/**
 * ====================================================================================
 * RESUMEN DE LA FUNCIÓN
 * ====================================================================================
 * 
 * FLUJO DE EJECUCIÓN:
 * 1. Inicializa variables de control
 * 2. Define directorios necesarios
 * 3. Verifica si falta estructura
 * 4. Detecta archivos en la raíz
 * 5. Crea estructura si falta
 * 6. Mueve archivos desde la raíz
 * 7. Crea archivos de baja
 * 8. Verifica archivos críticos
 * 9. Muestra mensaje final
 * 
 * CASOS DE USO:
 * - Primera ejecución: Crea toda la estructura y mueve archivos
 * - Ejecución normal: Solo verifica que todo esté presente
 * - Recuperación: Recrea estructura si se eliminó accidentalmente
 * 
 * MANEJO DE ERRORES:
 * - Errores no críticos: Se registran pero no detienen el programa
 * - Errores críticos: Se marcan en todoOk y se muestra advertencia final
 * 
 * ====================================================================================
 */

