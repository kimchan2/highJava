package kr.or.ddit.jsoup;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class JJsoup {
	
	public static void main(String[] args) throws IOException {
		
		Document doc = Jsoup.connect("https://www.gmarket.co.kr/?&jaehuid=200011415&gclid=EAIaIQobChMI76Po94%5Ft6gIVRLaWCh1UdgcWEAAYASAAEgKjrfD%5FBwE").get();
		String title = doc.title();
		
		System.out.println("홈페이지 제목 : " + title);
		
		System.out.println("======================================");
		
		Elements depth2 = doc.select(".link__2depth-item");
		for(int i = 0; i < depth2.size()/2; i++) {
			System.out.println("부모 카테고리 : " + depth2.get(i).parent().parent()
					.parent().parent().parent().parent().select(".link__1depth-item").text());
			System.out.println("\t상세 카테고리 : " + depth2.get(i).text());
		}
		
		System.out.println("======================================");
		
	}
	
}
