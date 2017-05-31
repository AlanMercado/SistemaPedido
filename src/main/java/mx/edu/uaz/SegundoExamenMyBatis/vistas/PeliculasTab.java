package mx.edu.uaz.SegundoExamenMyBatis.vistas;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.TabSheet;



public class PeliculasTab extends TabSheet{
	
	private static final long serialVersionUID = 1L;

	public PeliculasTab(){
		addTab(new PeliculaForms());
		addTab(new ListaPeliculasRecibir());
	}

}
