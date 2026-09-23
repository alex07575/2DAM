package MansionZombie;

import java.util.Scanner;

public class Principal extends Juego {

    public static void main(String[] args) {
        Juego j = new Juego();
        Scanner sc = new Scanner(System.in);
        System.out.println("===BIENVENIDO A LA MANSIÓN ZOMBIE===");
        System.out.println("Elige la dificultad, 1=fácil o 2=difícil:");
        int respuesta = sc.nextInt();
        j.elegirDif(respuesta);
        j.combate(); 
        j.buscar();
        j.avanzar();
        j.curarse();
    }
    
}
