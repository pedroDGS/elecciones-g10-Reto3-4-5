/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Clases.ClsMensaje;
import Clases.ClsVotante;
import Clases.ClsVoto;
import Modelos.MdlVotar;
import java.util.LinkedList;



/**
 *
 * @author Asus
 */
public class CtlVotar {
    MdlVotar modelo;
    
    public CtlVotar() {

        this.modelo = new MdlVotar();
    }
    
    public ClsMensaje AgregarVoto(ClsVoto voto) {
            ClsMensaje respuesta = this.modelo.agregarVoto(voto);
            return respuesta;
    
    }
        public LinkedList<ClsVoto> obtenerVotos() {

        return this.modelo.obtenerVotos();

    }
    
}
