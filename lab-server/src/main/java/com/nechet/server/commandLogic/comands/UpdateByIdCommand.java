package com.nechet.server.commandLogic.comands;

import com.nechet.common.util.model.Vehicle;
import com.nechet.common.util.requestLogic.CommandDescription;
import com.nechet.server.system.VehiclesManager;

import java.util.ArrayList;
import java.util.Objects;
import java.util.LinkedList;

public class
UpdateByIdCommand implements BaseCommand {
    private final String name = "update";
    private String result = "";
    public String getResult(){
        return result;
    }
    @Override
    public void execute(CommandDescription d){
        VehiclesManager colMan = VehiclesManager.getInstance();
        LinkedList<Vehicle> coll = colMan.getCollection();
        ArrayList<String> str = d.getArgs();
        long id;
        try{
            id = Long.parseLong(str.get(1));
            if(!coll.removeIf(vh -> Objects.equals(vh.getId(), id)))
            {
                result+=("Элемента с таким Id коллекции нет");
            }
            else{
                Vehicle update = d.getObjectArray().get(0);
                update.setId(id);
                coll.add(update);
                colMan.setCollection(coll);
            }
        } catch (NumberFormatException e) {
            result+=("Неправильный ввод значения Id. Попробуйте заново.");
        }

    }

    @Override
    public String descr() {
        return "Обновляет элемент, Id которого равен указанному.\n";
    }

    @Override
    public String getName() {
        return this.name;
    }
}
