package com.nechet.server.commandLogic.comands;

import com.nechet.common.util.model.Vehicle;
import com.nechet.common.util.requestLogic.CommandDescription;
import com.nechet.server.system.VehiclesManager;
import com.nechet.server.system.Utils;

import java.util.LinkedList;

public class RemoveGreaterCommand implements BaseCommand{
    private final String name = "remove_greater";
    private String result = "";
    public String getResult(){
        return result;
    }

    @Override
    public void execute(CommandDescription d){
        LinkedList<Vehicle> coll = VehiclesManager.getInstance().getCollection();
        Vehicle newVehicle = d.getObjectArray().get(0);
        newVehicle.setId(Utils.getNewId());
        coll.removeIf(vh -> newVehicle.getPower() < vh.getPower());
        result+="Удалены все объекты больше полученного";
    }

    @Override
    public String descr() {
        return "Удаляет элементы, большие заданного";
    }

    @Override
    public String getName() {
        return this.name;
    }
}
