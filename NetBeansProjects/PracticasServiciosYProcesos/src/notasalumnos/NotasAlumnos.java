
package notasalumnos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class NotasAlumnos {
    File fichero = new File("alumnos.txt");
    public static void main(String[] args) {
    NotasAlumnos n = new NotasAlumnos();
    List<String> lista = n.leerFich();
    for (String linea : lista) {
        System.out.println(linea);
    }
    System.out.println("La media es: " + n.calcularMedia());
    }
    
    public List<String> leerFich(){
         List<String> lista = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                lista.add(linea);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }  
    
    public double calcularMedia(){
    List<String> lista = leerFich();
    double suma = 0;
    for (String linea : lista) {
        String[] datos = linea.split(" ");
        suma += Double.parseDouble(datos[1].replace(",", "."));
    }
    return suma / lista.size();
    }
}
