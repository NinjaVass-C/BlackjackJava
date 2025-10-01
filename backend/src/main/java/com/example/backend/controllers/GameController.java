package com.example.backend.controllers;

import com.example.backend.dtos.GameSetupRequest;
import com.example.backend.dtos.GameStateResponse;
import com.example.backend.dtos.PlayerActionRequest;
import com.example.backend.models.Hand;
import com.example.backend.models.PlayerHand;
import com.example.backend.services.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/api/game")
public class GameController {
    GameService game;
    @PostMapping("/start")
    public Map<String,String> SetupGame(@RequestBody GameSetupRequest gameSetupRequest) {
        game = new GameService(gameSetupRequest.getDeckNo(), gameSetupRequest.getChipNo());
        return Map.of("status", "success");
    }
    @PostMapping("/hand/add")
    public Map<String, String> AddHand(int ante) {
        boolean success = game.addHand(ante);
        if (success) {
            return Map.of("status", "success");
        }
        else {
            return Map.of("status", "fail");
        }
    }
    @PostMapping("/hand/start")
    public Map<String, String> StartHand() {
        game.startHand();
        return Map.of("status", "success");
    }
    @GetMapping("/hand/get")
    public ArrayList<PlayerHand> GetHands() {
        return game.getPlayer().getHands();
    }
    @GetMapping("/dealer/get")
    public Hand GetDealerHand() {
        return game.getDealer().getHand();
    }
    @PostMapping("/player/action")
    public GameStateResponse playerAction(@RequestBody PlayerActionRequest request) {
        game.playerAction(request.getAction());
        return new GameStateResponse(
                game.getPlayer().getHands(),
                game.getDealer().getHand(),
                game.getPlayer().getChips(),
                game.getPlayer().getActiveHandIndex()
        );
    }
    @GetMapping("/game/resolve")
    public GameStateResponse resolveGame() {
        game.resolveRound();
        return new GameStateResponse(
                game.getPlayer().getHands(),
                game.getDealer().getHand(),
                game.getPlayer().getChips(),
                game.getPlayer().getActiveHandIndex()
        );
    }
}
