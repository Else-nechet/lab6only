package com.nechet.server.commandLogic.comands;


import com.nechet.common.util.requestLogic.CommandDescription;
import com.nechet.server.system.VehiclesManager;


public class InfoCommand implements BaseCommand{
    private final String name = "info";
    private String result = "";
    public String getResult(){
        return result;
    }
    @Override
    public void execute(CommandDescription d) {
        var colMan = VehiclesManager.getInstance();
        result+="Тип коллекции: "+colMan.getCollection().getClass()+"\n";
        result+="Дата создания: "+colMan.getInitDate().toString()+"\n";
        result+="Количество элементов: "+colMan.getSize()+"\n";
    }

    @Override
    public String descr() {
        return "Показывает информацию о коллекции(тип, дату создания, количество элементов)";
    }

    @Override
    public String getName() {
        return this.name;
    }
}

