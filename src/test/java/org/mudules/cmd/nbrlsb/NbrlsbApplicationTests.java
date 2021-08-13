package org.mudules.cmd.nbrlsb;

import org.apache.ibatis.ognl.DynamicSubscript;
import org.junit.jupiter.api.Test;
import org.mudules.cmd.nbrlsb.NbOutsider.entity.NbOutsider;
import org.mudules.cmd.nbrlsb.NbOutsider.service.INbOutsiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class NbrlsbApplicationTests {


    @Autowired
    private INbOutsiderService iNbOutsiderService;

    @Test
    void su(){
        List<NbOutsider> all = iNbOutsiderService.getAll();
        System.out.println(DynamicSubscript.all);
    }

}
