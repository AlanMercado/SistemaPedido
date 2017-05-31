package mx.edu.uaz.SegundoExamenMyBatis.accesodatos;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.vaadin.ui.Notification;

import mx.edu.uaz.SegundoExamenMyBatis.config.PersistenciaSesion;

import mx.edu.uaz.SegundoExamenMyBatis.modelos.Pedido;



public class ADPedido {
	public ADPedido(){
		
	}
	
	public List<Pedido> obtenerTodos(){
		SqlSession sesion = PersistenciaSesion.getSqlMapper().openSession();
		List<Pedido> pedidos = null;
		try {
			pedidos = sesion.selectList("todosPedidos");
		} catch (Exception e) {
			Notification.show("No se puedieron recuperar los usuarios de la BD "+e.getMessage(), Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}
		return pedidos;
	}
	public List<Pedido> obtenerTodoUser(int id){
		SqlSession sesion = PersistenciaSesion.getSqlMapper().openSession();
		List<Pedido> pedidos = null;
		try {
			pedidos = sesion.selectList("todosPedidosUser",id);
		} catch (Exception e) {
			Notification.show("No se puedieron recuperar los usuarios de la BD "+e.getMessage(), Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}
		return pedidos;
	}
	
	public boolean actualizar(Pedido pedido){
		boolean ok = false;
		SqlSession sesion = PersistenciaSesion.getSqlMapper().openSession();
		try {
			sesion.insert("actualizarPedido", pedido);
			sesion.commit();
			ok = true;
		} catch (Exception e) {
			Notification.show("Error al Actualizar el usuario ", e.getCause().getMessage(),Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}
		return ok;
	}
	
	public boolean guardar(Pedido pedido){
		boolean ok = false;
		SqlSession sesion = PersistenciaSesion.getSqlMapper().openSession();
		try {
			sesion.insert("guardarPedido", pedido);
			sesion.commit();
			ok = true;
		} catch (Exception e) {
			Notification.show("Error al guardar el pedido ", e.getCause().getMessage(),Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}
		return ok;
	}
}


