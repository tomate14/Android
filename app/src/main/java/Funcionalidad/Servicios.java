package Funcionalidad;

/**
 * Created by Maxi on 6/11/2017.
 */

public class Servicios {

    public String[] getHeaderBecas(){
        String [] header = {"Beca TICs","FONCyT"};
        return header;
    }

    public String[] getSubHeaderBecas(){
        String [] subheader = {"Tipo: Ingenieria \nEstudiante: Universitario" , "Tipo: Ingenieria Informatica \nEstudiante: Universitario"};
        return subheader;
    }

    public String [][] getFooterBecas(){
        String [][]footer =  {
                {"El objetivo es convocar a las instituciones universitarias de gestión pública radicadas en el país, a la presentación de propuestas para la adjudicación de cupos de becas a estudiantes para la finalización de estudios de grado en carreras relacionadas con el sector TIC."},
                {"La Agencia Nacional de Promoción Científica y Tecnológica (ANPCyT) apoya, a través del Fondo para la Investigación Científica y Tecnológica (FonCyT), proyectos de investigación cuya finalidad sea la generación de nuevos conocimientos científicos y tecnológicos."}
        };
        return footer;

    }
}
