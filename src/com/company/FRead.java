package com.company;

import java.io.File;
import java.io.FileInputStream;

public class FRead {

    public static int DataSize = 1000;

    private FileInputStream inStream;
    private String fName;
    private long fSize;

    public FRead(String fN) throws Exception{
        this.fName = fN;

        File dir = new File(".\\UPFiles\\");
        dir.mkdir();

        try {
            File f = new File(".\\UPFiles\\" + this.fName);
            this.fSize = f.length();
            System.out.println("File size: "+this.fSize);
            this.inStream = new FileInputStream(f);
        }catch (Exception e){
            System.out.println("Erro a encontrar o ficheiro");
            e.printStackTrace();

        }

    }


    public byte[] getData()throws Exception{

        byte[] dgram;


        if (this.fSize<DataSize){
            dgram = new byte[(int)this.fSize];
            this.fSize = 0;
        }else {
            this.fSize -= DataSize;
            dgram = new byte[DataSize];
        }
        this.inStream.read(dgram);


        return dgram;
    }

    public boolean endOfFile(){
        System.out.println("\nFaltam "+this.fSize+" Bytes para acabar o ficheiro");

        return this.fSize == 0;

    }
    public long getfSize()      {   return fSize;}
    public String getfName()    {   return fName;}

}
