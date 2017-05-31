package mx.edu.uaz.SegundoExamenMyBatis.vistas;

import java.util.List;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;

import mx.edu.uaz.SegundoExamenMyBatis.accesodatos.ADPedido;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.Pedido;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.Usuarios;



public class ListaPedido extends CssLayout{
	private Grid<Pedido> grid;
    private Pedido pelicula;
    List<Pedido> p;
	public ListaPedido(){
		setSizeFull();
        setResponsive(true);
        Usuarios user = (Usuarios) VaadinSession.getCurrent().getAttribute("usuario");
        p = new ADPedido().obtenerTodoUser(user.getIdUsuario());

    
        grid = new Grid<Pedido>(Pedido.class);
        grid.setStyleName("mi-grid");
        grid.setItems(p);
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.setColumns("idPedido","fecha_pedido","fecha_entrega","total","estatus","cantidad","alto","ancho");
		grid.addColumn(Pedido::getIdMaterial).setCaption("Materiales");
        grid.setResponsive(true);
        grid.setWidth("100%");

        addComponent(grid);
	}

}
