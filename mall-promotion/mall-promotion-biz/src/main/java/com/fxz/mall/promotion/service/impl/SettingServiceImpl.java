package com.fxz.mall.promotion.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.mall.promotion.entity.Setting;
import com.fxz.mall.promotion.mapper.SettingMapper;
import com.fxz.mall.promotion.service.SettingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author fxz
 * @date 2022-08-09
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SettingServiceImpl extends ServiceImpl<SettingMapper, Setting> implements SettingService {

}