package com.nechet.server;

import com.nechet.common.util.model.Vehicle;
import com.nechet.server.connectionLogic.RequestHandler;
import com.nechet.server.connectionLogic.TCPserver;
import com.nechet.server.fileManipulation.ReadFromFileObject;
import com.nechet.server.fileManipulation.ReadJSONCollection;
import com.nechet.server.system.VehiclesManager;
import com.nechet.server.system.Utils;

import java.util.LinkedList;

public class Server {
        public static void main(String[] args) throws Exception {

            Utils.setEnv("lab5");
            RequestHandler requestHandler = new RequestHandler();
            TCPserver server = new TCPserver(requestHandler);
            VehiclesManager vehicleManager = VehiclesManager.getInstance();
            ReadFromFileObject<LinkedList<Vehicle>> reader = new ReadJSONCollection();
            LinkedList<Vehicle> vehicles = reader.read(System.getenv(Utils.getEnv())+"/Vehicles.json");
            vehicleManager.setCollection(vehicles);
            try {
                server.openConnection();
                System.out.println("Сервер запущен");
                server.run();
                Runtime.getRuntime().addShutdownHook(new Thread() {
                    {
                        System.out.println("Получен сигнал завершения работы (Ctrl+D).");
                        System.out.println("Закрываем программу");
                        server.close();
                        System.exit(1);
                    }

                    @Override
                    public void run() {
                    }
                });
            } finally {
                server.close();
            }
        }
    }

