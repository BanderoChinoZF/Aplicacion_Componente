/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author luisa
 */
public class BotonesPlanillas extends JComponent implements Serializable {
    
    private JButton[] btn_planillas;
    private JLabel tituloBotones;
    private int num_planillas;
    
    private static Color color[] = {Color.CYAN, Color.GREEN, Color.MAGENTA,
                                    Color.YELLOW, Color.ORANGE, Color.RED, Color.PINK};
    
    private static String[] nombrePlanillas = {"CYAN","VERDE","MAGENTA",
                                        "AMARILLO","NARANJA","ROJO","ROSA"};
    
    
    public BotonesPlanillas(int nPlanillas)
    {   
        //AIGNAMOS EL NUMERO DE PLANILLAS
        if(nPlanillas>7)
        {
            System.out.println("Solo Pueden Existir 7 Planillas.");            
        }
        this.num_planillas = nPlanillas;
        
        iniciarComponentes();   
    }
    
    public void iniciarComponentes()
    {
        tituloBotones = new JLabel("Presiona en la planilla para votar");
        //AIGNAMOS EL TAMAÑO A NUESTRO ARREGLO DE BOTONES
        btn_planillas = new JButton[num_planillas];
        
        for(int o=0; o<num_planillas; o++)
        {
            btn_planillas[o] = new JButton(nombrePlanillas[o]);
            btn_planillas[o].setBackground(color[o]);
            add(btn_planillas[o]);
        }
        
        add(tituloBotones);
        
    }
    
    public void paintComponent(Graphics f)
    {
        tituloBotones.setBounds((int)((getWidth()-f.getFontMetrics().stringWidth(tituloBotones.getText()))/2), 10, f.getFontMetrics().stringWidth(tituloBotones.getText())*2,20);
        
        System.out.println(this.getWidth());
        System.out.println(this.getHeight());
        
    }
    
    
}
