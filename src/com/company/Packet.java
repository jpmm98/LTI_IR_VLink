package com.company;

import static com.company.FRead.DataSize;

public class Packet {

    private ComPort cp;
    FRead fr;
    byte sq;
    boolean flag = true;
    byte[] lastPacket;


    public Packet(String PortName, int BaudBRate, String FileName) throws Exception {

        this.cp = new ComPort(PortName, BaudBRate);
        fr = new FRead(FileName);




    }

    public void sendPacket() throws Exception {


            do {
                try {

                    Send_T sendTrama = new Send_T();
                    byte[] pack = sendTrama.dataT(fr.getData(), this.sq);
                    this.cp.send(pack, pack.length);


                    switch (this.cp.receiveConf()) {

                        case (byte)1:
                            lastPacket = pack;

                            if (sq == 0) {
                                this.sq = 1;
                            } else {
                                this.sq = 0;
                            }
                            break;
                        case (byte)2:
                            this.cp.send(lastPacket,lastPacket.length);
                            System.out.println("Re-Sending Packet");
                            break;

                    }
                    if(pack.length < DataSize+8){
                        this.flag=false;
                    }

                    System.out.println("\nBytes availables :"+pack.length);
                } catch (Exception e) {
                    System.out.println("Erro no envio!");
                    e.printStackTrace();
                }



            }while (this.flag);

    }
}
