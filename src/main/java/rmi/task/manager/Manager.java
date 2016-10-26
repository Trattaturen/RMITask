package rmi.task.manager;

import java.rmi.Remote;
import java.rmi.RemoteException;

import rmi.task.worker.Worker;

public interface Manager extends Remote
{
    public void wakeUp(Worker worker) throws RemoteException;
}
