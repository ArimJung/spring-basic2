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
		// Spring �����̳ʸ� ���۽�ų���ֵ��� �ڵ��ۼ�
		AbstractApplicationContext factory=new GenericXmlApplicationContext("applicationContext.xml");
		
		BoardService bs=(BoardService)factory.getBean("boardService");
		MemberService ms=(MemberService)factory.getBean("memberService");
		
		BoardVO bvo=new BoardVO();
		MemberVO mvo = new MemberVO();
		Scanner sc=new Scanner(System.in);

		while(true) {
		System.out.println("1.�α��� 2.ȸ������ 3.����");
		System.out.print("�Է� : ");
		int action=sc.nextInt();
		
		
		if(action==1) {
			System.out.println("==�α���==");
			System.out.print("���̵� : ");
			String mid=sc.next();
			System.out.print("��й�ȣ : ");
			String mpw=sc.next();
			
			mvo.setMid(mid);
			mvo.setMpw(mpw);
			
			if(ms.selectOneMember(mvo)!=null) {
				System.out.println("�α��� ����");
				
				while(true) {
					System.out.println("1.�۾��� 2.�� ��Ϻ��� 3.����");
					System.out.print("�Է� : ");
					int action2 = sc.nextInt();
					
					if(action2==1) {
						System.out.print("�����ۼ� >> ");
						String msg=sc.next();
						bvo.setContent(msg);
						bvo.setTitle("�� ����");
						bvo.setWriter("���� Ƽ��");
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
				System.out.println("�α��� ����");
			}
		}
		else if(action==2) {
			System.out.println("==ȸ������==");
			System.out.print("���̵� : ");
			String mid=sc.next();
			System.out.print("��й�ȣ : ");
			String mpw=sc.next();
			System.out.print("�̸� : ");
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
