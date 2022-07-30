/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import Clases.ClsCandidato;
import Clases.ClsJdbc;
import Clases.ClsMensaje;
import Clases.ClsVotante;
import Clases.ClsVoto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

/**
 *
 * @author Asus
 */
public class MdlVotar {

    ClsJdbc jdbc;

    public MdlVotar() {
        this.jdbc = new ClsJdbc();
        this.jdbc.CrearConexion();
    }

    public ClsMensaje agregarVoto(ClsVoto voto) {

        ClsMensaje mensaje = new ClsMensaje();

        try {

            String sql = "INSERT INTO tbl_voto VALUES ( ?,?, ?, now())";

            PreparedStatement sentencia = this.jdbc.conexion.prepareStatement(sql);
            sentencia.setInt(1, voto.getIdEleccion());
            sentencia.setString(2, voto.getIdVotante());
            sentencia.setString(3, voto.getIdCandidato());

            int resultado = sentencia.executeUpdate();
            System.out.println(resultado);
            if (resultado == 1) {
                mensaje.CambiarMensaje(mensaje.OK, "Se ha registrado su voto");
            } else {

                mensaje.CambiarMensaje(mensaje.ERROR, "Ya habia votado en esta eleccon");
            }

            return mensaje;

        } catch (Exception e) {
            mensaje.CambiarMensaje(mensaje.ERROR, "Excepción: " + e.getMessage());
            return mensaje;
        }
    }

    public LinkedList<ClsVoto> obtenerVotos() {

        try {
            LinkedList<ClsVoto> listavotos = new LinkedList<>();
            String sql = "SELECT * FROM tbl_voto";
            PreparedStatement sentencia = this.jdbc.conexion.prepareStatement(sql);;
            ResultSet resultados = sentencia.executeQuery();
            while (resultados.next()) {
                int idElec = resultados.getInt("id_eleccion");
                String idVotante = resultados.getString("id_votante");
                String idCandidato = resultados.getString("id_candidato");

                ClsVoto voto = new ClsVoto(idElec, idVotante, idCandidato);
                listavotos.add(voto);
            }
            return listavotos;

        } catch (Exception e) {

            System.out.println("Uy error" + e.getMessage());
            return null;
        }

    }
}
