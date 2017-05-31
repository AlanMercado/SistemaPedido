package mx.edu.uaz.SegundoExamenMyBatis.vistas;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.renderers.ButtonRenderer;

import mx.edu.uaz.SegundoExamenMyBatis.accesodatos.ADDetallerenta;
import mx.edu.uaz.SegundoExamenMyBatis.accesodatos.ADPelicula;
import mx.edu.uaz.SegundoExamenMyBatis.accesodatos.ADRenta;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.detallerenta;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.pelicula;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.renta;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.usuario;





public class ListaPeliculasForm extends CssLayout{
	
	private Grid<pelicula> grid;
	private Grid<pelicula> grid2;
	private ADPelicula adPelicula;
	private pelicula pelicula;
	private Button btnAgregar;
	private Button btnAgre;
	private Button btnEliminar;
	private Set<pelicula> currentlyEditing;
	private Set<pelicula> currentlyEditing2;
	
	@SuppressWarnings("unchecked")
	public ListaPeliculasForm(){
		setSizeFull();
		setResponsive(true);
		usuario user = (usuario) VaadinSession.getCurrent().getAttribute("usuario");
		currentlyEditing = new HashSet<>();
		currentlyEditing2= new HashSet<>();
		grid = new Grid<pelicula>(pelicula.class);
		grid2= new Grid<pelicula>(pelicula.class);
		grid.setStyleName("mi-grid");
		grid2.setStyleName("my-grid");
		adPelicula = new ADPelicula();
		grid.setItems(adPelicula.obtenerTodos());
		grid.setSelectionMode(SelectionMode.MULTI);
		grid.setColumns("titulo","duracion","clasificacion","estatus");
		grid2.setSelectionMode(SelectionMode.MULTI);
		grid2.setColumns("titulo");
		
		grid.setResponsive(true);
		grid2.setResponsive(true);
		grid.setWidth("100%");
		grid2.setWidth("100%");
		grid.addSelectionListener(new SelectionListener<pelicula>() {
			
			@Override
			public void selectionChange(SelectionEvent<pelicula> event) {
				
				if (!event.getAllSelectedItems().isEmpty()){
					pelicula = event.getFirstSelectedItem().get();
					
				}
			}
		});
		VerticalLayout vl= new VerticalLayout();
		
		btnAgregar = new Button("Rentar", VaadinIcons.ALARM);
		
		//btnAgregar.addClickListener(new EliminarUsuario());
		
		addComponents(grid, btnAgregar);
		
		
		
		btnAgregar.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				
					//adUsuario = new ADUsuario();
					boolean ok = false;
					Date dat= new Date();
					java.sql.Date fechaSQL = new java.sql.Date(dat.getTime());
					ADDetallerenta addr=new ADDetallerenta();
					ADRenta adr= new ADRenta();
					ADPelicula adp= new ADPelicula();
					currentlyEditing= grid.getSelectedItems();
					int total=0;
					for (pelicula bugEntry : currentlyEditing) {
						int costo=0;
						if(bugEntry.getEstreno()==1){
							costo=20;
						}else{
							costo=15;
						}
						total+= costo;
						Notification.show("Gracias por rentar la pelicula",bugEntry.getTitulo()+" "+" El costo es "+costo+" el total es "+total,Notification.Type.TRAY_NOTIFICATION);
						renta r= new renta();
						detallerenta dr= new detallerenta();
						r.setFecha(fechaSQL);
						r.setIdUsuario(user.getIdUsuario());
						adr.guardar(r);
						List<renta> list=adr.obtenerTodos();
						
						dr.setIdRenta(list.get(0).getIdRenta());
						dr.setIdPelicula(bugEntry.getIdPelicula());
						bugEntry.setEstatus("1");
						adp.actualizar(bugEntry);
						addr.guardar(dr);
					
						grid.setItems(adPelicula.obtenerTodos());
						//eliminador.add(bugEntry.getIdUsuario());
						
					}
					
					
					if (ok){
						
						
					}
				
			}
		});
		
		
		
		
	
	}
	public void llenaGrid(){
		grid.setItems(adPelicula.obtenerTodos());
	}
	class EliminarUsuario implements ClickListener{

		@Override
		public void buttonClick(ClickEvent event) {
			if (!grid.getSelectedItems().isEmpty()){

			}
			else
				Notification.show("Selecciona al menos un usuario para eliminar",Notification.Type.WARNING_MESSAGE);
		}
			
	}

}
