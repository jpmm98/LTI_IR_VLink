package com.company;

import java.util.Scanner;

public class Menu {
        private String porta = "COM1";
        private int baudrate;
        private String fName = "test1.txt";

    public Menu() throws Exception {
        Scanner aux = new Scanner(System.in);
        System.out.println("Insira o valor de baudrate\n");
        this.baudrate = aux.nextInt();
        ComPort CP = new ComPort(porta,baudrate);

        if(CP.portStatus()){
            System.out.println("Conexao efetuada com a porta serie\n");
        }

        System.out.println("Quer receber ou enviar um ficheiro?\n");
        System.out.println("1-Receber ficheiro 2-Enviar ficheiro\n");
        int chose = aux.nextInt();
        if (chose != 1 && chose != 2) {
            System.out.println("Insira apenas os números apresentados\n");
        }

        switch (chose) {
            case (1):
                //receberFicheiro rF = new receberFicheiro(filename, porta, baudrate);
                //rF.receber();

                break;
            case (2):

                Packet eF = new Packet(porta, baudrate,fName);
                eF.sendPacket();
                break;
        }
    }
}
