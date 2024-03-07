package org.example.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.properties.GrpcClientProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.example.client.NumberServiceGrpc;
import org.example.client.NumberRequest;

import java.util.concurrent.TimeUnit;

public class GrpcClient {
    private static final Logger log = LoggerFactory.getLogger(GrpcClient.class);
    private static GrpcClientProperties grpcClientProperties = new GrpcClientProperties();

    public GrpcClient() {

    }

    public static void clientInit(int data) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(grpcClientProperties.getHost(), grpcClientProperties.getPort())
                .usePlaintext()
                .build();

        if (data > 0) {
            log.info("startGrpcClient :: Starting gRPC Client with requestParam {}", data);
        }

        try {
            int roundRobinNumber = 1;
            while (true) {
                if (data > 0) {
                    int inputNumber = data;
                    startClient(inputNumber, channel);
                } else {
                    if (roundRobinNumber > 3) {
                        roundRobinNumber = 1;
                    }
                    startClient(roundRobinNumber, channel);
                    roundRobinNumber++;
                }
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
            log.error("startGrpcClient :: Error in client: {}", e.getMessage());
        }
    }

    private static void startClient(int inputNumber, ManagedChannel channel) {

        var stub = org.example.client.NumberServiceGrpc.newBlockingStub(channel);
        NumberRequest request = NumberRequest.newBuilder().setNumber(inputNumber).build();
        long startTime = System.currentTimeMillis();
        var response = stub.getNumber(request);
        long endTime = System.currentTimeMillis();
        log.info("number - {} :: txId - {} :: {}ms", response.getNumber(), response.getTxId(), (endTime - startTime));
    }


}
