/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cofuzzy;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Rule;

public class LogicaDifusa {
	
	public static double x; 

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
        LogicaDifusa.x = x;
    }
}