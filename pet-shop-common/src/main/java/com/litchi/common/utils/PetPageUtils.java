package com.litchi.common.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @Author Mark
 * 2023/2/2 17:11
 */
public class PetPageUtils {

    public static <T> PageUtils getPageUtils(Integer pageIndex, Integer limit, List<T> entities) {
        Page<T> page = new Page<>(pageIndex, limit);
        int totalSize = entities.size();
        // 若找不到数据，直接返回page
        if (totalSize == 0) {
            return new PageUtils(page);
        }
        int startIndex = (pageIndex - 1) * limit;
        int endIndex = pageIndex * limit;
        //总页数
        int pageCount = 0;
        //通过 % 判断是否给总页数加1
        int num = totalSize % limit;
        pageCount = num == 0 ? totalSize / limit : totalSize / limit + 1;
        //如果当前页是最后一页的话 要包含集合的最后一条数据，因为sublist方法本身结束的下标是不包含当前元素的
        if (pageIndex == pageCount) {
            endIndex = totalSize;
        }
        List<T> adminEntitySubList = entities.subList(startIndex, endIndex);
        page.setRecords(adminEntitySubList);
        page.setTotal(totalSize);

        return new PageUtils(page);
    }
}
