package com.gruposv.microservice_adm_and_config.modules.address.service;

import com.gruposv.microservice_adm_and_config.modules.address.entity.AddressEntity;
import com.gruposv.microservice_adm_and_config.modules.address.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AddressEntity create(AddressEntity address){
        return addressRepository.save(address);
    }
}
