package actividad08;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Login extends JFrame {//clase Login que hereda de la clase JFrame
    private JTextField rpl_pidoUsu; //atributos de la clase Login
    private JPasswordField rpl_pidoContra;
    private JLabel rpl_debesIntroducirDatos;
    private JLabel rpl_credencialesIncorrectas;

    public Login() { //constructor Login con sus componentes
        /** características principales de la ventana **/
        setTitle("Login Principal");//titulo de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//cuando pulsamos la X la aplicación se cierra
        getContentPane().setLayout(null);

        /** etiqueta LOGIN **/
        JLabel rpl_labelLogin = new JLabel("LOGIN");//se crea la etiqueta "Login"
        rpl_labelLogin.setBounds(200, 20, 56, 15);//
        getContentPane().add(rpl_labelLogin);//se añade a la clase lo que se pase por parámetro

        /** etiqueta Nombre Usuario **/
        JLabel rpl_nomUsuario = new JLabel("Nombre Usuario");
        rpl_nomUsuario.setBounds(100, 60, 200, 25);
        getContentPane().add(rpl_nomUsuario);

        /** campo de texto **/
        rpl_pidoUsu = new JTextField();
        rpl_pidoUsu.setBounds(200, 60, 200, 25);
        getContentPane().add(rpl_pidoUsu);

        /** etiqueta Contraseña **/
        JLabel rpl_contasena = new JLabel("Contraseña");
        rpl_contasena.setBounds(100, 85, 200, 25);
        getContentPane().add(rpl_contasena);

        /** campo para contraseña **/
        rpl_pidoContra = new JPasswordField(20);
        rpl_pidoContra.setBounds(200, 85, 200, 25);
        getContentPane().add(rpl_pidoContra);

        /** boton ACEPTAR **/
        JButton rpl_aceptarLogin = new JButton("ACEPTAR");
        rpl_aceptarLogin.addActionListener(new aceptarLoginActionListener());//se pasa por parametro el objeto de la clase creada para controlar la funcion del boton
        rpl_aceptarLogin.setBounds(180, 180, 110, 25);
        getContentPane().add(rpl_aceptarLogin);

        /** etiqueta CREDENCIALES INCORRECTAS **/
        rpl_credencialesIncorrectas = new JLabel("CREDENCIALES INCORRECTAS");
        rpl_credencialesIncorrectas.setBounds(155, 140, 300, 25);//posicion
        rpl_credencialesIncorrectas.setForeground(Color.RED);//color
        rpl_credencialesIncorrectas.setVisible(false);//que esté oculta
        getContentPane().add(rpl_credencialesIncorrectas);//se añade al panel

        /** etiqueta DEBES INTRODUCIR DATOS **/
        rpl_debesIntroducirDatos = new JLabel("DEBES INTRODUCIR DATOS");
        rpl_debesIntroducirDatos.setBounds(155, 160, 300, 25);
        rpl_debesIntroducirDatos.setForeground(Color.RED);
        rpl_debesIntroducirDatos.setVisible(false);
        getContentPane().add(rpl_debesIntroducirDatos);
    }

    private class aceptarLoginActionListener implements ActionListener {//clase para controlar el boton aceptar
        @Override
        public void actionPerformed(ActionEvent e) {//método actionPerformed
            String rpl_nombreUsurio = rpl_pidoUsu.getText();//guardo en la variable el valor del textField
            String rpl_contrasena = String.valueOf(rpl_pidoContra.getPassword());//transformo el valor de la contraseña a String

            if (rpl_nombreUsurio.length() == 0 || rpl_contrasena.length() == 0) {//si no escribe nada en las variables
                rpl_debesIntroducirDatos.setVisible(true);//que el mensaje oculto se vuelva visible
            } else {//si no
                try {//prueba
                    if (logeo(rpl_nombreUsurio, rpl_contrasena)) {//si la funcion logeo con sus parámetros devuelve -> true
                        dispose();//cierra la ventana
                        new actividad08.HolaUsuario(rpl_nombreUsurio);//llamo a la clase y paso por parámetro el nombreUsuario y aparece en la siguiente
                    } else {//si no
                        rpl_credencialesIncorrectas.setVisible(true);//vuelve visible esta etiqueta que estaba oculta
                    }
                } catch (IOException fileNotFoundException) {//o captura el error
                    fileNotFoundException.printStackTrace();
                }
            }
        }
    }

    private boolean logeo(String rpl_usuario, String rpl_contra) throws IOException {
        String rpl_rutaProyecto = System.getProperty("user.dir");//ruta interna del proyecto
        String rpl_sep = File.separator;//separador
        File rpl_archivoTexto = new File(rpl_rutaProyecto + rpl_sep + "datos_login.txt");//archivo donde se guarda el usuario y contraseña

        FileReader rpl_leerArchivo = new FileReader(rpl_archivoTexto);//para abrir el archivo del fichero
        BufferedReader rpl_texto = new BufferedReader(rpl_leerArchivo);//para leer el fichero
        String rpl_linea;//variable para guardar la linea leida

        while ((rpl_linea = rpl_texto.readLine()) != null){//mientras los carácteres del archivo existan
            if (rpl_linea.equals(rpl_usuario + " " + rpl_contra)) {//si la linea es igual a eso
                rpl_leerArchivo.close();//se cierra el fichero
                return true;//devuelve true
            }
        }
        rpl_leerArchivo.close();//se cierra el archivo
        return false;//devuelve false
    }
}
