package org.mateoospina.infrastructure.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.mateoospina.domain.entities.Item;

public class ItemMapper {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static Item jsonPatchToToDoList(JsonPatch patch, Item item) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(item, JsonNode.class));
        return objectMapper.treeToValue(patched, Item.class);
    }
}
