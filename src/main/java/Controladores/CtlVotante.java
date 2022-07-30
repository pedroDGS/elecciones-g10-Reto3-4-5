/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Clases.ClsMensaje;
import Clases.ClsVotante;
import Modelos.MdlVotante;
import java.util.LinkedList;

/**
 *
 * @author Asus
 */
public class CtlVotante {

    MdlVotante modelo;

    public CtlVotante() {

        this.modelo = new MdlVotante();
    }

    public ClsMensaje AgregarVotante(ClsVotante votante) {
        ClsMensaje mensaje = ValidarDatos(votante);
        if (mensaje.getTipo().equals(mensaje.OK)) {
            ClsMensaje respuesta = this.modelo.agregarVotante(votante);
            return respuesta;
        }
        return mensaje;

    }

    public ClsMensaje ActualizarVotante(ClsVotante votante) {
        ClsMensaje mensaje = ValidarDatos(votante);
        if (mensaje.getTipo().equals(mensaje.OK)) {
            ClsMensaje respuesta = this.modelo.ActualizarVotante(votante);
            return respuesta;
        }
        return mensaje;
    }

    public ClsMensaje EliminarVotante(String votante) {
        ClsMensaje respuesta = this.modelo.EliminarVotante(votante);
        return respuesta;
    }

    public LinkedList<ClsVotante> ObtenerVotantes() {

        return this.modelo.ObtenerVotantes();

    }

    private static boolean isNumber(String n) {
        try {
            Double.parseDouble(n);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }

    }

    private ClsMensaje ValidarDatos(ClsVotante votante) {
        ClsMensaje mensaje = new ClsMensaje();
        int espacios = 0;
        if (!isNumber(votante.getTelefono())) {
            mensaje.CambiarMensaje(mensaje.ERROR, "el telefono solo debe ser numerico");
            return mensaje;
        }
        for (int i = 0; i < votante.getTelefono().length(); i++) {
            // Si el carácter en [i] es un espacio (' ') aumentamos el contador 
            if (votante.getTelefono().charAt(i) == ' ') {
                espacios++;
            }
        }
        if (espacios > 0) {
            mensaje.CambiarMensaje(mensaje.ERROR, "el telefono no puede tener espacios");
            return mensaje;
        }

        if (!isNumber(votante.getNumeroDocumento())) {
            mensaje.CambiarMensaje(mensaje.ERROR, "la cedula solo puede contener numeros");
            return mensaje;
        }
        espacios = 0;
        for (int i = 0; i < votante.getNumeroDocumento().length(); i++) {
            if (votante.getNumeroDocumento().charAt(i) == ' ') {
                espacios++;
            }
        }
        if (espacios > 0) {
            mensaje.CambiarMensaje(mensaje.ERROR, "el numero de documento no puede tener espacios");
            return mensaje;
        }
        try {
            espacios = 0;
            for (int i = 0; i < votante.getCorreo().length(); i++) {
                if (votante.getCorreo().charAt(i) == ' ') {
                    espacios++;
                }
            }
            if (espacios > 0) {
                mensaje.CambiarMensaje(mensaje.ERROR, "el correo no puede tener espacios");
                return mensaje;
            }
            if (!votante.getCorreo().substring(votante.getCorreo().length() - 12, votante.getCorreo().length()).equals("@hotmail.com") || votante.getCorreo().substring(votante.getCorreo().length() - 12, votante.getCorreo().length()).equals("@gmail.com")) {
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
