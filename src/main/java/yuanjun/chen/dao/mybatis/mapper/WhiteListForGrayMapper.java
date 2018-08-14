package yuanjun.chen.dao.mybatis.mapper;

import yuanjun.chen.dao.mybatis.model.WhiteListForGray;

public interface WhiteListForGrayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WhiteListForGray record);

    int insertSelective(WhiteListForGray record);

    WhiteListForGray selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WhiteListForGray record);

    int updateByPrimaryKey(WhiteListForGray record);
}