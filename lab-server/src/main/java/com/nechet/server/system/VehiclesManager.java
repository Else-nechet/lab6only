package com.nechet.server.system;

import com.nechet.common.util.model.Vehicle;
import com.nechet.common.util.model.comparators.VehicleEnginePowerComparator;

import java.time.Instant;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

public class VehiclesManager implements CollectionReceiver<LinkedList<Vehicle>, Vehicle> {
    static private VehiclesManager collection;
    public static  VehiclesManager getInstance(){
        if (collection == null){
            collection = new VehiclesManager();
        }
        return collection;
    }
    private LinkedList<Vehicle> vehicles;
    private final Date initDate;

    private VehiclesManager() {
        vehicles = new LinkedList<>();
        initDate = Date.from(Instant.now());
    }

    @Override
    public LinkedList<Vehicle> getCollection() {
        return vehicles;
    }

    @Override
    public void setCollection(LinkedList<Vehicle> value) {
        this.vehicles = value;
    }

    @Override
    public void addElementToCollection(Vehicle value) {
        this.vehicles.add(value);
    }

    @Override
    public void clearCollection() {
        this.vehicles.clear();
    }

    @Override
    public void sort() {
        LinkedList<Vehicle> sortedVehicles = new LinkedList<>();

        for (Iterator<Vehicle> obj = vehicles.stream().sorted(new VehicleEnginePowerComparator()).iterator(); obj.hasNext(); ) {
            Vehicle sortedItem = obj.next();

            sortedVehicles.add(sortedItem);
        }

        this.vehicles = sortedVehicles;
    }


    @Override
    public Date getInitDate() {
        return initDate;
    }

    public int getSize(){
        return vehicles.size();
    }
    public  Vehicle getMinElement(Comparator<Vehicle> comparator){
        return getCollection().stream().min(comparator).orElse(null);
    }
    public Vehicle getMaxElement(Comparator<Vehicle> comparator){
        return getCollection().stream().max(comparator).orElse(null);
    }
}
