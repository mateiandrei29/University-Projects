package services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CityObserver extends Remote {
    void eventOccured() throws RemoteException;


}
