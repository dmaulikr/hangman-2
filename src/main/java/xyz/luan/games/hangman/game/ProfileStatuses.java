package xyz.luan.games.hangman.game;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProfileStatuses implements Serializable {

    private static final long serialVersionUID = -4130788656225420671L;

    private int gamesPlayed = 0;
    private double hoursLoggedIn = 0;
    private double hoursPlayed = 0;

}