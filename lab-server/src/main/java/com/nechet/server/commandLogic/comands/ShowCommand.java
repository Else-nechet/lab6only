package com.nechet.server.commandLogic.comands;

import com.nechet.common.util.model.Vehicle;
import com.nechet.common.util.requestLogic.CommandDescription;
import com.nechet.server.system.VehiclesManager;
import com.nechet.server.system.UserConsole;

import java.util.Scanner;
import java.util.LinkedList;

public class ShowCommand implements BaseCommand{
    private final String name = "show";
    private String result = "";
    public String getResult(){
        return result;
    }

    @Override
    public void execute(CommandDescription d) {
        LinkedList<Vehicle> coll = VehiclesManager.getInstance().getCollection();
        if (coll.isEmpty()){
            result+="В коллекции нет объектов";
        } else {
            coll.forEach(obj -> result+=obj.toString()+"\n");
        }
    }

    @Override
    public String descr() {
        return "Выводит все элементы коллекции в строковом представлении";
    }

    @Override
    public String getName() {
        return this.name;
    }
}
