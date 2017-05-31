package mx.edu.uaz.SegundoExamenMyBatis.vistas;

import com.vaadin.ui.CssLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Binder;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.renderers.ButtonRenderer;

import mx.edu.uaz.SegundoExamenMyBatis.accesodatos.ADDetallerenta;
import mx.edu.uaz.SegundoExamenMyBatis.accesodatos.ADDirector;
import mx.edu.uaz.SegundoExamenMyBatis.accesodatos.ADGenero;
import mx.edu.uaz.SegundoExamenMyBatis.accesodatos.ADPelicula;
import mx.edu.uaz.SegundoExamenMyBatis.accesodatos.ADRenta;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.detallerenta;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.director;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.genero;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.pelicula;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.renta;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.usuario;


public class PeliculaForms extends CssLayout{
    private Grid<pelicula> grid;
    private pelicula pelicula;
    List<pelicula> p;
    private TextField tfTitulo, tfClasificacion, tfestatus, tfDuracion;
    private ComboBox<genero> cboIDGenero;
    private ComboBox<director> cboDirector;
    private Button btnAgregar, btnEliminar, btnEditar;
    private Binder<pelicula> binder;
    private usuario usuario;
    private ADPelicula adPelicula;

    public PeliculaForms(){
        this.pelicula = new pelicula();
        crearFormulario();
    }

    public void crearFormulario(){
        btnAgregar = new Button("Agregar");
        tfTitulo = new TextField("Titulo");
        tfClasificacion = new TextField("Clasificacion");
        tfestatus = new TextField("Estatus");
        tfDuracion = new TextField("Duracion");
        setSizeFull();
        setResponsive(true);
        p = new ADPelicula().obtenerTodos();

        cboIDGenero = new ComboBox<genero>("Genero");
        cboIDGenero.setEmptySelectionAllowed(false);
        cboIDGenero.setRequiredIndicatorVisible(true);

        ADGenero adGenero = new ADGenero();
        cboIDGenero.setItems(adGenero.obtenerTodos());

        cboDirector =  new ComboBox<director>("Director");
        cboDirector.setEmptySelectionAllowed(false);
        cboDirector.setRequiredIndicatorVisible(true);
        ADDirector adDirector = new ADDirector();
        cboDirector.setItems(adDirector.obtenerTodos());

        grid = new Grid<pelicula>(pelicula.class);
        grid.setStyleName("mi-grid");
        grid.setItems(p);
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.setColumns("idPelicula","titulo","duracion","clasificacion");
        grid.setResponsive(true);
        grid.setWidth("100%");

        addComponents(grid,tfTitulo, tfClasificacion, tfestatus, tfDuracion,cboIDGenero,cboDirector , btnAgregar);

        binder = new Binder<pelicula>(pelicula.class);
        binder.setBean(pelicula);

        binder.forField(tfTitulo)
                .asRequired("El titulo es requerido")
                .withValidator(titulo -> titulo.length() >= 5, "El titulo debe contener al menos 5 caracteres")
                .bind("titulo");

        
        binder.forField(tfDuracion)
                .withValidator(duracion -> duracion.length() >= 2, "La duracion debe contener al menos 2 caracteres")
                .bind("duracion");

        binder.forField(tfClasificacion)
                .withValidator(clasificacion -> clasificacion.length() >= 1, "La clasificacion debe contener al menos 1 caracter")
                .bind("clasificacion");

        binder.forField(tfestatus)
                .withValidator(estatus -> estatus.length() >= 1, "La clasificacion solo puede ser entre 1 y 0")
                .bind("estatus");

        

        btnAgregar.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (binder.validate().isOk()) {
                    adPelicula = new ADPelicula();
                    boolean ok = false;
                    if (btnAgregar.getCaption().equals("Agregar")) {
                        ok = adPelicula.guardar(pelicula);
                    } else {
                        //ok = adUsuario.actualizar(usuario);
                    }
                    if (ok) {
                        if (btnAgregar.getCaption().toString().equals("Agregar")) {
                            Notification.show("Se agregó correctamente la pelicula " + pelicula.getTitulo(), Notification.Type.WARNING_MESSAGE);
                        } else {
                            //Notification.show("Se actualizó correctamente el usuario " + usuario.getNombre(), Notification.Type.WARNING_MESSAGE);
                        }
                        pelicula = new pelicula();
                        binder.setBean(pelicula);
                        tfTitulo.setCursorPosition(3);
                        grid.setItems(adPelicula.obtenerTodos());
                        btnAgregar.setCaption("Agregar");
                    }
                } else {
                    Notification.show("Los datos son incorrectos", "Verifica tu información", Notification.Type.ERROR_MESSAGE);
                }
            }
        });
    }
}
