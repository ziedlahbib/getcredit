package tn.esprit.spring.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;


@RestController
public class OCRController {
    private Tesseract tesseract = new Tesseract();

    @PostMapping("/ocr")
    public String ocr(@RequestParam("file") MultipartFile file) throws IOException, TesseractException {
        File imageFile = convertMultipartFileToFile(file);

//        // Load OpenCV native library
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//
//        // Load image using OpenCV
//        Mat image = Imgcodecs.imread(imageFile.getAbsolutePath());
//
//        // Resize image to a smaller size
//        Size newSize = new Size(800, (int) (800 * (double) image.rows() / image.cols()));
//        Imgproc.resize(image, image, newSize);
//
//        // Save resized image to a temporary file
//        File tempImage = File.createTempFile("tempImage", ".jpg");
//        Imgcodecs.imwrite(tempImage.getAbsolutePath(), image);

        // Perform OCR on the resized image using Tesseract
        tesseract.setDatapath("C:\\Users\\lahbi\\Documents\\GitHub\\getcredit\\tessdata-main");
//        tesseract.setLanguage("eng");
//        tesseract.setPageSegMode(11); // set page segmentation mode
//        tesseract.setTessVariable("user_defined_dpi", "300");
        String result = tesseract.doOCR(imageFile);

        return result;
    }

    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
        return convertedFile;
    }
    

    
}