package org.mudules.cmd.nbrlsb.NbOutsider.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.mudules.cmd.nbrlsb.NbOutsider.entity.NbOutsider;
import org.mudules.cmd.nbrlsb.NbOutsider.mapper.NbOutsiderMapper;
import org.mudules.cmd.nbrlsb.NbOutsider.service.INbOutsiderService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NbOutsiderServiceImpl extends ServiceImpl<NbOutsiderMapper, NbOutsider> implements INbOutsiderService {





    @Override
    public NbOutsider getOneNboutsider() {
        return super.baseMapper.getOneNboutsider();
    }

    @Override
    public int updateUploadFlagById(String upFlag, String id) {
        return super.baseMapper.updateUploadFlagById(upFlag,id);
    }

    @Override
    public int updateUploadFlagByFalg(String upFlag, String id) {
        return super.baseMapper.updateUploadFlagByFalg(upFlag,id);
    }

//    @Override
//    public NbOutsider getOneInfo(Integer up, Integer del) {
//        return super.baseMapper.
//    }
//
//    @Override
//    public Boolean updateByUpLoad(Integer upid , String id) {
//
//        return super.baseMapper.updateById(upid, id);
//    }


}
