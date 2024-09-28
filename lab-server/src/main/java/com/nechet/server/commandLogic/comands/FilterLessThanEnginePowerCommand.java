package com.nechet.server.commandLogic.comands;

import com.nechet.common.util.model.Vehicle;
import com.nechet.common.util.requestLogic.CommandDescription;
import com.nechet.server.system.VehiclesManager;

import java.util.ArrayList;
import java.util.LinkedList;

public class FilterLessThanEnginePowerCommand implements BaseCommand {
    private final String name = "filter_less_than_engine_power";

    private String result = "";

    public String getResult() {
        return result;
    }

    @Override
    public void execute(CommandDescription d) {
        ArrayList<String> str = d.getArgs();
        LinkedList<Vehicle> coll = VehiclesManager.getInstance().getCollection();
        try {
            double health = Double.parseDouble(str.get(1));
            if (coll.isEmpty()) {
                result += "В коллекции нет объектов";
            } else {
                coll.stream().filter(obj -> health > obj.getPower()).forEach(obj -> result += obj.toString() + "\n");
            }
        } catch (NumberFormatException e) {
            result += "Не правильный ввод переменной engine power. Попробуйте заново.";
        }
    }

    @Override
    public String descr() {
        return " engine_power - выводит элементы, значение поля enginePower которых меньше заданного";
    }

    @Override
    public String getName() {
        return this.name;
    }
}
