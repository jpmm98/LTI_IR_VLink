package com.company;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static com.company.FRead.DataSize;

public class Trama {

    private byte flag;
    private byte[] DataPacket;
    private ByteArrayOutputStream baos;

    public Trama(){
        this.flag = 126;
        this.baos = new ByteArrayOutputStream(DataSize+8);

    }

    // Junta o lido do buffer tudo num array DataPacket
    public byte[] getDataPacket() {
        this.DataPacket = baos.toByteArray();
        return DataPacket;
    }
    //2 Bytes
    //Junta o byte de sequencia e o identificador do tipo de trama
    public void buildHead(byte type,byte sq) throws Exception{
        Header h = new Header(type);
        baos.write(h.getHeader(sq));

        System.out.println("\nHeader adicionado");
    }
    //1000 Bytes (depende do DataSize)
    //Junta os dados do ficheiro á trama
    public void buildData(byte[] data)throws Exception{
        baos.write(data);
        this.DataPacket = this.baos.toByteArray();
        System.out.println(" Data adicionado");
    }

    //4 Bytes (CRC32)
    //So pode ser chamado após a montagem dos dados na trama
    public void buildTrailer() throws IOException {
        Trailer t = new Trailer(this.DataPacket);
        baos.write(t.getCrcRes());

        System.out.println(" Trailer adicionado\n");
    }
    //2 Bytes
    //Junta ambas as Flags
    public void buildFlag(){

        baos.write(this.flag);

    }


    public void resetOutStream(){
        this.baos.reset();
    }

}
