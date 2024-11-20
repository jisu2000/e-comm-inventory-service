package org.jisu.e_comm_inventory_service.config;

import org.jisu.e_comm_inventory_service.dto.request.DiscountRequest;
import org.jisu.e_comm_inventory_service.model.DiscountEO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansProvider {
    
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

//        modelMapper.addMappings(
//                new PropertyMap<DiscountRequest, DiscountEO>() {
//
//                    @Override
//                    protected void configure() {
//                        map().setProductId(source.getProductId());
//                    }
//                }
//        );



        return modelMapper;
    }

    
}
