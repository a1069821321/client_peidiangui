package com.client.test;
        import java.awt.Color;
        import java.awt.image.BufferedImage;
        import java.io.File;
        import java.io.IOException;

        import javax.imageio.ImageIO;

public class Gaussian_Smoothing {
    public static void main(String[] args) throws IOException {
            start("人3");
    }
    public static void start(String path){
        try{
            String ppath = "D:\\热成像图片处理\\人3__gao.png";
            String opath = "D:\\热成像图片处理\\人3.png";
        BufferedImage img = ImageIO.read(new File(opath)); //将这个图片拷贝到你项目根目录下
        System.out.println(img);
        int height = img.getHeight();
        int width = img.getWidth();
        int[][] martrix = new int[3][3];
        int[] values = new int[9];
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++) {
                readPixel(img, i, j, values);
                fillMatrix(martrix, values);
                img.setRGB(i, j, avgMatrix(martrix));
            }
        ImageIO.write(img, "jpeg", new File(ppath));}
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void readPixel(BufferedImage img, int x, int y, int[] pixels) {
        int xStart = x - 1;
        int yStart = y - 1;
        int current = 0;
        for (int i = xStart; i < 3 + xStart; i++)
            for (int j = yStart; j < 3 + yStart; j++) {
                int tx = i;
                if (tx < 0) {
                    tx = -tx;

                } else if (tx >= img.getWidth()) {
                    tx = x;
                }
                int ty = j;
                if (ty < 0) {
                    ty = -ty;
                } else if (ty >= img.getHeight()) {
                    ty = y;
                }
                pixels[current++] = img.getRGB(tx, ty);

            }
    }

    private static void fillMatrix(int[][] matrix, int[] values) {
        int filled = 0;
        for (int i = 0; i < matrix.length; i++) {
            int[] x = matrix[i];
            for (int j = 0; j < x.length; j++) {
                x[j] = values[filled++];
            }
        }
    }

    private static int avgMatrix(int[][] matrix) {
        int r = 0;
        int g = 0;
        int b = 0;
        for (int i = 0; i < matrix.length; i++) {
            int[] x = matrix[i];
            for (int j = 0; j < x.length; j++) {
                if (j == 1) {
                    continue;
                }
                Color c = new Color(x[j]);
                r += c.getRed();
                g += c.getGreen();
                b += c.getBlue();
            }
        }
        return new Color(r / 8, g / 8, b / 8).getRGB();

    }
}
