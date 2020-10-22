package com.IceHockeyLeague.LeagueManager.Player;

public enum PlayerPosition {
    FORWARD {
        @Override
        public String toString() {
            return "forward";
        }
    },
    DEFENSE {
        @Override
        public String toString() {
            return "defense";
        }
    },
    GOALIE {
        @Override
        public String toString() {
            return "goalie";
        }
    }
}
