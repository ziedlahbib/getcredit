package tn.esprit.spring.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
	        tesseract.setDatapath("C:\\Users\\lahbi\\Documents\\GitHub\\getcredit\\tessdata-main");
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
