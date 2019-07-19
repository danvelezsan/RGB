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
	public static String titulo = "COFUZZY";
	public static JTextArea salidaLbl = new JTextArea("");
	public static String resultadoMadurez = "Las imágenes tienen espacios de color. En la imagen de un grano de café, los valores de los diferentes espacios de color cambian dependiendo del estado de maduración del grano. Leyendo una imagen de granos de café y extrayendo los valores de los espacios de color de la imagen podemos calcular su estado de madurez por métodos de fuzzificación/borrosificación su estado de madurez";
	
	public static void ejecutar() {
		
		VentanaCargarImagen Ventana1 = new VentanaCargarImagen();
		
		Ventana1.setLayout(new BorderLayout());
		Ventana1.setVisible(true);
		Ventana1.setDefaultCloseOperation(EXIT_ON_CLOSE);
				
		//TITULO DE LA VENTANA
				
		JLabel tituloLbl = new JLabel(titulo,SwingConstants.CENTER);
		tituloLbl.setFont(new Font("SansSerif", Font.BOLD, 30));
		Ventana1.add(tituloLbl,BorderLayout.NORTH);
		
		//Contenedor de Boton, la imagen y Label
		JPanel contAbajo = new JPanel();
		contAbajo.setLayout(new BorderLayout());
		      		
		//Label de la salida
		
		Border border = new LineBorder(Color.black, 3);
		salidaLbl.setBorder(border);
		salidaLbl.setText(resultadoMadurez);
		salidaLbl.setFont(new Font("SansSerif", Font.BOLD, 15));
		salidaLbl.setPreferredSize(new Dimension(300, 300));
		salidaLbl.setBackground(Color.white);
		salidaLbl.setOpaque(true);
		salidaLbl.setLineWrap(true);
		salidaLbl.setWrapStyleWord(true);
		contAbajo.add(salidaLbl,BorderLayout.WEST);
		
		//Contenedor de la imagen y el boton
		JPanel contDerecha = new JPanel();
		contDerecha.setLayout(new BorderLayout());		
		
		//IMAGEN
		JLabel ImgLbl = new JLabel();
		ImgLbl.setPreferredSize(new Dimension(270, 250));
		contDerecha.add(ImgLbl,BorderLayout.NORTH);
		
		//BOTON 
		JButton cargarImgsBtn = new JButton("Cargar imagen");
		cargarImgsBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
		contDerecha.add(cargarImgsBtn,BorderLayout.SOUTH);
		
		contAbajo.add(contDerecha,BorderLayout.EAST);
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
            			VentanaCargarImagen.guardarRutaImagen(rutaImg2,Ventana1);
	            	    
	            	    //Tratamos de poner la imagen leida en la ventana
	            	    try(FileReader fr = new FileReader(fichero)){
	            	    	
	            	        //Crear una imagen a partir de la ruta que contiene la imagen
	            			ImageIcon imagen2 = new ImageIcon(rutaImg2);
	            			
	            			ImgLbl.setIcon(imagen2);
	            			//Ventana1.add(ImgLbl,BorderLayout.SOUTH);
	            			
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
	
	public static void guardarRutaImagen(String ruta, VentanaCargarImagen ventana) {
		rutaImagen = ruta;
		rgb.calcularRGB(ruta,ventana);
	}
	
	public void setLabelEstadoMaduracion (String x) {
		resultadoMadurez = x;
		String salida = "El grano está " + resultadoMadurez; 
		salidaLbl.setText(salida);
		this.repaint();
	}
		
}

