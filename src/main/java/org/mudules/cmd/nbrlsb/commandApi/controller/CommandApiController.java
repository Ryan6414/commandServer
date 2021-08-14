package org.mudules.cmd.nbrlsb.commandApi.controller;


import lombok.extern.slf4j.Slf4j;
import org.mudules.cmd.nbrlsb.snStatus.entity.SnStatus;
import org.mudules.cmd.nbrlsb.snStatus.server.SnStatusServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/commandApi")
@Slf4j
public class CommandApiController {
    @Autowired
    private SnStatusServer statusServer;

    @GetMapping("/on")
    public String commandApi(@RequestParam("area") Integer area) {
        log.info("请求执行{}", area);
        if (area == 0 && statusServer.getStaByArea(area)) {
            List<SnStatus> statusInfo = statusServer.getStatusInfo();
            statusInfo.forEach(e -> {
                if (e.getStatus() == 0) {
                    statusServer.updateAllStatusById(e.getId());
                    statusServer.updateOneStatusByArea(e.getArea());
                }
            });

            return "1";
        } else {
            if (area == 1 && statusServer.getStaByArea(area)) {
                List<SnStatus> statusInfo = statusServer.getObjectByArea(area);
                statusInfo.forEach(e -> {
                    if (e.getArea() == 1) {
                        statusServer.updateAllStatusById(e.getId());
                        statusServer.updateOneStatusByArea(e.getArea());
                    }
                });

                return "1";
            } else if (area == 2 && statusServer.getStaByArea(area)) {
                List<SnStatus> statusInfo = statusServer.getObjectByArea(area);
                statusInfo.forEach(e -> {
                    if (e.getArea() == 2) {
                        statusServer.updateAllStatusById(e.getId());
                        statusServer.updateOneStatusByArea(e.getArea());
                    }
                });

                return "1";
            } else if (area == 3 && statusServer.getStaByArea(area)) {
                List<SnStatus> statusInfo = statusServer.getObjectByArea(area);
                statusInfo.forEach(e -> {
                    if (e.getArea() == 3) {
                        statusServer.updateAllStatusById(e.getId());
                        statusServer.updateOneStatusByArea(e.getArea());
                    }
                });

                return "1";
            }
        }

        return "0";
    }
}
