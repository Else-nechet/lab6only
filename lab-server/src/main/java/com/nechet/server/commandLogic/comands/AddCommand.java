package com.nechet.server.commandLogic.comands;

import com.nechet.common.util.model.Vehicle;
import com.nechet.common.util.requestLogic.CommandDescription;
import com.nechet.server.system.CollectionReceiver;
import com.nechet.server.system.VehiclesManager;
import com.nechet.server.system.Utils;

import java.util.LinkedList;
public class AddCommand implements BaseCommand{
    private final String name = "add";
    private String result = "";

    @Override
    public void execute(CommandDescription descr){
        CollectionReceiver<LinkedList<Vehicle>, Vehicle> colMan = VehiclesManager.getInstance();
        Vehicle vehicle = descr.getObjectArray().get(0);
        vehicle.setId(Utils.getNewId());
        colMan.addElementToCollection(vehicle);
        result +="Марин успешно добавлен в коллекцию";
    }
    public String getResult(){
        return result;
    }

    @Override
    public String descr() {
        return "Добавляет объект в коллекцию";
    }

    @Override
    public String getName() {
        return this.name;
    }
}
