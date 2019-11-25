package com.company;

import java.nio.ByteBuffer;
import java.util.zip.CRC32;

public class Trailer {

    private byte[] crcRes;

    public Trailer(byte[] dataP){

        CRC32 crc = new CRC32();
        crc.update(dataP);

        crcRes = ByteBuffer.allocate(4).putInt((int)crc.getValue()).array();


    }

    public byte[] getCrcRes() {return crcRes;}
}
