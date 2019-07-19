package cofuzzy;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import javax.imageio.ImageIO;

public class rgb {

	public static void main(String[] args) {
		
		int clr, rojo, verde, azul;
		int sumrojo = 0, sumverde = 0, sumazul = 0;

		try {
      		File file = new File("Imágenes/2.PNG");
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
      		
      		float[] hsv = new float[3];
      		Color.RGBtoHSB(sumrojo, sumverde, sumazul, hsv);
      		System.out.println(sumrojo);
      		System.out.println(sumverde);
      		System.out.println(sumazul);
      		System.out.println(hsv[0]*100 + "%");
      		
     	} 
		catch (Exception e) {
      		//System.out.println("Exeption");
     	}	     
	}
}
