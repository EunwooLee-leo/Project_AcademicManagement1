package Management.dept;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResizePNGImage {
    public static void main(String[] args) {
        try {
            // 원본 이미지 파일 경로
            File inputFile = new File("./image/snu.png");

            // 원본 이미지를 읽어옴
            BufferedImage originalImage = ImageIO.read(inputFile);

            // 새 이미지 크기 설정
            int newWidth = 80; // 새로운 너비
            int newHeight = 80; // 새로운 높이

            // 이미지 크기 조절
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = resizedImage.createGraphics();
            g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
            g2d.dispose();

            // 조절된 이미지를 파일로 저장
            File outputFile = new File("\\C:\\Users\\leo_m\\OneDrive\\바탕 화면\\resized.png");
            ImageIO.write(resizedImage, "png", outputFile);

            System.out.println("이미지 크기 조절 완료.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
