practica2-json
Esta práctica se ha llevado a cabo a partir de gestionGasolinera.
Se han implementado dos clases más:
AlmacenamientoJSON: es una clase prácticamente idéntica a la de la anterior práctica, solamente que tiene 2 métodos que 
sirven para poder crear un pago y un cliente en ese formato, después los métodos de leer y guardar vienen a ser los 
mismos pero con más complejidad a la hora de la creación de los ficheros ya que se requieren muchos "\", "\n", "[]" y "{}" 
tanto para los pagos como para los clientes y además de todo esto he implementado un método landa que me permite agilizar
mucho el código.
Métodos: ClienteJson(), PagoJson(), leerClientes(), leerPagos(), guardarClientes(), guardarPagos().
MigraCSVtoJson: consiste en pasar los clientes y pagos de los ficheros CSV a Json. He creado objetos de las anteriores
clases de almacenamiento para reutilizar los métodos y ahorrar código.
Métodos: migrarClientes(), migrarPagos(), ejecutar().
