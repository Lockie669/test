import com.jack.dao.IProductDao;
import com.jack.domain.Product;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Demo {

    @Test
    public void test1(){

        ApplicationContext ac = new ClassPathXmlApplicationContext ( "applicationContext-dao.xml" );
        IProductDao dao = ac.getBean ( IProductDao.class );
        List<Product> list = dao.findAll ();
        for (Product product : list) {
            System.out.println (product);

        }
    }
}
