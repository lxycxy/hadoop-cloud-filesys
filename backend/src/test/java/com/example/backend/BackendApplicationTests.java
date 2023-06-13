package com.example.backend;

import com.example.backend.mapred.AverageMR;
import com.example.backend.mapred.FindStudentMR;
import com.example.backend.utils.HDFSUtils;
import com.example.backend.utils.WordCount;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.UUID;

@SpringBootTest
class BackendApplicationTests {

    @Test
    void contextLoads() throws IOException {
        FindStudentMR findStudentMR = new FindStudentMR();
        String[] files = new String[] {"test.txt", "test1.txt"};
        try {
            String resultName = UUID
                    .randomUUID()
                    .toString()
                    .replace("-", "")
                    .substring(0, 8);;
            findStudentMR.run("Rose", files, resultName);

            HDFSUtils utils = new HDFSUtils();
            utils.moveToRecord(resultName,"11", "average");
            System.out.println(utils.readContent(resultName + "-" + "average"));
        } catch (IOException | InterruptedException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
