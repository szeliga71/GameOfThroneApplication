package pl.wp.gameofthroneapplication.connection;

import pl.wp.gameofthroneapplication.mapper.CharacterMapper;
import pl.wp.gameofthroneapplication.model.Character;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;


public class CharacterAPIHandler {
    private final HttpClient client = HttpClient.newHttpClient();
    private final String CHARACTER_API_URI = "https://www.anapioficeandfire.com/api/characters/";

    public Character getCharacterById(String id) {
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(CHARACTER_API_URI + id)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return CharacterMapper.jsonToCharacter(response.body());
        } catch (URISyntaxException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Character getCharacterByName(String characterName) {
        String encodeCharacterName = URLEncoder.encode(characterName, StandardCharsets.UTF_8);
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(CHARACTER_API_URI + "?name=" + encodeCharacterName)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return CharacterMapper.jsonToCharacter(response.body());
        } catch (IOException | InterruptedException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}