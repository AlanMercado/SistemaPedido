package mx.edu.uaz.SegundoExamenMyBatis.vistas;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;

import mx.edu.uaz.SegundoExamenMyBatis.accesodatos.ADUsuarios;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.Usuarios;




public class ListaUsuariosForm extends CssLayout{
	private Grid<Usuarios> grid;
	private ADUsuarios adUsuario;
	private Usuarios usuario;
	private Button btnEliminar;
	
	@SuppressWarnings("unchecked")
	public ListaUsuariosForm(){
		setSizeFull();
		setResponsive(true);
		
		grid = new Grid<Usuarios>(Usuarios.class);
		grid.setStyleName("mi-grid");
		adUsuario = new ADUsuarios();
		grid.setItems(adUsuario.obtenerTodos());
		grid.setSelectionMode(SelectionMode.MULTI);
		grid.setColumns("nombre","apellidos","email","telefono","usuario");
		grid.getColumn("telefono").setCaption("Teléfono");
		
		grid.addColumn(Usuarios -> "Modificar",
			      new ButtonRenderer(clickEvent -> {
			    	  TabSheet tab = (TabSheet) this.getParent();
			    	  UsuarioForm lu = (UsuarioForm) tab.getTab(0).getComponent();
			    	  usuario = (Usuarios) clickEvent.getItem();
			    	  lu.enlazarExterno(usuario);
			    	  tab.setSelectedTab(0);
			    }));
		grid.setResponsive(true);
		grid.setWidth("100%");
		grid.addSelectionListener(new SelectionListener<Usuarios>() {
			
			@Override
			public void selectionChange(SelectionEvent<Usuarios> event) {
				
				if (!event.getAllSelectedItems().isEmpty()){
					usuario = event.getFirstSelectedItem().get();
					
				}
			}
		});
		
		btnEliminar = new Button("Eliminar", VaadinIcons.DEL);
		btnEliminar.addClickListener(new EliminarUsuario());
		
		addComponents(grid, btnEliminar);
	
	}
	public void llenaGrid(){
		grid.setItems(adUsuario.obtenerTodos());
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
			                	Set<Usuarios> usuarios = grid.getSelectedItems();
								List<Integer> users = new ArrayList<Integer>();
								for (Usuarios user : usuarios) {
									users.add(user.getIdUsuario());
								}
								ADUsuarios adUsuario = new ADUsuarios();
								boolean ok = adUsuario.eliminar(users);
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
