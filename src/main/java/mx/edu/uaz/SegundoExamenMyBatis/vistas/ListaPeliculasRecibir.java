package mx.edu.uaz.SegundoExamenMyBatis.vistas;

import java.util.List;
import java.util.Set;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import mx.edu.uaz.SegundoExamenMyBatis.accesodatos.ADPelicula;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.pelicula;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.usuario;

public class ListaPeliculasRecibir extends CssLayout{
	
	private Grid<pelicula> grid;
    private pelicula pelicula;
	private Button btnRecibir;
	private Set<pelicula> currentlyEditing;
    List<pelicula> p;
	public ListaPeliculasRecibir(){
		setSizeFull();
        setResponsive(true);
        usuario user = (usuario) VaadinSession.getCurrent().getAttribute("usuario");
        p = new ADPelicula().obtenerRentadas(user.getIdUsuario());

        grid = new Grid<pelicula>(pelicula.class);
        grid.setStyleName("mi-grid");
        grid.setItems(p);
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.setColumns("titulo");
        grid.setResponsive(true);
        grid.setWidth("100%");
        grid.addSelectionListener(new SelectionListener<pelicula>() {
			
			@Override
			public void selectionChange(SelectionEvent<pelicula> event) {
				
				if (!event.getAllSelectedItems().isEmpty()){
					pelicula = event.getFirstSelectedItem().get();
					
				}
			}
		});
        
        btnRecibir=new Button("Recibir",VaadinIcons.ALARM);

        addComponents(grid,btnRecibir);
        
        btnRecibir.addClickListener(new ClickListener(){
        	public void buttonClick(ClickEvent event) {
        		for (pelicula bugEntry : currentlyEditing) {
        			bugEntry.setEstatus("0");
        		}
        	}
        });
	}


}
