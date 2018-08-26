package com.boldseas.porscheshop.user.service;

import com.boldseas.porscheshop.dtos.LeadsDTO;
import com.boldseas.porscheshop.dtos.LeadsDealerSaveInputDTO;
import com.boldseas.porscheshop.dtos.UserDTO;
import com.boldseas.porscheshop.interceptor.UserProvider;
import com.boldseas.porscheshop.user.LeadsService;
import com.boldseas.porscheshop.user.beans.Leads;
import com.boldseas.porscheshop.user.beans.LeadsDealer;
import com.boldseas.porscheshop.user.repository.LeadsRepository;
import com.google.common.eventbus.AsyncEventBus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Chen Jingxuan
 * @version 2018/5/21
 */
@Service
public class LeadsServiceImpl implements LeadsService {
    private final UserProvider userProvider;
    private final LeadsRepository leadsRepository;
    private final ModelMapper mapper;
    private final AsyncEventBus eventBus;

    @Autowired
    public LeadsServiceImpl(UserProvider userProvider, LeadsRepository leadsRepository, ModelMapper mapper, AsyncEventBus eventBus) {
        this.userProvider = userProvider;
        this.leadsRepository = leadsRepository;
        this.mapper = mapper;
        this.eventBus = eventBus;
    }


    @Override
    public LeadsDTO save(List<LeadsDealerSaveInputDTO> leadsDealerSaveInputDTOList) {
        UserDTO userDTO = userProvider.getCurrentUser();
        Leads leads = mapper.map(userDTO, Leads.class);
        List<LeadsDealer> leadsDealers = leadsDealerSaveInputDTOList
                .stream()
                .map(leadsDealerSaveInputDTO -> mapper.map(leadsDealerSaveInputDTO, LeadsDealer.class))
                .collect(Collectors.toList());
        leads.setUserId(userDTO.getId());
        leads.setSource(userProvider.getUserSource());
        leads.setLeadsDealers(leadsDealers);
        leads = leadsRepository.save(leads);
        LeadsDTO leadsDTO = mapper.map(leads, LeadsDTO.class);
        eventBus.post(leadsDTO);
        return leadsDTO;
    }

    @Override
    public LeadsDTO getLast(Long userId) {
        List<Leads> leadsList = leadsRepository.findByUserId(userId);
        Leads lastLeads = leadsList.stream()
                .sorted((l1, l2) -> l2.getCreateDate().compareTo(l1.getCreateDate()))
                .findFirst()
                .orElse(new Leads());
        return mapper.map(lastLeads, LeadsDTO.class);
    }

    @Override
    public LeadsDTO get(Long leadsId) {
        Leads leads = leadsRepository.findOne(leadsId);
        return mapper.map(leads, LeadsDTO.class);
    }

    @Override
    public List<LeadsDTO> findBefore(LocalDateTime dateTime) {
        return leadsRepository.findByCreateDateBefore(dateTime)
                .stream()
                .map(leads -> mapper.map(leads, LeadsDTO.class))
                .collect(Collectors.toList());
    }
}
