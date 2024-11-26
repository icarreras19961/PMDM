package com.example.examen_ivan_carreras;

import java.util.List;

public interface PuntuacionStorage {
    public void storeScore(int score, String name, long date);
    public List<String> getScoreList(int maxNo);



}
