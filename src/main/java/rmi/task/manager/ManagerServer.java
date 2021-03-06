package rmi.task.manager;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ManagerServer
{
    public static void main(String[] args)
    {

	try
	{
	    LocateRegistry.createRegistry(1099);
	    ManagerImpl stub = new ManagerImpl();
	    Naming.rebind("rmi://192.168.1.54:1099/Manager", stub);
	    new Thread(stub).start();
	    System.out.println("Manager server is up and running");
	} catch (RemoteException e)
	{
	    e.printStackTrace();
	} catch (MalformedURLException ex)
	{
	    ex.printStackTrace();
	}

	/*
	 * try
	 * {
	 * ManagerImpl stub = new ManagerImpl();
	 * Registry registry = LocateRegistry.createRegistry(1099);
	 * //Manager stub = (Manager) UnicastRemoteObject.exportObject(new
	 * ManagerImpl(), 0);
	 * registry.bind("rmi://192.168.1.54:1099/Manager", stub);
	 * new Thread(stub).start();
	 * System.out.println("Manager server is up and running");
	 * } catch (RemoteException e)
	 * {
	 * // TODO Auto-generated catch block
	 * e.printStackTrace();
	 * } catch (AlreadyBoundException e)
	 * {
	 * // TODO Auto-generated catch block
	 * e.printStackTrace();
	 * }
	 */
    }
}
