package com.uniroma1.speeddetectoranalyzer.handler;

import com.google.gson.Gson;
import com.uniroma1.commons.utility.ParamConnection;
import com.uniroma1.speeddetectoranalyzer.producer.Producer;

public class MessageHandler {
    private Gson gson = new Gson();
    public Object handleMessage(String message, Class cls) {
        Object obj = gson.fromJson(message, cls);
        return obj;
    }

    public void sendObject(Object message, String queue) {
        Producer producter = new Producer(ParamConnection.BROKER_URL, queue);
        String text = gson.toJson(message);
        producter.sendMessage(text);
    }
}
