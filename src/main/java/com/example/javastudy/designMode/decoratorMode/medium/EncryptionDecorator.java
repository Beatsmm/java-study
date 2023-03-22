package com.example.javastudy.designMode.decoratorMode.medium;

import java.util.Base64;

public class EncryptionDecorator extends DataSourceDecorator{


    public EncryptionDecorator(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void writeData(String data) {
        super.writeData(data);
    }

    @Override
    public String readData() {
        return super.readData();
    }

    private String encode(String data){
        byte[] result = data.getBytes();
        for (int i = 0; i < result.length; i++) {
            result[i] += (byte)1;
        }
        return Base64.getEncoder().encodeToString(result);
    }

    private String decode(String data){
        byte[] result = Base64.getDecoder().decode(data);
        for (int i = 0; i < result.length; i++) {
            result[i] -= (byte) 1;
        }
        return new String(result);
    }
}
