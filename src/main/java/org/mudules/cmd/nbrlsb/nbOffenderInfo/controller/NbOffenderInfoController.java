package org.jeecg.modules.netboat.nbOffenderInfo.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.netboat.nbOffenderInfo.entity.NbOffenderInfo;
import org.jeecg.modules.netboat.nbOffenderInfo.service.INbOffenderInfoService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: nb_offender_info
 * @Author: jeecg-boot
 * @Date:   2021-08-04
 * @Version: V1.0
 */
@Api(tags="nb_offender_info")
@RestController
@RequestMapping("/nbOffenderInfo/nbOffenderInfo")
@Slf4j
public class NbOffenderInfoController extends JeecgController<NbOffenderInfo, INbOffenderInfoService> {
	@Autowired
	private INbOffenderInfoService nbOffenderInfoService;
	
	/**
	  * 分页列表查询
	  *
	  * @param nbOffenderInfo
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "nb_offender_info-分页列表查询")
	 @ApiOperation(value="nb_offender_info-分页列表查询", notes="nb_offender_info-分页列表查询")
	 @GetMapping(value = "/list")
	 public Result<?> queryPageList(NbOffenderInfo nbOffenderInfo,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									HttpServletRequest req) {
		 QueryWrapper<NbOffenderInfo> queryWrapper = QueryGenerator.initQueryWrapper(nbOffenderInfo, req.getParameterMap());
		 Page<NbOffenderInfo> page = new Page<NbOffenderInfo>(pageNo, pageSize);
		 IPage<NbOffenderInfo> pageList = nbOffenderInfoService.page(page, queryWrapper);
		 return Result.OK(pageList);
	 }
	
	/**
	 *   添加
	 *
	 * @param nbOffenderInfo
	 * @return
	 */
	@AutoLog(value = "nb_offender_info-添加")
	@ApiOperation(value="nb_offender_info-添加", notes="nb_offender_info-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody NbOffenderInfo nbOffenderInfo) {
		nbOffenderInfoService.save(nbOffenderInfo);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param nbOffenderInfo
	 * @return
	 */
	@AutoLog(value = "nb_offender_info-编辑")
	@ApiOperation(value="nb_offender_info-编辑", notes="nb_offender_info-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody NbOffenderInfo nbOffenderInfo) {
		nbOffenderInfoService.updateById(nbOffenderInfo);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "nb_offender_info-通过id删除")
	@ApiOperation(value="nb_offender_info-通过id删除", notes="nb_offender_info-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		nbOffenderInfoService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "nb_offender_info-批量删除")
	@ApiOperation(value="nb_offender_info-批量删除", notes="nb_offender_info-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.nbOffenderInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "nb_offender_info-通过id查询")
	@ApiOperation(value="nb_offender_info-通过id查询", notes="nb_offender_info-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		NbOffenderInfo nbOffenderInfo = nbOffenderInfoService.getById(id);
		if(nbOffenderInfo==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(nbOffenderInfo);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param nbOffenderInfo
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, NbOffenderInfo nbOffenderInfo) {
        return super.exportXls(request, nbOffenderInfo, NbOffenderInfo.class, "nb_offender_info");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, NbOffenderInfo.class);
    }

}
