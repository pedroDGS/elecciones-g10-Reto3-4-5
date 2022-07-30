/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Clases.ClsCandidato;
import Clases.ClsMensaje;
import Clases.ClsPropuesta;
import Modelos.MdlCandidato;
import java.util.LinkedList;

/**
 *
 * @author davidperezarias
 */
public class CtlCandidato {

    MdlCandidato modelo;

    public CtlCandidato() {

        this.modelo = new MdlCandidato();

    }

    public ClsMensaje AgregarCandidato(ClsCandidato candidato) {
        ClsMensaje mensaje=ValidarDatos(candidato);
if (mensaje.getTipo().equals(mensaje.OK)) {
          ClsMensaje respuesta = this.modelo.agregarCandidato(candidato);
        return respuesta;  
        }
      
        return mensaje;
    
    }

    public ClsMensaje ActualizarCandidato(ClsCandidato candidato) {
ClsMensaje mensaje=ValidarDatos(candidato);
if (mensaje.getTipo().equals(mensaje.OK)) {
        ClsMensaje respuesta = this.modelo.ActualizarCandidato(candidato);
        return respuesta;
    }
return mensaje;
    
    }

    public ClsMensaje EliminarCandidato(String candidato) {
        ClsMensaje respuesta = this.modelo.EliminarCandidato(candidato);
        return respuesta;
    }

    public LinkedList<ClsCandidato> ObtenerCandidatos() {

        return this.modelo.ObtenerCandidatos();

    }

    public LinkedList<ClsPropuesta> ObtenerPropuestas(String idCandidato) {

        return this.modelo.ObtenerPropuestas(idCandidato);

    }

    public ClsMensaje AgregarPropuesta(ClsPropuesta propuesta) {

        ClsMensaje respuesta = this.modelo.AgregarPropuesta(propuesta);
        return respuesta;
    }

    public ClsMensaje EliminarPropuestas(String id) {
        ClsMensaje respuesta = this.modelo.EliminarPropuesta(id);
        return respuesta;
    }

    public ClsMensaje ActualizarPropuesta(ClsPropuesta propuesta) {

        ClsMensaje respuesta = this.modelo.ActualizarPropuesta(propuesta);
        return respuesta;
    }

    private static boolean isNumber(String n) {
        try {
            Double.parseDouble(n);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
    
    private ClsMensaje ValidarDatos(ClsCandidato candidato){
    ClsMensaje mensaje = new ClsMensaje();
        int espacios = 0;
        if (!isNumber(candidato.getTelefono())) {
            mensaje.CambiarMensaje(mensaje.ERROR, "el telefono solo debe ser numerico");
            return mensaje;
        }
        for (int i = 0; i < candidato.getTelefono().length(); i++) {
            // Si el carácter en [i] es un espacio (' ') aumentamos el contador 
            if (candidato.getTelefono().charAt(i) == ' ') {
                espacios++;
            }
        }
        if (espacios > 0) {
            mensaje.CambiarMensaje(mensaje.ERROR, "el telefono no puede tener espacios");
            return mensaje;
        }

        if (!isNumber(candidato.getNumeroDocumento())) {
            mensaje.CambiarMensaje(mensaje.ERROR, "la cedula solo puede contener numeros");
            return mensaje;
        }
        espacios = 0;
        for (int i = 0; i < candidato.getNumeroDocumento().length(); i++) {
            if (candidato.getNumeroDocumento().charAt(i) == ' ') {
                espacios++;
            }
        }
        if (espacios > 0) {
            mensaje.CambiarMensaje(mensaje.ERROR, "el numero de documento no puede tener espacios");
            return mensaje;
        }
        try {
            espacios = 0;
            for (int i = 0; i < candidato.getCorreo().length(); i++) {
                if (candidato.getCorreo().charAt(i) == ' ') {
                    espacios++;
                }
            }
            if (espacios > 0) {
                mensaje.CambiarMensaje(mensaje.ERROR, "el correo no puede tener espacios");
                return mensaje;
            }
            if (!candidato.getCorreo().substring(candidato.getCorreo().length() - 12, candidato.getCorreo().length()).equals("@hotmail.com") || candidato.getCorreo().substring(candidato.getCorreo().length() - 12, candidato.getCorreo().length()).equals("@gmail.com")) {
                mensaje.CambiarMensaje(mensaje.ERROR, "el dominio del correo es invalido");
                return mensaje;
            }
        } catch (Exception e) {
            mensaje.CambiarMensaje(mensaje.ERROR, "el correo es invalido");
            return mensaje;
        }
         mensaje.CambiarMensaje(mensaje.OK, "el correo es invalido");
         return mensaje;
}

}
