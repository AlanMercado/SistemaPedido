package mx.edu.uaz.SegundoExamenMyBatis.vistas;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.renderers.ButtonRenderer;

import mx.edu.uaz.SegundoExamenMyBatis.accesodatos.ADMateriales;
import mx.edu.uaz.SegundoExamenMyBatis.form.ResponsiveForm;

import mx.edu.uaz.SegundoExamenMyBatis.modelos.Materiales;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.pelicula;





public class CrudMateriales extends ResponsiveForm{
	private Button btnEliminar;
	private Grid<Materiales> grid;
	private ADMateriales adMateriales;
	private Materiales material;
	
	
	private Binder<Materiales> binder;
	private Materiales usuario;
	private String cad;
	private ADMateriales adUsuario;
	private boolean edicion = false;
	public CrudMateriales(){
		setSizeFull();
		setResponsive(true);
		grid = new Grid<Materiales>(Materiales.class);
		grid.setStyleName("mi-grid");
		adMateriales = new ADMateriales();
		grid.setItems(adMateriales.obtenerTodos());
		grid.setSelectionMode(SelectionMode.MULTI);
		grid.setColumns("nombre","descripcion","precio","cantidad","tipo","promo","precio_promo");
		//grid.getColumn("telefono").setCaption("Teléfono");
		//grid.addColumn(Usuario::getEstado).setCaption("Estado");
		grid.addColumn(Materiales -> "Modificar",
			      new ButtonRenderer(clickEvent -> {
			    	  btnAgregar.setCaption("Guardar");
			    	  edicion=true;
						usuario = (Materiales)clickEvent.getItem();
						binder.setBean(usuario);
			    }));
		grid.setResponsive(true);
		grid.setWidth("100%");
		grid.addSelectionListener(new SelectionListener<Materiales>() {
			
			@Override
			public void selectionChange(SelectionEvent<Materiales> event) {
				
				if (!event.getAllSelectedItems().isEmpty()){
					usuario = event.getFirstSelectedItem().get();
					
				}
			}
		});
		
		btnEliminar = new Button("Eliminar", VaadinIcons.DEL);
		//btnEliminar.addClickListener(new EliminarUsuario());
		layoutGrid.addComponents(grid,btnEliminar);
		
		
		
		
		
		
		
		
		usuario = new Materiales();
		binder = new Binder<Materiales>(Materiales.class);
		binder.setBean(usuario);
		enlazarDatos();
		
	}
	
	public void enlazarExterno(Materiales usuario){
		this.usuario = usuario;
		binder.setBean(usuario);
//		enlazarDatos();
		btnAgregar.setCaption("Actualizar");
		edicion = true;
		
	}
	public void enlazarDatos(){
		
		binder.setBean(usuario);
		//cad=CadenaAleatoria.getCadenaAleatoria(65);
		//usuario.setCadena(cad);
		//ADEstado adEstado = new ADEstado();
		//cboEstados.setItems(adEstado.obtenerTodos());
		
		binder.forField(tfNombre)
			.asRequired("El nombre es requerido")
			.withValidator(nombre -> nombre.length() >=3, "El nombre debe contener al menos 3 caracteres")
			.bind(Materiales::getNombre, Materiales::setNombre);
		
		binder.forField(jTADescripcion)
			.asRequired("La descripcion es requerida")
			.withValidator(descripcion -> descripcion.length() >=5, "La descripcion debe de tener almenos 5 caracteres")
			.bind(Materiales::getDescripcion, Materiales::setDescripcion);
	
		binder.forField(tfPrecio)
			.asRequired("El precio es requerido")
			.withValidator(precio -> precio.length() >0, "La descripcion debe de tener almenos un numero")
			.withConverter(new StringToIntegerConverter("Debe ser Integer"))
			.bind(Materiales::getPrecio, Materiales::setPrecio);
		
		
		binder.forField(tfCantidad)
		.asRequired("La cantidad del material es requerida")
		.withValidator(cantidad -> cantidad.length() >0, "La cantidad minima a escribir debe de ser de 1 numero")
		.withConverter(new StringToIntegerConverter("Debe ser Integer"))
		.bind(Materiales::getCantidad, Materiales::setCantidad);

		binder.forField(tfTipo)
		.asRequired("el tipo es requerida")
		.withValidator(tipo -> tipo.length() >0, "el tipo debe de tener almenos 1 caracteres")
		.bind(Materiales::getTipo, Materiales::setTipo);
		
		
		binder.forField(cbPromo)
		.asRequired("Debes seleccionar si existe promoción o no")
		.bind("promo");
		
		binder.forField(tfPrecioPromo)
		.asRequired("el precio promo es requerido aunque le ponga 0")
		.withValidator(preciopromo -> preciopromo.length() >0, "el tipo debe de tener almenos 1 numero aunque sea 0")
		.withConverter(new StringToIntegerConverter("Debe ser Integer"))
		.bind(Materiales::getPrecio_promo, Materiales::setPrecio_promo);
		
		
		btnAgregar.addClickListener(new CrudUsuario());
		
		
		//Image img = (Image) BuscaComponentes.findComponentById(UI.getCurrent(), "imgUser");
//		Notification.show(img.getStyleName());
		//usuario.setIdUsuario(2);
		//usuario.setNombre("otro");
		//SubirFoto receiver = new SubirFoto(img, usuario);
		//uploadFoto.setReceiver(receiver);
		//uploadFoto.addSucceededListener(receiver);
		/*btnAgregarEstado.addClickListener(new ClickListener() {
			
			
			public void buttonClick(ClickEvent event) {
			
				UI.getCurrent().addWindow(new Window());
				
			}
		});*/
	}
	
	class CrudUsuario implements ClickListener{
		@Override
		public void buttonClick(ClickEvent event) {
			if (binder.validate().isOk()){
				adUsuario = new ADMateriales();
				boolean ok = false;
				if (edicion){
					ok = adUsuario.actualizar(usuario);
					btnAgregar.setCaption("Agregar");
				}
				else{
					ok = adUsuario.guardar(usuario);
					//((ListaUsuariosForm)((TabSheet)UsuarioForm.this.getParent()).getTab(1).getComponent()).llenaGrid();
				}
				if (ok){
					Notification.show("El usuario "+usuario.getNombre()+" se "+((edicion)?"actualizó":"agregó")+" correctamente",
							Notification.Type.TRAY_NOTIFICATION);
					if(edicion){
						//((TabSheet)UsuarioForm.this.getParent()).setSelectedTab(1);
						//edicion = false;
					}else{
						//EnviarCorreo enviar= new EnviarCorreo();
						//enviar.sendMail(String.valueOf(usuario.getIdUsuario()), usuario.getEmail(), cad, usuario.getNombre(), usuario.getApellidos());
					}
					usuario= new Materiales();
					binder.setBean(usuario);
					grid.setItems(adUsuario.obtenerTodos());
					
				}
			}
			else{
				Notification.show("Verifica tus datos",Notification.Type.WARNING_MESSAGE);
			}
		}
		
	}
	
	
	class EliminarUsuario implements ClickListener{

		@Override
		public void buttonClick(ClickEvent event) {
			if (!grid.getSelectedItems().isEmpty()){
				ConfirmDialog.show(
					UI.getCurrent(), 
					"Confirmar eliminación:", 
					"¿Deseas relamente eliminar los registros?",
				    "Eliminar", "Cancelar", 
				    new ConfirmDialog.Listener() {

				        public void onClose(ConfirmDialog dialog) {
			                if (dialog.isConfirmed()) {
			                	Set<Materiales> usuarios = grid.getSelectedItems();
								List<Integer> eliminar = new ArrayList<Integer>();
								for(Materiales bugEntry: usuarios){
									eliminar.add(bugEntry.getIdMaterial());
								}
								ADMateriales adUsuario = new ADMateriales();
								boolean ok = adUsuario.eliminar(eliminar);
								if (ok){
									grid.setItems(adUsuario.obtenerTodos());
									Notification.show("Registros eliminados...",Notification.Type.WARNING_MESSAGE);
								}
				              } 
				            }
				        });
			}
			else
				Notification.show("Selecciona al menos un usuario para eliminar",Notification.Type.WARNING_MESSAGE);
		}
			
	}
}
