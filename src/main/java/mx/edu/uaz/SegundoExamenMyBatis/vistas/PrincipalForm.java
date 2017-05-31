package mx.edu.uaz.SegundoExamenMyBatis.vistas;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

import mx.edu.uaz.SegundoExamenMyBatis.FormUsers;

import mx.edu.uaz.SegundoExamenMyBatis.modelos.usuario;

public class PrincipalForm extends FormUsers{
	public PrincipalForm(){
		usuario user = (usuario) VaadinSession.getCurrent().getAttribute("usuario");
		labelNom.setCaption(user.getNombre()+" "+user.getApellidos());
		usuario.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				contenido.removeAllComponents();
				contenido.addComponent(new ListaPeliculasForm());
			
				
			}
		});
		reports.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				contenido.removeAllComponents();
				contenido.addComponent((Component) new ListaPeliculasPrestadas());
			
				
			}
		});
	}

}
