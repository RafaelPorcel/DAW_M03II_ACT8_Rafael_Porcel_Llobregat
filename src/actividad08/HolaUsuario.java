package actividad08;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class HolaUsuario extends JFrame {
    private JTextField rpl_nuevoTrabajador;
    private JCheckBox rpl_check_cantar;
    private JCheckBox rpl_check_bailar;
    private JCheckBox rpl_check_deporte;
    private JComboBox rpl_seleccion_edad;
    private ButtonGroup rpl_sexos;
    private JRadioButton rpl_masculino;
    private JRadioButton rpl_femenino;


    public HolaUsuario(String rpl_nombreUsuario){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//para que cuando se cierre la ventana acabe la aplicación with exit code
        setSize(800,500);
        setVisible(true);
        getContentPane().setLayout(null);

        JLabel rpl_holaUsuario = new JLabel("Hola usuario: " + rpl_nombreUsuario);
        rpl_holaUsuario.setBounds(350, 20, 200, 25);
        getContentPane().add(rpl_holaUsuario);

        JLabel rpl_labelNuevoTrabajador = new JLabel("Nuevo trabajador");
        rpl_labelNuevoTrabajador.setBounds(150, 50, 200, 25);
        getContentPane().add(rpl_labelNuevoTrabajador);

        rpl_nuevoTrabajador = new JTextField();
        rpl_nuevoTrabajador.setBounds(300, 50, 200, 25);
        getContentPane().add(rpl_nuevoTrabajador);

        JLabel rpl_labelAficiones = new JLabel("ELIGE AFICIONES");
        rpl_labelAficiones.setBounds(150, 100, 200, 25);
        getContentPane().add(rpl_labelAficiones);

        rpl_check_cantar = new JCheckBox("cantar");
        rpl_check_cantar.setBounds(300, 100, 100, 25);
        getContentPane().add(rpl_check_cantar);

        rpl_check_bailar = new JCheckBox("bailar");
        rpl_check_bailar.setBounds(450, 100, 100, 25);
        getContentPane().add(rpl_check_bailar);

        rpl_check_deporte = new JCheckBox("deporte");
        rpl_check_deporte.setBounds(600, 100, 100, 25);
        getContentPane().add(rpl_check_deporte);

        JLabel rpl_edad = new JLabel("EDAD");
        rpl_edad.setBounds(150, 150, 100, 25);
        getContentPane().add(rpl_edad);

        rpl_seleccion_edad = new JComboBox();
        rpl_seleccion_edad.addItem("10-15");
        rpl_seleccion_edad.addItem("15-20");
        rpl_seleccion_edad.addItem("20-25");
        rpl_seleccion_edad.addItem("25 - o más");
        rpl_seleccion_edad.setBounds(300, 150, 200, 25);
        getContentPane().add(rpl_seleccion_edad);

        JLabel rpl_sexo = new JLabel("SEXO");
        rpl_sexo.setBounds(150, 200, 100, 25);
        getContentPane().add(rpl_sexo);

        rpl_masculino = new JRadioButton("masculino");
        rpl_masculino.setBounds(300, 200, 150,25 );
        getContentPane().add(rpl_masculino);

        rpl_femenino = new JRadioButton("femenino");
        rpl_femenino.setBounds(450, 200, 150,25 );
        getContentPane().add(rpl_femenino);

        rpl_sexos = new ButtonGroup();
        rpl_sexos.add(rpl_masculino);
        rpl_sexos.add(rpl_femenino);

        JButton rpl_nuevoEmpleado = new JButton("NUEVO EMPLEADO");
        rpl_nuevoEmpleado.addActionListener(new botonNuevoEmpleado());
        rpl_nuevoEmpleado.setBounds(200, 300, 150, 25);
        getContentPane().add(rpl_nuevoEmpleado);

        JButton rpl_mostrarEmpleados = new JButton("MOSTRAR EMPLEADOS");
        rpl_mostrarEmpleados.addActionListener(new botonMostrarEmpleados());
        rpl_mostrarEmpleados.setBounds(400, 300, 250, 25);
        getContentPane().add(rpl_mostrarEmpleados);
    }

    private class botonNuevoEmpleado implements ActionListener {//clase para controlar el boton "Nuevo empleado"
        @Override
        public void actionPerformed(ActionEvent e) {//metodo
            ArrayList<Empleado> rpl_empleadosSerializados = new ArrayList<>();//arraylist para empleados (objetos) serializados
            if (rpl_nuevoTrabajador.getText().isEmpty() //si pasa alguna cosa de estas:
                    || (!rpl_check_cantar.isSelected() && !rpl_check_bailar.isSelected() && !rpl_check_deporte.isSelected())//conjunto de cosas que tienen que pasar todas
                    || rpl_seleccion_edad.getSelectedItem().toString().length() ==0
                    || rpl_sexos.getSelection() == null) {//que se muestre esta ventana con este mensaje
                JDialog rpl_dialogError = new JDialog();
                JLabel rpl_mensajeError = new JLabel("Rellena TODOS los campos");
                rpl_dialogError.add(rpl_mensajeError);
                rpl_dialogError.setSize(400, 200);
                rpl_dialogError.setVisible(true);
                //System.out.println("SE QUEDA AQUÍ EL FLUJO JODER!");
            }else{//si no
                ObjectOutputStream rpl_oos;//creo variable de la clase para serializar objetos
                ObjectInputStream rpl_ois;//creo la variable de la clase para leer objetos YA serializados y recuperarlos
                try {//prueba
                    String rpl_separador = File.separator;
                    String rpl_rutaProyecto = System.getProperty("user.dir");
                    File rpl_archivoSer = new File(rpl_rutaProyecto + rpl_separador + "datos_empleados");//apunto al fichero datos_empleados
                    if (rpl_archivoSer.exists()) {//si el archivo existe
                         try {//intenta esto
                            rpl_ois = new ObjectInputStream(new FileInputStream(rpl_archivoSer));//recupera objetos serializados
                            rpl_empleadosSerializados = (ArrayList<Empleado>) rpl_ois.readObject();//si hay guardo los objetos en el array
                            rpl_ois.close();//cerrar flujo de entrada
                         } catch (Exception errorLeer) {
                         }
                    }
                    ArrayList<String> aficiones = new ArrayList<String>();//creo un array para las aficiones
                    if (rpl_check_cantar.isSelected()) {//si esta seleccionado el check "cantar"
                        aficiones.add(rpl_check_cantar.getText());//tomo el texto y lo añado al array aficiones
                    }
                    if (rpl_check_bailar.isSelected()) {//si esta seleccionado el check "bailar"
                        aficiones.add(rpl_check_bailar.getText());//tomo el texto y lo añado al array aficiones
                    }
                    if (rpl_check_deporte.isSelected()) {//si esta seleccionado el check "deporte"
                        aficiones.add(rpl_check_deporte.getText());//tomo el texto y lo añado al array aficiones
                    }
                    String rpl_sexo;
                    if (rpl_masculino.isSelected()) {//si está seleccionado el radiobuton masculino
                        rpl_sexo = rpl_masculino.getText();//tomo el texto de rpl_masculino y lo añado al String "rpl_sexo"
                    } else {//si no
                        rpl_sexo = rpl_femenino.getText();//tomo el texto de rpl_femenino y lo añado al String
                    }

                    Empleado rpl_nuevoEmpleado = new Empleado(rpl_nuevoTrabajador.getText(),//creo objeto Empleado con los datos obtenidos
                            rpl_seleccion_edad.getSelectedItem().toString(), rpl_sexo, aficiones);
                    rpl_empleadosSerializados.add(rpl_nuevoEmpleado);//se añade al array un nuevo empleado

                    rpl_oos = new ObjectOutputStream(new FileOutputStream(rpl_archivoSer));//se crea objeto de la clase oos sobre el fichero
                    rpl_oos.writeObject(rpl_empleadosSerializados);//se llama al método writeObject pasando como parámetro el objeto a guardar-serializado
                    rpl_oos.close();//se cierra flujo de salida
                    JDialog rpl_dialogGracias = new JDialog();//hacemos que aprezca este mensaje
                    JLabel rpl_mensajeGracias = new JLabel("GRACIAS POR USAR LA APLICACIÓN");
                    rpl_dialogGracias.add(rpl_mensajeGracias);
                    rpl_dialogGracias.setSize(400, 200);
                    rpl_dialogGracias.setVisible(true);
                } catch (IOException j) {//si no que se capture el error y salte este mensaje
                    JDialog rpl_dialogError = new JDialog();
                    JLabel rpl_mensajeError = new JLabel("Error");
                    rpl_dialogError.add(rpl_mensajeError);
                    rpl_dialogError.setSize(400, 200);
                    rpl_dialogError.setVisible(true);
                    j.printStackTrace();
                }

            }
        }
    }

    private static class botonMostrarEmpleados implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String rpl_separador = File.separator;
            String rpl_rutaProyecto = System.getProperty("user.dir");
            File rpl_archivo = new File(rpl_rutaProyecto + rpl_separador + "datos_empleados");
            ArrayList<Empleado> rpl_empleadosLeidos;
            ObjectInputStream ois;
            try {
                ois = new ObjectInputStream(new FileInputStream(rpl_archivo));//recupero objetos serializados
                rpl_empleadosLeidos = (ArrayList<Empleado>) ois.readObject();//se leen los objetos y se guardan en rpl_empleadosLeidos
                ois.close();//se cierra el flujo de entrada
                String rpl_empleados = new String();//se crea una variable para usarla en el foreach, y se inicializa porque si no no deja usarla
                for (Empleado rpl_iterador : rpl_empleadosLeidos) {//foreach para recorrer el array empleados
                    rpl_empleados = rpl_empleados + rpl_iterador;
                }
                JDialog rpl_dialogEmpleados = new JDialog();//hacemos que aprezca este mensaje
                JLabel rpl_mensajeEmpleados= new JLabel("<html> Empleados<br>" + rpl_empleados + "</html>");
                rpl_dialogEmpleados.add(rpl_mensajeEmpleados);
                rpl_dialogEmpleados.setSize(600, 400);
                rpl_dialogEmpleados.setVisible(true);
            } catch (Exception e1) {
                JDialog rpl_dialogNoEmpleados = new JDialog();
                JLabel rpl_mensajeError = new JLabel("Aún NO hay empleados que mostrar");
                rpl_dialogNoEmpleados.add(rpl_mensajeError);
                rpl_dialogNoEmpleados.setSize(400, 200);
                rpl_dialogNoEmpleados.setVisible(true);
                e1.printStackTrace();
            }
        }
    }
}

