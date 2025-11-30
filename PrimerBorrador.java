import java.util.*;
import java.io.*;

public class PrimerBorrador {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            continuar = Bienvenida(in);
        }

        System.out.println("Programa finalizado.");
        in.close();
    }
    
    public static boolean Bienvenida(Scanner in){
        int opcion;
        do{
            System.out.println("Bienvenido a ¡La Conquista!\n---------------------------\n1. Nuevo Juego\n2. Cargar Partida\n3. Ver Resultados\n4. Salir\n---------------------------");
            
            opcion = in.nextInt();
    
            if(!opcionValida(opcion)){
                System.out.println("Opción no válida!");
                System.out.println();
            }
            }while(!opcionValida(opcion));

        switch(opcion){
            case 1 -> Opcion1(in);
            case 2 -> System.out.println("Opcion2()");
            case 3 -> Opcion3();
            case 4 -> {
                System.out.println("Saliendo...");
                return false;
            }
            }
        return true;
    }
  
    public static void Opcion1(Scanner sc){
        System.out.println("Introduce el tamaño de cuadrados del tablero (filas y columnas) valor maximo 5");
        String input = sc.nextLine();
        String[] parts = input.split("[ x]+");
        int filas = Integer.parseInt(parts[0]); // Filas
        int columnas = Integer.parseInt(parts[1]); // Columnas

        ImprimeMatriz(filas, columnas);
        
        System.out.println("[] proximo palito (** guardar y salir)");
        System.out.println();
    }

    public static void Opcion3(){
        File myObj = new File("ficheros/resultados.txt");
        try {
            Scanner lector = new Scanner(myObj);
            while (lector.hasNextLine()) {
              String data = lector.nextLine();
              System.out.println(data);
            }
            lector.close();
          } catch (FileNotFoundException e) {
            System.out.println("Error leyendo los resultados.");
          }
          System.out.println();
    }

    public static boolean opcionValida(int opcion){
        boolean b = false;
        if(opcion >= 1 && opcion <= 4){
            b = true;
        }
        return b;
    }

    public static void ImprimeMatriz(int filas, int columnas){
        char[][] tablero = new char[filas + 1][columnas + 1];
        
        char posicion = 'A';
        for(int i = 0; i <= filas; i++){
            for(int j = 0; j <= columnas; j++){
                System.out.print("·");
                if(j < columnas){
                    tablero[i][j] = posicion;
                    System.out.print(" " + tablero[i][j] + " ");
                    posicion++;
                    if(posicion == '['){
                        posicion = 'a';
                    }
                    else if(posicion == '{'){
                        posicion = '0';
                    }
                }
            }
            System.out.println();
            for(int j = 0; j <= columnas; j++){
                if(i < filas){
                    tablero[i][j] = posicion;
                    System.out.print(tablero[i][j] + "   ");
                    posicion++;
                    if(posicion == '['){
                        posicion = 'a';
                    }else if(posicion == '{'){
                        posicion = '0';
                    }
                }
            }
            System.out.println();
        }
    }

}