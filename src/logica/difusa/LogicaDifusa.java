/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.difusa;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Rule;

/**
 * Test parsing an FCL file
 * @author pcingola@users.sourceforge.net
 */
public class LogicaDifusa {

    public static void main(String[] args) throws Exception {
        // Load from 'FCL' file
        String fileName = "src/logica/difusa/Archivo FCL.fcl";
        FIS fis = FIS.load(fileName, true);
        // Error while loading?
        if (fis == null) {
            System.err.println("Can't load file: '" + fileName + "'");
            return;
        }
        // Set inputs
        fis.setVariable("rojo", 116);
        fis.setVariable("azul", 53);
        fis.setVariable("verde", 144);

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
    }
}