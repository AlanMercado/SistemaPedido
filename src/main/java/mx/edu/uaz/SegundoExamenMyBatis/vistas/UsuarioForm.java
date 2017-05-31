package mx.edu.uaz.SegundoExamenMyBatis.vistas;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.LocalDateToDateConverter;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;

import mx.edu.uaz.SegundoExamenMyBatis.accesodatos.ADUsuarios;
import mx.edu.uaz.SegundoExamenMyBatis.form.UsuarioDesign;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.Usuarios;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.Image;



public class UsuarioForm extends UsuarioDesign{
	private Binder<Usuarios> binder;
	private Usuarios usuario;
	private String cad;
	private ADUsuarios adUsuario;
	private boolean edicion = false;
	public UsuarioForm(){
		usuario = new Usuarios();
		binder = new Binder<Usuarios>(Usuarios.class);
		setSizeFull();
        setResponsive(true);
		binder.setBean(usuario);
		enlazarDatos();
		
	}
	
	public void enlazarExterno(Usuarios usuario){
		this.usuario = usuario;
		binder.setBean(usuario);
		enlazarDatos();
		btnAgregar.setCaption("Actualizar");
		edicion = true;
		
	}
	public void enlazarDatos(){
		setSizeFull();
		setResponsive(true);
		binder.setBean(usuario);
		//cad=CadenaAleatoria.getCadenaAleatoria(65);
		//usuario.setCadena(cad);
		//ADEstado adEstado = new ADEstado();
		//cboEstados.setItems(adEstado.obtenerTodos());
		
		binder.forField(tfNombre)
			.asRequired("El nombre es requerido")
			.withValidator(nombre -> nombre.length() >=3, "El nombre debe contener al menos 3 caracteres")
			.bind(Usuarios::getNombre, Usuarios::setNombre);
		
		binder.forField(tfApellidos)
			.asRequired("Los apellidos son requeridos")
			.withValidator(apellidos -> apellidos.length() >=10, "Los apellidos debe contener al menos 10 caracteres")
			.bind(Usuarios::getApellidos, Usuarios::setApellidos);
	
		binder.forField(tfEmail)
			.asRequired("El e-mail es requerido")
			.withValidator(new EmailValidator("El correo {0} no tiene el formato correcto"))
			.bind(Usuarios::getEmail, Usuarios::setEmail);
		
		
		binder.forField(tfUsuario)
		.asRequired("el usuario es requerido")
		.withValidator(apellidos -> apellidos.length() >=4, "Los usuarios nesesitan tener 4 caracteres")
		.bind(Usuarios::getUsuario, Usuarios::setUsuario);

		binder.forField(pfContra)
		.asRequired("la contraseña es requerida")
		.withValidator(apellidos -> apellidos.length() >=6, "La contraseña requiere 6 caracteres por lo menos")
		.bind(Usuarios::getPassword, Usuarios::setPassword);
		
		
		binder.forField(tfTelefono)
			.withValidator(new RegexpValidator("El teléfono {0} tiene un formato inválido", "^([0-9]{3})-? ?([0-9]{3})-? ?([0-9]{2})-? ?([0-9]{2})$"))
			.bind("telefono");
		

		
		btnAgregar.addClickListener(new CrudUsuario());
		
		
		//Image img = (Image) BuscaComponentes.findComponentById(UI.getCurrent(), "imgUser");
//		Notification.show(img.getStyleName());
		//usuario.setIdUsuario(2);
		//usuario.setNombre("otro");
		setSizeFull();
		setResponsive(true);
		//SubirFoto receiver = new SubirFoto(img, usuario);
		//uploadFoto.setReceiver(receiver);
		//uploadFoto.addSucceededListener(receiver);
		/*btnAgregarEstado.addClickListener(new ClickListener() {
			
			
			public void buttonClick(ClickEvent event) {
			
				UI.getCurrent().addWindow(new Window());
				
			}
		});*/
	}
	
	class CrudUsuario implements ClickListener{
		@Override
		public void buttonClick(ClickEvent event) {
			if (binder.validate().isOk()){
				adUsuario = new ADUsuarios();
				boolean ok = false;
				usuario.setTipo("user");
				if (edicion){
					ok = adUsuario.actualizar(usuario);
					btnAgregar.setCaption("Agregar");
				
				}
				else{
					ok = adUsuario.guardar(usuario);
					((ListaUsuariosForm)((TabSheet)UsuarioForm.this.getParent()).getTab(1).getComponent()).llenaGrid();
				}
				if (ok){
					Notification.show("El usuario "+usuario.getNombre()+" se "+((edicion)?"actualizó":"agregó")+" correctamente",
							Notification.Type.TRAY_NOTIFICATION);
					if(edicion){
						((TabSheet)UsuarioForm.this.getParent()).setSelectedTab(1);
						edicion = false;
					}else{
						//EnviarCorreo enviar= new EnviarCorreo();
						//enviar.sendMail(String.valueOf(usuario.getIdUsuario()), usuario.getEmail(), cad, usuario.getNombre(), usuario.getApellidos());
					}
					usuario= new Usuarios();
					binder.setBean(usuario);
					
				}
			}
			else{
				Notification.show("Verifica tus datos",Notification.Type.WARNING_MESSAGE);
			}
		}
		
	}
	
}

