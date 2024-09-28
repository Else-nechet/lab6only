package com.nechet.server.commandLogic.comands;

import com.nechet.common.util.model.*;
import com.nechet.common.util.requestLogic.CommandDescription;
import com.nechet.server.system.VehiclesManager;
import com.nechet.server.system.UserConsole;

import java.net.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.LinkedList;

public class FilterGreaterThanTypeCommand implements BaseCommand{
    private final String name = "filter_greater_than_type";
    private String result = "";
    public String getResult(){
        return result;
    }

    @Override
    public void execute(CommandDescription d){
        LinkedList<Vehicle> coll = VehiclesManager.getInstance().getCollection();
        ArrayList<String> str = d.getArgs();

        try {
            VehicleType type = VehicleType.valueOf(str.get(1));
            if (coll.isEmpty()) {
                result+="В коллекции нет объектов";
            } else {
                coll.stream().filter(obj -> type.ordinal() < obj.getType().ordinal()).forEach(obj -> result+=obj.toString()+"\n");
            }
        } catch (IllegalArgumentException | EnumConstantNotPresentException e) {
            result+="Нет такой переменной. Попробуйте заново.";
        }
    }

    @Override
    public String descr() {
        var types = Arrays.toString(VehicleType.values());
        return "Выводит все элементы коллекции, значение поля type которых больше заданного\n" +
                "   Возможные аргументы команды: "+ types;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
