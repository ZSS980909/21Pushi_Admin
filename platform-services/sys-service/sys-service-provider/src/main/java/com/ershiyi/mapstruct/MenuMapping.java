package com.ershiyi.mapstruct;

import com.ershiyi.domain.SysMenu;
import com.ershiyi.dto.SysMenuDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 菜单DTO转换接口
 *
 * @author liy
 * @since 2020/06/08
 */
@Mapper
public interface MenuMapping {
    MenuMapping MAPPER = Mappers.getMapper(MenuMapping.class);

    SysMenu toSysMenuList(SysMenuDTO sysMenuDTO);
}
