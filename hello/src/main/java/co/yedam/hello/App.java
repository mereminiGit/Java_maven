package co.yedam.hello;

import java.util.ArrayList;
import java.util.List;

import co.yedam.hello.product.service.ProductService;
import co.yedam.hello.product.service.ProductVO;
import co.yedam.hello.product.serviceImpl.productServiceImpl;
import co.yedam.hello.pucductmenu.ProductManager;

/**
 * Hello world!
 *
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        ProductService dao = new productServiceImpl();
//        List<ProductVO> products = new ArrayList<ProductVO>();
//        
//        products = dao.productSelectList();
//        
//        for(ProductVO v : products)
//        	v.toString();
    	
    	ProductManager menu = new ProductManager();
    	menu.run();
    }
}
