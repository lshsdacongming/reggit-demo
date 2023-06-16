package edu.czjt.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.czjt.reggie.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by wyx 2023/6/14
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
