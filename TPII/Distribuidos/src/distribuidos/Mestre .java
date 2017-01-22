/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuidos;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

/**
 *
 * @author amanda
 */
public class Mestre extends UnicastRemoteObject{
    
    public Mestre() throws RemoteException{
        super();
    }
}
