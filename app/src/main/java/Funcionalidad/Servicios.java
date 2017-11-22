package Funcionalidad;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import entity.Anuncio;
import entity.Notificacion;
import entity.Beca;
import ingenio.myapplication.R;

/**
 * Created by Maxi on 6/11/2017.
 */

public class Servicios {
    /*
     /getBecas con parametros
        --> Tiene de Input
            --> TIPOESTUDIANTE (tipoestudiante) STRING
            --> TIPOBECA (tipobeca)				STRING
            --> CIUDAD (nombre)					STRING
            --> ENTIDAD (nombre) 				STRING
            --> PAIS (argentina)				STRING


      /getBecasInteres

        --> Tiene de Input:
            --> idUsuario						INT
        --> Tiene de output:
            --> BECA (nombre)					STRING
            --> BECA (descripcion)				STRING
            --> TIPOESTUDIANTE (tipoestudiante)	STRING
            --> TIPOBECA (tipobeca)				STRING

      /getCiudades
        --> Tiene de output
            --> CIUDAD (nombre) 				STRING
        --> Tiene de Input
            --> PROVINCIA (nombre)				STRING

      /getProvincias
        --> Tiene de output:
            --> PROVINCIA (nombre)				STRING
        --> Tiene de input:
            --> PAIS (nombre)					STRING

      /getPaises
        --> Tiene de output:
            -->PAIS (nombre)					STRING

      /getTipoEstudiante
        --> Tiene de output:
            --> TIPOESTUDIANTE (tipoEstudiante)	STRING
     */
    /********************************************
     * Servicio que retorna las becas sin filtro
     ********************************************/
    public ArrayList<Beca> getBecasAll(){
        Beca primera = new Beca("Beca TICs",
                "El objetivo es convocar a las instituciones universitarias de gestión pública radicadas en el país, a la presentación de propuestas para la adjudicación de cupos de becas a estudiantes para la finalización de estudios de grado en carreras relacionadas con el sector TIC.",
                null,null,null,null,null,"Ingenieria","Universitario",false);
        Beca segunda = new Beca("FONCyT",
                "La Agencia Nacional de Promoción Científica y Tecnológica (ANPCyT) apoya, a través del Fondo para la Investigación Científica y Tecnológica (FonCyT), proyectos de investigación cuya finalidad sea la generación de nuevos conocimientos científicos y tecnológicos.",
                null,null,null,null,null,"Ingenieria","Universitario",false);
        ArrayList<Beca>becas = new ArrayList<Beca>();
        becas.add(primera);
        becas.add(segunda);
        return becas;
    }

    /**************************************************************************
     * Servicio que retorna las becas sugeridas en base a los datos del usuario
     **************************************************************************/
    public ArrayList<Beca> getBecasSugeridas(){
        Beca primera = new Beca("Beca TICs",
                "El objetivo es convocar a las instituciones universitarias de gestión pública radicadas en el país, a la presentación de propuestas para la adjudicación de cupos de becas a estudiantes para la finalización de estudios de grado en carreras relacionadas con el sector TIC.",
                null,null,null,null,null,"Ingenieria","Universitario",false);
        Beca segunda = new Beca("FONCyT",
                "La Agencia Nacional de Promoción Científica y Tecnológica (ANPCyT) apoya, a través del Fondo para la Investigación Científica y Tecnológica (FonCyT), proyectos de investigación cuya finalidad sea la generación de nuevos conocimientos científicos y tecnológicos.",
                null,null,null,null,null,"Ingenieria","Universitario",false);
        ArrayList<Beca>becas = new ArrayList<Beca>();
        becas.add(primera);
        becas.add(segunda);
        return becas;
    }

    public ArrayList<Beca> getSubscripciones(){
        Beca primera = new Beca("Beca TICs",
                "El objetivo es convocar a las instituciones universitarias de gestión pública radicadas en el país, a la presentación de propuestas para la adjudicación de cupos de becas a estudiantes para la finalización de estudios de grado en carreras relacionadas con el sector TIC.",
                null,null,null,null,null,"Ingenieria","Universitario",false);
        Beca segunda = new Beca("FONCyT",
                "La Agencia Nacional de Promoción Científica y Tecnológica (ANPCyT) apoya, a través del Fondo para la Investigación Científica y Tecnológica (FonCyT), proyectos de investigación cuya finalidad sea la generación de nuevos conocimientos científicos y tecnológicos.",
                null,null,null,null,null,"Ingenieria","Universitario",false);
        ArrayList<Beca>becas = new ArrayList<Beca>();
        becas.add(primera);
        becas.add(segunda);
        return becas;
    }

    public ArrayList<Notificacion> getAnunciosNotificaciones(){
        Notificacion primera = new Notificacion("Pago Beca TICs",
                "Santander Rio",
                new Date());
        Notificacion segunda = new Notificacion("Pago Beca FONCyT",
                "Ministerio de Educacion",
                new Date());
        ArrayList<Notificacion> anuncios = new ArrayList<Notificacion>();
        anuncios.add(primera);
        anuncios.add(segunda);
        return anuncios;
    }

    public ArrayList<Anuncio> getAnuncios(Context contexto){
        /*Drawable drawable = new BitmapDrawable(String.valueOf(R.drawable.banner1));
        Drawable drawable2 = new BitmapDrawable(String.valueOf(R.drawable.banner2));*/

            Bitmap b1 = BitmapFactory.decodeResource(contexto.getResources(),R.drawable.banner1);
            Bitmap b2 = BitmapFactory.decodeResource(contexto.getResources(),R.drawable.banner2);
            Anuncio a1 = new Anuncio(b1);

            Anuncio a2 = new Anuncio(b2);

            Anuncio a3 = new Anuncio(b2);

            ArrayList<Anuncio> anuncios = new ArrayList<Anuncio>();
            anuncios.add(a1);
            anuncios.add(a2);
            anuncios.add(a3);

            return anuncios;
    }


}
