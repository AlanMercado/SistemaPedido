package mx.edu.uaz.SegundoExamenMyBatis.vistas;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import mx.edu.uaz.SegundoExamenMyBatis.form.Cliente;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.Usuarios;


public class PrincipalCliente extends Cliente{
	public PrincipalCliente(){
		Usuarios user = (Usuarios) VaadinSession.getCurrent().getAttribute("usuario");
		btnNombre.setValue(user.getNombre());
		pedido.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				content.removeAllComponents();
				content.addComponent((Component) new PedidoForm());
			
				
			}
		});
		
		btnCerrarSesion.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().setContent(new Login());
			
				
			}
		});
btnHistorial.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				content.removeAllComponents();
				content.addComponent((Component) new ListaPedido());
			
				
			}
		});
	}
}
