package org.example;

import org.example.client.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static GrpcClient grpcClient = new GrpcClient();
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        int inputNumber = initializeArguments(args);
        int param = inputNumber == -1 ? inputNumber : Math.abs(inputNumber);
        grpcClient.clientInit(param);
    }

    public static int initializeArguments(String[] args) {
        int inputNumber = -1;
        if (args.length > 0) {
            try {
                inputNumber = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                log.error("Error parsing input: {}", e.getMessage());
            }
        } else {
            log.info("No Input! gRPC client will work on Round Robin");
        }
        return inputNumber;
    }
}
