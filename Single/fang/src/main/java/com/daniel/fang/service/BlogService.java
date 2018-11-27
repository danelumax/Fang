package com.daniel.fang.service;

import com.daniel.fang.mapper.BlogMapper;
import com.daniel.fang.model.Blog;
import com.daniel.fang.page.PageData;
import com.daniel.fang.page.PageParams;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
  
  @Autowired
  private BlogMapper blogMapper;

  public PageData<Blog> queryBlog(Blog query, PageParams params) {
    List<Blog> blogs =  blogMapper.selectBlog(query,params);
    populate(blogs);
    Long  count =  blogMapper.selectBlogCount(query);
    return PageData.<Blog>buildPage(blogs, count, params.getPageSize(), params.getPageNum());
  }

  private void populate(List<Blog> blogs) {
    if (!blogs.isEmpty()) {
      blogs.stream().forEach(item -> {
        String stripped =  Jsoup.parse(item.getContent()).text();
        item.setDigest(stripped.substring(0, Math.min(stripped.length(),40)));
        String tags = item.getTags();
        item.getTagList().addAll(Lists.newArrayList(Splitter.on(",").split(tags)));
      });
    }
  }

  public Blog queryOneBlog(int id) {
    Blog query = new Blog();
    query.setId(id);
    List<Blog> blogs = blogMapper.selectBlog(query, new PageParams(1, 1));
    if (!blogs.isEmpty()) {
      return blogs.get(0);
    }
    return null;
  }

  

}
