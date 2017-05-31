package mx.edu.uaz.SegundoExamenMyBatis.vistas;

import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;

import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ui.NotificationRole;
import com.vaadin.ui.Button;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;



import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import mx.edu.uaz.SegundoExamenMyBatis.accesodatos.ADUsuario;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.usuario;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;

public class Formulario extends FormLayout{
	private TextField tfUsuario;
	private PasswordField pfContra;
	private Button btnIngresar , btnRegistrar;
	private String usuario;
	private String pass;
	private usuario user;
	private ADUsuario ad;
	public Formulario(){
		crearFormularion();
		setStyleName("mifo");
	}
	
	public void crearFormularion(){
		ad= new ADUsuario();
		tfUsuario = new TextField("Correo");
		
		pfContra = new PasswordField("Contraseña");
		
		btnIngresar = new Button("Ingresar");
		
		btnRegistrar= new Button("Registrar");
		btnRegistrar.setStyleName("miboton");
		btnIngresar.setStyleName("miboton");
		HorizontalLayout h1= new HorizontalLayout();
		h1.addComponents(btnIngresar,btnRegistrar);
		h1.setSpacing(true);
		
		addComponent(tfUsuario);
		addComponent(pfContra);
		addComponent(h1);
		
		
		
		setResponsive(true);
		setMargin(true);
		setSpacing(true);
		
		btnIngresar.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				List<usuario> lis=ad.obtenerTodos();
				for (usuario component : lis) {
					if(component.getEmail().equals(tfUsuario.getValue())&&component.getPassword().equals(pfContra.getValue())){
						user=component;
					}
				}
				
				//user=ad.buscarValidarActivo(tfUsuario.getValue(), pfContra.getValue());
				if(user!=null){
					//System.out.println(Integer.parseInt(String.valueOf(user.getActivo())));
					
						Notification.show("Bienvenido al sistema","Acceso exitoso al sistema",Notification.Type.TRAY_NOTIFICATION);
						//VaadinSession.getCurrent().getSession().setMaxInactiveInterval(300); // 5 minutes
						VaadinSession.getCurrent().setAttribute("usuario", user);
						//Usuario user = (Usuario) VaadinSession.getCurrent().getAttribute("usuario");
						if(Integer.valueOf(user.getPerfil())==0){
							UI.getCurrent().setContent(new PrincipalForm());
						}else{
							
						}
						
					
				}else{
					Notification.show("Datos Incorrectos","Revisa tu informacion",Notification.Type.ERROR_MESSAGE);
				}
			/*	
				if(usuario.equals(tfUsuario.getValue().toString()) && pass.equals(pfContra.getValue().toString())){
					Notification.show("Bienvenido al sistema","Acceso exitoso al sistema Patito",Notification.Type.TRAY_NOTIFICATION);
				}else{
					Notification.show("Datos Incorrectos","Revisa tu informacion",Notification.Type.ERROR_MESSAGE);
					
				}*/
				
			}
		});
		
		btnRegistrar.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				//UI.getCurrent().setContent(new Registro());
				
			}
		});
	}
	
}
