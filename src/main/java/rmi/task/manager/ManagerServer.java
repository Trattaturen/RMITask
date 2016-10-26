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
	    Naming.rebind("rmi://localhost:1099/Manager", stub);
	    new Thread(stub).start();
	    System.out.println("Manager server is up and running");
	} catch (RemoteException e)
	{
	    e.printStackTrace();
	} catch (MalformedURLException ex)
	{
	    ex.printStackTrace();
	}

    }
}
