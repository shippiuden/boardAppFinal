package com.edwinzapata.boardapp;

import java.util.Timer;
import java.util.TimerTask;

import java.util.Timer;
import java.util.TimerTask;

public class Tiempo  {

    private Timer timer = new Timer();
    private Double seconds=0.0;
    private Double secondsConverterOk;

    //Clase interna que funciona como contador
    class Contador extends TimerTask {
        public void run() {
            seconds++;
            secondsConverterOk=seconds/100;
            //System.out.println("segundo: " + secondsConverterOk);
        }
    }
    //Crea un timer, inicia segundos a 0 y comienza a contar
    public void Contar()
    {
        this.seconds=0.0;
        timer = new Timer();
        timer.schedule(new Contador(), 0, 10);
    }
    //Detiene el contador
    public void Detener() {
        timer.cancel();
    }
    //Metodo que retorna los segundos transcurridos
    public Double getSegundos()
    {
        return this.secondsConverterOk;
    }

}