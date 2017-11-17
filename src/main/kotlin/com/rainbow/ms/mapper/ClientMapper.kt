package com.rainbow.ms.mapper

import com.rainbow.ms.commons.SuperMapper
import com.rainbow.ms.entity.Client
import org.apache.ibatis.annotations.Mapper

@Mapper
interface ClientMapper : SuperMapper<Client>