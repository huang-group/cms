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
import java.util.HashMap;
import java.util.List;
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
        String tag = (String)params.get("tag");         //内容标签
        String digest = (String)params.get("digest");         //内容摘要
        String auth = (String)params.get("auth");         //内容作者
        String content = (String)params.get("content");         //内容，必填
        String image = (String)params.get("image");         //内容图片
        String publish_date = (String)params.get("publish_date");         //发布日期，必填
        String archive_date = (String)params.get("archive_date");         //归档日期
        int sort = null == params.get("sort") ? 0 : (int)params.get("sort");      //排序
        String roles = (String) params.get("roles");        //权限

        if(Utils.isEmpty(channel_id)){
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "内容必须要存在某个频道下!");
        }
        if(Utils.isEmpty(section_id)){
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "内容必须要存在某个专栏下!");
        }
        if(Utils.isEmpty(article_type_id)){
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "内容必须要存在类型下!");
        }
        if(Utils.isEmpty(title)){
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "内容必须要存在标题!");
        }
        if(Utils.isEmpty(content)){
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "内容必须要存在内容!");
        }
        if(Utils.isEmpty(publish_date)){
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "内容必须要存在发布日期!");
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
        sysContent.setAuth_name(auth);
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
        String id = (String) params.get("id");
        int status = null == params.get("status") ? 0 : (int) params.get("status");

        //判断参数是否是删除，不是则抛出异常
        if (status != Definition.DATA_STATUS_LOGIC_DELETE && status != Definition.DATA_STATUS_PHYSICS_DELETE) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "请按照文档传入指定删除参数");
        }
        if (Utils.isEmpty(id)) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "请传入要删除的内容id");
        }

        return 1 == contentDao.deleteContent(Long.parseLong(id), status);
    }

    /** 更新内容 */
    @Override
    public boolean handleUpdateContent(Map<String, Object> params){
        String id = (String) params.get("id");
        String channel_id = (String)params.get("channel_id");       //所属频道id，必填
        String section_id = (String)params.get("section_id");       //所属专栏id，必填
        String article_type_id = (String)params.get("article_type_id");       //所属内容类型id，必填
        String dissertation_id = (String)params.get("dissertation_id");       //所属专题类型id
        String title = (String)params.get("title");         //内容标题，必填
        String short_title = (String)params.get("short_title");         //内容短标题
        String tag = (String)params.get("tag");         //内容标签
        String digest = (String)params.get("digest");         //内容摘要
        String auth = (String)params.get("auth");         //内容作者
        String content = (String)params.get("content");         //内容，必填
        String image = (String)params.get("image");         //内容图片
        String publish_date = (String)params.get("publish_date");         //发布日期，必填
        String archive_date = (String)params.get("archive_date");         //归档日期
        int sort = null == params.get("sort") ? 0 : (int)params.get("sort");      //排序
        String roles = (String) params.get("roles");        //权限

        if(Utils.isEmpty(channel_id)){
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "内容必须要存在某个频道下!");
        }
        if(Utils.isEmpty(section_id)){
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "内容必须要存在某个专栏下!");
        }
        if(Utils.isEmpty(article_type_id)){
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "内容必须要存在类型下!");
        }
        if(Utils.isEmpty(title)){
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "内容必须要存在标题!");
        }
        if(Utils.isEmpty(content)){
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "内容必须要存在内容!");
        }
        if(Utils.isEmpty(publish_date)){
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "内容必须要存在发布日期!");
        }
        if (Utils.isEmpty(id)) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "请传入要更新的内容id");
        }

        //更新数据
        Timestamp sqlTime = new Timestamp(System.currentTimeMillis());
        SysContent sysContent = new SysContent();
        sysContent.setId(Long.parseLong(id));
        sysContent.setChannel_id(channel_id);
        sysContent.setSection_id(section_id);
        sysContent.setDissertation_id(dissertation_id);
        sysContent.setArticle_type_id(article_type_id);
        sysContent.setTitle(title);
        sysContent.setShort_title(short_title);
        sysContent.setTag(tag);
        sysContent.setDigest(digest);
        sysContent.setAuth_name(auth);
        sysContent.setContent(content);
        sysContent.setImage(image);
        sysContent.setPublish_date(Utils.strToSqlDate(publish_date));
        sysContent.setArchive_date(Utils.strToSqlDate(archive_date));
        sysContent.setUpdate_date(sqlTime);
        sysContent.setAudit_status(Definition.CONTENT_AUDIT_NONE);
        sysContent.setSort(sort);
        sysContent.setRoles(roles);

        return 1 == contentDao.updateContent(sysContent);
    }

    /** 审核内容 */
    @Override
    public boolean handleAuditContent(Map<String, Object> params){
        String id = (String) params.get("id");
        int audit_status = null == params.get("audit_status") ? 0 : (int) params.get("audit_status");

        //判断参数是否是删除，不是则抛出异常
        if (audit_status != Definition.CONTENT_AUDIT_SUCCESS && audit_status != Definition.CONTENT_AUDIT_BACK) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "请按照文档传入指定审核参数");
        }
        if (Utils.isEmpty(id)) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "请传入要审核的内容id");
        }

        return 1 == contentDao.auditContent(Long.parseLong(id), audit_status);
    }

    /**
     * 获取内容列表
     */
    @Override
    public List<Map<String, Object>> handleGetContentList(Map<String, Object> params){
        String channel_id = (String)params.get("channel_id");       //所属频道id
        String section_id = (String)params.get("section_id");       //所属专栏id
        String article_type_id = (String)params.get("article_type_id");       //所属内容类型id
        String title = (String)params.get("title");         //内容标题
        String auth = (String)params.get("auth");         //内容作者

        if(Utils.isNull(params.get("page"))){
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "请传入分页页码");
        }
        if(Utils.isNull(params.get("rows"))){
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "请传入每页行数");
        }
        int page = null == params.get("page") ? 0 : (int)params.get("page");        //分页页码
        int rows = null == params.get("rows") ? 0 : (int)params.get("rows");        //每页行数

        Map<String, Object> map = new HashMap<>();
        map.put("channel_id", channel_id);
        map.put("section_id", section_id);
        map.put("article_type_id", article_type_id);
        map.put("title", title);
        map.put("auth", auth);
        map.put("page", page);
        map.put("rows", rows);

        List<Map<String, Object>> result = contentDao.getContentList(map);
        for(Map<String, Object> resultMap : result){
            String channel = (String)resultMap.get("channel_id");
            String section = (String)resultMap.get("section_id");
            String article_type = (String)resultMap.get("article_type_id");

            resultMap.put("channel", contentDao.getChannelNameById(channel));
            resultMap.put("section", contentDao.getSectionNameById(section));
            resultMap.put("article_type", contentDao.getArticleTypeNameById(article_type));
        }

        return result;
    }

    /**
     * 根据id获取内容信息
     */
    @Override
    public SysContent handleGetContentById(Map<String, Object> params){
        String id = (String) params.get("id");

        if (Utils.isEmpty(id)) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "请传入内容id");
        }

        return contentDao.getContentById(Long.parseLong(id));
    }
}
