package mx.edu.uaz.SegundoExamenMyBatis.vistas;

import java.util.Date;
import java.util.List;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import mx.edu.uaz.SegundoExamenMyBatis.accesodatos.ADMateriales;
import mx.edu.uaz.SegundoExamenMyBatis.accesodatos.ADPedido;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.Materiales;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.Pedido;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.Usuarios;





public class PedidoUnidad extends VerticalLayout {
    private ComboBox<Materiales> cboProducto;
    private TextField tfCantidad;
    private Button btnPedido, btnCotizar;
    private Binder<Pedido> binder;
	private Pedido pedido;
	private ADPedido adPedido;
	List<Materiales> listMat;
	private Usuarios user=null;

    public PedidoUnidad(){
    	user = (Usuarios) VaadinSession.getCurrent().getAttribute("usuario");
        crear();
    }

    public void crear(){
    	adPedido=new ADPedido();
        HorizontalLayout hoZ1 = new HorizontalLayout();
        HorizontalLayout hoZ2 = new HorizontalLayout();

        cboProducto = new ComboBox<Materiales>("Producto");
        cboProducto.setEmptySelectionAllowed(false);
		cboProducto.setRequiredIndicatorVisible(true);
		
		ADMateriales adMate = new ADMateriales();
		cboProducto.setItems(adMate.obtenerUnidad());
        tfCantidad = new TextField("Cantidad");
        
        
        pedido=new Pedido();
        binder = new Binder<Pedido>(Pedido.class);
		binder.setBean(pedido);
		
		binder.forField(tfCantidad)
			.asRequired("El nombre es requerido")
			.withValidator(nombre -> nombre.length() >0, "El nombre debe contener al menos 1 caracteres")
			.withConverter(new StringToIntegerConverter("Debe ser Integer"))
			.bind(Pedido::getCantidad, Pedido::setCantidad);
		
		
		binder.forField(cboProducto)
			.asRequired("Debes seleccionar un estado")
			.bind("idMaterial");
        
        btnPedido = new Button("Realizar Pedido");
        btnCotizar = new Button("Cotizar");
		btnPedido.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if (binder.validate().isOk()){
					adPedido = new ADPedido();
					boolean ok = false;
					Date dat= new Date();
					java.sql.Date fechaSQL = new java.sql.Date(dat.getTime());
					pedido.setEstatus(0);
					pedido.setFecha_pedido(fechaSQL);
					pedido.setIdUsuario(String.valueOf(user.getIdUsuario()));
					ADMateriales adMate= new ADMateriales();
					Materiales mat= new Materiales();
					listMat=adMate.obtenerUnidad();
					for (Materiales component : listMat) {
						if(component.getNombre().toString().equals(pedido.getIdMaterial().getNombre())){
							mat=component;
							
						pedido.setTotal(component.getPrecio()*pedido.getCantidad());
						}
					}
					
						ok = adPedido.guardar(pedido);
					
					
					
					if (ok){
						//if(btnAgregar.getCaption().toString().equals("Agregar")){
							Notification.show("Se agregó correctamente el pedido su total fue de "+ pedido.getTotal(),Notification.Type.WARNING_MESSAGE);
							mat.setCantidad(mat.getCantidad()-1);
							adMate.actualizar(mat);
							//	}else{
						//	Notification.show("Se agregó Actualizo el usuario "+ usuario.getNombre(),Notification.Type.WARNING_MESSAGE);
						//}
						
						pedido = new Pedido();
						binder.setBean(pedido);
						tfCantidad.setCursorPosition(3);
						//grid.setItems(adUsuario.obtenerTodos());
						//btnAgregar.setCaption("Agregar");
						//tfNombre.setEnabled(true);
					}
				}
				else{
					Notification.show("Los datos son incorrectos","Verifica tu información",Notification.Type.ERROR_MESSAGE);
				}
			}
		});
		btnCotizar.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				adPedido = new ADPedido();
				boolean ok = false;
				Date dat= new Date();
				java.sql.Date fechaSQL = new java.sql.Date(dat.getTime());
				pedido.setEstatus(0);
				pedido.setFecha_pedido(fechaSQL);
				pedido.setIdUsuario("1");
				ADMateriales adMate= new ADMateriales();
				Materiales mat= new Materiales();
				listMat=adMate.obtenerUnidad();
				for (Materiales component : listMat) {
					if(component.getNombre().toString().equals(pedido.getIdMaterial().getNombre())){
						mat=component;
						
					pedido.setTotal(component.getPrecio()*pedido.getCantidad());
					}
				}
				Notification.show("Su cotizacion es de "+ pedido.getTotal(),Notification.Type.WARNING_MESSAGE);
				
			}
		});
		
        hoZ1.addComponents(tfCantidad);
        hoZ1.setSpacing(true);
        hoZ1.setResponsive(true);

        hoZ2.addComponents(btnPedido, btnCotizar);
        hoZ2.setSpacing(true);
        hoZ2.setResponsive(true);

        addComponents(cboProducto,hoZ1,hoZ2);
        setMargin(true);
        setSpacing(true);
        setResponsive(true);


    }
}
