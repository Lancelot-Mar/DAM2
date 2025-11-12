package PF;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Main {
	
	//Expresiones Regulares:
	static String REGEX_NOMBRE = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$";
	static String REGEX_DECRIP = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ.,\\s]+$";

	static String REGEX_FOTO = "^[a-zA-Z0-9_\\-.]+$"; 
	// Mejor versión: nombres de archivo seguros

	static String REGEX_PRECIO = "^[0-9]+([.,][0-9]{1,2})?$";
	static String REGEX_STOCK = "^[1-9][0-9]*$";

	static String REGEX_ID = "^[0-9]+$"; // misma corrección
	static String REGEX_CONTRASEGNA = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\{}\\[\\]|:;\"'<>,.?/]).{8,}$";
	static String REGEX_CARGO = "^([Gg]estor|[Vv]endedor)$";
	
	
	static Scanner entrada = new Scanner(System.in);
	
	static List<Planta> plantasArr = new ArrayList<Planta>();
	static List<Planta> plantasBajaArr = new ArrayList<Planta>();
	static List<Empleado> empleadosArr = new ArrayList<Empleado>();
	static List<Empleado> empleadosBajaArr = new ArrayList<Empleado>();
	static List<ItemVenta> cesta = new ArrayList<ItemVenta>();
	
	public static void LecturaDatosPlantas() {
		
	    try {	        
	        // Leer el archivo XML
	        File ficheroDat = new File("PLANTAS/plantas.dat");
	        File ficheroXML = new File("PLANTAS/plantas.xml");
	        
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docB = dbf.newDocumentBuilder();
	        Document doc = docB.parse(ficheroXML);
	        doc.getDocumentElement().normalize();
	        
	        NodeList Lista = doc.getElementsByTagName("planta");
	        int cantidad = Lista.getLength();

	        // Continuar leyendo mientras haya datos disponibles en plantas.dat
	        for(int i = 0; i < cantidad; i++) {

	            // Leer datos del XML
	            Node nodo = Lista.item(i);

	            if(nodo.getNodeType() == Node.ELEMENT_NODE) {
	                Element plantas = (Element) nodo;

	                String codigo = plantas.getElementsByTagName("codigo").item(0).getTextContent();
	                String nombre = plantas.getElementsByTagName("nombre").item(0).getTextContent();
	                String foto = plantas.getElementsByTagName("foto").item(0).getTextContent();
	                String descripcion = plantas.getElementsByTagName("descripcion").item(0).getTextContent();

	                // Crear planta con datos del XML
	                Planta resultado = new Planta(Integer.valueOf(codigo), nombre, foto, descripcion);
	                
	                // Cargar precio y stock del archivo .dat
	                resultado.getPrecio(Integer.valueOf(codigo), ficheroDat);
	                resultado.getStock(Integer.valueOf(codigo), ficheroDat);
	                
	                // Agregar a la lista
	                plantasArr.add(resultado);
	            }
	        }
	        
	    } catch (IOException | SAXException | ParserConfigurationException e) {
	        e.printStackTrace();

	    } 
	}
	
	/*
	private static void cargarEmpleados() {

	    File f = new File("EMPLEADOS/empleados.dat");

	    // Si no existe o está vacío, dejar la lista vacía y salir
	    if (!f.exists() || f.length() == 0) {
	        empleadosArr.clear();
	        return;
	    }

	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {

	        Object o = ois.readObject();

	        if (o instanceof ArrayList) {
	            empleadosArr.clear();
	            empleadosArr.addAll((ArrayList<Empleado>) o);

	        } else {
	            System.err.println("Formato de empleados.dat no válido.");
	        }

	    } catch (Exception e) {
	        System.err.println("Error leyendo empleados.dat: " + e.getMessage());
	    }
	}
	
	*/
	
	public static void leerEmpleadosDat() {
	    ArrayList<Empleado> listaEmpleadotest = new ArrayList<>();

	    ArrayList<Empleado> listaEmpleados = new ArrayList<>();
	    File archivo = new File("EMPLEADOS/empleados.dat");

	    if (archivo.exists() && archivo.isFile()) {

	        try {

	            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));

	            // Pasa el texto dat, a texto plano
	            listaEmpleados = (ArrayList<Empleado>) ois.readObject();

	            for (Empleado empleado : listaEmpleados) {
	                Empleado empleados = new Empleado(0, null, null, null);


	                empleados.setId(empleado.getId());
	                empleados.setNombre(empleado.getNombre());
	                empleados.setPasswd(empleado.getPasswd());
	                empleados.setCargo(empleado.getCargo());

	                listaEmpleadotest.add(empleados);
	                
	            }
	            
	            for (Empleado empleado : listaEmpleados) {
					System.out.println(empleado);
				}

	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }
	}

	
	public static void IniciarSesion() {

	    Empleado seleccionado = null;

	    System.out.println("Inicio de sesion");
	    System.out.println("----------------");

	    // ELIMINAR ESTAS LÍNEAS
	    // System.out.print("Id: ");
	    // String id = entrada.nextLine();
	    // System.out.print("Contraseña: ");
	    // String passwd = entrada.nextLine();

	    String id;  
	    String passwd;

	    do {
	        System.out.print("ID: ");
	        id = entrada.nextLine();

	        System.out.print("Contraseña: ");
	        passwd = entrada.nextLine();

	        // Buscar el empleado
	        for (int i = 0; i < empleadosArr.size(); i++) {
	            if (Integer.toString(empleadosArr.get(i).getId()).equals(id)) {
	                if (empleadosArr.get(i).getPasswd().equals(passwd)) {
	                    if (seleccionado == null) {
	                        seleccionado = empleadosArr.get(i);
	                    }
	                }
	            }
	        }

	        // Validar si se encontró
	        if (seleccionado == null) {
	            System.out.println("Usuario o contraseña incorrectos. Intente de nuevo.\n");
	        }

	    } while (seleccionado == null);

	    // Mostrar el menú según el cargo
	    if (seleccionado.getCargo().equals("Vendedor")) {
	        menuVendedor(buscarEmpleado(Integer.parseInt(id)));
	    } else if (seleccionado.getCargo().equals("Gestor")) {
	        menuGestor();
	    } else {
	        System.out.println("Error: empleado no posee cargo válido");
	    }
	}
	
	public static void mostrarEmpleados() {
		if (empleadosArr.isEmpty()) {
			System.out.println("No empleados en la lista.");
		} else {
			System.out.println("---------------EMPLEADOS--------------");
			
			for (Empleado e : empleadosArr) {
				System.out.println(e.toString());
			}		
		}
		
		if (empleadosBajaArr.isEmpty()) {
			System.out.println("No empleados dados de baja.");
		} else {
			
			System.out.println("--------EMPLEADOS DADOS DE BAJA-------");
			
			for (Empleado e : empleadosBajaArr) {
				System.out.println(e.toString());
			}	
		}
	}
	
	
    public static void altaEmpleados() {

        boolean error = false;

        String nombreNuevo;
        String contrasNuevo;
        String cargoNuevo;

        // Validar nombre
        do {
            System.out.println("Introduce el nombre: ");
            nombreNuevo = entrada.nextLine();

            if (nombreNuevo.matches(REGEX_NOMBRE)) {
                error = false;
            } else {
                System.out.println("Formato de nombre incorrecto, por favor repita");
                error = true;
            }
        } while (error);

        // Validar descripción
        do {
            System.out.println("Introduce la contrasegna: ");
            contrasNuevo = entrada.nextLine();

            if (contrasNuevo.matches(REGEX_CONTRASEGNA)) {
                error = false;
            } else {
                System.out.println("Formato de contrasegna incorrecto tiene que tener una Mayuscula, una minuscula, un caracter especial y superar los 8 caracteres, por favor repita");
                error = true;
            }
        } while (error);

        // Validar foto
        do {
            System.out.println("Introduce el cargo: ");
            cargoNuevo = entrada.nextLine();

            if (cargoNuevo.matches(REGEX_CARGO)) {
                error = false;
            } else {
                System.out.println("Formato de cargo incorrecto solo se permiten los cargos gesto y vendedor, por favor repita");
                error = true;
            }
        } while (error);

        // Calcular el nuevo código RANDOMIZALO
        int idNuevo = 1;
        if (!empleadosArr.isEmpty()) {
            // Buscar el código más alto
            for (Empleado e : empleadosArr) {
                if (e.getId() >= idNuevo) {
                	idNuevo = e.getId() + 1;
                }
            }
        }

        // Crear la nueva planta
        Empleado nuevoEmpleado = new Empleado(idNuevo, nombreNuevo, contrasNuevo, cargoNuevo);

        // Agregar a la lista
        empleadosArr.add(nuevoEmpleado);
       
        System.out.println("\n¡Empleado dado de alta correctamente!");
        System.out.println("ID asignado: " + idNuevo);
        
        menuGestor();
    }
    
	
	public static void mostrarPlantas() {
				
		if (plantasArr.isEmpty()) {
			System.out.println("No plantas en la lista.");
		} else {
			System.out.println("--------------PLANTAS--------------");
			
			for (Planta p : plantasArr) {
				System.out.println(p.toString());				
			}
			
		}
		
		if (plantasBajaArr.isEmpty()) {
			System.out.println("No plantas dadas de baja.");
		} else {
			
			System.out.println("--------PLANTAS DADAS DE BAJA-------");
			
			for (Planta p : plantasBajaArr) {
				System.out.println(p.toString());
			}	
		}
	}
	
	// APARTADO DE BUCLES DE BUSQUEDA PARA EL ARRAY -----------------------------------------------------//
	
    public static Planta buscarPlanta(int codigo) {
        for (Planta p : plantasArr) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }
    
    public static Planta buscarPlantabaja(int codigo) {
        for (Planta p : plantasBajaArr) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }
    
    public static Empleado buscarEmpleado(int codigo) {
        for (Empleado e : empleadosArr) {
            if (e.getId() == codigo) {
                return e;
            }
        }
        return null;
    }
    
    public static Empleado buscarEmpleadobaja(int codigo) {
        for (Empleado e : empleadosBajaArr) {
            if (e.getId() == codigo) {
                return e;
            }
        }
        return null;
    }
    
    //---------------------------------------------------------------------------------------------------//
	
    public static void guardarPlantasXML() {
        try {
            // Crear escritor para el archivo plantas.xml
            BufferedWriter bw = new BufferedWriter(new FileWriter("PLANTAS/plantas.xml"));
            
            // Escribir la cabecera XML con versión y codificación
            bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            bw.newLine();
            
            // Abrir etiqueta raíz <plantas>
            bw.write("<plantas>");
            bw.newLine();
            
            // Recorrer todas las plantas del ArrayList
            for (Planta p : plantasArr) {
                // Abrir etiqueta <planta>
                bw.write("  <planta>");
                bw.newLine();
                
                // Escribir código de la planta
                bw.write("    <codigo>" + p.getCodigo() + "</codigo>");
                bw.newLine();
                
                // Escribir nombre de la planta
                bw.write("    <nombre>" + p.getNombre() + "</nombre>");
                bw.newLine();
                
                // Escribir foto de la planta
                bw.write("    <foto>" + p.getFoto() + "</foto>");
                bw.newLine();
                
                // Escribir descripción de la planta
                bw.write("    <descripcion>" + p.getDescripcion() + "</descripcion>");
                bw.newLine();
                
                // Cerrar etiqueta </planta>
                bw.write("  </planta>");
                bw.newLine();
            }
            
            // Cerrar etiqueta raíz </plantas>
            bw.write("</plantas>");
            
            // Cerrar el archivo
            bw.close();
            
            System.out.println("Archivo plantas.xml actualizado correctamente.");
            
        } catch (IOException e) {
            // Manejar errores de escritura
            e.printStackTrace();
        }
    }
    
    public static void altaPlanta() {

        boolean error = false;

        String nombreNuevo;
        String descripNuevo;
        String fotoNuevo;
        String precioNuevo;
        String stockNuevo;

        // Validar nombre
        do {
            System.out.println("Introduce el nombre: ");
            nombreNuevo = entrada.nextLine();

            if (nombreNuevo.matches(REGEX_NOMBRE)) {
                error = false;
            } else {
                System.out.println("Formato de nombre incorrecto, por favor repita");
                error = true;
            }
        } while (error);

        // Validar descripción
        do {
            System.out.println("Introduce la descripción: ");
            descripNuevo = entrada.nextLine();

            if (descripNuevo.matches(REGEX_DECRIP)) {
                error = false;
            } else {
                System.out.println("Formato de descripción incorrecto, por favor repita");
                error = true;
            }
        } while (error);

        // Validar foto
        do {
            System.out.println("Introduce la foto: ");
            fotoNuevo = entrada.nextLine();

            if (fotoNuevo.matches(REGEX_FOTO)) {
                error = false;
            } else {
                System.out.println("Formato de foto incorrecto, por favor repita");
                error = true;
            }
        } while (error);

        // Validar precio
        do {
            System.out.println("Introduce el precio: ");
            precioNuevo = entrada.nextLine();

            if (precioNuevo.matches(REGEX_PRECIO)) {
                error = false;
            } else {
                System.out.println("Formato de precio incorrecto, por favor repita");
                error = true;
            }
        } while (error);

        // Validar stock
        do {
            System.out.println("Introduce el stock: ");
            stockNuevo = entrada.nextLine();

            if (stockNuevo.matches(REGEX_STOCK)) {
                error = false;
            } else {
                System.out.println("Formato de stock incorrecto, por favor repita");
                error = true;
            }
        } while (error);

        // Calcular el nuevo código (el siguiente disponible)
        int codigoNuevo = 1;
        if (!plantasArr.isEmpty()) {
            // Buscar el código más alto
            for (Planta p : plantasArr) {
                if (p.getCodigo() >= codigoNuevo) {
                    codigoNuevo = p.getCodigo() + 1;
                }
            }
        }

        // Crear la nueva planta
        Planta nuevaPlanta = new Planta(codigoNuevo, nombreNuevo, fotoNuevo, descripNuevo);

        // Agregar a la lista
        plantasArr.add(nuevaPlanta);
        
        guardarPlantasXML();     

        // Guardar precio y stock en el archivo .dat
        File ficheroDat = new File("PLANTAS/plantas.dat");
        nuevaPlanta.setPrecio(codigoNuevo, ficheroDat, Float.parseFloat(precioNuevo));
        nuevaPlanta.setStock(codigoNuevo, ficheroDat, Integer.parseInt(stockNuevo));

        System.out.println("\n¡Planta dada de alta correctamente!");
        System.out.println("Código asignado: " + codigoNuevo);
        
        menuGestor();
    }
    
	public static void modificarPlanta(int codigo) {
       
        File ficheroDat = new File("plantas.dat");
        
		int opcion;
        do {
        	
            System.out.println("\n=== MODIFICACION ===");
            System.out.println("1. Cambiar Nombre");
            System.out.println("2. Cambiar Descripcion");
            System.out.println("3. Cambiar Foto");
            System.out.println("4. Cambiar Precio");
            System.out.println("5. Cambiar stock"); 
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = entrada.nextInt();
            entrada.nextLine();

            boolean error = false;
            
            switch (opcion) {
	           case 1:
	               String nombreNuevo;
	
	               do {
	                    System.out.println("Introduce el nuevo nombre: ");
	                    nombreNuevo = entrada.next();
	
	                    if (nombreNuevo.matches(REGEX_NOMBRE)) {
	                        error = false;
	                    } else {
	                        System.out.println("Formato de nombre incorrecto, por favor repita");
	                        error = true;
	                    }
	
	                } while (error);  // Equivalente a while(error == true)
	
	                buscarPlanta(codigo).setNombre(nombreNuevo);
	                break;
                    
                case 2:
                	
                	String descripNuevo;
                	
                	do{
                	
                		System.out.println("Introduce la nueva descripcion: ");
                		descripNuevo = entrada.next();
                	
                		if(descripNuevo.matches(REGEX_DECRIP)) {
                			error = false;
                		}else {
                			System.out.println("Formato de descripcion Incorecto porfavor repita");
                			error = true;
                		}
                		
                	}while(error);
                	
                    buscarPlanta(codigo).setDescripcion(descripNuevo); 
                    break;
                    
                case 3:
                	
                	String fotoNuevo;
                	
                	do{
                	
                		System.out.println("Introduce la nueva foto: ");
                		fotoNuevo = entrada.next();
                	
                		if(fotoNuevo.matches(REGEX_FOTO)) {
                			error = false;
                		}else {
                			System.out.println("Formato de foto Incorecto porfavor repita");
                			error = true;
                		}
                		
                	}while(error);
                	
                    buscarPlanta(codigo).setFoto(fotoNuevo);               	
                    break;
                    
                case 4:
                	
                	String precioNuevo;
                	
                	do{
                	
                		System.out.println("Introduce el nuevo precio: ");
                		precioNuevo = entrada.next();
                	
                		if(precioNuevo.matches(REGEX_PRECIO)) {
                			error = false;
                		}else {
                			System.out.println("Formato de precio Incorecto porfavor repita");
                			error = true;
                		}
                		
                	}while(error);                               	
                    buscarPlanta(codigo).setPrecio(codigo,ficheroDat,Float.parseFloat(precioNuevo.replace(",", ".")));
                	
                    break;
                    
                case 5:
                	
                	String stockNuevo;
                	
                	do{
                	
                		System.out.println("Introduce el nuevo stock: ");
                		stockNuevo = entrada.next();
                	
                		if(stockNuevo.matches(REGEX_STOCK)) {
                			error = false;
                		}else {
                			System.out.println("Formato de stock Incorecto porfavor repita");
                			error = true;
                		}
                		
                	}while(error);
                	
                	if(Integer.parseInt(stockNuevo) == 0) {
                		//llamar baja planta
                	}else {       
                		buscarPlanta(codigo).setStock(codigo,ficheroDat,Integer.parseInt(stockNuevo));
                	}
                	
                    break;
                	           
                case 0:
                    System.out.println("Saliendo...");
                    menuGestor();
                    break;
                default:
                    System.out.println("ERROR: Opcion invalida");
            }
        } while (opcion != 0);

    }
	
	public static void bajaPlanta() {
	    System.out.println("\n=== BAJA DE PLANTA ===");
	    
	    boolean error = false;
	    String codigo;

	    do {
	        System.out.println("Introduce el codigo: ");
	        codigo = entrada.next();

	        if (codigo.matches(REGEX_ID)) {
	            error = false;
	        } else {
	            System.out.println("Formato de codigo incorrecto, por favor repita");
	            error = true;
	        }
	    } while (error);
	    
	    Planta plantaBaja = buscarPlanta(Integer.parseInt(codigo));
	    
	    if (plantaBaja == null) {
	        System.out.println("Planta no encontrada.");
	        return;
	    }
	    
	    File ficheroDat = new File("PLANTAS/plantas.dat");
	    float precioActual = plantaBaja.getPrecio(Integer.parseInt(codigo), ficheroDat);
	    int stockActual = plantaBaja.getStock(Integer.parseInt(codigo), ficheroDat);
	    
	    if (precioActual == 0 && stockActual == 0) {
	        System.out.println("Esta planta ya está dada de baja.");
	        return;
	    }
	    
	    // Crear copia para plantasBajaArr con código original
	    Planta plantaBajaCopia = new Planta(Integer.parseInt(codigo), plantaBaja.getNombre(), 
	                                         plantaBaja.getFoto(), plantaBaja.getDescripcion());
	    plantaBajaCopia.precio = precioActual;
	    plantaBajaCopia.stock = stockActual;
	    
	    // Agregar a plantasBajaArr y guardar XML de bajas
	    plantasBajaArr.add(plantaBajaCopia);
	    guardarPlantasBajaXML();
	    
	    // Guardar en plantasbaja.dat
	    File ficheroBaja = new File("PLANTAS/plantasbaja.dat");
	    
	    try {
	        RandomAccessFile raf = new RandomAccessFile(ficheroBaja, "rw");
	        raf.seek(raf.length());
	        
	        raf.writeInt(Integer.parseInt(codigo));
	        raf.writeFloat(precioActual);
	        raf.writeInt(stockActual);
	        
	        raf.close();
	        
	        System.out.println("✓ Planta guardada en plantasbaja.dat y plantasbaja.xml con código: " + codigo);
	        
	    } catch (IOException e) {
	        System.out.println("Error al guardar en archivo de bajas.");
	        e.printStackTrace();
	        return;
	    }
	    
	    // Eliminar del ArrayList y reorganizar
	    plantasArr.remove(plantaBaja);
	    
	    int nuevoCodigo = 1;
	    for (Planta p : plantasArr) {
	        p.setCodigo(nuevoCodigo);
	        nuevoCodigo++;
	    }
	    
	    guardarPlantasXML();
	    
	    try {
	        RandomAccessFile raf = new RandomAccessFile(ficheroDat, "rw");
	        raf.setLength(0);
	        
	        for (Planta p : plantasArr) {
	            raf.writeInt(p.getCodigo());
	            raf.writeFloat(p.precio);
	            raf.writeInt(p.stock);
	        }
	        
	        raf.close();
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    System.out.println("\n¡Planta dada de baja correctamente!");
	    
	    menuGestor();
	}
	
	public static void recuperarPlanta() {
	    System.out.println("\n=== RECUPERAR PLANTA ===");
	    
	    if (plantasBajaArr.isEmpty()) {
	        System.out.println("No hay plantas dadas de baja.");
	        return;
	    }
	    
	    boolean error = false;
	    String codigo;
	    
	    do {
	        System.out.print("\nCódigo de planta a recuperar: ");
	        codigo = entrada.next();
	        
	        if (codigo.matches(REGEX_ID)) {
	            error = false;
	        } else {
	            System.out.println("Formato incorrecto");
	            error = true;
	        }
	    } while (error);
	    
	    int codigoOriginal = Integer.parseInt(codigo);
	    Planta plantaRecuperar = buscarPlantabaja(codigoOriginal);
	    
	    if (plantaRecuperar == null) {
	        System.out.println("Planta no encontrada en bajas.");
	        return;
	    }
	    
	    // Calcular nuevo código
	    int nuevoCodigo = 1;
	    if (!plantasArr.isEmpty()) {
	        for (Planta p : plantasArr) {
	            if (p.getCodigo() >= nuevoCodigo) {
	                nuevoCodigo = p.getCodigo() + 1;
	            }
	        }
	    }
	    
	    System.out.println("\nCódigo original: " + codigoOriginal + " → Nuevo código: " + nuevoCodigo);
	    System.out.println("Precio recuperado: " + plantaRecuperar.precio);
	    System.out.println("Stock recuperado: " + plantaRecuperar.stock);
	    
	    System.out.print("\n¿Confirmar recuperación? (s/n): ");
	    entrada.nextLine();
	    String confirmar = entrada.nextLine();
	    
	    if (!confirmar.equalsIgnoreCase("s")) {
	        System.out.println("Recuperación cancelada.");
	        return;
	    }
	    
	    // Crear planta con nuevo código
	    Planta plantaNueva = new Planta(nuevoCodigo, plantaRecuperar.getNombre(), 
	                                     plantaRecuperar.getFoto(), plantaRecuperar.getDescripcion());
	    plantaNueva.precio = plantaRecuperar.precio;
	    plantaNueva.stock = plantaRecuperar.stock;
	    
	    // Agregar a plantasArr
	    plantasArr.add(plantaNueva);
	    guardarPlantasXML();
	    
	    // Guardar precio y stock en plantas.dat
	    File ficheroDat = new File("PLANTAS/plantas.dat");
	    plantaNueva.setPrecio(nuevoCodigo, ficheroDat, plantaRecuperar.precio);
	    plantaNueva.setStock(nuevoCodigo, ficheroDat, plantaRecuperar.stock);
	    
	    // Eliminar de plantasBajaArr
	    plantasBajaArr.remove(plantaRecuperar);
	    guardarPlantasBajaXML();
	    
	    // Eliminar de plantasbaja.dat
	    File ficheroBaja = new File("PLANTAS/plantasbaja.dat");
	    try {
	        RandomAccessFile raf = new RandomAccessFile(ficheroBaja, "rw");
	        raf.setLength(0);
	        
	        for (Planta p : plantasBajaArr) {
	            raf.writeInt(p.getCodigo());
	            raf.writeFloat(p.precio);
	            raf.writeInt(p.stock);
	        }
	        
	        raf.close();
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    System.out.println("\n¡Planta recuperada correctamente!");
	    
	    menuGestor();
	}
	
	public static void guardarPlantasBajaXML() {
	    try {
	        BufferedWriter bw = new BufferedWriter(new FileWriter("PLANTAS/plantasbaja.xml"));
	        
	        bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	        bw.newLine();
	        bw.write("<plantasBaja>");
	        bw.newLine();
	        
	        for (Planta p : plantasBajaArr) {
	            bw.write("  <planta>");
	            bw.newLine();
	            bw.write("    <codigo>" + p.getCodigo() + "</codigo>");
	            bw.newLine();
	            bw.write("    <nombre>" + p.getNombre() + "</nombre>");
	            bw.newLine();
	            bw.write("    <foto>" + p.getFoto() + "</foto>");
	            bw.newLine();
	            bw.write("    <descripcion>" + p.getDescripcion() + "</descripcion>");
	            bw.newLine();
	            bw.write("    <precio>" + p.precio + "</precio>");
	            bw.newLine();
	            bw.write("    <stock>" + p.stock + "</stock>");
	            bw.newLine();
	            bw.write("  </planta>");
	            bw.newLine();
	        }
	        
	        bw.write("</plantasBaja>");
	        bw.close();
	        
	        System.out.println("Archivo plantasbaja.xml actualizado correctamente.");
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public static void cargarPlantasBajaXML() {
	    try {
	        File ficheroXML = new File("PLANTAS/plantasbaja.xml");
	        if (!ficheroXML.exists()) {
	            return;
	        }

	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docB = dbf.newDocumentBuilder();
	        Document doc = docB.parse(ficheroXML);
	        doc.getDocumentElement().normalize();
	        
	        NodeList Lista = doc.getElementsByTagName("planta");
	        
	        for(int i = 0; i < Lista.getLength(); i++) {
	            Node nodo = Lista.item(i);
	            
	            if(nodo.getNodeType() == Node.ELEMENT_NODE) {
	                Element plantas = (Element) nodo;
	                
	                int codigo = Integer.parseInt(plantas.getElementsByTagName("codigo").item(0).getTextContent());
	                String nombre = plantas.getElementsByTagName("nombre").item(0).getTextContent();
	                String foto = plantas.getElementsByTagName("foto").item(0).getTextContent();
	                String descripcion = plantas.getElementsByTagName("descripcion").item(0).getTextContent();
	                float precio = Float.parseFloat(plantas.getElementsByTagName("precio").item(0).getTextContent());
	                int stock = Integer.parseInt(plantas.getElementsByTagName("stock").item(0).getTextContent());
	                
	                Planta p = new Planta(codigo, nombre, foto, descripcion);
	                p.precio = precio;
	                p.stock = stock;
	                
	                plantasBajaArr.add(p);
	            }
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static void bajaEmpleado() {
	    System.out.println("\n=== BAJA DE EMPLEADO ===");

	    String id;
	    boolean err;

	    do {
	        System.out.println("Introduce el codigo del empleado que desea dar de baja: ");
	        id = entrada.next();

	        err = !id.matches(REGEX_ID);

	    } while (err);

	    // Buscar el empleado
	    Empleado empBaja = buscarEmpleado(Integer.parseInt(id));

	    if (empBaja == null) {
	        System.out.println("Empleado no encontrado.");
	        return;
	    }

	    File ficheroBaja = new File("EMPLEADOS/BAJA/empleadosBaja.dat");
	    File ficheroActivo = new File("EMPLEADOS/empleados.dat");

	    try {
	        //Guardar en empleadosBaja.dat
	        try (RandomAccessFile rafBaja = new RandomAccessFile(ficheroBaja, "rw")) {
	            rafBaja.seek(rafBaja.length());

	            rafBaja.writeInt(empBaja.getId());
	            rafBaja.writeUTF(empBaja.getNombre());
	            rafBaja.writeUTF(empBaja.getPasswd());
	            rafBaja.writeUTF(empBaja.getCargo());
	        }

	        System.out.println("Empleado guardado en empleadosBaja.dat");

	        //Leer todos los empleados excepto el dado de baja
	        ArrayList<Empleado> empleadosTemp = new ArrayList<>();

	        try (RandomAccessFile rafLeer = new RandomAccessFile(ficheroActivo, "r")) {

	            while (rafLeer.getFilePointer() < rafLeer.length()) {

	                int empId = rafLeer.readInt();
	                String nombre = rafLeer.readUTF();
	                String passwd = rafLeer.readUTF();
	                String cargo = rafLeer.readUTF();

	                if (empId != Integer.parseInt(id)) {
	                    empleadosTemp.add(new Empleado(empId, nombre, passwd, cargo));
	                }
	            }
	        }

	        //Reescribir archivo sin el empleado de baja
	        try (RandomAccessFile rafEscribir = new RandomAccessFile(ficheroActivo, "rw")) {

	            rafEscribir.setLength(0);

	            for (Empleado emp : empleadosTemp) {
	                rafEscribir.writeInt(emp.getId());
	                rafEscribir.writeUTF(emp.getNombre());
	                rafEscribir.writeUTF(emp.getPasswd());
	                rafEscribir.writeUTF(emp.getCargo());
	            }
	        }

	        //Actualizar arrays en memoria
	        empleadosArr.remove(empBaja);
	        empleadosBajaArr.add(empBaja);

	        System.out.println("\nEmpleado eliminado correctamente.");

	    } catch (Exception e) {
	        System.out.println("Error al dar de baja el empleado.");
	        e.printStackTrace();
	    }

	    menuGestor();
	}

	
	public static void recuperarEmpleado() {
	    System.out.println("\n=== RECUPERAR EMPLEADO ===");
	    
	    File ficheroBaja = new File("EMPLEADOS/BAJA/empleadosBaja.dat");
	    File ficheroActivo = new File("EMPLEADOS/empleados.dat");
	    
	    if (!ficheroBaja.exists()) {
	        System.out.println("No hay empleados dados de baja.");
	        return;
	    }
	    
	    try {

	        
	        //Pedir ID del empleado a recuperar
		    
		    String id;
		    boolean err = false;
		    
	    	do {
	        	System.out.println("Introduce el codigo del empleado que desea recuperar: ");
	        	id = entrada.next();
	        	
	        	if(id.matches(REGEX_ID)) {
	        		err = false;
	        	}else {
	        		err = true;
	        	}
	    	}while(err);
	        
	        //Buscar el empleado en la lista de bajas

	        Empleado empRecuperar = buscarEmpleadobaja(Integer.parseInt(id));

	        
	        if (empRecuperar == null) {
	            System.out.println("Empleado no encontrado en bajas.");
	            return;
	        }
	        
	        //Guardar en empleados.dat (activos)
	        RandomAccessFile rafActivo = new RandomAccessFile(ficheroActivo, "rw");
	        rafActivo.seek(rafActivo.length()); // Ir al final
	        
	        rafActivo.writeInt(empRecuperar.getId());
	        rafActivo.writeUTF(empRecuperar.getNombre());
	        rafActivo.writeUTF(empRecuperar.getPasswd());
	        rafActivo.writeUTF(empRecuperar.getCargo());
	        
	        rafActivo.close();
	        System.out.println("Empleado agregado a empleados.dat");
	        
	        //Eliminar de empleadosBaja.dat
	        empleadosBajaArr.remove(empRecuperar);
	        
	        RandomAccessFile rafBaja = new RandomAccessFile(ficheroBaja, "rw");
	        rafBaja.setLength(0); // Limpiar archivo
	        
	        for (Empleado emp : empleadosBajaArr) {
	            rafBaja.writeInt(emp.getId());
	            rafBaja.writeUTF(emp.getNombre());
	            rafBaja.writeUTF(emp.getPasswd());
	            rafBaja.writeUTF(emp.getCargo());
	        }
	        rafBaja.close();
	        
	        // 6. Agregar al ArrayList de activos
	        empleadosArr.add(empRecuperar);
	        
	        System.out.println("\n¡Empleado recuperado correctamente!");
	        System.out.println("Ahora está activo en empleados.dat");
	        
	    } catch (Exception e) {
	        System.out.println("Error al recuperar el empleado.");
	        e.printStackTrace();
	    }
	    
	    
	    menuGestor();
	}
	
	public static void estadisticas() {
	    System.out.println("\n=== ESTADÍSTICAS ===");
	    
	    File carpetaTickets = new File("TICKETS");
	    File[] tickets = carpetaTickets.listFiles((dir, name) -> name.endsWith(".txt"));
	    
	    if (tickets == null || tickets.length == 0) {
	        System.out.println("No hay tickets para procesar.");
	        return;
	    }
	    
	    float totalRecaudado = 0;
	    int[] contadorPlantas = new int[1000];
	    
	    for (File ticket : tickets) {
	        try {
	            BufferedReader br = new BufferedReader(new FileReader(ticket));
	            String linea;
	            boolean leyendoItems = false;
	            
	            while ((linea = br.readLine()) != null) {
	                if (linea.startsWith("CodigoProducto")) {
	                    leyendoItems = true;
	                    continue;
	                }
	                
	                if (leyendoItems && linea.startsWith("——")) {
	                    leyendoItems = false;
	                    continue;
	                }
	                
	                if (leyendoItems) {
	                    String[] partes = linea.trim().split("\\s+");
	                    if (partes.length == 3) {
	                        int codigo = Integer.parseInt(partes[0]);
	                        int unidades = Integer.parseInt(partes[1]);
	                        float precio = Float.parseFloat(partes[2]);
	                        
	                        contadorPlantas[codigo] += unidades;
	                        totalRecaudado += unidades * precio;
	                    }
	                }
	                
	                if (linea.startsWith("Total:")) {
	                    // Ya sumamos arriba
	                }
	            }
	            
	            br.close();
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    System.out.println("\n--- RESULTADOS ---");
	    System.out.printf("Total recaudado: %.2f €\n", totalRecaudado);
	    System.out.println("Total de tickets: " + tickets.length);
	    
	    // Buscar planta más vendida
	    int codigoMasVendido = 0;
	    int maxVentas = 0;
	    
	    for (int i = 0; i < contadorPlantas.length; i++) {
	        if (contadorPlantas[i] > maxVentas) {
	            maxVentas = contadorPlantas[i];
	            codigoMasVendido = i;
	        }
	    }
	    
	    if (maxVentas > 0) {
	        Planta p = buscarPlanta(codigoMasVendido);
	        if (p != null) {
	            System.out.println("\nPlanta más vendida:");
	            System.out.println("  - Nombre: " + p.getNombre());
	            System.out.println("  - Código: " + codigoMasVendido);
	            System.out.println("  - Unidades vendidas: " + maxVentas);
	        } else {
	            System.out.println("\nPlanta más vendida (código " + codigoMasVendido + 
	                             "): " + maxVentas + " unidades");
	            System.out.println("  (Planta ya no está en catálogo activo)");
	        }
	    }
	    
	    System.out.println("\n" + "=".repeat(60));
	}
	
	public static void menuGestor() {
		
		/*
		  
		Gestores:
		Alta/baja/modificación de plantas (actualiza fichero XML).
		Alta/baja/recontratación de empleados (bajas en carpeta BAJA).
		Estadísticas: total recaudado y planta más vendida.

		 */
		int opcion;

		do {
        	
		System.out.println( "\n--------------------------------------------------------------\n"
					+ "\t  \t  MENU GESTOR \n"
					+ "--------------------------------------------------------------\n");

            System.out.println("1. Alta planta");
            System.out.println("2. Baja planta");
            System.out.println("3. Modificar planta");
            System.out.println("4. Recuperar planta");
            System.out.println("5. Alta empleado");
            System.out.println("6. Baja empleado");
            System.out.println("7. Recuperar empleado");
            System.out.println("8. Listar empleados");
            System.out.println("9. Listar plantas");
            System.out.println("10. Estadísticas");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = entrada.nextInt();
            entrada.nextLine();

    		boolean err = false;;
            
            switch (opcion) {
                case 1:                	
                	altaPlanta();
                    break;
                case 2:
                	bajaPlanta();
                    break;
                case 3:
                	String codPlant;
                	
                	do {
	                	System.out.println("Introduce el codigo de la planta que desea modificar: ");
	                	codPlant = entrada.next();
	                	
	                	if(codPlant.matches(REGEX_ID)) {
	                		err = false;
	                	}else {
	                		err = true;
	                	}
                	}while(err);
                	
                	modificarPlanta(Integer.parseInt(codPlant));
                	
                    break;
                case 4:
                	recuperarPlanta();
                    break;
                case 5:
                	altaEmpleados();
                    break;
                case 6:
                	bajaEmpleado();
                    break;
                case 7:
                	recuperarEmpleado();
                    break;
                case 8:
                	mostrarEmpleados();
                    break;
                case 9:
                	mostrarPlantas();
                    break;
                case 10:
                	estadisticas();             	
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("ERROR: Opcion invalida");
            }
        } while (opcion != 0);
    }
	
	
	public static void generarVenta(Empleado empleado) {
	    System.out.println("\n=== GENERAR VENTA ===");
	    
	    cesta.clear();
	    String continuar = null;
	    
	    do {
	        String codigo;
	        String cantidad;
	        boolean err = false;
	        
	        do {
	            System.out.print("Código de planta: ");
	            codigo = entrada.next();
	            
	            if(codigo.matches(REGEX_ID)) {
	                err = false;
	            } else {
	                System.out.println("Formato incorrecto");
	                err = true;
	            }
	        } while(err);
	        
	        do {
	            System.out.print("Cantidad: ");
	            cantidad = entrada.next();
	            
	            if(cantidad.matches(REGEX_STOCK)) {
	                err = false;
	            } else {
	                System.out.println("Formato incorrecto");
	                err = true;
	            }
	        } while(err);
	        
	        Planta p = buscarPlanta(Integer.parseInt(codigo));
	        
	        if (p == null) {
	            System.out.println("Planta no encontrada.");
	            continue;
	        }
	        
	        File ficheroDat = new File("PLANTAS/plantas.dat");
	        int stockActual = p.getStock(Integer.parseInt(codigo), ficheroDat);
	        float precioActual = p.getPrecio(Integer.parseInt(codigo), ficheroDat);
	        
	        if (stockActual < Integer.parseInt(cantidad)) {
	            System.out.println("Stock insuficiente. Disponible: " + stockActual);
	            continue;
	        }
	        
	        if (precioActual == 0) {
	            System.out.println("Planta no disponible para venta.");
	            continue;
	        }
	        
	        cesta.add(new ItemVenta(Integer.parseInt(codigo), Integer.parseInt(cantidad), precioActual));
	        System.out.println("Agregado a la cesta.");
	        
	        System.out.print("¿Agregar más productos? (s/n): ");
	        entrada.nextLine();
	        continuar = entrada.nextLine();
	        
	    } while (continuar.equalsIgnoreCase("s"));
	    
	    if (cesta.isEmpty()) {
	        System.out.println("No hay productos en la cesta.");
	        return;
	    }
	    
	    mostrarResumen();
	    
	    System.out.print("\n¿Confirmar venta? (s/n): ");
	    String confirmar = entrada.nextLine();
	    
	    if (confirmar.equalsIgnoreCase("s")) {
	        int numeroTicket = generarTicket(empleado);
	        
	        // Actualizar stock
	        File ficheroDat = new File("PLANTAS/plantas.dat");
	        for (ItemVenta item : cesta) {
	            Planta p = buscarPlanta(item.getCodigoProducto());
	            int stockActual = p.getStock(item.getCodigoProducto(), ficheroDat);
	            int nuevoStock = stockActual - item.getUnidades();
	            
	            if (nuevoStock <= 0) {
	                System.out.println("\nStock agotado para: " + p.getNombre());
	                System.out.println("Dando de baja automáticamente...");
	                bajaPlanta();
	            } else {
	                p.setStock(item.getCodigoProducto(), ficheroDat, nuevoStock);
	            }
	        }
	        
	        System.out.println("\nVenta realizada. Ticket generado: " + numeroTicket);
	        cesta.clear();
	    } else {
	        System.out.println("Venta cancelada.");
	        cesta.clear();
	    }
	}

	private static void mostrarResumen() {
	    System.out.println("\n--- RESUMEN DE COMPRA ---");
	    float total = 0;
	    
	    for (ItemVenta item : cesta) {
	        Planta p = buscarPlanta(item.getCodigoProducto());
	        System.out.printf("%s x%d - %.2f€\n", 
	                        p.getNombre(), item.getUnidades(), item.getSubtotal());
	        total += item.getSubtotal();
	    }
	    
	    System.out.printf("TOTAL: %.2f€\n", total);
	}

	public static int generarTicket(Empleado empleado) {
	    int numeroTicket = obtenerSiguienteNumeroTicket();
	    
	    try {
	        BufferedWriter bw = new BufferedWriter(new FileWriter("TICKETS/" + numeroTicket + ".txt"));
	        
	        bw.write("Número Ticket: " + numeroTicket);
	        bw.newLine();
	        bw.write("——————————————//———————————------------------------");
	        bw.newLine();
	        bw.write("Empleado que ha atendido: " + empleado.getId());
	        bw.newLine();
	        bw.write("Nombre del empleado: " + empleado.getNombre());
	        bw.newLine();
	        bw.write("Fecha: " + java.time.LocalDateTime.now());
	        bw.newLine();
	        bw.write("CodigoProducto Cantidad PrecioUnitario");
	        bw.newLine();
	        
	        float total = 0;
	        for (ItemVenta item : cesta) {
	            bw.write(item.getCodigoProducto() + " " + item.getUnidades() + " " + item.getPrecioUnitario());
	            bw.newLine();
	            total += item.getSubtotal();
	        }
	        
	        bw.write("——————————————//———————————------------------------");
	        bw.newLine();
	        bw.write(String.format("Total: %.2f €", total));
	        bw.newLine();
	        
	        bw.close();
	        
	        guardarNumeroTicket(numeroTicket + 1);
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    return numeroTicket;
	}

	public static int obtenerSiguienteNumeroTicket() {
	    int numero = 1;
	    try {
	        File fichero = new File("TICKETS/contador.dat");
	        if (fichero.exists()) {
	            DataInputStream dis = new DataInputStream(new FileInputStream(fichero));
	            numero = dis.readInt();
	            dis.close();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return numero;
	}

	public static void guardarNumeroTicket(int numero) {
	    try {
	        DataOutputStream dos = new DataOutputStream(new FileOutputStream("TICKETS/contador.dat"));
	        dos.writeInt(numero);
	        dos.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void generarDevolucion() {
	    System.out.println("\n=== GENERAR DEVOLUCIÓN ===");
	    
	    boolean err = false;
	    String numeroStr;
	    
	    do {
	        System.out.print("Número de ticket: ");
	        numeroStr = entrada.next();
	        entrada.nextLine();
	        
	        if(numeroStr.matches(REGEX_ID)) {
	            err = false;
	        } else {
	            System.out.println("Formato incorrecto");
	            err = true;
	        }
	    } while(err);
	    
	    int numero = Integer.parseInt(numeroStr);
	    File archivoTicket = new File("TICKETS/" + numero + ".txt");
	    
	    if (!archivoTicket.exists()) {
	        System.out.println("Ticket no encontrado.");
	        return;
	    }
	    
	    // Leer items del ticket
	    ArrayList<ItemVenta> items = new ArrayList<>();
	    
	    try {
	        BufferedReader br = new BufferedReader(new FileReader(archivoTicket));
	        String linea;
	        boolean leyendoItems = false;
	        
	        while ((linea = br.readLine()) != null) {
	            if (linea.startsWith("CodigoProducto")) {
	                leyendoItems = true;
	                continue;
	            }
	            
	            if (leyendoItems && linea.startsWith("——")) {
	                break;
	            }
	            
	            if (leyendoItems) {
	                String[] partes = linea.trim().split("\\s+");
	                if (partes.length == 3) {
	                    int codigo = Integer.parseInt(partes[0]);
	                    int unidades = Integer.parseInt(partes[1]);
	                    float precio = Float.parseFloat(partes[2]);
	                    items.add(new ItemVenta(codigo, unidades, precio));
	                }
	            }
	        }
	        br.close();
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	        return;
	    }
	    
	    if (items.isEmpty()) {
	        System.out.println("No se pudieron leer los items del ticket.");
	        return;
	    }
	    
	    // Devolver stock
	    File ficheroDat = new File("PLANTAS/plantas.dat");
	    for (ItemVenta item : items) {
	        Planta p = buscarPlanta(item.getCodigoProducto());
	        if (p != null) {
	            int stockActual = p.getStock(item.getCodigoProducto(), ficheroDat);
	            int nuevoStock = stockActual + item.getUnidades();
	            p.setStock(item.getCodigoProducto(), ficheroDat, nuevoStock);
	        }
	    }
	    
	    // Mover ticket a DEVOLUCIONES
	    try {
	        BufferedReader br = new BufferedReader(new FileReader(archivoTicket));
	        BufferedWriter bw = new BufferedWriter(new FileWriter("DEVOLUCIONES/" + numero + ".txt"));
	        
	        String linea;
	        while ((linea = br.readLine()) != null) {
	            bw.write(linea);
	            bw.newLine();
	        }
	        
	        bw.write("\n*** DEVUELTO ***");
	        bw.newLine();
	        
	        br.close();
	        bw.close();
	        
	        archivoTicket.delete();
	        
	        System.out.println("\n✓ Devolución procesada correctamente.");
	        System.out.println("✓ Stock restaurado.");
	        System.out.println("✓ Ticket movido a DEVOLUCIONES.");
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public static void buscarTicket() {
	    System.out.println("\n=== BUSCAR TICKET ===");
	    
	    boolean err = false;
	    String numeroStr;
	    
	    do {
	        System.out.print("Número de ticket: ");
	        numeroStr = entrada.next();
	        entrada.nextLine();
	        
	        if(numeroStr.matches(REGEX_ID)) {
	            err = false;
	        } else {
	            System.out.println("Formato incorrecto");
	            err = true;
	        }
	    } while(err);
	    
	    int numero = Integer.parseInt(numeroStr);
	    File archivoTicket = new File("TICKETS/" + numero + ".txt");
	    
	    if (!archivoTicket.exists()) {
	        archivoTicket = new File("DEVOLUCIONES/" + numero + ".txt");
	        if (!archivoTicket.exists()) {
	            System.out.println("Ticket no encontrado.");
	            return;
	        }
	    }
	    
	    try {
	        BufferedReader br = new BufferedReader(new FileReader(archivoTicket));
	        String linea;
	        
	        System.out.println("\n" + "=".repeat(60));
	        while ((linea = br.readLine()) != null) {
	            System.out.println(linea);
	        }
	        System.out.println("=".repeat(60));
	        
	        br.close();
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public static void menuVendedor(Empleado empleado) {
		
		/*
		  
		Vendedores:
		 
		Ver catálogo de plantas con precio y stock.
		Realizar ventas (desde catálogo o por código): genera ticket con datos del empleado, fecha, productos y total; reduce stock y guarda en TICKETS.
		Hacer devoluciones: ticket negativo, aumenta stock, se guarda en DEVOLUCIONES.
		Plantas sin stock → se dan de baja automáticamente y no se pueden vender.

		 */
		
        int opcion;
        do {
       	
        	System.out.println( "\n--------------------------------------------------------------\n"
        						+ "\t  \t MENU VENDEDOR \n"
        						+ "--------------------------------------------------------------\n");
		
        	System.out.println("1. Visualizar catálogo de plantas");
        	System.out.println("2. Generar venta");
            System.out.println("3. Generar devolución");
            System.out.println("4. Buscar ticket");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1:
                	mostrarPlantas();        
                    break;
                case 2:
                    generarVenta(empleado);
                    break;
                case 3:
                    generarDevolucion();
                    break;
                case 4:
                    buscarTicket();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("ERROR: Opcion invalida");
            }
        } while (opcion != 0);
    }

	public static void main(String[] args) {
		
		//Cargamos los datos de plantas en el array de plantas
		LecturaDatosPlantas();
		
		//Creamos los datos predefinidos de empleados en el archivo .dat
		leerEmpleadosDat();
		//Cargamos los datos de empleados en el array de empleados
		
		cargarPlantasBajaXML();

		mostrarEmpleados();
		
		IniciarSesion();

		
	}

}
