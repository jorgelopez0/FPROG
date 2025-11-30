import java.util.*;
import java.text.*;
import java.io.*;

public class PracticaFinal {
    public static String DimensionesTablero(Scanner in) {
		//Valida la entrada, debe ser <digito>x<digito> y digito contenido en [1,5]
		String dimension = "";
		do{
			System.out.println("Introduce el tamaño de cuadrados del tablero <filas> x <columnas> valor maximo 5");	
			dimension = in.nextLine();	
		}while(dimension.length() <= 2 || dimension.length()>3 || dimension.charAt(0)-'0'> 5||dimension.charAt(2)-'0' > 5 || dimension.charAt(1) != 'x' || dimension.charAt(0)-'0' < 1 || dimension.charAt(2)-'0' < 1);
		
		return dimension;
    }

	public static char[][] CrearMatriz(int filas, int columnas){
		char[][] matriz = new char[filas][columnas];
        char letra = 'A';
		//Filas
        for(int i = 0; i < filas; i++){
			//pares: <.><caracter><.><caracter><.><caracter>...
			if(i % 2 == 0){
				//Columas
                for(int j = 0; j < columnas; j++){
					//pares: <.>
                    if(j % 2 == 0){
                        matriz[i][j] = '.';
					//impares: <caracter>
                    }else{
						if(letra == '['){
                            letra = 'a';
                        }else if(letra == '{'){
                            letra = '0';
                        }
                        matriz[i][j] = letra;
                        letra++;                   
                    }
                }
			//impares: <caracter><espacio><caracter><espacio><caracter>...
            }else{
				//Columas
                for(int j = 0; j < columnas; j++){
					//pares: <caracter>
                    if(j % 2 == 0){
                        if(letra == '['){
                            letra = 'a';
                        }else if(letra == '{'){
                            letra = '0';
                        }
                        matriz[i][j] = letra;
                        letra++;
					//impares: <espacio>
                    }else{
						matriz[i][j] = ' ';
                    }
                }
            }
        }
        return matriz;
    }
    
	public static void ImprimirMatriz(char[][]matriz, int filas, int columnas) {
        //Recorre matriz imprimiendo los elementos
		for(int i = 0; i < filas; i++){
            for(int j = 0; j < columnas; j++){
				//Si el elemento es '-' lo rodea de otros dos '-' para crear "---"
				if(matriz[i][j] == '-'){
					System.out.print( "-" + matriz[i][j] + "-");
				}else{
					System.out.print( " " + matriz[i][j] + " ");
				}
            }
            System.out.println();
        }
    }
			
	public static char[][] ActualizarPosicion(char[][] matriz, int filas, int columnas, char jugada, int turno){
		//Recorre matriz buscando el caracter introducido
		for(int i = 0; i < filas; i++) {
    		for (int j = 0; j < columnas; j++) {
    			if(matriz[i][j] == jugada) {
					//Si la fila es par se marca con '-'
					if(i % 2 == 0){
						matriz[i][j] = '-';
						//Busca posibles cuadrados completos debajo de la fila actual
						if( i < filas - 1 && matriz[i+2][j] == '-' && matriz[i+1][j-1] == '|' && matriz[i+1][j+1] == '|'){
							//Dependiendo de que jugador sea el turno se marca con '#' o '*'
							if(turno == 0){
								matriz[i+1][j] = '#';
							}else{
								matriz[i+1][j] = '*';
							}
						}
						//Busca posibles cuadrados completos arriba de la fila actual
						if(i >= 2 && matriz[i-2][j] == '-' && matriz[i-1][j-1] == '|' && matriz[i-1][j+1] == '|'){
							//Dependiendo de que jugador sea el turno se marca con '#' o '*'
							if(turno == 0){
								matriz[i-1][j] = '#';
							}else{
								matriz[i-1][j] = '*';
							}
						}
					//Si la fila es impar se marca con '|'
    				}else{
						matriz[i][j] = '|';
						//Busca posibles cuadrados completos a la derecha de la columna actual
						if(j < columnas - 1 && matriz[i][j+2] == '|' && matriz[i+1][j+1] == '-' && matriz[i-1][j+1] == '-'){
							if(turno == 0){
								matriz[i][j+1] = '#';
							}else{
								matriz[i][j+1] = '*';
							}
						}
						//Busca posibles cuadrados completos a la izquierda de la columna actual
						if(j >= 2 && matriz[i][j-2] == '|' && matriz[i-1][j-1] == '-' && matriz[i+1][j-1] == '-'){
							//Dependiendo de que jugador sea el turno se marca con '#' o '*'
							if(turno == 0){
								matriz[i][j-1] = '#';
							}else{
								matriz[i][j-1] = '*';
							}
						}
					}
    			}
    		}
		}
    	return matriz;
    }

	public static int Lineas(char[][] matriz, int filas, int columnas) {
		// Se cuentan las posiciones que tienen alguno de los caracteres '-' o '|'
		int lineas = 0;
		for(int i = 0; i < filas; i++) {
			for(int j = 0; j < columnas; j++) {
				if(matriz[i][j] == '|'){
					lineas++;
				}
				if(matriz[i][j] == '-'){
					lineas++;
				}
			}
		}
		return lineas;
	}
	
	public static int[] Puntuacion(char[][] matriz, int filas, int columnas) {
		int[] array_resultados= new int[2];
    	int puntos1 = 0, puntos2 = 0;
		//Recorre matriz buscando '# y '*'
    	for(int i = 0; i < filas; i++) {
    		for(int j = 1; j < columnas; j++) {
    			if(matriz[i][j] == '#') {
    				puntos1++;
    			} else if(matriz[i][j] == '*') {
    				puntos2++;
				}
    		}
    	}
		//Los resultados se almacenan en un array de dos enteros
    	array_resultados[0] = puntos1;
    	array_resultados[1] = puntos2;
    	return array_resultados;
    }
	
	public static boolean FinalPartida(char[][] matriz, int filas, int columnas) {
		//Si no existen espacios en la matriz se considera que la partida ha terminado
		for(int i = 0; i < filas; i++) {
    		for(int j = 0; j < columnas; j++) {
    			if(matriz[i][j] == ' '){
    				return false;
    			}
    		}
    	}	
		return true;
	}

	public static boolean Partida(char[][] matriz, int filas, int columnas, String dimension, int opcion, Scanner in){
		//Fecha y hora actual
    	Date ahora = new Date();
    	SimpleDateFormat formato = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");

        // Establecer zona horaria
        formato.setTimeZone(TimeZone.getTimeZone("CET"));
        String fechaHoraFormateada = formato.format(ahora);

		//Turno y puntuacion y numero de lineas
		int turno = (int) (Math.random()*2);
		int [] puntuacion, nueva_puntuacion;
		int lineas, lineas_tras_jugada;

		//Modificacion del tablero
		boolean sigue = true;
		boolean guardar = false;
		while(sigue){
			//Puntuacion actual
			puntuacion = Puntuacion(matriz, filas, columnas);

			//Numero de Lineas actuals
			lineas = Lineas(matriz, filas, columnas);

			//Entradas de los jugadores
			char entrada;
			System.out.println("["+ ((turno == 0) ? "JUGADOR 1" : "JUGADOR 2" ) +"] (* guardar y salir)");
			entrada = in.nextLine().charAt(0);

			//Guardar partida
			if(entrada == '*'){
				try  {
					//Guarda la partida en el fichero
					FileWriter Partida = new FileWriter("ficheros/partidaGuardada.txt");
					Partida.write(filas + " " + columnas + " " + dimension + "\n");
					for(int i = 0; i < filas; i++){
						for(int j = 0; j < columnas; j++){
							Partida.write(matriz[i][j]);
						}
						Partida.write("\n");
					}
					Partida.close();
				}catch(IOException e){
					System.out.println("Error al escribir en el fichero");
				}
				sigue = false;	
				guardar = true;
			}else{

				//Modificar el tablero e imprimirlo
				matriz = ActualizarPosicion(matriz, filas, columnas, entrada, turno);
				ImprimirMatriz(matriz, filas, columnas);

				//Cambiar el turno si no se ha completado un cuadrado y si se ha cambiado un caracter
				nueva_puntuacion = Puntuacion(matriz, filas, columnas);
				lineas_tras_jugada = Lineas(matriz, filas, columnas);
				if(nueva_puntuacion[0] == puntuacion[0] && nueva_puntuacion[1] == puntuacion[1] && lineas_tras_jugada > lineas){
					if(turno == 0){
						turno = 1;
					}else{
						turno = 0;
					}
				}
				if(guardar){
					main(null);
				}
			
				//Terminar partida
				if(FinalPartida(matriz, filas, columnas)){
					//Vaciar el fichero de partida guardada si se ha jugado la partida guardada
					if(opcion == 2){
						try{
							FileWriter Partida = new FileWriter("ficheros/partidaGuardada.txt");
							Partida.write("No hay ninguna partida guardada.\n");
							Partida.write("\n");
							Partida.close();
						}catch(IOException e){
							System.out.println("Error al escribir en el fichero");
						}
					}
					//Guardar el resultado en el fichero
					try  {
						FileWriter Resultados = new FileWriter("ficheros/resultados.txt", true);
						Resultados.write("[" + fechaHoraFormateada + "] Tam: "+ dimension + " Jugador 1: " + nueva_puntuacion[0] + " VS Jugador 2: " + nueva_puntuacion[1] + "\n");
						Resultados.close();
					}catch(IOException e){
						System.out.println("Error al escribir en el fichero");
					}
					//Puntuacion de los jugadores
					System.out.println("Juego terminado!!");
					System.out.println("Jugador 1: " + nueva_puntuacion[0] + " cuadrados");
					System.out.println("Jugador 2: " + nueva_puntuacion[1] + " cuadrados");
					System.out.println();
					sigue = false;
				}
				
			}
		}
		return guardar;
	}
	
	public static void Opcion1(){
		Scanner in = new Scanner(System.in);
		boolean partida = true;
		while(partida){
        	//Entrada de tamaño de la matriz
			String dimension = DimensionesTablero(in);		
        
        	//Crear el tablero y representarlo
        	int filas = 2 * (dimension.charAt(0) -'0') + 1;
        	int columnas= 2 * (dimension.charAt(2) -'0') + 1;
        	char[][] matriz = new char[filas][columnas];
        	matriz = CrearMatriz(filas, columnas);
        	ImprimirMatriz(matriz, filas, columnas);

			//Jugar partida
			int opcion = 1;
			boolean guardar = Partida(matriz, filas, columnas, dimension, opcion, in);

			//Si la partida se ha guardado va directamente al menú
			if(!guardar){
			//Nueva partida
				System.out.println("Desea jugar otra partida? (S/N)");
				partida = in.nextLine().charAt(0) == 'S';
			}else{ partida = false; }
			
			//Volver al menú
			if(!partida){
				main(null);
			}
		}
		in.close();
	}
        
   	public static void Opcion2(){
		Scanner in = new Scanner(System.in);
		File Partida = new File("ficheros/partidaGuardada.txt");
		//Carga la partida guardada
		try {
			Scanner lector = new Scanner(Partida);
			String datos = lector.nextLine();
			//Si no hay ninguna partida guardada
			if(datos.equals("No hay ninguna partida guardada.")){
				System.out.println(datos);
				System.out.println();
				main(null);
				in.close();
			//Si hay una partida guardada
			}else{
				boolean partida = true;
				
				//Obtener datos de la partida guardada
				int filas = datos.charAt(0) -'0';
				int columnas = datos.charAt(2) -'0';
				String dimension = datos.substring(4,7);
				char[][] matriz = new char[filas][columnas];

				//Obtener la matriz guardada
				for(int i = 0; i < filas && lector.hasNextLine(); i++){
					String linea = lector.nextLine();
					for(int j = 0; j < columnas; j++){
						matriz[i][j] = linea.charAt(j);
					}
				}
				lector.close();

				//Imprimir la matriz
				ImprimirMatriz(matriz, filas, columnas);
				
				//Jugar la partida
				int opcion = 2;
				boolean guardar = Partida(matriz, filas, columnas, dimension, opcion, in);
				
				
				//Si la partida se ha guardado va directamente al menú
				if(!guardar){
					//Nueva partida
					System.out.println("Desea jugar otra partida? (S/N)");
					partida = in.nextLine().charAt(0) == 'S';
				}else{ partida = false; }

				//Volver al menú
				if(partida){
					Opcion1();
				}else{ main(null); }
				
			}
			in.close();
	  	} catch (FileNotFoundException e) {
			System.out.println("No hay ninguna partida guardada.");
	  	}

		
	}

  	public static void Opcion3(){
		//Leer los resultados del fichero
		File Resultados = new File("ficheros/resultados.txt");
		try {
			Scanner lector = new Scanner(Resultados);
			while (lector.hasNextLine()) {
			  	String data = lector.nextLine();
				//Imprimir los resultados
			  	System.out.println(data);
			}
			lector.close();
	  	} catch (FileNotFoundException e) {
			System.out.println("Error leyendo los resultados.");
	  	}
	  	System.out.println();
		//Volver al menú
		main(null);
	} 
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        int opcion;
        do{
			//Imprimir menú de opciones
            System.out.println("Bienvenido a ¡La Conquista!\n---------------------------\n1. Nuevo Juego\n2. Cargar Partida\n3. Ver Resultados\n4. Salir\n---------------------------");

			//PRE: La entrada al elegir opciones debe ser un número
            opcion = in.nextInt();

			//Validar opcion
            if(opcion < 1 || opcion > 4){
                System.out.println("Opción no válida!");
                System.out.println();
            }
        }while(opcion < 1 || opcion > 4);

		//Ejecutar opcion
        switch(opcion){
            case 1 -> Opcion1();
            case 2 -> Opcion2();
            case 3 -> Opcion3();
            case 4 -> {
                System.out.println("Saliendo...");
            }
        }
		in.close();	
    }
}