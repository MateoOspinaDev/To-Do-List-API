package org.mateoospina.infrastructure.config;

import org.mateoospina.domain.itemServices.ItemMediator;
import org.mateoospina.domain.itemServices.ItemMediatorDefault;
import org.mateoospina.domain.listServices.ListMediator;
import org.mateoospina.domain.listServices.ListMediatorDefault;
import org.mateoospina.domain.persistence.ItemRepository;
import org.mateoospina.domain.persistence.ListRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListsConfiguration {

    @Bean
    public ListMediator providesListCreatorInstance(ListRepository listRepository){
        return new ListMediatorDefault(listRepository);
    }

    @Bean
    public ItemMediator providesItemCreatorInstance(ItemRepository itemRepository){
        return new ItemMediatorDefault(itemRepository);
    }
}
