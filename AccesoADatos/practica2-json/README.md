practica2-json
Esta práctica se ha llevado a cabo a partir de gestionGasolinera.
Se han implementado dos clases más:
AlmacenamientoJSON: es una clase prácticamente idéntica a la de la anterior práctica, solamente que tiene 2 métodos que 
sirven para poder crear un pago y un cliente en ese formato, después los métodos de leer y guardar vienen a ser los 
mismos pero con más complejidad a la hora de la creación de los ficheros ya que se requieren muchos "\", "\n", "[]" y "{}" 
tanto para los pagos como para los clientes y además de todo esto he implementado un método landa que me permite agilizar
mucho el código.
Métodos: ClienteJson(), PagoJson(), leerClientes(), leerPagos(), guardarClientes(), guardarPagos().
MigraCSVtoJson: consiste en pasar los clientes y pagos de los ficheros CSV a Json. He intentado hacer en cada método de migrar
2 try´s para leer y escribir en el mismo método y no hacer mucho código. Lógicamente otra vez con muchos "\", "\n", "[]" y 
"{}" para que el archivo fuera legible, para mí lo más difícil sin duda porque lo demás con paciencia se saca pero eso es a base de fallar en la ejecución. Y en el método ejecutar() solo he puesto algunos prints orientativos y he invocado a los métodos que he comentado previamente (el único método fácil).
Métodos: migrarClientes(), migrarPagos(), ejecutar().
