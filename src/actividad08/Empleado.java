package actividad08;

import java.io.Serializable;
import java.util.ArrayList;

public class Empleado implements Serializable {
    private String rpl_nombre;
    private String rpl_edad;
    private String rpl_sexo;
    private ArrayList<String> rpl_aficiones;

    /** CONSTRUCTOR **/
    public Empleado(String rpl_nombre, String rpl_edad, String rpl_sexo, ArrayList<String> rpl_aficiones) {
//        super();
//        this.rpl_nombre = rpl_nombre;
//        this.rpl_edad = rpl_edad;
//        this.rpl_sexo = rpl_sexo;
//        this.rpl_aficiones = rpl_aficiones;
        setRpl_nombre(rpl_nombre);
        setRpl_edad(rpl_edad);
        setRpl_sexo(rpl_sexo);
        setRpl_aficiones(rpl_aficiones);
    }

    /** GETTERS **/
    public String getRpl_nombre() {
        return rpl_nombre;
    }

    public String getRpl_edad() {
        return rpl_edad;
    }

    public String getRpl_sexo() {
        return rpl_sexo;
    }

    public ArrayList<String> getRpl_aficiones() {
        return rpl_aficiones;
    }

    /** SETTERS **/
    public void setRpl_nombre(String rpl_nombre) {
        this.rpl_nombre = rpl_nombre;
    }

    public void setRpl_edad(String rpl_edad) {
        this.rpl_edad = rpl_edad;
    }

    public void setRpl_sexo(String rpl_sexo) {
        this.rpl_sexo = rpl_sexo;
    }

    public void setRpl_aficiones(ArrayList<String> rpl_aficiones) {
        this.rpl_aficiones = rpl_aficiones;
    }

    /** TOSTRING **/
    @Override
    public String toString() {
        return "Nombre= " + rpl_nombre + ". Edad= " + rpl_edad  + ". Sexo= " + rpl_sexo  + ". Aficiones= " + rpl_aficiones + "<br>";
    }
}
