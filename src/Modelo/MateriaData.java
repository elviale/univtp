/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elviale
 */
public class MateriaData {
    private Connection connection = null;

    public MateriaData(Conexion conexion) throws ClassNotFoundException {
        try {
            connection = conexion.getConexion();
        } catch (SQLException ex) {
            System.out.println("Error al abrir al obtener la conexion");
        }
    }
    
    
    public void guardarMateria(Materia materia) throws SQLException{
    
            
            String sql = "INSERT INTO materia (nombre,responsable,periodo) VALUES ( ?, ?, ? );";
    try{
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, materia.getNombre());
            statement.setString(2, materia.getResponsable());
            statement.setString(3, materia.getPeriodo());
            
            
            statement.executeUpdate();
            
            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                materia.setId_mat(rs.getInt(1));
            } else {
               // System.out.println("No se pudo guardar materia");
            }
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al insertar materia" + ex.getMessage());
        }
    }
    
    public List<Materia> obtenerMaterias(){
        List<Materia> materias = new ArrayList<Materia>();
            

        try {
            String sql = "SELECT * FROM materia;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            Materia materia;
            while(resultSet.next()){
                materia = new Materia();
                materia.setId_mat(resultSet.getInt("id_mat"));
                materia.setNombre(resultSet.getString("nombre"));
                materia.setPeriodo(resultSet.getString("periodo"));
                materia.setResponsable(resultSet.getString("responsable"));
                
               

                materias.add(materia);
            }      
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener materia: " + ex.getMessage());
        }
        
        
        return materias;
    }
    
    public Materia buscarMateria(int id_mat){
    
        Materia materia=null;
    try {
            
            String sql = "SELECT * FROM materia WHERE id_mat =?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id_mat);
           
            
            ResultSet resultSet=statement.executeQuery();
            
            while(resultSet.next()){
                materia = new Materia();
                materia.setId_mat(resultSet.getInt("id_mat"));
                materia.setNombre(resultSet.getString("nombre"));
                materia.setPeriodo(resultSet.getString("periodo"));
                materia.setResponsable(resultSet.getString("responsable"));
               

                
            }      
            statement.close();
            
            
            
            
    
        } catch (SQLException ex) {
            System.out.println("Error al buscar la materia: " + ex.getMessage());
        }
        
        return materia;
    }
    public void borrarMateria (int id_mat){
    
        
    try {
            
            String sql = "DELETE FROM materia WHERE id_mat =?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id_mat);
           
            
            statement.executeUpdate();
            
            
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al borrar una materia: " + ex.getMessage());
        }
        
    
    
    }
    public void actualizarMateria(Materia materia){
    try {
            
            String sql = "UPDATE materia SET nombre = ? SET responsable = ? SET periodo = ? WHERE id_mat = ?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, materia.getNombre());
            statement.setString(2, materia.getResponsable());
            statement.setString(3, materia.getPeriodo());
            statement.setInt(4, materia.getId_mat());
            statement.executeUpdate();
            
          
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al actualizar: " + ex.getMessage());
        }
     
        
    
    } 
}
