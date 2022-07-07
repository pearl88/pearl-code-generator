package com.pearl.generator.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pearl.generator.entity.DataSource;
import com.pearl.generator.java.Result;
import com.pearl.generator.service.IDataSourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author TangDan
 * @since 2022-07-07
 */
@Api(value = "DataSourceController", tags = "数据源管理")
@RestController
@RequestMapping("/dataSource")
public class DataSourceController {

    @Resource
    IDataSourceService dataSourceService;

    @ApiOperation(value = "分页查询数据源", produces = "application/json")
    @GetMapping("/page")
    public Result<Page<DataSource>> page(@RequestParam("current") Long current, @RequestParam("current") Long size) {
        Page<DataSource> page = dataSourceService.page(new Page<>(current, size));
        return Result.success(page);
    }
}
