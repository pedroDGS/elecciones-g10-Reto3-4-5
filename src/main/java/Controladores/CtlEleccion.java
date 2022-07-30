/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Clases.ClsCandidato;
import Clases.ClsEleccion;
import Clases.ClsMensaje;
import Modelos.MdlEleccion;
import java.util.LinkedList;

/**
 *
 * @author davidperezarias
 */
public class CtlEleccion {

    MdlEleccion modelo;

    public CtlEleccion() {
        this.modelo = new MdlEleccion();
    }

    public ClsMensaje AgregarEleccion(ClsEleccion eleccion) {
        
        ClsMensaje respuesta = this.modelo.AgregarEleccion(eleccion);
        return respuesta;
    }

    public LinkedList<ClsEleccion> ObtenerElecciones() {

        return this.modelo.ObtenerElecciones();

    }

    public LinkedList<ClsCandidato> ObtenerCandidatosEleccion(String idEleccion) {

        return this.modelo.ObtenerCandidatosEleccion(idEleccion);

    }

    public ClsMensaje EliminarEleccion(String id) {
        ClsMensaje respuesta = this.modelo.EliminarEleccion(id);
        return respuesta;
    }

    public ClsMensaje AsociarCandidato(String idEleccion, String idCandidato) {

        ClsMensaje respuesta = this.modelo.AsociarCandidato(idEleccion, idCandidato);
        return respuesta;

    }
    
     public ClsMensaje ActualizarElecion(ClsEleccion eleccion,int id) {

        ClsMensaje respuesta = this.modelo.ActualizarEleccion(eleccion,id);
        return respuesta;
   
    
    }
     public ClsMensaje EliminarCandidatoEleccion(String idE,String idCandidato ) {
        ClsMensaje respuesta = this.modelo.EliminarCandidatoEleccion(idE,idCandidato);
        return respuesta;
    }
 public String ObtenerGanador(int eleccion ) {
       return  this.modelo.ObtenerGanador(eleccion);
      
    }
}
