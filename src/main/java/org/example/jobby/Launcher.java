package org.example.jobby;



public class Launcher {

    static {
        System.setProperty("org.apache.pdfbox.rendering.UsePureJavaImplementation", "true");

    }
    public static void main(String[] args) {
        Main.main(args);
    }
}

