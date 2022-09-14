package test;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.kim.biz.board.BoardService;
import com.kim.biz.board.BoardVO;
import com.kim.biz.member.MemberService;
import com.kim.biz.member.MemberVO;

public class Client {
	public static void main(String[] args) {
		// Spring 컨테이너를 동작시킬수있도록 코드작성
		AbstractApplicationContext factory=new GenericXmlApplicationContext("applicationContext.xml");
		
		BoardService bs=(BoardService)factory.getBean("boardService");
		MemberService ms=(MemberService)factory.getBean("memberService");
		
		BoardVO bvo=new BoardVO();
		MemberVO mvo = new MemberVO();
		Scanner sc=new Scanner(System.in);

		while(true) {
		System.out.println("1.로그인 2.회원가입 3.종료");
		System.out.print("입력 : ");
		int action=sc.nextInt();
		
		
		if(action==1) {
			System.out.println("==로그인==");
			System.out.print("아이디 : ");
			String mid=sc.next();
			System.out.print("비밀번호 : ");
			String mpw=sc.next();
			
			mvo.setMid(mid);
			mvo.setMpw(mpw);
			
			if(ms.selectOneMember(mvo)!=null) {
				System.out.println("로그인 성공");
				
				while(true) {
					System.out.println("1.글쓰기 2.글 목록보기 3.종료");
					System.out.print("입력 : ");
					int action2 = sc.nextInt();
					
					if(action2==1) {
						System.out.print("내용작성 >> ");
						String msg=sc.next();
						bvo.setContent(msg);
						bvo.setTitle("글 제목");
						bvo.setWriter("작은 티모");
						bs.insertBoard(bvo);
					}
					else if(action2==2) {
						List<BoardVO> datas=bs.selectAllBoard(bvo);
						for(BoardVO v:datas) {
							System.out.println(v);
						}
					}
					else if(action2==3) {
						break;
					}
					
				}
				
				
			}
			else {
				System.out.println("로그인 실패");
			}
		}
		else if(action==2) {
			System.out.println("==회원가입==");
			System.out.print("아이디 : ");
			String mid=sc.next();
			System.out.print("비밀번호 : ");
			String mpw=sc.next();
			System.out.print("이름 : ");
			String name=sc.next();
	
			mvo.setMid(mid);
			mvo.setMpw(mpw);
			mvo.setName(name);
			ms.insertMember(mvo);
		}
		else if(action==3) {
			break;
		}

		factory.close();
		}
	}
}
