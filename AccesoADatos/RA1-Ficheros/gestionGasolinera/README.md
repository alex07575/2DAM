gestionGasolinera:
El proyecto se divide en 2 partes fundamentales, el src con todas las clases e interfaces: 
Principal, Cliente, Pago, AlmacenamientoCSV, Gasolinera y la interfaz GestorArchivos y la otra parte
es la carpeta datos con los ficheros que se crean gracias a la interfaz con los métodos correspondientes.
Decisiones de diseño:
-Se ha implementado una sola interfaz (GestorArchivo) para que lea y guarde tanto los clientes como los pagos.

-Principal: se ha hecho un menú a medida para el usuario para que introduzca los datos requeridos por pantalla.
 Métodos: altaCliente(),dosDecimales() ,buscarCliente(), procesarPago().

-Cliente y Pago: dos clases con sus atributos, getters, toString y con la interfaz Comparable implementada para 
 no tener que hacer comparadores de otras clases.

-Gasolinera: se lleva el trabajo de campo, la base del funcionamiento del programa con métodos que hacen 
 los pequeños detalles como cargarClientes(), siguienteIdCliente(), matriculaExiste(), buscarCliente() tanto por
 nombre como por ID, hayClientes(), mostrarClientes(), registrarPago(), listarPago() y convertirFecha().

-AlmacenamientoCSV: la clase que lleva las riendas sobre crear o no el fichero que se proponga y el tipo del fichero
 implementando además la interfaz GestorArchivos para que si en un futuro se desea cambiar el tipo de ficheros 
 la molestia sea mínima.

-GestorArchivos <<interface>>: lleva los métodos más importantes para pasar los datos a los ficheros.
 Métodos: leerClientes(), leerPagos(), guardarCliente() y guardarPago().
