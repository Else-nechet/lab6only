package com.nechet.server.commandLogic.comands;

import com.nechet.common.util.model.Vehicle;
import com.nechet.common.util.requestLogic.CommandDescription;
import com.nechet.server.commandLogic.ServerCommandManager;
import com.nechet.server.system.VehiclesManager;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class HeadCommand implements BaseCommand {
    private final String name = "help";
    private String result = "";

    public String getResult() {
        return result;
    }

    @Override
    public void execute(CommandDescription d) {
        LinkedList<Vehicle> coll = VehiclesManager.getInstance().getCollection();
        try {
            result += coll.getFirst().toString();
        } catch (NoSuchElementException e){
            result += "Коллекция пуста!";
        }

    }


    @Override
    public String descr() {
        return "Показывает описание для каждой команды";
    }

    @Override
    public String getName() {
        return this.name;
    }
}