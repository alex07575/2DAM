fun main(args: Array<String>) {
    //ej01();
   // ej02();
   // ej03();
    //ej04();
    //ej05();
    //0ej06();
}

fun ej01(){
    var a = 1;
    var b = 2;
    var suma = a+b;
    var resta = a-b;
    var multip = a*b;
    var div = b/a;
    var modulo = a % b;
    println(suma)
    println(resta)
    println(multip)
    println(div)
    println(modulo)
}
fun ej02(){
    var nombre : String = "Alex";
    println("Bienvenido: " + nombre);
}
fun ej03(){
    println("Dime tu nombre: ")
    var respuesta = readln();
}
fun ej04(){
    println("Dime 2 numeros: ")
    var n1 = readln().toInt();
    var n2 = readln().toInt();
    if (n1 > n2){
        println("El primer numero es mayor que el segundo. ")
    } else if (n1 < n2) {
        println("El segundo numero es mayor que el primero. ")
    } else {
        println("Son iguales. ")
    }
}
fun ej05(){
    println("Dime un numero: ")
    var respuesta = readln().toInt();
    if (respuesta % 2 == 0){
        println("Es divisible entre 2. ")
    } else {
        println("No es divisible entre 2. ")
    }
}
fun ej06(){
    var respuesta: Char
    do {
        println("¿Cuál es la capital de Colombia?")
        println("a. La Paz")
        println("b. Buenos Aires")
        println("c. La Habana")
        println("d. Bogotá")
        print("Respuesta: ")
        respuesta = readLine()!!.lowercase()[0]
        if (respuesta == 'd') {
            println("¡Felicitaciones!")
        } else {
            println("Respuesta incorrecta.")
        }
    } while (respuesta != 'd')
}
fun ej07(){
    for (int i = 0 || i <= 100){

    }
}