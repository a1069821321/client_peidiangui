package com.client.modules;

import com.client.Main_window;

import java.awt.*;

public class MessagePrint {

    public void print(String information){
        Main_window.information_public.appendText("["+GetTime.gettime()+"]"+information+"\n");
    }
}
