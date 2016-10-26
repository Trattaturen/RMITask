package rmi.task.worker;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import rmi.task.manager.Manager;

public class WorkerImpl extends UnicastRemoteObject implements Worker
{

    private static final long serialVersionUID = 1L;
    // final static Logger logger = Logger.getLogger(WorkerImpl.class);

    String name;
    boolean finished = true;
    String task;
    Manager manager;
    Worker thisWorker = this;

    public WorkerImpl() throws RemoteException {
	// logger.info("Initializing worker");
	name = String.valueOf(System.currentTimeMillis());
	// logger.info("Trying to lokup for manager");

	while (manager == null)
	{
	    System.out.println("manager not found");
	    try
	    {
		manager = (Manager) Naming.lookup("rmi://192.168.1.54:1099/Manager");
		// Registry registry =
		// LocateRegistry.getRegistry("rmi://192.168.1.54", 1099);
		// manager = (Manager) registry.lookup("Manager");

		// logger.info("Not found manager");

	    } catch (RemoteException e)
	    {
		e.printStackTrace();
		// logger.info("Not found manager");
	    } catch (NotBoundException e)
	    {
		e.printStackTrace();
		// logger.info("Not found manager");
	    } catch (MalformedURLException e)
	    {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
	manager.wakeUp(this);
    }

    public void execute(String json) throws RemoteException
    {
	// logger.info("Got new task");
	finished = false;
	task = json;
	new Thread(new Runnable()
	{
	    public void run()
	    {

		System.out.println("Executing task: " + task);
		finished = true;
		try
		{
		    manager.wakeUp(thisWorker);
		} catch (RemoteException e)
		{

		    e.printStackTrace();
		}

	    }
	}).start();

    }

    public boolean isFinished()
    {
	return finished;
    }

    public String getName()
    {
	return name;
    }

    public static void main(String[] args) throws RemoteException
    {
	Worker worker = new WorkerImpl();
    }

}
