/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cofuzzy;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Rule;

public class LogicaDifusa {
	
	public static String x; 

    public static void fusificar(int sumrojo, int sumverde, int sumazul) throws Exception {
        // Load from 'FCL' file
        String fileName = "src/cofuzzy/Archivo FCL.fcl";
        FIS fis = FIS.load(fileName, true);
        // Error while loading?
        if (fis == null) {
            System.err.println("Can't load file: '" + fileName + "'");
        }
        // Set inputs
        fis.setVariable("rojo", sumrojo);
        fis.setVariable("verde", sumverde);
        fis.setVariable("azul", sumazul);
    
        // Evaluate
        fis.evaluate();

        // Show
        JFuzzyChart.get().chart(fis.getFunctionBlock("RGB"));

        Double x = fis.getVariable("estado").getLatestDefuzzifiedValue();
        System.err.println("Para los valores de salida el grado de pertenencia es: " + x);
        
        // Show rules
        for (Rule r : fis.getFunctionBlock("RGB").getFuzzyRuleBlock("No1").getRules()) {
            System.out.println(r);
        }
        LogicaDifusa.x = categorizar(x);
    }
    
    public static String categorizar(double x) {
    	String c;
    	if (x >= 0.25 && x <= 1.75) {
    		c = "Verde 1";
    	}
    	else if (x >= 1.25 && x <= 2.75) {
    		c = "Verde 2";
    	}
    	else if (x >= 2.25 && x <= 3.75) {
    		c = "Verde 3";
    	}
    	else if (x >= 3.25 && x <= 4.75) {
    		c = "Amarillo";
    	}
    	else if (x >= 4.25 && x <= 5.75) {
    		c = "Pinton";
    	}
    	else if (x >= 5.25 && x <= 6.75) {
    		c = "Maduro";
    	}
    	else if (x >= 6.25 && x <= 7.75) {
    		c = "Sobremaduro";
    	}
    	else if (x >= 7.25 && x <= 8.75) {
    		c = "Seco";
    	}
    	else {
    		c= "No se ha podido identificar el estado del grano";
    	}
    	return c;
    }
}