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
public class CursadaData {
     private Connection connection;
     private Conexion conexion;
    
    public CursadaData(Conexion conexion) throws SQLException {
        
         try {
             this.conexion = conexion;    
             connection = conexion.getConexion();
         } catch (ClassNotFoundException ex) {
             System.out.println("Error al abrir al obtener la conexion");
       
         }
         
    }
    public void guardarCursada(Cursada cursada){
        try {
            
            String sql = "INSERT INTO cursada (id_alum, id_mat, nota) VALUES ( ? , ? , ? );";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, cursada.getAlumno().getId_alum());
            statement.setInt(2, cursada.getMateria().getId_mat());
            statement.setDouble(3, cursada.getNota());
            
            
            statement.executeUpdate();
            
            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                cursada.setId_curs(rs.getInt(1));
            } else {
                System.out.println("No se pudo obtener el id luego de guardar cursada");
            }
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al insertar cursada: " + ex.getMessage());
        }
    }
    
    public List<Cursada> obtenerCursadas() throws ClassNotFoundException{
        List<Cursada> cursadas = new ArrayList<Cursada>();
            
                  Cursada cursada = null;
       
            String sql = "SELECT * FROM cursada;";
        try{    
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
           
            
            while(resultSet.next()){
                cursada = new Cursada();
                Alumno a=new Alumno();
                Materia m=new Materia();
                
                cursada.setId_curs(resultSet.getInt("id_curs"));
                
              //  Alumno a=buscarAlumno(resultSet.getInt("id_alum"));
                cursada.setAlumno(buscarAlumno(resultSet.getInt("id_alum")));
                
                cursada.setAlumno(a);
                
                //Materia m=buscarMateria(resultSet.getInt("id_mat"));
                cursada.setMateria(buscarMateria(resultSet.getInt("id_mat")));
                
                cursada.setMateria(m);
                
                cursada.setNota(resultSet.getInt("nota"));
               

                cursadas.add(cursada);
            }      
            statement.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener lista de cursada: " + ex.getMessage());
        }
        
        
        return cursadas;
    }
    
    public List<Cursada> obtenerCursadasXAlumno(int id_alum)throws ClassNotFoundException{
        List<Cursada> cursadas = new ArrayList<Cursada>();
            

        try {
            String sql = "SELECT * FROM cursada WHERE id_alum = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id_alum);
            ResultSet resultSet = statement.executeQuery();
            Cursada cursada;
            while(resultSet.next()){
                cursada = new Cursada();
                cursada.setId_curs(resultSet.getInt("id_curs"));
                
                Alumno a=buscarAlumno(resultSet.getInt("id_alum"));
                cursada.setAlumno(a);
                
                Materia m=buscarMateria(resultSet.getInt("id_mat"));
                cursada.setMateria(m);
                cursada.setNota(resultSet.getInt("nota"));
               

                cursadas.add(cursada);
            }      
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener cursada del alumno: " + ex.getMessage());
        }
        
        
        return cursadas;
    }

    
    public Alumno buscarAlumno(int id_alum) throws ClassNotFoundException, SQLException{ 
    
        AlumnoData ad=new AlumnoData(conexion);
        
        return ad.buscarAlumno(id_alum);
        
    }
    
    public Materia buscarMateria(int id_mat) throws ClassNotFoundException{
    
        MateriaData md=new MateriaData(conexion);
        return md.buscarMateria(id_mat);
    
    }
    
    public List<Materia> obtenerMateriasCursadas(int id_mat){
    List<Materia> materias = new ArrayList<Materia>();
            

        try {
            String sql = "SELECT id_mat, nombre FROM cursada, materia WHERE cursada.id_mat = materia.id_mat\n" +
"and cursada.id_alum= ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id_mat);
            ResultSet resultSet = statement.executeQuery();
            Materia materia;
            while(resultSet.next()){
                materia = new Materia();
                materia.setId_mat(resultSet.getInt("id_mat"));
                materia.setNombre(resultSet.getString("nombre"));
                materias.add(materia);
            }      
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener materias cursadas: " + ex.getMessage());
        }
        
        
        return materias;
      
    }
    
    public List<Materia> obtenerMateriasNOCursadas(int id_mat){
    List<Materia> materias = new ArrayList<Materia>();
            

        try {
            String sql = "Select * from materia where id_mat not in "
                    + "(select id_mat from cursada where id_alum =?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id_mat);
            ResultSet resultSet = statement.executeQuery();
            Materia materia;
            while(resultSet.next()){
                materia = new Materia();
                materia.setId_mat(resultSet.getInt("id_mat"));
                materia.setNombre(resultSet.getString("nombre"));
                materias.add(materia);
            }      
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener las materias no cursadas: " + ex.getMessage());
        }
        
        
        return materias;
      
    }
    
    public void borrarCursadaDeUnaMateriaDeunAlumno(int id_mat ,int id_alum){
    
        try {
            
            String sql = "DELETE FROM cursada WHERE id_alum =? and id_mat =?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id_mat);
            statement.setInt(2, id_alum);
           
            
            statement.executeUpdate();
            
            
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al borrar: " + ex.getMessage());
        }
        
        
        
        
    
    }
    public void actualizarNotaCursada(int id_alum,int id_mat, int nota){
    
        try {
            
            String sql = "UPDATE cursada SET nota = ? WHERE id_alum =? and id_mat =?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,nota);
            statement.setInt(2, id_alum);
            statement.setInt(3, id_mat);
           
            
            statement.executeUpdate();
            
            
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al actualizar: " + ex.getMessage());
        }
        
        
        
        
    
    }
     
     
     
}
