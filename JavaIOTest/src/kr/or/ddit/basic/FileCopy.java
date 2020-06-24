package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/*
 * 'd:/D_Other/'에 있는 'Tulips.jpg'파일을
 * '복사본_Tulips.jpg'로 복사하는 프로그램을
 * 작성하시오.
 */
public class FileCopy {
	
	public static void main(String[] args) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream("E:\\D_Other\\Tulips.jpg");
			fos = new FileOutputStream("E:\\D_Other\\Tulips_Copy.jpg");
			int c;
			while( (c = fis.read()) != -1) {
				fos.write(c);
			}
			
			fis.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
