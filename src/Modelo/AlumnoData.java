/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elviale
 */
public class AlumnoData {
    private Connection con;//armamos un atributo de tipo conexion
    
    public AlumnoData(Conexion conexion) throws ClassNotFoundException {
       
        
        try {
            con = conexion.getConexion();
          //  System.out.println("Error de conexion");
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }
     public void guardarAlumno(Alumno alumno){
        try {
            
            String sql = "INSERT INTO alumno(nombre, dni, domicilio,fechaNac) VALUES ( ? , ? , ?, ? );";
//los datos me los trae el alumno por parametro(Alumno alumno)menos el id q se asigna dsd msql
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    //si quiero insertar borrar,consultar,etc,necesito la variable de tipo preparedStat.        
            ps.setString(1, alumno.getNombre());
    //obtengo un String de Alumno mediante el get nombre y asi con los demas
            ps.setInt(2, alumno.getDni());
            //le doy la posicion que esta en base de datos
            ps.setString(3, alumno.getDomicilio());
            ps.setDate(4, Date.valueOf(alumno.getFechaNac()));
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            //recorro la base de datos y ver si se genero el id

            if (rs.next()) {
                alumno.setId_alum(rs.getInt(1));//completa el id del alumno
            } else {
                System.out.println("No se pudo obtener el id luego de insertar un alumno");
            }
            ps.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al insertar un alumno: " + ex.getMessage());
        }
    }
    public List<Alumno> obtenerAlumnos(){
        List<Alumno> alumnos = new ArrayList<Alumno>();
            

        try {
            String sql = "SELECT * FROM alumno;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            Alumno alumno;
            
            while(resultSet.next()){
                alumno = new Alumno();
                alumno.setId_alum(resultSet.getInt("id_alum"));//nombre de la columna en base de datos
                alumno.setNombre(resultSet.getString("nombre"));
                alumno.setDni(resultSet.getInt("dni"));
                alumno.setDomicilio(resultSet.getString("domicilio"));
                alumno.setFechaNac(resultSet.getDate("fechaNac").toLocalDate());

                alumnos.add(alumno);
            }      
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener los alumnos: " + ex.getMessage());
        }
        
        
        return alumnos;
    } 
    public void borrarAlumno(int id_alum){
    try {
            
            String sql = "DELETE FROM alumno WHERE id_alum =?;";

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id_alum);
                      
            ps.executeUpdate();
                        
            ps.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al borrar un alumno: " + ex.getMessage());
        }
    }
     public void actualizarAlumno(Alumno alumno){
    
        try {
            
            String sql = "UPDATE alumno SET nombre = ?, dni = ? ,domicilio =?, fechaNac = ? WHERE id_alum = ?;";//update actualiza los campos de base de BD

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, alumno.getNombre());
            ps.setInt(2, alumno.getDni());
            ps.setString(3, alumno.getDomicilio());
            ps.setDate(4, Date.valueOf(alumno.getFechaNac()));
            ps.setInt(5, alumno.getId_alum());
            
            ps.executeUpdate();
            
            ResultSet rs= ps.getGeneratedKeys();
            
              if (rs.next()) {
                alumno.setId_alum(rs.getInt(1));//completa el id del alumno
            } else {
                System.out.println("No se pudo actualizar");
            }
            
            
          
            ps.close();
    
        } catch (SQLException ex) {
            System.out.println("no se pudo actualizar: " + ex.getMessage());
        }
    
    }
     public Alumno buscarAlumno(int id_alum) throws SQLException{
         
     Alumno alumno = null;
    
        String sql = "SELECT * FROM alumno WHERE id_alum =?;";

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, id_alum);
            
            
            ResultSet resultSet=ps.executeQuery();
            
            while(resultSet.next()){
                alumno = new Alumno();
                alumno.setId_alum(resultSet.getInt("id_alum"));
                alumno.setNombre(resultSet.getString("nombre"));
                alumno.setDni(resultSet.getInt("dni"));
                alumno.setDomicilio(resultSet.getString("domicilio"));
                alumno.setFechaNac(resultSet.getDate("fechaNac").toLocalDate());
                
                
            }
        
             } catch (SQLException ex) {
            System.out.println("Error al insertar un alumno: " + ex.getMessage());
        }
        
        return alumno;
     }
        
     }  
    
    


