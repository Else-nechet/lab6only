package com.nechet.server.fileManipulation;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.nechet.common.util.model.Vehicle;
import com.nechet.server.system.UserConsole;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Scanner;
import java.util.TreeSet;

public class ReadJSONCollection implements ReadFromFileObject<TreeSet<Vehicle>> {
    @Override
    public TreeSet<Vehicle> read(String path) {
        Gson gson = new Gson();
        Type type = new TypeToken<TreeSet<Vehicle>>() {
        }.getType();
        UserConsole console = new UserConsole(new Scanner(System.in));
        console.printLine(path);
        TreeSet<Vehicle> vehicles = new TreeSet<Vehicle>();
        try {
            Reader file = new FileReader(path);
            vehicles = gson.fromJson(file, type);
            return vehicles;
        } catch (FileNotFoundException e) {
            console.printLine("Предыдущий файл не был найден или его не существовало.");
            return vehicles;
        } catch (JsonSyntaxException e){
            File f = new File(path);
            if (f.exists()) {
                f.delete();
            }
            try {
                f.createNewFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            console.printLine("Предыдущий файл не правильно записан в Json. Он удален и создан новый");
            return vehicles;
        }
    }
}
