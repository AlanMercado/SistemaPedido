package mx.edu.uaz.SegundoExamenMyBatis.vistas;

import java.util.List;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;

import mx.edu.uaz.SegundoExamenMyBatis.accesodatos.ADPelicula;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.pelicula;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.usuario;

public class ListaPeliculasPrestadas extends CssLayout{
	private Grid<pelicula> grid;
    private pelicula pelicula;
    List<pelicula> p;
	public ListaPeliculasPrestadas(){
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

        addComponent(grid);
	}

}
