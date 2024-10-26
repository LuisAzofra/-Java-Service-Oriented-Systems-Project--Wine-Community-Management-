/**

 /**

 /**

 /**

 * WineSocialUPMSkeleton.java

 *

 * This file was auto-generated from WSDL

 * by the Apache Axis2 version: 1.6.2 Built on : Apr 17, 2012 (05:33:49 IST)

 */

package es.upm.etsiinf.sos;



import java.util.ArrayList;

import java.util.Collections;

import java.util.HashMap;

import java.util.HashSet;

import java.util.List;

import java.util.Map;

import java.util.Set;

import java.util.UUID;



import javax.annotation.PostConstruct;



import es.upm.etsiinf.sos.AddFollowerResponse;

import es.upm.etsiinf.sos.AddUser;

import es.upm.etsiinf.sos.GetMyFollowersResponse;

import es.upm.etsiinf.sos.UnfollowResponse;

import es.upm.etsiinf.sos.model.xsd.AddUserResponse;

import es.upm.etsiinf.sos.model.xsd.FollowerList;

import es.upm.etsiinf.sos.model.xsd.Response;

import es.upm.etsiinf.sos.model.xsd.User;

import es.upm.etsiinf.sos.model.xsd.Username;

import es.upm.etsiinf.sos.model.xsd.Wine;

import es.upm.etsiinf.sos.model.xsd.WineList;

import es.upm.etsiinf.sos.model.xsd.WinesRatedList;

import es.upm.fi.sos.t3.backend.UPMAuthenticationAuthorizationWSSkeletonStub;

import es.upm.fi.sos.t3.backend.UPMAuthenticationAuthorizationWSSkeletonStub.AddUserResponseBackEnd;

import es.upm.fi.sos.t3.backend.UPMAuthenticationAuthorizationWSSkeletonStub.ChangePassword;

import es.upm.fi.sos.t3.backend.UPMAuthenticationAuthorizationWSSkeletonStub.ChangePasswordBackEnd;

import es.upm.fi.sos.t3.backend.UPMAuthenticationAuthorizationWSSkeletonStub.ChangePasswordResponseE;

import es.upm.fi.sos.t3.backend.UPMAuthenticationAuthorizationWSSkeletonStub.ExistUser;

import es.upm.fi.sos.t3.backend.UPMAuthenticationAuthorizationWSSkeletonStub.ExistUserResponse;

import es.upm.fi.sos.t3.backend.UPMAuthenticationAuthorizationWSSkeletonStub.ExistUserResponseE;

import es.upm.fi.sos.t3.backend.UPMAuthenticationAuthorizationWSSkeletonStub.Login;

import es.upm.fi.sos.t3.backend.UPMAuthenticationAuthorizationWSSkeletonStub.LoginBackEnd;

import es.upm.fi.sos.t3.backend.UPMAuthenticationAuthorizationWSSkeletonStub.LoginResponse;

import es.upm.fi.sos.t3.backend.UPMAuthenticationAuthorizationWSSkeletonStub.LoginResponseBackEnd;

import es.upm.fi.sos.t3.backend.UPMAuthenticationAuthorizationWSSkeletonStub.UserBackEnd;



/**

 * WineSocialUPMSkeleton java skeleton for the axisService

 */

public class WineSocialUPMSkeleton{



	private static Map<String,ArrayList<String>> followers = new HashMap<String,ArrayList<String>>();

	private static Map<String,ArrayList<Wine>> vinos = new HashMap<String,ArrayList<Wine>>();

	private User usuarioActivo;

	private static Map<String, Integer> userRatedWines = new HashMap<>();

	private static Map<String, Map<String, Integer>> followerRatedWines = new HashMap<>();

	// Cliente para el servicio UPMAuthenticationAuthorization



	public WineSocialUPMSkeleton() {

		usuarioActivo = new User();

		User admin = new User();

		admin.setName("admin");

		admin.setPwd("admin");

		usuarioActivo.setName("");

		usuarioActivo.setPwd("");

	}



	/**

	 * Auto generated method signature

	 *

	 * @param getMyFollowers

	 * @return getMyFollowersResponse

	 */



	public es.upm.etsiinf.sos.GetMyFollowersResponse getMyFollowers(es.upm.etsiinf.sos.GetMyFollowers getMyFollowers){

		GetMyFollowersResponse response = new GetMyFollowersResponse();

		FollowerList list = new FollowerList();

		if(usuarioActivo.getName() == null || usuarioActivo.getPwd() == null) {

			list.setResult(false);

			list.setFollowers(new String[0]);

			response.set_return(list);

			return response;

		}

		if(followers.containsKey(usuarioActivo.getName())) {

			ArrayList<String> seguidores = followers.get(usuarioActivo.getName());

			String[] res = new String[seguidores.size()];

			for(int i = 0;i < seguidores.size();i++) {

				res[i] = seguidores.get(i);

			}

			list.setResult(true);

			list.setFollowers(res);

			response.set_return(list);

		}else {

			list.setFollowers(new String[0]);

			list.setResult(false);

			response.set_return(list);

		}

		return response;

	}





	/**

	 * Auto generated method signature

	 *

	 * @param removeUser

	 * @return removeUserResponse

	 */



	public es.upm.etsiinf.sos.RemoveUserResponse removeUser(es.upm.etsiinf.sos.RemoveUser removeUser) {

		es.upm.etsiinf.sos.RemoveUserResponse response = new es.upm.etsiinf.sos.RemoveUserResponse();

		es.upm.etsiinf.sos.model.xsd.Response res = new es.upm.etsiinf.sos.model.xsd.Response();


		// Verificar si la solicitud de removeUser es nula

		if (removeUser == null || removeUser.getArgs0() == null || removeUser.getArgs0().getUsername() == null) {

			res.setResponse(false);

			response.set_return(res);

			return response;

		}


		String usernameToRemove = removeUser.getArgs0().getUsername();



		// Verificar si el usuario está autenticado

		if (!isAuthenticated()) {

			res.setResponse(false);

			response.set_return(res);

			return response;

		}


		// Verificar si el usuario es el administrador o el mismo usuario que se quiere eliminar

		if (!usuarioActivo.getName().equals("admin") && !usuarioActivo.getName().equals(usernameToRemove)) {

			res.setResponse(false);

			response.set_return(res);

			return response;

		}


		try {

			// Llamar a la operación removeUser del servicio UPMAuthenticationAuthorization

			String serviceURL = "http://porter.dia.fi.upm.es:8080/axis2/services/UPMAuthenticationAuthorizationWSSkeleton";

			UPMAuthenticationAuthorizationWSSkeletonStub stub = new UPMAuthenticationAuthorizationWSSkeletonStub(serviceURL);



			// Crear un objeto RemoveUser

			UPMAuthenticationAuthorizationWSSkeletonStub.RemoveUser removeUserObj = new UPMAuthenticationAuthorizationWSSkeletonStub.RemoveUser();

			removeUserObj.setName(usernameToRemove);



			// Establecer el objeto RemoveUser en RemoveUserE

			UPMAuthenticationAuthorizationWSSkeletonStub.RemoveUserE userToRemove = new UPMAuthenticationAuthorizationWSSkeletonStub.RemoveUserE();

			userToRemove.setRemoveUser(removeUserObj);


			// Invocar al servicio para eliminar al usuario

			UPMAuthenticationAuthorizationWSSkeletonStub.RemoveUserResponseE backendResponse = stub.removeUser(userToRemove);


			if (backendResponse.get_return().getResult()) {


				// Si la operación se realizó correctamente, eliminar al usuario del mapa de seguidores

				followers.remove(usernameToRemove);
				
				 // Si el usuario eliminado es el mismo que el usuario actual, reiniciar el objeto usuarioActivo
	            if (usernameToRemove.equals(usuarioActivo.getName())) {
	                usuarioActivo = new User(); // Reiniciar el objeto usuarioActivo (Se queda sin la informacion que tuviese guardada en el)
	            }

				usuarioActivo.setName("");

				usuarioActivo.setPwd("");

				res.setResponse(true);

			} else {

				// Si la operación no se realizó correctamente, configurar la respuesta como falsa

				res.setResponse(false);

			}

		} catch (Exception e) {

			// En caso de error, configurar la respuesta como falsa

			res.setResponse(false);

			e.printStackTrace();

		}



		response.set_return(res);

		return response;

	}











	/**

	 * Auto generated method signature

	 *

	 * @param addFollower

	 * @return addFollowerResponse

	 */



	public es.upm.etsiinf.sos.AddFollowerResponse addFollower(es.upm.etsiinf.sos.AddFollower addFollower){

		AddFollowerResponse response = new AddFollowerResponse();

		Response res = new Response();

		if(addFollower == null) {

			res.setResponse(false);

			response.set_return(res);

			return response;

		}

		if(usuarioActivo.getName() == null || usuarioActivo.getPwd() == null) {

			res.setResponse(false);

			response.set_return(res);

			return response;

		}

		try {

			// stub para el envio al servicio 

			String serviceURL = "http://porter.dia.fi.upm.es:8080/axis2/services/UPMAuthenticationAuthorizationWSSkeleton";

			UPMAuthenticationAuthorizationWSSkeletonStub stub = new UPMAuthenticationAuthorizationWSSkeletonStub(serviceURL);

			ExistUser username = new ExistUser();

			es.upm.fi.sos.t3.backend.UPMAuthenticationAuthorizationWSSkeletonStub.Username username2 = new es.upm.fi.sos.t3.backend.UPMAuthenticationAuthorizationWSSkeletonStub.Username();

			username.setUsername(username2);

			username2.setName(addFollower.getArgs0().getUsername());

			ExistUserResponseE responseBackend = stub.existUser(username);

			if(responseBackend.get_return().getResult()) {

				if(followers.containsKey(addFollower.getArgs0().getUsername())) {

					ArrayList<String> seguidores = followers.get(addFollower.getArgs0().getUsername());

					if(member(seguidores,usuarioActivo.getName())) {

						res.setResponse(true);

					}else {

						seguidores.add(usuarioActivo.getName());

						res.setResponse(true);

					}

				}else {

					ArrayList<String> seguidores = new ArrayList<String>();

					followers.put(addFollower.getArgs0().getUsername(),seguidores);

					seguidores.add(usuarioActivo.getName());

					res.setResponse(true);

				}

			}else {

				res.setResponse(false);

			}

		} catch (Exception e) {

			// En caso de error, configurar la respuesta como falsa

			res.setResponse(false);

			e.printStackTrace();

		}

		response.set_return(res);

		return response;

	}



	private boolean member(ArrayList<String> lista,String elemento) {

		boolean esta = false;

		for(int i = 0;i < lista.size() && !esta;i++) {

			if(lista.get(i).equals(elemento)) {

				esta = true;

			}

		}

		return esta;

	}

	/**

	 * Auto generated method signature

	 *

	 * @param logout

	 * @return logoutResponse

	 */

	public es.upm.etsiinf.sos.LogoutResponse logout(es.upm.etsiinf.sos.Logout logout) {

		es.upm.etsiinf.sos.LogoutResponse response = new es.upm.etsiinf.sos.LogoutResponse();

		es.upm.etsiinf.sos.model.xsd.Response res = new es.upm.etsiinf.sos.model.xsd.Response();



		// Verificar si el usuario está autenticado (Si ha inciado sesion y esta sesion no se ha cerrado aun)

		if (!isAuthenticated()) {

			// Si el usuario no ha iniciado sesión, configurar la respuesta como falsa

			res.setResponse(false);

			response.set_return(res);

			return response;

		}



		// Cerrar la sesión del usuario

		usuarioActivo.setName(""); // Establecer el nombre de usuario a null para indicar que no está autenticado

		usuarioActivo.setPwd(""); // Establecer la contraseña a null

		res.setResponse(true); // Configurar la respuesta como true, indicando que se ha cerrado sesión correctamente

		response.set_return(res);

		return response;

	}

	private boolean isAuthenticated() {

		// Verificar si el usuario está autenticado (En login se le dan valores a ambos, asi que si son null, es que no ha inciado sesion o que se ha cerrado la sesion)

		return usuarioActivo.getName() != null && usuarioActivo.getPwd() != null;

	}











	/**

	 * Auto generated method signature

	 *

	 * @param removeWine

	 * @return removeWineResponse

	 */



	public es.upm.etsiinf.sos.RemoveWineResponse removeWine(es.upm.etsiinf.sos.RemoveWine removeWine) {

		es.upm.etsiinf.sos.RemoveWineResponse response = new es.upm.etsiinf.sos.RemoveWineResponse();

		es.upm.etsiinf.sos.model.xsd.Response res = new es.upm.etsiinf.sos.model.xsd.Response();



		// Verificar si la solicitud de removeWine es nula

		if (removeWine == null || removeWine.getArgs0() == null || removeWine.getArgs0().getName() == null) {

			res.setResponse(false);

			response.set_return(res);

			return response;

		}



		String wineNameToRemove = removeWine.getArgs0().getName();



		// Verificar si el usuario está autenticado

		if (!isAuthenticated()) {

			res.setResponse(false);

			response.set_return(res);

			return response;

		}



		// Verificar si el usuario es el administrador

		if (!isAdministrator()) {

			res.setResponse(false);

			response.set_return(res);

			return response;

		}



		// Verificar si el vino existe en el sistema

		if (!wineExists(wineNameToRemove)) {

			res.setResponse(false);

			response.set_return(res);

			return response;

		}



		try {

			// Remover el vino del mapa de vinos

			vinos.remove(wineNameToRemove);

			res.setResponse(true);

		} catch (Exception e) {

			// En caso de error, configurar la respuesta como falsa

			res.setResponse(false);

			e.printStackTrace();

		}



		response.set_return(res);

		return response;

	}



	// Método para verificar si el vino existe en el sistema

	private boolean wineExists(String wineName) {

		return vinos.containsKey(wineName);

	}









	/**

	 * Auto generated method signature

	 *

	 * @param getMyRates

	 * @return getMyRatesResponse

	 */



	public es.upm.etsiinf.sos.GetMyRatesResponse getMyRates(es.upm.etsiinf.sos.GetMyRates getMyRates) {
	    es.upm.etsiinf.sos.GetMyRatesResponse response = new es.upm.etsiinf.sos.GetMyRatesResponse();
	    WinesRatedList winesRatedList = new WinesRatedList();

	    // Verificar si el usuario está autenticado
	    boolean isAuthenticated = isAuthenticated();
	    if (!isAuthenticated) {
	    	winesRatedList.setNames(new String[0]);
	    	winesRatedList.setYears(new int[0]);
	    	winesRatedList.setGrapes(new String[0]);
	    	winesRatedList.setRates(new int[0]);
	        winesRatedList.setResult(false);
	        response.set_return(winesRatedList);
	        return response;
	    }

	    // Listas para nombres, años, tipos de uva y puntuaciones de los vinos
	    List<String> wineNames = new ArrayList<>();
	    List<Integer> wineYears = new ArrayList<>();
	    List<String> wineGrapes = new ArrayList<>();
	    List<Integer> wineRates = new ArrayList<>();

	    // Obtener las puntuaciones de los vinos del usuario (método ficticio)
	    Map<String, Integer> userRatedWines = getUserRatedWines();

	    // Si el usuario no ha puntuado ningún vino, devolver listas vacías y true
	    if (userRatedWines.isEmpty()) {
	        winesRatedList.setNames(new String[0]);
	        winesRatedList.setYears(new int[0]);
	        winesRatedList.setGrapes(new String[0]);
	        winesRatedList.setRates(new int[0]);
	        winesRatedList.setResult(true);
	        response.set_return(winesRatedList);
	        return response;
	    }

	    // Construir las listas de nombres, años, tipos de uva y puntuaciones de los vinos
	    for (Map.Entry<String, Integer> entry : userRatedWines.entrySet()) {
	        String wineName = entry.getKey();
	        int rate = entry.getValue();

	        // Obtener el detalle del vino del mapa 'vinos'
	        ArrayList<Wine> wineList = vinos.get(wineName);
	        if (wineList != null && !wineList.isEmpty()) {
	            Wine wine = wineList.get(0); // Asumimos que hay solo una entrada por nombre de vino
	            wineNames.add(wine.getName());
	            wineYears.add(wine.getYear());
	            wineGrapes.add(wine.getGrape());
	            wineRates.add(rate);
	        }
	    }

	    // Configurar el objeto WinesRatedList con los nombres, años, uvas y puntuaciones de los vinos
	    winesRatedList.setNames(wineNames.toArray(new String[0]));
	    winesRatedList.setYears(wineYears.stream().mapToInt(Integer::intValue).toArray());
	    winesRatedList.setGrapes(wineGrapes.toArray(new String[0]));
	    winesRatedList.setRates(wineRates.stream().mapToInt(Integer::intValue).toArray());

	    // Establecer la respuesta con las listas de vinos
	    winesRatedList.setResult(true);
	    response.set_return(winesRatedList);

	    return response;
	}





	/**

	 * Auto generated method signature

	 *

	 * @param unfollow

	 * @return unfollowResponse

	 */



	public es.upm.etsiinf.sos.UnfollowResponse unfollow(es.upm.etsiinf.sos.Unfollow unfollow){

		UnfollowResponse response = new UnfollowResponse();

		Response res = new Response();

		if(usuarioActivo.getName() == null || usuarioActivo.getPwd() == null) {

			res.setResponse(false);

			response.set_return(res);

			return response;

		}

		try {

			// stub para el envio al servicio 

			String serviceURL = "http://porter.dia.fi.upm.es:8080/axis2/services/UPMAuthenticationAuthorizationWSSkeleton";

			UPMAuthenticationAuthorizationWSSkeletonStub stub = new UPMAuthenticationAuthorizationWSSkeletonStub(serviceURL);

			ExistUser username = new ExistUser();

			es.upm.fi.sos.t3.backend.UPMAuthenticationAuthorizationWSSkeletonStub.Username username2 = new es.upm.fi.sos.t3.backend.UPMAuthenticationAuthorizationWSSkeletonStub.Username();

			username.setUsername(username2);

			username2.setName(unfollow.getArgs0().getUsername());

			ExistUserResponseE responseBackend = stub.existUser(username);

			if(responseBackend.get_return().getResult()) {

				if(followers.containsKey(unfollow.getArgs0().getUsername())) {

					ArrayList<String> seguidores = followers.get(unfollow.getArgs0().getUsername());

					if(member(seguidores,usuarioActivo.getName())) {

						seguidores.remove(usuarioActivo.getName());

						res.setResponse(true);

					}else {

						res.setResponse(false);

					}

				}else {

					res.setResponse(false);

				}

			}else {

				res.setResponse(false);

			}

		} catch (Exception e) {

			// En caso de error, configurar la respuesta como falsa

			res.setResponse(false);

			e.printStackTrace();

		}

		response.set_return(res);

		return response;

	}





	/**

	 * Auto generated method signature

	 *

	 * @param addUser

	 * @return addUserResponse

	 */

	public es.upm.etsiinf.sos.AddUserResponse addUser(es.upm.etsiinf.sos.AddUser addUser) { //FALTA REVISAR

		es.upm.etsiinf.sos.AddUserResponse response = new es.upm.etsiinf.sos.AddUserResponse();

		es.upm.etsiinf.sos.model.xsd.AddUserResponse res = new es.upm.etsiinf.sos.model.xsd.AddUserResponse();

		// Verificar si la solicitud de addUser es nula

		if (addUser == null || addUser.getArgs0() == null || addUser.getArgs0().getUsername() == null) {

			res.setResponse(false);
			res.setPwd("");

			response.set_return(res);

			return response;

		}

		// Verificar si el usuario NO es el administrador if

		if(!isAdministrator()) { 

			res.setResponse(false);
			res.setPwd("");

			response.set_return(res); 

			return response;

		}

		// Llamar a la operación addUser del servicio UPMAuthenticationAuthorization

		try {

			// stub para el envio al servicio 

			String serviceURL = "http://porter.dia.fi.upm.es:8080/axis2/services/UPMAuthenticationAuthorizationWSSkeleton";

			UPMAuthenticationAuthorizationWSSkeletonStub stub = new UPMAuthenticationAuthorizationWSSkeletonStub(serviceURL);

			es.upm.fi.sos.t3.backend.UPMAuthenticationAuthorizationWSSkeletonStub.AddUser usuario = new es.upm.fi.sos.t3.backend.UPMAuthenticationAuthorizationWSSkeletonStub.AddUser();

			UserBackEnd user = new UserBackEnd();

			user.setName(addUser.getArgs0().getUsername());

			usuario.setUser(user);

			es.upm.fi.sos.t3.backend.UPMAuthenticationAuthorizationWSSkeletonStub.AddUserResponse backendResponse = stub.addUser(usuario);

			if (backendResponse.get_return().getResult()) {

				// Si la operación se realizó correctamente, crear una entrada en el mapa de seguidores(*** revisar para lista vinos***)

				ArrayList<String> seguidores = new ArrayList<String>();

				followers.put(addUser.getArgs0().getUsername(), seguidores);

				res.setResponse(true);

				res.setPwd(backendResponse.get_return().getPassword());

			} else {

				// Si la operación no se realizó correctamente, configurar la respuesta como falsa

				res.setResponse(false);
				res.setPwd("");

			}

		} catch (Exception e) {

			// En caso de error, configurar la respuesta como falsa

			res.setResponse(false);
			res.setPwd("");

			System.out.println(e.getLocalizedMessage());

			e.printStackTrace();

		}



		//Crear el objeto AddUserResponse y configurar la respuesta principal:OPC1 (REVISAR IMPLEMENTACIONES)

		//es.upm.etsiinf.sos.model.xsd.AddUserResponse addUserResponseObj = new es.upm.etsiinf.sos.model.xsd.AddUserResponse();

		//addUserResponseObj.set_return(res);

		//response.set_return(addUserResponseObj);



		//Crear el objeto AddUserResponse y configurar la respuesta principal:OPC2

		response.set_return(res);

		return response;



	}



	private boolean isAdministrator() {

		// Verificar si el usuario es el administrador, solo hace falta comprobar el nombre porque solo el admin se va a llamar admin, ya que no puede haber dos nombres iguales

		return this.usuarioActivo != null && usuarioActivo.getName() != null && "admin".equals(usuarioActivo.getName());

	}















	/**

	 * Auto generated method signature

	 *

	 * @param rateWine

	 * @return rateif(usuarioActivo.getName() == null || usuarioActivo.getPwd() == null) {

	res.setResponse(false);

	response.set_return(res);

	return response;

	}WineResponse

	 */





	// Método para obtener las puntuaciones de los vinos del usuario

	public Map<String, Integer> getUserRatedWines() {

		return userRatedWines;

	}



	// Método rateWine modificado para actualizar el mapa de puntuaciones de los vinos del usuario

	public es.upm.etsiinf.sos.RateWineResponse rateWine(es.upm.etsiinf.sos.RateWine rateWine) {

		es.upm.etsiinf.sos.RateWineResponse response = new es.upm.etsiinf.sos.RateWineResponse();

		es.upm.etsiinf.sos.model.xsd.Response res = new es.upm.etsiinf.sos.model.xsd.Response();



		// Verificar si la solicitud de rateWine es nula

		if (rateWine == null || rateWine.getArgs0() == null || rateWine.getArgs0().getName() == null) {

			res.setResponse(false);

			response.set_return(res);

			return response;

		}



		String wineName = rateWine.getArgs0().getName();

		int wineYear = rateWine.getArgs0().getYear();

		String wineGrape = rateWine.getArgs0().getGrape();

		int rate = rateWine.getArgs0().getRate();



		// Verificar si el usuario está autenticado

		if (!isAuthenticated()) {

			res.setResponse(false);

			response.set_return(res);

			return response;

		}



		// Verificar si el vino existe en el sistema

		if (!wineExists(wineName, wineYear, wineGrape)) {

			res.setResponse(false);

			response.set_return(res);

			return response;

		}



		// Validar que la puntuación esté dentro del rango de 0 a 10

		if (rate < 0 || rate > 10) {

			res.setResponse(false);

			response.set_return(res);

			return response;

		}



		// Actualizar el mapa de puntuaciones de los vinos del usuario

		userRatedWines.put(wineName, rate);



		// Configurar la respuesta como exitosa

		res.setResponse(true);



		response.set_return(res);

		return response;

	}





	/**

	 * Auto generated method signature

	 *

	 * @param addWine

	 * @return addWineResponse

	 */



	public es.upm.etsiinf.sos.AddWineResponse addWine(es.upm.etsiinf.sos.AddWine addWine) {

		es.upm.etsiinf.sos.AddWineResponse response = new es.upm.etsiinf.sos.AddWineResponse();

		es.upm.etsiinf.sos.model.xsd.Response res = new es.upm.etsiinf.sos.model.xsd.Response();



		// Verificar si la solicitud de addWine es nula

		if (addWine == null || addWine.getArgs0() == null || addWine.getArgs0().getName() == null) {

			res.setResponse(false);

			response.set_return(res);

			return response;

		}



		String newWineName = addWine.getArgs0().getName();

		int newWineYear = addWine.getArgs0().getYear();

		String newWineGrape = addWine.getArgs0().getGrape();



		// Verificar si el usuario está autenticado

		if (!isAuthenticated()) {

			res.setResponse(false);

			response.set_return(res);

			return response;

		}



		// Verificar si el usuario es el administrador

		if (!isAdministrator()) {

			res.setResponse(false);

			response.set_return(res);

			return response;

		}



		// Verificar si el vino ya existe en el sistema

		if (wineExists(newWineName, newWineYear, newWineGrape)) {

			res.setResponse(false);

			response.set_return(res);

			return response;

		}



		try {

			// Añadir el vino al mapa de vinos

			Wine newWine = new Wine();

			newWine.setName(newWineName);

			newWine.setYear(newWineYear);

			newWine.setGrape(newWineGrape);



			ArrayList<Wine> wineList = vinos.getOrDefault(newWineName, new ArrayList<>());

			wineList.add(newWine);

			vinos.put(newWineName, wineList);



			res.setResponse(true);

		} catch (Exception e) {

			// En caso de error, configurar la respuesta como falsa

			res.setResponse(false);

			e.printStackTrace();

		}



		response.set_return(res);

		return response;

	}



	// Método para verificar si el vino ya existe en el sistema

	private boolean wineExists(String wineName, int wineYear, String wineGrape) {

		if (vinos.containsKey(wineName)) {

			// Si el vino ya existe, verificar si tiene la misma combinación de año y tipo de uva

			ArrayList<Wine> wineList = vinos.get(wineName);

			for (Wine existingWine : wineList) {

				if (existingWine.getYear() == wineYear && existingWine.getGrape().equals(wineGrape)) {

					return true; // El vino ya existe con la misma combinación de año y tipo de uva

				}

			}

		}

		return false; // El vino no existe o existe con una combinación diferente de año y tipo de uva

	}





	/**

	 * Auto generated method signature

	 *

	 * @param getWines

	 * @return getWinesResponse

	 */



	public es.upm.etsiinf.sos.GetWinesResponse getWines(es.upm.etsiinf.sos.GetWines getWines) {
	    es.upm.etsiinf.sos.GetWinesResponse response = new es.upm.etsiinf.sos.GetWinesResponse();
	    WineList wineListResponse = new WineList();
	    // Verificar si el usuario está autenticado
	    if (!isAuthenticated()) {
	    	wineListResponse.setResult(false);
	    	wineListResponse.setNames(new String[0]);
	    	wineListResponse.setYears(new int[0]);
	    	wineListResponse.setGrapes(new String[0]);
	        response.set_return(wineListResponse);
	        return response;
	    }
	    // Obtener la lista de vinos existentes
	    List<Wine> wineList = new ArrayList<>();
	    for (Map.Entry<String, ArrayList<Wine>> entry : vinos.entrySet()) {
	        wineList.addAll(entry.getValue());
	    }
	    // Si no hay vinos en la red social, devolver true y listas vacías
	    if (wineList.isEmpty()) {
	        wineListResponse.setNames(new String[0]);
	        wineListResponse.setYears(new int[0]);
	        wineListResponse.setGrapes(new String[0]);
	        wineListResponse.setResult(true);
	        response.set_return(wineListResponse);
	        return response;
	    }

	    // Ordenar la lista de vinos en orden inverso de añadido
	    Collections.reverse(wineList);
	    // Construir las listas de nombres de vinos, años y tipos de uva
	    List<String> names = new ArrayList<>();
	    List<Integer> years = new ArrayList<>();
	    List<String> grapes = new ArrayList<>();
	    for (Wine wine : wineList) {
	        names.add(wine.getName());
	        years.add(wine.getYear());
	        grapes.add(wine.getGrape());
	    }

	    // Configurar el objeto WineList con las listas y el resultado de la autenticación
	    wineListResponse.setNames(names.toArray(new String[0]));
	    wineListResponse.setYears(years.stream().mapToInt(Integer::intValue).toArray());
	    wineListResponse.setGrapes(grapes.toArray(new String[0]));

	    wineListResponse.setResult(true);
	    response.set_return(wineListResponse);

	    return response;
	}






	/**

	 * Auto generated method signature

	 *

	 * @param changePassword

	 * @return changePasswordResponse

	 */



	public es.upm.etsiinf.sos.ChangePasswordResponse changePassword(es.upm.etsiinf.sos.ChangePassword changePassword) {

		es.upm.etsiinf.sos.ChangePasswordResponse response = new es.upm.etsiinf.sos.ChangePasswordResponse();

		es.upm.etsiinf.sos.model.xsd.Response res = new es.upm.etsiinf.sos.model.xsd.Response();



		// Obtener el nombre de usuario y las contraseñas del parámetro changePassword

		String oldPassword = changePassword.getArgs0().getOldpwd();

		String newPassword = changePassword.getArgs0().getNewpwd();



		// Verificar si el usuario es admin

		if ("admin".equals(usuarioActivo.getName())) {

			// El usuario admin no debe cambiar la contraseña a través del servicio UPMAuthenticationAuthorization

			// Cambiar la contraseña del admin directamente sin usar el servicio UPMAuthenticationAuthorization

			String adminPassword = usuarioActivo.getPwd();

			if (adminPassword.equals(oldPassword)) {

				// La contraseña antigua coincide, cambiarla por la nueva

				usuarioActivo.setPwd(newPassword);

				// Devolver true para indicar que la operación fue exitosa

				res.setResponse(true);

			} else {

				// La contraseña antigua no coincide, devolver false

				res.setResponse(false);

			}

		} else {

			try {

				String serviceURL = "http://porter.dia.fi.upm.es:8080/axis2/services/UPMAuthenticationAuthorizationWSSkeleton";

				UPMAuthenticationAuthorizationWSSkeletonStub stub = new UPMAuthenticationAuthorizationWSSkeletonStub(serviceURL);

				ChangePassword changePasswordBackend = new ChangePassword();

				ChangePasswordBackEnd changePasswordBackend2 = new ChangePasswordBackEnd();

				changePasswordBackend2.setName(usuarioActivo.getName());

				changePasswordBackend2.setNewpwd(changePassword.getArgs0().getNewpwd());

				changePasswordBackend2.setOldpwd(changePassword.getArgs0().getOldpwd());

				changePasswordBackend.setChangePassword(changePasswordBackend2);

				ChangePasswordResponseE backendResponse = stub.changePassword(changePasswordBackend);

				if(backendResponse.get_return().getResult()) {

					usuarioActivo.setPwd(newPassword);

					res.setResponse(true);

				}else {

					res.setResponse(false);

				}

			}catch (Exception e) {

				// En caso de error, configurar la respuesta como falsa

				res.setResponse(false);

				e.printStackTrace();

			}

		}

		response.set_return(res);

		return response;

	}



	/**

	 * Auto generated method signature

	 *

	 * @param login

	 * @return loginResponse

	 */



	public es.upm.etsiinf.sos.LoginResponse login(es.upm.etsiinf.sos.Login login) {

		es.upm.etsiinf.sos.LoginResponse response = new es.upm.etsiinf.sos.LoginResponse();

		es.upm.etsiinf.sos.model.xsd.Response res = new es.upm.etsiinf.sos.model.xsd.Response();

		if(usuarioActivo.getName() == null || usuarioActivo.getPwd() == null) {

			res.setResponse(false);

			response.set_return(res);

			return response;

		}

		// Verificar si el objeto de login es nulo

		if (login == null || login.getArgs0() == null) {

			// Configurar la respuesta como fallida

			res.setResponse(false);

			response.set_return(res);

			return response;

		}

		// Obtener el nombre de usuario y la contraseña del parámetro login

		String username = login.getArgs0().getName();

		String password = login.getArgs0().getPwd();



		if(usuarioActivo.getName().equals(username)) {

			res.setResponse(true);

			response.set_return(res);

			return response;

		}

		if(!usuarioActivo.getName().equals(username) && !usuarioActivo.getName().equals("")) {

			res.setResponse(false);

			response.set_return(res);

			return response;

		}

		// Verificar si el usuario es admin

		if ("admin".equals(username) && "admin".equals(password)) {

			// El usuario admin no se gestiona a través del servicio UPMAuthenticationAuthorization

			usuarioActivo.setName("admin");

			usuarioActivo.setPwd("admin");

			res.setResponse(true);

			response.set_return(res);

			return response;

		}

		try {

			String serviceURL = "http://porter.dia.fi.upm.es:8080/axis2/services/UPMAuthenticationAuthorizationWSSkeleton";

			UPMAuthenticationAuthorizationWSSkeletonStub stub = new UPMAuthenticationAuthorizationWSSkeletonStub(serviceURL);

			Login login2 = new Login();

			LoginBackEnd login3 = new LoginBackEnd();

			login3.setName(username);

			login3.setPassword(password);

			login2.setLogin(login3);

			LoginResponse loginResponse = stub.login(login2);

			if(loginResponse.get_return().getResult()) {

				usuarioActivo.setName(username);

				usuarioActivo.setPwd(password);

				res.setResponse(true);

				response.set_return(res);

				return response;

			}else {

				res.setResponse(false);

				response.set_return(res);

				return response;

			}

		}catch (Exception e) {

			// En caso de error, configurar la respuesta como falsa

			res.setResponse(false);

			e.printStackTrace();

		}

		return response;

	}





	public String getUsuario() {

		return usuarioActivo.getName();

	}

	public String getPwd() {

		return usuarioActivo.getPwd();

	}



	/* private boolean authenticateUserWithService(String username, String password) {

 // Verificar si el usuario y la contraseña coinciden con los datos almacenados en el mapa de usuarios

 if (usuarios.containsKey(username)) {

 User user = usuarios.get(username);

 // Verificar si la contraseña coincide

 return user.getPwd().equals(password);

 }

 // Si el usuario no está en el mapa de usuarios, devolver false

 return false;

 } He cambiado la lógica de autenticación directamente en el método login,

 utilizando el Map<String, List<String>> userSessions para verificar si el usuario ya está

 autenticado en alguna sesión activa*/















	/**

	 * Auto generated method signature

	 *

	 * @param getMyFollowerRates

	 * @return getMyFollowerRatesResponse

	 */



	public es.upm.etsiinf.sos.GetMyFollowerRatesResponse getMyFollowerRates(es.upm.etsiinf.sos.GetMyFollowerRates getMyFollowerRates) {
	    es.upm.etsiinf.sos.GetMyFollowerRatesResponse response = new es.upm.etsiinf.sos.GetMyFollowerRatesResponse();
	    WinesRatedList winesRatedList = new WinesRatedList();

	    // Verificar si el usuario está autenticado
	    if (!isAuthenticated()) {
	        winesRatedList.setResult(false);
	        winesRatedList.setNames(new String[0]);
	        winesRatedList.setYears(new int[0]);
	        winesRatedList.setGrapes(new String[0]);
	        winesRatedList.setRates(new int[0]);
	        response.set_return(winesRatedList);
	        return response;
	    }

	    // Verificar si el usuario sigue al seguidor especificado
	    String followerUsername = getMyFollowerRates.getArgs0().getUsername();
	    if (!isFollowingUser(followerUsername)) {
	        winesRatedList.setResult(false);
	        winesRatedList.setNames(new String[0]);
	        winesRatedList.setYears(new int[0]);
	        winesRatedList.setGrapes(new String[0]);
	        winesRatedList.setRates(new int[0]);
	        response.set_return(winesRatedList);
	        return response;
	    }

	    // Obtener las puntuaciones de los vinos del seguidor
	    List<String> wineNames = new ArrayList<>();
	    List<Integer> wineRates = new ArrayList<>();
	    List<Integer> wineYears = new ArrayList<>();
	    List<String> wineGrapes = new ArrayList<>();

	    Map<String, Integer> followerRatedWines = getFollowerRatedWines(followerUsername);

	    // Si el seguidor no ha puntuado ningún vino, devolver listas vacías y true
	    if (followerRatedWines.isEmpty()) {
	        winesRatedList.setNames(new String[0]);
	        winesRatedList.setYears(new int[0]);
	        winesRatedList.setGrapes(new String[0]);
	        winesRatedList.setRates(new int[0]);
	        winesRatedList.setResult(true);
	        response.set_return(winesRatedList);
	        return response;
	    }

	    // Construir las listas de nombres, años, tipos de uva y puntuaciones de los vinos
	    for (Map.Entry<String, Integer> entry : followerRatedWines.entrySet()) {
	        String wineName = entry.getKey();
	        int rate = entry.getValue();

	        // Obtener el detalle del vino del mapa 'vinos'
	        ArrayList<Wine> wineList = vinos.get(wineName);
	        if (wineList != null && !wineList.isEmpty()) {
	            Wine wine = wineList.get(0); // Asumimos que hay solo una entrada por nombre de vino
	            wineNames.add(wine.getName());
	            wineYears.add(wine.getYear());
	            wineGrapes.add(wine.getGrape());
	            wineRates.add(rate);
	        }
	    }

	    // Configurar el objeto WinesRatedList con los nombres, años, uvas y puntuaciones de los vinos
	    winesRatedList.setNames(wineNames.toArray(new String[0]));
	    winesRatedList.setYears(wineYears.stream().mapToInt(Integer::intValue).toArray());
	    winesRatedList.setGrapes(wineGrapes.toArray(new String[0]));
	    winesRatedList.setRates(wineRates.stream().mapToInt(Integer::intValue).toArray());

	    // Establecer la respuesta con las listas de vinos y el resultado del inicio de sesión
	    winesRatedList.setResult(true);
	    response.set_return(winesRatedList);

	    return response;
	}

	private boolean isFollowingUser(String username) {
	    if (usuarioActivo.getName() == null || usuarioActivo.getPwd() == null) {
	        return false;
	    }

	    // Verificar si el usuario especificado está en la lista de seguidores del usuario activo
	    if (followers.containsKey(usuarioActivo.getName())) {
	        ArrayList<String> seguidores = followers.get(usuarioActivo.getName());
	        return seguidores.contains(username);
	    }

	    return false;
	}

	// Método para obtener las puntuaciones de vinos del seguidor especificado
	private Map<String, Integer> getFollowerRatedWines(String followerUsername) {
	    // Verificar si el seguidor tiene puntuaciones de vinos registradas
	    if (followerRatedWines.containsKey(followerUsername)) {
	        // Devolver las puntuaciones de vinos del seguidor
	        return followerRatedWines.get(followerUsername);
	    } else {
	        // Si el seguidor no tiene puntuaciones de vinos registradas, devolver un mapa vacío
	        return new HashMap<>();
	    }
	}


}
