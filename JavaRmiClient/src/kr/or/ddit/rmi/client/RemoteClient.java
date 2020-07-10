package kr.or.ddit.rmi.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.rmi.inf.RemoteInterface;
import kr.or.ddit.rmi.vo.FileInfoVO;
import kr.or.ddit.rmi.vo.TestVO;

/*
 * 클라이언트쪽의 프로젝트에도 서버의 패키지 구조와 같은 구조로 Interface와 VO파일이
 * 있어야 한다.
 */
public class RemoteClient {
	public static void main(String[] args) {
		// Registry 서버에 등록된 객체를 구한다.
		try {
			// 1. 등록된 서버를 찾기 위해 Registry객체를 생성한 후
			// 사용할 객체를 불러온다.
			Registry reg = LocateRegistry.getRegistry("192.168.201.30", 8888);
			RemoteInterface clientInf = (RemoteInterface) reg.lookup("server");
			
			// 이제부터는 불러온 객체의 메서드를 호출해서 사용할 수 있다.
			int a = clientInf.doRemotePrint("안녕하세요");
			System.out.println("반환값 => " + a);
			System.out.println("---------------------------------------------");
			
			List<String> list = new ArrayList<>();
			list.add("대전");
			list.add("대구");
			list.add("광주");
			list.add("인천");
			clientInf.doPrintList(list);
			System.out.println("List 호출 끝...");
			System.out.println("---------------------------------------------");
			
			TestVO vo = new TestVO();
			vo.setTestId("dditMan");
			vo.setTestNum(123);
			clientInf.doPrintVo(vo);
			System.out.println("VO출력 메서드 호출 끝...");
			System.out.println("---------------------------------------------");
			
			// 파일 전송하기
			File[] files = new File[2];
			files[0] = new File("E:/D_Other/Desert.jpg");
			files[1] = new File("E:/D_Other/Desert2.jpg");
			
			FileInfoVO[] fInfos = new FileInfoVO[files.length];
			
			// 2개의 파일을 읽어서 byte[]에 담아서 서버측 메서드에 전달하면 된다.
			FileInputStream fis = null;
			for(int i = 0; i < files.length; i++) {
				int len = (int)files[i].length(); // 파일 크
				fis = new FileInputStream(files[i]);
				byte[] data = new byte[len];
				
				fis.read(data); // 파일의 내용을 읽어서 byte형 배열에 저장
				
				fInfos[i] = new FileInfoVO();
				fInfos[i].setFileData(data); // 파일 데이터 저장
				fInfos[i].setFileName(files[i].getName()); // 파일명 저장
			}
			
			clientInf.setFiles(fInfos); // 서버의 파일저장 메서드 호출
			System.out.println("파일 전송 작업 끝...");
			System.out.println("---------------------------------------------");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
