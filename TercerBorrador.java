import java.util.*;
import java.text.*;
import java.io.*;

public class TercerBorrador {
	
	public static boolean actualizarPosicion(char[][] matriz1, char[][] matriz2, int filas, int columnas, char nuevoValor, int m, boolean empieza1) {
    	boolean correcto = false;
    	for(int i=0; i<=filas; i++) {
    		for (int j=0; j<columnas; j++) {
    			if(matriz1[i][j]==nuevoValor) {
    				correcto =true;
    			}
    		}
    	}
    	for(int i=0; i<filas; i++) {
    		for (int j=0; j<columnas+filas+4; j++) {
    			if(matriz2[i][j]==nuevoValor) {
    				correcto =true;
    			}
    		}
    	}
    	if(!correcto) {
    		System.out.println("Mal introducido, repita turno");
    	}
    	return correcto;
    	
    }

	public static int completarCuadrado(char[][] matriz1, char[][] matriz2, int filas, int columnas, char nuevoValor, String jugador, int completado) {
    	int completado2 = 0;
    	for(int i=0; i<=filas; i++) {
    		for (int j=0; j<columnas; j++) {
    			if(matriz1[i][j]==nuevoValor) {
    				matriz1[i][j]='-';
    				
    					if(i!=filas && matriz1[i][j]=='-' && matriz2[i][j*2]=='|' && matriz2[i][j*2+2]=='|' && matriz1[i+1][j]=='-') {
    						if(jugador=="Jugador 1") {
        						matriz2[i][j*2+1]='#'; completado2=1; 
        					}else if(jugador=="Jugador 2") {
        						matriz2[i][j*2+1]='*'; completado2=2;
        					}
    					}else if(i>0) {
            				if(matriz1[i][j]=='-' && matriz2[i-1][j*2]=='|' && matriz2[i-1][j*2+2]=='|' && matriz1[i-1][j]=='-') {
            					if(jugador=="Jugador 1") {
            						matriz2[i-1][j*2+1]='#'; completado2=1;
            					}else if(jugador=="Jugador 2") {
            						matriz2[i-1][j*2+1]='*'; completado2=2;
            					}
                			}
            			}
    			}  
    		}
    	}
    	for(int i=0; i<filas; i++) {
    		for (int j=0; j<columnas+filas+4; j++) {
    			if(matriz2[i][j]==nuevoValor) {
    				matriz2[i][j]='|';
    				if(j<=columnas*2-2 && matriz1[i][j/2]=='-' && matriz2[i][j]=='|' && matriz2[i][j+2]=='|' && matriz1[i+1][j/2]=='-') {
    					if(jugador=="Jugador 1") {
    						matriz2[i][j+1]='#'; completado2=1;
    					}else if(jugador=="Jugador 2") {
    						matriz2[i][j+1]='*'; completado2=2;
    					}
					}else if(j>0) {
        				if(matriz1[i][j/2-1]=='-' && matriz2[i][j-2]=='|' && matriz2[i][j]=='|' && matriz1[i+1][j/2-1]=='-') {
        					if(jugador=="Jugador 1") {
        						matriz2[i][j-1]='#'; completado2=1;
        					}else if(jugador=="Jugador 2") {
        						matriz2[i][j-1]='*'; completado2=2;
        					}
            			}
        			}
    			}  
    		}
    	}

    	return completado2;
    }

	public static void imprimirTablero(char[][]matriz1, char[][] matriz2, int filas, int columnas, int m) {
    	int cont1=0;
    	int cont2=0;
    	int cont3;
    	for(int i = 0; i <=filas*2; i++){
    		cont3=0;
    		if(i>0 && i%2==0) {
    			cont1++;
    		} else if(i>1 && i%2!=0) {
    			cont2++;
    		}
            for(int j = 0; j <columnas; j++){
            	if(j>0) {
            		cont3++;
            	}
                //System.out.print("•");
                if(i%2==0){
                	System.out.print("•");
                    System.out.print(" " + matriz1[cont1][j] + " ");
                    if(j==columnas-1) {
                    	System.out.print("•");
                    }
                } else if(i%2!=0) {
                	System.out.print(matriz2[cont2][cont3]+" ");
                	cont3++;
                	System.out.print(matriz2[cont2][cont3]+" ");
                	if(j==columnas-1) {
                		cont3++;
                		System.out.print(matriz2[cont2][cont3]);
                	}
                } 
            }
            System.out.println();
            
        }
    }

	public static int[] comprobarVictoria(char[][] matriz2, int filas, int columnas, int elementos, int m) {
    	int[] array_resultados= new int[2];
    		int puntos1=0, puntos2=0;
    		for(int i=0; i<filas; i++) {
    			for(int j=1; j<columnas+filas+3; j+=2) {
    				if(matriz2[i][j]=='#') {
    					puntos1++;
    				} else if(matriz2[i][j]=='*') {
    					puntos2++;
    				}
    			}
    		}
    		array_resultados[0]=puntos1;
    		array_resultados[1]=puntos2;
    	return array_resultados	;
    }
	
	public static boolean opcionValida(int opcion){
        boolean b = false;
        if(opcion >= 1 && opcion <= 4){
            b = true;
        }
        return b;
    }
	
	public static boolean Bienvenida(Scanner in){
        int opcion;
        do{
			//Imprimir menu de opciones
            System.out.println("Bienvenido a ¡La Conquista!\n---------------------------\n1. Nuevo Juego\n2. Cargar Partida\n3. Ver Resultados\n4. Salir\n---------------------------");
            
            opcion = in.nextInt();

			//Validar opcion
            if(!opcionValida(opcion)){
                System.out.println("Opción no válida!");
                System.out.println();
            }
        }while(!opcionValida(opcion));

		//Ejecutar opcion
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
    	Date ahora = new Date();
    	SimpleDateFormat formato = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");

        // Establecer zona horaria (opcional)
        formato.setTimeZone(TimeZone.getTimeZone("CET"));
        String fechaHoraFormateada = formato.format(ahora);


        //Entrada de tamaño de la matriz
        System.out.println("Introduce el tamaño de cuadrados del tablero (filas y columnas) valor maximo 5");
        int filas = sc.nextInt();
        int columnas= sc.nextInt();

        //Inicializar matrices 
        char[][] matriz1 = new char[filas+1][columnas]; //elementos de las filas pares
        char[][] matriz2= new char[filas][columnas+4+filas]; //elementos de las filas impares
        //reservando espacios para los # y *
        
        //contadores para las posiciones en la matriz
        int cont1=0, cont2=0, cont3;

        //Escribir el tablero inicialmente
        char posicion = 'A';
        for(int i = 0; i <=filas*2; i++){
			cont3 = 0;
        	if(i>0 && i%2 == 0) {
        		cont1++;
        	} else if(i>1 && i%2!=0) {
        		cont2++;
        	}
            for(int j = 0; j <columnas; j++){
                if(i%2==0){
                	System.out.print("0");
                    System.out.print(" " + posicion + " ");
                    matriz1[cont1][j] = posicion;
                    posicion++;
                    if(posicion == '['){
                        posicion = 'a';
                    }
                    else if(posicion == '{'){
                        posicion = '0';
                    }
                    if(j==columnas-1) {
                    	System.out.print("0");
                    }
                } else if(i%2!=0){
                	System.out.print(posicion + "   ");
                    matriz2[cont2][cont3] = posicion;
                    posicion++;
                    cont3++;
                    matriz2[cont2][cont3] =' '; //espacio reservado donde luego iran los # y *
                    cont3++;
                    if(posicion == '['){
                        posicion = 'a';
                    }else if(posicion == '{'){
                        posicion = '0';
                    }
                    if (columnas == j+1) {
                    	System.out.print(posicion + "   ");
                        matriz2[cont2][cont3] = posicion;
                        posicion++;
                        if(posicion == '['){
                            posicion = 'a';
                        }else if(posicion == '{'){
                            posicion = '0';
                        }
                    }
                }
            }
            System.out.println();
            
        }
        
        System.out.println();
    
        //Primer turno aleatoriamente
        int numEmpezar = (int) (Math.random()*2);
        System.out.println("["+(numEmpezar == 0 ? "JUGADOR 1" : "JUGADOR 2")+"] (** guardar y salir)" + "\n");
        System.out.println();
        
        //boolean para ver quien empieza y string jugador
        boolean empieza1;
        String jugador = "";
        
        if(numEmpezar==0) {
        	empieza1=true;
        } else {
        	empieza1=false;
        }
        
        int elementos = (filas+1)*columnas + (columnas+1)*filas; //numero total de caracteres para terminar el juego
        boolean entrada_correcta=true; //inicializo que el caracter introducido es correcto inicialmente
        int completado=0, completado2=3; //inicializo valores para ver si algun cuadrado se ha completado
        boolean primera_completada=false;
        
        //bucle donde se jugara todo el juego
	    for(int m=0; m<elementos; m++) {
	    	//ver a que jugador le toca el turno
	    	if(completado2==1) { 
	    		jugador = "Jugador 1";
	    		primera_completada=true;
	    	} else if(completado2==2) {
	    		jugador = "Jugador 2";
	    		primera_completada=true;
	    	}else if (completado==0 && !primera_completada) {
	    		if(m%2==0 && empieza1) {
		        	jugador = "Jugador 1";
		        } else if(m%2==0 && !empieza1) {
		        	jugador = "Jugador 2";
		        } else if(m%2!=0 && empieza1) {
		       		jugador = "Jugador 2";
		        } else if(m%2!=0 && !empieza1) {
		       		jugador = "Jugador 1";
		       	}
	    	} else if(completado2==0 && primera_completada) {
	    		if(jugador== "Jugador 1") {
	    			jugador = "Jugador 2";
	    		} else if(jugador=="Jugador 2") {
	    			jugador = "Jugador 1";
	    		}
	    	}
	        System.out.println("Turno de "+jugador +", introduzca caracter");
	       	String entrada= sc.next();
	       	char caracter = entrada.charAt(0);
	       	entrada_correcta = actualizarPosicion(matriz1, matriz2, filas, columnas, caracter, m, empieza1);
	       	completado2 = completarCuadrado(matriz1, matriz2, filas, columnas, caracter, jugador, completado);
	       	imprimirTablero(matriz1, matriz2, filas, columnas, m);

	       	if(elementos==m+1) {
	       		String ganador;
	       		int[] resultados = comprobarVictoria(matriz2, filas, columnas, elementos, m);
	       		if (resultados[0]>resultados[1]) {
	       			ganador="jugador1";
	       			System.out.println("Ganó el "+ganador);
	       		} else if(resultados[0]<resultados[1]) {
	       			ganador="jugador 2";
	       			System.out.println("Ganó el "+ganador);
	       		} else {
	       			ganador="Empate";
	       			System.out.println(ganador);
	       		}
	       		
				/*
	       		String resultadoFormateado = String.format(
	       		        "[%s]     Tam: %dx%d      Jugador 1: %d  vs.   Jugador 2: %d\n",
	       		        fechaHoraFormateada, filas, columnas, resultados[0], resultados[1]
	       		    );

	       		    // Guardar en el archivo
	       		    /*try (ficheros escritor = new FileWriter("ficheros/resultados.txt", true)) {
	       		        escritor.write(resultadoFormateado);
	       		    } catch (IOException e) {
	       		        System.out.println("Error al guardar el resultado en el archivo.");
	       		    }*/
	       	}
	       	//System.out.println(m + "" +entrada_correcta);
	       	if(entrada_correcta==false) {
	       		m--;
	       	}
	    }	
   }

   	public static void Opcion2(){}

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
   	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        
		boolean continuar = true;
        while (continuar) {
            continuar = Bienvenida(in);
        }

        System.out.println("Programa finalizado.");
        in.close();
    }
}