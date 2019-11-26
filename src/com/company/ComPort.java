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
        if (this.sPort.isOpen()){
            sPort.writeBytes(packet,(long)size);
        }
    }


    public byte[] receive(int tamanho){
        byte[] pacote = new byte[tamanho];
        if (this.sPort.isOpen()){
            sPort.readBytes(pacote,tamanho);
            return pacote;
        }
        return null;
    }

    public boolean receiveConf() {

        byte[] t = new byte[4];
        byte ack = 1;

        if (this.sPort.openPort()){

            try {

                while (sPort.bytesAvailable() != 4) {
                    Thread.sleep(300);
                }

                byte[] readBuffer = new byte[sPort.bytesAvailable()];
                sPort.readBytes(readBuffer,readBuffer.length);
                System.out.println("\nACK LIDO\n");

            }catch(Exception e){e.printStackTrace();}
        }
            sPort.readBytes(t,4);
        System.out.println("\n T POS [1] = "+t[1]+" \n");
        System.out.println(t[1] == ack);
            return (t[1] == ack);
        }


    public int getBytesAv(){ return this.sPort.bytesAvailable(); }

}
