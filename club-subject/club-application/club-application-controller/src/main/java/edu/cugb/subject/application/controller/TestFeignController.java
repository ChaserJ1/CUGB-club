package edu.cugb.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import edu.cugb.subject.application.convert.SubjectAnswerDTOConverter;
import edu.cugb.subject.application.convert.SubjectInfoDTOConverter;
import edu.cugb.subject.application.dto.SubjectInfoDTO;
import edu.cugb.subject.common.entity.PageResult;
import edu.cugb.subject.common.entity.Result;
import edu.cugb.subject.domain.entity.SubjectAnswerBO;
import edu.cugb.subject.domain.entity.SubjectInfoBO;
import edu.cugb.subject.domain.service.SubjectInfoDomainService;
import edu.cugb.subject.infra.entity.UserInfo;
import edu.cugb.subject.infra.rpc.UserRPC;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @Author pengjia
 * @Data 2024/10/16 9:22
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping("/subject")
public class TestFeignController {
    @Resource
    private UserRPC userRPC;
    @GetMapping("/testFeign")
    public void testFeign(){
        UserInfo userInfo = userRPC.getUserInfo("123");
          if (log.isInfoEnabled()){
            log.info("testFeign.userInfo:{}",userInfo);
        }

    }





}
