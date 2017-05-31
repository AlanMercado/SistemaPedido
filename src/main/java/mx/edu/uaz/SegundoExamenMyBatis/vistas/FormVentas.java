package mx.edu.uaz.SegundoExamenMyBatis.vistas;

import java.util.List;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.renderers.ButtonRenderer;


import mx.edu.uaz.SegundoExamenMyBatis.accesodatos.ADVentas;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.Pedido;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.Ventas;




public class FormVentas extends CssLayout{
	private Grid<Ventas> grid;
    private Ventas pelicula;
    List<Ventas> p;
	public FormVentas(){
		setSizeFull();
        setResponsive(true);
        //usuario user = (usuario) VaadinSession.getCurrent().getAttribute("usuario");
        p = new ADVentas().obtenerVentas();

    for (Ventas component : p) {
	//	System.out.println(component.idMaterial.nombre);
		//System.out.println(component);
	}
        grid = new Grid<Ventas>(Ventas.class);
        grid.setStyleName("mi-grid");
        grid.setItems(p);
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        
        
		
		grid.setSelectionMode(SelectionMode.MULTI);
		grid.setColumns("idVenta","costo_total","fecha_venta");
		grid.addColumn(Ventas::getIdMaterial).setCaption("Materiales");
		grid.addColumn(Ventas::getIdPedido).setCaption("Pedido");

		grid.setResponsive(true);
		grid.setWidth("100%");
        
        
        

        addComponent(grid);
	}


}
