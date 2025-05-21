package org.example.jobby;

public class Launcher {
    // 在main方法前添加静态初始化块
    static {
        System.setProperty("org.apache.pdfbox.rendering.UsePureJavaImplementation", "true");
    }
    public static void main(String[] args) {
        Main.main(args);
    }
}

