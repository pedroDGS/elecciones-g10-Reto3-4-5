/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Asus
 */
public class ClsVoto {
    
    private int idEleccion;
    private String idVotante;
    private String idCandidato;
    private String fechaVoto;

    public int getIdEleccion() {
        return idEleccion;
    }

    public void setIdEleccion(int idEleccion) {
        this.idEleccion = idEleccion;
    }

    public ClsVoto(int idEleccion, String idVotante, String idCandidato) {
        this.idEleccion = idEleccion;
        this.idVotante = idVotante;
        this.idCandidato = idCandidato;
    }

    public String getIdVotante() {
        return idVotante;
    }

    public void setIdVotante(String idVotante) {
        this.idVotante = idVotante;
    }

    public String getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(String idCandidato) {
        this.idCandidato = idCandidato;
    }

    public String getFechaVoto() {
        return fechaVoto;
    }

    public void setFechaVoto(String fechaVoto) {
        this.fechaVoto = fechaVoto;
    }
    
}
