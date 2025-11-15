package com.example.backend.controllers;

import com.example.backend.dtos.AnteRequest;
import com.example.backend.dtos.GameSetupRequest;
import com.example.backend.dtos.GameStateResponse;
import com.example.backend.dtos.PlayerActionRequest;
import com.example.backend.models.*;
import com.example.backend.services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/game")
public class GameController {
    private final GameService game;
    public GameController(GameService game) {
        this.game = game;
    }
    @PostMapping("/start")
    public ResponseEntity<GameStateResponse> SetupGame(@RequestBody GameSetupRequest gameSetupRequest) {
        this.game.setup(gameSetupRequest.getDeckNo(), gameSetupRequest.getChipNo());
       GameStateResponse response = new GameStateResponse(
                game.getPlayer().getHands(),
                game.getDealer().getCards(),
                game.getPlayer().getChips(),
                game.getPlayer().getActiveHandIndex(),
                game.getPlayer().turnOver(),
                game.getHandRunning(),
                game.getTrueCount()
        );
       return ResponseEntity.ok(response);
    }
    @PostMapping("/hand/add")
    public ResponseEntity<GameStateResponse> AddHand(@RequestBody AnteRequest anteRequest) {
        this.game.addHand(anteRequest.getAnte());
        GameStateResponse response = new GameStateResponse(
                game.getPlayer().getHands(),
                game.getDealer().getCards(),
                game.getPlayer().getChips(),
                game.getPlayer().getActiveHandIndex(),
                game.getPlayer().turnOver(),
                game.getHandRunning(),
                game.getTrueCount()
        );
        return ResponseEntity.ok(response);

    }
    @PostMapping("/hand/start")
    public ResponseEntity<GameStateResponse> StartHand() {
        if (!game.getPlayer().getHands().isEmpty()) {
            game.startHand();
            game.checkHandStatus();
        }
        GameStateResponse response = new GameStateResponse(
                game.getPlayer().getHands(),
                game.getDealer().getCards(),
                game.getPlayer().getChips(),
                game.getPlayer().getActiveHandIndex(),
                game.getPlayer().turnOver(),
                game.getHandRunning(),
                game.getTrueCount()
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping("/hand/get")
    public ArrayList<PlayerHand> GetHands() {
        return game.getPlayer().getHands();
    }
    @GetMapping("/dealer/get")
    public Hand GetDealerHand() {
        return game.getDealer().getCards();
    }
    @PostMapping("/player/action")
    public ResponseEntity<GameStateResponse> playerAction(@RequestBody PlayerActionRequest request) {
        if (!game.getPlayer().turnOver() && game.getHandRunning()) {
            game.playerAction(request.getAction());
        }

        GameStateResponse response = new GameStateResponse(
                game.getPlayer().getHands(),
                game.getDealer().getCards(),
                game.getPlayer().getChips(),
                game.getPlayer().getActiveHandIndex(),
                game.getPlayer().turnOver(),
                game.getHandRunning(),
                game.getTrueCount()
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping("/resolve")
    public ResponseEntity<GameStateResponse> resolveGame() {
        if (game.getPlayer().turnOver()) {
            game.resolveRound();
        }
        GameStateResponse response = new GameStateResponse(
                game.getPlayer().getHands(),
                game.getDealer().getCards(),
                game.getPlayer().getChips(),
                game.getPlayer().getActiveHandIndex(),
                game.getPlayer().turnOver(),
                game.getHandRunning(),
                game.getTrueCount()
        );
        return ResponseEntity.ok(response);
    }
}
