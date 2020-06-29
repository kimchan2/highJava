package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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
			BufferedInputStream bis = new BufferedInputStream(fis);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			int c;
			while( (c = bis.read()) != -1) {
				bos.write(c);
			}
			bis.close();
			bos.close();
			
			fis.close();
			fos.close();
			
			System.out.println("파일 복사 완료");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
