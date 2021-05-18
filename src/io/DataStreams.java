package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.EOFException;

public class DataStreams {
    static final String dataFile = "assets/invoicedata";

    static final double[] prices = { 19.99, 9.99, 15.99, 3.99, 4.99 };
    static final int[] units = { 12, 8, 13, 29, 50 };
    static final String[] descs = { "Java T-shirt",
            "Java Mug",
            "Duke Juggling Dolls",
            "Java Pin",
            "Java Key Chain" };

    public static void main(String[] args) throws IOException {

 
        // DataOutputStream out = null;
        
        // try {
        //     out = new DataOutputStream(new
        //             BufferedOutputStream(new FileOutputStream(dataFile)));

        //     for (int i = 0; i < prices.length; i ++) {
        //         out.writeDouble(prices[i]);
        //         out.writeInt(units[i]);
        //         out.writeUTF(descs[i]);
        //     }
        // } finally {
        //     out.close();
        // }

        try (
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)));
        ) {
            for (int i = 0; i < prices.length; i++) {
                out.writeDouble(prices[i]);
                out.writeInt(units[i]);
                out.writeUTF(descs[i]);
            }   
        }

        // DataInputStream in = null;
        // double total = 0.0;
        // try {
        //     in = new DataInputStream(new
        //             BufferedInputStream(new FileInputStream(dataFile)));

        //     double price;
        //     int unit;
        //     String desc;

        //     try {
        //         while (true) {
        //             price = in.readDouble();
        //             unit = in.readInt();
        //             desc = in.readUTF();
        //             System.out.format("You ordered %d units of %s at $%.2f%n",
        //                     unit, desc, price);
        //             total += unit * price;
        //         }
        //     } catch (EOFException e) { }
        //     System.out.format("For a TOTAL of: $%.2f%n", total);
        // }
        // finally {
        //     in.close();
        // }

        try (
            DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(dataFile)));
        ) {
            //Bad programming technique: use floating point numbers to represent monetary values
            double total = 0.0;

            double price;
            int unit;
            String desc;

            try {
                while (true) {
                    price = in.readDouble();
                    unit = in.readInt();
                    desc = in.readUTF();
                    System.out.format("%d units of %s at price $%.2f%n", unit, desc, price);
                    total += unit * price;
                }
            } catch (EOFException e) {
                System.out.format("Total price: $%.2f%n", total);
            }
        }
    }
}