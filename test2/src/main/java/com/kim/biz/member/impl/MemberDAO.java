package com.kim.biz.member.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.kim.biz.common.JDBCUtil;
import com.kim.biz.member.MemberVO;

@Repository("memberDAO")
public class MemberDAO {
	Connection conn;
	PreparedStatement pstmt;
	
	final String sql_selectOne="SELECT * FROM MEMBER WHERE MID=? AND MPW=?"; 
	final String sql_insert="INSERT INTO MEMBER VALUES(?,?,?,?)";

	public boolean insert_M(MemberVO mvo) { //회원가입
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(sql_insert);
			pstmt.setString(1, mvo.getMid()); 
			pstmt.setString(2, mvo.getMpw()); 
			pstmt.setString(3, mvo.getName());
			pstmt.setString(4, "member");
			pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn); 
		}
		return true;
	}
	
	public MemberVO selectOne(MemberVO mvo) { //로그인에 사용
		conn = JDBCUtil.connect();
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql_selectOne);
			pstmt.setString(1, mvo.getMid());
			pstmt.setString(2, mvo.getMpw());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				MemberVO data = new MemberVO();
				data.setMid(rs.getString("MID"));
				data.setMpw(rs.getString("MPW"));
				data.setName(rs.getString("NAME"));
				data.setRole(rs.getString("ROLE"));
				return data;
			} else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JDBCUtil.disconnect(pstmt, conn);
		}
	}
}
