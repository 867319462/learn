package com.learn.controller.cs;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.learn.entity.Book;
import com.learn.service.IBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangxl
 * @since 2021-07-17
 */
@Api(tags = {"书籍api文档"})
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private IBookService bookService;

    @ApiOperation("获取所有书籍")
    @GetMapping("/list")
    public List<Book> getBooks() {
        return bookService.list();
    }

    @ApiOperation("分页获取书籍")
    @GetMapping("/pageList")
    public Page<Book> getBooks(Integer currentPage, Integer pageSize) {
        return bookService.page(new Page<>(currentPage, pageSize));
    }
}
