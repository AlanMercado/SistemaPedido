package mx.edu.uaz.SegundoExamenMyBatis.vistas;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.TabSheet;

public class UsuariosTab extends TabSheet{

	private static final long serialVersionUID = 1L;

	public UsuariosTab(){
		setSizeFull();
        setResponsive(true);
        
		addTab(new UsuarioForm(), "Agregar", VaadinIcons.ADD_DOCK);
		addTab(new ListaUsuariosForm(), "Usuarios", VaadinIcons.LIST);
		//addTab(new Grafica().crearGrafica(), "Gráfica", VaadinIcons.PIE_BAR_CHART);
	}

}
