package org.mateoospina.infrastructure.config;

import org.mateoospina.domain.listServices.ListCreator;
import org.mateoospina.domain.listServices.ListCreatorDefault;
import org.mateoospina.domain.listServices.ListServices;
import org.mateoospina.domain.listServices.ListServicesDefault;
import org.mateoospina.domain.persistence.ListRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListsConfiguration {

    @Bean
    public ListCreator providesListCreatorInstance(ListRepository listRepository){
        return new ListCreatorDefault(listRepository);
    }

    @Bean
    public ListServices providesListGeneratorInstance(ListRepository listRepository){
        return new ListServicesDefault(listRepository);
    }

}
