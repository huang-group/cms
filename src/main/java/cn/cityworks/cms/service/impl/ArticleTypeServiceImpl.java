package cn.cityworks.cms.service.impl;

import cn.cityworks.cms.base.Definition;
import cn.cityworks.cms.dao.ArticleTypeDao;
import cn.cityworks.cms.domain.SysArticleType;
import cn.cityworks.cms.domain.SysSection;
import cn.cityworks.cms.exception.BizException;
import cn.cityworks.cms.service.ArticleTypeService;
import cn.cityworks.cms.utils.Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 文章类型操作-逻辑层-实现类
 */
@Service
@Log4j2
public class ArticleTypeServiceImpl implements ArticleTypeService {
    private ArticleTypeDao articleTypeDao;

    @Autowired(required = false)
    public void setArticleTypeDao(ArticleTypeDao articleTypeDao) {
        this.articleTypeDao = articleTypeDao;
    }

    /**
     * 添加文章类型
     */
    @Override
    public boolean handleAddArticleType(Map<String, Object> params) {
        //获取参数
        String name = null == params.get("name") ? null : (String) params.get("name");        //文章类型名称
        int sort = null == params.get("sort") ? 0 : (int) params.get("sort");     //排序
        String rolse = null == params.get("rolse") ? null : (String) params.get("rolse");      //权限

        if (null == name || "".equals(name)) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "专栏名称不能为空!");
        }

        //插入数据
        Timestamp time = new Timestamp(System.currentTimeMillis());
        SysArticleType articleType = new SysArticleType();
        articleType.setId(Utils.randomUUID());
        articleType.setName(name);
        articleType.setSort(sort);
        articleType.setStatus(Definition.DATA_STATUS_NORMAL);
        articleType.setRecord_status(Definition.DATA_STATUS_NORMAL);
        articleType.setCreate_date(time);
        articleType.setUpdate_date(time);
        articleType.setRoles(rolse);

        return 1 == articleTypeDao.insertArticleType(articleType);
    }

    /**
     * 删除文章类型
     */
    @Override
    public boolean handleDeleteArticleType(Map<String, Object> params) {
        String id = null == params.get("id") ? null : (String) params.get("id");
        int status = null == params.get("status") ? 0 : (int) params.get("status");

        if (status != Definition.DATA_STATUS_LOGIC_DELETE && status != Definition.DATA_STATUS_PHYSICS_DELETE) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "请按照文档传入指定删除参数");
        }
        if (null == id || "".equals(id)) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "请传入要删除的频道id");
        }

        return 1 == articleTypeDao.deleteArticleType(id, status);
    }

    /**
     * 获取文章类型
     */
    @Override
    public List<SysArticleType> handleGetArticleType() {
        List<SysArticleType> articleType = articleTypeDao.getAllArticleType();

        if (null == articleType) {
            return new ArrayList<>();
        }

        return articleType;
    }

    /**
     * 更新文章类型
     */
    @Override
    public boolean handleUpdateArticleType(Map<String, Object> params) {
        //获取参数
        String id = null == params.get("id") ? null : (String) params.get("id");
        String name = null == params.get("name") ? null : (String) params.get("name");
        int sort = null == params.get("sort") ? 0 : (int) params.get("sort");
        String roles = null == params.get("roles") ? null : (String) params.get("roles");

        if (null == id || "".equals(id)) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "请传入要更新的专栏id");
        }
        if (null == name || "".equals(name)) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "专栏名称不能为空");
        }

        SysArticleType articleType = new SysArticleType();
        articleType.setId(id);
        articleType.setName(name);
        articleType.setSort(sort);
        articleType.setRoles(roles);
        articleType.setUpdate_date(new Timestamp(System.currentTimeMillis()));

        int result = articleTypeDao.updateArticleType(articleType);

        return 1 == result;
    }
}
