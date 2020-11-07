import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

//定时清理功能启动类
public class ScheduledStart {
    public static void main(String[] args) throws IOException {
        new ClassPathXmlApplicationContext("classpath:spring-jobs.xml");
        System.in.read();
    }
}
