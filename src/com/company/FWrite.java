package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FWrite {

    private FileOutputStream oStream;
    private String fName;
    private long fSize;

    public FWrite (String fN) throws FileNotFoundException {
        this.fName = fN;

        File dir = new File(".\\DFiles\\");

        if(!dir.mkdir()){
            System.out.println("Erro na localização da Directoria");
            return;
        }
        File f = new File(".\\DFiles\\" + this.fName);

        this.oStream = new FileOutputStream(f);
    }

    public void writeData(byte[] data) {

        try{
            this.oStream.write(data);
        }catch(IOException e){
            System.out.println("Exception: " + e);
        }

    }

    public long getfSize()      { return new File(this.fName).length(); }
    public String getfName()    { return fName; }

}
