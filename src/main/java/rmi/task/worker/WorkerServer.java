package rmi.task.worker;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class WorkerServer
{
    public static void main(String[] args)
    {
	try
	{
	    LocateRegistry.createRegistry(1098);
	    Worker stub = new WorkerImpl();
	    Naming.rebind("rmi://192.168.1.54:1098/Worker", stub);
	} catch (RemoteException e)
	{
	    System.out.println("remote exc");
	    e.printStackTrace();
	} catch (MalformedURLException ex)
	{
	    System.out.println("malf exc");
	    ex.printStackTrace();
	}

    }
}
