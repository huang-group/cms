package cn.cityworks.cms.service.impl;

import cn.cityworks.cms.base.Definition;
import cn.cityworks.cms.dao.ContentDao;
import cn.cityworks.cms.domain.SysContent;
import cn.cityworks.cms.exception.BizException;
import cn.cityworks.cms.service.ContentService;
import cn.cityworks.cms.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Map;

/**
 * 内容操作-逻辑层-实现类
 */
@Service
public class ContentServiceImpl implements ContentService {

    private ContentDao contentDao;
    @Autowired(required = false)
    public void setContentDao(ContentDao contentDao){
        this.contentDao = contentDao;
    }

    /** 添加内容 */
    @Override
    public boolean handleAddContent(Map<String, Object> params){
        String channel_id = (String)params.get("channel_id");       //所属频道id，必填
        String section_id = (String)params.get("section_id");       //所属专栏id，必填
        String article_type_id = (String)params.get("article_type_id");       //所属内容类型id，必填
        String dissertation_id = (String)params.get("dissertation_id");       //所属专题类型id
        String title = (String)params.get("title");         //内容标题，必填
        String short_title = (String)params.get("short_title");         //内容短标题
        String tag = (String)params.get("tag");         //内容短标题
        String digest = (String)params.get("digest");         //内容短标题
        String content = (String)params.get("content");         //内容，必填
        String image = (String)params.get("image");         //内容图片
        String publish_date = (String)params.get("publish_date");         //发布日期，必填
        String archive_date = (String)params.get("archive_date");         //归档日期
        int sort = null == params.get("sort") ? 0 : (int)params.get("sort");      //排序
        String roles = (String) params.get("roles");        //权限

        if(Utils.isEmpty(channel_id)){
            throw new BizException("传入参数异常", "内容必须要存在某个频道下!");
        }
        if(Utils.isEmpty(section_id)){
            throw new BizException("传入参数异常", "内容必须要存在某个专栏下!");
        }
        if(Utils.isEmpty(article_type_id)){
            throw new BizException("传入参数异常", "内容必须要存在类型下!");
        }
        if(Utils.isEmpty(title)){
            throw new BizException("传入参数异常", "内容必须要存在标题!");
        }
        if(Utils.isEmpty(content)){
            throw new BizException("传入参数异常", "内容必须要存在内容!");
        }
        if(Utils.isEmpty(publish_date)){
            throw new BizException("传入参数异常", "内容必须要存在发布日期!");
        }

        //存储数据
        Timestamp sqlTime = new Timestamp(System.currentTimeMillis());
        SysContent sysContent = new SysContent();
        sysContent.setChannel_id(channel_id);
        sysContent.setSection_id(section_id);
        sysContent.setDissertation_id(dissertation_id);
        sysContent.setArticle_type_id(article_type_id);
        sysContent.setTitle(title);
        sysContent.setShort_title(short_title);
        sysContent.setTag(tag);
        sysContent.setDigest(digest);
        sysContent.setAuth_name("auth");
        sysContent.setContent(content);
        sysContent.setImage(image);
        sysContent.setPublish_date(Utils.strToSqlDate(publish_date));
        sysContent.setArchive_date(Utils.strToSqlDate(archive_date));
        sysContent.setCreate_date(sqlTime);
        sysContent.setUpdate_date(sqlTime);
        sysContent.setAudit_status(Definition.CONTENT_AUDIT_NONE);
        sysContent.setSort(sort);
        sysContent.setStatus(Definition.DATA_STATUS_NORMAL);
        sysContent.setRecord_status(Definition.DATA_STATUS_NORMAL);
        sysContent.setRoles(roles);

        return 1 == contentDao.insertContent(sysContent);
    }

    /** 删除内容 */
    @Override
    public boolean handleDeleteContent(Map<String, Object> params){
        return true;
    }

    /** 更新内容 */
    @Override
    public boolean handleUpdateContent(Map<String, Object> params){
        return true;
    }

    /** 审核内容 */
    @Override
    public boolean handleAuditContent(Map<String, Object> params){
        return true;
    }
}
