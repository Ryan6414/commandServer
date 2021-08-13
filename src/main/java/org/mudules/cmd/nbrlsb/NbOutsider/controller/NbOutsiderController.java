package org.mudules.cmd.nbrlsb.NbOutsider.controller;

import lombok.extern.slf4j.Slf4j;
import org.mudules.cmd.nbrlsb.NbOutsider.service.INbOutsiderService;

import org.mudules.cmd.nbrlsb.common.CommandServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/net")
@Slf4j
public class NbOutsiderController {
    @Autowired
    private INbOutsiderService nbOutsiderService;

    @Autowired
    private CommandServer soc;

//    @Autowired
//    private SocketListener sl;

 //   @GetMapping("/test/a")
    public String su() {
//        sl.setCmd(1);

        return "ok";
    }
   // @GetMapping("/test/b")
    public String su2() {
//        sl.setCmd(0);
        return "ok";
    }


    @GetMapping("a/b")
    public String su1() {
        String a = "{ \n" +
                "    \"funcId\": \"cmd.update.user\", \n" +
                "    \"payload\": { \n" +
                "        \"params\": { \n" +
                "            \"users\": { \n" +
                "                \"pin\": \"xxxx\", \n" +
                "                \"identity_number\": \"350623xxxxxxxxxxx\", \n" +
                "                \"name\": \"xxxxxxx\", \n" +
                "                \"nation\": \"xxxxxxx\", \n" +
                "                \"sex\": \"xxxxxxx\", \n" +
                "                \"birth\": \"xxxxxxx\", \n" +
                "                \"address\": \"xxxxxxx\", \n" +
                "                \"card\": \"xxxxxxx\",\n" +
                "                \"phone\": \"xxxxxxx\",\n" +
                "                \"identity_photo\": \"xxxxxxx\", \n" +
                "                \"photo\": \"xxxxxxx\", \n" +
                "                \"fingperprint\": [{ \n" +
                "                    \"index\": \"0\", \n" +
                "                    \"template\": \"xxxxxxxxxx\" \n" +
                "                }] \n" +
                "            } \n" +
                "        } \n" +
                "    } \n" +
                "} ";

        return "ok";
    }


}
