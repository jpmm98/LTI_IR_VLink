package com.company;

import com.fazecast.jSerialComm.*;

public class ComPort {

    private String portN;
    private SerialPort sPort;
    private int baudrate;


    public ComPort(String nomePorta, int baudRate){
        this.portN = nomePorta;
        this.baudrate = baudRate;

        this.sPort = SerialPort.getCommPort(this.portN);
        this.sPort.openPort();
        this.sPort.setComPortParameters(this.baudrate,8, 1, 0);

    }

    public boolean portStatus()     {return this.sPort.openPort();}

    public void send(byte[] packet, int size){
        int[] timer = new int[100];

        while (!this.sPort.isOpen()){
            this.reconect();
        }
            sPort.writeBytes(packet,(long)size);


    }


    public byte[] receive(int tamanho){
        byte[] pacote = new byte[tamanho];
        if (this.sPort.isOpen()){
            sPort.readBytes(pacote,tamanho);
            return pacote;
        }
        return null;
    }

    public byte receiveConf() {

        byte[] t = new byte[4];

        if (this.sPort.openPort()){

            try {

                while (sPort.bytesAvailable() != 4) {
                    Thread.sleep(300);
                }

                byte[] readBuffer = new byte[sPort.bytesAvailable()];
                sPort.readBytes(readBuffer,4);
                t = readBuffer;

            }catch(Exception e){e.printStackTrace();}
        }

        System.out.println("\nResponse received: ");
        for(int i = 0;i<t.length;i++){
            System.out.print(t[i]);
        }
            return t[1];
        }

    public void reconect(){
        int[] timer = new int[100];
        System.out.println("\nTrying to open port...");

        for (int t: timer) {
            while(!this.sPort.isOpen()){

                try{
                    this.sPort.openPort();
                    System.out.print(".");
                    Thread.sleep(1000);
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        }
    }

    public int getBytesAv(){ return this.sPort.bytesAvailable(); }

}
