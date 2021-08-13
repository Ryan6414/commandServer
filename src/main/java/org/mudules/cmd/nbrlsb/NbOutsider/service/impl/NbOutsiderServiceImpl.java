package org.mudules.cmd.nbrlsb.NbOutsider.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.mudules.cmd.nbrlsb.NbOutsider.entity.NbOutsider;
import org.mudules.cmd.nbrlsb.NbOutsider.mapper.NbOutsiderMapper;
import org.mudules.cmd.nbrlsb.NbOutsider.service.INbOutsiderService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NbOutsiderServiceImpl extends ServiceImpl<NbOutsiderMapper, NbOutsider> implements INbOutsiderService {



    @Override
    public List<NbOutsider> getAll() {
        return super.baseMapper.selectList(null);
    }

    @Override
    public NbOutsider getOneInfo() {
        return super.baseMapper.getOneByUpload();
    }

    @Override
    public Boolean updateByUpLoad(Integer upid , String id) {

        return super.baseMapper.updateById(upid, id);
    }


}
