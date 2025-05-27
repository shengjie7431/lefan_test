package com.lefancrm.apicenter.backendapi.impl;

import cn.jpush.api.utils.StringUtils;
import com.lefancrm.apicenter.backendapi.BackendInfoPublishsApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.InfoPublishsDto;
import com.lefancrm.apicenter.dto.InfoPublishsForumDto;
import com.lefancrm.apicenter.dto.InfoPublishsForumFileDto;
import com.lefancrm.apicenter.dto.InfoPublishsSafeCompanyDto;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.ConvertToBeanUtil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by lixianfeng on 2018/11/22.
 */
@Service
@ApiService(descript = "信息排查API")
public class BackendInfoPublishsApiImpl extends BaseServiceImpl implements BackendInfoPublishsApi{
    @Autowired
    private InfoPublishsMapper infoPublishsMapper;
    @Autowired
    private InfoPublishsForumMapper infoPublishsForumMapper;
    @Autowired
    private InfoPublishsForumFileMapper infoPublishsForumFileMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private CommonFileMapper commonFileMapper;
    @Autowired
    private InfoPublishsSafeCompanyMapper infoPublishsSafeCompanyMapper;
    @Autowired
    private InfoSafeCompanyMapper infoSafeCompanyMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;

    @ApiMethod(descript = "排查清单" ,value = "backend-info-publishs-list")
    @Override
    public ApiResponse list(ApiRequest apiRequest) {
        Integer menuType = apiRequest.getInt("menuType");
        Long currentUserId = apiRequest.getLong("operatorId");//当前登录人id  apiReq.getCurrentUserId()
        if (menuType == 2){//我的排查
            apiRequest.put("createBy",currentUserId);
        }else if (menuType == 4){//参与排查
            apiRequest.put("currentUserId",currentUserId);//当前登陆人参与的排查
            apiRequest.put("menuType",4);
        }else if (menuType == 6){//排查清单
            apiRequest.put("isPublish",1);//已发布
        }else{
            apiRequest.put("userName","-1");//无数据
        }
        apiRequest.put("isDelete",0);//未删除
        setBackendPageSize(apiRequest);
        int count = infoPublishsMapper.findListSize(apiRequest);
        List<InfoPublishs> list = infoPublishsMapper.findList(apiRequest);
        return new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }

    @ApiMethod(descript = "排查清单保存/发布" ,value = "backend-info-publishs-save")
    @Override
    public ApiResponse save(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        InfoPublishs infoPublishs = infoPublishsMapper.selectByPrimaryKey(id);
        Long currentUserId = apiRequest.getLong("operatorId");//当前登录人id  apiReq.getCurrentUserId()
        Integer isPublish = apiRequest.getInt("isPublish");//0保存 1发布
        if (infoPublishs == null){
            infoPublishs = ConvertToBeanUtil.toBeanFromApiRequest(apiRequest,InfoPublishs.class);
            infoPublishs.setCreateBy(currentUserId);
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
            infoPublishs.setCreateName(userInfo.getUserName());
            infoPublishs.setCreateTime(new Date());
            infoPublishs.setIsPublish(isPublish);
            infoPublishs.setIsDelete(0);
            infoPublishsMapper.insert(infoPublishs);
            //保存协助保司信息
            String safeCompanys = apiRequest.get("safeCompanys") == null ? "" : apiRequest.get("safeCompanys").toString();
            savePSCompay(safeCompanys,infoPublishs.getId(),"");
        }else{
            infoPublishs = ConvertToBeanUtil.toBeanFromApiRequest(apiRequest,infoPublishs);
            infoPublishsMapper.updateByPrimaryKey(infoPublishs);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    /**
     * 保存协助保司信息
     * @param safeCompanys
     * @param publishsId
     */
    private void savePSCompay(String safeCompanys,Long publishsId,String choose){
        safeCompanys = safeCompanys == null ? "" : safeCompanys;
        String [] companyIds = safeCompanys.split(",");
        for (String companyId : companyIds) {
            if (StringUtils.isNotEmpty(companyId)){
                Map map = new HashMap();
                map.put("publishsId",publishsId);
                map.put("safeCompanyId",companyId);
                InfoPublishsSafeCompany infoPublishsSafeCompany = infoPublishsSafeCompanyMapper.selectInfoByPubIdandCompanyId(map);
                if ("del".equals(choose)){
                    if (infoPublishsSafeCompany != null){
                        infoPublishsSafeCompanyMapper.deleteByPrimaryKey(infoPublishsSafeCompany.getId());
                    }
                }else{
                    if (infoPublishsSafeCompany == null){
                        InfoPublishsSafeCompany safeCompany = new InfoPublishsSafeCompany();
                        safeCompany.setPublishsId(publishsId);
                        safeCompany.setSafeCompanyId(new Long(companyId));
                        infoPublishsSafeCompanyMapper.insert(safeCompany);
                    }
                }
            }
        }
    }

    @ApiMethod(descript = "回复排查" ,value = "backend-info-publishs-save-forum")
    @Override
    public ApiResponse saveForum(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        Long currentUserId = apiRequest.getLong("operatorId");//当前登录人id  apiReq.getCurrentUserId()
        String paths = apiRequest.getString("uploadPaths");
        InfoPublishsForum forum = infoPublishsForumMapper.selectByPrimaryKey(id);
        if (forum == null){
            forum = ConvertToBeanUtil.toBeanFromApiRequest(apiRequest,InfoPublishsForum.class);
            forum.setCreateBy(currentUserId);
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
            forum.setCreateName(userInfo.getUserName());
            forum.setCreateTime(new Date());
            infoPublishsForumMapper.insert(forum);
            if (StringUtils.isNotEmpty(paths)){
                String [] urls = paths.split(",");
                for (String url : urls) {
                    if (StringUtils.isNotEmpty(url)){
                        CommonFile commonFile = new CommonFile();
                        commonFile.setFilePath(url);
                        int firstName = url.lastIndexOf("/") + 1 ;
                        int lastName = url.lastIndexOf(".");
                        String name = url.substring(firstName,lastName);
                        commonFile.setFileName(name);
                        commonFile.setCreateTime(new Date());
                        commonFileMapper.insertSelective(commonFile);

                        InfoPublishsForumFile forumFile = new InfoPublishsForumFile();
                        forumFile.setFileId(commonFile.getId());
                        forumFile.setForumId(forum.getId());
                        infoPublishsForumFileMapper.insert(forumFile);
                    }
                }
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    @ApiMethod(descript = "排查信息修改获取" ,value = "backend-info-publishs-get")
    @Override
    public ApiResponse get(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        InfoPublishsDto dto = new InfoPublishsDto();
        InfoPublishs infoPublishs = infoPublishsMapper.selectByPrimaryKey(id);
        try {
            BeanUtils.copyProperties(dto,infoPublishs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,dto);
    }
    @ApiMethod(descript = "排查信息详情" ,value = "backend-info-publishs-info")
    @Override
    public ApiResponse info(ApiRequest apiRequest) {
        Long currentUserId = apiRequest.getLong("operatorId");//当前登录人id  apiReq.getCurrentUserId()
        InfoPublishsDto dto = new InfoPublishsDto();
        try {
            Long id = apiRequest.getLong("id");
            InfoPublishs infoPublishs = infoPublishsMapper.selectByPrimaryKey(id);
            BeanUtils.copyProperties(dto,infoPublishs);
            List<InfoPublishsForum> forums = infoPublishsForumMapper.findByPublishsId(dto.getId());
            List<InfoPublishsForumDto> forumDtos = new ArrayList<>();
            for (InfoPublishsForum forum : forums) {
                InfoPublishsForumDto forumDto =  new InfoPublishsForumDto();
                BeanUtils.copyProperties(forumDto,forum);
                List<InfoPublishsForumFile> forumFiles = infoPublishsForumFileMapper.findByPublishsForumId(forumDto.getId());
                List<InfoPublishsForumFileDto> forumFileDtos = new ArrayList<>();
                for (InfoPublishsForumFile forumFile : forumFiles) {
                    InfoPublishsForumFileDto forumFileDto = new InfoPublishsForumFileDto();
                    BeanUtils.copyProperties(forumFileDto,forumFile);
                    CommonFile commonFile = commonFileMapper.selectByPrimaryKey(forumFileDto.getFileId());
                    String path = commonFile.getFilePath();
                    int firstName = path.lastIndexOf(".") + 1;
                    String fileType = path.substring(firstName,path.length());
                    forumFileDto.setFileType(fileType);
                    forumFileDto.setFilePath(path);
                    forumFileDtos.add(forumFileDto);
                }
                forumDto.setInfoPublishsForumFiles(forumFileDtos);
                forumDtos.add(forumDto);
            }
            dto.setInfoPublishsForumDtos(forumDtos);
            String safeCompanys = "";
            String safeCompanyNames = "";
            List<InfoPublishsSafeCompany> publishsSafeCompanys = infoPublishsSafeCompanyMapper.selectListByPublishsId(id);
            int i = 0;
            for (InfoPublishsSafeCompany publishsSafeCompany : publishsSafeCompanys) {
                InfoSafeCompany infoSafeCompany = infoSafeCompanyMapper.selectByPrimaryKey(publishsSafeCompany.getSafeCompanyId());
                if (i == publishsSafeCompanys.size() - 1){
                    safeCompanys = safeCompanys.concat(publishsSafeCompany.getSafeCompanyId().toString());
                    safeCompanyNames = safeCompanyNames.concat(infoSafeCompany.getSafeName());
                    break;
                }
                i++;
                safeCompanys = safeCompanys.concat(publishsSafeCompany.getSafeCompanyId() + ",");
                if (infoSafeCompany != null) {
                    safeCompanyNames = safeCompanyNames.concat(infoSafeCompany.getSafeName() + ",");
                }
            }
            dto.setSafeCompanys(safeCompanys);
            dto.setSafeCompanyNames(safeCompanyNames);
            List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
            dto.setIsAssignRole(isRoleUser(userRoles, 43L));//排查分配着
        }catch (Exception e){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,dto);
    }

    private Boolean isRoleUser(List<BusUserRole> busUserRoles,Long roleId){
        for (BusUserRole busUserRole : busUserRoles){
            if (busUserRole.getRoleId() == roleId){
                return true;
            }
        }
        return false;
    }

    @Override
    public ApiResponse delete(ApiRequest apiRequest) {
        return null;
    }


    /**
     *
     * @param apiRequest
     * @return
     */
    @ApiMethod(descript = "轮播附件" ,value = "backend-info-publishs-forum-file-info")
    @Override
    public ApiResponse fileShow(ApiRequest apiRequest) {
        List<InfoPublishsForumFile> forumFiles = infoPublishsForumFileMapper.findByPublishsForumId(apiRequest.getLong("forumId"));
        List<InfoPublishsForumFileDto> forumFileDtos = new ArrayList<>();
        for (InfoPublishsForumFile forumFile : forumFiles) {
            InfoPublishsForumFileDto forumFileDto = new InfoPublishsForumFileDto();
            try {
                BeanUtils.copyProperties(forumFileDto,forumFile);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            CommonFile commonFile = commonFileMapper.selectByPrimaryKey(forumFileDto.getFileId());
            String path = commonFile.getFilePath();
            int firstName = path.lastIndexOf(".") + 1;
            String fileType = path.substring(firstName,path.length());
            if("gif".equals(fileType) || "jpg".equals(fileType) || "jpeg".equals(fileType) || "bmp".equals(fileType) || "png".equals(fileType)){
                fileType ="jpg";
            }else if("txt".equals(fileType)){
                fileType ="txt";
            }else if("doc".equals(fileType) || "docx".equals(fileType)){
                fileType ="doc";
            }else if("xls".equals(fileType) || "xlsx".equals(fileType)){
                fileType ="xls";
            }else if("pdf".equals(fileType)){
                fileType ="pdf";
            }else{
                fileType ="other";
            }
            forumFileDto.setFileType(fileType);
            forumFileDto.setFilePath(path);
            forumFileDtos.add(forumFileDto);
        }

        return new ApiResponse(ApiMsgEnum.SUCCESS,1,forumFileDtos);
    }

    @ApiMethod(descript = "分配保险公司确认", value = "backend-info-publishs-select-companyok")
    @Override
    public ApiResponse selectCompanyOK(ApiRequest apiRequest) {
        String safeCompanys = apiRequest.get("safeCompanys") == null ? "" : apiRequest.get("safeCompanys").toString();
        Long publishsId = apiRequest.getLong("publishsId");
        String choose = apiRequest.get("choose") == null ? "" : apiRequest.get("choose").toString();
        savePSCompay(safeCompanys,publishsId,choose);
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    @ApiMethod(descript = "查询可移除的保险公司", value = "backend-info-safe-company-listed")
    @Override
    public ApiResponse companyListed(ApiRequest apiRequest) {
        Long publishsId = apiRequest.getLong("id");
        Map map = new HashMap();
        map.put("publishsId",publishsId);
        map.put("safeName",apiRequest.get("safeName"));
        map.put("safeUser",apiRequest.get("safeUser"));
        map.put("safeTel",apiRequest.get("safeTel"));
        List<InfoSafeCompany>  infoPublishsSafeCompanies = infoPublishsSafeCompanyMapper.selectListDtoByPublishsIdYremove(map);
        return new ApiResponse(ApiMsgEnum.SUCCESS,infoPublishsSafeCompanies.size(),infoPublishsSafeCompanies);
    }

    @ApiMethod(descript = "查询可选择的保险公司", value = "backend-info-safe-company-listing")
    @Override
    public ApiResponse companyListing(ApiRequest apiRequest) {
        Long publishsId = apiRequest.getString("id") == null ? -1 : new Long(apiRequest.getString("id").toString());
        Map map = new HashMap();
        map.put("publishsId",publishsId);
        map.put("safeName",apiRequest.get("safeName"));
        map.put("safeUser",apiRequest.get("safeUser"));
        map.put("safeTel",apiRequest.get("safeTel"));
        List<InfoSafeCompany>  infoPublishsSafeCompanies = infoPublishsSafeCompanyMapper.selectListDtoByPublishsIdYselect(map);
        return new ApiResponse(ApiMsgEnum.SUCCESS,infoPublishsSafeCompanies.size(),infoPublishsSafeCompanies);
    }


}
