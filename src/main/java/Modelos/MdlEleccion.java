/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import Clases.ClsCandidato;
import Clases.ClsEleccion;
import Clases.ClsJdbc;
import Clases.ClsMensaje;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedList;

/**
 *
 * @author davidperezarias
 */
public class MdlEleccion {

    ClsJdbc jdbc;

    public MdlEleccion() {
        this.jdbc = new ClsJdbc();
        this.jdbc.CrearConexion();
    }

    public ClsMensaje AgregarEleccion(ClsEleccion eleccion) {

        ClsMensaje mensaje = new ClsMensaje();

        try {

            String sql = "INSERT INTO tbl_elecciones VALUES ( ?, ?, ?, now(), ?, ?, ?, NULL)";

            PreparedStatement sentencia = this.jdbc.conexion.prepareStatement(sql);
            sentencia.setInt(1, eleccion.getIdEleccion());
            sentencia.setString(2, eleccion.getNombre());
            sentencia.setString(3, eleccion.getTipo());
            sentencia.setString(4, eleccion.getFechaInicio());
            sentencia.setString(5, eleccion.getFechaFin());
            sentencia.setString(6, eleccion.getEstado());

            int resultado = sentencia.executeUpdate();

            if (resultado >= 1) {
                mensaje.CambiarMensaje(mensaje.OK, "Has creado una nueva elección");
            } else {

                mensaje.CambiarMensaje(mensaje.ERROR, "Error no encontrado");
            }

            return mensaje;

        } catch (Exception e) {
            mensaje.CambiarMensaje(mensaje.ERROR, "Excepción: " + e.getMessage());
            return mensaje;
        }

    }

    public LinkedList<ClsEleccion> ObtenerElecciones() {

        try {

            LinkedList<ClsEleccion> listaElecciones = new LinkedList<>();

            String sql = "SELECT * FROM tbl_elecciones";
            PreparedStatement sentencia = this.jdbc.conexion.prepareStatement(sql);
            ResultSet resultados = sentencia.executeQuery();

            while (resultados.next()) {

                int idEleccion = resultados.getInt("id_eleccion");
                String nombre = resultados.getString("nombre");
                String tipo = resultados.getString("tipo");
                String fechaInicio = resultados.getString("fecha_inicio");
                String fechaFin = resultados.getString("fecha_fin");
                String estado = resultados.getString("estado");
                String ganador = resultados.getString("ganador");

                ClsEleccion eleccion = new ClsEleccion(idEleccion, nombre, tipo, fechaInicio, fechaFin, estado,ganador);
                eleccion.setGanador(ganador);

                listaElecciones.add(eleccion);

            }

            return listaElecciones;

        } catch (Exception e) {

            System.out.println("Uy error" + e.getMessage());
            return null;
        }

    }

    public LinkedList<ClsCandidato> ObtenerCandidatosEleccion(String idEleccion) {

        try {

            LinkedList<ClsCandidato> listaCandidatos = new LinkedList<>();

            String sql = "SELECT tc.*  FROM tbl_candidatos_x_eleccion tcxe, "
                    + "tbl_candidatos tc WHERE tcxe.id_eleccion = ? AND "
                    + " tcxe.id_candidato = tc.id_candidato ";

            PreparedStatement sentencia = this.jdbc.conexion.prepareStatement(sql);
            sentencia.setString(1, idEleccion);
            ResultSet resultados = sentencia.executeQuery();

            while (resultados.next()) {

                String partidoPolitico = resultados.getString("partido_politico");
                String descripcion = resultados.getString("descripcion");
                String numeroDocumento = resultados.getString("id_candidato");
                String nombre = resultados.getString("nombre");
                String telefono = resultados.getString("telefono");
                String correo = resultados.getString("correo");
                String mensajeCampania = resultados.getString("mensaje_campana");
                String ciudadorigen = resultados.getString("ciudad_origen");

                ClsCandidato candi = new ClsCandidato(partidoPolitico, ciudadorigen, descripcion, mensajeCampania, numeroDocumento, nombre, telefono, correo);

                listaCandidatos.add(candi);

            }

            return listaCandidatos;

        } catch (Exception e) {

            System.out.println("Uy error" + e.getMessage());
            return null;
        }

    }

    public ClsMensaje EliminarEleccion(String id) {

        ClsMensaje mensaje = new ClsMensaje();

        try {

            String sql = "DELETE FROM tbl_elecciones WHERE id_eleccion = ?";

            PreparedStatement sentencia = this.jdbc.conexion.prepareStatement(sql);
            sentencia.setString(1, id);

            int resultado = sentencia.executeUpdate();

            if (resultado == 1) {
                mensaje.CambiarMensaje(mensaje.OK, "Has eliminado a la elección: " + id);
            } else {

                mensaje.CambiarMensaje(mensaje.ERROR, "Error no encontrado");
            }

            return mensaje;

        } catch (Exception e) {
            mensaje.CambiarMensaje(mensaje.ERROR, "Excepción: " + e.getMessage());
            return mensaje;
        }

    }

    public ClsMensaje AsociarCandidato(String idEleccion, String idCandidato) {

        ClsMensaje mensaje = new ClsMensaje();

        try {

            String sql = "INSERT INTO tbl_candidatos_x_eleccion VALUES ( ?, ?, now())";

            PreparedStatement sentencia = this.jdbc.conexion.prepareStatement(sql);
            sentencia.setString(1, idCandidato);
            sentencia.setString(2, idEleccion);

            int resultado = sentencia.executeUpdate();

            if (resultado >= 1) {
                mensaje.CambiarMensaje(mensaje.OK, "Has asociado un candidato a una elección");
            } else {

                mensaje.CambiarMensaje(mensaje.ERROR, "Error no encontrado");
            }

            return mensaje;

        } catch (SQLIntegrityConstraintViolationException e) {

            mensaje.CambiarMensaje(mensaje.ERROR, "Esté candidato ya está asociado a la elección seleccionada");

            return mensaje;
        } catch (Exception e) {
            mensaje.CambiarMensaje(mensaje.ERROR, "Excepción: " + e.getMessage());
            return mensaje;
        }

    }

    public ClsMensaje ActualizarEleccion(ClsEleccion eleccion, int id) {
        ClsMensaje mensaje = new ClsMensaje();

        try {

            String sql = "UPDATE tbl_elecciones SET id_eleccion = ?, nombre = ?, tipo = ?, fecha_inicio=?,fecha_fin=?,estado=?,ganador=? where id_eleccion = ?";

            PreparedStatement sentencia = this.jdbc.conexion.prepareStatement(sql);
            sentencia.setInt(1, eleccion.getIdEleccion());
            sentencia.setString(2, eleccion.getNombre());
            sentencia.setString(3, eleccion.getTipo());
            sentencia.setString(4, eleccion.getFechaInicio());
            sentencia.setString(5, eleccion.getFechaFin());
            sentencia.setString(6, eleccion.getEstado());
            sentencia.setString(7, eleccion.getGanador());
            sentencia.setInt(8, id);

            int resultado = sentencia.executeUpdate();

            if (resultado == 1) {
                mensaje.CambiarMensaje(mensaje.OK, "Has editado la eleccion: ");
            } else {

                mensaje.CambiarMensaje(mensaje.ERROR, "Error no encontrado");
            }

            return mensaje;

        } catch (Exception e) {
            mensaje.CambiarMensaje(mensaje.ERROR, "Excepción: " + e.getMessage());
            return mensaje;
        }

    }

    public ClsMensaje EliminarCandidatoEleccion(String idE, String idCandidato) {

        ClsMensaje mensaje = new ClsMensaje();

        try {

            String sql = "DELETE FROM tbl_candidatos_x_eleccion WHERE id_candidato = ? AND id_eleccion=?";

            PreparedStatement sentencia = this.jdbc.conexion.prepareStatement(sql);
            sentencia.setString(1, idCandidato);
            sentencia.setString(2, idE);

            int resultado = sentencia.executeUpdate();

            if (resultado == 1) {
                mensaje.CambiarMensaje(mensaje.OK, "Has deliminado el candidato: " + idCandidato + " de la eleccion");
            } else {

                mensaje.CambiarMensaje(mensaje.ERROR, "Error no encontrado");
            }

            return mensaje;

        } catch (Exception e) {
            mensaje.CambiarMensaje(mensaje.ERROR, "Excepción: " + e.getMessage());
            return mensaje;
        }

    }

    public String ObtenerGanador(int eleccion) {

        try {
            int ganador = 0;
            String idGanador = null;
            String sql = "select id_candidato, count(id_candidato) AS conteo  from tbl_voto   where id_eleccion = ? GROUP BY id_candidato";

            PreparedStatement sentencia = this.jdbc.conexion.prepareStatement(sql);
            sentencia.setInt(1, eleccion);

            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                String idEleccion = resultado.getString("id_candidato");
                int votos = resultado.getInt("conteo");
                if (ganador < votos) {
                    ganador = votos;
                    idGanador = idEleccion;
                }

            }
            return idGanador;


        } catch (Exception e) {
            return null;
        }

    }

}
