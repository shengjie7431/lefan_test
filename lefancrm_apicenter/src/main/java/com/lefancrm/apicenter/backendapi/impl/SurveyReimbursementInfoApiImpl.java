package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.SurveyReimbursementInfoApi;
import com.lefancrm.apicenter.dao.InvestigatorReInfoMapper;
import com.lefancrm.apicenter.dao.SurveyCaseDirectionMapper;
import com.lefancrm.apicenter.dao.SurveyReimbursementFileMapper;
import com.lefancrm.apicenter.dao.SurveyReimbursementInfoMapper;
import com.lefancrm.apicenter.dto.SurveyReimbursementFileDto;
import com.lefancrm.apicenter.dto.SurveyReimbursementInfoDto;
import com.lefancrm.apicenter.model.InvestigatorReInfo;
import com.lefancrm.apicenter.model.SurveyCaseDirection;
import com.lefancrm.apicenter.model.SurveyReimbursementFile;
import com.lefancrm.apicenter.model.SurveyReimbursementInfo;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 调查费用报销登记表(SurveyReimbursementInfo)表服务实现类
 *
 * @author makejava
 * @since 2020-04-09 17:06:39
 */
@Service
@ApiService(descript = "调查费用登记报销表API")
public class SurveyReimbursementInfoApiImpl extends BaseServiceImpl implements SurveyReimbursementInfoApi {
    @Resource
    private SurveyReimbursementInfoMapper surveyReimbursementInfoMapper;
    @Autowired
    private SurveyReimbursementFileMapper surveyReimbursementFileMapper;
    @Autowired
    private InvestigatorReInfoMapper investigatorReInfoMapper;
    @Autowired
    private SurveyCaseDirectionMapper surveyCaseDirectionMapper;


    @Override
    public SurveyReimbursementInfo queryById(Long id) {
        return surveyReimbursementInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SurveyReimbursementInfo> queryAllByLimit(int offset, int limit) {
        return null;
    }

    /**
     * 新增数据
     *
     * @param surveyReimbursementInfo 实例对象
     * @return 实例对象
     */
    @Override
    public SurveyReimbursementInfo insert(SurveyReimbursementInfo surveyReimbursementInfo) {
        this.surveyReimbursementInfoMapper.insert(surveyReimbursementInfo);
        return surveyReimbursementInfo;
    }

    @Override
    public SurveyReimbursementInfo update(SurveyReimbursementInfo surveyReimbursementInfo) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @ApiMethod(needLogin = false,descript = "费用报销登记信息",value = "backend-survey-reimbursement-info")
    @Override
    public ApiResponse selectBySurveyDirectionId(ApiRequest apiRequest) {
        Long surveyDirectionId = apiRequest.getLong("directionId");
        SurveyReimbursementInfoDto surveyReimbursementInfoDto = new SurveyReimbursementInfoDto();
        SurveyCaseDirection surveyCaseDirection = surveyCaseDirectionMapper.selectByPrimaryKey(surveyDirectionId);
        InvestigatorReInfo investigatorReInfo = investigatorReInfoMapper.selectByInvestigatorCaseId(Long.valueOf(surveyCaseDirection.getSurveyInvestigatorCaseId()));
        SurveyReimbursementInfo surveyReimbursementInfo = surveyReimbursementInfoMapper.selectBySurveyDirectionId(surveyDirectionId);
        if (surveyReimbursementInfo!=null){
            List<SurveyReimbursementFileDto> surveyReimbursementFileList = surveyReimbursementFileMapper.selectByReimbursementId(surveyReimbursementInfo.getId());
            SurveyReimbursementFileDto fileDto = new SurveyReimbursementFileDto();
            for (int i = 0; i < surveyReimbursementFileList.size(); i++) {
                SurveyReimbursementFileDto dto = surveyReimbursementFileList.get(i);
                String fileCode = dto.getFileCode();
                if ("medh".equals(fileCode)){
                    fileDto.setMedhCount(fileDto.getMedhCount()+1);
                }else if ("trou".equals(fileCode)){
                    fileDto.setTrouCount(fileDto.getTrouCount()+1);
                }else if ("print".equals(fileCode)){
                    fileDto.setPrintCount(fileDto.getPrintCount()+1);
                }else if ("acco".equals(fileCode)){
                    fileDto.setAccoCount(fileDto.getAccoCount()+1);
                }else if ("car".equals(fileCode)){
                    fileDto.setCarCount(fileDto.getCarCount()+1);
                }else if ("train".equals(fileCode)){
                    fileDto.setTrainCount(fileDto.getTrainCount()+1);
                }else if ("aircraft".equals(fileCode)){
                    fileDto.setAircraftCount(fileDto.getAircraftCount()+1);
                }else if ("self".equals(fileCode)){
                    fileDto.setSelfCount(fileDto.getSelfCount()+1);
                }else if ("other".equals(fileCode)){
                    fileDto.setOtherCount(fileDto.getOtherCount()+1);
                }else if ("inner".equals(fileCode)){
                    fileDto.setInnerCount(fileDto.getInnerCount()+1);
                }
            }
            surveyReimbursementInfo.setSurveyReimbursementFileDto(fileDto);
            try {
                BeanUtils.copyProperties(surveyReimbursementInfoDto,surveyReimbursementInfo);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        if (investigatorReInfo == null) {
            surveyReimbursementInfoDto.setReState(0);
        }else{
            surveyReimbursementInfoDto.setReState(investigatorReInfo.getReState());
        }

        return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyReimbursementInfoDto);
    }


}