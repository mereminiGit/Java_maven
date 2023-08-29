package co.yedam.CRUDproject;

import co.yedam.CRUDproject.member.controller.MemberAdminController;
import co.yedam.CRUDproject.product.controller.ProductAdminController;

public class App 
{
    public static void main( String[] args )
    {
        MemberAdminController mac = new MemberAdminController();
        ProductAdminController pac = new ProductAdminController();
        
        pac.run();
    }
}
