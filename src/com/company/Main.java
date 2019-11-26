package com.company;

public class Main {


    public static void main(String[] args) throws Exception {
        // Menu m = new Menu();
        byte x=0;
        String fName;


        //Scanner scan = new Scanner(System.in);
        //System.out.println("Insira o nome do ficheiro que pretende enviar\n");
        //fName = scan.nextLine();
        Packet p = new Packet("COM2",9600,"tracy-chapman.jpg");
        p.sendPacket();



    /*
    Header h = new Header((byte)00\0);
    for(int i=0; i<h.getHeader().length; i++){
        h.getHeader();
        System.out.println(h.getHeader()[i] & 0xFF);

    }
*/

    }


}
