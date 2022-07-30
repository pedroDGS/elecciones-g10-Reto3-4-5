/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import Clases.ClsVotante;
import Clases.ClsJdbc;
import Clases.ClsMensaje;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

/**
 *
 * @author Asus
 */
public class MdlVotante {
    ClsJdbc jdbc;
    
    public MdlVotante() {
        this.jdbc = new ClsJdbc();
        this.jdbc.CrearConexion();
    }
    
    public ClsMensaje agregarVotante(ClsVotante votante) {

        ClsMensaje mensaje = new ClsMensaje();

        try {

            String sql = "INSERT INTO tbl_votantes VALUES ( ?,?, ?, ?)";

            PreparedStatement sentencia = this.jdbc.conexion.prepareStatement(sql);
            sentencia.setString(1, votante.getNumeroDocumento());
            sentencia.setString(2, votante.getNombre());
            sentencia.setString(3, votante.getTelefono());
            sentencia.setString(4, votante.getCorreo());
            
            int resultado = sentencia.executeUpdate();
            System.out.println(resultado);
            if (resultado == 1) {
                mensaje.CambiarMensaje(mensaje.OK, "Has creado un nuevo candidato");
            } else {

                mensaje.CambiarMensaje(mensaje.ERROR, "Error no encontrado");
            }

            return mensaje;

        } catch (Exception e) {
            mensaje.CambiarMensaje(mensaje.ERROR, "Excepción: " + e.getMessage());
            return mensaje;
        }

    }
     public ClsMensaje ActualizarVotante (ClsVotante votante) {
        ClsMensaje mensaje = new ClsMensaje();

        try {

            String sql = "UPDATE tbl_votantes SET nombre = ?, telefono = ?, correo=? where id_votante = ?";

            PreparedStatement sentencia = this.jdbc.conexion.prepareStatement(sql);
            sentencia.setString(1, votante.getNombre());
            sentencia.setString(2, votante.getTelefono());
            sentencia.setString(3, votante.getCorreo());
            sentencia.setString(4, votante.getNumeroDocumento());

            int resultado = sentencia.executeUpdate();

            if (resultado == 1) {
                mensaje.CambiarMensaje(mensaje.OK, "Has editado al votante: ");
            } else {

                mensaje.CambiarMensaje(mensaje.ERROR, "Error no encontrado");
            }

            return mensaje;

        } catch (Exception e) {
            mensaje.CambiarMensaje(mensaje.ERROR, "Excepción: " + e.getMessage());
            return mensaje;
        }

    }
     
       public ClsMensaje EliminarVotante(String idVotante) {

        ClsMensaje mensaje = new ClsMensaje();

        try {

            String sql = "DELETE FROM tbl_votantes WHERE id_votante = ?";

            PreparedStatement sentencia = this.jdbc.conexion.prepareStatement(sql);
            sentencia.setString(1, idVotante);

            int resultado = sentencia.executeUpdate();

            if (resultado == 1) {
                mensaje.CambiarMensaje(mensaje.OK, "Has eliminado al candidato: " + idVotante);
            } else {

                mensaje.CambiarMensaje(mensaje.ERROR, "Error no encontrado");
            }

            return mensaje;

        } catch (Exception e) {
            mensaje.CambiarMensaje(mensaje.ERROR, "Excepción: " + e.getMessage());
            return mensaje;
        }
        
        }
   public LinkedList<ClsVotante> ObtenerVotantes() {

        try {

            LinkedList<ClsVotante> listaVotantes = new LinkedList<>();

            String sql = "SELECT * FROM tbl_votantes";
            PreparedStatement sentencia = this.jdbc.conexion.prepareStatement(sql);
            ResultSet resultados = sentencia.executeQuery();

            while (resultados.next()) {

                String numeroDocumento = resultados.getString("id_votante");
                String nombre = resultados.getString("nombre");
                String telefono = resultados.getString("telefono");
                String correo = resultados.getString("correo");
                
                ClsVotante votan = new ClsVotante( numeroDocumento, nombre, telefono, correo);

                listaVotantes.add(votan);

            }

            return listaVotantes;

        } catch (Exception e) {

            System.out.println("Uy error" + e.getMessage());
            return null;
        }

    }

    }


