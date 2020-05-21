package com.jpdacruz.visorpeliculas.myapp;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {

    /**
     * definir contexto a nivel de la aplicacion
     */
    private static MyApp instace;

    /**
     * devolver objeto MyApp como instance
     */
    public static MyApp getInstance(){

        return instace;
    }

    /**
     * Devolver instance como Context
     */
    public static Context getContext(){

        return instace;
    }

    /**
     * definir valor inicial instance
     */
    @Override
    public void onCreate() {
        /**
         * asignar a instance el valor de la propia clase en la que nos encontramos
         */
        instace = this;
        super.onCreate();
    }

    /**
     * se agrega en Manifest
     * android:name=".myapp.MyApp"
     * apunta a la clase que hereda de aplication MyApp
     */
}
