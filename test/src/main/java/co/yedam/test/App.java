package co.yedam.test;

import co.yedam.test.common.SHA256;
import co.yedam.test.menu.MemberMenu;

public class App 
{
	// 이 프로젝트에서 시작과 끝을 찾아가는것
    public static void main( String[] args )
    {
//    	SHA256 sha256 = new SHA256();
//    	String stx = "1234";
//    	String cyperText = sha256.encrypt(stx);
//    	System.out.println(cyperText);
    	
        MainMenu menu = new MainMenu();
        menu.run();
    }
}
