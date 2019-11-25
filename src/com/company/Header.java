package com.company;

import java.io.ByteArrayOutputStream;

public class Header {

    private boolean flag = true;
    private int seqN;       //Identificador do pacote
    private byte sq;
    private byte type;      //Identificador do tipo de pacote
    private ByteArrayOutputStream bous;


    public Header(byte type){
        this.bous = new ByteArrayOutputStream(2);
        this.type = type;

    }
    public byte[] getHeader(byte x){
        byte[] allHead;

        this.sq = x;

        bous.write(this.type);
        bous.write(this.sq);

        this.seqN++;
        allHead = bous.toByteArray();
        bous.reset();

        return allHead;
    }

    public int getSeq() { return seqN;}

    public byte getType() {
        switch (type){
            case 0: System.out.println("Trama de Informação");
            case 1: System.out.println("ACK - Acknowledge");
            case 2: System.out.println("NACK");
            case 3: System.out.println("POLL");
            case 4: System.out.println("SELECT");
            case 5: System.out.println("FIM da trama");
        }
        return type;}

}
