package com.nechet.server.commandLogic.comands;

import com.nechet.common.util.model.Vehicle;
import com.nechet.common.util.model.VehicleType;
import com.nechet.common.util.requestLogic.CommandDescription;
import com.nechet.server.system.VehiclesManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class CountLessThanTypeCommand implements BaseCommand{
    private final String name = "count_less_than_type";
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
                AtomicInteger count = new AtomicInteger();
                coll.stream().filter(vh -> type.ordinal() > vh.getType().ordinal()).forEach(vh -> count.addAndGet(1));
                result += count.toString();
            }
        } catch (IllegalArgumentException | EnumConstantNotPresentException e) {
            result+="Нет такой переменной. Попробуйте заново.";
        }
    }

    @Override
    public String descr() {
        var types = Arrays.toString(VehicleType.values());
        return "Выводит количество элементов коллекции, значение поля type которых меньше заданного\n" +
                "   Возможные аргументы команды: "+ types;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
