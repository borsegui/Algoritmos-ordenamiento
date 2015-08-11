import java.awt.EventQueue;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.Arrays;
import javax.swing.ButtonGroup;
import javax.swing.JTextPane;
public class Interfaz {

	private JFrame frame;
	private JTextField txtK;
	/**
	 * Variables para la generacion de datos desordenados.
	 */
	Random rand = new Random();
	int max = 99;
	int min = 10;
	int m = 0;
	/**
	 * Variables para el vector de datos y la cantidad de datos a ordenar respectivamente.
	 */
	int[] n = new int[3000];
	int l = 2;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setBounds(100, 100, 474, 356);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		JRadioButton Selection = new JRadioButton("Selection sort", true);
		Selection.setBounds(10, 126, 109, 23);
		frame.getContentPane().add(Selection);
		
		JRadioButton Insertion = new JRadioButton("Insertion sort");
		Insertion.setBounds(10, 152, 109, 23);
		frame.getContentPane().add(Insertion);
		
		JRadioButton Merge = new JRadioButton("Merge sort");
		Merge.setBounds(10, 178, 109, 23);
		frame.getContentPane().add(Merge);
		
		JRadioButton Quick = new JRadioButton("Quick sort");
		Quick.setBounds(10, 204, 109, 23);
		frame.getContentPane().add(Quick);
		
		ButtonGroup group = new ButtonGroup();
		group.add(Selection);
		group.add(Insertion);
		group.add(Merge);
		group.add(Quick);
		
		JButton btnNewButton = new JButton("Ordernar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean OpcionSelection = Selection.isSelected();
					boolean OpcionInsertion = Insertion.isSelected();
					boolean OpcionMerge = Merge.isSelected();
					boolean OpcionQuick = Quick.isSelected();
					l = Integer.parseInt(txtK.getText());
					
					if (OpcionSelection) {
						System.out.println("Se ha realizado un ordenamiento mediante Selection sort con " + l + " datos.");
						SelectionSort(n, l);
						System.out.println(Arrays.toString(n));
					}
					if (OpcionInsertion) {
						System.out.println("Se ha realizado un ordenamiento mediante Insertion sort con " + l + " datos.");
						InsertionSort(n, l);
						System.out.println(Arrays.toString(n));
					}
					if (OpcionMerge) {
						MergeSort(n, 0, l);
						System.out.println("Se ha realizado un ordenamiento mediante Merge sort con " + l + " datos.");
						System.out.println(Arrays.toString(n));
					}
					if (OpcionQuick) {
						System.out.println("Se ha realizado un ordenamiento mediante Quick sort con " + l + " datos.");
						QuickSort(n, l);
						System.out.println(Arrays.toString(n));
					}
					
					
					} 
				catch (NumberFormatException nfe) { 
					l = 2;
					txtK.setText("2");
					System.out.println("No se han ingresado numeros. El numero por defecto es dos");
				}
				
				}
	
			}
		);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(296, 274, 96, 23);
		frame.getContentPane().add(btnNewButton);
		
		txtK = new JTextField();
		txtK.setText("2");
		txtK.setBounds(10, 277, 109, 20);
		frame.getContentPane().add(txtK);
		txtK.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Crear datos");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					/**
		             * @Se crea el archivo de texto
		             */
					PrintWriter writer = new PrintWriter("desordenados.txt", "UTF-8");
					/**
		             * @Se inicializa un ciclo que imprime linea por linea un numero aleatorio
		             */
					   for(int i=0; i<3000; i++){
						   /**
				             * @Crea un random cada vez que inicia el ciclo
				             */
						   int r = rand.nextInt((max - min) + 1) + min;
						   /**
				             * @Imprime el random creado en la linea actual del archivo de texto
				             */
						   writer.println(r);     
			           }
					/**
			          * @Cierra el proceso
			          */
			        System.out.println("Se han creado exitosamente los datos.");
					writer.close();
					} 
				catch ( IOException ioe ) { ioe.printStackTrace(); }
				}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(10, 42, 105, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Abrir datos");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 
				try{
		        	/**
		             * @se abre el archivo de texto
		             */
		            FileInputStream fstream = new FileInputStream("desordenados.txt");
		            /**
		             * @creamos el objeto de entrada 
		             */
		            DataInputStream entrada = new DataInputStream(fstream);
		            /**
		             * @se crea el Buffer de lectura del  archivo de texto
		             */
		            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
		            String strLinea;
		            /**
		             * @prepara el entero "i" para ser usado acontinuacion en un contador
		             */
		            m = 0;
		            
		            /**
		             * @lee el archivo linea por linea, salta al encontrar un espacio"null"
		             */
		            
		            while ((strLinea = buffer.readLine()) != null)   {
		            	/**
		                 * Lee el archivo linea por linea como string y luego la vuelve a entero, antes de guardarlo en el vector.
		                 */
		                int leer = Integer.parseInt(strLinea);
		                n[m] = leer;
		                m = m+1;
		            }
		            /**
		             * @cierra el archivo
		             */

		            System.out.println("Se han cargado exitosamente los datos.");
		            entrada.close();
          
		        }catch (Exception e){ 
		            System.err.println("Ocurrio un error: " + e.getMessage());
		        }
			}
		});
		
		
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2.setBounds(125, 42, 105, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JLabel label = new JLabel("-----------------------------------------------------------------------------------------------------------------------");
		label.setBounds(0, 76, 474, 14);
		frame.getContentPane().add(label);
		
		JLabel lblTipoDeOrdenamiento = new JLabel("Tipo de ordenamiento");
		lblTipoDeOrdenamiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTipoDeOrdenamiento.setBounds(10, 91, 142, 14);
		frame.getContentPane().add(lblTipoDeOrdenamiento);
		
		JLabel lblNewLabel_1 = new JLabel("Cantidad de datos \r\na ordenar");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 250, 183, 20);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("HOJA DE TRABAJO # 3");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 11, 175, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JTextPane txtpnCreeDatos = new JTextPane();
		txtpnCreeDatos.setEditable(false);
		txtpnCreeDatos.setText("PARA USAR:\r\n* Oprima crear datos\r\n* Oprima abrir datos\r\n* Escoja el metodo para ordenar\r\n* Escoja la cantidad de datos para ordenar\r\n* Oprima el boton ordenar\r\n* Los mensajes se mostraran en consola");
		txtpnCreeDatos.setBounds(255, 91, 193, 151);
		frame.getContentPane().add(txtpnCreeDatos);
	}
	
	//INSERTION SORT
	public int [] InsertionSort (int[] arreglo, int limite){
		for (int i=1; i < limite; i++) {
			int x = arreglo[i];
			int j;
			for (j=i-1; j>=0 && x<arreglo[j]; j--)
				arreglo[j+1]= arreglo[j];
				arreglo[j+1] = x;
		}		
		return arreglo;
	}
	
	//QUICK SORT
	public int [] QuickSort (int[] arreglo, int limite){
		int a = limite;
		int b = a/2;
		for (int i=1; i < b; i++) {
			int x = arreglo[i];
			int j;
			for (j=i-1; j>=0 && x<arreglo[j]; j--)
				arreglo[j+1]= arreglo[j];
				arreglo[j+1] = x;
		}
		for (int k=arreglo.length; k < b; k--) {
			int x = arreglo[k];
			int j;
			for (j=k-1; j>=0 && x<arreglo[j]; j--)
				arreglo[j+1]= arreglo[j];
				arreglo[j+1] = x;
		}		
		return arreglo;
	}
	
	public int [] SelectionSort(int[] arreglo, int limite) {
	      int i, j, minIndex, tmp;
	      int l = limite;
	      for (i = 0; i < l - 1; i++) {
	            minIndex = i;
	            for (j = i + 1; j < l; j++)
	                  if (arreglo[j] < arreglo[minIndex])
	                        minIndex = j;
	            if (minIndex != i) {
	                  tmp = arreglo[i];
	                  arreglo[i] = arreglo[minIndex];
	                  arreglo[minIndex] = tmp;
	            }
	      }
	      return arreglo;
	}
	
	// MERGESORT
	public static void MergeSort(int array[], int start, int end) {
	    int med;     // Numero medio del arreglo.
	    int iz;       // Numero de la izquierda a ordenar.
	    int der;      // Numero de la derecha a ordenar.
	    int t;       // Memoria temporal.

	    if (start < end) {
	        // Divide el arreglo en mitades.
	        med = (start + end) / 2;
	        MergeSort(array, start, med);
	        MergeSort(array, med + 1, end);

	        // Declara el valor de algunas variables.
	        iz = start;
	        der = med + 1;

	        // Ordena numeros en el arreglo mientras sea posible.
	        while (iz <= med && der <= end) {
	            // Arregla los numeros si la izquierda es mas grande que derecha.
	            if (array[iz] > array[der]) {
	            	
	                // Guarda el numero derecho
	                t = array[der];
	                // Mueve la izquierda a derecha una posicion.
	                for (int i=der-1; i>=iz; i--) {
	                    array[i+1] = array[i];
	                }
	                // Pone el anterior derecho a la izquierda ahora.
	                array[iz] = t;
	                // Toma nuevas posiciones para ordenar.
	                der++;
	                med++;
	            }
	            // Sin importar lo que de el algoritmo anterior, toma nuevas posiciones de izquierda.
	            iz++;
	        }

	    }
	}
}


