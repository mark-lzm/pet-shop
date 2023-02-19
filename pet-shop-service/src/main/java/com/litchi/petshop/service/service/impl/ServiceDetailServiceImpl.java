package com.litchi.petshop.service.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.litchi.common.utils.PetPageUtils;
import com.litchi.petshop.service.entity.ServiceEntity;
import com.litchi.petshop.service.entity.ServiceItemSubclassEntity;
import com.litchi.petshop.service.feign.AdminFeignService;
import com.litchi.petshop.service.feign.MemberFeignService;
import com.litchi.petshop.service.feign.PetFeignService;
import com.litchi.petshop.service.service.ServiceItemSubclassService;
import com.litchi.petshop.service.service.ServiceService;
import com.litchi.petshop.service.vo.ServiceDetailVo;
import com.litchi.pojo.admin.dto.EmployeeDto;
import com.litchi.pojo.member.dto.MemberDto;
import com.litchi.pojo.pet.dto.PetDto;
import com.litchi.pojo.service.dto.ServiceDetailDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.litchi.common.utils.PageUtils;
import com.litchi.common.utils.Query;

import com.litchi.petshop.service.dao.ServiceDetailDao;
import com.litchi.petshop.service.entity.ServiceDetailEntity;
import com.litchi.petshop.service.service.ServiceDetailService;


@Service("serviceDetailService")
public class ServiceDetailServiceImpl extends ServiceImpl<ServiceDetailDao, ServiceDetailEntity> implements ServiceDetailService {

    @Autowired
    ServiceService serviceService;

    @Autowired
    MemberFeignService memberFeignService;

    @Autowired
    PetFeignService petFeignService;

    @Autowired
    ServiceItemSubclassService serviceItemSubclassService;

    @Autowired
    AdminFeignService adminFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<ServiceDetailVo> voList = new ArrayList<>();

        QueryWrapper<ServiceDetailEntity> wrapper = new QueryWrapper<>();

        String key = (String) params.get("key");
        //key检索
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((obj) -> {
                obj.eq("service_detail_id", key).or().eq("service_id", key);
            });
        }

        List<ServiceDetailEntity> entities = this.list(wrapper);
        for (ServiceDetailEntity entity : entities) {
            ServiceDetailVo vo = new ServiceDetailVo();
            BeanUtils.copyProperties(entity, vo);
            ServiceEntity service = serviceService.getById(entity.getServiceId());
            Integer memberId = service.getMemberId();
            MemberDto memberDto = memberFeignService.memberById(memberId);
            vo.setMemberName(memberDto.getName());

            Integer petId = entity.getPetId();
            PetDto petDto = petFeignService.selectByPetId(petId);
            vo.setPetName(petDto.getName());


            ServiceItemSubclassEntity serviceItemSubclass = serviceItemSubclassService.getById(entity.getServiceItemSubId());
            vo.setServiceItemSubName(serviceItemSubclass.getServiceItemSubName());

            EmployeeDto employeedto = adminFeignService.employeedto(entity.getEmployeeId());
            vo.setEmployeeName(employeedto.getName());

            voList.add(vo);
        }

        if (params.get("page") != null && params.get("limit") != null) {
            Integer pageIndex = Integer.parseInt((String) params.get("page"));
            Integer limit = Integer.parseInt((String) params.get("limit"));
            return PetPageUtils.getPageUtils(pageIndex, limit, voList);
        }

        Page<ServiceDetailVo> page = new Page<>();
        page.setRecords(voList);
        page.setTotal(voList.size());
        return new PageUtils(page);
    }


    @Override
    public boolean isServiceEnd(Integer serviceId) {
        boolean flag = true;
        List<ServiceDetailEntity> serviceDetailEntities = this.list(new QueryWrapper<ServiceDetailEntity>().eq("service_id", serviceId));
        for (ServiceDetailEntity serviceDetailEntity : serviceDetailEntities) {
            if (serviceDetailEntity.getServiceEndTag() == 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * 服务id，存在服务详情，返回true
     *
     * @param serviceId
     * @return
     */
    @Override
    public boolean existServiceDetail(Integer serviceId) {
        List<ServiceDetailEntity> serviceDetailEntities = this.list(new QueryWrapper<ServiceDetailEntity>().eq("service_id", serviceId));
        return serviceDetailEntities.size() != 0;
    }

    @Override
    public List<ServiceDetailDto> listAllServiceDetail() {
        List<ServiceDetailDto> result = new ArrayList<>();
        List<ServiceDetailEntity> list = this.list();
        for (ServiceDetailEntity serviceDetail : list) {
            ServiceDetailDto dto = new ServiceDetailDto();
            BeanUtils.copyProperties(serviceDetail, dto);
            result.add(dto);
        }
        return result;
    }

}