/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class Process {
    private float process;
    private int idv, idu, id;
    public Process()
    {}
    public Process(int id, int idv, int idu, float process)
    {
        this.id = id;
        this.idv = idv;
        this.idu = idu;
        this.process = process;
    }

    public float getProcess() {
        return process;
    }

    public void setProcess(float process) {
        this.process = process;
    }

    public int getIdv() {
        return idv;
    }

    public void setIdv(int idv) {
        this.idv = idv;
    }

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
