package mx.edu.uaz.SegundoExamenMyBatis.vistas;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

import mx.edu.uaz.SegundoExamenMyBatis.form.Pedido;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.Usuarios;

/**
 * Created by LuisEduardoLeyvaHerrera on 27/05/17.
 */
public class PedidoForm extends Pedido {
    public PedidoForm(){
        VerticalLayout vl = new VerticalLayout();
        //setMargin(true);
        setResponsive(true);
        Usuarios user = (Usuarios) VaadinSession.getCurrent().getAttribute("usuario");
      //  tabForm = new TabSheet();
        CssLayout css= new CssLayout();
        tabForm.setSizeUndefined();
        tabForm.addTab(new PedidoMedida(),"Pedido por Medida");
        tabForm.addTab(new PedidoUnidad(),"Pedido por unidad");
        addComponent(css);
        
        addComponent(tabForm);
        //vl.setComponentAlignment(tabForm, Alignment.BOTTOM_CENTER);
    }
}
