package cofuzzy;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import javax.imageio.ImageIO;

public class rgb {
	
	public static int sumrojo;
	public static int sumazul;
	public static int sumverde;

	public static void calcularRGB(String ruta) {
		
		int clr, rojo, verde, azul;
		int sumrojo = 0, sumverde = 0, sumazul = 0;

		try {
      		File file = new File(ruta);
      		BufferedImage image = ImageIO.read(file);
      		
      		int alto = image.getHeight(); //Altura de la imagen
      		int ancho = image.getWidth(); //Ancho de la imagen
      		
      		for (int i = 0; i < ancho; i++) {
       			for (int j = 0; j < alto; j++) {
        			clr = image.getRGB(i, j);
        			rojo = (clr & 0xff0000) >> 16;
        			verde = (clr & 0x00ff00) >> 8;
        			azul = clr & 0x0000ff;
        			sumrojo = rojo + sumrojo; //Calcula el total del valor rojo
        			sumverde = verde + sumverde; //Calcula el total del valor verde
        			sumazul = azul + sumazul; //Calcula el total del valor azul
       			}
      		}

      		sumrojo = sumrojo / (alto * ancho); //promedio del valor rojo 
      		sumazul = sumazul / (alto * ancho); //promedio del valor azul
      		sumverde = sumverde / (alto * ancho); //promedio del valor verde
      		rgb.sumrojo =sumrojo;
      		rgb.sumverde = sumverde;
      		rgb.sumazul = sumazul;
      		
     	} 
		catch (Exception e) {
     	}	     
	}
}
