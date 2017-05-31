package mx.edu.uaz.SegundoExamenMyBatis.accesodatos;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.vaadin.ui.Notification;

import mx.edu.uaz.SegundoExamenMyBatis.config.PersistenciaSesion;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.Usuarios;

import mx.edu.uaz.SegundoExamenMyBatis.utils.cifrar;





public class ADUsuarios {
	public ADUsuarios(){
		
	}
	public boolean guardar(Usuarios usuario){
		boolean ok = false;
		SqlSession sesion = PersistenciaSesion.getSqlMapper().openSession();
		try {
			cifrar c= new cifrar();
			String cifrado= c.sha1(usuario.getPassword());
			usuario.setPassword(cifrado);
			sesion.insert("guardarUsuarios", usuario);
			sesion.commit();
			ok = true;
		} catch (Exception e) {
			Notification.show("Error al guardar el usuario", e.getCause().getMessage(),Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}
		return ok;
	}
	public List<Usuarios> obtenerTodos(){
		SqlSession sesion = PersistenciaSesion.getSqlMapper().openSession();
		List<Usuarios> estados = null;
		try {
			estados = sesion.selectList("todosUsuarios");
		} catch (Exception e) {
			Notification.show("No se puedieron recuperar los usuarios de la BD "+e.getMessage(), Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}
		return estados;
	}
	
	public Usuarios obtenerUser(String email,String key){
		SqlSession sesion = PersistenciaSesion.getSqlMapper().openSession();
		List<Usuarios> users = null;
		Usuarios user= null;
		try {
			users = sesion.selectList("todosUsuarios");
			user= new Usuarios();
			for (Usuarios ussers : users) {
				if(email.equals(ussers.getEmail())){
					if(key.equals(ussers.getPassword())){
						user=ussers;
					}
				}
			}
		} catch (Exception e) {
			Notification.show("No se puedieron recuperar los usuarios de la BD "+e.getMessage(), Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}
		return user;
	}
	
	public boolean actualizar(Usuarios usuario){
		boolean ok = false;
		SqlSession sesion = PersistenciaSesion.getSqlMapper().openSession();
		try {
			sesion.insert("actualizarUsuario", usuario);
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
	public boolean eliminar( List<Integer> usuario){
		
		boolean ok = false;
		SqlSession sesion = PersistenciaSesion.getSqlMapper().openSession();
		try {
			sesion.delete("borrarUsuarios", usuario);
			sesion.commit();
			ok = true;
		} catch (Exception e) {
			Notification.show("Error al borrar el usuario ", e.getCause().getMessage(),Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}
		return ok;
	}
}
