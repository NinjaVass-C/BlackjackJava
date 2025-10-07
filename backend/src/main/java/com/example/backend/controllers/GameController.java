package com.example.backend.controllers;

import com.example.backend.dtos.GameSetupRequest;
import com.example.backend.dtos.GameStateResponse;
import com.example.backend.dtos.PlayerActionRequest;
import com.example.backend.models.*;
import com.example.backend.services.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/api/game")
public class GameController {
    GameService game;
    @PostMapping("/start")
    public GameStateResponse SetupGame(@RequestBody GameSetupRequest gameSetupRequest, GameService gameService) {
        this.game = gameService;
        this.game.setup(gameSetupRequest.getDeckNo(), gameSetupRequest.getChipNo());
        return new GameStateResponse(
                game.getPlayer().getHands(),
                game.getDealer().getHand(),
                game.getPlayer().getChips(),
                game.getPlayer().getActiveHandIndex()
        );
    }
    @PostMapping("/hand/add")
    public GameStateResponse AddHand(int ante) {
        boolean success = game.addHand(ante);
        return new GameStateResponse(
                game.getPlayer().getHands(),
                game.getDealer().getHand(),
                game.getPlayer().getChips(),
                game.getPlayer().getActiveHandIndex()
        );

    }
    @PostMapping("/hand/start")
    public GameStateResponse StartHand() {
        game.startHand();
        return new GameStateResponse(
                game.getPlayer().getHands(),
                game.getDealer().getHand(),
                game.getPlayer().getChips(),
                game.getPlayer().getActiveHandIndex()
        );
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
