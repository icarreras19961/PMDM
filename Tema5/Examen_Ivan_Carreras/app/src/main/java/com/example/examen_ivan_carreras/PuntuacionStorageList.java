package com.example.examen_ivan_carreras;

import java.util.ArrayList;
import java.util.List;

public class PuntuacionStorageList implements PuntuacionStorage {
    private List<String> puntiaciones;
    public PuntuacionStorageList(){
        puntiaciones= new ArrayList();
        puntiaciones.add("1000");
        puntiaciones.add("950");
        puntiaciones.add("900");
        puntiaciones.add("850");
        puntiaciones.add("800");
    }

    @Override
    public void storeScore(int score, String name, long date) {
        puntiaciones.add(0, score + " "+ name);
    }

    public List<String> getScoreList(int maxNo) {
        return puntiaciones;
    }


}
