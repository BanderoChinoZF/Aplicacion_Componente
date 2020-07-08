/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author luisa
 */
public class Alumno {
    
    private int num_control;
    private int semestre;
    private String carrera;
    
    public Alumno(int n, int s, String c)
    {
        this.num_control = n;
        this.semestre = s;
        this.carrera = c;
    }

    public int getNum_control() {
        return num_control;
    }

    public void setNum_control(int num_control) {
        this.num_control = num_control;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return String.valueOf(num_control).concat(" - ").concat(String.valueOf(semestre)); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
}
