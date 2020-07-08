/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionBD;

import Clases.Alumno;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

/**
 *
 * @author luisa
 */
public class Conexion {
            
    private Connection connection;
    private ArrayList<Alumno> a = new ArrayList<Alumno>();
    
    private static Conexion con;
    
    public static Conexion getConexionBase(String host,String port,String database,String user,String password)
    {
        //Patron singleton
        if(con == null)
        {
            con = new Conexion();
            con.conectarBaseParametros(host, port, database, user, password);
            
        }else
        {
            System.out.println("No se puede crear otra conexion a la base de datos.");
        }
        
        return con;
    }
    
    private Conexion()
    {   
    }
    
    public void conexionBaseDeDatos()
    {
        //Registramos el driver de PostgreSQL
        try
        {
            try
            {
                Class.forName("org.postgresql.Driver");
                
            }catch(ClassNotFoundException ex)
            {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);    
            }
            
            connection = null;
        
            //Conectamos con la base de datos.
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5440/practicaDISOR", "postgres", "Luis1125");
            
            boolean valid = connection.isValid(50000);
            
            System.out.println(valid ? "TEST OK" : "TEST FAIL");
            
        }catch(java.sql.SQLException sqle)
        {
            System.out.println("Error: " + sqle);
            
        }
    }
     
    public void conectarBaseParametros(String host,String port,String database,String user,String password)
    {
        String url = "";
        try
        {
            try
            {
                Class.forName("org.postgresql.Driver");
                
            }catch(ClassNotFoundException ex)
            {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            
            connection = null;
            url = "jdbc:postgresql://"+host+":"+port+"/"+database;
            
            //Conectamos con la base de datos
            connection = DriverManager.getConnection(url, user, password);
            
            boolean valid = connection.isValid(50000);
            
            System.out.println(valid ? "TEST OK" : "TEST FAIL");
            
        }catch(java.sql.SQLException sqle)
        {
            System.out.println("Error al conectar con la base de datos de PostgreSQL ("+url+") :" + sqle);
            
        }
        
    }
    
    //--------------------------------------------------------------------------
    //METODOS GET Y SET

    public static Conexion getCon() {
        return con;
    }

    public Connection getConnection() {
        return connection;
    }
    
    
    
    
    //--------------------------------------------------------------------------
    public void obtenerDatos(Connection con)
    {
        try
        {
            String SQL = "select * from planillas.numeros_control";
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            
            while(rs.next())
            {
                System.out.println(rs.getString("num_control") 
                          + ", " + rs.getString("semestre")
                          + ", " + rs.getString("carrera"));
            }
            
            rs.close();
            stmt.close();
            
            
        }catch(java.sql.SQLException e)
        {
            e.printStackTrace();
        }
        
    }
    
    //--------------------------------------------------------------------------
    public ArrayList<Alumno> obtenerDatosControl(Connection con)
    {
        try
        {
            String SQL = "select * from planillas.numeros_control";
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            
            while(rs.next())
            {
                
                int n_control = rs.getInt("num_control");
                int sem = rs.getInt("semestre");
                String c = rs.getString("carrera");
                
                Alumno al = new Alumno(n_control, sem, c);
                
                a.add(al);
            }
            
            rs.close();
            stmt.close();   
            
            
        }catch(java.sql.SQLException e)
        {
            e.printStackTrace();
        }
        
        return a;
    }
    
    
    
}
