package cofuzzy;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import cofuzzy.rgb;

@SuppressWarnings("serial")
public class VentanaCargarImagen extends JFrame {
		
	public VentanaCargarImagen() {		
		super("Fuzzificador de cafe con Vision artificial");
		
	}
	
	public static String rutaImagen;
	public static String titulo = "Titulo";
	public static String resultadoMadurez = "Aca va el Fuzzy-resultado de madurez";
	
	public static void main(String[] args) {
		ejecutar();
	}
	
	public static void ejecutar() {
		
		VentanaCargarImagen Ventana1 = new VentanaCargarImagen();
		
		//setSize(1000,1300);
		Ventana1.setLayout(new BorderLayout());
		Ventana1.setVisible(true);
		Ventana1.setDefaultCloseOperation(EXIT_ON_CLOSE);
				
		//TITULO DE LA VENTANA
				
		JLabel tituloLbl = new JLabel(titulo,SwingConstants.CENTER);
		tituloLbl.setFont(new Font("SansSerif", Font.BOLD, 30));
		Ventana1.add(tituloLbl,BorderLayout.NORTH);
				
		//IMAGEN Y ESPACIO PARA LA MISMA
				
		JLabel ImgLbl = new JLabel();
		//String rutaImg = "C:/ScaredCat.jpg";
		//ImageIcon imagen = new ImageIcon(rutaImg);
		//ImgLbl.setIcon(imagen);
		//Ventana1.add(ImgLbl);
		
		//Contenedor de Boton y Label
		JPanel contAbajo = new JPanel();
		contAbajo.setLayout(new FlowLayout());
		
		//BOTON 
		JButton cargarImgsBtn = new JButton("Cargar imagen");
		cargarImgsBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
		contAbajo.add(cargarImgsBtn);
		      		
		//Label de la salida
		
		Border border = new LineBorder(Color.black, 3);
		
		String salida = resultadoMadurez; 
		JLabel salidaLbl = new JLabel(salida,SwingConstants.CENTER);
		salidaLbl.setFont(new Font("SansSerif", Font.BOLD, 15));
		salidaLbl.setBorder(border);
		salidaLbl.setPreferredSize(new Dimension(500, 50));
		contAbajo.add(salidaLbl);
		
		Ventana1.add(contAbajo,BorderLayout.CENTER);
		Ventana1.pack();
		
		cargarImgsBtn.addActionListener(new ActionListener() {
			
	        public void actionPerformed(ActionEvent e) {
	        	
	            try {
	            	
	            	//Creamos el objeto JFileChooser
	            	JFileChooser fc =new JFileChooser();
	            	 
	            	//Abrimos la ventana, guardamos la opcion seleccionada por el usuario
	            	int seleccion = fc.showOpenDialog(Ventana1);
	            	 
	            	//Si el usuario, pincha en aceptar
	            	if(seleccion==JFileChooser.APPROVE_OPTION){
	            	 
	            	    //Seleccionamos el fichero
	            	    File fichero = fc.getSelectedFile();
	            	 
	            	    //Ecribe la ruta del fichero seleccionado en el campo de texto
	            	    
	            	    String rutaImg2 = fichero.getAbsolutePath();
	            	    System.out.println(rutaImg2);
	            	    
            			//Guardar la imagen para poder usarla luego en el proceso de Vision artificial
            			Ventana1.guardarRutaImagen(rutaImg2);
	            	    
	            	    //Tratamos de poner la imagen leida en la ventana
	            	    try(FileReader fr = new FileReader(fichero)){
	            	    	
	            	        //Crear una imagen a partir de la ruta que contiene la imagen
	            			ImageIcon imagen2 = new ImageIcon(rutaImg2);
	            			
	            			ImgLbl.setIcon(imagen2);
	            			Ventana1.add(ImgLbl,BorderLayout.SOUTH);
	            			
	            			Ventana1.validate();
	            			Ventana1.repaint();
	            			Ventana1.pack();
	            	        
	            	    } catch (IOException e1) {
	            	        e1.printStackTrace();
	            	    }
	            	}
	            	
	            	
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	            
	        }
	    });
				
	}
	
	public static void guardarRutaImagen(String ruta) {
		rutaImagen = ruta;
		rgb.calcularRGB(ruta);
	}
	
}
