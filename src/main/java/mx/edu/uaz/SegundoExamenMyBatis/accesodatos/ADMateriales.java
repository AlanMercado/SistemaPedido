package mx.edu.uaz.SegundoExamenMyBatis.accesodatos;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.vaadin.ui.Notification;

import mx.edu.uaz.SegundoExamenMyBatis.config.PersistenciaSesion;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.Materiales;





public class ADMateriales {
public ADMateriales(){
		
	}
	
	public List<Materiales> obtenerTodos(){
		SqlSession sesion = PersistenciaSesion.getSqlMapper().openSession();
		List<Materiales> estados = null;
		try {
			estados = sesion.selectList("obtenerTodosMate");
		} catch (Exception e) {
			Notification.show("No se puedieron recuperar los usuarios de la BD "+e.getMessage(), Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}
		return estados;
	}
	
	public List<Materiales> obtenerUnidad(){
		boolean ok = false;
		List<Materiales> peliculas =null;
		String tipo="unidad";
		SqlSession sesion = PersistenciaSesion.getSqlMapper().openSession();
		try {
			peliculas = sesion.selectList("obtenUnidad",tipo);
		} catch (Exception e) {
			Notification.show("Error al guardar el usuario ", e.getCause().getMessage(),Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}
		return peliculas;
	}
	
	public List<Materiales> obtenerMate(){
		SqlSession sesion = PersistenciaSesion.getSqlMapper().openSession();
		List<Materiales> estados = null;
		try {
		String tipo="medida";
			estados = sesion.selectList("obtenerMate",tipo);
		} catch (Exception e) {
			Notification.show("No se puedieron recuperar los Materiales de la BD "+e.getMessage(), Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}
		return estados;
	}
	
	public boolean guardar(Materiales renta){
		boolean ok = false;
		SqlSession sesion = PersistenciaSesion.getSqlMapper().openSession();
		try {
			sesion.insert("guardarMateriales", renta);
			sesion.commit();
			ok = true;
		} catch (Exception e) {
			Notification.show("Error al guardar el material ", e.getCause().getMessage(),Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}
		return ok;
	}
	
	public boolean actualizar(Materiales usuario){
		boolean ok = false;
		SqlSession sesion = PersistenciaSesion.getSqlMapper().openSession();
		try {
			sesion.insert("actualizarMateriales", usuario);
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
	
public boolean eliminar( List<Integer> material){
		
		boolean ok = false;
		SqlSession sesion = PersistenciaSesion.getSqlMapper().openSession();
		try {
			sesion.delete("borrarMateriales", material);
			sesion.commit();
			ok = true;
		} catch (Exception e) {
			Notification.show("Error al borrar el material ", e.getCause().getMessage(),Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}
		return ok;
	}
}
