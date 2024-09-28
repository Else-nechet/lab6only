package com.nechet.server.commandLogic.comands;

import com.nechet.common.util.exceptions.WrongValuesOfCommandArgumentException;
import com.nechet.common.util.model.Vehicle;
import com.nechet.common.util.requestLogic.CommandDescription;
import com.nechet.server.system.VehiclesManager;
import com.nechet.server.system.UserConsole;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.LinkedList;

public class RemoveByIdCommand implements BaseCommand{
    private final String name = "remove_by_id";
    private String result = "";
    public String getResult(){
        return result;
    }
    @Override
    public void execute(CommandDescription d) throws WrongValuesOfCommandArgumentException {
        VehiclesManager colMan = VehiclesManager.getInstance();
        LinkedList<Vehicle> coll = colMan.getCollection();
        ArrayList<String> str = d.getArgs();
        long id;
        try{
            id = Long.parseLong(str.get(1));
            if(!coll.removeIf(marine -> Objects.equals(marine.getId(), id)))
            {
                result+="Элемента с таким Id коллекции нет";
            }
            else {
                colMan.setCollection(coll);
                result+="Успешно удалено!";
            }
        } catch (NumberFormatException e) {
           result+="Неправильный ввод Id. Попробуйте заново.";
        }

    }

    @Override
    public String descr() {
        return "Удаляет элемент, Id которого равен указанному.\n";
    }

    @Override
    public String getName() {
        return this.name;
    }
}
