package com.company;

public class Send_T {

    public Send_T(){
    }

    //Envia uma trama de dados
    public byte[] dataT( byte[] data, byte sq) throws Exception{

        Trama d = new Trama();
        d.buildFlag();
        d.buildHead((byte) 0,sq);
        d.buildData(data);
        d.getDataPacket();
        d.buildTrailer();
        d.buildFlag();
        byte [] packet = d.getDataPacket();
        d.resetOutStream();

        for(int i = 0; i<packet.length; i++){
            System.out.print((Integer.toHexString(packet[i] & 0xFF) )+" ");
        }

        return packet;

    }

    //Envia um ACK
    public byte[] ackT(byte sq)throws Exception{
        Trama ack = new Trama();
        ack.buildFlag();
        ack.buildHead((byte)1,sq);
        ack.buildFlag();
        byte[] ackPACK = ack.getDataPacket();
        ack.resetOutStream();

        return ackPACK;

    }

    //Envia um NACK
    public byte[] nackT(byte sq)throws Exception{
        Trama nack = new Trama();
        nack.buildFlag();
        nack.buildHead((byte) 2,sq);
        nack.buildFlag();
        byte[] nackPACK = nack.getDataPacket();
        nack.resetOutStream();

        return nackPACK;

    }


}
