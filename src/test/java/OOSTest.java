import com.aliyun.oss.OSSClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
//注解Contest寻找配置文件
@ContextConfiguration("classpath:application.yml")
public class OOSTest {

    @Autowired
    private OSSClient ossClient;

    @Value("${bucketName}")
    private String bucketName;

    @Value("${spring.cloud.alicloud.oss.endpoint}")
    private String endpoint;

    @Test
    public void fileUpload() throws FileNotFoundException {


        //设置图片上传的文件名称


        File file = new File("/Users/zhenbaoyu/code/实战项目/aliyun-oss-demo/src/test/java/OOSTest.java");
        String fileName = System.currentTimeMillis() + "_" + file.getName();
        ossClient.putObject(bucketName, fileName, new FileInputStream(file));

        //拼接文件路径进行返回
        String logoPath = "https://" + bucketName + "." + endpoint + "/" + fileName;
        System.out.println(logoPath);


    }
}
