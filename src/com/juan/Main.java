package com.juan;

import java.io.*;

import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main  {

    public static void main(String[] args) throws IOException {

        DataInputStream in = null ;
        DataOutputStream out = null;
        Socket socket = null;
        Scanner sc = new Scanner(System.in);
        socket = new Socket("127.0.0.1", 8000);

        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        try {
            System.out.println("ingrese su usuario");
            LocalDateTime locaDate = LocalDateTime.now();
            int hours  = locaDate.getHour();
            int minutes = locaDate.getMinute();
            int seconds = locaDate.getSecond();

            String usuario = sc.nextLine();

            out.writeUTF(usuario);

            System.out.println("bienvenido " + in.readUTF());
            String mensaje = "";

            while (!mensaje.equals("bye")) {

                mensaje = sc.nextLine();
                out.writeUTF(mensaje);
                String envioServi = in.readUTF();
                System.out.println(envioServi);

            }

            sc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (out != null) out.close();
            if (in != null) in.close();
            if (socket != null) socket.close();
            System.out.println("conexion cerrada");
        }


    }


}