package rmi.task.manager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

import org.apache.log4j.Logger;

import rmi.task.worker.Worker;

public class ManagerImpl extends UnicastRemoteObject implements Manager, Runnable
{

    private static final long serialVersionUID = 1L;
    final static Logger logger = Logger.getLogger(ManagerImpl.class);
    Map<String, Worker> workers;
    Stack<String> tempTasks;

    public ManagerImpl() throws RemoteException {
	logger.info("Starting manager");
	workers = new HashMap<String, Worker>();
	tempTasks = new Stack<String>();
	tempTasks.add("TASK 1");
	tempTasks.add("TASK 2");
	tempTasks.add("TASK 3");
	tempTasks.add("TASK 4");
	tempTasks.add("TASK 5");
	tempTasks.add("TASK 6");
	tempTasks.add("TASK 7");
	tempTasks.add("TASK 8");
	tempTasks.add("TASK 9");
	tempTasks.add("TASK 10");
	tempTasks.add("TASK 11");
	tempTasks.add("TASK 12");
	tempTasks.add("TASK 13");
	tempTasks.add("TASK 14");
	tempTasks.add("TASK 15");
	tempTasks.add("TASK 16");
	tempTasks.add("TASK 17");
	tempTasks.add("TASK 18");
	tempTasks.add("TASK 19");
	tempTasks.add("TASK 20");

    }

    public void run()
    {
	while (!tempTasks.isEmpty())
	{

	    logger.info("Manager entered loop of workers");
	    for (Entry<String, Worker> currentEntry : workers.entrySet())
	    {
		logger.info("Iterating over Workers map");
		Worker currentWorker = currentEntry.getValue();
		try
		{
		    if (currentWorker.isFinished())
		    {
			logger.info("Found free worker. Giving him a task");
			currentWorker.execute(tempTasks.pop());
		    }
		} catch (RemoteException e)
		{
		    logger.error("Problems while searching for free worker ", e);
		}
	    }
	    synchronized (this)
	    {
		try
		{
		    logger.info("Manager starting waiting");
		    wait();
		} catch (InterruptedException e)
		{
		    logger.error("Manager interrupted while waiting");
		}
	    }

	}
	System.out.println("All tasks done");

    }

    public void wakeUp(Worker worker) throws RemoteException
    {
	logger.info("Manager got wakeUp call");
	if (!workers.containsKey(worker.getName()))
	{
	    logger.info("Adding worker to a map");
	    workers.put(worker.getName(), worker);
	}
	synchronized (this)
	{
	    notify();
	}

    }

}
