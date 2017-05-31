package mx.edu.uaz.SegundoExamenMyBatis.vistas;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

import mx.edu.uaz.SegundoExamenMyBatis.accesodatos.ADUsuarios;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.Usuarios;
import mx.edu.uaz.SegundoExamenMyBatis.utils.cifrar;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by johanlongoria13 on 22/02/17.
 */

public class Login extends CustomLayout{
    private TextField tfEmail;
    private PasswordField pwPassword;
    private Button btnLogin,btnSignup;


    public Login(){
        crearFormulario();
    }

    public void crearFormulario(){
        setTemplateName("login");
        setStyleName("custom-principal");
        tfEmail = new TextField();
        pwPassword = new PasswordField();
        btnLogin = new Button("Login");
        btnSignup = new Button("Signup");
        tfEmail.setPlaceholder((new String (Character.toChars(VaadinIcons.USER.getCodepoint())))+"E-mail address");

        pwPassword.setPlaceholder(new String(Character.toChars(VaadinIcons.USER.getCodepoint()))+"Password");

        btnLogin.setPrimaryStyleName("btn-login");
        btnSignup.setPrimaryStyleName("btn-link");

        addComponent(tfEmail,"mail");
        addComponent(pwPassword,"contra");
        addComponent(btnLogin,"btn-login");
        addComponent(btnSignup,"signup");

        btnLogin.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                String contra = null;
                String usu = tfEmail.getValue().toString();
                cifrar c= new cifrar();
    			 contra= c.sha1(pwPassword.getValue().toString());
               


                ADUsuarios adUsuario = new ADUsuarios();

                Usuarios user = adUsuario.obtenerUser(usu, contra);



                if(user != null){
                    VaadinSession.getCurrent().setAttribute("usuario", user);
                                        	
                    if(user.getTipo().equals("admin")){
                    	UI.getCurrent().setContent(new PrincipalForm());
                    }else{
                    	UI.getCurrent().setContent(new PrincipalCliente());
                    }
                    
                }else{
                    Notification.show("Datos incorrectos","Revisa tu informacion",Notification.TYPE_ERROR_MESSAGE);

                }

            }
        });

    }

    
}
