package mx.edu.uaz.SegundoExamenMyBatis.vistas;

import java.util.Date;
import java.util.List;

import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.renderers.ButtonRenderer;

import mx.edu.uaz.SegundoExamenMyBatis.accesodatos.ADMateriales;
import mx.edu.uaz.SegundoExamenMyBatis.accesodatos.ADPedido;
import mx.edu.uaz.SegundoExamenMyBatis.accesodatos.ADVentas;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.Materiales;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.Pedido;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.Ventas;



public class FormTrabajos extends CssLayout{
	private Grid<Pedido> grid;
    private Pedido pelicula;
    private Ventas venta;
    private ADPedido adPedido;
    private ADVentas adVentas;
    List<Pedido> p;
    List<Materiales> listMat;
	public FormTrabajos(){
		setSizeFull();
        setResponsive(true);
        //usuario user = (usuario) VaadinSession.getCurrent().getAttribute("usuario");
        p = new ADPedido().obtenerTodos();
        
    for (Pedido component : p) {
	//	System.out.println(component.idMaterial.nombre);
		//System.out.println(component);
	}
        grid = new Grid<Pedido>(Pedido.class);
        grid.setStyleName("mi-grid");
        grid.setItems(p);
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        
        
		
		grid.setSelectionMode(SelectionMode.MULTI);
		grid.setColumns("idPedido","fecha_pedido","fecha_entrega","total","estatus","cantidad","alto","ancho");
		grid.addColumn(Pedido::getIdMaterial).setCaption("Materiales");
		grid.addColumn(Pedido::getIdUsuario).setCaption("Usuario");
		grid.addColumn(Pedido -> "Terminar Pedido",
			      new ButtonRenderer(clickEvent -> {
			    	 // btnAgregar.setCaption("Guardar");
			    	  ADMateriales ad= new ADMateriales();
			    	  Materiales material= new Materiales();
			    	  adPedido= new ADPedido();
			    	  adVentas= new ADVentas();
			    	  Pedido pedido= new Pedido();
			    	  listMat=ad.obtenerTodos();
			    	  
			    	  	venta= new Ventas();
						pelicula = (Pedido)clickEvent.getItem();
						for (Materiales component : listMat) {
							if(component.getNombre().toString().equals(pelicula.getIdMaterial())){
							//pelicula.setIdMaterial(String.valueOf(component.getIdMaterial()));	
							}
						}
						System.out.println(pelicula.getIdPedido());
						Date dat= new Date();
						java.sql.Date fechaSQL = new java.sql.Date(dat.getTime());
						pelicula.setFecha_entrega(fechaSQL);
						pelicula.setEstatus(1);
						adPedido.actualizar(pelicula);
						venta.setCosto_total(pelicula.getTotal());
						venta.setFecha_venta(pelicula.getFecha_entrega());
						venta.setIdMaterial(String.valueOf(pelicula.getIdMaterial().getIdMaterial()));
						venta.setIdPedido(pelicula.getIdPedido());
						adVentas.guardar(venta);
						p=adPedido.obtenerTodos();
						grid.setItems(p);
			    }));
		grid.setResponsive(true);
		grid.setWidth("100%");
        

        

        addComponent(grid);
	}

}
