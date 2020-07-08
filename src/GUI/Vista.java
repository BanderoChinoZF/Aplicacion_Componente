/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Clases.Alumno;
import ConexionBD.Conexion;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com_grafico.Grafico;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * @author luisa
 */
public final class Vista extends JFrame implements ActionListener {
    
    JPanel panelTitulo, panelValidar, panelPlanillas, panelGrafica;
    JLabel lbl_titulo, lbl_numControl, lbl_semestre;
    JTextField field_numControl;
    JComboBox combo_semestre;
    JButton btnValidar, btnFinalizar;
    JButton btnA, btnB, btnC, btnD;
    String txt_titulo = "Votaciones Por Planilla";
    Grafico grafico;
    
    int [] numeroVotos = {0,0,0,0};
    
    //
    private static Color color[] = {Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA,
                                   Color.YELLOW, Color.ORANGE, Color.RED, Color.DARK_GRAY, Color.PINK};
    private static String[] nombreColor = {"Azul","Cyan","Verde","Magenta","Amarillo","Naranja","Rojo","Gris Obscuro","Rosa"};

    private static String[] leyendas = {"Panilla Azul","Planilla Cyan","Planilla Verde"," Planilla Magenta"};
     
    //CREAMOS NUESTRO OBJETO CONEXION, A NUESTRA BASE DE DATOS.
    Conexion con = Conexion.getConexionBase("localhost", "5440", "practicaDISOR", "postgres", "Luis1125");
    
    //--------------------------------------------------------------------------
    private ArrayList<Alumno> alumnos = new ArrayList();
    private ArrayList<Integer> yaVotaron = new ArrayList();
    
    
    public Vista()
    {
        super();
        iniciarComponentes();
        
        this.setVisible(true);
    }
    
    public void iniciarComponentes()
    {
        //Configuraciones de la ventana.
        setTitle("Votaciones Grupos Representativos");
        setSize(800,600);
        //setSize(1200,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setResizable(false);
        setLayout(null);
        
        int ancho = this.getWidth();
        int alto = this.getHeight();
        
        int mitad = ancho/2;
        
        //Creamos nuestro panel del titulo.
        panelTitulo = new JPanel();
        //panelTitulo.setBackground(Color.red);
        panelTitulo.setBounds(0, 0, ancho, 50);
        //panelTitulo.setLayout(null);
        add(panelTitulo);
        
        //AGREGAMOS EL TITULO DE NUESTRO JFrame
        lbl_titulo = new JLabel(txt_titulo);
        lbl_titulo.setSize(50, 1);
        lbl_titulo.setFont(new Font("Serif", Font.BOLD, 30));
        panelTitulo.add(lbl_titulo);
        
        //CREAMOS NUESTRO PANEL DE VALIDACIONDE NUMERO DE CONTROL Y SEMESTRE
        panelValidar = new JPanel();
        //panelValidar.setBackground(Color.BLUE);
        panelValidar.setBounds(0, 50, ancho, 150);
        panelValidar.setLayout(null);
        add(panelValidar);
        
        //CREAMOS NUESTRA ETIQUETA, Y TEXFIELD DEL NUMERO DE CONTROL
        lbl_numControl = new JLabel("Ingrese Num. De Control: ");
        lbl_numControl.setBounds(130, 45, 180, 25);
        lbl_numControl.setFont(new Font("Serif", Font.BOLD, 15));
        
            //CREAMOS NUESTRO JTextField DONDE INGRESAREMOS EL NUMERO DE CONTROL
            field_numControl = new JTextField();
            field_numControl.setBounds(320, 45, 100, 25);
            
        //CREAMOS NUESTRA ETIQUETA, Y JComboBox PARA EL SEMESTRE
        lbl_semestre = new JLabel("Semestre: ");
        lbl_semestre.setBounds(430, 45, 70, 25);
        lbl_semestre.setFont(new Font("Serif", Font.BOLD, 15));
        
            //CREAMOS NUESTRO JComboBox PARA ELEGIR EL SEMESTRE
            combo_semestre = new JComboBox();
            combo_semestre.setBounds(510, 45, 100, 25);
            
            for(int h=1; h<14; h++)
            {
                //System.out.println(h);
                combo_semestre.addItem(""+ h +"");
            }
            
        //AGREGAMOS NUESTRO BOTON PARA VALIDAR EL NUMERO DE CONTROL INGRESADO
        btnValidar = new JButton("Validar");
        btnValidar.setBounds(340, 100, 100, 35);
        
        //AGREGAMOS NUESTRO EVENTO AL BOTON
        btnValidar.addActionListener(this);
        
        
        
        //AGREGAMOS LAS ETIQUETAS AL NUESTRO PANEL DE VALIDACIONES (panelValidar)
        panelValidar.add(lbl_numControl);
        panelValidar.add(lbl_semestre);
        
        //AGREGAMOS EL JTextField A NUESTRO PANEL
        panelValidar.add(field_numControl);
        panelValidar.add(combo_semestre);
        
        //AGREGAMOS NUESTRO BOTON AL PANEL
        panelValidar.add(btnValidar);
        
//------------------------------------------------------------------------------
        
        //CREAMOS NUESTROS BOTONES PARA REALIZAR LA VOTACION DE UNA PLANTILLA
        panelPlanillas = new JPanel();
        //panelPlanillas.setBackground(Color.PINK);
        panelPlanillas.setBounds(0, 200, ancho/2, 370);
        panelPlanillas.setLayout(null);
        
        //ETIQUETA PARA EL TITULO DEL PANEL DE BOTONES
        JLabel lbl_titulo_botones = new JLabel("Da un clic en la planilla de tu eleccion para votar");
        lbl_titulo_botones.setBounds(65, 20, 280, 30);
        panelPlanillas.add(lbl_titulo_botones);
        
        //CREAMOS EL BOTON A
        btnA = new JButton("Planilla "+nombreColor[0]);
        btnA.setBackground(color[0]);
        btnA.setBounds(40, 60, 150, 100);
        btnA.setEnabled(false);
        btnA.addActionListener(this);
        
        //CREAMOS EL BOTON B
        btnB = new JButton("Planilla "+nombreColor[1]);
        btnB.setBackground(color[1]);
        btnB.setBounds(220, 60, 150, 100);
        btnB.setEnabled(false);
        btnB.addActionListener(this);
        
        //CREAMOS EL BOTON C
        btnC = new JButton("Planilla "+nombreColor[2]);
        btnC.setBackground(color[2]);
        btnC.setBounds(40, 190, 150, 100);
        btnC.setEnabled(false);
        btnC.addActionListener(this);
        
        //CREAMOS EL BOTON C
        btnD = new JButton("Planilla "+nombreColor[3]);
        btnD.setBackground(color[3]);
        btnD.setBounds(220, 190, 150, 100);
        btnD.setEnabled(false);
        btnD.addActionListener(this);
        
        //AGREGAMOS LOS BOTONES A NUESTRO PANEL
        panelPlanillas.add(btnA);
        panelPlanillas.add(btnB);
        panelPlanillas.add(btnC);
        panelPlanillas.add(btnD);
        
        
        //BOTON PARA FINALIZAR EL PROCESO DE VOTACION
        btnFinalizar = new JButton("Terminar Proceso de Votaciones");
        btnFinalizar.setBounds(80, 320, 230, 30);
        btnFinalizar.addActionListener(this);
        panelPlanillas.add(btnFinalizar);
        
        
        add(panelPlanillas);
        
        
        
//------------------------------------------------------------------------------

        //CREAMOS NUESTRO PANEL DONDE SE ENCONTRARA LA GRAFICA DE LOS VOTOS DE
        //LAS PLANILLAS
        panelGrafica = new JPanel();
        panelGrafica.setLayout(null);
        //
        panelGrafica.setBounds(ancho/2, 200, ancho/2, 370);
        
        grafico = new Grafico("Numero De Votos X Planilla",leyendas);
        grafico.setValores(numeroVotos);
        grafico.setBounds(0,0, (ancho/2)-20, 370);
        grafico.iniciarElementos();
        panelGrafica.add(grafico);
        
        add(panelGrafica);
        
    }
    
    public void activarBotones()
    {
        btnA.setEnabled(true);
        btnB.setEnabled(true);
        btnC.setEnabled(true);
        btnD.setEnabled(true);
        
    }
    public void desactivarBotones()
    {
        btnA.setEnabled(false);
        btnB.setEnabled(false);
        btnC.setEnabled(false);
        btnD.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == btnValidar)
        {
            Connection c = con.getConnection();
            alumnos = con.obtenerDatosControl(c);
            int numeroIngresado = Integer.parseInt(field_numControl.getText());
             
            Iterator<Alumno> iteradorAlumnos = alumnos.iterator();
            //System.out.println(alumnos.toString());
            while(iteradorAlumnos.hasNext())
            {
                Alumno alum = iteradorAlumnos.next();
                int posicion = combo_semestre.getSelectedIndex();
                int semestreIngresado = Integer.parseInt(combo_semestre.getItemAt(posicion).toString());
                
                System.out.println(alum);
                if(alum.getNum_control() == numeroIngresado && alum.getSemestre() == semestreIngresado)
                {
                    
                    for (Integer yaVotaron1 : yaVotaron) 
                    {
                        if(yaVotaron1 == numeroIngresado)
                        {
                            //JOptionPane.showMessageDialog(null, "El alumno ya realizo su voto.");
                            JOptionPane.showMessageDialog(null,"El Alumno Ya Ejercio Su Voto.", "ATENCION...", JOptionPane.WARNING_MESSAGE);
                            desactivarBotones();
                            return;

                        }else
                        {   
                        }

                    }

                    //System.out.println("Semestre y No. De Control Correcto");

                    JOptionPane.showMessageDialog(null,"Datos Ingresados Encontrados En Los Registros", "Voto Seguro", JOptionPane.INFORMATION_MESSAGE);

                    activarBotones();

                    yaVotaron.add(numeroIngresado);

                    break;

                }/*else
                {
                    //System.out.println("Alumno no egistrado");

                    JOptionPane.showMessageDialog(null,"Numero De Control o Semestre Incorrecto.", "ATENCION...", JOptionPane.WARNING_MESSAGE);

                    //break;

                }*/
            }
        
        }else if(e.getSource() == btnA)
        {
            
            //System.out.println("Soy el boton A");
            
            numeroVotos[0] += 10;
            grafico.setValores(numeroVotos);
            
            desactivarBotones();
            
            
        }else if(e.getSource() == btnB)
        {
            //System.out.println("Soy el boton B");
            
            numeroVotos[1] += 25;
            grafico.setValores(numeroVotos);
            desactivarBotones();
            
            
        }else if(e.getSource() == btnC)
        {
            //System.out.println("Soy el boton C");
            
            numeroVotos[2] += 15;
            grafico.setValores(numeroVotos);
            desactivarBotones();
            
        
        }else if(e.getSource() == btnD)
        {
            //System.out.println("Soy el boton D");
            
            numeroVotos[3] += 20;
            grafico.setValores(numeroVotos);
            desactivarBotones();
            
            
        }else if(e.getSource() == btnFinalizar)
        {
            JOptionPane.showMessageDialog(null,"Gracias por realizar su voto.", "Sistema De Votacion.", JOptionPane.INFORMATION_MESSAGE);
            desactivarBotones();
             grafico.setValores(numeroVotos);
        }
        panelGrafica.repaint();
        
    }
}
