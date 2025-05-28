package org.example.jobby;

import org.example.jobby.Model.FileData;

import java.nio.charset.StandardCharsets;

public class Launcher {

    static {
        System.setProperty("org.apache.pdfbox.rendering.UsePureJavaImplementation", "true");

    }
    public static void main(String[] args) {
        Main.main(args);
    }
}

