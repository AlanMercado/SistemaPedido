package mx.edu.uaz.SegundoExamenMyBatis.vistas;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

import mx.edu.uaz.SegundoExamenMyBatis.FormUsers;
import mx.edu.uaz.SegundoExamenMyBatis.form.FormPrincipal;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.usuario;


public class PrincipalForm extends FormPrincipal{
	public PrincipalForm(){
		//usuario user = (usuario) VaadinSession.getCurrent().getAttribute("usuario");
		//labelNom.setCaption(user.getNombre()+" "+user.getApellidos());
		/*usuario.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				contenido.removeAllComponents();
				contenido.addComponent(new ListaPeliculasForm());
			
				
				
			}
		})*/;
		dbMateriales.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				content.removeAllComponents();
				content.addComponent((Component) new CrudMateriales());
			
				
			}
		});
		
rventas.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				content.removeAllComponents();
				content.addComponent((Component) new FormVentas());
			
				
			}
		});


btnTrabajos.addClickListener(new ClickListener() {
	
	@Override
	public void buttonClick(ClickEvent event) {
		content.removeAllComponents();
		content.addComponent((Component) new FormTrabajos());
	
		
	}
});
btnUsuarios.addClickListener(new ClickListener() {
	
	@Override
	public void buttonClick(ClickEvent event) {
		content.removeAllComponents();
		content.addComponent((Component) new UsuariosTab());
	
		
	}
});
btnCerrarSesion.addClickListener(new ClickListener() {
	
	@Override
	public void buttonClick(ClickEvent event) {
		UI.getCurrent().setContent(new Login());
	
		
	}
});
		
		
	}

}
