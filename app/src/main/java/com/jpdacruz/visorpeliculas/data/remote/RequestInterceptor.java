package com.jpdacruz.visorpeliculas.data.remote;

import com.jpdacruz.visorpeliculas.data.Constantes;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {

        //crear objeto request y asginale el valor de la cadena que le llega -Z de esta forma ya tengo la peticion original
        Request originalRequest = chain.request();
        //obtenemos la url porque queremos concatenar el dato a la url
        HttpUrl orginalHttpUrl = originalRequest.url();

        //a esta url le vamos a agregar la apikey
        HttpUrl url = orginalHttpUrl.newBuilder().addQueryParameter("api_key", Constantes.API_KEY).build();

        //armar request -> al request original se le asigna la nueva url ya con la api key
        Request request = originalRequest.newBuilder().url(url).build();

        //retorna la cadena con el request con las modificaciones
        return chain.proceed(request);
    }
}
