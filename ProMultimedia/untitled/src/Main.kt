fun main() {
    //ej01();
    //ej02();
    //ej03();
    //ej04();
    //ej05();
    //0ej06();
    //ej07();
    //ej08();
    //ej09();
    //ej10();
    //ej11();
    //ej12();
    //ej13();
    //ej14();
    //ej15();
    //ej16();
    //ej17();
    //ej18();

}

fun ej01() {
    var a = 1;
    var b = 2;
    var suma = a + b;
    var resta = a - b;
    var multip = a * b;
    var div = b / a;
    var modulo = a % b;
    println(suma)
    println(resta)
    println(multip)
    println(div)
    println(modulo)
}

fun ej02() {
    var nombre: String = "Alex";
    println("Bienvenido: " + nombre);
}

fun ej03() {
    println("Dime tu nombre: ")
    var respuesta = readln();
}

fun ej04() {
    println("Dime 2 numeros: ")
    var n1 = readln().toInt();
    var n2 = readln().toInt();
    if (n1 > n2) {
        println("El primer numero es mayor que el segundo. ")
    } else if (n1 < n2) {
        println("El segundo numero es mayor que el primero. ")
    } else {
        println("Son iguales. ")
    }
}

fun ej05() {
    println("Dime un numero: ")
    var respuesta = readln().toInt();
    if (respuesta % 2 == 0) {
        println("Es divisible entre 2. ")
    } else {
        println("No es divisible entre 2. ")
    }
}

fun ej06() {
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

fun ej07() {
    for (num in 0..100) {
        print("$num ");
    }
}

fun ej08() {
    var num: Int = 0;
    do {
        num++;
        print("$num ");
    } while (num < 100)
}

fun ej09() {
    var num: Int = 0;
    do {
        num++;
        if (num % 2 == 0 && num % 3 == 0) {
            print("\n$num");
        }
    } while (num < 100)
}

fun ej10() {
    var num: Int
    do {
        print("Introduce un número: ")
        num = readln().toInt()

    } while (num < 0)
    println("El número es: $num");
}

    fun ej11() {
        val contraseña = "1234"
        var intentos = 3
        var acertado = false

        while (intentos > 0 && !acertado) {
            print("Introduce la contraseña: ")
            val respuesta = readln()
            if (respuesta == contraseña) {
                println("Enhorabuena")
                acertado = true
            } else {
                intentos--
                println("Has fallado. Te quedan $intentos intentos")
            }
        }
    }

    fun ej12() {
        print("El mes tiene 30 días");
        val mes = readln().toInt();

        when (mes) {
            2 -> println("El mes tiene 28/29 días")
            4, 6, 9, 11 -> println("El mes tiene 30 días")
            1, 3, 5, 7, 8, 10, 12 -> println("El mes tiene 31 días")
            else -> println("Mes incorrecto")
        }
    }

    fun ej13() {
        print("Introduce un día: ")
        val dia = readln().lowercase()
        when (dia) {
            "lunes", "martes", "miércoles", "jueves", "viernes" ->
                println("Es un día laborable")

            "sábado", "domingo" ->
                println("Es fin de semana")

            else ->
                println("Día incorrecto")
        }
    }

    fun ej14() {
        print("¿Cuántas ventas has realizado? ")
        val cantidad = readln().toInt()
        var suma = 0.0
        for (i in 1..cantidad) {
            print("Introduce la venta $i: ")
            val venta = readln().toDouble()
            suma += venta
        }
        println("Las ventas totales son $suma")
    }

fun ej15() {
    var suma = 0.0
    var ventas = 0
    print("Introduce una venta (* para terminar): ")
    var texto = readln()
    while (texto != "*") {
        val venta = texto.toDouble()
        suma += venta
        ventas++
        print("Introduce otra venta (* para terminar): ")
        texto = readln()
    }
    println("Número de ventas: $ventas")
    println("Suma total: $suma")
    }
fun ej16() {
    print("Introduce el primer número: ")
    val num1 = readln().toInt()
    print("Introduce el segundo número: ")
    val num2 = readln().toInt()
    val menor = minOf(num1, num2)
    val mayor = maxOf(num1, num2)
    for (i in 1..10) {
        val aleatorio = (menor..mayor).random()
        println(aleatorio)
    }
}
fun ej17() {
    print("Introduce una frase: ")
    val frase = readln()
    var vocales = 0
    var consonantes = 0
    var numeros = 0
    var espacios = 0
    for (caracter in frase) {
        if (caracter.lowercaseChar() in "aeiou") {
            vocales++
        } else if (caracter.isLetter()) {
            consonantes++
        } else if (caracter.isDigit()) {
            numeros++
        } else if (caracter == ' ') {
            espacios++
        }
    }
    println("Vocales: $vocales")
    println("Consonantes: $consonantes")
    println("Números: $numeros")
    println("Espacios: $espacios")
}
fun ej18() {
    val frase = "La lluvia en Sevilla es una maravilla"
    println(frase.replace("a", "e"))
}


