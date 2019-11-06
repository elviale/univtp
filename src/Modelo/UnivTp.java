/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elviale
 */
public class UnivTp {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        try {
            Conexion con=new Conexion();
            Alumno a1=new Alumno(1,"cordoba",114556,"junin1842",LocalDate.of(2005,3,18));
            AlumnoData ad=new AlumnoData(con);
            //ad.guardarAlumno(a1);
           //a1=ad.buscarAlumno(13);
           // System.out.println("mostrar alumno:"+a1.getNombre());
           // ad.borrarAlumno(7);
          // ad.actualizarAlumno(a1);
           
           
            Materia m1=new Materia ("quimica","segundo tr","viviana");
            MateriaData md=new MateriaData (con);
             md.guardarMateria(m1);
            
            // m1=md.buscarMateria(2);
           // System.out.println("mostrar materia:"+m1.getNombre());
           // md.borrarMateria(2);
          // md.actualizarMateria(m1);//probar nuevamente
            
            
            
           Cursada c1=new Cursada (a1, m1,8);
           CursadaData cd=new CursadaData (con);
           cd.guardarCursada(c1);
            //c1=ad.buscarCursada(2);
           // System.out.println("mostrar cursada:"+c1.getNombre());
           // ad.borrarCursada(7);
          // ad.actualizarCursada(c1);
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UnivTp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
