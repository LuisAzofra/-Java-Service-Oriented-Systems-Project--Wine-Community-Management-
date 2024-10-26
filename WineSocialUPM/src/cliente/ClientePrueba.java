package cliente;

import java.util.Scanner;

import es.upm.etsiinf.sos.*;
import es.upm.etsiinf.sos.AddUserResponse;
import es.upm.etsiinf.sos.model.xsd.*;

public class ClientePrueba {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        WineSocialUPMSkeleton stub = new WineSocialUPMSkeleton();

        while (!exit) {
            // Mostrar el menú
            System.out.println("=== Menú Principal ===");
            System.out.println("[1] addUser(nombre)");
            System.out.println("[2] login(nombre,contraseña)");
            System.out.println("[3] logout()");
            System.out.println("[4] removeUser(nombre)");
            System.out.println("[5] changePassword(contraseña)");
            System.out.println("[6] addFollower(nombre)");
            System.out.println("[7] unfollow(nombre)");
            System.out.println("[8] getMyFollowers()");
            System.out.println("[9] addWine(nombre,año,uva)");
            System.out.println("[10] removeWine(nombre,año,uva)");
            System.out.println("[11] getWines()");
            System.out.println("[12] rateWine(nombre,año,uva,puntuacion)");
            System.out.println("[13] getMyRates()");
            System.out.println("[14] getMyFollowerRates(nombre)");
            System.out.println("[15] Salir");
            System.out.println("[16] Info usuario");
            System.out.print("Elige una opción: ");

            try {
                // Capturar la entrada del usuario
                int choice = Integer.parseInt(scanner.nextLine().trim());
                
                // Manejar la selección del usuario
                switch (choice) {
                    case 1:
                        System.out.println("Inserte un nombre de usuario a añadir:");
                        String nombre1 = scanner.nextLine().trim();
                        AddUser addUser = new AddUser();
                        Username username1 = new Username();
                        username1.setUsername(nombre1);
                        addUser.setArgs0(username1);
                        es.upm.etsiinf.sos.AddUserResponse addUserResponse = stub.addUser(addUser);
                        if (addUserResponse.get_return().getResponse()) {
                            String pwd = addUserResponse.get_return().getPwd();
                            System.out.println("[+] Usuario añadido con éxito");
                            System.out.println("Su contraseña es: " + pwd);
                        } else {
                            System.out.println("[!] Error al añadir el usuario");
                        }
                        break;
                    case 2:
                        System.out.println("Inserte el nombre de usuario:");
                        String nombre2 = scanner.nextLine().trim();
                        System.out.println("Inserte la contraseña:");
                        String contraseña2 = scanner.nextLine().trim();
                        Login login = new Login();
                        User user2 = new User();
                        user2.setName(nombre2);
                        user2.setPwd(contraseña2);
                        login.setArgs0(user2);
                        System.out.println("Realizando login("+nombre2+","+contraseña2+")...");
                        LoginResponse loginResponse = stub.login(login);
                        if (loginResponse.get_return().getResponse()) {
                            System.out.println("[+] login realizado con éxito");
                        } else {
                            System.out.println("[!] error en login");
                        }
                        break;
                    case 3:
                        Logout logout = new Logout();
                        LogoutResponse logoutResponse = stub.logout(logout);
                        if (logoutResponse.get_return().getResponse()) {
                            System.out.println("[+] logout realizado con éxito");
                        } else {
                            System.out.println("[!] error en logout");
                        }
                        break;
                    case 4:
                        System.out.println("Inserte nombre de usuario:");
                        String nombre4 = scanner.nextLine().trim();
                        RemoveUser removeUser = new RemoveUser();
                        Username username4 = new Username();
                        username4.setUsername(nombre4);
                        removeUser.setArgs0(username4);
                        RemoveUserResponse removeUserResponse = stub.removeUser(removeUser);
                        if (removeUserResponse.get_return().getResponse()) {
                            System.out.println("[+] usuario eliminado");
                        } else {
                            System.out.println("[!] error al eliminar el usuario");
                        }
                        break;
                    case 5:
                        System.out.println("Inserte la contraseña actual:");
                        String oldPwd = scanner.nextLine().trim();
                        System.out.println("Inserte la nueva contraseña:");
                        String newPwd = scanner.nextLine().trim();
                        ChangePassword changePassword = new ChangePassword();
                        PasswordPair passwordPair = new PasswordPair();
                        passwordPair.setNewpwd(newPwd);
                        passwordPair.setOldpwd(oldPwd);
                        changePassword.setArgs0(passwordPair);
                        ChangePasswordResponse response5 = stub.changePassword(changePassword);
                        if (response5.get_return().getResponse()) {
                            System.out.println("[+] contraseña actualizada");
                        } else {
                            System.out.println("[!] error al actualizar la contraseña");
                        }
                        break;
                    case 6:
                        System.out.println("Inserte el nombre del usuario a seguir:");
                        String nombre6 = scanner.nextLine().trim();
                        AddFollower addFollower = new AddFollower();
                        Username username6 = new Username();
                        username6.setUsername(nombre6);
                        addFollower.setArgs0(username6);
                        AddFollowerResponse response6 = stub.addFollower(addFollower);
                        if (response6.get_return().getResponse()) {
                            System.out.println("[+] seguidor añadido");
                        } else {
                            System.out.println("[!] error al añadir el seguidor");
                        }
                        break;
                    case 7:
                        System.out.println("Inserte el nombre del usuario:");
                        String nombre7 = scanner.nextLine().trim();
                        Unfollow unfollow = new Unfollow();
                        Username username7 = new Username();
                        username7.setUsername(nombre7);
                        unfollow.setArgs0(username7);
                        UnfollowResponse response7 = stub.unfollow(unfollow);
                        if (response7.get_return().getResponse()) {
                            System.out.println("[+] seguidor eliminado");
                        } else {
                            System.out.println("[!] error al eliminar el seguidor");
                        }
                        break;
                    case 8:
                    	GetMyFollowers followers = new GetMyFollowers();
                    	GetMyFollowersResponse followersResponse = stub.getMyFollowers(followers);
                    	if(followersResponse.get_return().getResult()) {
                    		String[] seguidores = followersResponse.get_return().getFollowers();
                    		System.out.print("Seguidores: ");
                    		for(int i = 0;i < seguidores.length;i++) {
                    			System.out.print(seguidores[i]+" ");
                    		}
                    		System.out.print("\n");
                    	}else {
                    		System.out.println("[!] error al devolver los seguidores");
                    	}
                    	break;
                    case 9:
                    	System.out.println("Inserte el nombre del vino: ");
                    	String nombre9 = scanner.nextLine().trim();
                    	System.out.println("Inserte el año del vino: ");
                    	int año9 = Integer.parseInt(scanner.nextLine().trim());
                    	System.out.println("Inserte el tipo de uva: ");
                    	String uva9 = scanner.nextLine().trim();
                    	AddWine addWine = new AddWine();
                    	Wine vino9 = new Wine();
                    	vino9.setName(nombre9);
                    	vino9.setYear(año9);
                    	vino9.setGrape(uva9);
                    	addWine.setArgs0(vino9);
                    	AddWineResponse responseWine = stub.addWine(addWine);
                    	if(responseWine.get_return().getResponse()) {
                    		System.out.println("[+] Vino añadido con exito");
                    	}else {
                    		System.out.println("[!] Error al añadir el vino");
                    	}
                    	break;
                    case 10:
                    	System.out.println("Inserte el nombre del vino: ");
                    	String nombre10 = scanner.nextLine().trim();
                    	System.out.println("Inserte el año del vino: ");
                    	int año10 = Integer.parseInt(scanner.nextLine().trim());
                    	System.out.println("Inserte el tipo de uva: ");
                    	String uva10 = scanner.nextLine().trim();
                    	RemoveWine removeWine = new RemoveWine();
                    	Wine vino10 = new Wine();
                    	vino10.setName(nombre10);
                    	vino10.setYear(año10);
                    	vino10.setGrape(uva10);
                    	removeWine.setArgs0(vino10);
                    	RemoveWineResponse responseRWine = stub.removeWine(removeWine);
                    	if(responseRWine.get_return().getResponse()) {
                    		System.out.println("[+] Vino eliminado con exito");
                    	}else {
                    		System.out.println("[!] Error al eliminar el vino");
                    	}
                    	break;
                    case 11:
                    	GetWines vinos = new GetWines();
                    	GetWinesResponse responseGWines = stub.getWines(vinos);
                    	if(responseGWines.get_return().getResult() == true) {
                    		String[] nombres = responseGWines.get_return().getNames();
                    		int[] años = responseGWines.get_return().getYears();
                    		String[] uvas = responseGWines.get_return().getGrapes();
                    		System.out.print("vinos[ ");
                    		for(int i = 0;i < nombres.length;i++) {
                    			System.out.print("nombre: "+nombres[i]+" ");
                    			System.out.print("años: "+años[i]+" ");
                    			System.out.print("uva: "+uvas[i]+" ");
                    		}
                    		System.out.print("]\n");
                    	}else {
                    		System.out.println("[!] Error al devolver los vinos");
                    	}
                    	break;
                    case 12:
                    	System.out.println("Inserte el nombre del vino: ");
                    	String nombre12 = scanner.nextLine().trim();
                    	System.out.println("Inserte el año del vino: ");
                    	int año12 = Integer.parseInt(scanner.nextLine().trim());
                    	System.out.println("Inserte el tipo de uva: ");
                    	String uva12 = scanner.nextLine().trim();
                    	System.out.println("Inserte la puntuacion del vino");
                    	int rate12 = Integer.parseInt(scanner.nextLine().trim());
                    	RateWine rateWine = new RateWine();
                    	WineRated wineRated = new WineRated();
                    	wineRated.setName(nombre12);
                    	wineRated.setYear(año12);
                    	wineRated.setGrape(uva12);
                    	wineRated.setRate(rate12);
                    	rateWine.setArgs0(wineRated);
                    	RateWineResponse rateWResponse = stub.rateWine(rateWine);
                    	if(rateWResponse.get_return().getResponse()) {
                    		System.out.println("[+] Puntuacion añadida con exito");
                    	}else {
                    		System.out.println("[!] Erro al añadir la puntuacion");
                    	}
                    	break;
                    case 13:
                    	GetMyRates myRates = new GetMyRates();
                    	GetMyRatesResponse myRatesResponse = stub.getMyRates(myRates);
                    	if(myRatesResponse.get_return().getResult()) {
                    		int[] rates = myRatesResponse.get_return().getRates();
                    		String[] nombres = myRatesResponse.get_return().getNames();
                    		int[] años = myRatesResponse.get_return().getYears();
                    		String[] uvas = myRatesResponse.get_return().getGrapes();
                    		System.out.print("vinos: ");
                    		for(int i = 0;i < nombres.length;i++) {
                    			System.out.print("nombre: "+nombres[i]+" ");
                    			System.out.print("años: "+años[i]+" ");
                    			System.out.print("uva: "+uvas[i]+" ");
                    			System.out.print("puntuacion :"+rates[i]+" ");
                    		}
                    		System.out.print("\n");
                    	}else {
                    		System.out.println("[!] Error al devolver las puntuaciones");
                    	}
                    	break;
                    case 14:
                    	System.out.println("Introduzca el nombre del seguidor: ");
                    	String nombre14 = scanner.nextLine().trim();
                    	GetMyFollowerRates followerRates = new GetMyFollowerRates();
                    	Username username14 = new Username();
                    	username14.setUsername(nombre14);
                    	followerRates.setArgs0(username14);
                    	GetMyFollowerRatesResponse followerRResponse = stub.getMyFollowerRates(followerRates);
                    	if(followerRResponse.get_return().getResult()) {
                    		int[] rates = followerRResponse.get_return().getRates();
                    		String[] nombres = followerRResponse.get_return().getNames();
                    		int[] años = followerRResponse.get_return().getYears();
                    		String[] uvas = followerRResponse.get_return().getGrapes();
                    		System.out.print("vinos: ");
                    		for(int i = 0;i < nombres.length;i++) {
                    			System.out.print("nombre: "+nombres[i]+" ");
                    			System.out.print("años: "+años[i]+" ");
                    			System.out.print("uva: "+uvas[i]+" ");
                    			System.out.print("puntuacion :"+rates[i]+" ");
                    		}
                    	}else {
                    		System.out.println("[!] Error al devolver las puntuaciones de "+nombre14);
                    	}
                    	break;
                    case 15:
                        exit = true;
                        System.out.println("Saliendo del programa...");
                        break;
                    case 16:
                    	String nombre16 = stub.getUsuario();
                    	String contraseña16 = stub.getPwd();
                    	System.out.println("Usuario activo:"+nombre16+" ,contraseña:"+contraseña16);
                    	break;
                    default:
                        System.out.println("Opción no válida. Inténtalo de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, introduce un número.");
            } catch (Exception e) {
                System.out.println("Ocurrió un error: "+ e.getLocalizedMessage());
                e.printStackTrace();
            }
        }

        scanner.close();
    }
}
