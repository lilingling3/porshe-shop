package com.boldseas.porscheshop.user;

import com.boldseas.porscheshop.dtos.LeadsDTO;
import com.boldseas.porscheshop.dtos.LeadsDealerSaveInputDTO;
import com.boldseas.porscheshop.dtos.UserDTO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Chen Jingxuan
 * @version 2018/5/21
 */
public interface LeadsService {
    /**
     * 保存线索
     * @param leadsDealerSaveInputDTOList
     * @return
     */
    LeadsDTO save(List<LeadsDealerSaveInputDTO> leadsDealerSaveInputDTOList);

    /**
     * 获取某一用户的最后一条线索信息
     * @param userId
     * @return
     */
    LeadsDTO getLast(Long userId);

    /**
     * 根据leads id获取某一leads信息
     * @param leadsId
     * @return
     */
    LeadsDTO get(Long leadsId);

    /**
     * 获取某一时点之前的所有leads
     * @param dateTime
     * @return
     */
    List<LeadsDTO> findBefore(LocalDateTime dateTime);
}
