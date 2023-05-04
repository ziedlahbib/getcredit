package tn.esprit.spring.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
	        
	        
	        System.loadLibrary("opencv_java342");
	        Mat image = Imgcodecs.imread(imageFile.getAbsolutePath());

	        // Redimensionner l'image
	        int height = image.height();
	        int width = image.width();
	        double aspectRatio = (double) width / height;
	        Size newSize = new Size(800, (int) (800 / aspectRatio));
	        Imgproc.resize(image, image, newSize);
	        File tempImage = File.createTempFile("tempImage", ".jpg");

	     // Write the OpenCV Mat object to the file
	     Imgcodecs.imwrite(tempImage.getAbsolutePath(), image);
	     
	     
	     
	        tesseract.setDatapath("C:\\Users\\lahbi\\Documents\\GitHub\\getcredit\\tessdata-main");
	        String result = tesseract.doOCR(tempImage);
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
