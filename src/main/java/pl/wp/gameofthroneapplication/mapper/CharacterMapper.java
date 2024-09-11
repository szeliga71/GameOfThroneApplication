package pl.wp.gameofthroneapplication.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import pl.wp.gameofthroneapplication.model.Character;

import java.util.ArrayList;
import java.util.List;

public class CharacterMapper {
    private final static ObjectMapper mapper = createObjectMapper();

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    public static Character jsonToCharacter(String json) throws JsonProcessingException {

        JsonNode firstRootNode = mapper.readTree(json);
        JsonNode rootNode = null;
        if (firstRootNode.isArray()) {
            rootNode = firstRootNode.get(0);
        } else rootNode = firstRootNode;

        String name = rootNode.get("name").asText();
        String gender = rootNode.get("gender").asText();
        String culture = rootNode.get("culture").asText();

        JsonNode titlesNode = rootNode.get("titles");
        List<String> titles = new ArrayList();
        if (titlesNode.isArray()) {
            for (JsonNode node : titlesNode) {
                titles.add(node.toString());
            }
        }
        JsonNode aliasesNode = rootNode.get("aliases");
        List<String> aliases = new ArrayList();
        if (aliasesNode.isArray()) {
            for (JsonNode node : aliasesNode) {
                aliases.add(node.toString());
            }
        }
        JsonNode allegiancesNode = rootNode.get("allegiances");
        List<String> allegiances = new ArrayList();
        if (allegiancesNode.isArray()) {
            for (JsonNode node : allegiancesNode) {
                allegiances.add(node.toString());
            }
        }
        JsonNode playedByNode = rootNode.get("playedBy");
        List<String> playedBy = new ArrayList();
        if (playedByNode.isArray()) {
            for (JsonNode node : playedByNode) {
                playedBy.add(node.toString());
            }
        }
        JsonNode tvSeriesNode = rootNode.get("tvSeries");
        List<String> tvSeriesSeasons = new ArrayList();
        if (tvSeriesNode.isArray()) {
            for (JsonNode node : tvSeriesNode) {
                tvSeriesSeasons.add(node.toString());
            }
        }
        String mother = rootNode.get("mother").asText();
        String father = rootNode.get("father").asText();
        String spouse = rootNode.get("spouse").asText();

        return new Character(name, gender, culture, titles, aliases, allegiances, playedBy, tvSeriesSeasons, mother, father, spouse);
    }

    public static List<Character> jsonToCharacterList(String json) throws JsonProcessingException {
        JsonNode rootNode = mapper.readTree(json);
        List<Character> characters = new ArrayList<>();
        if (rootNode.isArray()) {
            for (JsonNode node : rootNode) {
                characters.add(jsonToCharacter(node.toString()));
            }
        }
        return characters;
    }
}