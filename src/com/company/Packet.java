package com.company;

public class Packet {

    private ComPort cp;
    FRead fr;
    byte sq;


    public Packet(String PortName, int BaudBRate, String FileName) throws Exception {

        this.cp = new ComPort(PortName, BaudBRate);
        fr = new FRead(FileName);



    }

    public void sendPacket() throws Exception {

        do {


            do {
                try {

                    Send_T sendTrama = new Send_T();
                    byte[] pack = sendTrama.dataT(fr.getData(), this.sq);
                    this.cp.send(pack, pack.length);

                    if (sq == 0) {
                        this.sq = 1;
                    } else {
                        this.sq = 0;
                    }

                } catch (Exception e) {
                    System.out.println("Erro no envio!");
                    e.printStackTrace();
                }


            }while (this.cp.receiveConf());

        }while (!this.fr.endOfFile());
    }
}