package rmi.task.worker;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Worker extends Remote
{
    public String getName() throws RemoteException;

    public void execute(String json) throws RemoteException;

    public boolean isFinished() throws RemoteException;
}
